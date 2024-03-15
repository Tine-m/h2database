DROP SCHEMA EMP_DEPT;
CREATE SCHEMA EMP_DEPT;
DROP TABLE DEPT;
CREATE TABLE DEPT (
	DEPTNO INTEGER, 
	DNAME VARCHAR(30), 
	LOC VARCHAR(30), 
	PRIMARY KEY (DEPTNO)
);
INSERT INTO
      DEPT (DEPTNO,DNAME,LOC)
  VALUES
      (10,'ACCOUNTING','NEW YORK'),
      (20,'RESEARCH','DALLAS'),
      (30,'SALES','CHICAGO'),
      (40,'OPERATIONS','BOSTON'),
      (50, 'A', 'A');