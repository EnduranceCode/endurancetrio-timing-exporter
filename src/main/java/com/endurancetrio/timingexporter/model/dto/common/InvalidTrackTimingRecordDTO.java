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

import java.io.Serializable;
import java.time.Instant;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Timing data obtained from a Timing Record registered with an invalid location.
 * <p/>
 * An invalid location is a location that can't be converted into one of the EnduranceTrio Timing
 * Exporter valid waypoints.
 */
public class InvalidTrackTimingRecordDTO implements Serializable {

  private static final long serialVersionUID = 628486747744547696L;

  private String chip;
  private Instant time;
  private String location;
  private Integer lap;

  public InvalidTrackTimingRecordDTO() {
    super();
  }

  public InvalidTrackTimingRecordDTO(Builder builder) {
    this.chip = builder.chip;
    this.time = builder.time;
    this.location = builder.location;
    this.lap = builder.lap;
  }

  public String getChip() {
    return chip;
  }

  public void setChip(String chip) {
    this.chip = chip;
  }

  public Instant getTime() {
    return time;
  }

  public void setTime(Instant time) {
    this.time = time;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Integer getLap() {
    return lap;
  }

  public void setLap(Integer lap) {
    this.lap = lap;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof InvalidTrackTimingRecordDTO)) {
      return false;
    }

    InvalidTrackTimingRecordDTO that = (InvalidTrackTimingRecordDTO) o;

    return new EqualsBuilder().append(chip, that.chip).append(time, that.time)
                              .append(location, that.location).append(lap, that.lap).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(chip).append(time).append(location).append(lap)
                                      .toHashCode();
  }

  public static class Builder {

    private String chip;
    private Instant time;
    private String location;
    private Integer lap;

    public Builder chip(String chip) {
      this.chip = chip;
      return this;
    }

    public Builder time(Instant time) {
      this.time = time;
      return this;
    }

    public Builder location(String location) {
      this.location = location;
      return this;
    }

    public Builder lap(Integer lap) {
      this.lap = lap;
      return this;
    }

    public InvalidTrackTimingRecordDTO build() {
      return new InvalidTrackTimingRecordDTO(this);
    }
  }
}
