DROP TABLE IF EXISTS BG_USER;
CREATE TABLE BG_USER
(
	USER_ID VARCHAR(40) NOT NULL,
	USER_NAME VARCHAR(40) NOT NULL,
	PASSWORD VARCHAR(100) NOT NULL,
	INSERT_DATE_TIME TIMESTAMP,
    UPDATE_DATE_TIME TIMESTAMP,
    PRIMARY KEY (USER_ID)
);

DROP TABLE IF EXISTS BG_DELIVERY;
CREATE TABLE BG_DELIVERY
(
	DELIVERY_ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
	USER_ID VARCHAR(40) NOT NULL,
	DORO_ID BIGINT NOT NULL,
	INSERT_DATE_TIME TIMESTAMP,
	UPDATE_DATE_TIME TIMESTAMP,
	STATUS VARCHAR(40) NOT NULL,
	PRIMARY KEY (DELIVERY_ID)
);

DROP TABLE IF EXISTS BG_DORO;
CREATE TABLE BG_DORO
(
	DORO_ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
	SIDO_ID BIGINT NOT NULL,
	SIGUNGU_ID BIGINT NOT NULL,
	DORO_NAME_ID BIGINT NOT NULL,
	BUILDING_NUMBER BIGINT NOT NULL,
	PRIMARY KEY (DORO_ID),
	UNIQUE (SIDO_ID, SIGUNGU_ID,DORO_NAME_ID,BUILDING_NUMBER)
);
