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
import static org.junit.jupiter.api.Assertions.assertNull;

import com.endurancetrio.timingexporter.model.constants.PathTimezone;
import com.endurancetrio.timingexporter.model.dto.common.TimingRecordDTO;
import com.endurancetrio.timingexporter.model.entity.common.EnduranceTrioWaypoint;
import com.endurancetrio.timingexporter.model.entity.mylaps.MylapsTimes;
import com.endurancetrio.timingexporter.model.entity.raceresult.RaceResultRecord;
import com.endurancetrio.timingexporter.model.exception.MalformedParameterException;
import com.endurancetrio.timingexporter.utils.DateTimeUtils;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class TimingRecordMapperTest {

  @InjectMocks
  TimingRecordMapper timingRecordMapper;

  String tzIdentifier = PathTimezone.LISBON.getTimezone();
  ZoneId zoneId = DateTimeUtils.getZoneId(tzIdentifier);

  MylapsTimes mylapsEntity;
  RaceResultRecord raceResultEntity;
  TimingRecordDTO expectedTimingDTO;

  TimingRecordMapperTest() throws MalformedParameterException {
    super();
  }

  @BeforeEach
  void setUp() {
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
                                       .time(testLocalDateTime.atZone(zoneId).toInstant()).lap(1)
                                       .build();
  }

  @Test
  void mylapsEntityWithValidLocationToTimingDto() {

    TimingRecordDTO dto = timingRecordMapper.map(zoneId, mylapsEntity);

    assertEquals(expectedTimingDTO.getChip(), dto.getChip());
    assertEquals(expectedTimingDTO.getTime(), dto.getTime());
    assertEquals(expectedTimingDTO.getWaypoint(), dto.getWaypoint());
    assertNull(dto.getLocation());
    assertEquals(expectedTimingDTO.getLap(), dto.getLap());
  }

  @Test
  void mylapsEntityWithInvalidLocationToTimingDto() {

    mylapsEntity.setLocation("UNKNOWN");

    TimingRecordDTO dto = timingRecordMapper.map(zoneId, mylapsEntity);

    assertEquals(expectedTimingDTO.getChip(), dto.getChip());
    assertEquals(expectedTimingDTO.getTime(), dto.getTime());
    assertNull(dto.getWaypoint());
    assertEquals("UNKNOWN", dto.getLocation());
    assertEquals(expectedTimingDTO.getLap(), dto.getLap());
  }

  @Test
  void raceResultEntityWithValidTimingPointToTimingDto() {

    expectedTimingDTO.setLap(null);

    TimingRecordDTO dto = timingRecordMapper.map(zoneId, raceResultEntity);

    assertEquals(expectedTimingDTO.getChip(), dto.getChip());
    assertEquals(expectedTimingDTO.getTime(), dto.getTime());
    assertEquals(expectedTimingDTO.getWaypoint(), dto.getWaypoint());
    assertNull(dto.getLocation());
    assertEquals(expectedTimingDTO.getLap(), dto.getLap());
  }

  @Test
  void raceResultEntityWithInvalidTimingPointToTimingDto() {

    raceResultEntity.setTimingPoint("UNKNOWN");
    expectedTimingDTO.setLap(null);

    TimingRecordDTO dto = timingRecordMapper.map(zoneId, raceResultEntity);

    assertEquals(expectedTimingDTO.getChip(), dto.getChip());
    assertEquals(expectedTimingDTO.getTime(), dto.getTime());
    assertNull(dto.getWaypoint());
    assertEquals("UNKNOWN", dto.getLocation());
    assertEquals(expectedTimingDTO.getLap(), dto.getLap());
  }
}
