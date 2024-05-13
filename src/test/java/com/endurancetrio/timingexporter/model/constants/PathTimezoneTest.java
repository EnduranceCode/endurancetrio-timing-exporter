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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.endurancetrio.timingexporter.model.exception.EnduranceTrioException;
import org.junit.jupiter.api.Test;

class PathTimezoneTest {

  @Test
  void testValidLisbonTimezone() throws EnduranceTrioException {
    PathTimezone result = PathTimezone.fromString("lisbon");

    assertEquals(PathTimezone.LISBON, result);
  }

  @Test
  void testValidAzoresTimezone() throws EnduranceTrioException {
    PathTimezone result = PathTimezone.fromString("azores");

    assertEquals(PathTimezone.AZORES, result);
  }

  @Test
  void testInvalidTimezone() throws EnduranceTrioException {
    assertThrows(EnduranceTrioException.class, () -> PathTimezone.fromString("utc"));
  }
}