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
import com.endurancetrio.timingexporter.model.entity.raceresult.RaceResultRecord;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * Maps an OEM timing record object into an EnduranceTrio Timing Exporter timing record
 * (TimingRecordDTO).
 */
@Component
public class TimingRecordMapper {

  /**
   * Maps a MylapsTimes entity into a EnduranceTrio Timing Exporter timing record (TimingRecordDTO).
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
      // When the registered location can be converted to a valid waypoint,
      // it is included in the TimingRecordDTO
      return TimingRecordDTO.builder().waypoint(getWaypoint(entity.getLocation()))
                            .chip(entity.getChip()).time(chipZoneDateTime.toInstant()).build();
    } catch (IllegalArgumentException exception) {
      // When the registered location can NOT be converted to a valid waypoint,
      // it is NOT included in the TimingRecordDTO, and it is added as location
      return TimingRecordDTO.builder().location(entity.getLocation()).chip(entity.getChip())
                            .time(chipZoneDateTime.toInstant()).build();
    }
  }

  /**
   * Maps a RaceResultRecord entity into a EnduranceTrio Timing Exporter timing record
   * (TimingRecordDTO).
   *
   * @param zoneId the given ZoneId
   * @param entity the given MylapsTimes entity
   * @return the converted EnduranceTrio Timing Exporter time record (TimingRecordDTO)
   */
  public TimingRecordDTO map(ZoneId zoneId, RaceResultRecord entity) {
    LocalDate chipDate = entity.getChipDate();
    ZonedDateTime zeroChipZoneDateTime = LocalDateTime.of(chipDate, LocalTime.MIN).atZone(zoneId);
    long chipMillisecond = entity.getChipSecond().multiply(BigDecimal.valueOf(1000)).longValue();

    ZonedDateTime chipZoneDateTime = zeroChipZoneDateTime.plus(chipMillisecond, ChronoUnit.MILLIS);

    try {
      // When the registered timing point can be converted to a valid waypoint,
      // it is included in the TimingRecordDTO
      return TimingRecordDTO.builder().waypoint(getWaypoint(entity.getTimingPoint()))
                            .chip(entity.getChip()).time(chipZoneDateTime.toInstant()).build();
    } catch (IllegalArgumentException exception) {
      // When the registered timing point can NOT be converted to a valid waypoint,
      // it is NOT included in the TimingRecordDTO, and it is added as location
      return TimingRecordDTO.builder().location(entity.getTimingPoint()).chip(entity.getChip())
                            .time(chipZoneDateTime.toInstant()).build();
    }
  }

  /**
   * Sets the correct lap count, per waypoint, for each chip reading on the given list of timing
   * records (TimingRecordDTO).
   * <p>
   * Sets the correct lap count, per waypoint, for each chip reading on the given list of timing
   * records (TimingRecordDTO) and sorts the list by TimingRecordDTO#time.
   *
   * @param timingRecords the given list of timing records (TimingRecordDTO)
   * @return the given list of timing records with the correct lap count on each entry, sorted by
   * record time
   */
  public List<TimingRecordDTO> setLapCount(List<TimingRecordDTO> timingRecords) {
    Map<String, List<TimingRecordDTO>> groupedRecords = timingRecords.stream().collect(
        Collectors.groupingBy(
            timingRecord -> timingRecord.getChip() + "-" + timingRecord.getWaypoint()));

    for (List<TimingRecordDTO> group : groupedRecords.values()) {
      group.sort(Comparator.comparing(TimingRecordDTO::getTime));

      for (int i = 0; i < group.size(); i++) {
        group.get(i).setLap(i + 1);
      }
    }

    return timingRecords.stream()
                        .sorted(Comparator.comparing(TimingRecordDTO::getTime))
                        .collect(Collectors.toList());
  }

  /**
   * Converts the OEM system registered location/timing point into a valid EnduranceTrio Timing
   * Exporter waypoint.
   *
   * @param location the OEM system registered location
   * @return a valid EnduranceTrio Timing Exporter waypoint
   */
  private static EnduranceTrioWaypoint getWaypoint(String location) throws IllegalArgumentException {

    String locationValue = location.substring(0, 2).toUpperCase();
    return EnduranceTrioWaypoint.valueOf(locationValue);
  }
}
