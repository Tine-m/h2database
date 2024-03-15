package demo.h2database;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentRepositoryTest {

    @Autowired
    DepartmentRepository repository;

    @Test
    void getAccountingDepartment() throws SQLException {
        Department found = repository.getDepartment(10);
        assertEquals("ACCOUNTING", found.getName());
    }

    @Test
    void deleteDepartment40() throws IncorrectDepartmentIDException {
        repository.deleteDepartment(40);
    }

    @Test
    void deleteNotExistingDepartment() {
        assertThrows(IncorrectDepartmentIDException.class, () -> repository.deleteDepartment(50));
    }
}