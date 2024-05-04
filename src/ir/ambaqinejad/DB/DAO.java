package ir.ambaqinejad.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DAO {
    private final String DB_URL = "jdbc:mysql://localhost:3306/";
    private final String USER = "root";
    private final String PASSWORD = "Mbam475275$";

    public DAO() throws SQLException {
//        Class.forName("com.my")
    }

    public List<EmployeeEntity> getAll(String query) throws Exception {
        Connection con = DriverManager.getConnection(this.DB_URL, this.USER, this.PASSWORD);
        Statement st = con.createStatement();
        st.execute(query);
        ResultSet rs = st.getResultSet();
        List<EmployeeEntity> employees = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String role = rs.getString("role");
            String createdAt = rs.getString("createdAt");
            String updatedAt = rs.getString("updatedAt");
            EmployeeEntity employeeEntity = new EmployeeEntity(id, name, email, role, createdAt, updatedAt);
            employees.add(employeeEntity);
        }
        st.close();
        con.close();
        return employees;
    }

    public List<EmployeeEntity> insertEmployee(String query, EmployeeEntity employee) throws Exception {
        Connection con = DriverManager.getConnection(this.DB_URL, this.USER, this.PASSWORD);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        java.util.Date createDate = dateFormat.parse(employee.getCreatedAt());
        java.util.Date updateDate = dateFormat.parse(employee.getUpdatedAt());
        Date createSqlDate = new Date(createDate.getTime());
        Date updateSqlDate = new Date(updateDate.getTime());
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, employee.getId());
        st.setString(2, employee.getName());
        st.setString(3, employee.getEmail());
        st.setString(4, employee.getRole());
        st.setDate(5, createSqlDate);
        st.setDate(6, updateSqlDate);
        int rowsAffected = st.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);
        st.close();
        query = "SELECT * FROM mydb.employee;";
        return getAll(query);
    }
}
