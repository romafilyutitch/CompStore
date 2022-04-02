create table computer (id bigint not null, price double, brand varchar(255), purpose varchar(255), system varchar(255), graphics_id bigint, processor_id bigint, random_access_memory_id bigint, read_memory_id bigint, primary key (id))
create table graphics_unit (id bigint not null, price double, brand varchar(255), model varchar(255), type varchar(255), primary key (id))
create table processor (id bigint not null, price double, brand varchar(255), cores_amount integer, frequency double, series varchar(255), primary key (id))
create table random_access_memory (id bigint not null, price double, frequency integer, type varchar(255), volume integer, primary key (id))
create table read_memory (id bigint not null, price double, type varchar(255), volume integer, primary key (id))
create table user (id integer not null, password varchar(255), username varchar(255), primary key (id))
create sequence hibernate_sequence start with 1 increment by 1
alter table computer add constraint FK8w3w4ie6gw4aod3brngxkvgqe foreign key (graphics_id) references graphics_unit
alter table computer add constraint FK3jbp9x2se63ij7uswppbuiysx foreign key (processor_id) references processor
alter table computer add constraint FKgyi7x4nsg1f9jvhsaos2ae4s9 foreign key (random_access_memory_id) references random_access_memory
alter table computer add constraint FKeoeqlay6owwcfgt54h5qvgo3r foreign key (read_memory_id) references read_memory
