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

package com.endurancetrio.timingexporter.model.entity.mylaps;

import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Markers entity represent the Markers information sent by MYLAPS Timing & Scoring Software
 * when it performs an export of the data read by the MYLAPS decoders.
 * <p/>
 * The corresponding table must exist on the EnduranceTrio Timing Exporter database to avoid errors
 * when MYLAPS Timing & Scoring Software uses it as an exporter. The name of the table, as well as
 * the columns names (including the capitalization), can't be changed because any change will cause
 * errors when MYLAPS Timing & Scoring Software exports its data to this database.
 *
 * @author Ricardo do Canto
 */
@Entity(name = "MylapsMarkers")
@Table(name = "Markers")
public class MylapsMarkers {

  /**
   * Primary key of the entity necessary to comply with the JPA Specification.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Id", nullable = false)
  private Long id;

  /**
   * The Marker information sent by MYLAPS Timing & Scoring Software.
   */
  @Column(name = "Marker", length = 64)
  private String marker;

  /**
   * The Type information sent by MYLAPS Timing & Scoring Software.
   */
  @Column(name = "Type", length = 32)
  private String type;

  /**
   * The MarkerTime information sent by MYLAPS Timing & Scoring Software.
   */
  @Column(name = "MarkerTime", nullable = false)
  private OffsetDateTime markerTime;

  /**
   * The MilliSecs information sent by MYLAPS Timing & Scoring Software.
   */
  @Column(name = "MilliSecs", nullable = false)
  private Long milliSecs;

  /**
   * The Location information sent by MYLAPS Timing & Scoring Software.
   */
  @Column(name = "Location", nullable = false, length = 20)
  private String location;

  /**
   * The Device information sent by MYLAPS Timing & Scoring Software.
   */
  @Column(name = "Device", nullable = false, length = 20)
  private String device;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMarker() {
    return marker;
  }

  public void setMarker(String marker) {
    this.marker = marker;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public OffsetDateTime getMarkerTime() {
    return markerTime;
  }

  public void setMarkerTime(OffsetDateTime markerTime) {
    this.markerTime = markerTime;
  }

  public Long getMilliSecs() {
    return milliSecs;
  }

  public void setMilliSecs(Long milliSecs) {
    this.milliSecs = milliSecs;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getDevice() {
    return device;
  }

  public void setDevice(String device) {
    this.device = device;
  }
}
