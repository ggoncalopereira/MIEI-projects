--Tablespace
column "Tablespace" format a13
column "Used MB"    format 99,999,999
column "Free MB"    format 99,999,999
column "Total MB"   format 99,999,999
select
   fs.tablespace_name                          "Tablespace",
   (df.totalspace - fs.freespace)              "Used MB",
   fs.freespace                                "Free MB",
   df.totalspace                               "Total MB",
   round(100 * (fs.freespace / df.totalspace)) "Pct. Free"
from
   (select
      tablespace_name,
      round(sum(bytes) / 1048576) TotalSpace
   from
      dba_data_files
   group by
      tablespace_name
   ) df,
   (select
      tablespace_name,
      round(sum(bytes) / 1048576) FreeSpace
   from
      dba_free_space
   group by
      tablespace_name
   ) fs
where
   df.tablespace_name = fs.tablespace_name;
   
   
--Datafiles 
SELECT TABLESPACE_NAME, FILE_NAME, BYTES
    FROM DBA_DATA_FILES;
    
--Users
SELECT process, username, sid, osuser, terminal, machine
    FROM v$session
    ORDER BY process;
    
--Memory
SELECT username, sess.SID, SUM (VALUE) sess_mem
    FROM v$session sess, v$sesstat stat, v$statname NAME
   WHERE sess.SID = stat.SID
     AND stat.statistic# = NAME.statistic#
     AND NAME.NAME LIKE 'session % memory'
GROUP BY username, sess.SID;
    
--Sessions
SELECT NVL(s.username, '(oracle)') AS username,
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
FROM   v$session s,
       v$process p
WHERE  s.paddr = p.addr
ORDER BY s.username, s.osuser;  
   
--CPU
select 
   ss.username,
   se.SID,
   ss.serial#,
   VALUE/100 cpu_usage_seconds
from
   v$session ss, 
   v$sesstat se, 
   v$statname sn
where
   se.STATISTIC# = sn.STATISTIC#
and
   NAME like '%CPU used by this session%'
and
   se.SID = ss.SID
and 
   ss.status='ACTIVE'
and 
   ss.username is not null
order by VALUE desc;

