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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The NewMail entity represent the NewMail information sent by MYLAPS Timing & Scoring Software
 * when it performs an export of the data read by the MYLAPS decoders.
 * <p/>
 * The corresponding table must exist on the EnduranceTrio Timing Exporter database to avoid errors
 * when MYLAPS Timing & Scoring Software uses it as an exporter. The name of the table, as well as
 * the columns names (including the capitalization), can't be changed because any change will cause
 * errors when MYLAPS Timing & Scoring Software exports its data to this database.
 *
 * @author Ricardo do Canto
 */
@Entity(name = "MylapsNewMail")
@Table(name = "NewMail")
public class MylapsNewMail {

  /**
   * Primary key of the entity necessary to comply with the JPA Specification.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Id", nullable = false)
  private Long id;
  /**
   * The Pid information sent by MYLAPS Timing & Scoring Software.
   */
  @Column(name = "Pid", nullable = false)
  private Integer pid;

  /**
   * The Location information sent by MYLAPS Timing & Scoring Software.
   */
  @Column(name = "Location", nullable = false, length = 20)
  private String location;

  /**
   * The Flag information sent by MYLAPS Timing & Scoring Software.
   */
  @Column(name = "Flag", nullable = false)
  private Integer flag;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getPid() {
    return pid;
  }

  public void setPid(Integer pid) {
    this.pid = pid;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Integer getFlag() {
    return flag;
  }

  public void setFlag(Integer flag) {
    this.flag = flag;
  }
}
