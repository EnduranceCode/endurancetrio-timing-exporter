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

import com.endurancetrio.timingexporter.model.dto.common.EventTimingDTO;
import com.endurancetrio.timingexporter.model.dto.common.TimingRecordDTO;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * EventTimingMapper is a utility class that provides methods for converting a list of
 * TimingRecordDTO objects into an EventTimingDTO.
 *
 * @see TimingRecordDTO
 * @see EventTimingDTO
 */
@Component
public class EventTimingMapper {

  /**
   * Maps a list of TimingRecordDTO into a EventTimingDTO object.
   *
   * @param timingRecords the given timing records list
   * @return a EventTimingDTO object
   */
  public EventTimingDTO convert(List<TimingRecordDTO> timingRecords) {

    EventTimingDTO eventTiming = new EventTimingDTO();
    distributeTimingRecords(eventTiming, timingRecords);
    sortTimingRecords(eventTiming);

    return eventTiming;
  }

  /**
   * Maps the given list of TimingRecordDTO objects into the adequate location of the given
   * EventTimingDTO object.
   *
   * @param eventTiming   the given EventTimingDTO object
   * @param timingRecords the given timing records list
   */
  private void distributeTimingRecords(EventTimingDTO eventTiming,
      List<TimingRecordDTO> timingRecords
  ) {

    timingRecords.forEach(timingRecord -> {
      try {
        switch (timingRecord.getWaypoint()) {
          case CI:
            eventTiming.getCheckIn().add(timingRecord);
            break;
          case SL:
            eventTiming.getStartLine().add(timingRecord);
            break;
          case FL:
            eventTiming.getFinishLine().add(timingRecord);
            break;
          default:
            eventTiming.getIntermediateWaypoints().add(timingRecord);
            break;
        }
      } catch (NullPointerException exception) {
        eventTiming.getInvalid().add(timingRecord);
      }
    });
  }

  /**
   * Sorts the TimingRecordDTO lists of the given EventTimingDTO object.
   *
   * @param eventTiming given race timing data
   */
  private void sortTimingRecords(EventTimingDTO eventTiming) {
    List<TimingRecordDTO> checkInSorted =
        eventTiming.getCheckIn().stream().sorted(Comparator.comparing(TimingRecordDTO::getTime))
                   .collect(Collectors.toList());

    List<TimingRecordDTO> startLineSorted =
        eventTiming.getStartLine().stream().sorted(Comparator.comparing(TimingRecordDTO::getTime))
                   .collect(Collectors.toList());

    List<TimingRecordDTO> finishLineSorted =
        eventTiming.getFinishLine().stream().sorted(Comparator.comparing(TimingRecordDTO::getTime))
                   .collect(Collectors.toList());

    Comparator<TimingRecordDTO> waypointsComparator =
        Comparator.comparing(TimingRecordDTO::getChip).thenComparing(TimingRecordDTO::getTime);
    List<TimingRecordDTO> intermediateSorted =
        eventTiming.getIntermediateWaypoints().stream().sorted(waypointsComparator)
                   .collect(Collectors.toList());

    List<TimingRecordDTO> invalidSorted =
        eventTiming.getInvalid().stream().sorted(Comparator.comparing(TimingRecordDTO::getTime))
                   .collect(Collectors.toList());

    eventTiming.setCheckIn(checkInSorted);
    eventTiming.setStartLine(startLineSorted);
    eventTiming.setFinishLine(finishLineSorted);
    eventTiming.setIntermediateWaypoints(intermediateSorted);
    eventTiming.setInvalid(invalidSorted);
  }
}
