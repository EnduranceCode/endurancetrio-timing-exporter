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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

/**
 * The Results entity represent the Results information sent by MYLAPS Timing & Scoring Software
 * when it performs an export of the data read by the MYLAPS decoders.
 * <p/>
 * The corresponding table must exist on the EnduranceTrio Timing Exporter database to avoid errors
 * when MYLAPS Timing & Scoring Software uses it as an exporter. The name of the table, as well as
 * the columns names (including the capitalization), can't be changed because any change will cause
 * errors when MYLAPS Timing & Scoring Software exports its data to this database.
 *
 * @author Ricardo do Canto
 */
@Entity(name = "MylapsResults")
@Table(name = "Results")
public class MylapsResults {

  /* If it becomes necessary to add fields for additional timing waypoints, it should be added after
   * the last existing cardinal Waypoint and before the Finish Line waypoint.
   * The database migration scripts must also be written in a way that guarantees that the
   * additional waypoints columns are inserted after the last existing cardinal Waypoint column and
   * before the Finish Line waypoint.
   */

  /**
   * The Pid information sent by MYLAPS Timing & Scoring Software which is also the Primary Key of
   * the table.
   */
  @Id
  @Column(name = "Pid", nullable = false)
  private Integer pid;

  /**
   * The Lap information sent by MYLAPS Timing & Scoring Software.
   */
  @Column(name = "Lap")
  private Integer lap;

  /**
   * The Mail information sent by MYLAPS Timing & Scoring Software.
   */
  @Column(name = "Mail")
  @Type(type = "org.hibernate.type.NumericBooleanType")
  private Boolean mail;

  /**
   * The Time information sent by MYLAPS Timing & Scoring Software.
   */
  @Column(name = "Time")
  private Long time;

  /**
   * The time, in milliseconds, registered by the MYLAPS decoders at the location named CI in the
   * MYLAPS Timing & Scoring Software application.
   * <p/>
   * CI is an abbreviation for "Check-in". On a triathlon race it will be used to register the time
   * when the athlete checks-in at the transition area.
   */
  @Column(name = "TimeCI")
  private Integer checkIn;

  /**
   * The time, in milliseconds, registered by the MYLAPS decoders at the location named SL in the
   * MYLAPS Timing & Scoring Software application.
   * <p/>
   * SL is an abbreviation for "Start Line". On a triathlon race it will be used to register the
   * time when the athlete crosses the start line.
   */
  @Column(name = "TimeSL")
  private Integer timeStartLine;

  /**
   * The time, in milliseconds, registered by the MYLAPS decoders at the location named WA in the
   * MYLAPS Timing & Scoring Software application.
   * <p/>
   * WA is an abbreviation for "Waypoint 1". On a triathlon race it will be used to register the
   * time when the athlete finishes the swim segment.
   */
  @Column(name = "TimeWA")
  private Integer timeWaypointA;

  /**
   * The time, in milliseconds, registered by the MYLAPS decoders at the location named WB in the
   * MYLAPS Timing & Scoring Software application.
   * <p/>
   * WB is an abbreviation for "Waypoint 2". On a triathlon race it will be used to register the
   * time when the athlete finishes the first transition.
   */
  @Column(name = "TimeWB")
  private Integer timeWaypointB;

  /**
   * The time, in milliseconds, registered by the MYLAPS decoders at the location named WC in the
   * MYLAPS Timing & Scoring Software application.
   * <p/>
   * WC is an abbreviation for "Waypoint 3". On a triathlon race it will be used to register the
   * time when the athlete finishes the bike segment.
   */
  @Column(name = "TimeWC")
  private Integer timeWaypointC;

  /**
   * The time, in milliseconds, registered by the MYLAPS decoders at the location named WD in the
   * MYLAPS Timing & Scoring Software application.
   * <p/>
   * WD is an abbreviation for "Waypoint 4". On a triathlon race it will be used to register the
   * time when the athlete finishes the second transition.
   */
  @Column(name = "TimeWD")
  private Integer timeWaypointD;

  /**
   * The time, in milliseconds, registered by the MYLAPS decoders at the location named FL in the
   * MYLAPS Timing & Scoring Software application.
   * <p/>
   * FL is an abbreviation for "Finish Line". On a triathlon race it wil be used to register the
   * time when the athlete crosses the finish line.
   */
  @Column(name = "TimeFL")
  private Integer timeFinishLine;

  public Integer getPid() {
    return pid;
  }

  public void setPid(Integer pid) {
    this.pid = pid;
  }

  public Integer getLap() {
    return lap;
  }

  public void setLap(Integer lap) {
    this.lap = lap;
  }

  public Boolean getMail() {
    return mail;
  }

  public void setMail(Boolean mail) {
    this.mail = mail;
  }

  public Long getTime() {
    return time;
  }

  public void setTime(Long time) {
    this.time = time;
  }

  public Integer getCheckIn() {
    return checkIn;
  }

  public void setCheckIn(Integer checkIn) {
    this.checkIn = checkIn;
  }

  public Integer getTimeStartLine() {
    return timeStartLine;
  }

  public void setTimeStartLine(Integer timeStartLine) {
    this.timeStartLine = timeStartLine;
  }

  public Integer getTimeWaypointA() {
    return timeWaypointA;
  }

  public void setTimeWaypointA(Integer timeWaypointA) {
    this.timeWaypointA = timeWaypointA;
  }

  public Integer getTimeWaypointB() {
    return timeWaypointB;
  }

  public void setTimeWaypointB(Integer timeWaypointB) {
    this.timeWaypointB = timeWaypointB;
  }

  public Integer getTimeWaypointC() {
    return timeWaypointC;
  }

  public void setTimeWaypointC(Integer timeWaypointC) {
    this.timeWaypointC = timeWaypointC;
  }

  public Integer getTimeWaypointD() {
    return timeWaypointD;
  }

  public void setTimeWaypointD(Integer timeWaypointD) {
    this.timeWaypointD = timeWaypointD;
  }

  public Integer getTimeFinishLine() {
    return timeFinishLine;
  }

  public void setTimeFinishLine(Integer timeFinishLine) {
    this.timeFinishLine = timeFinishLine;
  }
}
