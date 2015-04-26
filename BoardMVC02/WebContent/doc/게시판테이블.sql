drop table memo;
drop table album;
drop table bbs;
drop table reboard;
drop table board;
drop table board_list;
drop table board_type;
drop table board_category;


drop sequence type_seq;
drop sequence category_seq;
drop sequence list_seq;
drop sequence board_seq;
drop sequence reboard_rseq;
drop sequence bbs_bseq;
drop sequence album_aseq;
drop sequence memo_mseq;

create table board_type
(
  btype number,
  btype_name varchar2(100),
  constraint board_type_btype_pk primary key (btype)
);

create sequence type_seq
start with 1 increment by 1; 

insert into board_type (btype, btype_name)
values (type_seq.nextval, '공지사항');

insert into board_type (btype, btype_name)
values (type_seq.nextval, '방명록');

insert into board_type (btype, btype_name)
values (type_seq.nextval, '일반게시판');

insert into board_type (btype, btype_name)
values (type_seq.nextval, '답변게시판');

insert into board_type (btype, btype_name)
values (type_seq.nextval, '자료실');

insert into board_type (btype, btype_name)
values (type_seq.nextval, '사진게시판');

commit;


create table board_category
(
  ccode number,
  cname varchar2(100),
  constraint board_category_ccode_pk primary key (ccode)
);

create sequence category_seq
start with 1 increment by 1; 

insert into board_category (ccode, cname)
values (category_seq.nextval, '갤럭시노트2');

insert into board_category (ccode, cname)
values (category_seq.nextval, '갤럭시S4');

insert into board_category (ccode, cname)
values (category_seq.nextval, '아이폰5');

commit;


create table board_list
(
  bcode number,
  bname varchar2(100),
  btype number,
  ccode number,
  constraint board_list_bcode_pk primary key (bcode),
  constraint board_list_btype_fk foreign key (btype)
  references board_type (btype),
  constraint board_list_ccode_fk foreign key (ccode)
  references board_category (ccode)
);

create sequence list_seq
start with 1 increment by 1; 

insert into board_list (bcode, bname, btype, ccode)
values (list_seq.nextval, '노트2 알림판', 1, 1);

insert into board_list (bcode, bname, btype, ccode)
values (list_seq.nextval, '노트2 자유 토론장', 3, 1);

insert into board_list (bcode, bname, btype, ccode)
values (list_seq.nextval, '노트2 묻고답하기', 4, 1);

insert into board_list (bcode, bname, btype, ccode)
values (list_seq.nextval, '노트2 자랑하기', 6, 1);

insert into board_list (bcode, bname, btype, ccode)
values (list_seq.nextval, '노트2 어플올리기', 5, 1);

insert into board_list (bcode, bname, btype, ccode)
values (list_seq.nextval, 'S4 공지', 1, 2);

insert into board_list (bcode, bname, btype, ccode)
values (list_seq.nextval, 'S4 방명록', 2, 2);

insert into board_list (bcode, bname, btype, ccode)
values (list_seq.nextval, 'S4 궁금해요', 4, 2);

insert into board_list (bcode, bname, btype, ccode)
values (list_seq.nextval, 'S4 앨범', 6, 2);

insert into board_list (bcode, bname, btype, ccode)
values (list_seq.nextval, '아이폰5S 공지사항', 1, 3);

insert into board_list (bcode, bname, btype, ccode)
values (list_seq.nextval, '아이폰5S 토론', 4, 3);

insert into board_list (bcode, bname, btype, ccode)
values (list_seq.nextval, '아이폰5S 샷', 6, 3);

commit;
 

create table board
(
  seq number,
  name varchar2(10) not null,
  id varchar2(16) not null,
  email varchar2(100),
  subject varchar2(100) not null,
  content clob not null,
  hit number default 0,
  logtime date default sysdate,
  bcode number,
  constraint board_seq_pk primary key (seq),
  constraint board_bcode_fk foreign key (bcode)
  references board_list (bcode)
);

create sequence board_seq
start with 1 increment by 1; 


create table reboard
(
  rseq number,
  seq number,
  ref number,
  lev number,
  step number,
  pseq number,
  reply number default 0,
  constraint reboard_rseq_pk primary key (rseq),
  constraint reboard_seq_fk foreign key (seq)
  references board (seq)
);

create sequence reboard_rseq
start with 1 increment by 1; 


create table bbs
(
  bseq number,
  seq number,
  orign_file varchar2(100),
  save_file varchar2(110),
  filesize number,
  constraint bbs_bseq_pk primary key (bseq),
  constraint bbs_seq_fk foreign key (seq)
  references board (seq)
);

create sequence bbs_bseq
start with 1 increment by 1; 


create table album
(
  aseq number,
  seq number,
  orign_picture varchar2(30),
  save_picture varchar2(40),
  constraint album_aseq_pk primary key (aseq),
  constraint album_seq_fk foreign key (seq)
  references board (seq)
);

create sequence album_aseq
start with 1 increment by 1; 

create table memo
(
  mseq number,
  seq number,
  mcontent varchar2(500),
  name varchar2(20),
  mtime date default sysdate,
  constraint memo_mseq_pk primary key (mseq),
  constraint memo_seq_fk foreign key (seq)
  references board (seq)
);

create sequence memo_mseq
start with 1 increment by 1;  