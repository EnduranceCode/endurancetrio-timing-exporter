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

package com.endurancetrio.timingexporter.service.mylaps.impl;

import com.endurancetrio.timingexporter.mapper.TimeRecordMapper;
import com.endurancetrio.timingexporter.model.dto.common.ErrorDTO;
import com.endurancetrio.timingexporter.model.dto.common.TimeRecordDTO;
import com.endurancetrio.timingexporter.model.entity.mylaps.MylapsTimes;
import com.endurancetrio.timingexporter.model.exception.EnduranceTrioError;
import com.endurancetrio.timingexporter.model.exception.EnduranceTrioException;
import com.endurancetrio.timingexporter.model.exception.MalformedParameterException;
import com.endurancetrio.timingexporter.repository.mylaps.MylapsTimesRepository;
import com.endurancetrio.timingexporter.service.mylaps.MylapsTimesService;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MylapsTimesServiceImp implements MylapsTimesService {

  private final TimeRecordMapper timeRecordMapper;
  private final MylapsTimesRepository mylapsTimesRepository;

  @Autowired
  public MylapsTimesServiceImp(TimeRecordMapper timeRecordMapper,
      MylapsTimesRepository mylapsTimesRepository) {

    this.timeRecordMapper = timeRecordMapper;
    this.mylapsTimesRepository = mylapsTimesRepository;
  }

  @Override
  public List<TimeRecordDTO> findByChipTimeDate(String date) throws EnduranceTrioException {

    // Get the given date as an OffsetDate time with the earliest possible time
    OffsetDateTime queryStartDate;
    try {

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDate localDate = LocalDate.parse(date, formatter);

      queryStartDate = OffsetDateTime.of(localDate, LocalTime.MIN, ZoneOffset.UTC);
    } catch (DateTimeParseException exception) {

      String message = exception.getMessage();
      ErrorDTO error = new ErrorDTO(EnduranceTrioError.MALFORMED_PARAMETER);

      throw new MalformedParameterException(message, error);
    }

    final List<MylapsTimes> times =
        mylapsTimesRepository.findByChipTimeBetween(queryStartDate, queryStartDate.plusDays(1L));

    return times.stream()
                .map(timeRecordMapper::map)
                .distinct()
                .sorted(Comparator.comparing(TimeRecordDTO::getTime))
                .collect(Collectors.toCollection(ArrayList::new));
  }
}