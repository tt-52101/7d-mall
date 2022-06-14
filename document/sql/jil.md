# 修改表：

```mysql
mysql> select
    ->      
    ->      
    ->     id, name, growth_point, default_status, free_freight_point, comment_growth_point, 
    ->     priviledge_free_freight, priviledge_sign_in, priviledge_comment, priviledge_promotion, 
    ->     priviledge_member_price, priviledge_birthday, note
    ->    
    ->     from ums_member_level
    ->      
    ->        
    ->      WHERE (  default_status = 1 )
    -> ;
+----+------+--------------+----------------+--------------------+----------------------+-------------------------+--------------------+--------------------+----------------------+-------------------------+---------------------+------+
| id | name | growth_point | default_status | free_freight_point | comment_growth_point | priviledge_free_freight | priviledge_sign_in | priviledge_comment | priviledge_promotion | priviledge_member_price | priviledge_birthday | note |
+----+------+--------------+----------------+--------------------+----------------------+-------------------------+--------------------+--------------------+----------------------+-------------------------+---------------------+------+
|  4 | ???? |            1 |              1 |             199.00 |                   20 |                       1 |                  1 |                  1 |                    1 |                       0 |                   0 | NULL |
+----+------+--------------+----------------+--------------------+----------------------+-------------------------+--------------------+--------------------+----------------------+-------------------------+---------------------+------+
1 row in set (0.00 sec)

mysql> expalin select
    ->      
    ->      
    ->     id, name, growth_point, default_status, free_freight_point, comment_growth_point, 
    ->     priviledge_free_freight, priviledge_sign_in, priviledge_comment, priviledge_promotion, 
    ->     priviledge_member_price, priviledge_birthday, note
    ->    
    ->     from ums_member_level
    ->      
    ->        
    ->      WHERE (  default_status = 1 );
ERROR 1064 (42000): You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'expalin select
     
     
    id, name, growth_point, default_status, free_frei' at line 1
mysql> 
mysql> 
mysql> 
mysql> explain  SELECT 
    ->     id,
    ->     name,
    ->     growth_point,
    ->     default_status,
    ->     free_freight_point,
    ->     comment_growth_point,
    ->     priviledge_free_freight,
    ->     priviledge_sign_in,
    ->     priviledge_comment,
    ->     priviledge_promotion,
    ->     priviledge_member_price,
    ->     priviledge_birthday,
    ->     note
    -> FROM
    ->     ums_member_level
    -> WHERE
    ->     (default_status = 1);
+----+-------------+------------------+------------+------+---------------+------+---------+------+------+----------+-------------+
| id | select_type | table            | partitions | type | possible_keys | key  | key_len | ref  | rows | filtered | Extra       |
+----+-------------+------------------+------------+------+---------------+------+---------+------+------+----------+-------------+
|  1 | SIMPLE      | ums_member_level | NULL       | ALL  | NULL          | NULL | NULL    | NULL |    4 |    25.00 | Using where |
+----+-------------+------------------+------------+------+---------------+------+---------+------+------+----------+-------------+
1 row in set, 1 warning (0.00 sec)

mysql> 
mysql> 
mysql> 
mysql> 
mysql> ALTER TABLE ums_member_level ADD INDEX index_default_status (default_status);
Query OK, 0 rows affected (0.00 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> 
mysql> 
mysql> explain  SELECT 
    ->     id,
    ->     name,
    ->     growth_point,
    ->     default_status,
    ->     free_freight_point,
    ->     comment_growth_point,
    ->     priviledge_free_freight,
    ->     priviledge_sign_in,
    ->     priviledge_comment,
    ->     priviledge_promotion,
    ->     priviledge_member_price,
    ->     priviledge_birthday,
    ->     note
    -> FROM
    ->     ums_member_level
    -> WHERE
    ->     (default_status = 1);
+----+-------------+------------------+------------+------+----------------------+----------------------+---------+-------+------+----------+-------+
| id | select_type | table            | partitions | type | possible_keys        | key                  | key_len | ref   | rows | filtered | Extra |
+----+-------------+------------------+------------+------+----------------------+----------------------+---------+-------+------+----------+-------+
|  1 | SIMPLE      | ums_member_level | NULL       | ref  | index_default_status | index_default_status | 5       | const |    1 |   100.00 | NULL  |
+----+-------------+------------------+------------+------+----------------------+----------------------+---------+-------+------+----------+-------+
1 row in set, 1 warning (0.00 sec)

mysql> 
```

## 日志：
```mysql
mysql> show full processlist;
+-------+--------+----------------+------+---------+------+-----------+-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Id    | User   | Host           | db   | Command | Time | State     | Info                                                                                                                                                                                                                                                                                                                                    |
+-------+--------+----------------+------+---------+------+-----------+-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|     6 | reader | 10.0.1.1:56418 | mall | Sleep   |    9 |           | NULL                                                                                                                                                                                                                                                                                                                                    |
|     7 | reader | 10.0.3.1:45124 | mall | Sleep   |   19 |           | NULL                                                                                                                                                                                                                                                                                                                                    |
|     9 | reader | 10.0.1.1:44488 | mall | Sleep   |    9 |           | NULL                                                                                                                                                                                                                                                                                                                                    |
|    11 | reader | 10.0.1.1:36436 | mall | Sleep   |   19 |           | NULL                                                                                                                                                                                                                                                                                                                                    |
|  6398 | reader | 10.0.1.1:57738 | mall | Sleep   |    0 |           | NULL                                                                                                                                                                                                                                                                                                                                    |
|  7211 | reader | 10.0.3.1:46676 | mall | Query   |    0 | starting  | select
     
     
    id, member_level_id, username, password, nickname, phone, status, create_time, icon, 
    gender, birthday, city, job, personalized_signature, source_type, integration, growth, 
    luckey_count, history_integration
   
    from ums_member
     
       
     WHERE (  username = '7dgroup_2_15171881992' ) |
| 10771 | root   | localhost      | mall | Sleep   | 2574 |           | NULL                                                                                                                                                                                                                                                                                                                                    |
| 10884 | reader | 10.0.1.1:52870 | mall | Sleep   |  509 |           | NULL                                                                                                                                                                                                                                                                                                                                    |
| 10885 | reader | 10.0.1.1:52872 | mall | Sleep   |    0 |           | NULL                                                                                                                                                                                                                                                                                                                                    |
| 10886 | reader | 10.0.1.1:52874 | mall | Sleep   |    0 |           | NULL                                                                                                                                                                                                                                                                                                                                    |
| 10887 | reader | 10.0.1.1:52884 | mall | Sleep   |    0 |           | NULL                                                                                                                                                                                                                                                                                                                                    |
| 10888 | reader | 10.0.1.1:52890 | mall | Sleep   |    0 |           | NULL                                                                                                                                                                                                                                                                                                                                    |
| 10889 | reader | 10.0.1.1:52894 | mall | Sleep   |    0 |           | NULL                                                                                                                                                                                                                                                                                                                                    |
| 10890 | reader | 10.0.1.1:52896 | mall | Sleep   |    0 |           | NULL                                                                                                                                                                                                                                                                                                                                    |
| 10891 | reader | 10.0.1.1:52898 | mall | Sleep   |    0 |           | NULL                                                                                                                                                                                                                                                                                                                                    |
| 10892 | reader | 10.0.3.1:60608 | mall | Sleep   |    0 |           | NULL                                                                                                                                                                                                                                                                                                                                    |
| 10893 | reader | 10.0.1.1:52904 | mall | Sleep   |    0 |           | NULL                                                                                                                                                                                                                                                                                                                                    |
| 10896 | reader | 10.0.1.1:53102 | mall | Query   |    0 | query end | insert into ums_member_login_log (member_id, create_time, ip, 
      city, login_type, province
      )
    values (1060182, '2022-06-05 10:23:46.571', '172.31.184.237', 
      null, null, null
      )                                                                                                                               |
| 10899 | reader | 10.0.3.1:60914 | mall | Sleep   |    0 |           | NULL                                                                                                                                                                                                                                                                                                                                    |
| 10915 | root   | localhost      | mall | Query   |    0 | starting  | show full processlist                                                                                                                                                                                                                                                                                                                   |
| 10953 | reader | 10.0.3.1:37610 | mall | Sleep   |    0 |           | NULL                                                                                                                                                                                                                                                                                                                                    |
| 10954 | reader | 10.0.3.1:37746 | mall | Sleep   |    0 |           | NULL                                                                                                                                                                                                                                                                                                                                    |
| 10955 | reader | 10.0.3.1:37748 | mall | Sleep   |    0 |           | NULL                                                                                                                                                                                                                                                                                                                                    |
| 10956 | reader | 10.0.3.1:37750 | mall | Sleep   |   16 |           | NULL                                                                                                                                                                                                                                                                                                                                    |
| 10957 | reader | 10.0.3.1:37752 | mall | Sleep   |    0 |           | NULL                                                                                                                                                                                                                                                                                                                                    |
| 10958 | reader | 10.0.3.1:37754 | mall | Sleep   |    0 |           | NULL                                                                                                                                                                                                                                                                                                                                    |
| 10961 | reader | 10.0.3.1:37936 | mall | Sleep   |    0 |           | NULL                                                                                                                                                                                                                                                                                                                                    |
+-------+--------+----------------+------+---------+------+-----------+-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
27 rows in set (0.00 sec)

mysql> show full processlist;
+-------+--------+----------------+------+---------+------+-------------------+----------------------------------------+
| Id    | User   | Host           | db   | Command | Time | State             | Info                                   |
+-------+--------+----------------+------+---------+------+-------------------+----------------------------------------+
|     6 | reader | 10.0.1.1:56418 | mall | Sleep   |   11 |                   | NULL                                   |
|     7 | reader | 10.0.3.1:45124 | mall | Sleep   |    1 |                   | NULL                                   |
|     9 | reader | 10.0.1.1:44488 | mall | Sleep   |   11 |                   | NULL                                   |
|    11 | reader | 10.0.1.1:36436 | mall | Sleep   |    1 |                   | NULL                                   |
|  6398 | reader | 10.0.1.1:57738 | mall | Query   |    0 | Sending to client | SELECT @@session.transaction_read_only |
|  7211 | reader | 10.0.3.1:46676 | mall | Sleep   |    0 |                   | NULL                                   |
| 10771 | root   | localhost      | mall | Sleep   | 2576 |                   | NULL                                   |
| 10884 | reader | 10.0.1.1:52870 | mall | Sleep   |  511 |                   | NULL                                   |
| 10885 | reader | 10.0.1.1:52872 | mall | Sleep   |    0 |                   | NULL                                   |
| 10886 | reader | 10.0.1.1:52874 | mall | Sleep   |    0 |                   | NULL                                   |
| 10887 | reader | 10.0.1.1:52884 | mall | Sleep   |    0 |                   | NULL                                   |
| 10888 | reader | 10.0.1.1:52890 | mall | Sleep   |    0 |                   | NULL                                   |
| 10889 | reader | 10.0.1.1:52894 | mall | Sleep   |    0 |                   | NULL                                   |
| 10890 | reader | 10.0.1.1:52896 | mall | Sleep   |    0 |                   | NULL                                   |
| 10891 | reader | 10.0.1.1:52898 | mall | Sleep   |    0 |                   | NULL                                   |
| 10892 | reader | 10.0.3.1:60608 | mall | Sleep   |    1 |                   | NULL                                   |
| 10893 | reader | 10.0.1.1:52904 | mall | Sleep   |    0 |                   | NULL                                   |
| 10896 | reader | 10.0.1.1:53102 | mall | Sleep   |    0 |                   | NULL                                   |
| 10899 | reader | 10.0.3.1:60914 | mall | Sleep   |    0 |                   | NULL                                   |
| 10915 | root   | localhost      | mall | Query   |    0 | starting          | show full processlist                  |
| 10953 | reader | 10.0.3.1:37610 | mall | Sleep   |    2 |                   | NULL                                   |
| 10954 | reader | 10.0.3.1:37746 | mall | Sleep   |    0 |                   | NULL                                   |
| 10955 | reader | 10.0.3.1:37748 | mall | Sleep   |    0 |                   | NULL                                   |
| 10956 | reader | 10.0.3.1:37750 | mall | Sleep   |   18 |                   | NULL                                   |
| 10957 | reader | 10.0.3.1:37752 | mall | Sleep   |    1 |                   | NULL                                   |
| 10958 | reader | 10.0.3.1:37754 | mall | Sleep   |    0 |                   | NULL                                   |
| 10961 | reader | 10.0.3.1:37936 | mall | Sleep   |    0 |                   | NULL                                   |
+-------+--------+----------------+------+---------+------+-------------------+----------------------------------------+
27 rows in set (0.00 sec)

mysql> show full processlist;
+-------+--------+----------------+------+---------+------+----------+-----------------------+
| Id    | User   | Host           | db   | Command | Time | State    | Info                  |
+-------+--------+----------------+------+---------+------+----------+-----------------------+
|     6 | reader | 10.0.1.1:56418 | mall | Sleep   |   13 |          | NULL                  |
|     7 | reader | 10.0.3.1:45124 | mall | Sleep   |    3 |          | NULL                  |
|     9 | reader | 10.0.1.1:44488 | mall | Sleep   |   13 |          | NULL                  |
|    11 | reader | 10.0.1.1:36436 | mall | Sleep   |    3 |          | NULL                  |
|  6398 | reader | 10.0.1.1:57738 | mall | Sleep   |    0 |          | NULL                  |
|  7211 | reader | 10.0.3.1:46676 | mall | Sleep   |    0 |          | NULL                  |
| 10771 | root   | localhost      | mall | Sleep   | 2578 |          | NULL                  |
| 10884 | reader | 10.0.1.1:52870 | mall | Sleep   |  513 |          | NULL                  |
| 10885 | reader | 10.0.1.1:52872 | mall | Sleep   |    0 |          | NULL                  |
| 10886 | reader | 10.0.1.1:52874 | mall | Sleep   |    0 |          | NULL                  |
| 10887 | reader | 10.0.1.1:52884 | mall | Sleep   |    0 |          | NULL                  |
| 10888 | reader | 10.0.1.1:52890 | mall | Sleep   |    0 |          | NULL                  |
| 10889 | reader | 10.0.1.1:52894 | mall | Sleep   |    0 |          | NULL                  |
| 10890 | reader | 10.0.1.1:52896 | mall | Sleep   |    0 |          | NULL                  |
| 10891 | reader | 10.0.1.1:52898 | mall | Sleep   |    0 |          | NULL                  |
| 10892 | reader | 10.0.3.1:60608 | mall | Sleep   |    1 |          | NULL                  |
| 10893 | reader | 10.0.1.1:52904 | mall | Sleep   |    0 |          | NULL                  |
| 10896 | reader | 10.0.1.1:53102 | mall | Sleep   |    1 |          | NULL                  |
| 10899 | reader | 10.0.3.1:60914 | mall | Sleep   |    0 |          | NULL                  |
| 10915 | root   | localhost      | mall | Query   |    0 | starting | show full processlist |
| 10953 | reader | 10.0.3.1:37610 | mall | Sleep   |    0 |          | NULL                  |
| 10954 | reader | 10.0.3.1:37746 | mall | Sleep   |    1 |          | NULL                  |
| 10955 | reader | 10.0.3.1:37748 | mall | Sleep   |    0 |          | NULL                  |
| 10956 | reader | 10.0.3.1:37750 | mall | Sleep   |    1 |          | NULL                  |
| 10957 | reader | 10.0.3.1:37752 | mall | Sleep   |    1 |          | NULL                  |
| 10958 | reader | 10.0.3.1:37754 | mall | Sleep   |    0 |          | NULL                  |
| 10961 | reader | 10.0.3.1:37936 | mall | Sleep   |    1 |          | NULL                  |
+-------+--------+----------------+------+---------+------+----------+-----------------------+
27 rows in set (0.00 sec)

mysql> expalin insert into ums_member_login_log (member_id, create_time, ip, 
    ->       city, login_type, province
    ->       )
    ->     values (1060182, '2022-06-05 10:23:46.571', '172.31.184.237', 
    ->       null, null, null
    ->       ) ;
ERROR 1064 (42000): You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'expalin insert into ums_member_login_log (member_id, create_time, ip, 
      cit' at line 1
mysql> 
mysql> 
mysql> 
mysql> explain insert into ums_member_login_log (member_id, create_time, ip, 
    ->       city, login_type, province
    ->       )
    ->     values (1060182, '2022-06-05 10:23:46.571', '172.31.184.237', 
    ->       null, null, null
    ->       ) ;
+----+-------------+----------------------+------------+------+---------------+------+---------+------+------+----------+-------+
| id | select_type | table                | partitions | type | possible_keys | key  | key_len | ref  | rows | filtered | Extra |
+----+-------------+----------------------+------------+------+---------------+------+---------+------+------+----------+-------+
|  1 | INSERT      | ums_member_login_log | NULL       | ALL  | NULL          | NULL | NULL    | NULL | NULL |     NULL | NULL  |
+----+-------------+----------------------+------------+------+---------------+------+---------+------+------+----------+-------+
1 row in set (0.00 sec)

mysql> 
mysql> 
mysql> desc ums_member_login_log;
+-------------+-------------+------+-----+---------+----------------+
| Field       | Type        | Null | Key | Default | Extra          |
+-------------+-------------+------+-----+---------+----------------+
| id          | bigint(20)  | NO   | PRI | NULL    | auto_increment |
| member_id   | bigint(20)  | YES  |     | NULL    |                |
| create_time | datetime    | YES  |     | NULL    |                |
| ip          | varchar(64) | YES  |     | NULL    |                |
| city        | varchar(64) | YES  |     | NULL    |                |
| login_type  | int(11)     | YES  |     | NULL    |                |
| province    | varchar(64) | YES  |     | NULL    |                |
+-------------+-------------+------+-----+---------+----------------+
7 rows in set (0.00 sec)

mysql> explain select * from ums_member_login_log where id = '1060182'
    -> ;
+----+-------------+----------------------+------------+-------+---------------+---------+---------+-------+------+----------+-------+
| id | select_type | table                | partitions | type  | possible_keys | key     | key_len | ref   | rows | filtered | Extra |
+----+-------------+----------------------+------------+-------+---------------+---------+---------+-------+------+----------+-------+
|  1 | SIMPLE      | ums_member_login_log | NULL       | const | PRIMARY       | PRIMARY | 8       | const |    1 |   100.00 | NULL  |
+----+-------------+----------------------+------------+-------+---------------+---------+---------+-------+------+----------+-------+
1 row in set, 1 warning (0.00 sec)

mysql> explain select * from ums_member_login_log where member_id = '1060182'
    -> ;
+----+-------------+----------------------+------------+------+---------------+------+---------+------+---------+----------+-------------+
| id | select_type | table                | partitions | type | possible_keys | key  | key_len | ref  | rows    | filtered | Extra       |
+----+-------------+----------------------+------------+------+---------------+------+---------+------+---------+----------+-------------+
|  1 | SIMPLE      | ums_member_login_log | NULL       | ALL  | NULL          | NULL | NULL    | NULL | 1142704 |    10.00 | Using where |
+----+-------------+----------------------+------------+------+---------------+------+---------+------+---------+----------+-------------+
1 row in set, 1 warning (0.00 sec)

mysql> ;2Dmysql> ;
ERROR: 
No query specified

```
## 添加索引：
```mysql
mysql> explain select * from ums_member_login_log where member_id = '1060182';
+----+-------------+----------------------+------------+------+---------------+------+---------+------+---------+----------+-------------+
| id | select_type | table                | partitions | type | possible_keys | key  | key_len | ref  | rows    | filtered | Extra       |
+----+-------------+----------------------+------------+------+---------------+------+---------+------+---------+----------+-------------+
|  1 | SIMPLE      | ums_member_login_log | NULL       | ALL  | NULL          | NULL | NULL    | NULL | 1179480 |    10.00 | Using where |
+----+-------------+----------------------+------------+------+---------------+------+---------+------+---------+----------+-------------+
1 row in set, 1 warning (0.00 sec)

mysql> ALTER TABLE ums_member_login_log ADD INDEX index_member_id (member_id);
Query OK, 0 rows affected (1.50 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> explain select * from ums_member_login_log where member_id = '1060182';
+----+-------------+----------------------+------------+------+-----------------+-----------------+---------+-------+------+----------+-----------------------+
| id | select_type | table                | partitions | type | possible_keys   | key             | key_len | ref   | rows | filtered | Extra                 |
+----+-------------+----------------------+------------+------+-----------------+-----------------+---------+-------+------+----------+-----------------------+
|  1 | SIMPLE      | ums_member_login_log | NULL       | ref  | index_member_id | index_member_id | 9       | const |    1 |   100.00 | Using index condition |
+----+-------------+----------------------+------------+------+-----------------+-----------------+---------+-------+------+----------+-----------------------+
1 row in set, 1 warning (0.00 sec)

mysql> 
```
