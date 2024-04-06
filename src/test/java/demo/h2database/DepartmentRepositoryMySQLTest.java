package demo.h2database;

import demo.h2database.model.Department;
import demo.h2database.model.IncorrectDepartmentIDException;
import demo.h2database.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles({"dev"})
@SpringJUnitConfig
@Sql("../../../resources/schema.sql")
class DepartmentRepositoryMySQLTest {

    @Autowired
    DepartmentRepository repository;

    @Value("${spring.datasource.url}")
    private String db_url;

    @Value("${spring.datasource.username}")
    private String uid;

    @Value("${spring.datasource.password}")
    private String pwd;


    @BeforeEach
    public void setup() {
        try (
                Connection conn =
                        DriverManager.getConnection(db_url, uid, pwd)) {
            Statement stmt = conn.createStatement();

            String initSchema = "CREATE SCHEMA IF NOT EXISTS EMP_DEPT";
            String dropTableEmp = "drop table if exists emp";
            String dropTableDept = "drop table if exists dept";
            stmt.executeUpdate(initSchema);
            stmt.executeUpdate(dropTableEmp);
            stmt.executeUpdate(dropTableDept);
            String createTable = "CREATE TABLE DEPT " +
                    "(deptno INTEGER, " +
                    " dname VARCHAR(30), " +
                    " loc VARCHAR(30), " +
                    " PRIMARY KEY ( deptno ))";
            stmt.executeUpdate(createTable);
            System.out.println("Database created");
            String sqlInsertRow = "INSERT INTO DEPT VALUES (10,'ACCOUNTING','NEW YORK')";
            stmt.executeUpdate(sqlInsertRow);

            sqlInsertRow = "INSERT INTO DEPT VALUES (20,'RESEARCH','DALLAS')";
            stmt.executeUpdate(sqlInsertRow);

            sqlInsertRow = "INSERT INTO DEPT VALUES (30,'SALES','CHICAGO')";
            stmt.executeUpdate(sqlInsertRow);

            sqlInsertRow = "INSERT INTO DEPT VALUES(40,'OPERATIONS','BOSTON')";
            stmt.executeUpdate(sqlInsertRow);
            int rows = stmt.executeUpdate(sqlInsertRow);

            System.out.println("Inserted records into the table...");
        } catch (SQLException e) {
            System.out.println("Create went wrong" + e.getMessage());
            System.out.println("Inserts went wrong " + e.getMessage());
        }
    }

    @Test
    void findAccountingDepartment() throws SQLException {
        Department found = repository.getDepartment(10);
        assertEquals("ACCOUNTING", found.getName());
    }

    @Test
    @Sql(scripts = "../../../resources/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void deleteDepartment40() throws IncorrectDepartmentIDException {
        repository.deleteDepartment(40);
    }

    @Test
    @Sql({"../../../resources/schema.sql", "../../../resources/data.sql"})
        //@Sql({"src/test/resources/data.sql"})
    void findDepartment40() throws IncorrectDepartmentIDException, SQLException {
        Department found = repository.getDepartment(40);
        assertNotNull(found);

    }

    @Test
    void deleteNotExistingDepartment() {
        assertThrows(IncorrectDepartmentIDException.class, () -> repository.deleteDepartment(50));
    }
}