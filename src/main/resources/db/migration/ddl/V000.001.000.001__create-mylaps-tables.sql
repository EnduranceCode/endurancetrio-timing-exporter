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
-- This script creates the tables to receive the data sent by MYLAPS Timing & Scoring Software
--
-- It's possible that not all tables created with this script will be used by the
-- EnduranceTrio Timing Exporter REST API, but all these tables must exist on the database to avoid
-- errors when MYLAPS Timing & Scoring Software uses it as an exporter. The names of the tables,
-- as well as the columns names (including the capitalization), can't be changed because any change
-- will cause errors when MYLAPS Timing & Scoring Software exports its data to this database.
-- ---

-- Creates the table to receive the Chips data sent by MYLAPS Timing & Scoring Software
CREATE TABLE IF NOT EXISTS Chips (
  Chip  VARCHAR(7)  NOT NULL,
  Pid   BIGINT      NOT NULL,
  CONSTRAINT PK_Chips_Chip PRIMARY KEY (Chip)
);

-- Creates the table to receive the Markers data sent by MYLAPS Timing & Scoring Software
CREATE TABLE IF NOT EXISTS Markers (
  Id          BIGINT        NOT NULL,
  Marker      VARCHAR(64)   NULL,
  Type        VARCHAR(32)   NULL,
  MarkerTime  TIMESTAMP     NOT NULL,
  MilliSecs   BIGINT        NOT NULL,
  Location    VARCHAR(20)   NOT NULL,
  Device      VARCHAR(20)   NOT NULL,
  CONSTRAINT PK_Markers_Id PRIMARY KEY (Id)
);

-- Creates the table to receive the NewMail data sent by MYLAPS Timing & Scoring Software
CREATE TABLE IF NOT EXISTS NewMail (
  Id        BIGINT      NOT NULL  AUTO_INCREMENT,
  Pid       BIGINT      NOT NULL,
  Location  VARCHAR(20) NOT NULL,
  Flag      INT         NOT NULL,
  CONSTRAINT PK_NewMail_Id PRIMARY KEY (Id)
);

-- Creates the table to receive the Results data sent by MYLAPS Timing & Scoring Software
CREATE TABLE IF NOT EXISTS Results (
  Pid     BIGINT  NOT NULL,
  CI      BIGINT  NULL,
  SL      BIGINT  NULL,
  WA      BIGINT  NULL,
  WB      BIGINT  NULL,
  WC      BIGINT  NULL,
  WD      BIGINT  NULL,
  FL      BIGINT  NULL,
  Lap     INT     NULL,
  Mail    BIT(1)  NULL,
  Time    BIGINT  NULL,
  CONSTRAINT PK_Results_Pid PRIMARY KEY (Pid)
);

-- Creates the table to receive the Times data sent by MYLAPS Timing & Scoring Software
CREATE TABLE IF NOT EXISTS Times (
  Id          BIGINT        NOT NULL  AUTO_INCREMENT,
  Chip        VARCHAR(7)    NOT NULL,
  ChipTime    datetime      NOT NULL,
  ChipType    VARCHAR(20)   NULL,
  PC          INT           NULL,
  Reader      INT           NOT NULL,
  Antenna     VARCHAR(1)    NULL,
  MilliSecs   BIGINT        NOT NULL,
  Location    VARCHAR(20)   NOT NULL,
  LapRaw      INT           NOT NULL,
  GroupId     BIGINT        NULL,
  BibId       BIGINT        NULL,
  Bibtag      VARCHAR(20)   NULL,
  Batch       VARCHAR(18)   NULL,
  AntennaInfo VARCHAR(20)   NULL,
  CONSTRAINT PK_Times PRIMARY KEY (Id)
);

-- Creates an Index for the column Chip of the table Times
CREATE INDEX IX_Times_Chip ON Times(Chip);

-- Creates an Index for the column ChipTime of the table Times
CREATE INDEX IX_Times_ChipTime ON Times(ChipTime);
