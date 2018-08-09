--date extraction
SELECT          a.activity_type, 
                COUNT(a.activity_type) AS activity_type_count   
FROM            t_activity_log a
WHERE           Extract ( epoch FROM a.event_date_time ) >= extract ( epoch FROM '2018-05-16' ::timestamptz )
AND             extract ( epoch FROM a.event_date_time ) <= extract ( epoch FROM '2018-05-18' ::timestamptz )
GROUP By        a.activity_type 
ORDER BY        activity_type_count DESC
FOR READ only;

--2nd highest: 
select id from t_activity_log where id in (select id from t_activity_log order by id desc limit 2) limit 1

--get top occurrence event 
select count(activity_type) from t_activity_log group by activity_type order by count(activity_type) desc limit 1

--get top occurrence event + event type 
select count(activity_type), activity_type from t_activity_log group by activity_type order by count(activity_type) desc

--get all duplication event record in the order 
select count(activity_type), activity_type from t_activity_log group by activity_type having count(activity_type) > 1 order by count(activity_type) desc

--Delete table data with dependency
truncate t_exam cascade 

--Get Unmatched records from two tables (note : here join column is "place")
select * from table1 t1 full outer join table2 t2 on t1.place = t2.place where t1.place is null or t2.place is null

--Get Unmatched records from left table only (note : here join column is "place") here the right table join column should be is null check 
select * from table1 t1 full outer join table2 t2 on t1.place = t2.place where t2.place is null

--Get Unmatched records from right table only (note : here join column is "place") here the left table join column should be is null check 
select * from table1 t1 full outer join table2 t2 on t1.place = t2.place where t1.place is null

--or 
--unmatched records from two tables (note : here join column is "place")
select t1.name, t1.place from table1 as t1 where t1.place not in (select place from table2) 
UNION 
select t2.name, t2.place from table2 as t2 where t2.place not in (select place from table1) 

--or 

select t1.name, t1.place from table1 as t1 left join table2 as t2 on t1.place = t2.place where t2.place is null 
UNION ALL 
select t2.name, t2.place from table2 as t2 left join table1 as t1 on t1.place = t2.place where t1.place is null 


