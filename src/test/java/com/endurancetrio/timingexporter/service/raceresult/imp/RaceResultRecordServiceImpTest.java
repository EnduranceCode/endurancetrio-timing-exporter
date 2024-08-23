/*
 * MIT License
 *
 * Copyright (c) 2024 Ricardo do Canto
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

package com.endurancetrio.timingexporter.service.raceresult.imp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.endurancetrio.timingexporter.mapper.TimingRecordMapper;
import com.endurancetrio.timingexporter.model.constants.PathTimezone;
import com.endurancetrio.timingexporter.model.dto.common.TimingRecordDTO;
import com.endurancetrio.timingexporter.model.entity.common.EnduranceTrioWaypoint;
import com.endurancetrio.timingexporter.model.entity.raceresult.RaceResultRecord;
import com.endurancetrio.timingexporter.model.exception.EnduranceTrioException;
import com.endurancetrio.timingexporter.repository.raceresult.RaceResultRecordRepository;
import com.endurancetrio.timingexporter.utils.DateTimeUtils;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class RaceResultRecordServiceImpTest {

  @InjectMocks
  RaceResultRecordServiceImp underTest;

  @Mock
  RaceResultRecordRepository raceResultRecordRepository;

  @Mock
  TimingRecordMapper timingRecordMapper;

  final List<RaceResultRecord> testData = new ArrayList<>();
  RaceResultRecord testRaceResultRecord1;
  RaceResultRecord testRaceResultRecord2;
  RaceResultRecord testRaceResultRecord3;
  RaceResultRecord testRaceResultRecord9;

  String eventReference;

  @BeforeEach
  void setUp() {
    eventReference = "19840815NAC001";
    LocalDate date = LocalDate.parse("1984-08-15");

    testRaceResultRecord1 =
        RaceResultRecord.builder().id(1L).eventReference(eventReference).timingPoint("WA")
                        .chip("AAAAAAA").chipDate(date).chipSecond(BigDecimal.valueOf(54420.150))
                        .passageNo(1).build();
    testRaceResultRecord2 =
        RaceResultRecord.builder().id(2L).eventReference(eventReference).timingPoint("WA")
                        .chip("AAAAAAB").chipDate(date).chipSecond(BigDecimal.valueOf(54426.200))
                        .passageNo(2).build();
    testRaceResultRecord3 =
        RaceResultRecord.builder().id(3L).eventReference(eventReference).timingPoint("WA")
                        .chip("AAAAAAC").chipDate(date).chipSecond(BigDecimal.valueOf(54432.250))
                        .passageNo(3).build();
    testRaceResultRecord9 =
        RaceResultRecord.builder().id(9L).eventReference(eventReference).timingPoint("WA")
                        .chip("AAAAAAC").chipDate(date).chipSecond(BigDecimal.valueOf(54432.250))
                        .passageNo(3).build();

    testData.addAll(List.of(testRaceResultRecord1, testRaceResultRecord2, testRaceResultRecord3,
                            testRaceResultRecord9
    ));
  }

  @Test
  void findByEventReference() throws EnduranceTrioException {
    String tzIdentifier = PathTimezone.LISBON.getTimezone();
    ZoneId zoneId = DateTimeUtils.getZoneId(tzIdentifier);

    LocalDateTime testTime1 = LocalDateTime.parse("1984-08-15T15:07:00.15");
    LocalDateTime testTime2 = LocalDateTime.parse("1984-08-15T15:07:06.20");
    LocalDateTime testTime3 = LocalDateTime.parse("1984-08-15T15:07:12.25");

    TimingRecordDTO recordDTO1 =
        TimingRecordDTO.builder().waypoint(EnduranceTrioWaypoint.WA).chip("AAAAAAA")
                       .time(testTime1.atZone(zoneId).toInstant()).build();
    TimingRecordDTO recordDTO2 =
        TimingRecordDTO.builder().waypoint(EnduranceTrioWaypoint.WA).chip("AAAAAAB")
                       .time(testTime2.atZone(zoneId).toInstant()).build();
    TimingRecordDTO recordDTO3 =
        TimingRecordDTO.builder().waypoint(EnduranceTrioWaypoint.WA).chip("AAAAAAC")
                       .time(testTime3.atZone(zoneId).toInstant()).build();
    TimingRecordDTO recordDTO9 =
        TimingRecordDTO.builder().waypoint(EnduranceTrioWaypoint.WA).chip("AAAAAAC")
                       .time(testTime3.atZone(zoneId).toInstant()).build();

    when(raceResultRecordRepository.findByEventReference(eventReference)).thenReturn(testData);
    when(timingRecordMapper.map(zoneId, testRaceResultRecord1)).thenReturn(recordDTO1);
    when(timingRecordMapper.map(zoneId, testRaceResultRecord2)).thenReturn(recordDTO2);
    when(timingRecordMapper.map(zoneId, testRaceResultRecord3)).thenReturn(recordDTO3);
    when(timingRecordMapper.map(zoneId, testRaceResultRecord9)).thenReturn(recordDTO9);
    when(timingRecordMapper.setLapCount(any())).thenReturn(
        Stream.of(recordDTO1, recordDTO2, recordDTO3)
              .sorted(Comparator.comparing(TimingRecordDTO::getTime)).collect(Collectors.toList()));

    List<TimingRecordDTO> results = underTest.findByEventReference(tzIdentifier, eventReference);

    verify(raceResultRecordRepository, times(1)).findByEventReference(anyString());
    verify(timingRecordMapper, times(4)).map(any(), any(RaceResultRecord.class));
    assertNotNull(results);
    assertEquals(3, results.size());
    assertEquals("AAAAAAA", results.get(0).getChip());
    assertEquals("AAAAAAB", results.get(1).getChip());
    assertEquals("AAAAAAC", results.get(2).getChip());
  }
}
