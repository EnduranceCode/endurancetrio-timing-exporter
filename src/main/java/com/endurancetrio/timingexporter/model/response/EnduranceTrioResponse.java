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

package com.endurancetrio.timingexporter.model.response;

import java.io.Serializable;

public class EnduranceTrioResponse<T> implements Serializable {

  private static final long serialVersionUID = 6039994098197597352L;

  private String status;
  private String code;
  private String message;
  private T data;

  public EnduranceTrioResponse() {
    super();
  }

  public EnduranceTrioResponse(String status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }

  public EnduranceTrioResponse(String status, String code, String message, T data) {
    this.status = status;
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public String getStatus() {
    return status;
  }

  public EnduranceTrioResponse<T> setStatus(String status) {
    this.status = status;
    return this;
  }

  public String getCode() {
    return code;
  }

  public EnduranceTrioResponse<T> setCode(String code) {
    this.code = code;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public EnduranceTrioResponse<T> setMessage(String message) {
    this.message = message;
    return this;
  }

  public T getData() {
    return data;
  }

  public EnduranceTrioResponse<T> setData(T data) {
    this.data = data;
    return this;
  }
}
