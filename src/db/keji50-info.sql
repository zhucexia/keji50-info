SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- 资讯类别信息表
-- ----------------------------
DROP TABLE IF EXISTS `info_category`;
CREATE TABLE `info_category` (
  `id` int(11) unsigned auto_increment NOT NULL COMMENT '主键ID',
  `code` varchar(64) NOT NULL COMMENT '资讯类别代码',
  `name` varchar(64) NOT NULL COMMENT '资讯类别名称',
  `color_code` varchar(7) DEFAULT '#66B3FF' COMMENT '类别颜色代码',
  `state` varchar(1) COLLATE utf8_bin DEFAULT 'c' COMMENT '数据状态   c:草稿 s;已审核 d:删除',
  `parent` int(11) DEFAULT 0 COMMENT '父类别id',
  PRIMARY KEY (`id`),
  KEY `info_category_id` (`id`),
  UNIQUE KEY `info_category_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='资讯类别信息表';

insert into info_category(code, name, color_code, state) values ('BigData', '大数据', '#FFC125', 's');

-- ----------------------------
-- 资讯信息表
-- ----------------------------
DROP TABLE IF EXISTS `info`;
CREATE TABLE `info` (
  `id` int(11) unsigned auto_increment NOT NULL COMMENT '主键ID',
  `info_category_id` int(11) NOT NULL COMMENT '资讯类别ID',
  `author_id` int(11) NOT NULL COMMENT '作者ID',
  `title` varchar(200) NOT NULL COMMENT '资讯标题',
  `image` varchar(200) NOT NULL COMMENT '展示图片',
  `short_desc` varchar(200) COMMENT '资讯简述',
  `content` text NOT NULL COMMENT '资讯内容',
  `tags` varchar(64) COMMENT '标签集， 格式 标签1|标签2|标签3',
  `suggest` varchar(1) DEFAULT '0' COMMENT '首页推荐  1是   0不是',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime COMMENT '更新时间',
  `state` varchar(1) COLLATE utf8_bin DEFAULT 'c' COMMENT '数据状态   c:草稿 s;已审核 d:删除',
  PRIMARY KEY (`id`),
  KEY `info_id` (`id`),
  KEY `info_info_category_id` (`info_category_id`),
  KEY `info_author_id` (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='资讯信息表';

-- ----------------------------
-- 资讯评论表
-- ----------------------------
DROP TABLE IF EXISTS `info_comment`;
CREATE TABLE `info_comment` (
  `id` int(11) unsigned auto_increment NOT NULL COMMENT '主键ID',
  `author_id` int(11) NOT NULL COMMENT '评论人ID',
  `to_author` varchar(60)  COMMENT '被评论人昵称',
  `info_id` int(11) NOT NULL COMMENT '资讯ID',
  `content` varchar(400) NOT NULL COMMENT '评论内容',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `state` varchar(1) COLLATE utf8_bin DEFAULT 'c' COMMENT '数据状态   c:草稿 s;已审核 d:删除',
  PRIMARY KEY (`id`),
  KEY `info_comment_id` (`id`),
  KEY `info_comment_info_id` (`info_id`),
  KEY `info_comment_author_id` (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='资讯评论表';