

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for questions
-- ----------------------------
DROP TABLE IF EXISTS `questions`;
CREATE TABLE `questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;


INSERT INTO `questions` VALUES ('1', 'what is your favorite color?');
INSERT INTO `questions` VALUES ('2', 'where is your hometown?');
INSERT INTO `questions` VALUES ('3', 'what is your pet name?');
INSERT INTO `questions` VALUES ('4', 'where is your mother hometown?');
INSERT INTO `questions` VALUES ('5', 'what is your first school?');
