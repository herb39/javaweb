show tables;

create table board (
	idx   int not null auto_increment,	/* 게시글의 고유번호 */
	mid      varchar(20) not null,					/* 게시글 올린이 아이디 */
	nickName varchar(20) not null,			/* 게시글 올린이 닉네임 */
	title   varchar(100) not null,				/* 게시글 제목 */
	email		varchar(50),							/* 이메일 주소 */
	homePage varchar(50),							/* 홈페이지(개인블로그) 주소 */
	content text not null,						/* 게시글 내용 */
	readNum int default 0,						/* 글 조회수 */
	hostIp  varchar(40) not null,			/* 글 올린이의 IP */
	openSw  char(2)	default 'OK',			/* 게시글 공개여부(OK:공개,NO:비공개) */
	wDate   datetime  default now(),		/* 글 올린 날짜/시간 */
	good		int default 0,						/* '좋아요' 클릭 횟수 누적 */
	primary key(idx)
);

desc board;

insert into board values (default,'admin','관리맨','게시판 서비스를 시작합니다.','cjsk1126@naver.com','cjsk1126.tistory.com','이곳은 게시판입니다.',default,'192.168.50.20',default,default,default);

select * from board;
