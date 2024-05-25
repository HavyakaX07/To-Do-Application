CREATE table USER_LOGIN_DETAILS (

userID bigint not null,
userName varchar(255) unique,
primary key(userID)
);

INSERT INTO USER_LOGIN_DETAILS values(1001,'root');

CREATE SEQUENCE sequence_todo_entity_id START WITH 1;

create table todo_entity (
    task_id bigint DEFAULT nextval('sequence_todo_entity_id'),
    task_Name VARCHAR,
    task_Description VARCHAR,
    target_Date Date,
    done boolean,
    user_Id bigint,
    primary key(task_id)
);