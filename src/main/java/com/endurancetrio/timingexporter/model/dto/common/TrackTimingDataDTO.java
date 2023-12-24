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
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Contains the complete timing data of a track.
 *
 * @param <E>
 */
public class TrackTimingDataDTO<E extends Serializable> implements Serializable {

  private static final long serialVersionUID = 8193446270735719716L;

  private List<E> validTrackRecords = new ArrayList<>();
  private List<InvalidTrackTimingRecordDTO> invalidTrackRecords = new ArrayList<>();

  public TrackTimingDataDTO() {
    super();
  }

  public List<E> getValidTrackRecords() {
    return validTrackRecords;
  }

  public void setValidTrackRecords(List<E> validTrackRecords) {
    this.validTrackRecords = validTrackRecords;
  }

  public List<InvalidTrackTimingRecordDTO> getInvalidTrackRecords() {
    return invalidTrackRecords;
  }

  public void setInvalidTrackRecords(List<InvalidTrackTimingRecordDTO> invalidTrackRecords) {
    this.invalidTrackRecords = invalidTrackRecords;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof TrackTimingDataDTO)) {
      return false;
    }

    TrackTimingDataDTO<?> that = (TrackTimingDataDTO<?>) o;

    return new EqualsBuilder().append(validTrackRecords, that.validTrackRecords)
                              .append(invalidTrackRecords, that.invalidTrackRecords).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(validTrackRecords).append(invalidTrackRecords)
                                      .toHashCode();
  }
}
