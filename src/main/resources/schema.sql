create table users (
id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
email VARCHAR(256), -- TODO: NOT NULL制約の付与
password VARCHAR(256), -- TODO: NOT NULL制約の付与
is_admin BOOLEAN NOT NUll,
created_at timestamp  default current_timestamp,
updated_at timestamp default current_timestamp on update current_timestamp,
deleted_at timestamp NUll default NUll
);

create table milestones (
id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
user_id BIGINT, -- TODO: NOT NULL制約の付与
name VARCHAR(256) NOT NULl,
description VARCHAR(256) NOT NULl,
is_complete BOOLEAN NOT NUll default false,
progress INT NOT NUll default 0,
deadline timestamp NOT NULL default current_timestamp,
created_at timestamp  default current_timestamp,
updated_at timestamp default current_timestamp on update current_timestamp,
FOREIGN KEY (user_id) REFERENCES users(id)
);

create table tasks (
id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
user_id BIGINT, -- TODO: NOT NULL制約の付与
milestone_id BIGINT, -- TODO: NOT NULL制約の付与
name VARCHAR(256) NOT NUll,
description VARCHAR(256) NOT NULl,
is_complete BOOLEAN NOT NUll default false,
deadline timestamp NOT NULL default current_timestamp,
created_at timestamp  default current_timestamp,
updated_at timestamp default current_timestamp on update current_timestamp,

FOREIGN KEY (user_id) REFERENCES users(id),
FOREIGN KEY (milestone_id) REFERENCES milestones(id)
);