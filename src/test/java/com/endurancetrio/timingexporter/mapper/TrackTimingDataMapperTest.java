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

import com.endurancetrio.timingexporter.model.dto.common.FiveWaypointsTrackTimingRecordDTO;
import com.endurancetrio.timingexporter.model.dto.common.TimeRecordDTO;
import com.endurancetrio.timingexporter.model.dto.common.TrackTimingDataDTO;
import com.endurancetrio.timingexporter.model.entity.common.EnduranceTrioWaypoint;
import java.time.OffsetDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class TrackTimingDataMapperTest {

  @InjectMocks
  TrackTimingDataMapper trackTimingDataMapper;

  OffsetDateTime testTime1;
  OffsetDateTime testTime2;
  OffsetDateTime testTime3;
  OffsetDateTime testTime4;
  OffsetDateTime testTime5;

  TimeRecordDTO recordDTO1;
  TimeRecordDTO recordDTO2;
  TimeRecordDTO recordDTO3;
  TimeRecordDTO recordDTO4;
  TimeRecordDTO recordDTO5;
  TimeRecordDTO recordDTO9;

  @BeforeEach
  void setUp() {
    testTime1 = OffsetDateTime.parse("1984-08-15T15:07:00.15+00:00");
    testTime2 = OffsetDateTime.parse("1984-08-15T15:08:15.85+00:00");
    testTime3 = OffsetDateTime.parse("1984-08-15T15:47:10.15+00:00");
    testTime4 = OffsetDateTime.parse("1984-08-15T15:48:35.85+00:00");
    testTime5 = OffsetDateTime.parse("1984-08-15T16:15:15.15+00:00");

    recordDTO1 = TimeRecordDTO.builder().chip("AAAAAAA").time(testTime1.toInstant())
                              .waypoint(EnduranceTrioWaypoint.WA).lap(1).build();
    recordDTO2 = TimeRecordDTO.builder().chip("AAAAAAA").time(testTime2.toInstant())
                              .waypoint(EnduranceTrioWaypoint.WB).lap(1).build();
    recordDTO3 = TimeRecordDTO.builder().chip("AAAAAAA").time(testTime3.toInstant())
                              .waypoint(EnduranceTrioWaypoint.WC).lap(1).build();
    recordDTO4 = TimeRecordDTO.builder().chip("AAAAAAA").time(testTime4.toInstant())
                              .waypoint(EnduranceTrioWaypoint.WD).lap(1).build();
    recordDTO5 = TimeRecordDTO.builder().chip("AAAAAAA").time(testTime5.toInstant())
                              .waypoint(EnduranceTrioWaypoint.FL).lap(1).build();
    recordDTO9 = TimeRecordDTO.builder().chip("AAAAAAA").time(testTime1.toInstant())
                              .location("LS").lap(1).build();
  }

  @Test
  void map() {
    List<TimeRecordDTO> timeRecords =
        List.of(recordDTO1, recordDTO2, recordDTO3, recordDTO4, recordDTO5, recordDTO9);

    TrackTimingDataDTO<FiveWaypointsTrackTimingRecordDTO> result =
        trackTimingDataMapper.map(timeRecords);

    assertNotNull(result);
    assertEquals(1, result.getValidTrackRecords().size());
    assertEquals(1, result.getInvalidTrackRecords().size());
    assertEquals(testTime1.toInstant(), result.getValidTrackRecords().get(0).getTimeOnWaypointA());
    assertEquals(testTime2.toInstant(), result.getValidTrackRecords().get(0).getTimeOnWaypointB());
    assertEquals(testTime3.toInstant(), result.getValidTrackRecords().get(0).getTimeOnWaypointC());
    assertEquals(testTime4.toInstant(), result.getValidTrackRecords().get(0).getTimeOnWaypointD());
    assertEquals(testTime5.toInstant(), result.getValidTrackRecords().get(0).getTimeOnFinishLine());
    assertEquals(testTime1.toInstant(), result.getInvalidTrackRecords().get(0).getTime());
  }
}
