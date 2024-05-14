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

package com.endurancetrio.timingexporter.utils;

import com.endurancetrio.timingexporter.model.dto.common.ErrorDTO;
import com.endurancetrio.timingexporter.model.exception.EnduranceTrioError;
import com.endurancetrio.timingexporter.model.exception.MalformedParameterException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class DateTimeUtils {

  private DateTimeUtils() {
    throw new IllegalStateException("Utility Class");
  }

  /**
   * Gets the ZoneId from the given timezone identifier.
   *
   * @param tzIdentifier the given timezone identifier
   * @return the ZoneId of the given timezone identifier
   * @throws MalformedParameterException the malformed parameter exception
   */
  public static ZoneId getZoneId(String tzIdentifier) throws MalformedParameterException {
    try {
      return ZoneId.of(tzIdentifier);
    } catch (DateTimeException exception) {

      String message = exception.getMessage();
      ErrorDTO error = new ErrorDTO(EnduranceTrioError.INVALID_TIMEZONE);

      throw new MalformedParameterException(message, error);
    }
  }

  /**
   * Gets the LocalDate for the given date as string.
   *
   * @param date the given date as string
   * @return the LocalDate for the given date as string
   * @throws MalformedParameterException the malformed parameter exception
   */
  public static LocalDate getLocalDate(String date) throws MalformedParameterException {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    try {
      return LocalDate.parse(date, formatter);
    } catch (DateTimeParseException exception) {

      String message = exception.getMessage();
      ErrorDTO error = new ErrorDTO(EnduranceTrioError.MALFORMED_PARAMETER);

      throw new MalformedParameterException(message, error);
    }
  }
}
