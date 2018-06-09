drop database superperson2;
create database superperson2;
use superperson2;

create table `address` (
	`id` int not null auto_increment,
    `number` varchar(10),
    `street` varchar(50),
    `city` varchar(50),
    `state` varchar(2),
    `zip` varchar(10),
    primary key (`id`)
);

create table `location` (
	`id` int not null auto_increment,
    `latitude` decimal(6,4),
    `longitude` decimal(7,4),
    `name` varchar(50),
    `address_id` int,
    primary key (`id`),
    constraint `fk_location_address_id`
		foreign key (`address_id`)
        references `address`(`id`)
);

create table `power` (
	`id` int not null auto_increment,
    `name` varchar(50),
    `description` varchar(1000),
    primary key (`id`)
);

create table `organization` (
	`id` int not null auto_increment,
    `name` varchar(50),
    `head` varchar(50),
    `description` varchar(1000),
    `location_id` int,
    primary key (`id`),
    constraint `fk_organization_location_id`
		foreign key (`location_id`)
        references `location`(`id`)
);

create table `superperson` (
	`id` int not null auto_increment,
    `name` varchar(50),
    `real_name` varchar(50),
    `date_of_birth` date,
	`description` varchar(1000),
    primary key (`id`)
);

create table `user` (
	`id` int not null auto_increment,
    `username` varchar(50),
    `email` varchar(50),
    `date_created` timestamp not null,
    primary key (`id`)
);

create table `sighting` (
	`id` int not null auto_increment,
    `timestamp` timestamp not null,
    `location_id` int not null,
    `user_id` int not null,
    primary key (`id`),
    constraint `fk_sighting_location_id`
		foreign key (`location_id`)
        references `location`(`id`),
	constraint `fk_sighting_user_id`
		foreign key (`user_id`)
        references `user`(`id`)
);

create table `superperson_organization` (
	`id` int not null auto_increment,
    `superperson_id` int not null,
    `organization_id` int not null,
    primary key (`id`),
    constraint `fk_superperson_organization_superperson_id`
		foreign key (`superperson_id`)
        references `superperson`(`id`),
	constraint `fk_superperson_organization_organization_id`
		foreign key (`organization_id`)
        references `organization`(`id`)
);

create table `superperson_power` (
	`id` int not null auto_increment,
    `superperson_id` int not null,
    `power_id` int not null,
    primary key (`id`),
    constraint `fk_superperson_power_superperson_id`
		foreign key (`superperson_id`)
        references `superperson`(`id`),
	constraint `fk_superperson_power_power_id`
		foreign key (`power_id`)
        references `power`(`id`)
);

create table `sighting_superperson` (
	`id` int not null auto_increment,
    `sighting_id` int not null,
    `superperson_id` int not null,
    primary key (`id`),
    constraint `fk_sighting_superperson_sighting_id`
		foreign key (`sighting_id`)
        references `sighting`(`id`),
	constraint `fk_sighting_superperson_superperson_id`
		foreign key (`superperson_id`)
        references `superperson`(`id`)
);
    
insert into `address` (`id`, `number`, `street`, `city`, `state`, `zip`) values
(1, '1234', 'Fake St', 'Fakeville', 'MT', '12345'),
(2, '100', '1st Ave', 'New York', 'NY', '10000'),
(3, '666', 'Evil St', 'Newark', 'NJ', '12345-1234');

insert into `location` (`id`, `latitude`, `longitude`, `name`, `address_id`) values
(1, 45.0000, 90.0000, 'Hero HQ', 2),
(2, -45.0000, 180.0000, 'Villain HQ', 3),
(3, 0.0000, 0.0000, 'Starbucks', 1);

insert into `organization` (`id`, `name`, `head`, `description`, `location_id`) values
(1, 'Hero League', 'Joe Biden', 'This is a heroic alliance of superheroes dedicated to protecting the world from evil and general unpleasantness.', 1),
(2, 'Villain Corp', 'Cardinal Richelieu', 'This is a dastardly league of evildoers bent on world domination and generally being problematic.', 2),
(3, 'Freemasons', '???', 'Who will help the widow\'s son?', 1);

insert into `power` (`id`, `name`, `description`) values
(1, 'Teleportation', 'User can move to another point in space at will.'),
(2, 'Strength', 'User possesses superhuman strength.'),
(3, 'Telekinesis', 'User can manipulate objects using their mind.');

insert into `superperson` (`id`, `name`, `real_name`, `date_of_birth`, `description`) values
(1, 'Captain Freeworld', 'John Smith', '1980-01-01', 'This superhero is the embodiment of patriotic jingoism and anti-communist paranoia. More heroic than he sounds!'),
(2, 'Teleporto', 'Malachi Malvado', '1950-01-01', 'This arch-villain is known for his ability to read minds and to teleport from place to place at will. He is also annoyingly hyper-critical and a bit too controlling.');

insert into `user` (`id`, `username`, `email`, `date_created`) values
(1, 'jdoe', 'johndoe@example.com', current_timestamp),
(2, 'jdoe2', 'janedoe@example.com', current_timestamp);

insert into `sighting` (`id`, `timestamp`, `location_id`, `user_id`) values
(1, current_timestamp, 1, 1),
(2, current_timestamp, 3, 2);

insert into `superperson_power` (`id`, `superperson_id`, `power_id`) values
(1, 1, 2),
(2, 2, 1),
(3, 2, 3);

insert into `superperson_organization` (`id`, `superperson_id`, `organization_id`) values
(1, 1, 1),
(2, 2, 2),
(3, 1, 3);

insert into `sighting_superperson` (`id`, `sighting_id`, `superperson_id`) values
(1, 1, 1),
(2, 2, 1),
(3, 2, 2);