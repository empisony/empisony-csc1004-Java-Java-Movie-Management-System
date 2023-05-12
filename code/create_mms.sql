-- --------------------------------------------------------
-- 主机:                           localhost
-- 服务器版本:                        8.0.28 - MySQL Community Server - GPL
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  12.0.0.6468
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- 导出  表 movie-ms.mms_film 结构
CREATE TABLE IF NOT EXISTS `mms_film` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '电影名称',
  `rate` varchar(20) DEFAULT NULL COMMENT '评级',
  `type` varchar(50) DEFAULT NULL COMMENT '电影类型',
  `description` varchar(200) DEFAULT NULL COMMENT '电影介绍',
  `poster` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电影海报',
  `movie` varchar(200) DEFAULT NULL COMMENT '电影视频',
  `au_rate` varchar(50) DEFAULT NULL COMMENT '收视率',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='影片信息';

-- 正在导出表  movie-ms.mms_film 的数据：~7 rows (大约)
INSERT INTO `mms_film` (`id`, `name`, `rate`, `type`, `description`, `poster`, `movie`, `au_rate`) VALUES
	('1572918019401711616', '城市之光', '', '喜剧', '城市之光', '', '1579525254902972416.mp4', NULL),
	('1982586699426824192', '阿凡达', '', '剧情', '阿凡达', '4291740631367155712.png', NULL, NULL),
	('2480032322779348992', '情书', '', '爱情', '情书', '4291740631367155712.png', NULL, NULL),
	('2564955261571694592', '剪刀手爱德华', '', '爱情', '剪刀手爱德华', '4291740631367155712.png', NULL, NULL),
	('4295585372844326912', '我不是药神', '', '喜剧', '徐峥', '4291740631367155712.png', NULL, NULL),
	('4440170078993907712', '泰坦尼克号', '', '爱情', '莱昂纳多', '4433707646805082112.png', NULL, NULL),
	('4702456373189804032', '狮子王', '', '剧情', '狮子王', '', NULL, NULL),
	('8617054257134174208', '阿甘正传', '', '剧情', '阿甘正传', '4291740631367155712.png', NULL, NULL),
	('8958867857035231232', '流浪地球', '', '剧情', '流浪地球', '8992822802285330432.jpg', '8961499192656658432.mp4', NULL);

-- 导出  表 movie-ms.mms_film_user 结构
CREATE TABLE IF NOT EXISTS `mms_film_user` (
  `film_id` varchar(50) DEFAULT NULL COMMENT '影片主键',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户主键',
  `score` int DEFAULT NULL COMMENT '评分',
  `comment` varchar(200) DEFAULT NULL COMMENT '评论'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='影片用户关联信息表';

-- 正在导出表  movie-ms.mms_film_user 的数据：~0 rows (大约)
INSERT INTO `mms_film_user` (`film_id`, `user_id`, `score`, `comment`) VALUES
	('1982586699426824192', 'taozi', 9, '测试'),
	('2480032322779348992', 'taozi', 10, '测试2'),
	('1982586699426824192', 'admin', 9, '不错'),
	('2480032322779348992', 'admin', 10, '非常棒'),
	('2564955261571694592', 'admin', 10, '非常棒'),
	('4295585372844326912', 'admin', 9, '不错'),
	('1982586699426824192', 'xiaozhu', 8, '哈哈'),
	('1982586699426824192', 'xiaoxiao', 8, '哈哈'),
	('8617054257134174208', 'admin', 8, '测试'),
	('1572918019401711616', 'xioxi', 10, '测试'),
	('1572918019401711616', 'huhu', 10, '测试'),
	('1982586699426824192', 'huhu', 9, '好看');

-- 导出  表 movie-ms.mms_user 结构
CREATE TABLE IF NOT EXISTS `mms_user` (
  `user_code` varchar(50) NOT NULL COMMENT '用户账号',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户姓名',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `age` varchar(20) DEFAULT NULL COMMENT '年龄',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `address` varchar(200) DEFAULT NULL COMMENT '住址',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `is_admin` char(1) DEFAULT NULL COMMENT '是否管理员',
  PRIMARY KEY (`user_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户信息';

-- 正在导出表  movie-ms.mms_user 的数据：~3 rows (大约)
INSERT INTO `mms_user` (`user_code`, `user_name`, `nick_name`, `age`, `sex`, `address`, `password`, `is_admin`) VALUES
	('admin', '管理员', '小王', '23', '男', '北京市', '123456', '1'),
	('huhu', 'huhu', 'huhu', '23', NULL, 'beijing', '123', '0'),
	('taozi', '涛子', '涛子', '25', NULL, '山东省', '123', '0'),
	('xiaoming', '李小明', '小明', '35', '男', '河南省郑州市', '123', '0'),
	('xiaoxiao', 'xiaoxiao', 'xiaoxiao', '45', NULL, '湖北', '123', '0'),
	('xiaozhu', '小猪', '小猪', '24', NULL, '湖南', '123', '0'),
	('xioxi', 'xioxi', 'xioxi', '22', NULL, '北京', '123', '0');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
