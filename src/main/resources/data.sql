insert into users (email, password, is_admin) values ('egu@gmail.com', 'egu', false);
insert into milestones (user_id, name, description, is_complete, progress) values (1, '2023年1月1日:ローンチ', 'ローンチ', false, 0);
insert into milestones (user_id, name, description, is_complete, progress) values (1, '2022年10月31日:プレリリース', '早期リリース', false, 0);
insert into milestones (user_id, name, description, is_complete, progress) values (1, '2022年7月10日:デモ開始', 'デモ作成中', false, 0);
insert into tasks (user_id, milestone_id, name, description, is_complete) values (1, 1, 'タスク1', 'タスク1の詳細', false);
insert into tasks (user_id, milestone_id, name, description, is_complete) values (1, 1, 'タスク2', 'タスク2の詳細', false);
insert into tasks (user_id, milestone_id, name, description, is_complete) values (1, 2, 'タスク3', 'タスク3の詳細', false);
insert into tasks (user_id, milestone_id, name, description, is_complete) values (1, 2, 'タスク4', 'タスク4の詳細', false);
insert into tasks (user_id, milestone_id, name, description, is_complete) values (1, 3, 'タスク5', 'タスク5の詳細', false);
insert into tasks (user_id, milestone_id, name, description, is_complete) values (1, 3, 'タスク6', 'タスク6の詳細', false);
