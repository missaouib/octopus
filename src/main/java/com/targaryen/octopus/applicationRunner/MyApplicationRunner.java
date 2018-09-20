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
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (1, 2, 'Tsinghua University', 0, 11, 7, 5, 3, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (2, 2, 'Fudan University', 0, 13, 11, 6, 3, 1);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (3, 2, 'Peking University', 0, 24, 18, 14, 10, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (4, 2, 'University of Electronic Science and Technology of China', 0, 9, 6, 5, 3, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (5, 2, 'East China Normal University', 0, 10, 5, 3, 2, 1);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (6, 2, 'Shanghai Jiao Tong University', 0, 15, 12, 9, 6, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (7, 2, 'Southeast University', 0, 16, 14, 10, 4, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (8, 2, 'Nanjing University of Science and Technology', 0, 13, 10, 7, 3, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (9, 2, 'Tongji University', 0, 16, 12, 5, 3, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (10, 2, 'Nanjing University', 0, 19, 16, 13, 6, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (11, 2, 'University of Chinese Academy of Sciences', 0, 14, 11, 10, 9, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (12, 2, 'Beihang University', 0, 16, 10, 6, 3, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (13, 2, 'Beijing', 1, 21, 18, 13, 8, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (14, 2, 'Guangzhou', 1, 22, 15, 9, 6, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (15, 2, 'Shenzhen', 1, 15, 11, 7, 3, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (16, 2, 'Chengdu', 1, 22, 16, 12, 8, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (17, 2, 'Hangzhou', 1, 22, 19, 13, 8, 6);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (18, 2, 'Shanghai', 1, 28, 20, 17, 12, 5);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (19, 2, 'Chongqing', 1, 14, 9, 4, 2, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (20, 2, 'Nanjing', 1, 32, 24, 18, 8, 5);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (21, 2, 'Algorithm Researcher', 2, 23, 19, 17, 12, 6);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (22, 2, 'Java Developer', 2, 18, 10, 6, 3, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (23, 2, 'Testing Engineer', 2, 20, 19, 12, 8, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (24, 2, 'Machine Learning Researcher', 2, 25, 19, 15, 9, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (25, 2, 'IT Support', 2, 24, 18, 12, 6, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (26, 2, 'Human Resource', 2, 19, 14, 9, 6, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (27, 2, 'Marketing', 2, 25, 20, 15, 8, 5);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (28, 2, 'C++ Developer', 2, 22, 13, 7, 3, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (29, 3, 'Nanjing University of Science and Technology', 0, 9, 9, 9, 7, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (30, 3, 'Shanghai Jiao Tong University', 0, 4, 2, 1, 1, 0);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (31, 3, 'Southeast University', 0, 16, 13, 6, 4, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (32, 3, 'Peking University', 0, 13, 9, 7, 6, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (33, 3, 'Nanjing University', 0, 12, 10, 5, 3, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (34, 3, 'University of Chinese Academy of Sciences', 0, 15, 14, 7, 5, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (35, 3, 'Beihang University', 0, 6, 4, 2, 2, 1);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (36, 3, 'East China Normal University', 0, 10, 9, 8, 4, 0);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (37, 3, 'Tsinghua University', 0, 10, 9, 7, 5, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (38, 3, 'University of Electronic Science and Technology of China', 0, 14, 10, 9, 5, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (39, 3, 'Fudan University', 0, 14, 14, 9, 7, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (40, 3, 'Tongji University', 0, 15, 9, 7, 5, 1);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (41, 3, 'Shanghai', 1, 17, 12, 8, 3, 1);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (42, 3, 'Shenzhen', 1, 12, 10, 6, 5, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (43, 3, 'Guangzhou', 1, 21, 17, 11, 9, 5);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (44, 3, 'Beijing', 1, 23, 20, 12, 9, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (45, 3, 'Chongqing', 1, 20, 16, 14, 10, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (46, 3, 'Nanjing', 1, 12, 9, 6, 5, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (47, 3, 'Chengdu', 1, 15, 11, 9, 6, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (48, 3, 'Hangzhou', 1, 18, 17, 11, 7, 5);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (49, 3, 'Machine Learning Researcher', 2, 13, 11, 7, 4, 0);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (50, 3, 'IT Support', 2, 16, 14, 10, 5, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (51, 3, 'Algorithm Researcher', 2, 29, 23, 18, 12, 7);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (52, 3, 'Human Resource', 2, 20, 17, 12, 9, 7);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (53, 3, 'C++ Developer', 2, 16, 14, 7, 5, 0);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (54, 3, 'Marketing', 2, 11, 9, 5, 4, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (55, 3, 'Testing Engineer', 2, 23, 18, 12, 9, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (56, 3, 'Java Developer', 2, 10, 6, 6, 6, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (57, 4, 'University of Electronic Science and Technology of China', 0, 7, 6, 6, 3, 1);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (58, 4, 'Nanjing University', 0, 20, 16, 9, 4, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (59, 4, 'Peking University', 0, 16, 15, 12, 9, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (60, 4, 'Shanghai Jiao Tong University', 0, 27, 20, 14, 12, 6);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (61, 4, 'Tsinghua University', 0, 12, 9, 6, 4, 1);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (62, 4, 'Beihang University', 0, 13, 11, 8, 5, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (63, 4, 'University of Chinese Academy of Sciences', 0, 16, 14, 11, 10, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (64, 4, 'Nanjing University of Science and Technology', 0, 22, 20, 14, 11, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (65, 4, 'Tongji University', 0, 15, 12, 7, 2, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (66, 4, 'Southeast University', 0, 14, 11, 10, 6, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (67, 4, 'East China Normal University', 0, 14, 11, 9, 6, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (68, 4, 'Fudan University', 0, 14, 11, 11, 7, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (69, 4, 'Shenzhen', 1, 25, 20, 14, 10, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (70, 4, 'Nanjing', 1, 24, 19, 11, 6, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (71, 4, 'Hangzhou', 1, 28, 25, 18, 16, 10);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (72, 4, 'Shanghai', 1, 27, 24, 21, 12, 5);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (73, 4, 'Chongqing', 1, 22, 16, 12, 8, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (74, 4, 'Beijing', 1, 19, 16, 11, 6, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (75, 4, 'Chengdu', 1, 15, 12, 10, 7, 1);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (76, 4, 'Guangzhou', 1, 30, 24, 20, 14, 6);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (77, 4, 'Algorithm Researcher', 2, 30, 23, 20, 12, 6);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (78, 4, 'Machine Learning Researcher', 2, 16, 13, 8, 6, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (79, 4, 'Testing Engineer', 2, 18, 13, 12, 5, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (80, 4, 'Java Developer', 2, 13, 12, 4, 2, 1);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (81, 4, 'IT Support', 2, 30, 27, 22, 17, 10);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (82, 4, 'Marketing', 2, 36, 29, 21, 18, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (83, 4, 'Human Resource', 2, 23, 21, 17, 10, 7);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (84, 4, 'C++ Developer', 2, 24, 18, 13, 9, 5);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (85, 5, 'Tsinghua University', 0, 14, 10, 9, 5, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (86, 5, 'Fudan University', 0, 21, 18, 14, 12, 5);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (87, 5, 'Nanjing University', 0, 7, 5, 2, 1, 1);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (88, 5, 'Shanghai Jiao Tong University', 0, 10, 8, 7, 5, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (89, 5, 'Peking University', 0, 22, 17, 12, 8, 6);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (90, 5, 'University of Chinese Academy of Sciences', 0, 18, 12, 6, 4, 0);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (91, 5, 'Tongji University', 0, 15, 14, 12, 9, 8);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (92, 5, 'Beihang University', 0, 16, 13, 10, 6, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (93, 5, 'Southeast University', 0, 14, 10, 8, 3, 1);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (94, 5, 'East China Normal University', 0, 14, 10, 8, 4, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (95, 5, 'Nanjing University of Science and Technology', 0, 11, 10, 8, 4, 1);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (96, 5, 'University of Electronic Science and Technology of China', 0, 15, 14, 13, 9, 6);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (97, 5, 'Guangzhou', 1, 29, 24, 13, 7, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (98, 5, 'Beijing', 1, 21, 20, 17, 12, 6);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (99, 5, 'Shenzhen', 1, 17, 11, 9, 6, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (100, 5, 'Chongqing', 1, 31, 23, 20, 14, 8);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (101, 5, 'Nanjing', 1, 22, 20, 16, 10, 5);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (102, 5, 'Shanghai', 1, 17, 13, 12, 10, 8);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (103, 5, 'Chengdu', 1, 21, 18, 13, 8, 6);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (104, 5, 'Hangzhou', 1, 19, 12, 9, 3, 1);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (105, 5, 'C++ Developer', 2, 14, 9, 8, 6, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (106, 5, 'Marketing', 2, 24, 23, 17, 6, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (107, 5, 'Human Resource', 2, 23, 16, 15, 10, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (108, 5, 'Java Developer', 2, 21, 16, 14, 11, 6);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (109, 5, 'IT Support', 2, 23, 20, 16, 10, 6);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (110, 5, 'Testing Engineer', 2, 18, 15, 14, 12, 9);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (111, 5, 'Machine Learning Researcher', 2, 33, 25, 16, 10, 7);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (112, 5, 'Algorithm Researcher', 2, 21, 17, 9, 5, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (113, 6, 'Shanghai Jiao Tong University', 0, 9, 6, 5, 4, 1);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (114, 6, 'Beihang University', 0, 13, 10, 5, 4, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (115, 6, 'University of Chinese Academy of Sciences', 0, 18, 14, 10, 6, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (116, 6, 'Tsinghua University', 0, 14, 14, 11, 7, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (117, 6, 'Southeast University', 0, 12, 7, 5, 3, 1);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (118, 6, 'Peking University', 0, 14, 12, 9, 9, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (119, 6, 'Nanjing University of Science and Technology', 0, 7, 5, 4, 2, 1);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (120, 6, 'Nanjing University', 0, 16, 13, 10, 5, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (121, 6, 'Fudan University', 0, 8, 5, 0, 0, 0);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (122, 6, 'University of Electronic Science and Technology of China', 0, 11, 11, 6, 4, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (123, 6, 'Tongji University', 0, 12, 11, 10, 7, 1);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (124, 6, 'East China Normal University', 0, 8, 7, 5, 3, 1);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (125, 6, 'Nanjing', 1, 19, 13, 9, 4, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (126, 6, 'Beijing', 1, 20, 16, 12, 6, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (127, 6, 'Hangzhou', 1, 17, 15, 13, 9, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (128, 6, 'Chengdu', 1, 15, 12, 6, 5, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (129, 6, 'Guangzhou', 1, 9, 7, 3, 1, 1);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (130, 6, 'Shenzhen', 1, 20, 17, 11, 8, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (131, 6, 'Chongqing', 1, 21, 17, 13, 10, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (132, 6, 'Shanghai', 1, 21, 18, 13, 11, 6);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (133, 6, 'Algorithm Researcher', 2, 15, 12, 10, 7, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (134, 6, 'Machine Learning Researcher', 2, 16, 13, 8, 5, 3);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (135, 6, 'Human Resource', 2, 22, 15, 10, 8, 2);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (136, 6, 'Testing Engineer', 2, 14, 11, 4, 3, 0);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (137, 6, 'C++ Developer', 2, 18, 15, 12, 9, 6);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (138, 6, 'IT Support', 2, 23, 22, 15, 8, 5);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (139, 6, 'Java Developer', 2, 19, 16, 11, 9, 4);\n" +
                    "INSERT INTO t_source_file(source_file_id, batch_id, source_name, source_type,application_num, filter_pass_num, interview_pass_num, offer_num, entry_num) VALUES (140, 6, 'Marketing', 2, 15, 11, 10, 5, 2);\n" +
                    "ALTER SEQUENCE t_source_file_seq RESTART WITH 141;\n" +
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
