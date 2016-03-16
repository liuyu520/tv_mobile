insert into t_dictionary(groupid,key2,value,description) values('bbs_settings','content_max',   '40','40');
insert into t_dictionary(groupid,key2,value,description) values('news_settings','content_max',   '40','40');
-- 新闻列表每页显示的记录条数
insert into t_dictionary(groupid,key2,value,description) values('news_settings','recordsPerPage',   '9','9');
insert into t_dictionary(groupid,key2,value,description) values('broadcast_settings','content_max',   '40','40');
insert into t_dictionary(groupid,key2,value,description) values('bbs_settings','cardcontent_max',   '40','40');
insert into t_dictionary(groupid,key2,value,description) values('global_settings','pic_prefix',   'http://hbjltv.com/static/img/','图片前缀');
-- 上传图片的大小限制,单位是Byte
insert into t_dictionary(groupId,key2,value,description) values('global_setting','pic_max_size','10500000','10500000');

insert into t_dictionary(groupId,key2,value,description) values('client_version','latestversion','13','13');
insert into t_dictionary(groupId,key2,value,description) values('client_version','path','shop7.apk','shop7.apk');
insert into t_dictionary(groupId,key2,value,description) values('client_version','md5','9dfbbce1915cc2d99e89908aa2e56a55','9dfbbce1915cc2d99e89908aa2e56a55');
-- 允许跨域请求
insert into t_dictionary(groupId,key2,value,description) values('global_setting','AccessControlAllowOrigin','*','*');
-- 图闻最低个数
insert into t_dictionary(groupId,key2,value,description) values('global_setting','pic_min_num','3','3');

insert into t_dictionary(groupId,key2,value,description) values('global_setting','uploadAbsulotePrefix','/var/www/ajl','/var/www/ajl');

insert into t_dictionary(groupId,key2,value,description) values('global_setting','uploadVideoPrefix','/var/www/ajl/uploadvideos','/var/www/ajl/uploadvideos');
