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
 * The Times entity represent the Times information sent by MYLAPS Timing & Scoring Software when it
 * performs an export of the data read by the MYLAPS decoders.
 * <p/>
 * The corresponding table must exist on the EnduranceTrio Timing Exporter database to avoid errors
 * when MYLAPS Timing & Scoring Software uses it as an exporter. The name of the table, as well as
 * the columns names (including the capitalization), can't be changed because any change will cause
 * errors when MYLAPS Timing & Scoring Software exports its data to this database.
 *
 * @author Ricardo do Canto
 */
@Entity(name = "MylapsTimes")
@Table(name = "Times")
public class MylapsTimes {

  /**
   * Primary key of the entity necessary to comply with the JPA Specification.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Id", nullable = false)
  private Long id;

  /**
   * The Chip information sent by MYLAPS Timing & Scoring Software.
   * <p/>
   * Chip code, or BibTag group id + bib number.
   */
  @Column(name = "Chip", nullable = false, length = 7)
  private String chip;

  /**
   * The ChipTime information sent by MYLAPS Timing & Scoring Software.
   * <p/>
   * The date and time registered by the MYLAPS decoders.
   */
  @Column(name = "ChipTime", nullable = false)
  private OffsetDateTime chipTime;

  /**
   * The ChipType information sent by MYLAPS Timing & Scoring Software.
   * <p/>
   * The textual chip type.
   */
  @Column(name = "ChipType", length = 20)
  private String chipType;

  /**
   * The PC information sent by MYLAPS Timing & Scoring Software.
   * <p/>
   * The device index.
   */
  @Column(name = "PC")
  private Integer pc;

  /**
   * The Reader information sent by MYLAPS Timing & Scoring Software.
   * <p/>
   * The LF reader number, non-LF amplitude.
   */
  @Column(name = "Reader", nullable = false)
  private Integer reader;

  /**
   * The Antenna information sent by MYLAPS Timing & Scoring Software.
   * <p/>
   * The hex antenna number.
   */
  @Column(name = "Antenna", length = 1)
  private String antenna;

  /**
   * The MilliSecs information sent by MYLAPS Timing & Scoring Software.
   * <p/>
   * The time, in milliseconds, registered by the MYLAPS devices.
   */
  @Column(name = "MilliSecs", nullable = false)
  private Long milliSecs;

  /**
   * The Location information sent by MYLAPS Timing & Scoring Software.
   * <p/>
   * The device location name.
   */
  @Column(name = "Location", nullable = false, length = 20)
  private String location;

  /**
   * The LapRaw information sent by MYLAPS Timing & Scoring Software.
   * <p/>
   * The lap count.
   */
  @Column(name = "LapRaw", nullable = false)
  private Integer lapRaw;

  /**
   * The GroupId information sent by MYLAPS Timing & Scoring Software.
   */
  @Column(name = "GroupId")
  private Long groupId;

  /**
   * The BibId information sent by MYLAPS Timing & Scoring Software
   */
  @Column(name = "BibId")
  private Long bibId;

  /**
   * The Bibtag information sent by MYLAPS Timing & Scoring Software
   */
  @Column(name = "Bibtag", length = 20)
  private String bibTag;

  /**
   * The Batch information sent by MYLAPS Timing & Scoring Software
   */
  @Column(name = "Batch", length = 18)
  private String batch;

  /**
   * The AntennaInfo information sent by MYLAPS Timing & Scoring Software
   */
  @Column(name = "AntennaInfo", length = 20)
  private String antennaInfo;

  public MylapsTimes() {
    super();
  }

  public MylapsTimes(Builder builder) {
    this.id = builder.id;
    this.chip = builder.chip;
    this.chipTime = builder.chipTime;
    this.chipType = builder.chipType;
    this.pc = builder.pc;
    this.reader = builder.reader;
    this.antenna = builder.antenna;
    this.milliSecs = builder.milliSecs;
    this.location = builder.location;
    this.lapRaw = builder.lapRaw;
    this.groupId = builder.groupId;
    this.bibId = builder.bibId;
    this.bibTag = builder.bibTag;
    this.batch = builder.batch;
    this.antennaInfo = builder.antennaInfo;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getChip() {
    return chip;
  }

  public void setChip(String chip) {
    this.chip = chip;
  }

  public OffsetDateTime getChipTime() {
    return chipTime;
  }

  public void setChipTime(OffsetDateTime chipTime) {
    this.chipTime = chipTime;
  }

  public String getChipType() {
    return chipType;
  }

  public void setChipType(String chipType) {
    this.chipType = chipType;
  }

  public Integer getPc() {
    return pc;
  }

  public void setPc(Integer pc) {
    this.pc = pc;
  }

  public Integer getReader() {
    return reader;
  }

  public void setReader(Integer reader) {
    this.reader = reader;
  }

  public String getAntenna() {
    return antenna;
  }

  public void setAntenna(String antenna) {
    this.antenna = antenna;
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

  public Integer getLapRaw() {
    return lapRaw;
  }

  public void setLapRaw(Integer lapRaw) {
    this.lapRaw = lapRaw;
  }

  public Long getGroupId() {
    return groupId;
  }

  public void setGroupId(Long groupId) {
    this.groupId = groupId;
  }

  public Long getBibId() {
    return bibId;
  }

  public void setBibId(Long bibId) {
    this.bibId = bibId;
  }

  public String getBibTag() {
    return bibTag;
  }

  public void setBibTag(String bibTag) {
    this.bibTag = bibTag;
  }

  public String getBatch() {
    return batch;
  }

  public void setBatch(String batch) {
    this.batch = batch;
  }

  public String getAntennaInfo() {
    return antennaInfo;
  }

  public void setAntennaInfo(String antennaInfo) {
    this.antennaInfo = antennaInfo;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private Long id;
    private String chip;
    private OffsetDateTime chipTime;
    private String chipType;
    private Integer pc;
    private Integer reader;
    private String antenna;
    private Long milliSecs;
    private String location;
    private Integer lapRaw;
    private Long groupId;
    private Long bibId;
    private String bibTag;
    private String batch;
    private String antennaInfo;

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder chip(String chip) {
      this.chip = chip;
      return this;
    }

    public Builder chipTime(OffsetDateTime chipTime) {
      this.chipTime = chipTime;
      return this;
    }

    public Builder chipType(String chipType) {
      this.chipType = chipType;
      return this;
    }

    public Builder pc(Integer pc) {
      this.pc = pc;
      return this;
    }

    public Builder reader(Integer reader) {
      this.reader = reader;
      return this;
    }

    public Builder antenna(String antenna) {
      this.antenna = antenna;
      return this;
    }

    public Builder milliSecs(Long milliSecs) {
      this.milliSecs = milliSecs;
      return this;
    }

    public Builder location(String location) {
      this.location = location;
      return this;
    }

    public Builder lapRaw(Integer lapRaw) {
      this.lapRaw = lapRaw;
      return this;
    }

    public Builder groupId(Long groupId) {
      this.groupId = groupId;
      return this;
    }

    public Builder bibId(Long bibId) {
      this.bibId = bibId;
      return this;
    }

    public Builder bibTag(String bibTag) {
      this.bibTag = bibTag;
      return this;
    }

    public Builder batch(String batch) {
      this.batch = batch;
      return this;
    }

    public Builder antennaInfo(String antennaInfo) {
      this.antennaInfo = antennaInfo;
      return this;
    }

    public MylapsTimes build() {
      return new MylapsTimes(this);
    }
  }
}
