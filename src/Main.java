import ir.ambaqinejad.DB.DAO;
import ir.ambaqinejad.DB.EmployeeEntity;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // getAll
            DAO dao = new DAO();
            String query = "SELECT * FROM mydb.employee;";
            List<EmployeeEntity> employees = dao.getAll(query);
            for (EmployeeEntity employee : employees) {
                System.out.println(employee);
            }
            // insert and getAll
            query = "INSERT INTO mydb.employee VALUES(?, ?, ?, ?, ?, ?);";
            int id = employees.get(employees.size() - 1).getId() + 1;
            String name = "Hadi";
            String email = "Hadi@gmail.com";
            String role = "ENGINEER";
            String createdAt = Date.from(Instant.now()).toString();
            String updatedAt = Date.from(Instant.now()).toString();
            EmployeeEntity employee_ = new EmployeeEntity(id, name, email, role, createdAt, updatedAt);
            employees = dao.insertEmployee(query, employee_);
            for (EmployeeEntity employee : employees) {
                System.out.println(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}