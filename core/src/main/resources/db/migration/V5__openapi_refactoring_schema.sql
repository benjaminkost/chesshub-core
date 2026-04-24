-- 1. Add fields to `user`
ALTER TABLE user 
ADD COLUMN fide_id VARCHAR(255) DEFAULT NULL,
ADD COLUMN lichess_username VARCHAR(255) DEFAULT NULL,
ADD COLUMN chesscom_username VARCHAR(255) DEFAULT NULL;

-- 2. Add fields to `club`
ALTER TABLE club 
ADD COLUMN address VARCHAR(255) DEFAULT NULL;

-- 3. Add fields to `game`
ALTER TABLE game 
ADD COLUMN opening VARCHAR(255) DEFAULT NULL,
ADD COLUMN board VARCHAR(255) DEFAULT NULL,
ADD COLUMN team_id BIGINT DEFAULT NULL;

ALTER TABLE game ADD CONSTRAINT fk_game_team FOREIGN KEY (team_id) REFERENCES team(id);

-- 4. Create explicit Junction Table `team_membership`
CREATE TABLE team_membership (
    user_id BIGINT NOT NULL,
    team_id BIGINT NOT NULL,
    roles VARCHAR(255) DEFAULT 'PLAYER',
    PRIMARY KEY (user_id, team_id),
    CONSTRAINT fk_tm_user FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    CONSTRAINT fk_tm_team FOREIGN KEY (team_id) REFERENCES team(id) ON DELETE CASCADE
);

-- Migrate old data from `user_has_teams` to `team_membership`
INSERT INTO team_membership (user_id, team_id, roles)
SELECT user_id, team_id, 'PLAYER'
FROM user_has_teams;

-- Drop old `user_has_teams` table
DROP TABLE user_has_teams;

-- 5. Create explicit Junction Table `club_membership`
CREATE TABLE club_membership (
    user_id BIGINT NOT NULL,
    club_id BIGINT NOT NULL,
    status VARCHAR(50) DEFAULT 'MEMBER',
    roles VARCHAR(255) DEFAULT 'MEMBER',
    PRIMARY KEY (user_id, club_id),
    CONSTRAINT fk_cm_user FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    CONSTRAINT fk_cm_club FOREIGN KEY (club_id) REFERENCES club(id) ON DELETE CASCADE
);

-- Migrate old presidents into the club membership logic
INSERT INTO club_membership (user_id, club_id, status, roles)
SELECT user_id, id, 'MEMBER', 'DEPUTY_ADMIN'
FROM club
WHERE user_id IS NOT NULL;
