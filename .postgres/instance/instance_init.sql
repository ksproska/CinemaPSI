CREATE TABLE HALLS
(
    HALL_ID      BIGINT NOT NULL,
    HALL_NUMBER  INT    NOT NULL,
    ROWS_NUM     INT    NOT NULL,
    SEATS_IN_ROW INT    NOT NULL,
    CINEMA_ID    BIGINT NOT NULL
);
ALTER TABLE HALLS
    ADD CONSTRAINT HALLS_PK PRIMARY KEY (HALL_ID);
INSERT INTO HALLS
VALUES (1, 1, 10, 12, 1)
     , (2, 2, 8, 8, 1)
     , (3, 3, 12, 20, 1)
     , (4, 1, 12, 16, 2)
     , (5, 2, 8, 10, 2)
     , (6, 3, 10, 12, 2)
     , (7, 1, 12, 14, 3)
     , (8, 2, 8, 10, 3)
     , (9, 3, 14, 18, 3);

CREATE TABLE REPERTOIRE
(
    REPERTOIRE_ID    SERIAL PRIMARY KEY,
    STARTING         TIMESTAMP NOT NULL,
    ENDING           TIMESTAMP NOT NULL,
    HALL_ID          BIGINT    NOT NULL,
    MOVIE_VERSION_ID BIGINT    NOT NULL
);
-- DROP TABLE REPERTOIRE;

-- ALTER TABLE common.REPERTOIRE
--     ALTER COLUMN REPERTOIRE_ID BIGINT AUTO_INCREMENT;
-- ALTER TABLE common.REPERTOIRE
--     ADD CONSTRAINT REPERTOIRE_PK PRIMARY KEY (REPERTOIRE_ID);
INSERT INTO REPERTOIRE (STARTING, ENDING, HALL_ID, MOVIE_VERSION_ID)
VALUES ('2024-01-14 18:30:00', '2024-01-14 21:29:00', 8, 1)
     , ('2024-01-19 20:00:00', '2024-01-19 22:33:00', 4, 3)
     , ('2024-01-23 14:00:00', '2024-01-23 15:36:00', 8, 2)
     , ('2024-01-26 20:00:00', '2024-01-26 22:31:00', 4, 3)
     , ('2024-01-24 18:30:00', '2024-01-24 21:01:00', 3, 3)
     , ('2024-01-09 18:00:00', '2024-01-09 19:32:00', 4, 2)
     , ('2024-01-02 16:30:00', '2024-01-02 18:17:00', 1, 2)
     , ('2024-01-29 19:30:00', '2024-01-29 22:03:00', 3, 3)
     , ('2024-01-06 19:00:00', '2024-01-06 21:39:00', 8, 1)
     , ('2024-01-06 16:00:00', '2024-01-06 19:00:00', 7, 1)
     , ('2024-01-02 20:30:00', '2024-01-02 23:07:00', 7, 1)
     , ('2024-01-19 20:00:00', '2024-01-19 22:36:00', 5, 1)
     , ('2024-01-02 19:00:00', '2024-01-02 20:31:00', 1, 3)
     , ('2024-01-22 21:00:00', '2024-01-22 23:38:00', 1, 1)
     , ('2024-01-08 19:00:00', '2024-01-08 20:55:00', 8, 1)
     , ('2024-01-26 16:30:00', '2024-01-26 18:30:00', 2, 3)
     , ('2024-01-23 16:30:00', '2024-01-23 18:01:00', 1, 2)
     , ('2024-01-10 12:30:00', '2024-01-10 14:24:00', 5, 1)
     , ('2024-01-21 19:00:00', '2024-01-21 20:35:00', 8, 2)
     , ('2024-01-11 18:30:00', '2024-01-11 21:06:00', 6, 1)
     , ('2024-01-08 21:00:00', '2024-01-08 22:46:00', 3, 2)
     , ('2024-01-16 17:00:00', '2024-01-16 18:59:00', 5, 1)
     , ('2024-01-23 17:00:00', '2024-01-23 19:31:00', 3, 3)
     , ('2024-01-20 21:00:00', '2024-01-20 23:59:00', 9, 1)
     , ('2024-01-17 13:30:00', '2024-01-17 16:25:00', 2, 1)
     , ('2024-01-18 13:30:00', '2024-01-18 15:25:00', 5, 1)
     , ('2024-01-14 15:00:00', '2024-01-14 16:56:00', 3, 2)
     , ('2023-12-31 14:30:00', '2023-12-31 16:30:00', 2, 3)
     , ('2024-01-04 14:30:00', '2024-01-04 17:30:00', 5, 1)
     , ('2024-01-04 16:30:00', '2024-01-04 18:29:00', 9, 1)
     , ('2024-01-08 13:30:00', '2024-01-08 15:19:00', 3, 2)
     , ('2023-12-31 19:30:00', '2023-12-31 22:00:00', 4, 3)
     , ('2024-01-05 13:30:00', '2024-01-05 16:07:00', 7, 1)
     , ('2024-01-28 12:30:00', '2024-01-28 14:26:00', 1, 1)
     , ('2024-01-01 15:30:00', '2024-01-01 18:06:00', 2, 1)
     , ('2024-01-23 16:00:00', '2024-01-23 17:59:00', 3, 1)
     , ('2024-01-10 20:00:00', '2024-01-10 21:32:00', 1, 2)
     , ('2024-01-23 17:30:00', '2024-01-23 20:08:00', 7, 1)
     , ('2024-01-29 15:00:00', '2024-01-29 16:36:00', 9, 2)
     , ('2024-01-14 15:30:00', '2024-01-14 18:05:00', 6, 1)
     , ('2024-01-05 16:00:00', '2024-01-05 18:00:00', 3, 3)
     , ('2024-01-02 16:00:00', '2024-01-02 17:59:00', 7, 1)
     , ('2024-01-28 21:00:00', '2024-01-28 22:57:00', 7, 1)
     , ('2023-12-29 14:00:00', '2023-12-29 15:58:00', 2, 2)
     , ('2024-01-27 18:30:00', '2024-01-27 20:27:00', 6, 2)
     , ('2024-01-29 18:00:00', '2024-01-29 19:59:00', 5, 1)
     , ('2024-01-20 15:30:00', '2024-01-20 18:04:00', 4, 3)
     , ('2024-01-23 21:00:00', '2024-01-23 22:54:00', 3, 1)
     , ('2024-01-28 14:00:00', '2024-01-28 16:57:00', 4, 1)
     , ('2024-01-07 14:30:00', '2024-01-07 17:04:00', 9, 3);

CREATE TABLE SEATS
(
    SEAT_ID BIGINT NOT NULL,
    ROW_NUM INT    NOT NULL,
    NUM     INT    NOT NULL,
    HALL_ID BIGINT NOT NULL
);
ALTER TABLE SEATS
    ADD CONSTRAINT SEATS_PK PRIMARY KEY (SEAT_ID);
INSERT INTO SEATS
VALUES (1, 1, 1, 1)
     , (2, 1, 2, 1)
     , (3, 1, 3, 1)
     , (4, 1, 4, 1)
     , (5, 1, 5, 1)
     , (6, 1, 6, 1)
     , (7, 1, 7, 1)
     , (8, 1, 8, 1)
     , (9, 1, 9, 1)
     , (10, 1, 10, 1)
     , (11, 1, 11, 1)
     , (12, 2, 1, 1)
     , (13, 2, 2, 1)
     , (14, 2, 3, 1)
     , (15, 2, 4, 1)
     , (16, 2, 5, 1)
     , (17, 2, 6, 1)
     , (18, 2, 7, 1)
     , (19, 2, 8, 1)
     , (20, 2, 9, 1)
     , (21, 2, 10, 1)
     , (22, 2, 11, 1)
     , (23, 3, 1, 1)
     , (24, 3, 2, 1)
     , (25, 3, 3, 1)
     , (26, 3, 4, 1)
     , (27, 3, 5, 1)
     , (28, 3, 6, 1)
     , (29, 3, 7, 1)
     , (30, 3, 8, 1)
     , (31, 3, 9, 1)
     , (32, 3, 10, 1)
     , (33, 3, 11, 1)
     , (34, 4, 1, 1)
     , (35, 4, 2, 1)
     , (36, 4, 3, 1)
     , (37, 4, 4, 1)
     , (38, 4, 5, 1)
     , (39, 4, 6, 1)
     , (40, 4, 7, 1)
     , (41, 4, 8, 1)
     , (42, 4, 9, 1)
     , (43, 4, 10, 1)
     , (44, 4, 11, 1)
     , (45, 5, 1, 1)
     , (46, 5, 2, 1)
     , (47, 5, 3, 1)
     , (48, 5, 4, 1)
     , (49, 5, 5, 1)
     , (50, 5, 6, 1)
     , (51, 5, 7, 1)
     , (52, 5, 8, 1)
     , (53, 5, 9, 1)
     , (54, 5, 10, 1)
     , (55, 5, 11, 1)
     , (56, 6, 1, 1)
     , (57, 6, 2, 1)
     , (58, 6, 3, 1)
     , (59, 6, 4, 1)
     , (60, 6, 5, 1)
     , (61, 6, 6, 1)
     , (62, 6, 7, 1)
     , (63, 6, 8, 1)
     , (64, 6, 9, 1)
     , (65, 6, 10, 1)
     , (66, 6, 11, 1)
     , (67, 7, 1, 1)
     , (68, 7, 2, 1)
     , (69, 7, 3, 1)
     , (70, 7, 4, 1)
     , (71, 7, 5, 1)
     , (72, 7, 6, 1)
     , (73, 7, 7, 1)
     , (74, 7, 8, 1)
     , (75, 7, 9, 1)
     , (76, 7, 10, 1)
     , (77, 7, 11, 1)
     , (78, 8, 1, 1)
     , (79, 8, 2, 1)
     , (80, 8, 3, 1)
     , (81, 8, 4, 1)
     , (82, 8, 5, 1)
     , (83, 8, 6, 1)
     , (84, 8, 7, 1)
     , (85, 8, 8, 1)
     , (86, 8, 9, 1)
     , (87, 8, 10, 1)
     , (88, 8, 11, 1)
     , (89, 9, 1, 1)
     , (90, 9, 2, 1)
     , (91, 9, 3, 1)
     , (92, 9, 4, 1)
     , (93, 9, 5, 1)
     , (94, 9, 6, 1)
     , (95, 9, 7, 1)
     , (96, 9, 8, 1)
     , (97, 9, 9, 1)
     , (98, 9, 10, 1)
     , (99, 9, 11, 1)
     , (100, 10, 1, 1)
     , (101, 10, 2, 1)
     , (102, 10, 3, 1)
     , (103, 10, 4, 1)
     , (104, 10, 5, 1)
     , (105, 10, 6, 1)
     , (106, 10, 7, 1)
     , (107, 10, 8, 1)
     , (108, 10, 9, 1)
     , (109, 10, 10, 1)
     , (110, 10, 11, 1)
     , (111, 1, 1, 2)
     , (112, 1, 2, 2)
     , (113, 1, 3, 2)
     , (114, 1, 4, 2)
     , (115, 1, 5, 2)
     , (116, 1, 6, 2)
     , (117, 1, 7, 2)
     , (118, 2, 1, 2)
     , (119, 2, 2, 2)
     , (120, 2, 3, 2)
     , (121, 2, 4, 2)
     , (122, 2, 5, 2)
     , (123, 2, 6, 2)
     , (124, 2, 7, 2)
     , (125, 3, 1, 2)
     , (126, 3, 2, 2)
     , (127, 3, 3, 2)
     , (128, 3, 4, 2)
     , (129, 3, 5, 2)
     , (130, 3, 6, 2)
     , (131, 3, 7, 2)
     , (132, 4, 1, 2)
     , (133, 4, 2, 2)
     , (134, 4, 3, 2)
     , (135, 4, 4, 2)
     , (136, 4, 5, 2)
     , (137, 4, 6, 2)
     , (138, 4, 7, 2)
     , (139, 5, 1, 2)
     , (140, 5, 2, 2)
     , (141, 5, 3, 2)
     , (142, 5, 4, 2)
     , (143, 5, 5, 2)
     , (144, 5, 6, 2)
     , (145, 5, 7, 2)
     , (146, 6, 1, 2)
     , (147, 6, 2, 2)
     , (148, 6, 3, 2)
     , (149, 6, 4, 2)
     , (150, 6, 5, 2)
     , (151, 6, 6, 2)
     , (152, 6, 7, 2)
     , (153, 7, 1, 2)
     , (154, 7, 2, 2)
     , (155, 7, 3, 2)
     , (156, 7, 4, 2)
     , (157, 7, 5, 2)
     , (158, 7, 6, 2)
     , (159, 7, 7, 2)
     , (160, 8, 1, 2)
     , (161, 8, 2, 2)
     , (162, 8, 3, 2)
     , (163, 8, 4, 2)
     , (164, 8, 5, 2)
     , (165, 8, 6, 2)
     , (166, 8, 7, 2)
     , (167, 1, 1, 3)
     , (168, 1, 2, 3)
     , (169, 1, 3, 3)
     , (170, 1, 4, 3)
     , (171, 1, 5, 3)
     , (172, 1, 6, 3)
     , (173, 1, 7, 3)
     , (174, 1, 8, 3)
     , (175, 1, 9, 3)
     , (176, 1, 10, 3)
     , (177, 1, 11, 3)
     , (178, 1, 12, 3)
     , (179, 1, 13, 3)
     , (180, 1, 14, 3)
     , (181, 1, 15, 3)
     , (182, 1, 16, 3)
     , (183, 1, 17, 3)
     , (184, 1, 18, 3)
     , (185, 1, 19, 3)
     , (186, 2, 1, 3)
     , (187, 2, 2, 3)
     , (188, 2, 3, 3)
     , (189, 2, 4, 3)
     , (190, 2, 5, 3)
     , (191, 2, 6, 3)
     , (192, 2, 7, 3)
     , (193, 2, 8, 3)
     , (194, 2, 9, 3)
     , (195, 2, 10, 3)
     , (196, 2, 11, 3)
     , (197, 2, 12, 3)
     , (198, 2, 13, 3)
     , (199, 2, 14, 3)
     , (200, 2, 15, 3)
     , (201, 2, 16, 3)
     , (202, 2, 17, 3)
     , (203, 2, 18, 3)
     , (204, 2, 19, 3)
     , (205, 3, 1, 3)
     , (206, 3, 2, 3)
     , (207, 3, 3, 3)
     , (208, 3, 4, 3)
     , (209, 3, 5, 3)
     , (210, 3, 6, 3)
     , (211, 3, 7, 3)
     , (212, 3, 8, 3)
     , (213, 3, 9, 3)
     , (214, 3, 10, 3)
     , (215, 3, 11, 3)
     , (216, 3, 12, 3)
     , (217, 3, 13, 3)
     , (218, 3, 14, 3)
     , (219, 3, 15, 3)
     , (220, 3, 16, 3)
     , (221, 3, 17, 3)
     , (222, 3, 18, 3)
     , (223, 3, 19, 3)
     , (224, 4, 1, 3)
     , (225, 4, 2, 3)
     , (226, 4, 3, 3)
     , (227, 4, 4, 3)
     , (228, 4, 5, 3)
     , (229, 4, 6, 3)
     , (230, 4, 7, 3)
     , (231, 4, 8, 3)
     , (232, 4, 9, 3)
     , (233, 4, 10, 3)
     , (234, 4, 11, 3)
     , (235, 4, 12, 3)
     , (236, 4, 13, 3)
     , (237, 4, 14, 3)
     , (238, 4, 15, 3)
     , (239, 4, 16, 3)
     , (240, 4, 17, 3)
     , (241, 4, 18, 3)
     , (242, 4, 19, 3)
     , (243, 5, 1, 3)
     , (244, 5, 2, 3)
     , (245, 5, 3, 3)
     , (246, 5, 4, 3)
     , (247, 5, 5, 3)
     , (248, 5, 6, 3)
     , (249, 5, 7, 3)
     , (250, 5, 8, 3)
     , (251, 5, 9, 3)
     , (252, 5, 10, 3)
     , (253, 5, 11, 3)
     , (254, 5, 12, 3)
     , (255, 5, 13, 3)
     , (256, 5, 14, 3)
     , (257, 5, 15, 3)
     , (258, 5, 16, 3)
     , (259, 5, 17, 3)
     , (260, 5, 18, 3)
     , (261, 5, 19, 3)
     , (262, 6, 1, 3)
     , (263, 6, 2, 3)
     , (264, 6, 3, 3)
     , (265, 6, 4, 3)
     , (266, 6, 5, 3)
     , (267, 6, 6, 3)
     , (268, 6, 7, 3)
     , (269, 6, 8, 3)
     , (270, 6, 9, 3)
     , (271, 6, 10, 3)
     , (272, 6, 11, 3)
     , (273, 6, 12, 3)
     , (274, 6, 13, 3)
     , (275, 6, 14, 3)
     , (276, 6, 15, 3)
     , (277, 6, 16, 3)
     , (278, 6, 17, 3)
     , (279, 6, 18, 3)
     , (280, 6, 19, 3)
     , (281, 7, 1, 3)
     , (282, 7, 2, 3)
     , (283, 7, 3, 3)
     , (284, 7, 4, 3)
     , (285, 7, 5, 3)
     , (286, 7, 6, 3)
     , (287, 7, 7, 3)
     , (288, 7, 8, 3)
     , (289, 7, 9, 3)
     , (290, 7, 10, 3)
     , (291, 7, 11, 3)
     , (292, 7, 12, 3)
     , (293, 7, 13, 3)
     , (294, 7, 14, 3)
     , (295, 7, 15, 3)
     , (296, 7, 16, 3)
     , (297, 7, 17, 3)
     , (298, 7, 18, 3)
     , (299, 7, 19, 3)
     , (300, 8, 1, 3)
     , (301, 8, 2, 3)
     , (302, 8, 3, 3)
     , (303, 8, 4, 3)
     , (304, 8, 5, 3)
     , (305, 8, 6, 3)
     , (306, 8, 7, 3)
     , (307, 8, 8, 3)
     , (308, 8, 9, 3)
     , (309, 8, 10, 3)
     , (310, 8, 11, 3)
     , (311, 8, 12, 3)
     , (312, 8, 13, 3)
     , (313, 8, 14, 3)
     , (314, 8, 15, 3)
     , (315, 8, 16, 3)
     , (316, 8, 17, 3)
     , (317, 8, 18, 3)
     , (318, 8, 19, 3)
     , (319, 9, 1, 3)
     , (320, 9, 2, 3)
     , (321, 9, 3, 3)
     , (322, 9, 4, 3)
     , (323, 9, 5, 3)
     , (324, 9, 6, 3)
     , (325, 9, 7, 3)
     , (326, 9, 8, 3)
     , (327, 9, 9, 3)
     , (328, 9, 10, 3)
     , (329, 9, 11, 3)
     , (330, 9, 12, 3)
     , (331, 9, 13, 3)
     , (332, 9, 14, 3)
     , (333, 9, 15, 3)
     , (334, 9, 16, 3)
     , (335, 9, 17, 3)
     , (336, 9, 18, 3)
     , (337, 9, 19, 3)
     , (338, 10, 1, 3)
     , (339, 10, 2, 3)
     , (340, 10, 3, 3)
     , (341, 10, 4, 3)
     , (342, 10, 5, 3)
     , (343, 10, 6, 3)
     , (344, 10, 7, 3)
     , (345, 10, 8, 3)
     , (346, 10, 9, 3)
     , (347, 10, 10, 3)
     , (348, 10, 11, 3)
     , (349, 10, 12, 3)
     , (350, 10, 13, 3)
     , (351, 10, 14, 3)
     , (352, 10, 15, 3)
     , (353, 10, 16, 3)
     , (354, 10, 17, 3)
     , (355, 10, 18, 3)
     , (356, 10, 19, 3)
     , (357, 11, 1, 3)
     , (358, 11, 2, 3)
     , (359, 11, 3, 3)
     , (360, 11, 4, 3)
     , (361, 11, 5, 3)
     , (362, 11, 6, 3)
     , (363, 11, 7, 3)
     , (364, 11, 8, 3)
     , (365, 11, 9, 3)
     , (366, 11, 10, 3)
     , (367, 11, 11, 3)
     , (368, 11, 12, 3)
     , (369, 11, 13, 3)
     , (370, 11, 14, 3)
     , (371, 11, 15, 3)
     , (372, 11, 16, 3)
     , (373, 11, 17, 3)
     , (374, 11, 18, 3)
     , (375, 11, 19, 3)
     , (376, 12, 1, 3)
     , (377, 12, 2, 3)
     , (378, 12, 3, 3)
     , (379, 12, 4, 3)
     , (380, 12, 5, 3)
     , (381, 12, 6, 3)
     , (382, 12, 7, 3)
     , (383, 12, 8, 3)
     , (384, 12, 9, 3)
     , (385, 12, 10, 3)
     , (386, 12, 11, 3)
     , (387, 12, 12, 3)
     , (388, 12, 13, 3)
     , (389, 12, 14, 3)
     , (390, 12, 15, 3)
     , (391, 12, 16, 3)
     , (392, 12, 17, 3)
     , (393, 12, 18, 3)
     , (394, 12, 19, 3)
     , (395, 1, 1, 4)
     , (396, 1, 2, 4)
     , (397, 1, 3, 4)
     , (398, 1, 4, 4)
     , (399, 1, 5, 4)
     , (400, 1, 6, 4)
     , (401, 1, 7, 4)
     , (402, 1, 8, 4)
     , (403, 1, 9, 4)
     , (404, 1, 10, 4)
     , (405, 1, 11, 4)
     , (406, 1, 12, 4)
     , (407, 1, 13, 4)
     , (408, 1, 14, 4)
     , (409, 1, 15, 4)
     , (410, 2, 1, 4)
     , (411, 2, 2, 4)
     , (412, 2, 3, 4)
     , (413, 2, 4, 4)
     , (414, 2, 5, 4)
     , (415, 2, 6, 4)
     , (416, 2, 7, 4)
     , (417, 2, 8, 4)
     , (418, 2, 9, 4)
     , (419, 2, 10, 4)
     , (420, 2, 11, 4)
     , (421, 2, 12, 4)
     , (422, 2, 13, 4)
     , (423, 2, 14, 4)
     , (424, 2, 15, 4)
     , (425, 3, 1, 4)
     , (426, 3, 2, 4)
     , (427, 3, 3, 4)
     , (428, 3, 4, 4)
     , (429, 3, 5, 4)
     , (430, 3, 6, 4)
     , (431, 3, 7, 4)
     , (432, 3, 8, 4)
     , (433, 3, 9, 4)
     , (434, 3, 10, 4)
     , (435, 3, 11, 4)
     , (436, 3, 12, 4)
     , (437, 3, 13, 4)
     , (438, 3, 14, 4)
     , (439, 3, 15, 4)
     , (440, 4, 1, 4)
     , (441, 4, 2, 4)
     , (442, 4, 3, 4)
     , (443, 4, 4, 4)
     , (444, 4, 5, 4)
     , (445, 4, 6, 4)
     , (446, 4, 7, 4)
     , (447, 4, 8, 4)
     , (448, 4, 9, 4)
     , (449, 4, 10, 4)
     , (450, 4, 11, 4)
     , (451, 4, 12, 4)
     , (452, 4, 13, 4)
     , (453, 4, 14, 4)
     , (454, 4, 15, 4)
     , (455, 5, 1, 4)
     , (456, 5, 2, 4)
     , (457, 5, 3, 4)
     , (458, 5, 4, 4)
     , (459, 5, 5, 4)
     , (460, 5, 6, 4)
     , (461, 5, 7, 4)
     , (462, 5, 8, 4)
     , (463, 5, 9, 4)
     , (464, 5, 10, 4)
     , (465, 5, 11, 4)
     , (466, 5, 12, 4)
     , (467, 5, 13, 4)
     , (468, 5, 14, 4)
     , (469, 5, 15, 4)
     , (470, 6, 1, 4)
     , (471, 6, 2, 4)
     , (472, 6, 3, 4)
     , (473, 6, 4, 4)
     , (474, 6, 5, 4)
     , (475, 6, 6, 4)
     , (476, 6, 7, 4)
     , (477, 6, 8, 4)
     , (478, 6, 9, 4)
     , (479, 6, 10, 4)
     , (480, 6, 11, 4)
     , (481, 6, 12, 4)
     , (482, 6, 13, 4)
     , (483, 6, 14, 4)
     , (484, 6, 15, 4)
     , (485, 7, 1, 4)
     , (486, 7, 2, 4)
     , (487, 7, 3, 4)
     , (488, 7, 4, 4)
     , (489, 7, 5, 4)
     , (490, 7, 6, 4)
     , (491, 7, 7, 4)
     , (492, 7, 8, 4)
     , (493, 7, 9, 4)
     , (494, 7, 10, 4)
     , (495, 7, 11, 4)
     , (496, 7, 12, 4)
     , (497, 7, 13, 4)
     , (498, 7, 14, 4)
     , (499, 7, 15, 4)
     , (500, 8, 1, 4)
     , (501, 8, 2, 4)
     , (502, 8, 3, 4)
     , (503, 8, 4, 4)
     , (504, 8, 5, 4)
     , (505, 8, 6, 4)
     , (506, 8, 7, 4)
     , (507, 8, 8, 4)
     , (508, 8, 9, 4)
     , (509, 8, 10, 4)
     , (510, 8, 11, 4)
     , (511, 8, 12, 4)
     , (512, 8, 13, 4)
     , (513, 8, 14, 4)
     , (514, 8, 15, 4)
     , (515, 9, 1, 4)
     , (516, 9, 2, 4)
     , (517, 9, 3, 4)
     , (518, 9, 4, 4)
     , (519, 9, 5, 4)
     , (520, 9, 6, 4)
     , (521, 9, 7, 4)
     , (522, 9, 8, 4)
     , (523, 9, 9, 4)
     , (524, 9, 10, 4)
     , (525, 9, 11, 4)
     , (526, 9, 12, 4)
     , (527, 9, 13, 4)
     , (528, 9, 14, 4)
     , (529, 9, 15, 4)
     , (530, 10, 1, 4)
     , (531, 10, 2, 4)
     , (532, 10, 3, 4)
     , (533, 10, 4, 4)
     , (534, 10, 5, 4)
     , (535, 10, 6, 4)
     , (536, 10, 7, 4)
     , (537, 10, 8, 4)
     , (538, 10, 9, 4)
     , (539, 10, 10, 4)
     , (540, 10, 11, 4)
     , (541, 10, 12, 4)
     , (542, 10, 13, 4)
     , (543, 10, 14, 4)
     , (544, 10, 15, 4)
     , (545, 11, 1, 4)
     , (546, 11, 2, 4)
     , (547, 11, 3, 4)
     , (548, 11, 4, 4)
     , (549, 11, 5, 4)
     , (550, 11, 6, 4)
     , (551, 11, 7, 4)
     , (552, 11, 8, 4)
     , (553, 11, 9, 4)
     , (554, 11, 10, 4)
     , (555, 11, 11, 4)
     , (556, 11, 12, 4)
     , (557, 11, 13, 4)
     , (558, 11, 14, 4)
     , (559, 11, 15, 4)
     , (560, 12, 1, 4)
     , (561, 12, 2, 4)
     , (562, 12, 3, 4)
     , (563, 12, 4, 4)
     , (564, 12, 5, 4)
     , (565, 12, 6, 4)
     , (566, 12, 7, 4)
     , (567, 12, 8, 4)
     , (568, 12, 9, 4)
     , (569, 12, 10, 4)
     , (570, 12, 11, 4)
     , (571, 12, 12, 4)
     , (572, 12, 13, 4)
     , (573, 12, 14, 4)
     , (574, 12, 15, 4)
     , (575, 1, 1, 5)
     , (576, 1, 2, 5)
     , (577, 1, 3, 5)
     , (578, 1, 4, 5)
     , (579, 1, 5, 5)
     , (580, 1, 6, 5)
     , (581, 1, 7, 5)
     , (582, 1, 8, 5)
     , (583, 1, 9, 5)
     , (584, 2, 1, 5)
     , (585, 2, 2, 5)
     , (586, 2, 3, 5)
     , (587, 2, 4, 5)
     , (588, 2, 5, 5)
     , (589, 2, 6, 5)
     , (590, 2, 7, 5)
     , (591, 2, 8, 5)
     , (592, 2, 9, 5)
     , (593, 3, 1, 5)
     , (594, 3, 2, 5)
     , (595, 3, 3, 5)
     , (596, 3, 4, 5)
     , (597, 3, 5, 5)
     , (598, 3, 6, 5)
     , (599, 3, 7, 5)
     , (600, 3, 8, 5)
     , (601, 3, 9, 5)
     , (602, 4, 1, 5)
     , (603, 4, 2, 5)
     , (604, 4, 3, 5)
     , (605, 4, 4, 5)
     , (606, 4, 5, 5)
     , (607, 4, 6, 5)
     , (608, 4, 7, 5)
     , (609, 4, 8, 5)
     , (610, 4, 9, 5)
     , (611, 5, 1, 5)
     , (612, 5, 2, 5)
     , (613, 5, 3, 5)
     , (614, 5, 4, 5)
     , (615, 5, 5, 5)
     , (616, 5, 6, 5)
     , (617, 5, 7, 5)
     , (618, 5, 8, 5)
     , (619, 5, 9, 5)
     , (620, 6, 1, 5)
     , (621, 6, 2, 5)
     , (622, 6, 3, 5)
     , (623, 6, 4, 5)
     , (624, 6, 5, 5)
     , (625, 6, 6, 5)
     , (626, 6, 7, 5)
     , (627, 6, 8, 5)
     , (628, 6, 9, 5)
     , (629, 7, 1, 5)
     , (630, 7, 2, 5)
     , (631, 7, 3, 5)
     , (632, 7, 4, 5)
     , (633, 7, 5, 5)
     , (634, 7, 6, 5)
     , (635, 7, 7, 5)
     , (636, 7, 8, 5)
     , (637, 7, 9, 5)
     , (638, 8, 1, 5)
     , (639, 8, 2, 5)
     , (640, 8, 3, 5)
     , (641, 8, 4, 5)
     , (642, 8, 5, 5)
     , (643, 8, 6, 5)
     , (644, 8, 7, 5)
     , (645, 8, 8, 5)
     , (646, 8, 9, 5)
     , (647, 1, 1, 6)
     , (648, 1, 2, 6)
     , (649, 1, 3, 6)
     , (650, 1, 4, 6)
     , (651, 1, 5, 6)
     , (652, 1, 6, 6)
     , (653, 1, 7, 6)
     , (654, 1, 8, 6)
     , (655, 1, 9, 6)
     , (656, 1, 10, 6)
     , (657, 1, 11, 6)
     , (658, 2, 1, 6)
     , (659, 2, 2, 6)
     , (660, 2, 3, 6)
     , (661, 2, 4, 6)
     , (662, 2, 5, 6)
     , (663, 2, 6, 6)
     , (664, 2, 7, 6)
     , (665, 2, 8, 6)
     , (666, 2, 9, 6)
     , (667, 2, 10, 6)
     , (668, 2, 11, 6)
     , (669, 3, 1, 6)
     , (670, 3, 2, 6)
     , (671, 3, 3, 6)
     , (672, 3, 4, 6)
     , (673, 3, 5, 6)
     , (674, 3, 6, 6)
     , (675, 3, 7, 6)
     , (676, 3, 8, 6)
     , (677, 3, 9, 6)
     , (678, 3, 10, 6)
     , (679, 3, 11, 6)
     , (680, 4, 1, 6)
     , (681, 4, 2, 6)
     , (682, 4, 3, 6)
     , (683, 4, 4, 6)
     , (684, 4, 5, 6)
     , (685, 4, 6, 6)
     , (686, 4, 7, 6)
     , (687, 4, 8, 6)
     , (688, 4, 9, 6)
     , (689, 4, 10, 6)
     , (690, 4, 11, 6)
     , (691, 5, 1, 6)
     , (692, 5, 2, 6)
     , (693, 5, 3, 6)
     , (694, 5, 4, 6)
     , (695, 5, 5, 6)
     , (696, 5, 6, 6)
     , (697, 5, 7, 6)
     , (698, 5, 8, 6)
     , (699, 5, 9, 6)
     , (700, 5, 10, 6)
     , (701, 5, 11, 6)
     , (702, 6, 1, 6)
     , (703, 6, 2, 6)
     , (704, 6, 3, 6)
     , (705, 6, 4, 6)
     , (706, 6, 5, 6)
     , (707, 6, 6, 6)
     , (708, 6, 7, 6)
     , (709, 6, 8, 6)
     , (710, 6, 9, 6)
     , (711, 6, 10, 6)
     , (712, 6, 11, 6)
     , (713, 7, 1, 6)
     , (714, 7, 2, 6)
     , (715, 7, 3, 6)
     , (716, 7, 4, 6)
     , (717, 7, 5, 6)
     , (718, 7, 6, 6)
     , (719, 7, 7, 6)
     , (720, 7, 8, 6)
     , (721, 7, 9, 6)
     , (722, 7, 10, 6)
     , (723, 7, 11, 6)
     , (724, 8, 1, 6)
     , (725, 8, 2, 6)
     , (726, 8, 3, 6)
     , (727, 8, 4, 6)
     , (728, 8, 5, 6)
     , (729, 8, 6, 6)
     , (730, 8, 7, 6)
     , (731, 8, 8, 6)
     , (732, 8, 9, 6)
     , (733, 8, 10, 6)
     , (734, 8, 11, 6)
     , (735, 9, 1, 6)
     , (736, 9, 2, 6)
     , (737, 9, 3, 6)
     , (738, 9, 4, 6)
     , (739, 9, 5, 6)
     , (740, 9, 6, 6)
     , (741, 9, 7, 6)
     , (742, 9, 8, 6)
     , (743, 9, 9, 6)
     , (744, 9, 10, 6)
     , (745, 9, 11, 6)
     , (746, 10, 1, 6)
     , (747, 10, 2, 6)
     , (748, 10, 3, 6)
     , (749, 10, 4, 6)
     , (750, 10, 5, 6)
     , (751, 10, 6, 6)
     , (752, 10, 7, 6)
     , (753, 10, 8, 6)
     , (754, 10, 9, 6)
     , (755, 10, 10, 6)
     , (756, 10, 11, 6)
     , (757, 1, 1, 7)
     , (758, 1, 2, 7)
     , (759, 1, 3, 7)
     , (760, 1, 4, 7)
     , (761, 1, 5, 7)
     , (762, 1, 6, 7)
     , (763, 1, 7, 7)
     , (764, 1, 8, 7)
     , (765, 1, 9, 7)
     , (766, 1, 10, 7)
     , (767, 1, 11, 7)
     , (768, 1, 12, 7)
     , (769, 1, 13, 7)
     , (770, 2, 1, 7)
     , (771, 2, 2, 7)
     , (772, 2, 3, 7)
     , (773, 2, 4, 7)
     , (774, 2, 5, 7)
     , (775, 2, 6, 7)
     , (776, 2, 7, 7)
     , (777, 2, 8, 7)
     , (778, 2, 9, 7)
     , (779, 2, 10, 7)
     , (780, 2, 11, 7)
     , (781, 2, 12, 7)
     , (782, 2, 13, 7)
     , (783, 3, 1, 7)
     , (784, 3, 2, 7)
     , (785, 3, 3, 7)
     , (786, 3, 4, 7)
     , (787, 3, 5, 7)
     , (788, 3, 6, 7)
     , (789, 3, 7, 7)
     , (790, 3, 8, 7)
     , (791, 3, 9, 7)
     , (792, 3, 10, 7)
     , (793, 3, 11, 7)
     , (794, 3, 12, 7)
     , (795, 3, 13, 7)
     , (796, 4, 1, 7)
     , (797, 4, 2, 7)
     , (798, 4, 3, 7)
     , (799, 4, 4, 7)
     , (800, 4, 5, 7)
     , (801, 4, 6, 7)
     , (802, 4, 7, 7)
     , (803, 4, 8, 7)
     , (804, 4, 9, 7)
     , (805, 4, 10, 7)
     , (806, 4, 11, 7)
     , (807, 4, 12, 7)
     , (808, 4, 13, 7)
     , (809, 5, 1, 7)
     , (810, 5, 2, 7)
     , (811, 5, 3, 7)
     , (812, 5, 4, 7)
     , (813, 5, 5, 7)
     , (814, 5, 6, 7)
     , (815, 5, 7, 7)
     , (816, 5, 8, 7)
     , (817, 5, 9, 7)
     , (818, 5, 10, 7)
     , (819, 5, 11, 7)
     , (820, 5, 12, 7)
     , (821, 5, 13, 7)
     , (822, 6, 1, 7)
     , (823, 6, 2, 7)
     , (824, 6, 3, 7)
     , (825, 6, 4, 7)
     , (826, 6, 5, 7)
     , (827, 6, 6, 7)
     , (828, 6, 7, 7)
     , (829, 6, 8, 7)
     , (830, 6, 9, 7)
     , (831, 6, 10, 7)
     , (832, 6, 11, 7)
     , (833, 6, 12, 7)
     , (834, 6, 13, 7)
     , (835, 7, 1, 7)
     , (836, 7, 2, 7)
     , (837, 7, 3, 7)
     , (838, 7, 4, 7)
     , (839, 7, 5, 7)
     , (840, 7, 6, 7)
     , (841, 7, 7, 7)
     , (842, 7, 8, 7)
     , (843, 7, 9, 7)
     , (844, 7, 10, 7)
     , (845, 7, 11, 7)
     , (846, 7, 12, 7)
     , (847, 7, 13, 7)
     , (848, 8, 1, 7)
     , (849, 8, 2, 7)
     , (850, 8, 3, 7)
     , (851, 8, 4, 7)
     , (852, 8, 5, 7)
     , (853, 8, 6, 7)
     , (854, 8, 7, 7)
     , (855, 8, 8, 7)
     , (856, 8, 9, 7)
     , (857, 8, 10, 7)
     , (858, 8, 11, 7)
     , (859, 8, 12, 7)
     , (860, 8, 13, 7)
     , (861, 9, 1, 7)
     , (862, 9, 2, 7)
     , (863, 9, 3, 7)
     , (864, 9, 4, 7)
     , (865, 9, 5, 7)
     , (866, 9, 6, 7)
     , (867, 9, 7, 7)
     , (868, 9, 8, 7)
     , (869, 9, 9, 7)
     , (870, 9, 10, 7)
     , (871, 9, 11, 7)
     , (872, 9, 12, 7)
     , (873, 9, 13, 7)
     , (874, 10, 1, 7)
     , (875, 10, 2, 7)
     , (876, 10, 3, 7)
     , (877, 10, 4, 7)
     , (878, 10, 5, 7)
     , (879, 10, 6, 7)
     , (880, 10, 7, 7)
     , (881, 10, 8, 7)
     , (882, 10, 9, 7)
     , (883, 10, 10, 7)
     , (884, 10, 11, 7)
     , (885, 10, 12, 7)
     , (886, 10, 13, 7)
     , (887, 11, 1, 7)
     , (888, 11, 2, 7)
     , (889, 11, 3, 7)
     , (890, 11, 4, 7)
     , (891, 11, 5, 7)
     , (892, 11, 6, 7)
     , (893, 11, 7, 7)
     , (894, 11, 8, 7)
     , (895, 11, 9, 7)
     , (896, 11, 10, 7)
     , (897, 11, 11, 7)
     , (898, 11, 12, 7)
     , (899, 11, 13, 7)
     , (900, 12, 1, 7)
     , (901, 12, 2, 7)
     , (902, 12, 3, 7)
     , (903, 12, 4, 7)
     , (904, 12, 5, 7)
     , (905, 12, 6, 7)
     , (906, 12, 7, 7)
     , (907, 12, 8, 7)
     , (908, 12, 9, 7)
     , (909, 12, 10, 7)
     , (910, 12, 11, 7)
     , (911, 12, 12, 7)
     , (912, 12, 13, 7)
     , (913, 1, 1, 8)
     , (914, 1, 2, 8)
     , (915, 1, 3, 8)
     , (916, 1, 4, 8)
     , (917, 1, 5, 8)
     , (918, 1, 6, 8)
     , (919, 1, 7, 8)
     , (920, 1, 8, 8)
     , (921, 1, 9, 8)
     , (922, 2, 1, 8)
     , (923, 2, 2, 8)
     , (924, 2, 3, 8)
     , (925, 2, 4, 8)
     , (926, 2, 5, 8)
     , (927, 2, 6, 8)
     , (928, 2, 7, 8)
     , (929, 2, 8, 8)
     , (930, 2, 9, 8)
     , (931, 3, 1, 8)
     , (932, 3, 2, 8)
     , (933, 3, 3, 8)
     , (934, 3, 4, 8)
     , (935, 3, 5, 8)
     , (936, 3, 6, 8)
     , (937, 3, 7, 8)
     , (938, 3, 8, 8)
     , (939, 3, 9, 8)
     , (940, 4, 1, 8)
     , (941, 4, 2, 8)
     , (942, 4, 3, 8)
     , (943, 4, 4, 8)
     , (944, 4, 5, 8)
     , (945, 4, 6, 8)
     , (946, 4, 7, 8)
     , (947, 4, 8, 8)
     , (948, 4, 9, 8)
     , (949, 5, 1, 8)
     , (950, 5, 2, 8)
     , (951, 5, 3, 8)
     , (952, 5, 4, 8)
     , (953, 5, 5, 8)
     , (954, 5, 6, 8)
     , (955, 5, 7, 8)
     , (956, 5, 8, 8)
     , (957, 5, 9, 8)
     , (958, 6, 1, 8)
     , (959, 6, 2, 8)
     , (960, 6, 3, 8)
     , (961, 6, 4, 8)
     , (962, 6, 5, 8)
     , (963, 6, 6, 8)
     , (964, 6, 7, 8)
     , (965, 6, 8, 8)
     , (966, 6, 9, 8)
     , (967, 7, 1, 8)
     , (968, 7, 2, 8)
     , (969, 7, 3, 8)
     , (970, 7, 4, 8)
     , (971, 7, 5, 8)
     , (972, 7, 6, 8)
     , (973, 7, 7, 8)
     , (974, 7, 8, 8)
     , (975, 7, 9, 8)
     , (976, 8, 1, 8)
     , (977, 8, 2, 8)
     , (978, 8, 3, 8)
     , (979, 8, 4, 8)
     , (980, 8, 5, 8)
     , (981, 8, 6, 8)
     , (982, 8, 7, 8)
     , (983, 8, 8, 8)
     , (984, 8, 9, 8)
     , (985, 1, 1, 9)
     , (986, 1, 2, 9)
     , (987, 1, 3, 9)
     , (988, 1, 4, 9)
     , (989, 1, 5, 9)
     , (990, 1, 6, 9)
     , (991, 1, 7, 9)
     , (992, 1, 8, 9)
     , (993, 1, 9, 9)
     , (994, 1, 10, 9)
     , (995, 1, 11, 9)
     , (996, 1, 12, 9)
     , (997, 1, 13, 9)
     , (998, 1, 14, 9)
     , (999, 1, 15, 9)
     , (1000, 1, 16, 9)
     , (1001, 1, 17, 9)
     , (1002, 2, 1, 9)
     , (1003, 2, 2, 9)
     , (1004, 2, 3, 9)
     , (1005, 2, 4, 9)
     , (1006, 2, 5, 9)
     , (1007, 2, 6, 9)
     , (1008, 2, 7, 9)
     , (1009, 2, 8, 9)
     , (1010, 2, 9, 9)
     , (1011, 2, 10, 9)
     , (1012, 2, 11, 9)
     , (1013, 2, 12, 9)
     , (1014, 2, 13, 9)
     , (1015, 2, 14, 9)
     , (1016, 2, 15, 9)
     , (1017, 2, 16, 9)
     , (1018, 2, 17, 9)
     , (1019, 3, 1, 9)
     , (1020, 3, 2, 9)
     , (1021, 3, 3, 9)
     , (1022, 3, 4, 9)
     , (1023, 3, 5, 9)
     , (1024, 3, 6, 9)
     , (1025, 3, 7, 9)
     , (1026, 3, 8, 9)
     , (1027, 3, 9, 9)
     , (1028, 3, 10, 9)
     , (1029, 3, 11, 9)
     , (1030, 3, 12, 9)
     , (1031, 3, 13, 9)
     , (1032, 3, 14, 9)
     , (1033, 3, 15, 9)
     , (1034, 3, 16, 9)
     , (1035, 3, 17, 9)
     , (1036, 4, 1, 9)
     , (1037, 4, 2, 9)
     , (1038, 4, 3, 9)
     , (1039, 4, 4, 9)
     , (1040, 4, 5, 9)
     , (1041, 4, 6, 9)
     , (1042, 4, 7, 9)
     , (1043, 4, 8, 9)
     , (1044, 4, 9, 9)
     , (1045, 4, 10, 9)
     , (1046, 4, 11, 9)
     , (1047, 4, 12, 9)
     , (1048, 4, 13, 9)
     , (1049, 4, 14, 9)
     , (1050, 4, 15, 9)
     , (1051, 4, 16, 9)
     , (1052, 4, 17, 9)
     , (1053, 5, 1, 9)
     , (1054, 5, 2, 9)
     , (1055, 5, 3, 9)
     , (1056, 5, 4, 9)
     , (1057, 5, 5, 9)
     , (1058, 5, 6, 9)
     , (1059, 5, 7, 9)
     , (1060, 5, 8, 9)
     , (1061, 5, 9, 9)
     , (1062, 5, 10, 9)
     , (1063, 5, 11, 9)
     , (1064, 5, 12, 9)
     , (1065, 5, 13, 9)
     , (1066, 5, 14, 9)
     , (1067, 5, 15, 9)
     , (1068, 5, 16, 9)
     , (1069, 5, 17, 9)
     , (1070, 6, 1, 9)
     , (1071, 6, 2, 9)
     , (1072, 6, 3, 9)
     , (1073, 6, 4, 9)
     , (1074, 6, 5, 9)
     , (1075, 6, 6, 9)
     , (1076, 6, 7, 9)
     , (1077, 6, 8, 9)
     , (1078, 6, 9, 9)
     , (1079, 6, 10, 9)
     , (1080, 6, 11, 9)
     , (1081, 6, 12, 9)
     , (1082, 6, 13, 9)
     , (1083, 6, 14, 9)
     , (1084, 6, 15, 9)
     , (1085, 6, 16, 9)
     , (1086, 6, 17, 9)
     , (1087, 7, 1, 9)
     , (1088, 7, 2, 9)
     , (1089, 7, 3, 9)
     , (1090, 7, 4, 9)
     , (1091, 7, 5, 9)
     , (1092, 7, 6, 9)
     , (1093, 7, 7, 9)
     , (1094, 7, 8, 9)
     , (1095, 7, 9, 9)
     , (1096, 7, 10, 9)
     , (1097, 7, 11, 9)
     , (1098, 7, 12, 9)
     , (1099, 7, 13, 9)
     , (1100, 7, 14, 9)
     , (1101, 7, 15, 9)
     , (1102, 7, 16, 9)
     , (1103, 7, 17, 9)
     , (1104, 8, 1, 9)
     , (1105, 8, 2, 9)
     , (1106, 8, 3, 9)
     , (1107, 8, 4, 9)
     , (1108, 8, 5, 9)
     , (1109, 8, 6, 9)
     , (1110, 8, 7, 9)
     , (1111, 8, 8, 9)
     , (1112, 8, 9, 9)
     , (1113, 8, 10, 9)
     , (1114, 8, 11, 9)
     , (1115, 8, 12, 9)
     , (1116, 8, 13, 9)
     , (1117, 8, 14, 9)
     , (1118, 8, 15, 9)
     , (1119, 8, 16, 9)
     , (1120, 8, 17, 9)
     , (1121, 9, 1, 9)
     , (1122, 9, 2, 9)
     , (1123, 9, 3, 9)
     , (1124, 9, 4, 9)
     , (1125, 9, 5, 9)
     , (1126, 9, 6, 9)
     , (1127, 9, 7, 9)
     , (1128, 9, 8, 9)
     , (1129, 9, 9, 9)
     , (1130, 9, 10, 9)
     , (1131, 9, 11, 9)
     , (1132, 9, 12, 9)
     , (1133, 9, 13, 9)
     , (1134, 9, 14, 9)
     , (1135, 9, 15, 9)
     , (1136, 9, 16, 9)
     , (1137, 9, 17, 9)
     , (1138, 10, 1, 9)
     , (1139, 10, 2, 9)
     , (1140, 10, 3, 9)
     , (1141, 10, 4, 9)
     , (1142, 10, 5, 9)
     , (1143, 10, 6, 9)
     , (1144, 10, 7, 9)
     , (1145, 10, 8, 9)
     , (1146, 10, 9, 9)
     , (1147, 10, 10, 9)
     , (1148, 10, 11, 9)
     , (1149, 10, 12, 9)
     , (1150, 10, 13, 9)
     , (1151, 10, 14, 9)
     , (1152, 10, 15, 9)
     , (1153, 10, 16, 9)
     , (1154, 10, 17, 9)
     , (1155, 11, 1, 9)
     , (1156, 11, 2, 9)
     , (1157, 11, 3, 9)
     , (1158, 11, 4, 9)
     , (1159, 11, 5, 9)
     , (1160, 11, 6, 9)
     , (1161, 11, 7, 9)
     , (1162, 11, 8, 9)
     , (1163, 11, 9, 9)
     , (1164, 11, 10, 9)
     , (1165, 11, 11, 9)
     , (1166, 11, 12, 9)
     , (1167, 11, 13, 9)
     , (1168, 11, 14, 9)
     , (1169, 11, 15, 9)
     , (1170, 11, 16, 9)
     , (1171, 11, 17, 9)
     , (1172, 12, 1, 9)
     , (1173, 12, 2, 9)
     , (1174, 12, 3, 9)
     , (1175, 12, 4, 9)
     , (1176, 12, 5, 9)
     , (1177, 12, 6, 9)
     , (1178, 12, 7, 9)
     , (1179, 12, 8, 9)
     , (1180, 12, 9, 9)
     , (1181, 12, 10, 9)
     , (1182, 12, 11, 9)
     , (1183, 12, 12, 9)
     , (1184, 12, 13, 9)
     , (1185, 12, 14, 9)
     , (1186, 12, 15, 9)
     , (1187, 12, 16, 9)
     , (1188, 12, 17, 9)
     , (1189, 13, 1, 9)
     , (1190, 13, 2, 9)
     , (1191, 13, 3, 9)
     , (1192, 13, 4, 9)
     , (1193, 13, 5, 9)
     , (1194, 13, 6, 9)
     , (1195, 13, 7, 9)
     , (1196, 13, 8, 9)
     , (1197, 13, 9, 9)
     , (1198, 13, 10, 9)
     , (1199, 13, 11, 9)
     , (1200, 13, 12, 9)
     , (1201, 13, 13, 9)
     , (1202, 13, 14, 9)
     , (1203, 13, 15, 9)
     , (1204, 13, 16, 9)
     , (1205, 13, 17, 9)
     , (1206, 14, 1, 9)
     , (1207, 14, 2, 9)
     , (1208, 14, 3, 9)
     , (1209, 14, 4, 9)
     , (1210, 14, 5, 9)
     , (1211, 14, 6, 9)
     , (1212, 14, 7, 9)
     , (1213, 14, 8, 9)
     , (1214, 14, 9, 9)
     , (1215, 14, 10, 9)
     , (1216, 14, 11, 9)
     , (1217, 14, 12, 9)
     , (1218, 14, 13, 9)
     , (1219, 14, 14, 9)
     , (1220, 14, 15, 9)
     , (1221, 14, 16, 9)
     , (1222, 14, 17, 9);

CREATE TABLE TICKETS
(
    TICKET_ID     BIGINT  NOT NULL,
    IS_VALIDATED  BOOLEAN NOT NULL,
    IS_STUDENT    BOOLEAN NOT NULL,
    PRICE         REAL    NOT NULL,
    REPERTOIRE_ID BIGINT  NOT NULL,
    SEAT_ID       BIGINT  NOT NULL,
    PRICE_ID      BIGINT  NOT NULL
);
ALTER TABLE TICKETS
    ADD CONSTRAINT TICKETS_PK PRIMARY KEY (TICKET_ID);
INSERT INTO TICKETS
VALUES (1, true, false, 16.99, 1, 10, 5)
     , (2, true, true, 15.99, 1, 9, 6)
     , (3, false, false, 16.99, 1, 3, 2);
