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
-- This script changes the MYLAPS tables to comply with the MYLAPS Timing & Scoring Software
-- application data schema
-- ---

-- Sets AUTO_INCREMENT on the Markers primary key
ALTER TABLE Markers MODIFY COLUMN Id BIGINT AUTO_INCREMENT;

-- Change types on columns of MYLAPS tables
ALTER TABLE Chips MODIFY Pid INT;
ALTER TABLE Markers MODIFY MilliSecs INT;
ALTER TABLE NewMail MODIFY Pid INT;
ALTER TABLE Results MODIFY Pid INT;
ALTER TABLE Times MODIFY MilliSecs INT;

-- Add columns for the waypoints time records with the correct name structure on the Results table
ALTER TABLE Results ADD COLUMN TimeCI INT;
ALTER TABLE Results ADD COLUMN TimeSL INT;
ALTER TABLE Results ADD COLUMN TimeWA INT;
ALTER TABLE Results ADD COLUMN TimeWB INT;
ALTER TABLE Results ADD COLUMN TimeWC INT;
ALTER TABLE Results ADD COLUMN TimeWD INT;
ALTER TABLE Results ADD COLUMN TimeFL INT;

-- Copies the data, on the Results table, from the existing columns to the new columns
UPDATE Results SET TimeCI=CI;
UPDATE Results SET TimeSL=SL;
UPDATE Results SET TimeWA=WA;
UPDATE Results SET TimeWB=WB;
UPDATE Results SET TimeWC=WC;
UPDATE Results SET TimeWD=WD;
UPDATE Results SET TimeFL=FL;

-- Deletes the old columns on the Results table
ALTER TABLE Results DROP COLUMN CI;
ALTER TABLE Results DROP COLUMN SL;
ALTER TABLE Results DROP COLUMN WA;
ALTER TABLE Results DROP COLUMN WB;
ALTER TABLE Results DROP COLUMN WC;
ALTER TABLE Results DROP COLUMN WD;
ALTER TABLE Results DROP COLUMN FL;
