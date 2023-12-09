
INSERT  INTO  course  (id, course_name, link, start_date, end_date)  VALUES  (1, 'Java-core', 'http://link1.com', '', '');
INSERT  INTO  course  (id, course_name, link, start_date, end_date)  VALUES  (2, 'Python', 'http://link2.com', '', '');
INSERT  INTO  course  (id, course_name, link, start_date, end_date)  VALUES  (3, 'Security', 'http://link3.com', '', '');

--INSERT  INTO  users  (id, user_name, pass_word, role, contacts)  VALUES  (1, 'nthquyen', '123', 'admin', '123xxxxx');
INSERT  INTO  users  (id, user_name, pass_word, role, contacts)  VALUES  (2, 'hoang', '123', 'trainer', '123xxxxx');


INSERT  INTO  training_schedule  (id, training_type, class_type, course_id, create_date, create_by, training_date, training_time)
VALUES  (1, 'monthly', 'zoom', 1, '', '', '', '');
INSERT  INTO  training_schedule  (id, training_type, class_type, course_id, create_date, create_by, training_date, training_time)
VALUES  (2, 'AD_HOC', 'zoom', 2, '', '', '', '');


INSERT  INTO  training_user_detail  (training_schedule_id, user_id)  VALUES  (1, 1);
INSERT  INTO  training_user_detail  (training_schedule_id, user_id)  VALUES  (1, 2);