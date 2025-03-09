-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Erstellungszeit: 09. Mrz 2025 um 13:21
-- Server-Version: 9.2.0
-- PHP-Version: 8.2.27

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

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `club`
--

CREATE TABLE `club` (
                        `id` bigint NOT NULL,
                        `name` varchar(255) DEFAULT NULL,
                        `user_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `game`
--

CREATE TABLE `game` (
                        `id` bigint NOT NULL,
                        `black_player_name` varchar(255) DEFAULT NULL,
                        `comment` varchar(45) DEFAULT NULL,
                        `date` varchar(45) DEFAULT NULL,
                        `event` varchar(45) DEFAULT NULL,
                        `moves` mediumtext,
                        `result` varchar(45) DEFAULT NULL,
                        `round` varchar(45) DEFAULT NULL,
                        `site` varchar(45) DEFAULT NULL,
                        `white_player_name` varchar(255) DEFAULT NULL,
                        `black_user_id` bigint DEFAULT NULL,
                        `white_user_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `request`
--

CREATE TABLE `request` (
                           `id` bigint NOT NULL,
                           `status` varchar(255) DEFAULT NULL,
                           `game_id` bigint NOT NULL,
                           `recipient_id` bigint DEFAULT NULL,
                           `sender_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `role`
--

CREATE TABLE `role` (
                        `id` bigint NOT NULL,
                        `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `team`
--

CREATE TABLE `team` (
                        `id` bigint NOT NULL,
                        `name` varchar(255) DEFAULT NULL,
                        `club_id` bigint DEFAULT NULL,
                        `leader_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE `user` (
                        `id` bigint NOT NULL,
                        `email` varchar(255) DEFAULT NULL,
                        `first_name` varchar(255) DEFAULT NULL,
                        `last_name` varchar(255) DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        `phone` varchar(255) DEFAULT NULL,
                        `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `users_roles`
--

CREATE TABLE `users_roles` (
                               `user_id` bigint NOT NULL,
                               `role_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user_has_teams`
--

CREATE TABLE `user_has_teams` (
                                  `user_id` bigint NOT NULL,
                                  `team_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `club`
--
ALTER TABLE `club`
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE KEY `UK81x28p1m6fe4q4nn6tb0aogno` (`user_id`);

--
-- Indizes für die Tabelle `game`
--
ALTER TABLE `game`
    ADD PRIMARY KEY (`id`),
    ADD KEY `FKhonpj8orep0a6clquobs82x0w` (`black_user_id`),
    ADD KEY `FKm8q5r0o3kl5u09okg5jpnirp3` (`white_user_id`);

--
-- Indizes für die Tabelle `request`
--
ALTER TABLE `request`
    ADD PRIMARY KEY (`id`),
    ADD KEY `FK18jr2t8ppb2ayomjywava4kis` (`game_id`),
    ADD KEY `FK5421nydw4vjqseui4802mpy94` (`recipient_id`),
    ADD KEY `FKbsk0wfd7hsxyb3kqqcqrv7k1b` (`sender_id`);

--
-- Indizes für die Tabelle `role`
--
ALTER TABLE `role`
    ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `team`
--
ALTER TABLE `team`
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE KEY `UKg2l9qqsoeuynt4r5ofdt1x2td` (`name`),
    ADD UNIQUE KEY `UK55eo7kil7rg126ddxxkrjsf0h` (`leader_id`),
    ADD KEY `FKnl01gosacvic5nvy7gq3h7v2y` (`club_id`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
    ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `users_roles`
--
ALTER TABLE `users_roles`
    ADD PRIMARY KEY (`user_id`,`role_id`),
    ADD KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`);

--
-- Indizes für die Tabelle `user_has_teams`
--
ALTER TABLE `user_has_teams`
    ADD PRIMARY KEY (`user_id`,`team_id`),
    ADD KEY `FK1urg0se8vfd8onedlrqe5l8o1` (`team_id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `club`
--
ALTER TABLE `club`
    MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `game`
--
ALTER TABLE `game`
    MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `request`
--
ALTER TABLE `request`
    MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `role`
--
ALTER TABLE `role`
    MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `team`
--
ALTER TABLE `team`
    MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`
    MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `club`
--
ALTER TABLE `club`
    ADD CONSTRAINT `FKddxsuag5uhw1yjffefei7w3o6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints der Tabelle `game`
--
ALTER TABLE `game`
    ADD CONSTRAINT `FKhonpj8orep0a6clquobs82x0w` FOREIGN KEY (`black_user_id`) REFERENCES `user` (`id`),
    ADD CONSTRAINT `FKm8q5r0o3kl5u09okg5jpnirp3` FOREIGN KEY (`white_user_id`) REFERENCES `user` (`id`);

--
-- Constraints der Tabelle `request`
--
ALTER TABLE `request`
    ADD CONSTRAINT `FK18jr2t8ppb2ayomjywava4kis` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`),
    ADD CONSTRAINT `FK5421nydw4vjqseui4802mpy94` FOREIGN KEY (`recipient_id`) REFERENCES `user` (`id`),
    ADD CONSTRAINT `FKbsk0wfd7hsxyb3kqqcqrv7k1b` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`);

--
-- Constraints der Tabelle `team`
--
ALTER TABLE `team`
    ADD CONSTRAINT `FKbxs8rhdluvnucyymbjowulrl6` FOREIGN KEY (`leader_id`) REFERENCES `user` (`id`),
    ADD CONSTRAINT `FKnl01gosacvic5nvy7gq3h7v2y` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`);

--
-- Constraints der Tabelle `users_roles`
--
ALTER TABLE `users_roles`
    ADD CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    ADD CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

--
-- Constraints der Tabelle `user_has_teams`
--
ALTER TABLE `user_has_teams`
    ADD CONSTRAINT `FK1urg0se8vfd8onedlrqe5l8o1` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`),
    ADD CONSTRAINT `FKiq917ssgqlhunp4qsqlf3158` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
