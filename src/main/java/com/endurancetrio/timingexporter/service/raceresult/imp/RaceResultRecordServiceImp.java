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

package com.endurancetrio.timingexporter.service.raceresult.imp;

import com.endurancetrio.timingexporter.mapper.TimingRecordMapper;
import com.endurancetrio.timingexporter.model.dto.common.TimingRecordDTO;
import com.endurancetrio.timingexporter.model.entity.raceresult.RaceResultRecord;
import com.endurancetrio.timingexporter.model.exception.EnduranceTrioException;
import com.endurancetrio.timingexporter.repository.raceresult.RaceResultRecordRepository;
import com.endurancetrio.timingexporter.service.raceresult.RaceResultRecordService;
import com.endurancetrio.timingexporter.utils.DateTimeUtils;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaceResultRecordServiceImp implements RaceResultRecordService {

  private final RaceResultRecordRepository raceResultRepository;
  private final TimingRecordMapper timingRecordMapper;

  @Autowired
  public RaceResultRecordServiceImp(RaceResultRecordRepository raceResultRepository,
      TimingRecordMapper timingRecordMapper
  ) {
    this.raceResultRepository = raceResultRepository;
    this.timingRecordMapper = timingRecordMapper;
  }

  @Override
  public List<TimingRecordDTO> findByEventReference(String tzIdentifier, String eventReference)
      throws EnduranceTrioException {

    ZoneId zoneId = DateTimeUtils.getZoneId(tzIdentifier);

    List<RaceResultRecord> records = raceResultRepository.findByEventReference(eventReference);

    return records.stream().map(entity -> timingRecordMapper.map(zoneId, entity)).distinct()
                  .sorted(Comparator.comparing(TimingRecordDTO::getTime))
                  .collect(Collectors.toList());
  }
}
