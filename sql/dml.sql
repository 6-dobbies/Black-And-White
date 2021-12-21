-- create DATABase blackandwhiteDB default character set UTF8;
-- show databases;
use blackandwhitedb;
-- select * from member;
-- select * from member_role;
-- select * from board;
-- select * from post;

INSERT INTO MEMBER(birth_year, del, email, gender, member_id, nickname, pw, pw_answer, pw_question, region) values('2000', 0, 'geronimo.ovo@gmail.com', '여', 'aaaa', 'aaaa1111', 'aaaa', '눈사람', '가장 행복했던 순간은 언제인가요?', '일산');
INSERT INTO MEMBER(birth_year, del, email, gender, member_id, nickname, pw, pw_answer, pw_question, region) values('2000', 0, 'dmgjgj53@gmail.com', '여', 'bbbb', 'bbbb1111', 'bbbb', '플레이데이터', '현재 소속은?', '판교');
INSERT INTO MEMBER(birth_year, del, email, gender, member_id, nickname, pw, pw_answer, pw_question, region) values('2000', 0, 'elizabeth.choi.smith@gmail.com', '여', 'cccc', 'cccc1111', 'cccc', '플레이데이터', '현재 소속은?', '인천검단');
INSERT INTO MEMBER(birth_year, del, email, gender, member_id, nickname, pw, pw_answer, pw_question, region) values('2000', 0, 'twfno2017@gmail.com', '남', 'dddd', 'dddd1111', 'dddd', '플레이데이터', '현재 소속은?', '서울역');
INSERT INTO MEMBER(birth_year, del, email, gender, member_id, nickname, pw, pw_answer, pw_question, region) values('2000', 0, 'ahrakim329@gmail.com', '여', 'eeee', 'eeee1111', 'eeee', '플레이데이터', '현재 소속은?', '장지역');
INSERT INTO MEMBER(birth_year, del, email, gender, member_id, nickname, pw, pw_answer, pw_question, region) values('2000', 0, 'ptkeb46@gmail.com', '남', 'ffff', 'eeee1111', 'ffff', '플레이데이터', '현재 소속은?', '화성봉담');

INSERT INTO TIER(member, win, draw, loss, play) values(1, 3, 2, 1, 6);
INSERT INTO TIER(member, win, draw, loss, play) values(2, 2, 1, 0, 3);
INSERT INTO TIER(member, win, draw, loss, play) values(3, 1, 0, 1, 2);
INSERT INTO TIER(member, win, draw, loss, play) values(4, 2, 0, 1, 3);
INSERT INTO TIER(member, win, draw, loss, play) values(5, 1, 2, 1, 4);
INSERT INTO TIER(member, win, draw, loss, play) values(6, 1, 0, 1, 2);

INSERT INTO BOARD(category) values('자유');
INSERT INTO BOARD(category) values('공략');
INSERT INTO BOARD(category) values('신고');

INSERT INTO POST(content, created, title, updated, category, writer, del) values('자유내용내용내용내용내용1111', '2021-12-01', '자유제목제목제목11', '2021-12-01', 1, 1, 0);
INSERT INTO POST(content, created, title, updated, category, writer, del) values('공략내용내용내용내용내용2222', '2021-12-02', '공략제목제목제목', '2021-12-02', 2, 2, 0);
INSERT INTO POST(content, created, title, updated, category, writer, del) values('신고내용내용내용내용내용3333', '2021-12-02', '신고제목제목제목', '2021-12-02', 3, 3, 0);
INSERT INTO POST(content, created, title, updated, category, writer, del) values('자유내용내용내용내용내용4444', '2021-12-01', '자유제목제목제목', '2021-12-01', 1, 4, 0);
INSERT INTO POST(content, created, title, updated, category, writer, del) values('공략내용내용내용내용내용5555', '2021-12-02', '공략제목제목제목', '2021-12-02', 2, 5, 0);
INSERT INTO POST(content, created, title, updated, category, writer, del) values('신고내용내용내용내용내용6666', '2021-12-02', '신고제목제목제목', '2021-12-02', 2, 6, 1);

INSERT INTO MEMBER_ROLE(member_member_idx, role) values(1, "manager");
INSERT INTO MEMBER_ROLE(member_member_idx, role) values(2, "manager");
INSERT INTO MEMBER_ROLE(member_member_idx, role) values(3, "normal");
INSERT INTO MEMBER_ROLE(member_member_idx, role) values(4, "normal");
INSERT INTO MEMBER_ROLE(member_member_idx, role) values(5, "normal");
INSERT INTO MEMBER_ROLE(member_member_idx, role) values(6, "normal");

COMMIT;
