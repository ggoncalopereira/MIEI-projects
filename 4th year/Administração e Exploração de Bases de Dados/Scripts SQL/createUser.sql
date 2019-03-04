--Criação do novo user para o grupo no SYS.ORCL
--Temos de criar tabelas com as métricas: Tablespaces, Datafiles, Users, Memory, Sessions, CPU


CREATE TABLESPACE aebd_tables_project
    DATAFILE '\u01\app\oracle\oradata\orcl12\orcl\aebd_tables_project.dbf'
    SIZE 100M;
    
CREATE TEMPORARY TABLESPACE aebd_temp_project
    TEMPFILE '\u01\app\oracle\oradata\orcl12\orcl\aebd_temp_project.dbf'
    SIZE 50M
    AUTOEXTEND ON;

CREATE USER aebdProj
    IDENTIFIED BY aebd
    DEFAULT TABLESPACE aebd_tables_project 
    TEMPORARY TABLESPACE aebd_temp_project;
    
GRANT CONNECT TO aebdProj;

GRANT create session, create table, create view, create procedure, create synonym,
      alter any table, alter any procedure,
      drop any table, drop any view, drop any procedure, drop any synonym
      to aebdProj;

ALTER USER aebdProj QUOTA 100M ON AEBD_TABLES_PROJECT;

/*Verificar se o aebdProj funciona*/
SELECT username, account_status FROM dba_users;

/*Grants necessários para o aebdProj funcionar*/
GRANT ALL ON DBA_DATA_FILES TO aebdProj;
GRANT ALL ON dba_free_space TO aebdProj;
GRANT ALL ON v TO aebdProj;
GRANT ALL ON CpuUsage TO aebdProj;
GRANT ALL ON Sessions TO aebdProj;
GRANT ALL ON Users TO aebdProj;
GRANT ALL ON Tablespaces To aebdProj;
grant select on V_$SESSION to aebdProj;
grant select on V_$process to aebdProj;
grant select on v_$sesstat to aebdProj; 
grant select on v_$statname to aebdProj;

/*Testes para verificar tabelas do aebdProj*/
CREATE SEQUENCE s START WITH 500 INCREMENT BY 1;

CREATE TABLE t2 AS SELECT LEVEL id FROM dual CONNECT BY LEVEL < = 8;

UPDATE t2 SET ID = s.nextval;

SELECT * FROM t2;

CREATE TABLE t3 AS SELECT LEVEL id FROM dual CONNECT BY LEVEL < = 8;
UPDATE t3 SET ID = s.nextval;
SELECT * from t3;

GRANT ALL ON t3 TO aebdProj;


