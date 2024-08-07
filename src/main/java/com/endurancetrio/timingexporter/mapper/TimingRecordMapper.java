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

import com.endurancetrio.timingexporter.model.dto.common.TimingRecordDTO;
import com.endurancetrio.timingexporter.model.entity.common.EnduranceTrioWaypoint;
import com.endurancetrio.timingexporter.model.entity.mylaps.MylapsTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import org.springframework.stereotype.Component;

/**
 * Maps an OEM time record object into an EnduranceTrio Timing Exporter time record
 * (TimingRecordDTO).
 */
@Component
public class TimingRecordMapper {

  /**
   * Maps a MylapsTimes entity into a EnduranceTrio Timing Exporter time record (TimingRecordDTO).
   *
   * @param zoneId the given ZoneId
   * @param entity the given MylapsTimes entity
   * @return the converted EnduranceTrio Timing Exporter time record (TimingRecordDTO)
   */
  public TimingRecordDTO map(ZoneId zoneId, MylapsTimes entity) {
    LocalDateTime chipTime = entity.getChipTime();
    LocalDate chipDate =
        LocalDate.of(chipTime.getYear(), chipTime.getMonth(), chipTime.getDayOfMonth());
    ZonedDateTime zeroChipZoneDateTime = LocalDateTime.of(chipDate, LocalTime.MIN).atZone(zoneId);

    ZonedDateTime chipZoneDateTime =
        zeroChipZoneDateTime.plus(entity.getMilliSecs(), ChronoUnit.MILLIS);

    try {
      // When the registered location is valid, the waypoint is included in the TimingRecordDTO
      return TimingRecordDTO.builder().waypoint(getWaypoint(entity.getLocation()))
                            .chip(entity.getChip()).time(chipZoneDateTime.toInstant())
                            .lap(entity.getLapRaw()).build();
    } catch (IllegalArgumentException exception) {
      // When the registered location is NOT valid, the waypoint it is NOT included
      // in the TimingRecordDTO and the location is added
      return TimingRecordDTO.builder().location(entity.getLocation()).chip(entity.getChip())
                            .time(chipZoneDateTime.toInstant()).lap(entity.getLapRaw()).build();
    }
  }

  /**
   * Converts the OEM system registered location into a valid EnduranceTrio Timing Exporter
   * location.
   *
   * @param location the OEM system registered location
   * @return a valid EnduranceTrio Timing Exporter waypoint
   */
  private static EnduranceTrioWaypoint getWaypoint(String location) throws IllegalArgumentException {

    String locationValue = location.substring(0, 2).toUpperCase();
    return EnduranceTrioWaypoint.valueOf(locationValue);
  }
}
