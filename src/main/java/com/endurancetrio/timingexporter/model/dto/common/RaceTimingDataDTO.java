/*
 * MIT License
 *
 * Copyright (c) 2024 Ricardo do Canto
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

package com.endurancetrio.timingexporter.model.dto.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class RaceTimingDataDTO implements Serializable {

  private static final long serialVersionUID = 9013922786591954313L;

  private List<TimingRecordDTO> checkIn = new ArrayList<>();
  private List<TimingRecordDTO> startLine = new ArrayList<>();
  private List<TimingRecordDTO> intermediateWaypoints = new ArrayList<>();
  private List<TimingRecordDTO> finishLine = new ArrayList<>();
  private List<TimingRecordDTO> invalid = new ArrayList<>();

  public RaceTimingDataDTO() {
    super();
  }

  public RaceTimingDataDTO(Builder builder) {
    this.checkIn = builder.checkIn;
    this.startLine = builder.startLine;
    this.intermediateWaypoints = builder.intermediateWaypoints;
    this.finishLine = builder.finishLine;
    this.invalid = builder.invalid;
  }

  public List<TimingRecordDTO> getCheckIn() {
    return checkIn;
  }

  public void setCheckIn(
      List<TimingRecordDTO> checkIn) {
    this.checkIn = checkIn;
  }

  public List<TimingRecordDTO> getStartLine() {
    return startLine;
  }

  public void setStartLine(
      List<TimingRecordDTO> startLine) {
    this.startLine = startLine;
  }

  public List<TimingRecordDTO> getIntermediateWaypoints() {
    return intermediateWaypoints;
  }

  public void setIntermediateWaypoints(
      List<TimingRecordDTO> intermediateWaypoints) {
    this.intermediateWaypoints = intermediateWaypoints;
  }

  public List<TimingRecordDTO> getFinishLine() {
    return finishLine;
  }

  public void setFinishLine(
      List<TimingRecordDTO> finishLine) {
    this.finishLine = finishLine;
  }

  public List<TimingRecordDTO> getInvalid() {
    return invalid;
  }

  public void setInvalid(
      List<TimingRecordDTO> invalid) {
    this.invalid = invalid;
  }

  public static class Builder {

    private List<TimingRecordDTO> checkIn;
    private List<TimingRecordDTO> startLine;
    private List<TimingRecordDTO> intermediateWaypoints;
    private List<TimingRecordDTO> finishLine;
    private List<TimingRecordDTO> invalid;

    public Builder checkIn(List<TimingRecordDTO> checkIn) {
      this.checkIn = checkIn;
      return this;
    }

    public Builder startLine(List<TimingRecordDTO> startLine) {
      this.startLine = startLine;
      return this;
    }

    public Builder intermediateWaypoints(List<TimingRecordDTO> intermediateWaypoints) {
      this.intermediateWaypoints = intermediateWaypoints;
      return this;
    }

    public Builder finishLine(List<TimingRecordDTO> finishLine) {
      this.finishLine = finishLine;
      return this;
    }

    public Builder invalid(List<TimingRecordDTO> invalidRecords) {
      this.invalid = invalidRecords;
      return this;
    }

    public RaceTimingDataDTO build() {
      return new RaceTimingDataDTO(this);
    }
  }
}
