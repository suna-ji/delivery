INSERT INTO BG_USER (USER_ID, USER_NAME, PASSWORD) VALUES ('testUser1', 'testUser1', 'abcde123456789!!');
INSERT INTO BG_USER (USER_ID, USER_NAME, PASSWORD) VALUES ('testUser2', 'testUser2', 'abcde123456789@@');
INSERT INTO BG_USER (USER_ID, USER_NAME, PASSWORD) VALUES ('testUser3', 'testUser3', 'ABCDE123456789!!');
INSERT INTO BG_USER (USER_ID, USER_NAME, PASSWORD) VALUES ('testUser4', 'testUser4', 'ABCDE123456789@@');


INSERT INTO BG_DELIVERY (USER_ID, DORO_ID, INSERT_DATE_TIME, STATUS) VALUES ( 'testUser1', 1, CURRENT_TIMESTAMP, 'READY');
INSERT INTO BG_DELIVERY (USER_ID, DORO_ID, INSERT_DATE_TIME, STATUS) VALUES ( 'testUser1', 1, '2022-12-20', 'READY');
INSERT INTO BG_DELIVERY (USER_ID, DORO_ID, INSERT_DATE_TIME, STATUS) VALUES ( 'testUser1', 1, '2022-12-21', 'ING');
INSERT INTO BG_DELIVERY (USER_ID, DORO_ID, INSERT_DATE_TIME, STATUS) VALUES ( 'testUser1', 1, '2022-12-22', 'READY');
INSERT INTO BG_DELIVERY (USER_ID, DORO_ID, INSERT_DATE_TIME, STATUS) VALUES ( 'testUser1', 1, '2022-12-23', 'END');
INSERT INTO BG_DELIVERY (USER_ID, DORO_ID, INSERT_DATE_TIME, STATUS) VALUES ( 'testUser2', 1, CURRENT_TIMESTAMP, 'END');
INSERT INTO BG_DELIVERY (USER_ID, DORO_ID, INSERT_DATE_TIME, STATUS) VALUES ( 'testUser2', 1, '2022-12-20', 'READY');
INSERT INTO BG_DELIVERY (USER_ID, DORO_ID, INSERT_DATE_TIME, STATUS) VALUES ( 'testUser2', 1, '2022-12-21', 'READY');
INSERT INTO BG_DELIVERY (USER_ID, DORO_ID, INSERT_DATE_TIME, STATUS) VALUES ( 'testUser2', 1, '2022-12-22', 'END');
INSERT INTO BG_DELIVERY (USER_ID, DORO_ID, INSERT_DATE_TIME, STATUS) VALUES ( 'testUser2', 1, '2022-12-23', 'ING');


INSERT INTO BG_DORO (SIDO_ID, SIGUNGU_ID, DORO_NAME_ID, BUILDING_NUMBER) VALUES (1, 1, 1, 1);
INSERT INTO BG_DORO (SIDO_ID, SIGUNGU_ID, DORO_NAME_ID, BUILDING_NUMBER) VALUES (2, 2, 2, 2);
INSERT INTO BG_DORO (SIDO_ID, SIGUNGU_ID, DORO_NAME_ID, BUILDING_NUMBER) VALUES (3, 3, 3, 3);