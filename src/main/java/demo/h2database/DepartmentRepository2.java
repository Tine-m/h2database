package demo.h2database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class DepartmentRepository2 {

    @Value("${spring.datasource.url}")
    private String db_url;

    @Value("${spring.datasource.username}")
    private String uid;

    @Value("${spring.datasource.password}")
    private String pwd;

    public Department getDepartment(int deptno) throws SQLException {
        Connection connection = ConnectionManager.getConnection(db_url, uid, pwd); // singleton
        String SQL = "SELECT * FROM DEPT WHERE DEPTNO = ?";
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, deptno);
            ResultSet rs = ps.executeQuery();
            Department found = null;
            if (rs.next()) {
                System.out.println("hej " + connection);
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String location = rs.getString(3);
                found = new Department(id, name, location);
            }
            return found;
        }
    }
}

