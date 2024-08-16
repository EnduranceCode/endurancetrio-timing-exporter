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

package com.endurancetrio.timingexporter.repository.raceresult;

import static org.junit.jupiter.api.Assertions.*;

import com.endurancetrio.timingexporter.model.entity.raceresult.RaceResultRecord;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class RaceResultRecordRepositoryTest {


  /* As we are using the annotation @AutoConfigureTestDatabase with Replace.NONE, these tests
   * are made against the real database. Therefore, before executing these tests, it's necessary
   * to verify that the database is running and that it contains the data added with the
   * migration script V001.000.000.002__insert-race-result-test-data.sql without any modification.
   */

  @Autowired
  RaceResultRecordRepository underTest;

  @Test
  void findByEventReference() {
    String eventReference = "19840815NAC001";

    List<RaceResultRecord> result = underTest.findByEventReference(eventReference);

    assertNotNull(underTest);
    assertEquals(144, result.size());
  }
}
