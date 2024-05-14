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
import com.endurancetrio.timingexporter.model.constants.PathTimezone;
import com.endurancetrio.timingexporter.model.dto.common.TimeRecordDTO;
import com.endurancetrio.timingexporter.model.dto.common.TimingRecordDTO;
import com.endurancetrio.timingexporter.model.entity.common.EnduranceTrioWaypoint;
import com.endurancetrio.timingexporter.model.entity.mylaps.MylapsTimes;
import com.endurancetrio.timingexporter.model.exception.EnduranceTrioException;
import com.endurancetrio.timingexporter.repository.mylaps.MylapsTimesRepository;
import com.endurancetrio.timingexporter.utils.DateTimeUtils;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class MylapsTimesServiceImpTest {

  @InjectMocks
  MylapsTimesServiceImp mylapsTimesService;

  @Mock
  MylapsTimesRepository mylapsTimesRepository;

  @Mock
  TimeRecordMapper timeRecordMapper;

  final List<MylapsTimes> testData = new ArrayList<>();
  MylapsTimes testMylapsTimes1;
  MylapsTimes testMylapsTimes2;
  MylapsTimes testMylapsTimes3;
  MylapsTimes testMylapsTimes9;

  @BeforeEach
  void setUp() {
    LocalDateTime testChipTime1 = LocalDateTime.parse("1984-08-15T15:07:00");
    LocalDateTime testChipTime2 = LocalDateTime.parse("1984-08-15T15:07:06");
    LocalDateTime testChipTime3 = LocalDateTime.parse("1984-08-15T15:07:12");

    testMylapsTimes1 =
        MylapsTimes.builder().id(1L).chip("AAAAAAA").chipTime(testChipTime1).milliSecs(54420150)
                   .location("WA").lapRaw(1).build();
    testMylapsTimes2 =
        MylapsTimes.builder().id(2L).chip("AAAAAAB").chipTime(testChipTime2).milliSecs(54426200)
                   .location("WA").lapRaw(1).build();
    testMylapsTimes3 =
        MylapsTimes.builder().id(3L).chip("AAAAAAC").chipTime(testChipTime3).milliSecs(54432250)
                   .location("WA").lapRaw(1).build();
    testMylapsTimes9 =
        MylapsTimes.builder().id(9L).chip("AAAAAAC").chipTime(testChipTime3).milliSecs(54432250)
                   .location("WA").lapRaw(1).build();

    testData.addAll(
        List.of(testMylapsTimes1, testMylapsTimes2, testMylapsTimes3, testMylapsTimes9));
  }

  @Test
  void findByChipTimeDate() throws EnduranceTrioException {
    LocalDateTime testStart = LocalDateTime.parse("1984-08-15T00:00:00");
    LocalDateTime testEnd = testStart.plusDays(1L);

    LocalDateTime testTime1 = LocalDateTime.parse("1984-08-15T15:07:00.15");
    LocalDateTime testTime2 = LocalDateTime.parse("1984-08-15T15:07:06.20");
    LocalDateTime testTime3 = LocalDateTime.parse("1984-08-15T15:07:12.25");

    TimeRecordDTO recordDTO1 =
        TimeRecordDTO.builder().chip("AAAAAAA").time(testTime1.toInstant(ZoneOffset.UTC))
                     .waypoint(EnduranceTrioWaypoint.WA).lap(1).build();
    TimeRecordDTO recordDTO2 =
        TimeRecordDTO.builder().chip("AAAAAAB").time(testTime2.toInstant(ZoneOffset.UTC))
                     .waypoint(EnduranceTrioWaypoint.WA).lap(1).build();
    TimeRecordDTO recordDTO3 =
        TimeRecordDTO.builder().chip("AAAAAAC").time(testTime3.toInstant(ZoneOffset.UTC))
                     .waypoint(EnduranceTrioWaypoint.WA).lap(1).build();
    TimeRecordDTO recordDTO9 =
        TimeRecordDTO.builder().chip("AAAAAAC").time(testTime3.toInstant(ZoneOffset.UTC))
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
  void findByTimezoneAndChipTimeDate() throws EnduranceTrioException {
    String tzIdentifier = PathTimezone.LISBON.getTimezone();
    String date = "1984-08-15";

    ZoneId zoneId = DateTimeUtils.getZoneId(tzIdentifier);

    LocalDateTime testStart = LocalDateTime.parse("1984-08-15T00:00:00");
    LocalDateTime testEnd = testStart.plusDays(1L);

    LocalDateTime testTime1 = LocalDateTime.parse("1984-08-15T15:07:00.15");
    LocalDateTime testTime2 = LocalDateTime.parse("1984-08-15T15:07:06.20");
    LocalDateTime testTime3 = LocalDateTime.parse("1984-08-15T15:07:12.25");

    TimingRecordDTO recordDTO1 =
        TimingRecordDTO.builder().waypoint(EnduranceTrioWaypoint.WA).chip("AAAAAAA")
                       .time(testTime1.atZone(zoneId).toInstant()).lap(1).build();
    TimingRecordDTO recordDTO2 =
        TimingRecordDTO.builder().waypoint(EnduranceTrioWaypoint.WA).chip("AAAAAAB")
                       .time(testTime2.atZone(zoneId).toInstant()).lap(1).build();
    TimingRecordDTO recordDTO3 =
        TimingRecordDTO.builder().waypoint(EnduranceTrioWaypoint.WA).chip("AAAAAAC")
                       .time(testTime3.atZone(zoneId).toInstant()).lap(1).build();
    TimingRecordDTO recordDTO9 =
        TimingRecordDTO.builder().waypoint(EnduranceTrioWaypoint.WA).chip("AAAAAAC")
                       .time(testTime3.atZone(zoneId).toInstant()).lap(1).build();

    when(mylapsTimesRepository.findByChipTimeBetween(testStart, testEnd)).thenReturn(testData);
    when(timeRecordMapper.map(zoneId, testMylapsTimes1)).thenReturn(recordDTO1);
    when(timeRecordMapper.map(zoneId, testMylapsTimes2)).thenReturn(recordDTO2);
    when(timeRecordMapper.map(zoneId, testMylapsTimes3)).thenReturn(recordDTO3);
    when(timeRecordMapper.map(zoneId, testMylapsTimes9)).thenReturn(recordDTO9);

    List<TimingRecordDTO> results =
        mylapsTimesService.findByChipTimeDate(tzIdentifier, date);

    verify(mylapsTimesRepository, times(1)).findByChipTimeBetween(any(), any());
    verify(timeRecordMapper, times(4)).map(any(), any());
    assertNotNull(results);
    assertEquals(3, results.size());
    assertEquals("AAAAAAA", results.get(0).getChip());
    assertEquals("AAAAAAB", results.get(1).getChip());
    assertEquals("AAAAAAC", results.get(2).getChip());
  }

  @Test
  void findByChipTimeDateAndThrowAnException() {
    assertThrows(
        EnduranceTrioException.class, () -> mylapsTimesService.findByChipTimeDate("19840815"));
  }
}
