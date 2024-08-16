-- ---
-- MIT License
--
-- Copyright (c) 2023. Ricardo do Canto
--
-- Permission is hereby granted, free of charge, to any person obtaining a copy
-- of this software and associated documentation files (the "Software"), to deal
-- in the Software without restriction, including without limitation the rights
-- to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
-- copies of the Software, and to permit persons to whom the Software is
-- furnished to do so, subject to the following conditions:
--
-- The above copyright notice and this permission notice shall be included in all
-- copies or substantial portions of the Software.
--
-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
-- IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
-- FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
-- AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
-- LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
-- OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
-- SOFTWARE.
-- ---

-- ---
-- This script creates the table to receive the data sent by Race Result Software.
--
-- The names of the tables, columns follows the convention used for the MYLAPS tables as those can't
-- be changed and we want to be consistent.
-- ---

-- Creates the table to receive the data sent by Race Result Software
CREATE TABLE IF NOT EXISTS RaceResult (
  Id              BIGINT        NOT NULL	AUTO_INCREMENT,
  EventReference  VARCHAR(14)   NOT NULL,
  DecoderID       VARCHAR(20)   NOT NULL,
  DecoderName     VARCHAR(20)   NOT NULL,
  TimingPoint     VARCHAR(20)	  NOT NULL,
  Chip            VARCHAR(7)	  NOT NULL,
  ChipDate        DATE          NOT NULL,
  ChipSecond      DECIMAL(8,3)  NOT NULL,
  PassageNo       INT           NOT NULL,
  constraint PK_RaceResult PRIMARY KEY (Id)
);

-- Creates an Index for the column EventReference of the table RaceResult
CREATE INDEX IX_RaceResult_EventReference ON RaceResult(EventReference);
