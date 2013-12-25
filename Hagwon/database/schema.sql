CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `pictures` (
	`p_id` int(11) NOT NULL AUTO_INCREMENT,
	`url` varchar(220) NOT NULL,
	`u_id` int(11),
	PRIMARY KEY(`p_id`),
	FOREIGN KEY (`u_id`) REFERENCES users(`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;