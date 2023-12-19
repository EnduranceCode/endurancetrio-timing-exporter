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
-- This script inserts data in the Time table to be used for testing purposes.
-- ---

INSERT INTO Times
	(Chip, ChipTime, ChipType, PC, Reader, Antenna, MilliSecs, Location, LapRaw)
VALUES
	('AAAAAAA', '1984-08-15 15:07:00', 'ChampionChip', 1, 3, 'A', 54420150, 'WA', 1),
	('AAAAAAB', '1984-08-15 15:07:06', 'ChampionChip', 1, 3, 'A', 54426200, 'WA', 1),
	('AAAAAAC', '1984-08-15 15:07:12', 'ChampionChip', 1, 3, 'A', 54432250, 'WA', 1),
	('AAAAAAD', '1984-08-15 15:07:18', 'ChampionChip', 1, 3, 'A', 54438300, 'WA', 1),
	('AAAAAAE', '1984-08-15 15:07:24', 'ChampionChip', 1, 3, 'A', 54444350, 'WA', 1),
	('AAAAAAF', '1984-08-15 15:07:30', 'ChampionChip', 1, 3, 'A', 54450400, 'WA', 1),
	('AAAAAAG', '1984-08-15 15:07:36', 'ChampionChip', 1, 3, 'A', 54456350, 'WA', 1),
	('AAAAAAH', '1984-08-15 15:07:42', 'ChampionChip', 1, 3, 'A', 54462300, 'WA', 1),
	('AAAAAAI', '1984-08-15 15:07:48', 'ChampionChip', 1, 3, 'A', 54468250, 'WA', 1),
	('AAAAAAJ', '1984-08-15 15:07:54', 'ChampionChip', 1, 3, 'A', 54474200, 'WA', 1),
	('AAAAAAK', '1984-08-15 15:08:00', 'ChampionChip', 1, 3, 'A', 54480150, 'WA', 1),
	('AAAAAAL', '1984-08-15 15:08:06', 'ChampionChip', 1, 3, 'A', 54486100, 'WA', 1),
	('AAAAAAM', '1984-08-15 15:08:12', 'ChampionChip', 1, 3, 'A', 54492150, 'WA', 1),
	('AAAAAAA', '1984-08-15 15:08:15', 'ChampionChip', 2, 3, 'B', 54494850, 'WB', 1),
	('AAAAAAN', '1984-08-15 15:08:18', 'ChampionChip', 1, 3, 'A', 54498200, 'WA', 1),
	('AAAAAAB', '1984-08-15 15:08:22', 'ChampionChip', 2, 3, 'B', 54501800, 'WB', 1),
	('AAAAAAO', '1984-08-15 15:08:24', 'ChampionChip', 1, 3, 'A', 54504250, 'WA', 1),
	('AAAAAAC', '1984-08-15 15:08:29', 'ChampionChip', 2, 3, 'B', 54508750, 'WB', 1),
	('AAAAAAP', '1984-08-15 15:08:30', 'ChampionChip', 1, 3, 'A', 54510300, 'WA', 1),
	('AAAAAAD', '1984-08-15 15:08:36', 'ChampionChip', 2, 3, 'B', 54515700, 'WB', 1),
	('AAAAAAQ', '1984-08-15 15:08:36', 'ChampionChip', 1, 3, 'A', 54516350, 'WA', 1),
	('AAAAAAR', '1984-08-15 15:08:42', 'ChampionChip', 1, 3, 'A', 54522400, 'WA', 1),
	('AAAAAAE', '1984-08-15 15:08:43', 'ChampionChip', 2, 3, 'B', 54522650, 'WB', 1),
	('AAAAAAF', '1984-08-15 15:08:48', 'ChampionChip', 2, 3, 'B', 54527600, 'WB', 1);

INSERT INTO Times
	(Chip, ChipTime, ChipType, PC, Reader, Antenna, MilliSecs, Location, LapRaw)
VALUES
	('AAAAAAS', '1984-08-15 15:08:48', 'ChampionChip', 1, 3, 'A', 54528350, 'WA', 1),
	('AAAAAAG', '1984-08-15 15:08:53', 'ChampionChip', 2, 3, 'B', 54532650, 'WB', 1),
	('AAAAAAT', '1984-08-15 15:08:54', 'ChampionChip', 1, 3, 'A', 54534300, 'WA', 1),
	('AAAAAAH', '1984-08-15 15:08:58', 'ChampionChip', 2, 3, 'B', 54537700, 'WB', 1),
	('AAAAAAU', '1984-08-15 15:09:00', 'ChampionChip', 1, 3, 'A', 54540250, 'WA', 1),
	('AAAAAAI', '1984-08-15 15:09:03', 'ChampionChip', 2, 3, 'B', 54542750, 'WB', 1),
	('AAAAAAV', '1984-08-15 15:09:06', 'ChampionChip', 1, 3, 'A', 54546200, 'WA', 1),
	('AAAAAAJ', '1984-08-15 15:09:08', 'ChampionChip', 2, 3, 'B', 54547800, 'WB', 1),
	('AAAAAAW', '1984-08-15 15:09:12', 'ChampionChip', 1, 3, 'A', 54552150, 'WA', 1),
	('AAAAAAK', '1984-08-15 15:09:15', 'ChampionChip', 2, 3, 'B', 54554850, 'WB', 1),
	('AAAAAAX', '1984-08-15 15:09:18', 'ChampionChip', 1, 3, 'A', 54558100, 'WA', 1),
	('AAAAAAL', '1984-08-15 15:09:22', 'ChampionChip', 2, 3, 'B', 54561900, 'WB', 1),
	('AAAAAAY', '1984-08-15 15:09:24', 'ChampionChip', 1, 3, 'A', 54564150, 'WA', 1),
	('AAAAAAM', '1984-08-15 15:09:29', 'ChampionChip', 2, 3, 'B', 54568850, 'WB', 1),
	('AAAAAAZ', '1984-08-15 15:09:30', 'ChampionChip', 1, 3, 'A', 54570200, 'WA', 1),
	('AAAAAAN', '1984-08-15 15:09:36', 'ChampionChip', 2, 3, 'B', 54575800, 'WB', 1),
	('AAAAABA', '1984-08-15 15:09:36', 'ChampionChip', 1, 3, 'A', 54576250, 'WA', 1),
	('AAAAAAO', '1984-08-15 15:09:41', 'ChampionChip', 2, 3, 'B', 54580750, 'WB', 1),
	('AAAAABB', '1984-08-15 15:09:42', 'ChampionChip', 1, 3, 'A', 54582300, 'WA', 1),
	('AAAAAAP', '1984-08-15 15:09:46', 'ChampionChip', 2, 3, 'B', 54585700, 'WB', 1),
	('AAAAABC', '1984-08-15 15:09:48', 'ChampionChip', 1, 3, 'A', 54588350, 'WA', 1),
	('AAAAAAQ', '1984-08-15 15:09:51', 'ChampionChip', 2, 3, 'B', 54590650, 'WB', 1),
	('AAAAABD', '1984-08-15 15:09:54', 'ChampionChip', 1, 3, 'A', 54594400, 'WA', 1),
	('AAAAAAR', '1984-08-15 15:09:56', 'ChampionChip', 2, 3, 'B', 54595600, 'WB', 1);

INSERT INTO Times
	(Chip, ChipTime, ChipType, PC, Reader, Antenna, MilliSecs, Location, LapRaw)
VALUES
	('AAAAAAS', '1984-08-15 15:10:01', 'ChampionChip', 2, 3, 'B', 54600650, 'WB', 1),
	('AAAAAAT', '1984-08-15 15:10:08', 'ChampionChip', 2, 3, 'B', 54607700, 'WB', 1),
	('AAAAAAU', '1984-08-15 15:10:15', 'ChampionChip', 2, 3, 'B', 54614750, 'WB', 1),
	('AAAAAAV', '1984-08-15 15:10:22', 'ChampionChip', 2, 3, 'B', 54621800, 'WB', 1),
	('AAAAAAW', '1984-08-15 15:10:29', 'ChampionChip', 2, 3, 'B', 54628850, 'WB', 1),
	('AAAAAAX', '1984-08-15 15:10:34', 'ChampionChip', 2, 3, 'B', 54633900, 'WB', 1),
	('AAAAAAY', '1984-08-15 15:10:39', 'ChampionChip', 2, 3, 'B', 54638850, 'WB', 1),
	('AAAAAAZ', '1984-08-15 15:10:44', 'ChampionChip', 2, 3, 'B', 54643800, 'WB', 1),
	('AAAAABA', '1984-08-15 15:10:49', 'ChampionChip', 2, 3, 'B', 54648750, 'WB', 1),
	('AAAAABB', '1984-08-15 15:10:54', 'ChampionChip', 2, 3, 'B', 54653700, 'WB', 1),
	('AAAAABC', '1984-08-15 15:11:01', 'ChampionChip', 2, 3, 'B', 54660650, 'WB', 1),
	('AAAAABD', '1984-08-15 15:11:08', 'ChampionChip', 2, 3, 'B', 54667600, 'WB', 1),
	('AAAAAAA', '1984-08-15 15:47:10', 'ChampionChip', 3, 3, 'C', 56830150, 'WC', 1),
	('AAAAAAA', '1984-08-15 15:48:35', 'ChampionChip', 4, 3, 'D', 56914850, 'WD', 1),
	('AAAAAAB', '1984-08-15 15:49:38', 'ChampionChip', 3, 3, 'C', 56978200, 'WC', 1),
	('AAAAAAB', '1984-08-15 15:51:04', 'ChampionChip', 4, 3, 'D', 57063800, 'WD', 1),
	('AAAAAAC', '1984-08-15 15:55:36', 'ChampionChip', 3, 3, 'C', 57336250, 'WC', 1),
	('AAAAAAD', '1984-08-15 15:56:41', 'ChampionChip', 3, 3, 'C', 57401300, 'WC', 1),
	('AAAAAAC', '1984-08-15 15:57:03', 'ChampionChip', 4, 3, 'D', 57422750, 'WD', 1),
	('AAAAAAE', '1984-08-15 15:57:32', 'ChampionChip', 3, 3, 'C', 57452350, 'WC', 1),
	('AAAAAAF', '1984-08-15 15:58:02', 'ChampionChip', 3, 3, 'C', 57482400, 'WC', 1),
	('AAAAAAD', '1984-08-15 15:58:09', 'ChampionChip', 4, 3, 'D', 57488700, 'WD', 1),
	('AAAAAAG', '1984-08-15 15:58:27', 'ChampionChip', 3, 3, 'C', 57507350, 'WC', 1),
	('AAAAAAH', '1984-08-15 15:58:32', 'ChampionChip', 3, 3, 'C', 57512300, 'WC', 1);

INSERT INTO Times
	(Chip, ChipTime, ChipType, PC, Reader, Antenna, MilliSecs, Location, LapRaw)
VALUES
	('AAAAAAI', '1984-08-15 15:58:47', 'ChampionChip', 3, 3, 'C', 57527250, 'WC', 1),
	('AAAAAAE', '1984-08-15 15:59:01', 'ChampionChip', 4, 3, 'D', 57540650, 'WD', 1),
	('AAAAAAF', '1984-08-15 15:59:30', 'ChampionChip', 4, 3, 'D', 57569600, 'WD', 1),
	('AAAAAAJ', '1984-08-15 15:59:42', 'ChampionChip', 3, 3, 'C', 57582200, 'WC', 1),
	('AAAAAAG', '1984-08-15 15:59:54', 'ChampionChip', 4, 3, 'D', 57593650, 'WD', 1),
	('AAAAAAH', '1984-08-15 15:59:58', 'ChampionChip', 4, 3, 'D', 57597700, 'WD', 1),
	('AAAAAAI', '1984-08-15 16:00:12', 'ChampionChip', 4, 3, 'D', 57611750, 'WD', 1),
	('AAAAAAK', '1984-08-15 16:00:25', 'ChampionChip', 3, 3, 'C', 57625150, 'WC', 1),
	('AAAAAAJ', '1984-08-15 16:01:06', 'ChampionChip', 4, 3, 'D', 57665800, 'WD', 1),
	('AAAAAAL', '1984-08-15 16:01:13', 'ChampionChip', 3, 3, 'C', 57673100, 'WC', 1),
	('AAAAAAM', '1984-08-15 16:01:33', 'ChampionChip', 3, 3, 'C', 57693150, 'WC', 1),
	('AAAAAAK', '1984-08-15 16:01:50', 'ChampionChip', 4, 3, 'D', 57709850, 'WD', 1),
	('AAAAAAQ', '1984-08-15 16:01:54', 'ChampionChip', 3, 3, 'C', 57714350, 'WC', 1),
	('AAAAAAP', '1984-08-15 16:02:09', 'ChampionChip', 3, 3, 'C', 57729300, 'WC', 1),
	('AAAAAAN', '1984-08-15 16:02:14', 'ChampionChip', 3, 3, 'C', 57734200, 'WC', 1),
	('AAAAAAO', '1984-08-15 16:02:19', 'ChampionChip', 3, 3, 'C', 57739250, 'WC', 1),
	('AAAAAAL', '1984-08-15 16:02:39', 'ChampionChip', 4, 3, 'D', 57758900, 'WD', 1),
	('AAAAAAM', '1984-08-15 16:03:00', 'ChampionChip', 4, 3, 'D', 57779850, 'WD', 1),
	('AAAAAAQ', '1984-08-15 16:03:19', 'ChampionChip', 4, 3, 'D', 57798650, 'WD', 1),
	('AAAAAAP', '1984-08-15 16:03:35', 'ChampionChip', 4, 3, 'D', 57814700, 'WD', 1),
	('AAAAAAN', '1984-08-15 16:03:42', 'ChampionChip', 4, 3, 'D', 57821800, 'WD', 1),
	('AAAAAAO', '1984-08-15 16:03:46', 'ChampionChip', 4, 3, 'D', 57825750, 'WD', 1),
	('AAAAAAU', '1984-08-15 16:08:35', 'ChampionChip', 3, 3, 'C', 58115250, 'WC', 1),
	('AAAAAAT', '1984-08-15 16:08:42', 'ChampionChip', 3, 3, 'C', 58122300, 'WC', 1);

INSERT INTO Times
	(Chip, ChipTime, ChipType, PC, Reader, Antenna, MilliSecs, Location, LapRaw)
VALUES
	('AAAAAAS', '1984-08-15 16:08:59', 'ChampionChip', 3, 3, 'C', 58139350, 'WC', 1),
	('AAAAAAR', '1984-08-15 16:09:14', 'ChampionChip', 3, 3, 'C', 58154400, 'WC', 1),
	('AAAAAAU', '1984-08-15 16:10:00', 'ChampionChip', 4, 3, 'D', 58199750, 'WD', 1),
	('AAAAAAT', '1984-08-15 16:10:06', 'ChampionChip', 4, 3, 'D', 58205700, 'WD', 1),
	('AAAAAAS', '1984-08-15 16:10:22', 'ChampionChip', 4, 3, 'D', 58221650, 'WD', 1),
	('AAAAAAR', '1984-08-15 16:10:38', 'ChampionChip', 4, 3, 'D', 58237600, 'WD', 1),
	('AAAAAAV', '1984-08-15 16:11:33', 'ChampionChip', 3, 3, 'C', 58293200, 'WC', 1),
	('AAAAAAV', '1984-08-15 16:12:59', 'ChampionChip', 4, 3, 'D', 58378800, 'WD', 1),
	('AAAAAAW', '1984-08-15 16:13:39', 'ChampionChip', 3, 3, 'C', 58419150, 'WC', 1),
	('AAAAAAY', '1984-08-15 16:14:26', 'ChampionChip', 3, 3, 'C', 58466150, 'WC', 1),
	('AAAAAAX', '1984-08-15 16:14:36', 'ChampionChip', 3, 3, 'C', 58476100, 'WC', 1),
	('AAAAAAW', '1984-08-15 16:15:06', 'ChampionChip', 4, 3, 'D', 58505850, 'WD', 1),
	('AAAAAAA', '1984-08-15 16:15:15', 'ChampionChip', 5, 3, 'F', 58515150, 'FL', 1),
	('AAAAAAZ', '1984-08-15 16:15:43', 'ChampionChip', 3, 3, 'C', 58543200, 'WC', 1),
	('AAAAAAY', '1984-08-15 16:15:51', 'ChampionChip', 4, 3, 'D', 58550850, 'WD', 1),
	('AAAAAAX', '1984-08-15 16:16:02', 'ChampionChip', 4, 3, 'D', 58561900, 'WD', 1),
	('AAAAAAZ', '1984-08-15 16:17:07', 'ChampionChip', 4, 3, 'D', 58626800, 'WD', 1),
	('AAAAAAB', '1984-08-15 16:18:00', 'ChampionChip', 5, 3, 'F', 58680200, 'FL', 1),
	('AAAAABA', '1984-08-15 16:19:13', 'ChampionChip', 3, 3, 'C', 58753250, 'WC', 1),
	('AAAAABB', '1984-08-15 16:19:44', 'ChampionChip', 4, 3, 'C', 58784300, 'WC', 1),
	('AAAAABA', '1984-08-15 16:20:36', 'ChampionChip', 4, 3, 'D', 58835750, 'WD', 1),
	('AAAAABB', '1984-08-15 16:21:06', 'ChampionChip', 4, 3, 'D', 58865700, 'WD', 1),
	('AAAAAAC', '1984-08-15 16:24:15', 'ChampionChip', 5, 3, 'F', 59055250, 'FL', 1),
	('AAAAAAD', '1984-08-15 16:25:37', 'ChampionChip', 5, 3, 'F', 59137300, 'FL', 1);

INSERT INTO Times
	(Chip, ChipTime, ChipType, PC, Reader, Antenna, MilliSecs, Location, LapRaw)
VALUES
	('AAAAAAE', '1984-08-15 16:26:45', 'ChampionChip', 5, 3, 'F', 59205250, 'FL', 1),
	('AAAAAAF', '1984-08-15 16:27:30', 'ChampionChip', 5, 3, 'F', 59250200, 'FL', 1),
	('AAAAAAG', '1984-08-15 16:28:10', 'ChampionChip', 5, 3, 'F', 59290150, 'FL', 1),
	('AAAAAAH', '1984-08-15 16:28:30', 'ChampionChip', 5, 3, 'F', 59310200, 'FL', 1),
	('AAAAAAI', '1984-08-15 16:29:00', 'ChampionChip', 5, 3, 'F', 59340250, 'FL', 1),
	('AAAAAAJ', '1984-08-15 16:30:10', 'ChampionChip', 5, 3, 'F', 59410300, 'FL', 1),
	('AAAAAAK', '1984-08-15 16:31:10', 'ChampionChip', 5, 3, 'F', 59470250, 'FL', 1),
	('AAAAAAL', '1984-08-15 16:32:15', 'ChampionChip', 5, 3, 'F', 59535200, 'FL', 1),
	('AAAAAAM', '1984-08-15 16:32:52', 'ChampionChip', 5, 3, 'F', 59572150, 'FL', 1),
	('AAAAAAN', '1984-08-15 16:33:50', 'ChampionChip', 5, 3, 'F', 59630200, 'FL', 1),
	('AAAAAAO', '1984-08-15 16:34:10', 'ChampionChip', 5, 3, 'F', 59650250, 'FL', 1),
	('AAAAAAQ', '1984-08-15 16:34:15', 'ChampionChip', 5, 3, 'F', 59655250, 'FL', 1),
	('AAAAAAP', '1984-08-15 16:34:15', 'ChampionChip', 5, 3, 'F', 59655300, 'FL', 1),
	('AAAAAAS', '1984-08-15 16:41:50', 'ChampionChip', 5, 3, 'F', 60110150, 'FL', 1),
	('AAAAAAR', '1984-08-15 16:41:50', 'ChampionChip', 5, 3, 'F', 60110200, 'FL', 1),
	('AAAAAAT', '1984-08-15 16:41:50', 'ChampionChip', 5, 3, 'F', 60110200, 'FL', 1),
	('AAAAAAU', '1984-08-15 16:42:00', 'ChampionChip', 5, 3, 'F', 60120250, 'FL', 1),
	('AAAAAAV', '1984-08-15 16:45:15', 'ChampionChip', 5, 3, 'F', 60315300, 'FL', 1),
	('AAAAAAW', '1984-08-15 16:47:38', 'ChampionChip', 5, 3, 'F', 60458250, 'FL', 1),
	('AAAAAAX', '1984-08-15 16:48:50', 'ChampionChip', 5, 3, 'F', 60530200, 'FL', 1),
	('AAAAAAY', '1984-08-15 16:48:55', 'ChampionChip', 5, 3, 'F', 60535150, 'FL', 1),
	('AAAAAAZ', '1984-08-15 16:50:27', 'ChampionChip', 5, 3, 'F', 60627200, 'FL', 1),
	('AAAAABA', '1984-08-15 16:54:12', 'ChampionChip', 5, 3, 'F', 60852250, 'FL', 1),
	('AAAAABB', '1984-08-15 16:54:58', 'ChampionChip', 5, 3, 'F', 60898300, 'FL', 1);
