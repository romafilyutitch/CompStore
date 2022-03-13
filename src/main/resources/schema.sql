create table computer(
    id int not null primary key auto_increment,
    year int not null,
    processor varchar(100) not null,
    color varchar(100) not null,
    ram varchar(100) not null,
    hdd varchar(100) not null,
    graphics varchar(100) not null,
    price decimal(10,2) not null
);
