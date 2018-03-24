@echo off
for /f "tokens=1-4 delims=/ " %%i in ("%date%") do (
  set dow=%%i
  set month=%%j
  set day=%%k
  set year=%%l
)
set datestr=%dow%_%month%
echo datestr is %datestr%
 
set BACKUP_FILE=escoladabiblia_%datestr%.backup
echo backup file name is %BACKUP_FILE%
SET PGPASSWORD=@post!EB
echo on0
pg_dump -i -h localhost -p 5432 -U postgres -F c -b -v -f %BACKUP_FILE% escoladabiblia