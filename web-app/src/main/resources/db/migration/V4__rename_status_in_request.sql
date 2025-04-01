-- delete status
ALTER TABLE `game_request`
DROP COLUMN `status`;

-- add game_request_status
ALTER TABLE `game_request`
ADD `game_request_status` varchar(255) DEFAULT NULL;