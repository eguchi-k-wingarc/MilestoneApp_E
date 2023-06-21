create table milestones (
id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(256) NOT NULl,
description VARCHAR(256) NOT NULl,
deadline timestamp NOT NULL default current_timestamp,
created_at timestamp  default current_timestamp,
updated_at timestamp default current_timestamp on update current_timestamp
);
