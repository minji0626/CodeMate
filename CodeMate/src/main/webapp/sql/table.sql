﻿CREATE TABLE MEMBER (
    mem_num NUMBER NOT NULL,
    mem_id VARCHAR2(12) NOT NULL,
    mem_auth NUMBER(1) NOT NULL,
    mem_report NUMBER(3),
    CONSTRAINT PK_MEMBER PRIMARY KEY (mem_num)
);

CREATE TABLE MEMBER_DETAIL (
    mem_num NUMBER NOT NULL,
    mem_name VARCHAR2(30) NOT NULL,
    mem_nickname VARCHAR2(30) NOT NULL,
    mem_passwd VARCHAR2(12) NOT NULL,
    mem_phone VARCHAR2(11) NOT NULL,
    mem_email VARCHAR2(50) NOT NULL,
    mem_level NUMBER(1) NOT NULL,
    mem_reg_date DATE DEFAULT SYSDATE NOT NULL,
    mem_modify_date DATE,
    mem_photo VARCHAR2(400),
    CONSTRAINT PK_MEMBER_DETAIL PRIMARY KEY (mem_num),
    CONSTRAINT FK_MEMBER_TO_MEMBER_DETAIL FOREIGN KEY (mem_num) REFERENCES MEMBER (mem_num),
    CONSTRAINT UK_MEMBER_DETAIL_PHONE UNIQUE (mem_phone),
    CONSTRAINT UK_MEMBER_DETAIL_EMAIL UNIQUE (mem_email)
);

CREATE TABLE MATE_PROFILE (
    mem_num NUMBER NOT NULL,
    mp_introduce CLOB,
	mp_reg_date DATE DEFAULT SYSDATE NOT NULL,
    mp_modify_date DATE,
    mp_state NUMBER(1) DEFAULT 0 NOT NULL,
    mp_position VARCHAR2(40),
    CONSTRAINT PK_MATE_PROFILE PRIMARY KEY (mem_num),
    CONSTRAINT FK_MEMBER_TO_MATE_PROFILE FOREIGN KEY (mem_num) REFERENCES MEMBER (mem_num)
);

CREATE TABLE CONSULT (
    cs_num NUMBER NOT NULL,
    mem_num NUMBER NOT NULL,
    cs_title VARCHAR2(210) NOT NULL,
    cs_content CLOB NOT NULL,
    cs_reg_date DATE DEFAULT SYSDATE NOT NULL,
    cs_answer CLOB,
    cs_answer_date DATE,
    cs_category NUMBER(1) NOT NULL,
    CONSTRAINT PK_CONSULT PRIMARY KEY (cs_num),
    CONSTRAINT FK_MEMBER_TO_CONSULT FOREIGN KEY (mem_num) REFERENCES MEMBER (mem_num)
);

CREATE TABLE HARD_SKILL (
    hs_code NUMBER NOT NULL,
    hs_name VARCHAR2(90) NOT NULL,
    hs_category NUMBER(1) NOT NULL,
    hs_photo VARCHAR2(400),
    CONSTRAINT PK_HARD_SKILL PRIMARY KEY (hs_code),
    CONSTRAINT UK_HARD_SKILL_NAME UNIQUE (hs_name)
);

CREATE TABLE SOFT_SKILL (
    ss_code NUMBER NOT NULL,
    ss_name VARCHAR2(90) NOT NULL,
    CONSTRAINT PK_SOFT_SKILL PRIMARY KEY (ss_code),
    CONSTRAINT UK_SOFT_SKILL_NAME UNIQUE (ss_name)
);

CREATE TABLE MATE_HARD_SKILL (
    mh_num NUMBER NOT NULL,
    mem_num NUMBER NOT NULL,
    hs_code NUMBER NOT NULL,
    mh_pro NUMBER(1),
    CONSTRAINT PK_MATE_HARD_SKILL PRIMARY KEY (mh_num),
    CONSTRAINT FK_MEMBER_TO_MATE_HSKILL FOREIGN KEY (mem_num) REFERENCES MEMBER (mem_num),
    CONSTRAINT FK_HSKILL_TO_MATE_HSKILL FOREIGN KEY (hs_code) REFERENCES HARD_SKILL (hs_code)
);

CREATE TABLE MATE_SOFT_SKILL (
    ms_num NUMBER NOT NULL,
    mem_num NUMBER NOT NULL,
    ss_code NUMBER NOT NULL,
    CONSTRAINT PK_MATE_SOFT_SKILL PRIMARY KEY (ms_num),
    CONSTRAINT FK_MEMBER_TO_MATE_SSKILL FOREIGN KEY (mem_num) REFERENCES MEMBER (mem_num),
    CONSTRAINT FK_SSKILL_TO_MATE_SSKILL FOREIGN KEY (ss_code) REFERENCES SOFT_SKILL (ss_code)
);

CREATE TABLE MATE_EXP (
    me_num NUMBER NOT NULL,
    mem_num NUMBER NOT NULL,
    me_title VARCHAR2(210) NOT NULL,
    me_content CLOB NOT NULL,
    me_period NUMBER(3) NOT NULL,
    me_category NUMBER(1) NOT NULL,
    CONSTRAINT PK_MATE_EXP PRIMARY KEY (me_num),
    CONSTRAINT FK_MEMBER_TO_MATE_EXP FOREIGN KEY (mem_num) REFERENCES MEMBER (mem_num)
);

CREATE TABLE MATE_REVIEW (
    mr_num NUMBER NOT NULL,
    mr_writer NUMBER NOT NULL,
    mr_receiver NUMBER NOT NULL,
    mr_reg_date DATE DEFAULT SYSDATE NOT NULL,
    mr_content CLOB NOT NULL,
    CONSTRAINT PK_MATE_REVIEW PRIMARY KEY (mr_num),
    CONSTRAINT FK_WRITER_TO_MATE_REVIEW FOREIGN KEY (mr_writer) REFERENCES MEMBER (mem_num),
    CONSTRAINT FK_RECEIVER_TO_MATE_REVIEW FOREIGN KEY (mr_receiver) REFERENCES MEMBER (mem_num)
);

CREATE TABLE C_BOARD (
    cb_num NUMBER NOT NULL,
    mem_num NUMBER NOT NULL,
    cb_title VARCHAR2(210) NOT NULL,
    cb_content CLOB NOT NULL,
    cb_hit NUMBER DEFAULT 0 NOT NULL,
    cb_like NUMBER(6) DEFAULT 0 NOT NULL,
    cb_reg_date DATE DEFAULT SYSDATE NOT NULL,
    cb_modify_date DATE,
    cb_file VARCHAR2(400),
    cb_ip VARCHAR2(40) NOT NULL,
    cb_type NUMBER(1) NOT NULL,
    CONSTRAINT PK_C_BOARD PRIMARY KEY (cb_num),
    CONSTRAINT FK_MEMBER_TO_C_BOARD FOREIGN KEY (mem_num) REFERENCES MEMBER (mem_num)
);

CREATE TABLE C_COMMENT (
    cc_num NUMBER NOT NULL,
    mem_num NUMBER NOT NULL,
    cb_num NUMBER NOT NULL,
    cc_content VARCHAR2(450) NOT NULL,
    cc_reg_date DATE DEFAULT SYSDATE NOT NULL,
    cc_modify_date DATE,
    CONSTRAINT PK_C_COMMENT PRIMARY KEY (cc_num),
    CONSTRAINT FK_MEMBER_TO_C_COMMENT FOREIGN KEY (mem_num) REFERENCES MEMBER (mem_num),
    CONSTRAINT FK_C_BOARD_TO_C_COMMENT FOREIGN KEY (cb_num) REFERENCES C_BOARD (cb_num)
);

CREATE TABLE TEAM (
    team_num NUMBER NOT NULL,
    team_status NUMBER(1) DEFAULT 0 NOT NULL,
    CONSTRAINT PK_TEAM PRIMARY KEY (team_num)
);

CREATE TABLE R_BOARD (
    rb_num NUMBER NOT NULL,
    mem_num NUMBER NOT NULL,
	rb_reg_date DATE DEFAULT SYSDATE NOT NULL,
    rb_category NUMBER(1) NOT NULL,
    rb_meet NUMBER(1) NOT NULL,
    rb_teamsize NUMBER(3) NOT NULL,
    rb_period NUMBER(1) NOT NULL,
    rb_start NUMBER(8) NOT NULL,
    rb_title VARCHAR2(180) NOT NULL,
    rb_content CLOB NOT NULL,
    rb_endrecruit NUMBER(8) NOT NULL,
    rb_pj_title VARCHAR2(60) NOT NULL,
    CONSTRAINT PK_R_BOARD PRIMARY KEY (rb_num),
    CONSTRAINT FK_MEMBER_TO_R_BOARD FOREIGN KEY (mem_num) REFERENCES MEMBER (mem_num)
);

CREATE TABLE R_COMMENT (
    rc_num NUMBER NOT NULL,
    mem_num NUMBER NOT NULL,
    rb_num NUMBER NOT NULL,
    rc_content VARCHAR2(450) NOT NULL,
    rc_reg_date DATE DEFAULT SYSDATE NOT NULL,
    rc_modify_date DATE,
    CONSTRAINT PK_R_COMMENT PRIMARY KEY (rc_num),
    CONSTRAINT FK_MEMBER_TO_R_COMMENT FOREIGN KEY (mem_num) REFERENCES MEMBER (mem_num),
    CONSTRAINT FK_R_BOARD_TO_R_COMMENT FOREIGN KEY (rb_num) REFERENCES R_BOARD (rb_num)
);

CREATE TABLE FIELD_DB (
    f_code NUMBER NOT NULL,
    f_name VARCHAR2(45) NOT NULL,
    CONSTRAINT PK_FIELD_DB PRIMARY KEY (f_code),
    CONSTRAINT UNIQUE_F_NAME UNIQUE (f_name)
);

CREATE TABLE R_FIELD (
    rf_num NUMBER NOT NULL,
    rb_num NUMBER NOT NULL,
    f_code NUMBER NOT NULL,
    CONSTRAINT PK_R_FIELD PRIMARY KEY (rf_num),
    CONSTRAINT FK_FIELD_DB_TO_R_FIELD FOREIGN KEY (f_code) REFERENCES FIELD_DB (f_code),
    CONSTRAINT FK_R_BOARD_TO_R_FIELD FOREIGN KEY (rb_num) REFERENCES R_BOARD (rb_num)
);

CREATE TABLE R_SKILL (
    rs_num NUMBER NOT NULL,
    rb_num NUMBER NOT NULL,
    hs_code NUMBER NOT NULL,
    CONSTRAINT PK_R_SKILL PRIMARY KEY (rs_num),
    CONSTRAINT FK_R_BOARD_TO_R_SKILL FOREIGN KEY (rb_num) REFERENCES R_BOARD (rb_num),
    CONSTRAINT FK_HARD_SKILL_TO_R_SKILL FOREIGN KEY (hs_code) REFERENCES HARD_SKILL (hs_code)
);

CREATE TABLE R_APPLY (
    ra_num NUMBER NOT NULL,
    rb_num NUMBER NOT NULL,
    mem_num NUMBER NOT NULL,
    ra_content CLOB NOT NULL,
    ra_date DATE DEFAULT SYSDATE NOT NULL,
    ra_pass NUMBER(1),
    CONSTRAINT PK_R_APPLY PRIMARY KEY (ra_num),
    CONSTRAINT FK_R_BOARD_TO_R_APPLY FOREIGN KEY (rb_num) REFERENCES R_BOARD (rb_num),
    CONSTRAINT FK_MEMBER_TO_R_APPLY FOREIGN KEY (mem_num) REFERENCES MEMBER (mem_num),
);

CREATE TABLE R_BOOKMARK (
    mem_num NUMBER NOT NULL,
    rb_num NUMBER NOT NULL,
    CONSTRAINT PK_R_BOOKMARK PRIMARY KEY (mem_num, rb_num),
    CONSTRAINT FK_MEMBER_TO_R_BOOKMARK FOREIGN KEY (mem_num) REFERENCES MEMBER (mem_num),
    CONSTRAINT FK_R_BOARD_TO_R_BOOKMARK FOREIGN KEY (rb_num) REFERENCES R_BOARD (rb_num)
);

CREATE TABLE TEAM_MEMBER (
    mem_num NUMBER NOT NULL,
    team_num NUMBER NOT NULL,
    tm_auth NUMBER(1) NOT NULL,
    tm_review_status NUMBER(1) DEFAULT 0 NOT NULL,
    CONSTRAINT PK_TEAM_MEMBER PRIMARY KEY (mem_num, team_num),
    CONSTRAINT FK_MEMBER_TO_TEAM_MEMBER FOREIGN KEY (mem_num) REFERENCES MEMBER (mem_num),
    CONSTRAINT FK_TEAM_TO_TEAM_MEMBER FOREIGN KEY (team_num) REFERENCES TEAM (team_num)
);

CREATE TABLE TEAM_BOARD (
    tb_num NUMBER NOT NULL,
    team_num NUMBER NOT NULL,
    mem_num NUMBER NOT NULL,
    tb_title VARCHAR2(90) NOT NULL,
    tb_content CLOB NOT NULL,
    tb_reg_date DATE DEFAULT SYSDATE NOT NULL,
    tb_modify_date DATE,
    tb_file VARCHAR2(400),
    tb_auth NUMBER(1) NOT NULL,
    CONSTRAINT PK_TEAM_BOARD PRIMARY KEY (tb_num),
    CONSTRAINT FK_TEAM_TO_TEAM_BOARD FOREIGN KEY (team_num) REFERENCES TEAM (team_num),
    CONSTRAINT FK_MEMBER_TO_TEAM_BOARD FOREIGN KEY (mem_num) REFERENCES MEMBER (mem_num)
);

CREATE TABLE TEAM_COMMENT (
    tc_num NUMBER NOT NULL,
    mem_num NUMBER NOT NULL,
    tb_num NUMBER NOT NULL,
    tc_content VARCHAR2(450) NOT NULL,
    tc_reg_date DATE NOT NULL,
    tc_modify_date DATE,
    CONSTRAINT PK_TEAM_COMMENT PRIMARY KEY (tc_num),
    CONSTRAINT FK_MEMBER_TO_TEAM_COMMENT FOREIGN KEY (mem_num) REFERENCES MEMBER (mem_num),
    CONSTRAINT FK_TEAM_BOARD_TO_TEAM_COMMENT FOREIGN KEY (tb_num) REFERENCES TEAM_BOARD (tb_num)
);

CREATE TABLE TEAM_TODO (
    tt_num NUMBER NOT NULL,
    team_num NUMBER NOT NULL,
    tt_content VARCHAR2(120) NOT NULL,
    tt_date VARCHAR2(10) NOT NULL,
    tt_start NUMBER(4),
    tt_end NUMBER(4),
    tt_state NUMBER(1) DEFAULT 0 NOT NULL,
    CONSTRAINT PK_TEAM_TODO PRIMARY KEY (tt_num),
    CONSTRAINT FK_TEAM_TO_TEAM_TODO FOREIGN KEY (team_num) REFERENCES TEAM (team_num)
);



CREATE SEQUENCE member_seq;
CREATE SEQUENCE consult_seq;
CREATE SEQUENCE mate_hard_skill_seq;
CREATE SEQUENCE mate_soft_skill_seq;
CREATE SEQUENCE hard_skill_seq;
CREATE SEQUENCE soft_skill_seq;
CREATE SEQUENCE mate_exp_seq;
CREATE SEQUENCE mate_review_seq;
CREATE SEQUENCE c_board_seq;
CREATE SEQUENCE c_comment_seq;
CREATE SEQUENCE r_board_seq;
CREATE SEQUENCE r_comment_seq;
CREATE SEQUENCE r_field_seq;
CREATE SEQUENCE field_db_seq;
CREATE SEQUENCE r_skill_seq;
CREATE SEQUENCE r_apply_seq;
CREATE SEQUENCE r_apply_seq;
CREATE SEQUENCE team_seq;
CREATE SEQUENCE team_board_seq;
CREATE SEQUENCE team_comment_seq;
CREATE SEQUENCE team_todo_seq;


ALTER TABLE team
ADD CONSTRAINT fk_team_rb_num
FOREIGN KEY (team_num)
REFERENCES r_board(rb_num);

ALTER TABLE r_apply
ADD CONSTRAINT fk_rb_num
FOREIGN KEY (rb_num)
REFERENCES r_board(rb_num)
ON DELETE SET NULL;

ALTER TABLE mate_review
ADD CONSTRAINT fk_team_TO_mate_review
FOREIGN KEY (team_num)
REFERENCES team(team_num);

ALTER TABLE mate_review ADD team_num number;

--mem_report값이 업데이트 될 때 정지취소일을 업데이트 하는 트리거(첫번째 경고)
CREATE OR REPLACE TRIGGER update_unlock_date1_trigger
BEFORE UPDATE OF mem_report ON member
FOR EACH ROW
BEGIN
  -- 특정 조건에 맞을 때만 실행
  IF :NEW.mem_auth = 1 AND :NEW.mem_report = 1 THEN
    :NEW.mem_unlock_date := SYSTIMESTAMP + INTERVAL '7' DAY;
  END IF;
END;

--mem_report값이 업데이트 될 때 정지취소일을 업데이트 하는 트리거(두번째 경고)
CREATE OR REPLACE TRIGGER update_unlock_date2_trigger
BEFORE UPDATE OF mem_report ON member
FOR EACH ROW
BEGIN
  -- 특정 조건에 맞을 때만 실행
  IF :NEW.mem_auth = 1 AND :NEW.mem_report = 2 THEN
    :NEW.mem_unlock_date := SYSTIMESTAMP + INTERVAL '14' DAY;
  END IF;
END;

--mem_report값이 업데이트 될 때 정지취소일을 업데이트 하는 트리거(세번째 경고)
CREATE OR REPLACE TRIGGER update_unlock_date3_trigger
BEFORE UPDATE OF mem_report ON member
FOR EACH ROW
BEGIN
  -- 특정 조건에 맞을 때만 실행
  IF :NEW.mem_auth = 1 AND :NEW.mem_report = 3 THEN
    :NEW.mem_unlock_date := SYSTIMESTAMP + INTERVAL '30' DAY;
  END IF;
END;

--mem_report값이 업데이트 될 때 정지취소일을 업데이트 하는 트리거(네번째 경고)
CREATE OR REPLACE TRIGGER update_unlock_date4_trigger
BEFORE UPDATE OF mem_report ON member
FOR EACH ROW
BEGIN
  -- 특정 조건에 맞을 때만 실행
  IF :NEW.mem_auth = 1 AND :NEW.mem_report = 4 THEN
    :NEW.mem_unlock_date := NULL;
  END IF;
END;

--정지취소일이 지나면 자동으로 mem_auth값을 2로 변경하는 스케줄러
BEGIN
  DBMS_SCHEDULER.create_job (
    job_name        => 'UNLOCK_MEM_JOB',
    job_type        => 'PLSQL_BLOCK',
    job_action      => 'BEGIN
                          UPDATE member
                          SET mem_auth = 2, mem_unlock_date = null
                          WHERE mem_unlock_date < SYSTIMESTAMP AND mem_auth = 1;
                        END;',
    start_date      => SYSTIMESTAMP,
    repeat_interval => 'FREQ=MINUTELY; INTERVAL=1', -- 1분마다 실행
    enabled         => TRUE
  );
END;

--mem_auth값이 변경되면 mem_unlock_date를 null로 변경하는 트리거
CREATE OR REPLACE TRIGGER update_after_unlock_trigger
BEFORE UPDATE OF mem_auth ON member
FOR EACH ROW
BEGIN
  -- 특정 조건에 맞을 때만 실행
  IF :NEW.mem_auth = 2 THEN
    :NEW.mem_unlock_date := null;
  END IF;
END;
