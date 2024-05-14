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

import com.endurancetrio.timingexporter.model.entity.common.EnduranceTrioWaypoint;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.time.Instant;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Timing data on a waypoint or location.
 */
public class TimingRecordDTO implements Serializable {

  private static final long serialVersionUID = 8574495110559383106L;

  @JsonInclude(Include.NON_NULL)
  private EnduranceTrioWaypoint waypoint;
  @JsonInclude(Include.NON_NULL)
  private String location;
  private String chip;
  private Instant time;
  private Integer lap;

  public TimingRecordDTO() {
    super();
  }

  public TimingRecordDTO(Builder builder) {
    this.waypoint = builder.waypoint;
    this.location = builder.location;
    this.chip = builder.chip;
    this.time = builder.time;
    this.lap = builder.lap;
  }

  public EnduranceTrioWaypoint getWaypoint() {
    return waypoint;
  }

  public void setWaypoint(EnduranceTrioWaypoint waypoint) {
    this.waypoint = waypoint;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
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

    if (!(o instanceof TimingRecordDTO)) {
      return false;
    }

    TimingRecordDTO that = (TimingRecordDTO) o;

    return new EqualsBuilder().append(waypoint, that.waypoint).append(location, that.location)
                              .append(chip, that.chip).append(time, that.time).append(lap, that.lap)
                              .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(waypoint).append(location).append(chip).append(time)
                                      .append(lap).toHashCode();
  }

  public static TimingRecordDTO.Builder builder() {
    return new TimingRecordDTO.Builder();
  }

  public static class Builder {

    private EnduranceTrioWaypoint waypoint;
    private String location;
    private String chip;
    private Instant time;
    private Integer lap;

    public Builder waypoint(EnduranceTrioWaypoint waypoint) {
      this.waypoint = waypoint;
      return this;
    }

    public Builder location(String label) {
      this.location = label;
      return this;
    }

    public Builder chip(String chip) {
      this.chip = chip;
      return this;
    }

    public Builder time(Instant time) {
      this.time = time;
      return this;
    }

    public Builder lap(Integer lap) {
      this.lap = lap;
      return this;
    }

    public TimingRecordDTO build() {
      return new TimingRecordDTO(this);
    }
  }
}
