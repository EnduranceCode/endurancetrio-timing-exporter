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

package com.endurancetrio.timingexporter.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.endurancetrio.timingexporter.model.constants.PathTimezone;
import com.endurancetrio.timingexporter.model.dto.common.TimingRecordDTO;
import com.endurancetrio.timingexporter.model.entity.common.EnduranceTrioWaypoint;
import com.endurancetrio.timingexporter.model.entity.mylaps.MylapsTimes;
import com.endurancetrio.timingexporter.model.entity.raceresult.RaceResultRecord;
import com.endurancetrio.timingexporter.model.exception.MalformedParameterException;
import com.endurancetrio.timingexporter.utils.DateTimeUtils;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class TimingRecordMapperTest {

  @InjectMocks
  TimingRecordMapper underTest;

  String tzIdentifier;
  ZoneId zoneId;

  MylapsTimes mylapsEntity;
  RaceResultRecord raceResultEntity;
  TimingRecordDTO expectedTimingDTO;

  @BeforeEach
  void setUp() throws MalformedParameterException {
    tzIdentifier = PathTimezone.LISBON.getTimezone();
    zoneId = DateTimeUtils.getZoneId(tzIdentifier);

    int testTimeMilliseconds = 15 * 60 * 60 * 1000;
    LocalDateTime testLocalDateTime = LocalDateTime.of(1984, 8, 15, 15, 0, 0);

    mylapsEntity = MylapsTimes.builder().chip("AAAAAAA").chipTime(testLocalDateTime)
                              .milliSecs(testTimeMilliseconds).location("SL: Start line").lapRaw(1)
                              .build();

    raceResultEntity = RaceResultRecord.builder().timingPoint("SL: Start line").chip("AAAAAAA")
                                       .chipDate(testLocalDateTime.toLocalDate())
                                       .chipSecond(BigDecimal.valueOf(testTimeMilliseconds / 1000))
                                       .passageNo(1).build();

    expectedTimingDTO = TimingRecordDTO.builder().waypoint(EnduranceTrioWaypoint.SL).chip("AAAAAAA")
                                       .time(testLocalDateTime.atZone(zoneId).toInstant()).build();
  }

  @Test
  void mylapsEntityWithValidLocationToTimingDto() {

    TimingRecordDTO dto = underTest.map(zoneId, mylapsEntity);

    assertEquals(expectedTimingDTO.getChip(), dto.getChip());
    assertEquals(expectedTimingDTO.getTime(), dto.getTime());
    assertEquals(expectedTimingDTO.getWaypoint(), dto.getWaypoint());
    assertNull(dto.getLocation());
    assertEquals(expectedTimingDTO.getLap(), dto.getLap());
  }

  @Test
  void mylapsEntityWithInvalidLocationToTimingDto() {

    mylapsEntity.setLocation("UNKNOWN");

    TimingRecordDTO dto = underTest.map(zoneId, mylapsEntity);

    assertEquals(expectedTimingDTO.getChip(), dto.getChip());
    assertEquals(expectedTimingDTO.getTime(), dto.getTime());
    assertNull(dto.getWaypoint());
    assertEquals("UNKNOWN", dto.getLocation());
    assertEquals(expectedTimingDTO.getLap(), dto.getLap());
  }

  @Test
  void raceResultEntityWithValidTimingPointToTimingDto() {

    TimingRecordDTO dto = underTest.map(zoneId, raceResultEntity);

    assertEquals(expectedTimingDTO.getChip(), dto.getChip());
    assertEquals(expectedTimingDTO.getTime(), dto.getTime());
    assertEquals(expectedTimingDTO.getWaypoint(), dto.getWaypoint());
    assertNull(dto.getLocation());
    assertEquals(expectedTimingDTO.getLap(), dto.getLap());
  }

  @Test
  void raceResultEntityWithInvalidTimingPointToTimingDto() {

    raceResultEntity.setTimingPoint("UNKNOWN");

    TimingRecordDTO dto = underTest.map(zoneId, raceResultEntity);

    assertEquals(expectedTimingDTO.getChip(), dto.getChip());
    assertEquals(expectedTimingDTO.getTime(), dto.getTime());
    assertNull(dto.getWaypoint());
    assertEquals("UNKNOWN", dto.getLocation());
    assertEquals(expectedTimingDTO.getLap(), dto.getLap());
  }

  @Test
  void setLapCount() {
    LocalDateTime date = LocalDateTime.of(1984, 8, 15, 0, 0, 0);
    long testChipMillisecond1 = 16 * 60 * 60 * 1000;
    long testChipMillisecond2 = 15 * 60 * 60 * 1000;
    Instant testTime1 =
        date.plus(testChipMillisecond1, ChronoUnit.MILLIS).atZone(zoneId).toInstant();
    Instant testTime2 =
        date.plus(testChipMillisecond2, ChronoUnit.MILLIS).atZone(zoneId).toInstant();

    TimingRecordDTO testTimingRecord1 =
        TimingRecordDTO.builder().chip("AAAAAAA").location("WD").time(testTime1).build();
    TimingRecordDTO testTimingRecord2 =
        TimingRecordDTO.builder().chip("AAAAAAA").location("WD").time(testTime2).build();

    List<TimingRecordDTO> result =
        underTest.setLapCount(List.of(testTimingRecord1, testTimingRecord2));

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(1, result.get(0).getLap());
    assertEquals(2, result.get(1).getLap());
    assertTrue(result.get(1).getTime().compareTo(result.get(0).getTime()) >= 0);
  }
}
