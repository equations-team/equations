CREATE TABLE IF NOT EXISTS user(
    `user_id` VARCHAR(36),
    `user_name` VARCHAR(255),
    `user_password` VARCHAR(255),
    PRIMARY KEY (`user_id`)
);

CREATE TABLE IF NOT EXISTS game(
    `game_id` VARCHAR(36),
    `ref_user_id_one` VARCHAR(36),
    `ref_user_id_two` VARCHAR(36),
    `ref_user_id_three` VARCHAR(36),
    `user_one_score` INTEGER(10),
    `user_two_score` INTEGER(10),
    `user_three_score` INTEGER(10),
    PRIMARY KEY (`game_id`)
);