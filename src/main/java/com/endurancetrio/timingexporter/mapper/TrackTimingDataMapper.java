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

import com.endurancetrio.timingexporter.model.dto.common.RaceTimingDataDTO;
import com.endurancetrio.timingexporter.model.dto.common.TimingRecordDTO;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * Maps a list of EnduranceTrio Timing Exporter Time records into a Race Timing Data object.
 */
@Component
public class TrackTimingDataMapper {

  /**
   * Maps a list of timing records into a race timing data object.
   *
   * @param timingRecords the given timing records list
   * @return a race timing data object
   */
  public RaceTimingDataDTO convert(List<TimingRecordDTO> timingRecords) {

    RaceTimingDataDTO raceTimingData = new RaceTimingDataDTO();
    distributeTimingRecords(raceTimingData, timingRecords);
    sortTimingRecords(raceTimingData);

    return raceTimingData;
  }

  private void distributeTimingRecords(RaceTimingDataDTO raceTimingData,
      List<TimingRecordDTO> timingRecords
  ) {

    timingRecords.forEach(timingRecord -> {
      try {
        switch (timingRecord.getWaypoint()) {
          case CI:
            raceTimingData.getCheckIn().add(timingRecord);
            break;
          case SL:
            raceTimingData.getStartLine().add(timingRecord);
            break;
          case FL:
            raceTimingData.getFinishLine().add(timingRecord);
            break;
          default:
            raceTimingData.getIntermediateWaypoints().add(timingRecord);
            break;
        }
      } catch (NullPointerException exception) {
        raceTimingData.getInvalid().add(timingRecord);
      }
    });
  }

  /**
   * Sorts the given race timing data lists.
   *
   * @param raceTimingData given race timing data
   */
  private void sortTimingRecords(RaceTimingDataDTO raceTimingData) {
    List<TimingRecordDTO> checkInSorted = raceTimingData.getCheckIn().stream().sorted(
        Comparator.comparing(TimingRecordDTO::getTime)).collect(Collectors.toList());

    List<TimingRecordDTO> startLineSorted = raceTimingData.getStartLine().stream().sorted(
        Comparator.comparing(TimingRecordDTO::getTime)).collect(Collectors.toList());

    List<TimingRecordDTO> finishLineSorted = raceTimingData.getFinishLine().stream().sorted(
        Comparator.comparing(TimingRecordDTO::getTime)).collect(Collectors.toList());

    Comparator<TimingRecordDTO> waypointsComparator =
        Comparator.comparing(TimingRecordDTO::getChip).thenComparing(TimingRecordDTO::getTime);
    List<TimingRecordDTO> intermediateSorted =
        raceTimingData.getIntermediateWaypoints().stream().sorted(waypointsComparator)
                      .collect(Collectors.toList());

    List<TimingRecordDTO> invalidSorted = raceTimingData.getInvalid().stream().sorted(
        Comparator.comparing(TimingRecordDTO::getTime)).collect(Collectors.toList());

    raceTimingData.setCheckIn(checkInSorted);
    raceTimingData.setStartLine(startLineSorted);
    raceTimingData.setFinishLine(finishLineSorted);
    raceTimingData.setIntermediateWaypoints(intermediateSorted);
    raceTimingData.setInvalid(invalidSorted);
  }
}
