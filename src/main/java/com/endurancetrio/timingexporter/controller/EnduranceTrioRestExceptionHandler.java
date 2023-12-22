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

package com.endurancetrio.timingexporter.controller;

import com.endurancetrio.timingexporter.model.exception.EnduranceTrioException;
import com.endurancetrio.timingexporter.model.response.EnduranceTrioResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@SuppressWarnings({"rawtypes", "unchecked"})
public class EnduranceTrioRestExceptionHandler {

  private static final String STATUS_ERROR_MESSAGE = "ERROR";
  private static final String HTTP_SERVER_ERROR_CODE = HttpStatus.INTERNAL_SERVER_ERROR.toString();

  @ExceptionHandler({Exception.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public EnduranceTrioResponse unhandledErrors(HttpServletRequest request, Exception exception) {

    return new EnduranceTrioResponse(
        STATUS_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR.toString(), exception.getMessage());
  }

  @ExceptionHandler({EnduranceTrioException.class, EmptyResultDataAccessException.class,
      HttpMessageNotReadableException.class})
  @ResponseBody
  public EnduranceTrioResponse handledException(final HttpServletRequest request,
      final HttpServletResponse response, final EnduranceTrioException exception) {

    response.setStatus(exception.getCode());

    return new EnduranceTrioResponse(STATUS_ERROR_MESSAGE, HTTP_SERVER_ERROR_CODE,
                                     exception.getMessage(), exception.getErrors());
  }
}
