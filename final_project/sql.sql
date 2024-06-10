create database final_project;
use final_project;

drop table user;
create table user(
	userid varchar(300) primary key,
    userpw varchar(300),
    username varchar(300),
    usergender char(3),
    zipcode varchar(300),#우편번호
    addr varchar(1000),
    addrdetail varchar(1000),
    addretc varchar(300),
    profile varchar(3000) DEFAULT 'basics.jpg'
);

ALTER TABLE user
DROP COLUMN user_pet;

select * from user;
INSERT INTO user (userid, userpw, username)
VALUES ('apple', 'apple', '사과');


create table t_user(
	userid varchar(300) primary key,
    userpw varchar(300) not null,
    username varchar(300) not null,
    
    zipcode varchar(300) not null,
    
    userhobby varchar(300)
);

create table user_besides(
	user_id varchar(300),
    product_id bigint,
    like_plice_name varchar(300),
    like_plice_addr varchar(300)
);

create table user_board(
	boardnum bigint primary key auto_increment,
    boardtitle varchar(300) not null,
    boardcontents text not null,
    regdate datetime default now(),
    updatedate datetime default now(),
    readcount bigint default 0,
    userid varchar(300)
);
insert into user_board(boardtitle, boardcontents, userid)
values('test', 'testBoardcontents', 'apple');
SELECT COUNT(*) FROM user_board WHERE userid = 'apple';
SELECT * FROM user_board WHERE userid = 'apple';


create table user_file(
	u_systemname varchar(2000),
    u_orgname varchar(2000),
    user_id varchar(300)
);

create table uboard_file(
	u_b_systemname varchar(2000),
    u_b_orgname varchar(2000),
    boardnum bigint
);

create table t_reply(
	replynum bigint primary key auto_increment,
    replycontents varchar(2000),
    regdate datetime default now(),
    updatedate datetime default now(),
    boardnum bigint,
    userid varchar(300)
);

drop table user_board_file;

create table seller(
	seller_id varchar(300) primary key,
    seller_pw varchar(300),
    seller_name varchar(300),
    seller_company varchar(300)
);

drop table product;
create table product(
	product_id bigint auto_increment primary key,
    product_name varchar(300),
    product_explanation varchar(300),#상품 설명
    seller_id varchar(300),
    product_board bigint,
    product_amount int,
    product_like_cnt int,
    product_buy_cnt int,
    product_uplode TIMESTAMP,
    
    product_page_title varchar(300), 
    product_page_content varchar(3000),
    product_page_uplode  TIMESTAMP,
    product_page_update  TIMESTAMP
);

create table product_file(
	p_systemname varchar(2000),
    p_orgname varchar(2000),
    p_boardnum bigint
);

create table p_reply(
	p_replynum bigint primary key auto_increment,
    p_replycontents varchar(2000),
    p_regdate datetime default now(),
    p_updatedate datetime default now(),
    p_boardnum bigint,
    userid varchar(300)
);