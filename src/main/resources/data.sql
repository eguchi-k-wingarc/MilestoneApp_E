insert into users (name, email, password, authorities) values ('egu', 'egu@gmail.com', '$2a$10$Qb2HInxnyuztG4yG/m1TxOYux8UgiVkVLqB.94QTjqFTVOYQ0pjsa', 'ROLE_ADMIN');
insert into users (name, email, password, authorities) values ('egu2', 'egu2@gmail.com', '$2a$10$rYpIV6lZNA.fKwcURw7/KOfIbvJxMg5jTDFtznAMh79j3nEvnhBQG', 'ROLE_USER');
insert into milestones (user_id, name, description, progress, deadline) values (1, 'ローンチ', 'ローンチ', 50, '2024-06-30 23:59:00');
insert into milestones (user_id, name, description, progress, deadline) values (1, 'プレリリース', '早期リリース', 50, '2024-06-15 23:59:00');
insert into milestones (user_id, name, description, progress, deadline) values (1, 'デモ開始', 'デモ作成中', 50, '2024-06-1 23:59:00');
insert into tasks (user_id, milestone_id, name, description, is_complete) values (1, 1, 'タスク1', 'タスク1の詳細', true);
insert into tasks (user_id, milestone_id, name, description, is_complete) values (1, 1, 'タスク2', 'タスク2の詳細', false);
insert into tasks (user_id, milestone_id, name, description, is_complete) values (1, 2, 'タスク3', 'タスク3の詳細', true);
insert into tasks (user_id, milestone_id, name, description, is_complete) values (1, 2, 'タスク4', 'タスク4の詳細', false);
insert into tasks (user_id, milestone_id, name, description, is_complete) values (1, 3, 'タスク5', 'タスク5の詳細', true);
insert into tasks (user_id, milestone_id, name, description, is_complete) values (1, 3, 'タスク6', 'タスク6の詳細', false);
insert into labels (name, color) values ('HELL', 'red');
insert into labels (name, color) values ('DEATH', 'green');
insert into labels (name, color) values ('MURI', 'black');
insert into tasklabels (task_id, label_id) values (1, 1);
insert into tasklabels (task_id, label_id) values (1, 2);
insert into tasklabels (task_id, label_id) values (1, 3);