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

package com.endurancetrio.timingexporter.mapper.data;

import com.endurancetrio.timingexporter.model.dto.common.TimeRecordDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * Data container for the waypoint's timing records of a track with five waypoints (excluding
 * Check-in and Start Line).
 */
public class FiveWaypoints extends BaseWaypoints {

  private List<TimeRecordDTO> timesOnWaypointA = new ArrayList<>();
  private List<TimeRecordDTO> timesOnWaypointB = new ArrayList<>();
  private List<TimeRecordDTO> timesOnWaypointC = new ArrayList<>();
  private List<TimeRecordDTO> timesOnWaypointD = new ArrayList<>();

  public List<TimeRecordDTO> getTimesOnWaypointA() {
    return timesOnWaypointA;
  }

  public void setTimesOnWaypointA(List<TimeRecordDTO> timesOnWaypointA) {
    this.timesOnWaypointA = timesOnWaypointA;
  }

  public List<TimeRecordDTO> getTimesOnWaypointB() {
    return timesOnWaypointB;
  }

  public void setTimesOnWaypointB(List<TimeRecordDTO> timesOnWaypointB) {
    this.timesOnWaypointB = timesOnWaypointB;
  }

  public List<TimeRecordDTO> getTimesOnWaypointC() {
    return timesOnWaypointC;
  }

  public void setTimesOnWaypointC(List<TimeRecordDTO> timesOnWaypointC) {
    this.timesOnWaypointC = timesOnWaypointC;
  }

  public List<TimeRecordDTO> getTimesOnWaypointD() {
    return timesOnWaypointD;
  }

  public void setTimesOnWaypointD(List<TimeRecordDTO> timesOnWaypointD) {
    this.timesOnWaypointD = timesOnWaypointD;
  }
}
