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

package com.endurancetrio.timingexporter.controller.impl;

import com.endurancetrio.timingexporter.controller.MylapsController;
import com.endurancetrio.timingexporter.model.dto.common.TimeRecordDTO;
import com.endurancetrio.timingexporter.model.exception.EnduranceTrioException;
import com.endurancetrio.timingexporter.model.response.EnduranceTrioResponse;
import com.endurancetrio.timingexporter.service.mylaps.MylapsTimesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/timing-exporter/mylaps")
public class MylapsControllerImpl implements MylapsController {

  private static final String STATUS_SUCCESS_MESSAGE = "OK";
  private static final String HTTP_OK_CODE = String.valueOf(HttpStatus.OK);

  private final MylapsTimesService mylapsTimesService;

  @Autowired
  public MylapsControllerImpl(MylapsTimesService mylapsTimesService) {
    this.mylapsTimesService = mylapsTimesService;
  }

  @Override
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/times/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
  public EnduranceTrioResponse<List<TimeRecordDTO>> findTimeRecordsByDate(@PathVariable String date)
      throws EnduranceTrioException {

    List<TimeRecordDTO> data = mylapsTimesService.findByChipTimeDate(date);

    return new EnduranceTrioResponse<>(STATUS_SUCCESS_MESSAGE, HTTP_OK_CODE, "OK", data);
  }
}
