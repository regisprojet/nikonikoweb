USE nikonikoweb
INSERT INTO nikoniko (id,change_date,nikoniko_comment,isanonymous,log_date, satisfaction,project_id,user_id)
VALUES (1,"2009-12-31 23:59:59","coucou",false,"209-01-31 23:59:59",3,null,null),
	(2,"2009-12-31 23:59:59","coucou",false,"209-01-31 23:59:59",3,null,null),
	(3,"2009-12-31 23:59:59","coucou",false,"209-01-31 23:59:59",2,null,null),
	(4,"2009-12-31 23:59:59","coucou",false,"209-01-31 23:59:59",1,null,null),
	(5,"2019-12-31 23:59:59","coucou",false,"209-01-31 23:59:59",2,null,null),
	(6,"2509-12-31 23:59:59","coucou",false,"209-01-31 23:59:59",3,null,null),
	(7,"2609-12-31 23:59:59","coucou",false,"209-01-31 23:59:59",1,null,null),
	(8,"2019-12-31 23:59:59","coucou",false,"209-01-31 23:59:59",3,null,null),
	(9,"2019-12-31 23:59:59","coucou1",false,"209-01-31 23:59:59",3,null,null);

USE nikonikoweb
INSERT INTO project (id, end_date, name, start_date)
VALUES (1,"2019-12-31 23:59:59","project1","2009-12-31 23:59:59"),
	(2,"2019-12-31 23:59:59","project2","2091-01-31 23:59:59");
