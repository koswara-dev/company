CREATE DATABASE companydb
ON PRIMARY (NAME = companydb_data, FILENAME = '/var/opt/mssql/data/companydb_data.mdf')
LOG ON (NAME = companydb_log, FILENAME = '/var/opt/mssql/data/companydb_log.ldf')
;
ALTER DATABASE companydb SET RECOVERY SIMPLE;
