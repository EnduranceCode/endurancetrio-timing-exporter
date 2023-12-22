/*
 * MIT License
 *
 * Copyright (c) 2023 Ricardo do Canto
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.endurancetrio.timingexporter.service.mylaps.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.endurancetrio.timingexporter.mapper.TimeRecordMapper;
import com.endurancetrio.timingexporter.model.dto.common.TimeRecordDTO;
import com.endurancetrio.timingexporter.model.entity.common.EnduranceTrioWaypoint;
import com.endurancetrio.timingexporter.model.entity.mylaps.MylapsTimes;
import com.endurancetrio.timingexporter.model.exception.EnduranceTrioException;
import com.endurancetrio.timingexporter.repository.mylaps.MylapsTimesRepository;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class MylapsTimesServiceImpTest {

  @InjectMocks
  private MylapsTimesServiceImp mylapsTimesService;

  @Mock
  private MylapsTimesRepository mylapsTimesRepository;

  @Mock
  private TimeRecordMapper timeRecordMapper;

  private final List<MylapsTimes> testData = new ArrayList<>();
  private MylapsTimes testMylapsTimes1;
  private MylapsTimes testMylapsTimes2;
  private MylapsTimes testMylapsTimes3;
  private MylapsTimes testMylapsTimes9;

  @BeforeEach
  void setUp() {
    OffsetDateTime testChipTime1 = OffsetDateTime.parse("1984-08-15T15:07:00+00:00");
    OffsetDateTime testChipTime2 = OffsetDateTime.parse("1984-08-15T15:07:06+00:00");
    OffsetDateTime testChipTime3 = OffsetDateTime.parse("1984-08-15T15:07:12+00:00");

    testMylapsTimes1 =
        MylapsTimes.builder().id(1L).chip("AAAAAAA").chipTime(testChipTime1).milliSecs(54420150L)
                   .location("WA").lapRaw(1).build();
    testMylapsTimes2 =
        MylapsTimes.builder().id(2L).chip("AAAAAAB").chipTime(testChipTime2).milliSecs(54426200L)
                   .location("WA").lapRaw(1).build();
    testMylapsTimes3 =
        MylapsTimes.builder().id(3L).chip("AAAAAAC").chipTime(testChipTime3).milliSecs(54432250L)
                   .location("WA").lapRaw(1).build();
    testMylapsTimes9 =
        MylapsTimes.builder().id(9L).chip("AAAAAAC").chipTime(testChipTime3).milliSecs(54432250L)
                   .location("WA").lapRaw(1).build();

    testData.addAll(
        List.of(testMylapsTimes1, testMylapsTimes2, testMylapsTimes3, testMylapsTimes9));
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void findByChipTimeDate() throws EnduranceTrioException {
    OffsetDateTime testStart = OffsetDateTime.parse("1984-08-15T00:00:00+00:00");
    OffsetDateTime testEnd = testStart.plusDays(1L);

    OffsetDateTime testTime1 = OffsetDateTime.parse("1984-08-15T15:07:00.15+00:00");
    OffsetDateTime testTime2 = OffsetDateTime.parse("1984-08-15T15:07:06.20+00:00");
    OffsetDateTime testTime3 = OffsetDateTime.parse("1984-08-15T15:07:12.25+00:00");

    TimeRecordDTO recordDTO1 = TimeRecordDTO.builder().chip("AAAAAAA").time(testTime1.toInstant())
                                            .waypoint(EnduranceTrioWaypoint.WA).lap(1).build();
    TimeRecordDTO recordDTO2 = TimeRecordDTO.builder().chip("AAAAAAB").time(testTime2.toInstant())
                                            .waypoint(EnduranceTrioWaypoint.WA).lap(1).build();
    TimeRecordDTO recordDTO3 = TimeRecordDTO.builder().chip("AAAAAAC").time(testTime3.toInstant())
                                            .waypoint(EnduranceTrioWaypoint.WA).lap(1).build();
    TimeRecordDTO recordDTO9 = TimeRecordDTO.builder().chip("AAAAAAC").time(testTime3.toInstant())
                                            .waypoint(EnduranceTrioWaypoint.WA).lap(1).build();

    when(mylapsTimesRepository.findByChipTimeBetween(testStart, testEnd)).thenReturn(testData);
    when(timeRecordMapper.map(testMylapsTimes1)).thenReturn(recordDTO1);
    when(timeRecordMapper.map(testMylapsTimes2)).thenReturn(recordDTO2);
    when(timeRecordMapper.map(testMylapsTimes3)).thenReturn(recordDTO3);
    when(timeRecordMapper.map(testMylapsTimes9)).thenReturn(recordDTO9);

    List<TimeRecordDTO> results = mylapsTimesService.findByChipTimeDate("1984-08-15");

    verify(mylapsTimesRepository, times(1)).findByChipTimeBetween(any(), any());
    verify(timeRecordMapper, times(4)).map(any());
    assertNotNull(results);
    assertEquals(3, results.size());
    assertEquals("AAAAAAA", results.get(0).getChip());
    assertEquals("AAAAAAB", results.get(1).getChip());
    assertEquals("AAAAAAC", results.get(2).getChip());
  }

  @Test
  void findByChipTimeDateAndThrowAnException() throws EnduranceTrioException {
    assertThrows(
        EnduranceTrioException.class, () -> mylapsTimesService.findByChipTimeDate("19840815"));
  }
}