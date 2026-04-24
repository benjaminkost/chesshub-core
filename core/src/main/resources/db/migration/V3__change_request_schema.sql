-- table request
DROP TABLE `request`;

-- create new request table
CREATE TABLE `game_request` (
                           `id` bigint NOT NULL,
                           `status` varchar(255) DEFAULT NULL,
                           `game_id` bigint NOT NULL,
                           `recipient_id` bigint DEFAULT NULL,
                           `sender_id` bigint DEFAULT NULL);

ALTER TABLE `game_request`
    ADD PRIMARY KEY (`id`),
    ADD KEY `FK18jr2t8ppb2ayomjywava4kis` (`game_id`),
    ADD KEY `FK5421nydw4vjqseui4802mpy94` (`recipient_id`),
    ADD KEY `FKbsk0wfd7hsxyb3kqqcqrv7k1b` (`sender_id`);

-- Constraints der Tabelle `request`
--
ALTER TABLE `game_request`
    ADD CONSTRAINT `FK18jr2t8ppb2ayomjywava4kis` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`),
    ADD CONSTRAINT `FK5421nydw4vjqseui4802mpy94` FOREIGN KEY (`recipient_id`) REFERENCES `user` (`id`),
    ADD CONSTRAINT `FKbsk0wfd7hsxyb3kqqcqrv7k1b` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`);

-- AUTO_INCREMENT für Tabelle `request`
--
ALTER TABLE `game_request`
    MODIFY `id` bigint NOT NULL AUTO_INCREMENT;