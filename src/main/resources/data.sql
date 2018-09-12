INSERT INTO t_department (department_id, department_name) VALUES (1, 'HUE Development') ON conflict(department_id) DO NOTHING;
INSERT INTO t_post VALUES (1, 0, 'Java Developer', 'Shanghai', 'Java Developer', 'Java', 'R&D', '2018-09-01', 30, 0, 0, 1) ON conflict(post_id) DO NOTHING;
INSERT INTO t_applicant (applicant_id) VALUES (1) ON conflict(applicant_id) DO NOTHING;
INSERT INTO t_applicant (applicant_id) VALUES (2) ON conflict(applicant_id) DO NOTHING;
INSERT INTO t_applicant (applicant_id) VALUES (3) ON conflict(applicant_id) DO NOTHING;
INSERT INTO t_applicant (applicant_id) VALUES (4) ON conflict(applicant_id) DO NOTHING;
INSERT INTO t_applicant (applicant_id) VALUES (5) ON conflict(applicant_id) DO NOTHING;
INSERT INTO t_applicant (applicant_id) VALUES (6) ON conflict(applicant_id) DO NOTHING;
INSERT INTO t_applicant (applicant_id) VALUES (7) ON conflict(applicant_id) DO NOTHING;
INSERT INTO t_applicant (applicant_id) VALUES (8) ON conflict(applicant_id) DO NOTHING;
INSERT INTO t_applicant (applicant_id) VALUES (9) ON conflict(applicant_id) DO NOTHING;
INSERT INTO t_applicant (applicant_id) VALUES (10) ON conflict(applicant_id) DO NOTHING;
INSERT INTO t_application (application_id, create_time, status, applicant_id, post_id) VALUES (1, '2018-09-02', 1, 1, 1) ON conflict(application_id) DO NOTHING;
INSERT INTO t_application (application_id, create_time, status, applicant_id, post_id) VALUES (2, '2018-09-02', 1, 2, 1) ON conflict(application_id) DO NOTHING;
INSERT INTO t_application (application_id, create_time, status, applicant_id, post_id) VALUES (3, '2018-09-02', 1, 3, 1) ON conflict(application_id) DO NOTHING;
INSERT INTO t_application (application_id, create_time, status, applicant_id, post_id) VALUES (4, '2018-09-02', 1, 4, 1) ON conflict(application_id) DO NOTHING;
INSERT INTO t_application (application_id, create_time, status, applicant_id, post_id) VALUES (5, '2018-09-02', 1, 5, 1) ON conflict(application_id) DO NOTHING;
INSERT INTO t_application (application_id, create_time, status, applicant_id, post_id) VALUES (6, '2018-09-02', 1, 6, 1) ON conflict(application_id) DO NOTHING;
INSERT INTO t_application (application_id, create_time, status, applicant_id, post_id) VALUES (7, '2018-09-02', 1, 7, 1) ON conflict(application_id) DO NOTHING;
INSERT INTO t_application (application_id, create_time, status, applicant_id, post_id) VALUES (8, '2018-09-02', 1, 8, 1) ON conflict(application_id) DO NOTHING;
INSERT INTO t_application (application_id, create_time, status, applicant_id, post_id) VALUES (9, '2018-09-02', 1, 9, 1) ON conflict(application_id) DO NOTHING;
INSERT INTO t_application (application_id, create_time, status, applicant_id, post_id) VALUES (10, '2018-09-02', 1, 10, 1) ON conflict(application_id) DO NOTHING;
INSERT INTO t_interviewer (interviewer_id, interviewer_age, interviewer_department, interviewer_name, interviewer_position, department_id) VALUES (1, 25, 'HUE Development', 'Interviewer_1', 'Java Developer', 1) ON conflict(interviewer_id) DO NOTHING;
INSERT INTO t_interviewer (interviewer_id, interviewer_age, interviewer_department, interviewer_name, interviewer_position, department_id) VALUES (2, 25, 'HUE Development', 'Interviewer_2', 'Java Developer', 1) ON conflict(interviewer_id) DO NOTHING;
INSERT INTO t_interviewer (interviewer_id, interviewer_age, interviewer_department, interviewer_name, interviewer_position, department_id) VALUES (3, 25, 'HUE Development', 'Interviewer_3', 'Java Developer', 1) ON conflict(interviewer_id) DO NOTHING;
INSERT INTO t_interviewer (interviewer_id, interviewer_age, interviewer_department, interviewer_name, interviewer_position, department_id) VALUES (4, 25, 'HUE Development', 'Interviewer_4', 'Java Developer', 1) ON conflict(interviewer_id) DO NOTHING;
INSERT INTO t_interviewer (interviewer_id, interviewer_age, interviewer_department, interviewer_name, interviewer_position, department_id) VALUES (5, 25, 'HUE Development', 'Interviewer_5', 'Java Developer', 1) ON conflict(interviewer_id) DO NOTHING;
