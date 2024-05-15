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

package com.endurancetrio.timingexporter.model.entity.common;

/**
 * Valid designation for the timing waypoints
 * <p/>
 * The valid waypoint's designation list can be extended but all valid designations MUST have of a
 * length of exactly two characters.
 */
public enum EnduranceTrioWaypoint {
  // Check-in
  CI,
  // Start Line
  SL,
  // Intermediate Waypoints
  WA,
  WB,
  WC,
  WD,
  WE,
  WF,
  WG,
  WH,
  WI,
  WJ,
  WK,
  WL,
  WM,
  WN,
  WO,
  WP,
  WQ,
  WR,
  WS,
  WT,
  WU,
  WV,
  WW,
  WX,
  WY,
  WZ,
  // Finish Line
  FL;
}
