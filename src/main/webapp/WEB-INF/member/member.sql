show tables;

create table member (
	idx 			int 					not null 	auto_increment,	-- 고유번호
	mid 			varchar(20) 	not null,									-- 아이디(중복x)
	pwd 			varchar(100) 	not null,									-- 비밀번호(SHA256 암호화)
	nickName	varchar(20) 	not null,									-- 닉네임(중복x/수정o)
	name 			varchar(20) 	not null,									-- 성명
	gender 		varchar(5) 		default 	'남자',					-- 성별
	birthday 	datetime 			default 	now(),					-- 생일
	tel 			varchar(15),														-- 전화번호(010-1234-5678)
	address 	varchar(100),														-- 주소(다음API 우편번호)
	email			varchar(50)		not null,									-- 이메일(아이디/비밀번호 찾기) *폼체크 필수
	homePage	varchar(50),														-- 홈페이지
	job				varchar(20),														-- 직업
	hobby			varchar(100),														-- 취미(복수선택o/구분자'/')
	photo			varchar(100)	default 	'noimage.jpg',	-- 사진
	content		text,																		-- 소개
	userInfor	char(6)				default 	'공개',					-- 정보 공개여부
	userDel		char(2)				default		'NO',						-- 탈퇴신청여부 OK:탈퇴신청중
	point			int						default		100,						-- 포인트 가입시100 1회방문10 1일최대50
	level			int						default		1,							-- 회원등급 0관리자 1준회원 2정회원 3우수회원 4운영자
	visitCnt	int						default		0,							-- 총 방문 횟수
	startDate	datetime			default		now(),					-- 최초 가입일
	lastDate	datetime			default		now(),					-- 최종 접속일
	todayCnt	int						default		0,							-- 오늘 방문 횟수		
	primary key (idx)
);

desc member;

select * from member;





