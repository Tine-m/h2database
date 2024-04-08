package demo.h2database.repository;

import demo.h2database.model.IncorrectDepartmentIDException;
import demo.h2database.repository.util.ConnectionManager;
import demo.h2database.model.Department;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentRepository {

    @Value("${spring.datasource.url}")
    private String db_url;

    @Value("${spring.datasource.username}")
    private String uid;

    @Value("${spring.datasource.password}")
    private String pwd;

    public Department getDepartment(int deptno) throws SQLException {
        Connection connection = ConnectionManager.getConnection(db_url, uid, pwd); // singleton
        System.out.println("Singleton " + connection);

        String SQL = "SELECT * FROM DEPT WHERE DEPTNO = ?";
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, deptno);
            ResultSet rs = ps.executeQuery();
            Department found = null;
            if (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String location = rs.getString(3);
                found = new Department(id, name, location);
            }
            return found;
        }
    }

    public void deleteDepartment(int deptno) throws IncorrectDepartmentIDException {
        Connection connection = ConnectionManager.getConnection(db_url, uid, pwd); // singleton
        String SQL = "DELETE FROM DEPT WHERE DEPTNO = ?";
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, deptno);
            int rows = ps.executeUpdate();
            if (rows < 1) {
                throw new IncorrectDepartmentIDException();
            }
        } catch (SQLException e) {
            throw new IncorrectDepartmentIDException();
        }
    }

    public List<Department> findAllDepartments() throws SQLException {
        ArrayList<Department> departments = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection(db_url, uid, pwd); // singleton
        String SQL = "SELECT * FROM DEPT";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL);
            Department tmp = null;
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String location = rs.getString(3);
                tmp = new Department(id, name, location);
                departments.add(tmp);
            }

            return departments;
        }
    }

    public int findNumberOfDepartments() throws SQLException {
        int count = 0;
        Connection connection = ConnectionManager.getConnection(db_url, uid, pwd); // singleton
        String SQL = "SELECT COUNT(*) FROM DEPT";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL);
            Department tmp = null;
            if (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        }
    }

    // Demo af transaktionsstyring
    public boolean moveAllDepartmentsToSameLocation(String location) throws SQLException {
        boolean allGood = false;
        int count = findNumberOfDepartments();
        Connection connection = ConnectionManager.getConnection(db_url, uid, pwd); // singleton
        connection.setAutoCommit(false);
        String SQL = "UPDATE DEPT SET LOC = ?";
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
                ps.setString(1, location);
            int rows = ps.executeUpdate();
            allGood = rows == count;
            if (!allGood) {
                throw new SQLException("Business Transaction aborted");
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
        return allGood;
    }
}

