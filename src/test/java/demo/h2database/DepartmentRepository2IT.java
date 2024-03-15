package demo.h2database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DepartmentRepository2IT {

    @Autowired
    DepartmentRepository repository;
/*

    @Value("${spring.datasource.url}")
    private String db_url;

    @Value("${spring.datasource.username}")
    private String uid;

    @Value("${spring.datasource.password}")
    private String pwd;


    @BeforeEach
    public void setup()  {

        //VIRKER MED DENNE embedded local connection
        // #spring.datasource.url=jdbc:h2:./test
        try (
                Connection conn =
                        DriverManager.getConnection(db_url, uid, pwd)) {
            Statement stmt = conn.createStatement();
            String sql = "DROP TABLE if exists DEPT; CREATE TABLE DEPT " +
                    "(deptno INTEGER, " +
                    " dname VARCHAR(30), " +
                    " loc VARCHAR(30), " +
                    " PRIMARY KEY ( deptno ))";
            stmt.executeUpdate(sql);
            System.out.println("Create ALL GOOD");

            // STEP 3: Execute a query
            //stmt = conn.createStatement();
            String sql2 = "INSERT INTO DEPT VALUES (10,'ACCOUNTING2','NEW YORK')";
            stmt.executeUpdate(sql2);

            sql2 = "INSERT INTO DEPT VALUES (20,'RESEARCH2','DALLAS')";
            stmt.executeUpdate(sql2);

            sql2 = "INSERT INTO DEPT VALUES (30,'SALES2','CHICAGO')";
            stmt.executeUpdate(sql2);

            sql2 = "INSERT INTO DEPT VALUES(40,'OPERATIONS2','BOSTON')";
            stmt.executeUpdate(sql2);

            sql2 = "INSERT INTO DEPT VALUES(50,'HJEMME','LYNGBY')";
            int rows = stmt.executeUpdate(sql2);

            System.out.println("Result "+ rows);
            System.out.println("Inserted records into the table...");
        } catch (SQLException e) {
            System.out.println("Create went wrong" + e.getMessage());
            System.out.println("Inserts went wrong " + e.getMessage());
        }
    }*/
    /*@Test
    void getDepartment10() throws SQLException {
        // data hentes nu pga. ændring i proerty fil i c:users/tima *.sql
        // in-memory named
        // i roden af projekt *.db
        // https://www.h2database.com/html/features.html
        // spring.datasource.url=jdbc:h2:mem:test;INIT=runscript from '~/schema.sql'\\;runscript from '~/data.sql'
        Department found = repository.getDepartment(10);
        assertEquals("ACCOUNTING", found.getName());
    }

    @Test
    void getDepartment20() throws SQLException {
        // data hentes nu pga. ændring i proerty fil i c:users/tima *.sql
        // in-memory named
        // https://www.h2database.com/html/features.html
        // spring.datasource.url=jdbc:h2:mem:test;INIT=runscript from '~/schema.sql'\\;runscript from '~/data.sql'
        Department found = repository.getDepartment(20);
        assertEquals("RESEARCH", found.getName());
    }
/*
    @Test
    void getDepartment50() throws SQLException {
        // data hentes nu pga. ændring i proerty fil i c:users/tima *.sql
        // in-memory named
        // https://www.h2database.com/html/features.html
        // spring.datasource.url=jdbc:h2:mem:test;INIT=runscript from '~/schema.sql'\\;runscript from '~/data.sql'
        Department found = repository.getDepartment(50);
        assertEquals("HJEMME", found.getName());
    }*/
}