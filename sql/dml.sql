-- create DATABase blackandwhiteDB default character set UTF8;
-- show databases;
-- use blackandwhitedb;
-- select * from member;
-- select * from member_role;
-- select * from board;
-- select * from post;

INSERT INTO MEMBER(birth_year, del, email, gender, member_id, nickname, pw, pw_answer, pw_question, region, tier) values('2000', 0, 'geronimo.ovo@gmail.com', '여', 'aaaa', 'aaaa1111', 'aaaa', '플레이데이터', '현재 소속은?', '일산',  '1승 1무 1패 (상33% 중33% 하33%)');
INSERT INTO MEMBER(birth_year, del, email, gender, member_id, nickname, pw, pw_answer, pw_question, region, tier) values('2000', 0, 'dmgjgj53@gmail.com', '여', 'bbbb', 'bbbb1111', 'bbbb', '플레이데이터', '현재 소속은?', '판교',  '1승 1무 1패 (상100% 중0% 하0%)');
INSERT INTO MEMBER(birth_year, del, email, gender, member_id, nickname, pw, pw_answer, pw_question, region, tier) values('2000', 0, 'elizabeth.choi.smith@gmail.com', '여', 'cccc', 'cccc1111', 'cccc', '플레이데이터', '현재 소속은?', '인천검단',  '1승 1무 1패 (상0% 중100% 하0%)');
INSERT INTO MEMBER(birth_year, del, email, gender, member_id, nickname, pw, pw_answer, pw_question, region, tier) values('2000', 0, 'twfno2017@gmail.com', '남', 'dddd', 'dddd1111', 'dddd', '플레이데이터', '현재 소속은?', '서울역',  '1승 1무 1패 (상0% 중0% 하100%)');
INSERT INTO MEMBER(birth_year, del, email, gender, member_id, nickname, pw, pw_answer, pw_question, region, tier) values('2000', 0, 'ahrakim329@gmail.com', '여', 'eeee', 'eeee1111', 'eeee', '플레이데이터', '현재 소속은?', '장지역',  '1승 1무 1패 (상33% 중33% 하33%)');
INSERT INTO MEMBER(birth_year, del, email, gender, member_id, nickname, pw, pw_answer, pw_question, region, tier) values('2000', 0, 'ptkeb46@gmail.com', '남', 'ffff', 'eeee1111', 'ffff', '플레이데이터', '현재 소속은?', '화성봉담', '1승 1무 1패 (상33% 중33% 하33%)');

INSERT INTO BOARD(category) values('자유');
INSERT INTO BOARD(category) values('공략');
INSERT INTO BOARD(category) values('신고');

INSERT INTO POST(content, created, post_image, title, updated, category, writer, del) values('자유내용내용내용내용내용', '2021-12-01', '이미지1.jpg', '자유제목제목제목', '2021-12-01', 1, 1, 0);
INSERT INTO POST(content, created, post_image, title, updated, category, writer, del) values('공략내용내용내용내용내용', '2021-12-02', '이미지2.jpg', '공략제목제목제목', '2021-12-02', 2, 2, 0);
INSERT INTO POST(content, created, post_image, title, updated, category, writer, del) values('신고내용내용내용내용내용', '2021-12-02', '이미지3.jpg', '신고제목제목제목', '2021-12-02', 3, 3, 0);
INSERT INTO POST(content, created, post_image, title, updated, category, writer, del) values('자유내용내용내용내용내용', '2021-12-01', '이미지4.jpg', '자유제목제목제목', '2021-12-01', 1, 4, 0);
INSERT INTO POST(content, created, post_image, title, updated, category, writer, del) values('공략내용내용내용내용내용', '2021-12-02', '이미지5.jpg', '공략제목제목제목', '2021-12-02', 2, 5, 0);
INSERT INTO POST(content, created, post_image, title, updated, category, writer, del) values('신고내용내용내용내용내용', '2021-12-02', '이미지5.jpg', '신고제목제목제목', '2021-12-02', 2, 6, 1);

INSERT INTO MEMBER_ROLE(member_member_idx, role) values(1, "manager");
INSERT INTO MEMBER_ROLE(member_member_idx, role) values(2, "manager");
INSERT INTO MEMBER_ROLE(member_member_idx, role) values(3, "normal");
INSERT INTO MEMBER_ROLE(member_member_idx, role) values(4, "normal");
INSERT INTO MEMBER_ROLE(member_member_idx, role) values(5, "normal");
INSERT INTO MEMBER_ROLE(member_member_idx, role) values(6, "normal");

COMMIT;
