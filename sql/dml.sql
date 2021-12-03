INSERT INTO "MEMBER" values(member_seq.nextval, '2000', 'geronimo.ovo@gmail.com', '여', 'aaaa', 'aaaa1111', 0, 'aaaa', '플레이데이터', '현재 소속은?', '일산', 'manager', '1승 1무 1패 (상33% 중33% 하33%)');
INSERT INTO "MEMBER" values(member_seq.nextval, '2000', 'dmgjgj53@gmail.com', '여', 'bbbb', 'bbbb1111', 0, 'bbbb', '플레이데이터', '현재 소속은?', '판교', 'normal', '1승 1무 1패 (상100% 중0% 하0%)');
INSERT INTO "MEMBER" values(member_seq.nextval, '2000', 'elizabeth.choi.smith@gmail.com', '여', 'cccc', 'cccc1111', 0, 'cccc', '플레이데이터', '현재 소속은?', '인천검단', 'normal', '1승 1무 1패 (상0% 중100% 하0%)');
INSERT INTO "MEMBER" values(member_seq.nextval, '2000', 'twfno2017@gmail.com', '남', 'dddd', 'dddd1111', 0, 'dddd', '플레이데이터', '현재 소속은?', '서울역', 'normal', '1승 1무 1패 (상0% 중0% 하100%)');
INSERT INTO "MEMBER" values(member_seq.nextval, '2000', 'ahrakim329@gmail.com', '여', 'eeee', 'eeee1111', 0, 'eeee', '플레이데이터', '현재 소속은?', '장지역', 'normal', '1승 1무 1패 (상33% 중33% 하33%)');
INSERT INTO "MEMBER" values(member_seq.nextval, '2000', 'ptkeb46@gmail.com', '남', 'ffff', 'eeee1111', 0, 'ffff', '플레이데이터', '현재 소속은?', '수원', 'normal', '1승 1무 1패 (상33% 중33% 하33%)');

INSERT INTO BOARD values(board_seq.nextval, '자유 게시판');
INSERT INTO BOARD values(board_seq.nextval, '공략 게시판');
INSERT INTO BOARD values(board_seq.nextval, '신고 게시판');

INSERT INTO POST values(post_seq.nextval, '자유내용내용내용내용내용', '2021-12-01', '이미지1.jpg', '자유제목제목제목', '2021-12-01', 1, 1);
INSERT INTO POST values(post_seq.nextval, '공략내용내용내용내용내용', '2021-12-02', '이미지2.jpg', '공략제목제목제목', '2021-12-02', 2, 2);
INSERT INTO POST values(post_seq.nextval, '신고내용내용내용내용내용', '2021-12-02', '이미지3.jpg', '신고제목제목제목', '2021-12-02', 3, 3);
INSERT INTO POST values(post_seq.nextval, '자유내용내용내용내용내용', '2021-12-01', '이미지4.jpg', '자유제목제목제목', '2021-12-01', 1, 4);
INSERT INTO POST values(post_seq.nextval, '공략내용내용내용내용내용', '2021-12-02', '이미지5.jpg', '공략제목제목제목', '2021-12-02', 2, 5);

COMMIT;
