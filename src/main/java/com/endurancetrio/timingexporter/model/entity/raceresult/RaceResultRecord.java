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

package com.endurancetrio.timingexporter.model.entity.raceresult;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The RaceResultRecord entity represent the passing record data obtained by the Race Result devices
 * and sent by the custom exporter set on the Race Result Software.
 *
 * @author Ricardo do Canto
 */
@Entity(name = "RaceResultRecord")
@Table(name = "RaceResult")
public class RaceResultRecord {

  /**
   * Primary key of the entity necessary to comply with the JPA Specification.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Id", nullable = false)
  private Long id;

  /**
   * The event reference as set on the Race Result Software user defined field.
   */
  @Column(name = "EventReference", nullable = false, length = 14)
  private String eventReference;

  /**
   * The Race Result decoder's ID used to record the data.
   */
  @Column(name = "DecoderID", nullable = false)
  private String decoderId;

  /**
   * The Race Result decoder's name used to record the data.
   */
  @Column(name = "DecoderName", nullable = false)
  private String decoderName;

  /**
   * A Timing Point is a location where one or more timing systems are placed.
   */
  @Column(name = "TimingPoint", nullable = false)
  private String timingPoint;

  /**
   * The transponder code of the passing record sent by the Race Result software.
   */
  @Column(name = "Chip", nullable = false, length = 7)
  private String chip;

  /**
   * The passing record detection date.
   */
  @Column(name = "ChipDate", nullable = false)
  private LocalDate chipDate;

  /**
   * The raw time of the passing record in seconds.
   */
  @Column(name = "ChipSecond", nullable = false)
  private BigDecimal chipSecond;

  /**
   * The passing number as saved in a passing file.
   */
  @Column(name = "PassageNo", nullable = false)
  private Integer passageNo;

  public RaceResultRecord() {
    super();
  }

  public RaceResultRecord(Builder builder) {
    this.id = builder.id;
    this.eventReference = builder.eventReference;
    this.decoderId = builder.decoderId;
    this.decoderName = builder.decoderName;
    this.timingPoint = builder.timingPoint;
    this.chip = builder.chip;
    this.chipDate = builder.chipDate;
    this.chipSecond = builder.chipSecond;
    this.passageNo = builder.passageNo;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEventReference() {
    return eventReference;
  }

  public void setEventReference(String eventReference) {
    this.eventReference = eventReference;
  }

  public String getDecoderId() {
    return decoderId;
  }

  public void setDecoderId(String decoderId) {
    this.decoderId = decoderId;
  }

  public String getDecoderName() {
    return decoderName;
  }

  public void setDecoderName(String decoderName) {
    this.decoderName = decoderName;
  }

  public String getTimingPoint() {
    return timingPoint;
  }

  public void setTimingPoint(String timingPoint) {
    this.timingPoint = timingPoint;
  }

  public String getChip() {
    return chip;
  }

  public void setChip(String chip) {
    this.chip = chip;
  }

  public LocalDate getChipDate() {
    return chipDate;
  }

  public void setChipDate(LocalDate chipDate) {
    this.chipDate = chipDate;
  }

  public BigDecimal getChipSecond() {
    return chipSecond;
  }

  public void setChipSecond(BigDecimal chipSecond) {
    this.chipSecond = chipSecond;
  }

  public Integer getPassageNo() {
    return passageNo;
  }

  public void setPassageNo(Integer passageNo) {
    this.passageNo = passageNo;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private Long id;
    private String eventReference;
    private String decoderId;
    private String decoderName;
    private String timingPoint;
    private String chip;
    private LocalDate chipDate;
    private BigDecimal chipSecond;
    private Integer passageNo;

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder eventReference(String eventReference) {
      this.eventReference = eventReference;
      return this;
    }

    public Builder decoderId(String decoderId) {
      this.decoderId = decoderId;
      return this;
    }

    public Builder decoderName(String decoderName) {
      this.decoderName = decoderName;
      return this;
    }

    public Builder timingPoint(String timingPoint) {
      this.timingPoint = timingPoint;
      return this;
    }

    public Builder chip(String chip) {
      this.chip = chip;
      return this;
    }

    public Builder chipDate(LocalDate chipDate) {
      this.chipDate = chipDate;
      return this;
    }

    public Builder chipSecond(BigDecimal chipSecond) {
      this.chipSecond = chipSecond;
      return this;
    }

    public Builder passageNo(Integer passageNo) {
      this.passageNo = passageNo;
      return this;
    }

    public RaceResultRecord build() {
      return new RaceResultRecord(this);
    }
  }
}
