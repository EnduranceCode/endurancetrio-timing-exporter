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

package com.endurancetrio.timingexporter.controller.raceresult;

import com.endurancetrio.timingexporter.model.dto.common.TimingRecordDTO;
import com.endurancetrio.timingexporter.model.exception.EnduranceTrioException;
import com.endurancetrio.timingexporter.model.response.EnduranceTrioResponse;
import java.util.List;

public interface RaceResultController {

  /**
   * Finds the Race Result timing records for the given event reference that were recorded with the
   * given timezone.
   * <p/>
   * Find the Race Result timing records for the given event reference, recorded with the given
   * timezone, and returns the retrieved records converted into the system's time record type.
   *
   * @param timezone       the given timezone label used with the records of the given date
   * @param eventReference the given event reference
   * @return the  MYLAPS times records for the given day converted into the system's time record
   * type
   * @throws EnduranceTrioException the custom exception
   */
  EnduranceTrioResponse<List<TimingRecordDTO>> findTimeRecordsByDate(String timezone,
      String eventReference
  )
      throws EnduranceTrioException;
}
