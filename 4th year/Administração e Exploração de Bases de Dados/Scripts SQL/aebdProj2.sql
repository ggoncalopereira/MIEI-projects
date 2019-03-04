--Depois criar as tabelas nesta conexão
--Tabela Tablespaces
CREATE TABLE Tablespaces AS(
SELECT df.tablespace_name TABLESPACE, df.total_space TOTAL_SPACE,
    fs.free_space FREE_SPACE, df.total_space_mb TOTAL_SPACE_MB,
    (df.total_space_mb - fs.free_space_mb) USED_SPACE_MB,
    fs.free_space_mb FREE_SPACE_MB,
    ROUND(100 * (fs.free_space / df.total_space),2) PCT_FREE
FROM (SELECT tablespace_name, SUM(bytes) TOTAL_SPACE,
      ROUND(SUM(bytes) / 1048576) TOTAL_SPACE_MB
      FROM dba_data_files
      GROUP BY tablespace_name) df,
     (SELECT tablespace_name, SUM(bytes) FREE_SPACE,
       ROUND(SUM(bytes) / 1048576) FREE_SPACE_MB
       FROM dba_free_space
       GROUP BY tablespace_name) fs
WHERE df.tablespace_name = fs.tablespace_name(+));

SELECT * FROM Tablespaces;

   
--Tabela Datafiles 
CREATE TABLE DatafilesT AS(
SELECT TABLESPACE_NAME, FILE_NAME, BYTES
    FROM DBA_DATA_FILES
);   

SELECT * FROM DatafilesT;

--Tabela Users
CREATE TABLE Users AS(
SELECT process, username, sid, osuser, terminal, machine
    FROM sys.v_$session
);

SELECT * FROM Users;
    
--Tabela Memory
CREATE TABLE MemoryT AS(
SELECT username, sess.SID, SUM (VALUE) sess_mem
    FROM v$session sess, v$sesstat stat, v$statname NAME
   WHERE sess.SID = stat.SID
     AND stat.statistic# = NAME.statistic#
     AND NAME.NAME LIKE 'session % memory'
GROUP BY username, sess.SID
);
    
SELECT * FROM MemoryT;    
    
--Sessions
CREATE TABLE Sessions AS(
    SELECT  NVL(s.username, '(oracle)') AS username,
            s.osuser,
            s.sid,
            s.serial#,
            p.spid,
            s.status,
            s.service_name,
            s.module,
            s.machine,
            s.program,
            TO_CHAR(s.logon_Time,'DD-MON-YYYY HH24:MI:SS') AS logon_time,
            s.last_call_et AS last_call_et_secs
    FROM    sys.v_$session s,
            sys.v_$process p
    WHERE  s.paddr = p.addr
); 

SELECT * FROM Sessions;



--CPU
CREATE TABLE CpuUsage AS(
select distinct ss.osuser,
       ss.username,
       ss.SID,
       ss.serial#,
       VALUE/100 cpu_usage_seconds
from sys.v_$session ss, 
     sys.v_$sesstat se, 
     sys.v_$statname sn
where
     se.STATISTIC# = sn.STATISTIC#
and
     NAME like '%CPU used by this session%'
and
     se.SID = ss.SID
and 
     ss.username is not null);

SELECT * FROM CPUUSAGE;
