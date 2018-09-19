package com.targaryen.octopus.applicationRunner;

import com.targaryen.octopus.service.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Autowired
    JdbcTemplate template;
    @Autowired
    ServiceFactory serviceFactory;

    private static final String SELECT_SQL = "SELECT batch_id from t_batch where batch_id = 1";

    private static final String INSERT_SQL =
            "INSERT INTO t_batch (batch_id, batch_name, number, start_time, year) VALUES (1, '2018 Autumn', 2, '2018-09-01', '2018-01-01');\n" +
                    "INSERT INTO t_batch (batch_id, batch_name, number, start_time, end_time, year) VALUES (2, '2018 Spring', 1, '2018-03-01', '2018-05-01', '2018-01-01');\n" +
                    "INSERT INTO t_batch (batch_id, batch_name, number, start_time, end_time, year) VALUES (3, '2017 Autumn', 2, '2017-09-01', '2017-11-01', '2017-01-01');\n" +
                    "INSERT INTO t_batch (batch_id, batch_name, number, start_time, end_time, year) VALUES (4, '2017 Spring', 1, '2017-03-01', '2017-05-01', '2017-01-01');\n" +
                    "INSERT INTO t_batch (batch_id, batch_name, number, start_time, end_time, year) VALUES (5, '2016 Autumn', 2, '2016-09-01', '2016-11-01', '2016-01-01');\n" +
                    "INSERT INTO t_batch (batch_id, batch_name, number, start_time, end_time, year) VALUES (6, '2016 Spring', 1, '2016-03-01', '2016-05-01', '2016-01-01');\n" +
                    "ALTER SEQUENCE t_batch_seq RESTART WITH 7;" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type, application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (1,  2, 'Tsinghua University', 0, 5, 5, 4, 4, 1);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type, application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (2,  2, 'Peking University', 0, 7, 7, 7, 7, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type, application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (3,  2, 'Beihang University', 0, 9, 8, 5, 4, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type, application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (4,  2, 'University of Chinese Academy of Sciences', 0, 3, 3, 2, 2, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type, application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (5,  2, 'Southeast University', 0, 16, 13, 6, 5, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type, application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (6,  2, 'Fudan University', 0, 12, 10, 8, 8, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type, application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (7,  2, 'Shanghai Jiao Tong University', 0, 11, 10, 5, 5, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type, application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (8,  2, 'Nanjing University', 0, 14, 10, 5, 5, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type, application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (9,  2, 'East China Normal University', 0, 13, 8, 5, 4, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type, application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (10, 2, 'Tongji University', 0, 16, 11, 6, 5, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type, application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (11, 2, 'University of Electronic Science and Technology of China', 0, 4, 3, 1, 1, 0);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type, application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (12, 2, 'Nanjing University of Science and Technology', 0, 19, 13, 5, 4, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type, application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (13, 2, 'Beijing', 1, 21, 16, 10, 9, 5);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type, application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (14, 2, 'Shanghai', 1, 37, 30, 15, 13, 7);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type, application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (15, 2, 'Shenzhen', 1, 3, 3, 1, 1, 1);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type, application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (16, 2, 'Guangzhou', 1, 16, 14, 7, 6, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type, application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (17, 2, 'Nanjing', 1, 30, 25, 12, 11, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type, application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (18, 2, 'Chengdu', 1, 4, 3, 1, 1, 1);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type, application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (19, 2, 'Hangzhou', 1, 18, 15, 7, 7, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type, application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (20, 2, 'C++ Developer', 2, 41, 35, 15, 14, 8);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type, application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (21, 2, 'Java Developer', 2, 45, 35, 13, 12, 6);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type, application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (22, 2, 'IT Support', 2, 16, 14, 12, 12, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type, application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (23, 2, 'Marketing', 2, 8, 6, 3, 2, 1);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type, application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (24, 2, 'Testing Engineer', 2, 20, 17, 9, 8, 5);\n" +
                    "ALTER SEQUENCE t_source_file_seq RESTART WITH 25;\n" +
                    "INSERT INTO t_department (department_id, department_name) VALUES (1, 'HUE Development') ON conflict(department_id) DO NOTHING;\n" +
                    "ALTER SEQUENCE t_department_seq RESTART WITH 2;" +
                    "INSERT INTO t_user (user_id, user_name, user_password) VALUES (1, 'dpt_manager', '$2a$10$e4e9uIUe4L4i2enYkXbmiupCUsVNtpnkMPMQm10Mj0/pN0sl/zvQO') ON conflict(user_id) DO NOTHING;\n" +
                    "INSERT INTO t_user (user_id, user_name, user_password) VALUES (2, 'hr', '$2a$10$e4e9uIUe4L4i2enYkXbmiupCUsVNtpnkMPMQm10Mj0/pN0sl/zvQO') ON conflict(user_id) DO NOTHING;\n" +
                    "INSERT INTO t_user (user_id, user_name, user_password) VALUES (3, 'applicant_1', '$2a$10$e4e9uIUe4L4i2enYkXbmiupCUsVNtpnkMPMQm10Mj0/pN0sl/zvQO') ON conflict(user_id) DO NOTHING;\n" +
                    "INSERT INTO t_user (user_id, user_name, user_password) VALUES (4, 'applicant_2', '$2a$10$e4e9uIUe4L4i2enYkXbmiupCUsVNtpnkMPMQm10Mj0/pN0sl/zvQO') ON conflict(user_id) DO NOTHING;\n" +
                    "INSERT INTO t_user (user_id, user_name, user_password) VALUES (5, 'applicant_3', '$2a$10$e4e9uIUe4L4i2enYkXbmiupCUsVNtpnkMPMQm10Mj0/pN0sl/zvQO') ON conflict(user_id) DO NOTHING;\n" +
                    "INSERT INTO t_user (user_id, user_name, user_password) VALUES (6, 'applicant_4', '$2a$10$e4e9uIUe4L4i2enYkXbmiupCUsVNtpnkMPMQm10Mj0/pN0sl/zvQO') ON conflict(user_id) DO NOTHING;\n" +
                    "INSERT INTO t_user (user_id, user_name, user_password) VALUES (7, 'applicant_5', '$2a$10$e4e9uIUe4L4i2enYkXbmiupCUsVNtpnkMPMQm10Mj0/pN0sl/zvQO') ON conflict(user_id) DO NOTHING;\n" +
                    "INSERT INTO t_user (user_id, user_name, user_password) VALUES (8, 'applicant_6', '$2a$10$e4e9uIUe4L4i2enYkXbmiupCUsVNtpnkMPMQm10Mj0/pN0sl/zvQO') ON conflict(user_id) DO NOTHING;\n" +
                    "INSERT INTO t_user (user_id, user_name, user_password) VALUES (9, 'applicant_7', '$2a$10$e4e9uIUe4L4i2enYkXbmiupCUsVNtpnkMPMQm10Mj0/pN0sl/zvQO') ON conflict(user_id) DO NOTHING;\n" +
                    "INSERT INTO t_user (user_id, user_name, user_password) VALUES (10, 'applicant_8', '$2a$10$e4e9uIUe4L4i2enYkXbmiupCUsVNtpnkMPMQm10Mj0/pN0sl/zvQO') ON conflict(user_id) DO NOTHING;\n" +
                    "INSERT INTO t_user (user_id, user_name, user_password) VALUES (11, 'applicant_9', '$2a$10$e4e9uIUe4L4i2enYkXbmiupCUsVNtpnkMPMQm10Mj0/pN0sl/zvQO') ON conflict(user_id) DO NOTHING;\n" +
                    "INSERT INTO t_user (user_id, user_name, user_password) VALUES (12, 'applicant_10', '$2a$10$e4e9uIUe4L4i2enYkXbmiupCUsVNtpnkMPMQm10Mj0/pN0sl/zvQO') ON conflict(user_id) DO NOTHING;\n" +
                    "INSERT INTO t_user (user_id, user_name, user_password) VALUES (13, 'interviewer_1', '$2a$10$e4e9uIUe4L4i2enYkXbmiupCUsVNtpnkMPMQm10Mj0/pN0sl/zvQO') ON conflict(user_id) DO NOTHING;\n" +
                    "INSERT INTO t_user (user_id, user_name, user_password) VALUES (14, 'interviewer_2', '$2a$10$e4e9uIUe4L4i2enYkXbmiupCUsVNtpnkMPMQm10Mj0/pN0sl/zvQO') ON conflict(user_id) DO NOTHING;\n" +
                    "INSERT INTO t_user (user_id, user_name, user_password) VALUES (15, 'interviewer_3', '$2a$10$e4e9uIUe4L4i2enYkXbmiupCUsVNtpnkMPMQm10Mj0/pN0sl/zvQO') ON conflict(user_id) DO NOTHING;\n" +
                    "INSERT INTO t_user (user_id, user_name, user_password) VALUES (16, 'interviewer_4', '$2a$10$e4e9uIUe4L4i2enYkXbmiupCUsVNtpnkMPMQm10Mj0/pN0sl/zvQO') ON conflict(user_id) DO NOTHING;\n" +
                    "INSERT INTO t_user (user_id, user_name, user_password) VALUES (17, 'interviewer_5', '$2a$10$e4e9uIUe4L4i2enYkXbmiupCUsVNtpnkMPMQm10Mj0/pN0sl/zvQO') ON conflict(user_id) DO NOTHING;\n" +
                    "INSERT INTO t_user (user_id, user_name, user_password) VALUES (18, 'maintenance', '$2a$10$e4e9uIUe4L4i2enYkXbmiupCUsVNtpnkMPMQm10Mj0/pN0sl/zvQO') ON conflict(user_id) DO NOTHING;\n" +
                    "ALTER SEQUENCE t_user_seq RESTART WITH 19;" +
                    "INSERT INTO t_role (role_id, role, user_id) VALUES (1, 'ROLE_DPT', 1) ON conflict(role_id) DO NOTHING;\n" +
                    "INSERT INTO t_role (role_id, role, user_id) VALUES (2, 'ROLE_HR', 2) ON conflict(role_id) DO NOTHING;\n" +
                    "INSERT INTO t_role (role_id, role, user_id) VALUES (3, 'ROLE_APPLICANT', 3) ON conflict(role_id) DO NOTHING;\n" +
                    "INSERT INTO t_role (role_id, role, user_id) VALUES (4, 'ROLE_APPLICANT', 4) ON conflict(role_id) DO NOTHING;\n" +
                    "INSERT INTO t_role (role_id, role, user_id) VALUES (5, 'ROLE_APPLICANT', 5) ON conflict(role_id) DO NOTHING;\n" +
                    "INSERT INTO t_role (role_id, role, user_id) VALUES (6, 'ROLE_APPLICANT', 6) ON conflict(role_id) DO NOTHING;\n" +
                    "INSERT INTO t_role (role_id, role, user_id) VALUES (7, 'ROLE_APPLICANT', 7) ON conflict(role_id) DO NOTHING;\n" +
                    "INSERT INTO t_role (role_id, role, user_id) VALUES (8, 'ROLE_APPLICANT', 8) ON conflict(role_id) DO NOTHING;\n" +
                    "INSERT INTO t_role (role_id, role, user_id) VALUES (9, 'ROLE_APPLICANT', 9) ON conflict(role_id) DO NOTHING;\n" +
                    "INSERT INTO t_role (role_id, role, user_id) VALUES (10, 'ROLE_APPLICANT', 10) ON conflict(role_id) DO NOTHING;\n" +
                    "INSERT INTO t_role (role_id, role, user_id) VALUES (11, 'ROLE_APPLICANT', 11) ON conflict(role_id) DO NOTHING;\n" +
                    "INSERT INTO t_role (role_id, role, user_id) VALUES (12, 'ROLE_APPLICANT', 12) ON conflict(role_id) DO NOTHING;\n" +
                    "INSERT INTO t_role (role_id, role, user_id) VALUES (13, 'ROLE_INTERVIEWER', 13) ON conflict(role_id) DO NOTHING;\n" +
                    "INSERT INTO t_role (role_id, role, user_id) VALUES (14, 'ROLE_INTERVIEWER', 14) ON conflict(role_id) DO NOTHING;\n" +
                    "INSERT INTO t_role (role_id, role, user_id) VALUES (15, 'ROLE_INTERVIEWER', 15) ON conflict(role_id) DO NOTHING;\n" +
                    "INSERT INTO t_role (role_id, role, user_id) VALUES (16, 'ROLE_INTERVIEWER', 16) ON conflict(role_id) DO NOTHING;\n" +
                    "INSERT INTO t_role (role_id, role, user_id) VALUES (17, 'ROLE_INTERVIEWER', 17) ON conflict(role_id) DO NOTHING;\n" +
                    "INSERT INTO t_role (role_id, role, user_id) VALUES (18, 'ROLE_MAINTENANCE', 18) ON conflict(role_id) DO NOTHING;\n" +
                    "ALTER SEQUENCE t_role_seq RESTART WITH 19;" +
                    "INSERT INTO t_dpt_manager (dpt_manager_id, department_id, user_id)VALUES (1, 1, 1) ON conflict(dpt_manager_id) DO NOTHING;\n" +
                    "ALTER SEQUENCE t_dpt_manager_seq RESTART WITH 2;" +
                    "INSERT INTO t_hr (hr_id, user_id) VALUES (1, 2) ON conflict(hr_id) DO NOTHING;\n" +
                    "ALTER SEQUENCE t_hr_seq RESTART WITH 2;" +
                    "INSERT INTO t_applicant (applicant_id, user_id) VALUES (1, 3) ON conflict(applicant_id) DO NOTHING;\n" +
                    "INSERT INTO t_applicant (applicant_id, user_id) VALUES (2, 4) ON conflict(applicant_id) DO NOTHING;\n" +
                    "INSERT INTO t_applicant (applicant_id, user_id) VALUES (3, 5) ON conflict(applicant_id) DO NOTHING;\n" +
                    "INSERT INTO t_applicant (applicant_id, user_id) VALUES (4, 6) ON conflict(applicant_id) DO NOTHING;\n" +
                    "INSERT INTO t_applicant (applicant_id, user_id) VALUES (5, 7) ON conflict(applicant_id) DO NOTHING;\n" +
                    "INSERT INTO t_applicant (applicant_id, user_id) VALUES (6, 8) ON conflict(applicant_id) DO NOTHING;\n" +
                    "INSERT INTO t_applicant (applicant_id, user_id) VALUES (7, 9) ON conflict(applicant_id) DO NOTHING;\n" +
                    "INSERT INTO t_applicant (applicant_id, user_id) VALUES (8, 10) ON conflict(applicant_id) DO NOTHING;\n" +
                    "INSERT INTO t_applicant (applicant_id, user_id) VALUES (9, 11) ON conflict(applicant_id) DO NOTHING;\n" +
                    "INSERT INTO t_applicant (applicant_id, user_id) VALUES (10, 12) ON conflict(applicant_id) DO NOTHING;\n" +
                    "ALTER SEQUENCE t_applicant_seq RESTART WITH 11;" +
                    "INSERT INTO t_interviewer (interviewer_id, interview_num, interviewer_age, interviewer_department, interviewer_name, interviewer_position, department_id, user_id) VALUES (1, 0, 25, 'HUE Development', 'Interviewer_1', 'Java Developer', 1, 13) ON conflict(interviewer_id) DO NOTHING;\n" +
                    "INSERT INTO t_interviewer (interviewer_id, interview_num, interviewer_age, interviewer_department, interviewer_name, interviewer_position, department_id, user_id) VALUES (2, 0, 25, 'HUE Development', 'Interviewer_2', 'Java Developer', 1, 14) ON conflict(interviewer_id) DO NOTHING;\n" +
                    "INSERT INTO t_interviewer (interviewer_id, interview_num, interviewer_age, interviewer_department, interviewer_name, interviewer_position, department_id, user_id) VALUES (3, 0, 25, 'HUE Development', 'Interviewer_3', 'Java Developer', 1, 15) ON conflict(interviewer_id) DO NOTHING;\n" +
                    "INSERT INTO t_interviewer (interviewer_id, interview_num, interviewer_age, interviewer_department, interviewer_name, interviewer_position, department_id, user_id) VALUES (4, 0, 25, 'HUE Development', 'Interviewer_4', 'Java Developer', 1, 16) ON conflict(interviewer_id) DO NOTHING;\n" +
                    "INSERT INTO t_interviewer (interviewer_id, interview_num, interviewer_age, interviewer_department, interviewer_name, interviewer_position, department_id, user_id) VALUES (5, 0, 25, 'HUE Development', 'Interviewer_5', 'Java Developer', 1, 17) ON conflict(interviewer_id) DO NOTHING;\n" +
                    "ALTER SEQUENCE t_interviewer_seq RESTART WITH 6;" +
                    "INSERT INTO t_post (post_id, interview_round, post_description, post_locale, post_name, post_requirement, post_type, publish_time, recruit_num, recruit_type, status, batch_id, department_id) VALUES (1, 1, 'Java Developer', 'Shanghai', 'Java Developer', 'Java', 'R&D', '2018-09-01', 30, 0, 0, 1, 1) ON conflict(post_id) DO NOTHING;\n" +
                    "ALTER SEQUENCE t_post_seq RESTART WITH 2;" +
                    "INSERT INTO t_resume_model VALUES (1, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 1);" +
                    "ALTER SEQUENCE t_resume_model_seq RESTART WITH 2;" +
                    "INSERT INTO t_application (application_id, create_time, status, applicant_id, post_id) VALUES (1, '2018-09-02', 1, 1, 1) ON conflict(application_id) DO NOTHING;\n" +
                    "INSERT INTO t_application (application_id, create_time, status, applicant_id, post_id) VALUES (2, '2018-09-02', 1, 2, 1) ON conflict(application_id) DO NOTHING;\n" +
                    "INSERT INTO t_application (application_id, create_time, status, applicant_id, post_id) VALUES (3, '2018-09-02', 1, 3, 1) ON conflict(application_id) DO NOTHING;\n" +
                    "INSERT INTO t_application (application_id, create_time, status, applicant_id, post_id) VALUES (4, '2018-09-02', 1, 4, 1) ON conflict(application_id) DO NOTHING;\n" +
                    "INSERT INTO t_application (application_id, create_time, status, applicant_id, post_id) VALUES (5, '2018-09-02', 1, 5, 1) ON conflict(application_id) DO NOTHING;\n" +
                    "INSERT INTO t_application (application_id, create_time, status, applicant_id, post_id) VALUES (6, '2018-09-02', 1, 6, 1) ON conflict(application_id) DO NOTHING;\n" +
                    "INSERT INTO t_application (application_id, create_time, status, applicant_id, post_id) VALUES (7, '2018-09-02', 1, 7, 1) ON conflict(application_id) DO NOTHING;\n" +
                    "INSERT INTO t_application (application_id, create_time, status, applicant_id, post_id) VALUES (8, '2018-09-02', 1, 8, 1) ON conflict(application_id) DO NOTHING;\n" +
                    "INSERT INTO t_application (application_id, create_time, status, applicant_id, post_id) VALUES (9, '2018-09-02', 1, 9, 1) ON conflict(application_id) DO NOTHING;\n" +
                    "INSERT INTO t_application (application_id, create_time, status, applicant_id, post_id) VALUES (10, '2018-09-02', 1, 10, 1) ON conflict(application_id) DO NOTHING;\n" +
                    "ALTER SEQUENCE t_application_seq RESTART WITH 11;" +
                    "INSERT INTO t_resume (resume_id, applicant_name, applicant_age, applicant_current_salary, applicant_degree, applicant_expect_salary, applicant_sex, applicant_id) VALUES (1, 'applicant_1', 22, 0, 1, 10000, 0, 1) ON conflict(resume_id) DO NOTHING;\n" +
                    "INSERT INTO t_resume (resume_id, applicant_name, applicant_age, applicant_current_salary, applicant_degree, applicant_expect_salary, applicant_sex, applicant_id) VALUES (2, 'applicant_2', 22, 0, 1, 10000, 0, 2) ON conflict(resume_id) DO NOTHING;\n" +
                    "INSERT INTO t_resume (resume_id, applicant_name, applicant_age, applicant_current_salary, applicant_degree, applicant_expect_salary, applicant_sex, applicant_id) VALUES (3, 'applicant_3', 22, 0, 1, 10000, 0, 3) ON conflict(resume_id) DO NOTHING;\n" +
                    "INSERT INTO t_resume (resume_id, applicant_name, applicant_age, applicant_current_salary, applicant_degree, applicant_expect_salary, applicant_sex, applicant_id) VALUES (4, 'applicant_4', 22, 0, 1, 10000, 0, 4) ON conflict(resume_id) DO NOTHING;\n" +
                    "INSERT INTO t_resume (resume_id, applicant_name, applicant_age, applicant_current_salary, applicant_degree, applicant_expect_salary, applicant_sex, applicant_id) VALUES (5, 'applicant_5', 22, 0, 1, 10000, 0, 5) ON conflict(resume_id) DO NOTHING;\n" +
                    "INSERT INTO t_resume (resume_id, applicant_name, applicant_age, applicant_current_salary, applicant_degree, applicant_expect_salary, applicant_sex, applicant_id) VALUES (6, 'applicant_6', 22, 0, 1, 10000, 1, 6) ON conflict(resume_id) DO NOTHING;\n" +
                    "INSERT INTO t_resume (resume_id, applicant_name, applicant_age, applicant_current_salary, applicant_degree, applicant_expect_salary, applicant_sex, applicant_id) VALUES (7, 'applicant_7', 22, 0, 1, 10000, 1, 7) ON conflict(resume_id) DO NOTHING;\n" +
                    "INSERT INTO t_resume (resume_id, applicant_name, applicant_age, applicant_current_salary, applicant_degree, applicant_expect_salary, applicant_sex, applicant_id) VALUES (8, 'applicant_8', 22, 0, 1, 10000, 1, 8) ON conflict(resume_id) DO NOTHING;\n" +
                    "INSERT INTO t_resume (resume_id, applicant_name, applicant_age, applicant_current_salary, applicant_degree, applicant_expect_salary, applicant_sex, applicant_id) VALUES (9, 'applicant_9', 22, 0, 1, 10000, 1, 9) ON conflict(resume_id) DO NOTHING;\n" +
                    "INSERT INTO t_resume (resume_id, applicant_name, applicant_age, applicant_current_salary, applicant_degree, applicant_expect_salary, applicant_sex, applicant_id) VALUES (10, 'applicant_10', 22, 0, 1, 10000, 1, 10) ON conflict(resume_id) DO NOTHING;" +
                    "ALTER SEQUENCE t_resume_seq RESTART WITH 11;";

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Integer> batch_id = template.query(SELECT_SQL, ps -> {}, (rs, rowNum) -> rs.getInt(1));
        if(batch_id.size() == 0) {
            template.update(INSERT_SQL, ps -> {});
        }
    }
}
