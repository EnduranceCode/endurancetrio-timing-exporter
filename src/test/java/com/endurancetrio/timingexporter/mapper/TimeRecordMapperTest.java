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

import com.endurancetrio.timingexporter.model.dto.common.TimeRecordDTO;
import com.endurancetrio.timingexporter.model.entity.common.EnduranceTrioWaypoint;
import com.endurancetrio.timingexporter.model.entity.mylaps.MylapsTimes;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class TimeRecordMapperTest {

  @InjectMocks
  private TimeRecordMapper timeRecordMapper;

  private MylapsTimes mylapsTestEntity;
  private TimeRecordDTO expectedDto;

  @BeforeEach
  void setUp() {
    long testTimeMilliseconds = 15 * 60 * 60 * 1000;
    LocalDateTime testLocalDateTime = LocalDateTime.of(1984, 8, 15, 15, 0, 0);
    OffsetDateTime testChipTime = OffsetDateTime.of(testLocalDateTime, ZoneOffset.UTC);
    Instant testInstant = testChipTime.toInstant();

    mylapsTestEntity =
        MylapsTimes.builder().chip("AAAAAAA").chipTime(testChipTime).milliSecs(testTimeMilliseconds)
                   .location("SL: Start line").lapRaw(1).build();
    expectedDto =
        TimeRecordDTO.builder().chip("AAAAAAA").time(testInstant).waypoint(EnduranceTrioWaypoint.SL).lap(1)
                     .build();
  }

  @Test
  void entityToDto() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.parse("1984-08-15", formatter);
    OffsetDateTime earliestDateTimeOfDate =
        OffsetDateTime.of(localDate, LocalTime.MIN, ZoneOffset.UTC);

    TimeRecordDTO dto = timeRecordMapper.map(mylapsTestEntity);

    assertEquals(expectedDto.getChip(), dto.getChip());
    assertEquals(expectedDto.getTime(), dto.getTime());
    assertEquals(expectedDto.getWaypoint(), dto.getWaypoint());
    assertEquals(expectedDto.getLap(), dto.getLap());
  }
}
