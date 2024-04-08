package demo.h2database;

import demo.h2database.model.Department;
import demo.h2database.model.IncorrectDepartmentIDException;
import demo.h2database.repository.DepartmentRepository;
import demo.h2database.repository.DepartmentRepositoryUdenSingleton;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
class DepartmentRepositoryTest {

    @Autowired
    DepartmentRepository repository;

    /*@Test
    void testSingletonPerformance() throws SQLException {
        for (int i = 0; i < 1000; i++) {
            repository.getDepartment(10);
        }
    }*/

    @Test
    void findAccountingDepartment() throws SQLException {
        Department found = repository.getDepartment(10);
        assertEquals("ACCOUNTING", found.getName());
    }

   @Test
    void deleteDepartment40() throws IncorrectDepartmentIDException {
        repository.deleteDepartment(40);
    }

    @Test
    void findDepartment40() throws SQLException {
        Department found = repository.getDepartment(40);
        assertNotNull(found);
    }

    /*@Test
    void moveAllDepartmentsToOregon() throws SQLException {
        boolean status = repository.moveAllDepartmentsToSameLocation("Oregon");
        assertTrue(status);
    }*/

    @Test
    void deleteNotExistingDepartment() {
        assertThrows(IncorrectDepartmentIDException.class, () -> repository.deleteDepartment(50));
    }
}