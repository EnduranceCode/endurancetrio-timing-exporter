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

package com.endurancetrio.timingexporter.service.mylaps.imp;

import com.endurancetrio.timingexporter.mapper.TimingRecordMapper;
import com.endurancetrio.timingexporter.mapper.EventTimingMapper;
import com.endurancetrio.timingexporter.model.dto.common.EventTimingDTO;
import com.endurancetrio.timingexporter.model.dto.common.TimingRecordDTO;
import com.endurancetrio.timingexporter.model.entity.mylaps.MylapsTimes;
import com.endurancetrio.timingexporter.model.exception.EnduranceTrioException;
import com.endurancetrio.timingexporter.repository.mylaps.MylapsTimesRepository;
import com.endurancetrio.timingexporter.service.mylaps.MylapsTimesService;
import com.endurancetrio.timingexporter.utils.DateTimeUtils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MylapsTimesServiceImp implements MylapsTimesService {

  private final MylapsTimesRepository mylapsTimesRepository;
  private final TimingRecordMapper timingRecordMapper;
  private final EventTimingMapper eventTimingMapper;

  @Autowired
  public MylapsTimesServiceImp(TimingRecordMapper timingRecordMapper,
      EventTimingMapper eventTimingMapper, MylapsTimesRepository mylapsTimesRepository) {

    this.mylapsTimesRepository = mylapsTimesRepository;
    this.timingRecordMapper = timingRecordMapper;
    this.eventTimingMapper = eventTimingMapper;
  }

  @Override
  public List<TimingRecordDTO> findByChipTimeDate(String tzIdentifier, String date)
      throws EnduranceTrioException {

    // Get the given date as an LocalDateTime with the earliest possible time
    ZoneId zoneId = DateTimeUtils.getZoneId(tzIdentifier);
    LocalDate localDate = DateTimeUtils.getLocalDate(date);
    LocalDateTime queryStartDate = LocalDateTime.of(localDate, LocalTime.MIN);

    final List<MylapsTimes> times =
        mylapsTimesRepository.findByChipTimeBetween(queryStartDate, queryStartDate.plusDays(1L));

    return times.stream().map(entity -> timingRecordMapper.map(zoneId, entity)).distinct()
                .sorted(Comparator.comparing(TimingRecordDTO::getTime))
                .collect(Collectors.toList());
  }

  @Override
  public EventTimingDTO findEventTimingData(String tzIdentifier, String date)
      throws EnduranceTrioException {

    List<TimingRecordDTO> timingRecords = findByChipTimeDate(tzIdentifier, date);

    return eventTimingMapper.convert(timingRecords);
  }
}
