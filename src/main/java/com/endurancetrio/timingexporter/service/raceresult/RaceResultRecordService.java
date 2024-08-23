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

package com.endurancetrio.timingexporter.service.raceresult;

import com.endurancetrio.timingexporter.model.dto.common.EventTimingDTO;
import com.endurancetrio.timingexporter.model.dto.common.TimingRecordDTO;
import com.endurancetrio.timingexporter.model.exception.EnduranceTrioException;
import java.util.List;

public interface RaceResultRecordService {

  /**
   * Finds the Race Result timing records
   * {@link com.endurancetrio.timingexporter.model.entity.raceresult.RaceResultRecord} for the given
   * event reference that were recorded with the given timezone.
   * <p/>
   * Find the Race Result timing records
   * {@link com.endurancetrio.timingexporter.model.entity.raceresult.RaceResultRecord} for the given
   * event reference, recorded with the given timezone, and returns the retrieved records converted
   * into the system's time record type
   * {@link com.endurancetrio.timingexporter.model.dto.common.TimingRecordDTO}.
   *
   * @param tzIdentifier   the given timezone identifier used with the records of the given date
   * @param eventReference the given date
   * @return the  Race Result timing records for the given day converted into the system's time
   * record type
   * @throws EnduranceTrioException the custom exception
   */
  List<TimingRecordDTO> findByEventReference(String tzIdentifier, String eventReference)
      throws EnduranceTrioException;

  /**
   * Finds the event's timing data
   * {@link com.endurancetrio.timingexporter.model.dto.common.EventTimingDTO} for the given event
   * reference that were recorded with the given timezone.
   *
   * @param tzIdentifier   the given timezone identifier used with the records of the given event
   *                       reference
   * @param eventReference the given date
   * @return event's timing data for the given event reference that were recorded with the given
   * timezone
   * @throws EnduranceTrioException the custom exception
   */
  EventTimingDTO findEventTimingData(String tzIdentifier, String eventReference)
      throws EnduranceTrioException;
}
