create table t_access_log 
(
	id integer not null auto_increment, 
	access_day varchar(255), 
	access_daytime varchar(255), 
	access_type integer, 
	description varchar(255), 
	device_id varchar(255), 
	device_type varchar(255), 
	extranet_ip varchar(255), 
	ip varchar(255), 
	operate_result varchar(255), 
	os_type varchar(255), 
	query_string longtext, 
	request_target varchar(255), 
	requestURI varchar(255), 
	reserved longtext, 
	session2_id varchar(255), 
	time bigint not null, 
	user_agent varchar(255), 
	userId integer not null, 
	username varchar(255), 
	primary key (id)
)
create table t_admin 
(
	id integer not null auto_increment, 
	create_time integer, 
	create_id integer, 
	email varchar(255), 
	lastlogintime integer, 
	loginip varchar(255), 
	nickname varchar(255), 
	password varchar(255), 
	potrait varchar(255), 
	status integer not null, 
	type integer not null, 
	username varchar(255), 
	primary key (id)
)
create table t_compress_failed_pic (
	id integer not null auto_increment, 
	cause varchar(255), 
	failed_time datetime, 
	original_size bigint, 
	pic_path varchar(255), 
	primary key (id)
)
create table t_dictionary (
	id bigint not null auto_increment, 
	description varchar(255), 
	groupId varchar(255), 
	key2 varchar(255), 
	value varchar(255), 
	primary key (id)
)

create table t_news (
	id integer not null auto_increment, 
	content longtext, 
	delete_time bigint, 
	keyword varchar(255), 
	pic varchar(255), 
	release_time bigint, 
	reserved varchar(255), 
	sort integer, 
	status integer not null, 
	stick_time bigint, 
	sticktop integer not null, 
	title varchar(255), 
	type integer not null, 
	releaseId integer, 
	primary key (id)
)

create table t_user (
	id integer not null auto_increment, 
	create_time bigint, 
	email varchar(255), 
	level integer, 
	nickname varchar(255), 
	password varchar(255), 
	potrait varchar(255), 
	reserved varchar(255), 
	status integer not null, 
	update_time bigint, 
	username varchar(255), 
	create_id integer, 
	primary key (id)
)
alter table t_news add index FK_g046ikgfcfw1o0s4haapjlgtl 
(releaseId), add constraint FK_g046ikgfcfw1o0s4haapjlgtl 
foreign key (releaseId) references t_user (id)
alter table t_user add index FK_ccq4pgr2rwulfdo9nbdjxmron 
(create_id), add constraint FK_ccq4pgr2rwulfdo9nbdjxmron 
foreign key (create_id) references t_admin (id)

create table t_access_token (
	id integer not null auto_increment, 
	access_token varchar(255), 
	auth_username varchar(255), 
	loginTime varchar(255), 
	password varchar(255), 
	reserved varchar(255), 
	primary key (id)
)

create table t_app_update_log (
	id integer not null auto_increment, 
	apk_path varchar(255), 
	client_time varchar(255), 
	os_type varchar(255), 
	reserved varchar(255), 
	version varchar(255), 
	primary key (id)
)
alter table t_blog_info add column delete_time bigint
alter table t_push_device add index FK_4e4qn4vp4du6e5okbn0dq3wdf (userId), add constraint FK_4e4qn4vp4du6e5okbn0dq3wdf foreign key (userId) references t_user (id)
alter table t_push_message add index FK_ek2r577b6r0i1r49qsh724wnl (to_userId), add constraint FK_ek2r577b6r0i1r49qsh724wnl foreign key (to_userId) references t_user (id)
alter table t_received_push_message add index FK_75hkttxn875u4bh76ke91747 (push_device_id), add constraint FK_75hkttxn875u4bh76ke91747 foreign key (push_device_id) references t_push_device (id)
alter table t_received_push_message add index FK_1id8pc8ubfv8dg4k1sq8o4fxr (to_userId), add constraint FK_1id8pc8ubfv8dg4k1sq8o4fxr foreign key (to_userId) references t_user (id)
alter table t_vote_log add index FK_s30vk9xrraqmwc4qer3tgd0ch (user_id), add constraint FK_s30vk9xrraqmwc4qer3tgd0ch foreign key (user_id) references t_user (id)
