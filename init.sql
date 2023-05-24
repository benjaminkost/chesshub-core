-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 04. Mai 2023 um 09:03
-- Server-Version: 10.4.28-MariaDB
-- PHP-Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `chessmanagement`
--

CREATE DATABASE IF NOT EXISTS chessmanagement;
USE chessmanagement;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `requests`
--

CREATE TABLE `requests` (
  `Request_ID` int(11) NOT NULL,
`Sender_ID` int(11) NOT NULL,
`Recipient_ID` int(11) NOT NULL,
`Game_ID` int(11) NOT NULL,
  `Status` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `club`
--

CREATE TABLE `club` (
  `Club_ID` int(11) NOT NULL,
  `Clubname` varchar(45) DEFAULT NULL,
  `User_ID` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `club`
--

INSERT INTO `club` (`Club_ID`, `Clubname`, `User_ID`) VALUES
(1, 'HWR-Berlin', '1'),
(2, 'TU-Berlin', '2');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `game`
--

CREATE TABLE `game` (
  `Game_ID` int(11) NOT NULL,
  `Event` varchar(45) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Round` varchar(45) DEFAULT NULL,
  `Site` varchar(45) DEFAULT NULL,
  `Moves` mediumtext DEFAULT NULL,
  `Result` varchar(45) DEFAULT NULL,
  `White` int(11) DEFAULT NULL,
  `Black` int(11) DEFAULT NULL,
  `Comment` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `game`
--

INSERT INTO `game` (`Game_ID`, `Event`, `Date`, `Round`, `Site`, `Moves`, `Result`, `White`, `Black`, `Comment`) VALUES
(81, 'Lloyds Bank op', '1984-12-12', '1', 'London', ' 1. e4 e6 2. d4 d5 3. Nd2 Nf6 4. e5 Nfd7 5. f4 c5 6. c3 Nc6 7. Ndf3 cxd4 8. cxd4 f6  9. Bd3 Bb4+ 10. Bd2 Qb6 11. Ne2 fxe5 12. fxe5 O-O 13. a3 Be7 14. Qc2 Rxf3 15. gxf3 Nxd4  16. Nxd4 Qxd4 17. O-O-O Nxe5 18. Bxh7+ Kh8 19. Kb1 Qh4 20. Bc3 Bf6 21. f4 Nc4 22. Bxf6 Qxf6  23. Bd3 b5 24. Qe2 Bd7 25. Rhg1 Be8 26. Rde1 Bf7 27. Rg3 Rc8 28. Reg1 Nd6 29. Rxg7 Nf5  30. R7g5 Rc7 31. Bxf5 exf5 32. Rh5+  1-0', '1-0', 1, 0, 'David Sedgwick'),
(82, 'Schacholympiade 1992', '1992-01-17', '1', 'Manila', ' 1. e4 c6 2. d4 d5 3. exd5 cxd5 4. Nc3 Nc6 5. Bb5 Nf6 6. Nge2 Bg4 7. O-O e6 8. f3 Bf5 9. a3 a6 10. Ba4 b5 11. Bb3 Be7 12. Ng3 Bg6 13. f4 Qb6 14. Nce2 Nh5 15. c3 O-O 16. f5 Nxg3 17. Nxg3 exf5 18. Nxf5 Rad8 19. Nxe7+ Nxe7 20. Bg5 f6 21. Bd2 Rfe8 22. Qg4 f5 23. Qg5 a5 24. Bc2 h6 25. Qh4 f4 26. Bxg6 Nxg6 27. Qg4 Re4 28. Bxf4 Rf8 29. Bc7 Rxf1+ 30. Kxf1 Qf6+ 31. Qf3 Qxf3+ 32. gxf3 Re3 33. Kf2 Re7 34. Bxa5 Nf4 35. Kg3 Nh5+ 36. Kf2 Nf4 37. Kg3 Nh5+ 38. Kh4 Nf4 39. a4 bxa4 40. Rxa4 g5+ 41. Kg4 Ng2 42. Kg3 Ne3 43. b4 h5 44. Ra2 h4+ 45. Kf2 Nd1+ 46. Kg2 Nxc3 47. Rc2 Nb5 48. Bb6 Re3 49. Rd2 Rb3 50. Bc5 Na3 51. Re2 Nc4 52. Be7 Ne3+ 53. Kf2 Nd1+ 54. Ke1 Rb1 55. Kd2 Nb2 56. Kc2 Rd1 57. Bc5 Rf1 58. Kxb2 Rxf3 59. Rg2 Rf5 60. b5 Kh7 61. b6 Rf7 62. Rxg5 Kh6 63. Rg2 Kh5 64. Kc3 h3 65. Re2 Kg4 66. Bd6 Rb7 67. Rb2 Kf3 68. Kd3 Kg4 69. Ke3 Kf5 70. Bc7 Ke6 71. Ra2 Kd7 72. Ra7 Kc6 73. Rxb7 Kxb7 74. Kf4 Ka8 75. Ke5 Kb7 76. Kxd5 Ka6 77. Ke6 Kb7 78. d5 Kc8 79. d6 Kb7 80. d7 Kc6 81. d8=Q Kb7 82. Qd3 Ka8 83. Qa6# 1-0', '1-0', 0, 1, 'Bobby Fischer'),
(84, 'Z/C Return Match', '2023-04-27', '1', 'Berlin', ' 1. e4 e5 2. Nf3 Nc6 3. Bb5 a6 4. Ba4 Nf6 5. O-O Be7 6. Re1 b5 7. Bb3 d6 8. c3 O-O 9. h3 Nb8 10. d4 Nbd7 11. c4 c6 12. cxb5 axb5 13. Nc3 Bb7 14. Bg5 b4 15. Nb1 h6 16. Bh4 c5 17. dxe5 Nxe4 18. Bxe7 Qxe7 19. exd6 Qf6 20. Nbd2 Nxd6 21. Nc4 Nxc4 22. Bxc4 Nb6 23. Ne5 Rae8 24. Bxf7+ Rxf7 25. Nxf7 Rxe1+ 26. Qxe1 Kxf7 27. Qe3 Qg5 28. Qxg5 hxg5 29. b3 Ke6 30. a3 Kd6 31. axb4 cxb4 32. Ra5 Nd5 33. f3 Bc8 34. Kf2 Bf5 35. Ra7 g6 36. Ra6+ Kc5 37. Ke1 Nf4 38. g3 Nxh3 39. Kd2 Kb5 40. Rd6 Kc5 41. Ra6 Nf2 42. g4 Bd3 43. Re6 1/2-1/2', '1/2-1/2', 2, 0, 'Magnus Carlsen'),
(89, '', '2001-01-01', '1', '', '1.e4 e5 2.Bc4 Na6 3.Qh5 Nf6 4.Qxf7# *', '', 0, 1, 'Bobby Fischer');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `team`
--

CREATE TABLE `team` (
  `Team_ID` int(11) NOT NULL,
  `Teamname` varchar(255) NOT NULL,
  `User_ID` int(11) DEFAULT NULL,
  `Club_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `team`
--

INSERT INTO `team` (`Team_ID`, `Teamname`, `User_ID`, `Club_ID`) VALUES
(1, 'WI21A-Team', 3, 1),
(3, 'IP21', 1, 1),
(5, 'Info-Nerds', 4, 2),
(32, 'IN23A', 4, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE `user` (
  `User_ID` int(11) NOT NULL,
  `Firstname` varchar(45) DEFAULT NULL,
  `Lastname` varchar(45) DEFAULT NULL,
  `Email` varchar(45) NOT NULL,
  `Password` mediumtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`User_ID`, `Firstname`, `Lastname`, `Email`, `Password`) VALUES
(0, '0', '0', '0', '0'),
(1, 'Filip', 'Topic', 'filip@demo.com', '4db360dbcaaeaf38a5bec30df751281e8cb42914a95ef1a25d1fe0c7c69dd8fd'),
(2, 'Lukas', 'Zander', 'lukas@demo.com', '503395e9028e14921443607de4b994a04be835abd36b24b62d884b6b2ab264f3'),
(3, 'Ben', 'Kostka', 'ben@demo.com', 'cf8b421b1ce791768b61321c9dd7256cf562c13a49211ed1f69fa449ce3cb7a4'),
(4, 'Azad', 'Celik', 'azad@demo.com', '1b18033d8286c4efc126b8a131e85db079c731aca276c9204b6221ca00fedbb0');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user_has_teams`
--

CREATE TABLE `user_has_teams` (
  `user_ID` int(11) NOT NULL,
  `team_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `user_has_teams`
--

INSERT INTO `user_has_teams` (`user_ID`, `team_ID`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `requests`
--
ALTER TABLE `requests`
  ADD PRIMARY KEY (`Request_ID`);

--
-- Indizes für die Tabelle `club`
--
ALTER TABLE `club`
  ADD PRIMARY KEY (`Club_ID`);

--
-- Indizes für die Tabelle `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`Game_ID`),
  ADD KEY `Black_idx` (`Black`),
  ADD KEY `White_idx` (`White`);

--
-- Indizes für die Tabelle `team`
--
ALTER TABLE `team`
  ADD PRIMARY KEY (`Team_ID`),
  ADD KEY `User_ID` (`User_ID`),
  ADD KEY `Club_ID` (`Club_ID`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`User_ID`);

--
-- Indizes für die Tabelle `user_has_teams`
--
ALTER TABLE `user_has_teams`
  ADD PRIMARY KEY (`user_ID`,`team_ID`),
  ADD KEY `team_ID` (`team_ID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `requests`
--
ALTER TABLE `requests`
  MODIFY `Request_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT für Tabelle `club`
--
ALTER TABLE `club`
  MODIFY `Club_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT für Tabelle `game`
--
ALTER TABLE `game`
  MODIFY `Game_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=91;

--
-- AUTO_INCREMENT für Tabelle `team`
--
ALTER TABLE `team`
  MODIFY `Team_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`
  MODIFY `User_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=155;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `requests`
--
ALTER TABLE `requests`
  ADD CONSTRAINT `Sender_ID` FOREIGN KEY (`Sender_ID`) REFERENCES `user` (`User_ID`),
  ADD CONSTRAINT `Recipient_ID` FOREIGN KEY (`Recipient_ID`) REFERENCES `user` (`User_ID`),
  ADD CONSTRAINT `Game_ID` FOREIGN KEY (`Game_ID`) REFERENCES `game` (`Game_ID`),
  ADD CONSTRAINT `Sender_Recipient_Game` UNIQUE (`Sender_ID`,`Recipient_ID`,`Game_ID`);
--
-- Constraints der Tabelle `game`
--
ALTER TABLE `game`
  ADD CONSTRAINT `Black` FOREIGN KEY (`Black`) REFERENCES `user` (`User_ID`),
  ADD CONSTRAINT `White` FOREIGN KEY (`White`) REFERENCES `user` (`User_ID`);

--
-- Constraints der Tabelle `team`
--
ALTER TABLE `team`
  ADD CONSTRAINT `Leader` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`),
  ADD CONSTRAINT `Teamowner` FOREIGN KEY (`Club_ID`) REFERENCES `club` (`Club_ID`);

--
-- Constraints der Tabelle `user_has_teams`
--
ALTER TABLE `user_has_teams`
  ADD CONSTRAINT `user_has_teams_ibfk_1` FOREIGN KEY (`user_ID`) REFERENCES `user` (`User_ID`),
  ADD CONSTRAINT `user_has_teams_ibfk_2` FOREIGN KEY (`team_ID`) REFERENCES `team` (`Team_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
