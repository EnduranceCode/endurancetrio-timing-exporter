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

package com.endurancetrio.timingexporter.model.constants;

import org.springframework.http.HttpStatus;

public class ControllerConstants {

  public static final String ROOT_URL = "/";

  public static final String API_VERSION_1 = "/v1";
  public static final String API_RESOURCE_MYLAPS = "/mylaps";
  public static final String API_RESOURCE_RACE_RESULT = "/race-result";

  public static final String MSG_STATUS_OK = HttpStatus.OK.getReasonPhrase();
  public static final String MSG_STATUS_ERROR = "ERROR";
  public static final String MSG_CODE_OK = String.valueOf(HttpStatus.OK.value());
  public static final String MSG_CODE_ERROR = HttpStatus.INTERNAL_SERVER_ERROR.toString();
  public static final String MSG_SUCCESS = "Request handled with success";

  private ControllerConstants() {
    throw new IllegalStateException("Utility Class");
  }
}
