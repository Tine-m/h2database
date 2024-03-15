package demo.h2database;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentRepositoryTest {

    /*
    application.properties i src/test/resources overrider
    automatisk den i src/main/resources
    */

    @Autowired
    DepartmentRepository repository;

    @Test
    void getDepartment10() throws SQLException {
        Department found = repository.getDepartment(10);
        assertEquals("ACCOUNTING", found.getName());
    }

    @Test
    void getDepartment20() throws SQLException {
        Department found = repository.getDepartment(20);
        assertEquals("RESEARCH", found.getName());
    }

    @Test
    void getDepartment50() throws SQLException {
        //Findes kun i dataset for embedded database
        // husk @Disabled virker ikke med H2 db
        Department found = repository.getDepartment(50);
        assertEquals("A", found.getName());
    }
}