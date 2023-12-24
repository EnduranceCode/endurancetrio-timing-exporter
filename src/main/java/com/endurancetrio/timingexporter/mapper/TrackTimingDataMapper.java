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

import com.endurancetrio.timingexporter.mapper.data.FiveWaypoints;
import com.endurancetrio.timingexporter.model.dto.common.FiveWaypointsTrackTimingRecordDTO;
import com.endurancetrio.timingexporter.model.dto.common.InvalidTrackTimingRecordDTO;
import com.endurancetrio.timingexporter.model.dto.common.TimeRecordDTO;
import com.endurancetrio.timingexporter.model.dto.common.TrackTimingDataDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Maps a list of EnduranceTrio Timing Exporter Time records into a Race Timing Data object.
 */
@Component
public class TrackTimingDataMapper {

  /**
   * Maps a list of EnduranceTrio Timing Exporter Time records into a five waypoints Race Timing
   * Data object.
   *
   * @param timeRecords a list of time records
   * @return a five waypoint Race Timing Data * object
   */
  public TrackTimingDataDTO<FiveWaypointsTrackTimingRecordDTO> map(
      List<TimeRecordDTO> timeRecords) {

    FiveWaypoints fiveWaypoints = new FiveWaypoints();
    splitTimeRecordPerWaypoint(fiveWaypoints, timeRecords);

    List<FiveWaypointsTrackTimingRecordDTO> validTrackTimingRecords = new ArrayList<>();
    List<InvalidTrackTimingRecordDTO> invalidTrackTimingRecords = new ArrayList<>();
    Integer maxRecordsCount = getFiveWaypointsMaxRecordCount(fiveWaypoints);

    for (int i = 0; i < maxRecordsCount; i++) {

      FiveWaypointsTrackTimingRecordDTO trackRecord = new FiveWaypointsTrackTimingRecordDTO();

      getTimingDataOnCheckIn(fiveWaypoints.getTimesOnCheckIn(), i, trackRecord);
      getTimingDataOnStartLine(fiveWaypoints.getTimesOnStartLine(), i, trackRecord);
      getTimingDataOnWaypointA(fiveWaypoints.getTimesOnWaypointA(), i, trackRecord);
      getTimingDataOnWaypointB(fiveWaypoints.getTimesOnWaypointB(), i, trackRecord);
      getTimingDataOnWaypointC(fiveWaypoints.getTimesOnWaypointC(), i, trackRecord);
      getTimingDataOnWaypointD(fiveWaypoints.getTimesOnWaypointD(), i, trackRecord);
      getTimingDataOnFinishLine(fiveWaypoints.getTimesOnFinishLine(), i, trackRecord);
      getTimingDataOnInvalidLocation(fiveWaypoints.getTimesOnInvalidLocation(), i,
                                     invalidTrackTimingRecords);

      validTrackTimingRecords.add(trackRecord);
    }

    TrackTimingDataDTO<FiveWaypointsTrackTimingRecordDTO> trackTimingData =
        new TrackTimingDataDTO<>();
    trackTimingData.setValidTrackRecords(validTrackTimingRecords);
    trackTimingData.setInvalidTrackRecords(invalidTrackTimingRecords);

    return trackTimingData;
  }

  /**
   * Splits the given Time Records into a list per waypoint
   *
   * @param fiveWaypoints the timing records of a track with five waypoints (excluding Check-in and
   *                      Start Line).
   * @param timeRecords   a list of time records
   */
  private static void splitTimeRecordPerWaypoint(FiveWaypoints fiveWaypoints,
      List<TimeRecordDTO> timeRecords) {

    timeRecords.forEach(timeRecord -> {
      try {
        switch (timeRecord.getWaypoint()) {
          case CI:
            fiveWaypoints.getTimesOnCheckIn().add(timeRecord);
            break;
          case SL:
            fiveWaypoints.getTimesOnStartLine().add(timeRecord);
            break;
          case WA:
            fiveWaypoints.getTimesOnWaypointA().add(timeRecord);
            break;
          case WB:
            fiveWaypoints.getTimesOnWaypointB().add(timeRecord);
            break;
          case WC:
            fiveWaypoints.getTimesOnWaypointC().add(timeRecord);
            break;
          case WD:
            fiveWaypoints.getTimesOnWaypointD().add(timeRecord);
            break;
          case FL:
            fiveWaypoints.getTimesOnFinishLine().add(timeRecord);
            break;
          default:
            fiveWaypoints.getTimesOnInvalidLocation().add(timeRecord);
            break;
        }
      } catch (NullPointerException exception) {
        fiveWaypoints.getTimesOnInvalidLocation().add(timeRecord);
      }
    });
  }

  /**
   * Gets the maximum record count on the waypoints of a track with five waypoints (excluding
   * Check-in and Start Line)
   *
   * @param fiveWaypoints the timing records of a track with five waypoints (excluding Check-in and
   *                      Start Line).
   * @return the maximum record count on the waypoints of a track with five waypoints (excluding
   * Check-in and Start Line)
   */
  private static Integer getFiveWaypointsMaxRecordCount(FiveWaypoints fiveWaypoints) {
    int recordsCountOnCheckIn = fiveWaypoints.getTimesOnCheckIn().size();
    int recordsCountOnStartLine = fiveWaypoints.getTimesOnStartLine().size();
    int recordsCountOnWaypointA = fiveWaypoints.getTimesOnWaypointA().size();
    int recordsCountOnWaypointB = fiveWaypoints.getTimesOnWaypointB().size();
    int recordsCountOnWaypointC = fiveWaypoints.getTimesOnWaypointC().size();
    int recordsCountOnWaypointD = fiveWaypoints.getTimesOnWaypointD().size();
    int recordsCountOnFinishLine = fiveWaypoints.getTimesOnFinishLine().size();
    int recordsCountOnInvalidLocation = fiveWaypoints.getTimesOnInvalidLocation().size();

    List<Integer> recordsCount =
        List.of(recordsCountOnCheckIn, recordsCountOnStartLine, recordsCountOnWaypointA,
                recordsCountOnWaypointB, recordsCountOnWaypointC, recordsCountOnWaypointD,
                recordsCountOnFinishLine, recordsCountOnInvalidLocation);

    return Collections.max(recordsCount);
  }

  /**
   * Populates the given Track Timing Record with the data of the given index on the Check-In Timing
   * Records.
   *
   * @param timesOnCheckIn the list of timing records obtained on the Check-In waypoint.
   * @param index          the index of the track's waypoints timing data to be used to populate the
   *                       given track timing record.
   * @param trackRecord    the given Track Timing Record.
   */
  private static void getTimingDataOnCheckIn(List<TimeRecordDTO> timesOnCheckIn, int index,
      FiveWaypointsTrackTimingRecordDTO trackRecord) {

    if (!timesOnCheckIn.isEmpty() && index < timesOnCheckIn.size()) {
      trackRecord.setChipOnCheckIn(timesOnCheckIn.get(index).getChip());
      trackRecord.setTimeOnCheckIn(timesOnCheckIn.get(index).getTime());
      trackRecord.setLapOnCheckIn(timesOnCheckIn.get(index).getLap());
    }
  }

  /**
   * Populates the given Track Timing Record with the data of the given index on the Start Line
   * Timing Records.
   *
   * @param timesOnStartLine the list of timing records obtained on the Start Line waypoint.
   * @param index            the index of the track's waypoints timing data to be used to populate
   *                         the given track timing record.
   * @param trackRecord      the given Track Timing Record.
   */
  private static void getTimingDataOnStartLine(List<TimeRecordDTO> timesOnStartLine, int index,
      FiveWaypointsTrackTimingRecordDTO trackRecord) {

    if (!timesOnStartLine.isEmpty() && index < timesOnStartLine.size()) {
      trackRecord.setChipOnStartLine(timesOnStartLine.get(index).getChip());
      trackRecord.setTimeOnStartLine(timesOnStartLine.get(index).getTime());
      trackRecord.setLapOnStartLine(timesOnStartLine.get(index).getLap());
    }
  }

  /**
   * Populates the given Track Timing Record with the data of the given index on the Waypoint A
   * Timing Records.
   *
   * @param timesOnWaypointA the list of timing records obtained on the A waypoint.
   * @param index            the index of the track's waypoints timing data to be used to populate
   *                         the given track timing record.
   * @param trackRecord      the given Track Timing Record.
   */
  private static void getTimingDataOnWaypointA(List<TimeRecordDTO> timesOnWaypointA, int index,
      FiveWaypointsTrackTimingRecordDTO trackRecord) {

    if (!timesOnWaypointA.isEmpty() && index < timesOnWaypointA.size()) {
      trackRecord.setChipOnWaypointA(timesOnWaypointA.get(index).getChip());
      trackRecord.setTimeOnWaypointA(timesOnWaypointA.get(index).getTime());
      trackRecord.setLapOnWaypointA(timesOnWaypointA.get(index).getLap());
    }
  }

  /**
   * Populates the given Track Timing Record with the data of the given index on the Waypoint B
   * Timing Records.
   *
   * @param timesOnWaypointB the list of timing records obtained on the B waypoint.
   * @param index            the index of the track's waypoints timing data to be used to populate
   *                         the given track timing record.
   * @param trackRecord      the given Track Timing Record.
   */
  private static void getTimingDataOnWaypointB(List<TimeRecordDTO> timesOnWaypointB, int index,
      FiveWaypointsTrackTimingRecordDTO trackRecord) {

    if (!timesOnWaypointB.isEmpty() && index < timesOnWaypointB.size()) {
      trackRecord.setChipOnWaypointB(timesOnWaypointB.get(index).getChip());
      trackRecord.setTimeOnWaypointB(timesOnWaypointB.get(index).getTime());
      trackRecord.setLapOnWaypointB(timesOnWaypointB.get(index).getLap());
    }
  }

  /**
   * Populates the given Track Timing Record with the data of the given index on the Waypoint C
   * Timing Records.
   *
   * @param timesOnWaypointC the list of timing records obtained on the C waypoint.
   * @param index            the index of the track's waypoints timing data to be used to populate
   *                         the given track timing record.
   * @param trackRecord      the given Track Timing Record.
   */
  private static void getTimingDataOnWaypointC(List<TimeRecordDTO> timesOnWaypointC, int index,
      FiveWaypointsTrackTimingRecordDTO trackRecord) {

    if (!timesOnWaypointC.isEmpty() && index < timesOnWaypointC.size()) {
      trackRecord.setChipOnWaypointC(timesOnWaypointC.get(index).getChip());
      trackRecord.setTimeOnWaypointC(timesOnWaypointC.get(index).getTime());
      trackRecord.setLapOnWaypointC(timesOnWaypointC.get(index).getLap());
    }
  }

  /**
   * Populates the given Track Timing Record with the data of the given index on the Waypoint D
   * Timing Records.
   *
   * @param timesOnWaypointD the list of timing records obtained on the D waypoint.
   * @param index            the index of the track's waypoints timing data to be used to populate
   *                         the given track timing record.
   * @param trackRecord      the given Track Timing Record.
   */
  private static void getTimingDataOnWaypointD(List<TimeRecordDTO> timesOnWaypointD, int index,
      FiveWaypointsTrackTimingRecordDTO trackRecord) {

    if (!timesOnWaypointD.isEmpty() && index < timesOnWaypointD.size()) {
      trackRecord.setChipOnWaypointD(timesOnWaypointD.get(index).getChip());
      trackRecord.setTimeOnWaypointD(timesOnWaypointD.get(index).getTime());
      trackRecord.setLapOnWaypointD(timesOnWaypointD.get(index).getLap());
    }
  }

  /**
   * Populates the given Track Timing Record with the data of the given index on the Finish Line
   * Timing Records.
   *
   * @param timesOnFinishLine the list of timing records obtained on the Finish Line waypoint.
   * @param index             the index of the track's waypoints timing data to be used to populate
   *                          the given track timing record.
   * @param trackRecord       the given Track Timing Record.
   */
  private static void getTimingDataOnFinishLine(List<TimeRecordDTO> timesOnFinishLine, int index,
      FiveWaypointsTrackTimingRecordDTO trackRecord) {

    if (!timesOnFinishLine.isEmpty() && index < timesOnFinishLine.size()) {
      trackRecord.setChipOnFinishLine(timesOnFinishLine.get(index).getChip());
      trackRecord.setTimeOnFinishLine(timesOnFinishLine.get(index).getTime());
      trackRecord.setLapOnFinishLine(timesOnFinishLine.get(index).getLap());
    }
  }

  /**
   * Populates the given Invalid Track Records with the data of the given index on the Invalid Time
   * Records.
   *
   * @param invalidTimes        the list of invalid timing records of the track.
   * @param index               the index of the track's waypoints timing data to be used to
   *                            populate the given track timing record.
   * @param invalidTrackRecords the invalid Track Timing Records.
   */
  private static void getTimingDataOnInvalidLocation(List<TimeRecordDTO> invalidTimes, int index,
      List<InvalidTrackTimingRecordDTO> invalidTrackRecords) {

    if (!invalidTimes.isEmpty() && index < invalidTimes.size()) {
      InvalidTrackTimingRecordDTO invalidRecord = new InvalidTrackTimingRecordDTO();

      invalidRecord.setChip(invalidTimes.get(index).getChip());
      invalidRecord.setTime(invalidTimes.get(index).getTime());
      invalidRecord.setLocation(invalidTimes.get(index).getChip());
      invalidRecord.setLap(invalidTimes.get(index).getLap());

      invalidTrackRecords.add(invalidRecord);
    }
  }
}
