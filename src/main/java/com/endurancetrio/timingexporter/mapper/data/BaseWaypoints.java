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
 * Base container for a track's minimal required waypoints timing records.
 * <p/>
 * The minimal required waypoints in any track are the Check-In, the Start Line and the Finish
 * Line.
 */
public abstract class BaseWaypoints {

  private List<TimeRecordDTO> timesOnCheckIn = new ArrayList<>();
  private List<TimeRecordDTO> timesOnStartLine = new ArrayList<>();
  private List<TimeRecordDTO> timesOnFinishLine = new ArrayList<>();
  private List<TimeRecordDTO> timesOnInvalidLocation = new ArrayList<>();

  public List<TimeRecordDTO> getTimesOnCheckIn() {
    return timesOnCheckIn;
  }

  public void setTimesOnCheckIn(List<TimeRecordDTO> timesOnCheckIn) {
    this.timesOnCheckIn = timesOnCheckIn;
  }

  public List<TimeRecordDTO> getTimesOnStartLine() {
    return timesOnStartLine;
  }

  public void setTimesOnStartLine(List<TimeRecordDTO> timesOnStartLine) {
    this.timesOnStartLine = timesOnStartLine;
  }

  public List<TimeRecordDTO> getTimesOnFinishLine() {
    return timesOnFinishLine;
  }

  public void setTimesOnFinishLine(List<TimeRecordDTO> timesOnFinishLine) {
    this.timesOnFinishLine = timesOnFinishLine;
  }

  public List<TimeRecordDTO> getTimesOnInvalidLocation() {
    return timesOnInvalidLocation;
  }

  public void setTimesOnInvalidLocation(List<TimeRecordDTO> timesOnInvalidLocation) {
    this.timesOnInvalidLocation = timesOnInvalidLocation;
  }
}
