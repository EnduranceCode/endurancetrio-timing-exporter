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
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.endurancetrio.timingexporter.model.dto.common.EventTimingDTO;
import com.endurancetrio.timingexporter.model.dto.common.TimingRecordDTO;
import com.endurancetrio.timingexporter.model.entity.common.EnduranceTrioWaypoint;
import java.time.OffsetDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class EventTimingMapperTest {

  @InjectMocks
  EventTimingMapper eventTimingMapper;

  OffsetDateTime testTime1;
  OffsetDateTime testTime2;
  OffsetDateTime testTime3;
  OffsetDateTime testTime4;
  OffsetDateTime testTime5;

  TimingRecordDTO timingRecordDTO01;
  TimingRecordDTO timingRecordDTO02;
  TimingRecordDTO timingRecordDTO03;
  TimingRecordDTO timingRecordDTO04;
  TimingRecordDTO timingRecordDTO05;
  TimingRecordDTO timingRecordDTO09;

  @BeforeEach
  void setUp() {
    testTime1 = OffsetDateTime.parse("1984-08-15T15:07:00.15+00:00");
    testTime2 = OffsetDateTime.parse("1984-08-15T15:08:15.85+00:00");
    testTime3 = OffsetDateTime.parse("1984-08-15T15:47:10.15+00:00");
    testTime4 = OffsetDateTime.parse("1984-08-15T15:48:35.85+00:00");
    testTime5 = OffsetDateTime.parse("1984-08-15T16:15:15.15+00:00");

    timingRecordDTO01 = TimingRecordDTO.builder().waypoint(EnduranceTrioWaypoint.WA).chip("AAAAAAA")
                                       .time(testTime1.toInstant()).lap(1).build();
    timingRecordDTO02 = TimingRecordDTO.builder().waypoint(EnduranceTrioWaypoint.WB).chip("AAAAAAA")
                                       .time(testTime2.toInstant()).lap(1).build();
    timingRecordDTO03 = TimingRecordDTO.builder().waypoint(EnduranceTrioWaypoint.WC).chip("AAAAAAA")
                                       .time(testTime3.toInstant()).lap(1).build();
    timingRecordDTO04 = TimingRecordDTO.builder().waypoint(EnduranceTrioWaypoint.WD).chip("AAAAAAA")
                                       .time(testTime4.toInstant()).lap(1).build();
    timingRecordDTO05 = TimingRecordDTO.builder().waypoint(EnduranceTrioWaypoint.FL).chip("AAAAAAA")
                                       .time(testTime5.toInstant()).lap(1).build();
    timingRecordDTO09 =
        TimingRecordDTO.builder().location("LS").chip("AAAAAAA").time(testTime1.toInstant()).lap(1)
                       .build();
  }

  @Test
  void convert() {
    List<TimingRecordDTO> timingRecords =
        List.of(timingRecordDTO01, timingRecordDTO02, timingRecordDTO03, timingRecordDTO04,
                timingRecordDTO05, timingRecordDTO09);

    EventTimingDTO result = eventTimingMapper.convert(timingRecords);

    assertNotNull(result);
    assertEquals(0, result.getCheckIn().size());
    assertEquals(0, result.getStartLine().size());
    assertEquals(4, result.getIntermediateWaypoints().size());
    assertEquals(1, result.getFinishLine().size());
    assertEquals(1, result.getInvalid().size());
    assertTrue(result.getIntermediateWaypoints().get(3).getTime()
                     .compareTo(result.getIntermediateWaypoints().get(0).getTime()) > 0);
  }

  @Test
  void convertInvalidTimeRecords() {
    timingRecordDTO01.setWaypoint(null);
    timingRecordDTO02.setWaypoint(null);
    timingRecordDTO03.setWaypoint(null);
    timingRecordDTO04.setWaypoint(null);
    timingRecordDTO05.setWaypoint(null);
    timingRecordDTO01.setLocation("T1");
    timingRecordDTO02.setLocation("T2");
    timingRecordDTO03.setLocation("T3");
    timingRecordDTO04.setLocation("T4");
    timingRecordDTO05.setLocation("T5");

    List<TimingRecordDTO> timingRecords =
        List.of(timingRecordDTO01, timingRecordDTO01, timingRecordDTO01, timingRecordDTO01,
                timingRecordDTO01, timingRecordDTO01);

    EventTimingDTO result = eventTimingMapper.convert(timingRecords);

    assertNotNull(result);
    assertEquals(0, result.getCheckIn().size());
    assertEquals(0, result.getStartLine().size());
    assertEquals(0, result.getIntermediateWaypoints().size());
    assertEquals(0, result.getFinishLine().size());
    assertEquals(6, result.getInvalid().size());
  }
}
