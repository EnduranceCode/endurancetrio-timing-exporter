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

package com.endurancetrio.timingexporter.model.constants;

import com.endurancetrio.timingexporter.model.dto.common.ErrorDTO;
import com.endurancetrio.timingexporter.model.exception.EnduranceTrioError;
import com.endurancetrio.timingexporter.model.exception.EnduranceTrioException;
import com.endurancetrio.timingexporter.model.exception.MalformedParameterException;

/**
 * Enum representing the supported, as path parameters, timezones.
 * <p>
 * The application supports the most commonly used timezones as path parameters in order to make the
 * endpoints checks easier from any device (such as a smartphone). Those timezones are listed on
 * this Enum.
 * <p>
 * The timezones not listed on this Enum, will be supported as values passed in the request body.
 */
public enum PathTimezone {
  LISBON("Europe/Lisbon"),
  AZORES("Atlantic/Azores");

  private final String timezone;

  PathTimezone(String timezone) {
    this.timezone = timezone;
  }

  public String getTimezone() {
    return timezone;
  }

  public static PathTimezone fromString(String input) throws EnduranceTrioException {
    try {
      return PathTimezone.valueOf(input.toUpperCase());
    } catch (IllegalArgumentException exception) {

      String message = exception.getMessage();
      ErrorDTO error = new ErrorDTO(EnduranceTrioError.INVALID_TIMEZONE);

      throw new MalformedParameterException(message, error);
    }
  }
}
