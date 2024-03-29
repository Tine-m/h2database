package demo.h2database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class DepartmentRepository {

    @Value("${spring.datasource.url}")
    private String db_url;

    @Value("${spring.datasource.username}")
    private String uid;

    @Value("${spring.datasource.password}")
    private String pwd;

    public Department getDepartment(int deptno) throws SQLException {
        try (
                Connection connection =
                        DriverManager.getConnection(db_url, uid, pwd)) {
            String SQL = "SELECT * FROM DEPT WHERE DEPTNO = ?";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setInt(1, deptno);
            ResultSet rs = ps.executeQuery();
            Department found = null;
            if (rs.next()) {
                System.out.println("uden singleton " + connection);
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String location = rs.getString(3);
                found = new Department(id, name, location);
            }
            return found;
        }
    }

    public void deleteDepartment(int deptno) throws IncorrectDepartmentIDException {
        try (
                Connection connection =
                        DriverManager.getConnection(db_url, uid, pwd)) {
            String SQL = "DELETE FROM DEPT WHERE DEPTNO = ?";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setInt(1, deptno);
            int rows= ps.executeUpdate();
            if (rows < 1) {
                throw new IncorrectDepartmentIDException();
            }
           } catch (SQLException e) {
            throw new IncorrectDepartmentIDException();
        }
    }
}
