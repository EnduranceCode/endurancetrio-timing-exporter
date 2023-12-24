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

package com.endurancetrio.timingexporter.model.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.Instant;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Timing Record of a track with five waypoints (excluding Check-in and Start Line).
 */
public class FiveWaypointsTrackTimingRecordDTO implements Serializable {

  private static final long serialVersionUID = 7781735649625405384L;

  @JsonProperty("[CI][Chip]")
  private String chipOnCheckIn;

  @JsonProperty("[CI][Time]")
  private Instant timeOnCheckIn;

  @JsonProperty("[CI][Lap]")
  private Integer lapOnCheckIn;

  @JsonProperty("[SL][Chip]")
  private String chipOnStartLine;

  @JsonProperty("[SL][Time]")
  private Instant timeOnStartLine;

  @JsonProperty("[SL][Lap]")
  private Integer lapOnStartLine;

  @JsonProperty("[WA][Chip]")
  private String chipOnWaypointA;

  @JsonProperty("[WA][Time]")
  private Instant timeOnWaypointA;

  @JsonProperty("[WA][Lap]")
  private Integer lapOnWaypointA;

  @JsonProperty("[WB][Chip]")
  private String chipOnWaypointB;

  @JsonProperty("[WB][Time]")
  private Instant timeOnWaypointB;

  @JsonProperty("[WB][Lap]")
  private Integer lapOnWaypointB;

  @JsonProperty("[WC][Chip]")
  private String chipOnWaypointC;

  @JsonProperty("[WC][Time]")
  private Instant timeOnWaypointC;

  @JsonProperty("[WC][Lap]")
  private Integer lapOnWaypointC;

  @JsonProperty("[WD][Chip]")
  private String chipOnWaypointD;

  @JsonProperty("[WD][Time]")
  private Instant timeOnWaypointD;

  @JsonProperty("[WD][Lap]")
  private Integer lapOnWaypointD;

  @JsonProperty("[FL][Chip]")
  private String chipOnFinishLine;

  @JsonProperty("[FL][Time]")
  private Instant timeOnFinishLine;

  @JsonProperty("[FL][Lap]")
  private Integer lapOnFinishLine;

  public FiveWaypointsTrackTimingRecordDTO() {
    super();
  }

  public FiveWaypointsTrackTimingRecordDTO(Builder builder) {
    this.chipOnCheckIn = builder.chipOnCheckIn;
    this.timeOnCheckIn = builder.timeOnCheckIn;
    this.lapOnCheckIn = builder.lapOnCheckIn;
    this.chipOnStartLine = builder.chipOnStartLine;
    this.timeOnStartLine = builder.timeOnStartLine;
    this.lapOnStartLine = builder.lapOnStartLine;
    this.chipOnWaypointA = builder.chipOnWaypointA;
    this.timeOnWaypointA = builder.timeOnWaypointA;
    this.lapOnWaypointA = builder.lapOnWaypointA;
    this.chipOnWaypointB = builder.chipOnWaypointB;
    this.timeOnWaypointB = builder.timeOnWaypointB;
    this.lapOnWaypointB = builder.lapOnWaypointB;
    this.chipOnWaypointC = builder.chipOnWaypointC;
    this.timeOnWaypointC = builder.timeOnWaypointC;
    this.lapOnWaypointC = builder.lapOnWaypointC;
    this.chipOnWaypointD = builder.chipOnWaypointD;
    this.timeOnWaypointD = builder.timeOnWaypointD;
    this.lapOnWaypointD = builder.lapOnWaypointD;
    this.chipOnFinishLine = builder.chipOnFinishLine;
    this.timeOnFinishLine = builder.timeOnFinishLine;
    this.lapOnFinishLine = builder.lapOnFinishLine;
  }

  public String getChipOnCheckIn() {
    return chipOnCheckIn;
  }

  public void setChipOnCheckIn(String chipOnCheckIn) {
    this.chipOnCheckIn = chipOnCheckIn;
  }

  public Instant getTimeOnCheckIn() {
    return timeOnCheckIn;
  }

  public void setTimeOnCheckIn(Instant timeOnCheckIn) {
    this.timeOnCheckIn = timeOnCheckIn;
  }

  public Integer getLapOnCheckIn() {
    return lapOnCheckIn;
  }

  public void setLapOnCheckIn(Integer lapOnCheckIn) {
    this.lapOnCheckIn = lapOnCheckIn;
  }

  public String getChipOnStartLine() {
    return chipOnStartLine;
  }

  public void setChipOnStartLine(String chipOnStartLine) {
    this.chipOnStartLine = chipOnStartLine;
  }

  public Instant getTimeOnStartLine() {
    return timeOnStartLine;
  }

  public void setTimeOnStartLine(Instant timeOnStartLine) {
    this.timeOnStartLine = timeOnStartLine;
  }

  public Integer getLapOnStartLine() {
    return lapOnStartLine;
  }

  public void setLapOnStartLine(Integer lapOnStartLine) {
    this.lapOnStartLine = lapOnStartLine;
  }

  public String getChipOnWaypointA() {
    return chipOnWaypointA;
  }

  public void setChipOnWaypointA(String chipOnWaypointA) {
    this.chipOnWaypointA = chipOnWaypointA;
  }

  public Instant getTimeOnWaypointA() {
    return timeOnWaypointA;
  }

  public void setTimeOnWaypointA(Instant timeOnWaypointA) {
    this.timeOnWaypointA = timeOnWaypointA;
  }

  public Integer getLapOnWaypointA() {
    return lapOnWaypointA;
  }

  public void setLapOnWaypointA(Integer lapOnWaypointA) {
    this.lapOnWaypointA = lapOnWaypointA;
  }

  public String getChipOnWaypointB() {
    return chipOnWaypointB;
  }

  public void setChipOnWaypointB(String chipOnWaypointB) {
    this.chipOnWaypointB = chipOnWaypointB;
  }

  public Instant getTimeOnWaypointB() {
    return timeOnWaypointB;
  }

  public void setTimeOnWaypointB(Instant timeOnWaypointB) {
    this.timeOnWaypointB = timeOnWaypointB;
  }

  public Integer getLapOnWaypointB() {
    return lapOnWaypointB;
  }

  public void setLapOnWaypointB(Integer lapOnWaypointB) {
    this.lapOnWaypointB = lapOnWaypointB;
  }

  public String getChipOnWaypointC() {
    return chipOnWaypointC;
  }

  public void setChipOnWaypointC(String chipOnWaypointC) {
    this.chipOnWaypointC = chipOnWaypointC;
  }

  public Instant getTimeOnWaypointC() {
    return timeOnWaypointC;
  }

  public void setTimeOnWaypointC(Instant timeOnWaypointC) {
    this.timeOnWaypointC = timeOnWaypointC;
  }

  public Integer getLapOnWaypointC() {
    return lapOnWaypointC;
  }

  public void setLapOnWaypointC(Integer lapOnWaypointC) {
    this.lapOnWaypointC = lapOnWaypointC;
  }

  public String getChipOnWaypointD() {
    return chipOnWaypointD;
  }

  public void setChipOnWaypointD(String chipOnWaypointD) {
    this.chipOnWaypointD = chipOnWaypointD;
  }

  public Instant getTimeOnWaypointD() {
    return timeOnWaypointD;
  }

  public void setTimeOnWaypointD(Instant timeOnWaypointD) {
    this.timeOnWaypointD = timeOnWaypointD;
  }

  public Integer getLapOnWaypointD() {
    return lapOnWaypointD;
  }

  public void setLapOnWaypointD(Integer lapOnWaypointD) {
    this.lapOnWaypointD = lapOnWaypointD;
  }

  public String getChipOnFinishLine() {
    return chipOnFinishLine;
  }

  public void setChipOnFinishLine(String chipOnFinishLine) {
    this.chipOnFinishLine = chipOnFinishLine;
  }

  public Instant getTimeOnFinishLine() {
    return timeOnFinishLine;
  }

  public void setTimeOnFinishLine(Instant timeOnFinishLine) {
    this.timeOnFinishLine = timeOnFinishLine;
  }

  public Integer getLapOnFinishLine() {
    return lapOnFinishLine;
  }

  public void setLapOnFinishLine(Integer lapOnFinishLine) {
    this.lapOnFinishLine = lapOnFinishLine;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof FiveWaypointsTrackTimingRecordDTO)) {
      return false;
    }

    FiveWaypointsTrackTimingRecordDTO that = (FiveWaypointsTrackTimingRecordDTO) o;

    return new EqualsBuilder().append(chipOnCheckIn, that.chipOnCheckIn)
                              .append(timeOnCheckIn, that.timeOnCheckIn)
                              .append(lapOnCheckIn, that.lapOnCheckIn)
                              .append(chipOnStartLine, that.chipOnStartLine)
                              .append(timeOnStartLine, that.timeOnStartLine)
                              .append(lapOnStartLine, that.lapOnStartLine)
                              .append(chipOnWaypointA, that.chipOnWaypointA)
                              .append(timeOnWaypointA, that.timeOnWaypointA)
                              .append(lapOnWaypointA, that.lapOnWaypointA)
                              .append(chipOnWaypointB, that.chipOnWaypointB)
                              .append(timeOnWaypointB, that.timeOnWaypointB)
                              .append(lapOnWaypointB, that.lapOnWaypointB)
                              .append(chipOnWaypointC, that.chipOnWaypointC)
                              .append(timeOnWaypointC, that.timeOnWaypointC)
                              .append(lapOnWaypointC, that.lapOnWaypointC)
                              .append(chipOnWaypointD, that.chipOnWaypointD)
                              .append(timeOnWaypointD, that.timeOnWaypointD)
                              .append(lapOnWaypointD, that.lapOnWaypointD)
                              .append(chipOnFinishLine, that.chipOnFinishLine)
                              .append(timeOnFinishLine, that.timeOnFinishLine)
                              .append(lapOnFinishLine, that.lapOnFinishLine).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(chipOnCheckIn).append(timeOnCheckIn)
                                      .append(lapOnCheckIn).append(chipOnStartLine)
                                      .append(timeOnStartLine).append(lapOnStartLine)
                                      .append(chipOnWaypointA).append(timeOnWaypointA)
                                      .append(lapOnWaypointA).append(chipOnWaypointB)
                                      .append(timeOnWaypointB).append(lapOnWaypointB)
                                      .append(chipOnWaypointC).append(timeOnWaypointC)
                                      .append(lapOnWaypointC).append(chipOnWaypointD)
                                      .append(timeOnWaypointD).append(lapOnWaypointD)
                                      .append(chipOnFinishLine).append(timeOnFinishLine)
                                      .append(lapOnFinishLine).toHashCode();
  }

  public static class Builder {

    private String chipOnCheckIn;
    private Instant timeOnCheckIn;
    private Integer lapOnCheckIn;
    private String chipOnStartLine;
    private Instant timeOnStartLine;
    private Integer lapOnStartLine;
    private String chipOnWaypointA;
    private Instant timeOnWaypointA;
    private Integer lapOnWaypointA;
    private String chipOnWaypointB;
    private Instant timeOnWaypointB;
    private Integer lapOnWaypointB;
    private String chipOnWaypointC;
    private Instant timeOnWaypointC;
    private Integer lapOnWaypointC;
    private String chipOnWaypointD;
    private Instant timeOnWaypointD;
    private Integer lapOnWaypointD;
    private String chipOnFinishLine;
    private Instant timeOnFinishLine;
    private Integer lapOnFinishLine;

    public Builder chipOnCheckIn(String chipOnCheckIn) {
      this.chipOnCheckIn = chipOnCheckIn;
      return this;
    }

    public Builder timeOnCheckIn(Instant timeOnCheckIn) {
      this.timeOnCheckIn = timeOnCheckIn;
      return this;
    }

    public Builder lapOnCheckIn(Integer lapOnCheckIn) {
      this.lapOnCheckIn = lapOnCheckIn;
      return this;
    }

    public Builder chipOnStartLine(String chipOnStartLine) {
      this.chipOnStartLine = chipOnStartLine;
      return this;
    }

    public Builder timeOnStartLine(Instant timeOnStartLine) {
      this.timeOnStartLine = timeOnStartLine;
      return this;
    }

    public Builder lapOnStartLine(Integer lapOnStartLine) {
      this.lapOnStartLine = lapOnStartLine;
      return this;
    }

    public Builder chipOnWaypointA(String chipOnWaypointA) {
      this.chipOnWaypointA = chipOnWaypointA;
      return this;
    }

    public Builder timeOnWaypointA(Instant timeOnWaypointA) {
      this.timeOnWaypointA = timeOnWaypointA;
      return this;
    }

    public Builder lapOnWaypointA(Integer lapOnWaypointA) {
      this.lapOnWaypointA = lapOnWaypointA;
      return this;
    }

    public Builder chipOnWaypointB(String chipOnWaypointB) {
      this.chipOnWaypointB = chipOnWaypointB;
      return this;
    }

    public Builder timeOnWaypointB(Instant timeOnWaypointB) {
      this.timeOnWaypointB = timeOnWaypointB;
      return this;
    }

    public Builder lapOnWaypointB(Integer lapOnWaypointB) {
      this.lapOnWaypointB = lapOnWaypointB;
      return this;
    }

    public Builder chipOnWaypointC(String chipOnWaypointC) {
      this.chipOnWaypointC = chipOnWaypointC;
      return this;
    }

    public Builder timeOnWaypointC(Instant timeOnWaypointC) {
      this.timeOnWaypointC = timeOnWaypointC;
      return this;
    }

    public Builder lapOnWaypointC(Integer lapOnWaypointC) {
      this.lapOnWaypointC = lapOnWaypointC;
      return this;
    }

    public Builder chipOnWaypointD(String chipOnWaypointD) {
      this.chipOnWaypointD = chipOnWaypointD;
      return this;
    }

    public Builder timeOnWaypointD(Instant timeOnWaypointD) {
      this.timeOnWaypointD = timeOnWaypointD;
      return this;
    }

    public Builder lapOnWaypointD(Integer lapOnWaypointD) {
      this.lapOnWaypointD = lapOnWaypointD;
      return this;
    }

    public Builder chipOnFinishLine(String chipOnFinishLine) {
      this.chipOnFinishLine = chipOnFinishLine;
      return this;
    }

    public Builder timeOnFinishLine(Instant timeOnFinishLine) {
      this.timeOnFinishLine = timeOnFinishLine;
      return this;
    }

    public Builder lapOnFinishLine(Integer lapOnFinishLine) {
      this.lapOnFinishLine = lapOnFinishLine;
      return this;
    }

    public FiveWaypointsTrackTimingRecordDTO build() {
      return new FiveWaypointsTrackTimingRecordDTO(this);
    }
  }
}
