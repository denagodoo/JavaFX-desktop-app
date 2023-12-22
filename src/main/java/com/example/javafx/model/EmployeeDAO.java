package com.example.javafx.model;

import com.example.javafx.util.DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {

    public static Employee searchEmployee (String empId) throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM employee WHERE employee_id="+empId;

        try {

            ResultSet rsEmp = DB.dbExecuteQuery(selectStmt);

            Employee employee = getEmployeeFromResultSet(rsEmp);

            return employee;
        } catch (SQLException e) {
            System.out.println("While searching an employee with " + empId +
                    " id, an error occurred: " + e);
            throw e;

        }
    }

    public static Employee getEmployeeFromResultSet(ResultSet rs) throws SQLException {
        Employee emp = null;
        if (rs.next()) {
            emp = new Employee();
            emp.setEmployee_id(rs.getInt("employee_id"));
            emp.setEmail(rs.getString("email"));
            emp.setName(rs.getString("name"));
        }
        return emp;
    }

    public static ObservableList<Employee> searchEmployees() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM employee";

        try {
            ResultSet rsEmps = DB.dbExecuteQuery(selectStmt);

            ObservableList<Employee> empList = getEmployeeList(rsEmps);

            return empList;

        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;

        }

    }

    public static ObservableList<Employee> getEmployeeList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Employee> empList = FXCollections.observableArrayList();

        while (rs.next()) {
            Employee emp = new Employee();
            emp.setEmployee_id(rs.getInt("employee_id"));
            emp.setEmail(rs.getString("email"));
            emp.setName(rs.getString("name"));
            empList.add(emp);
        }

        return empList;
    }

    public static void updateEmpEmail (String empId, String empEmail) throws SQLException, ClassNotFoundException {
        String updateStmt =
                "BEGIN\n" +
                        "   UPDATE employee\n" +
                        "       SET EMAIL ='" + empEmail + "'\n" +
                        "   WHERE EMPLOYEE_ID = " + empId +";\n" +
                        "   COMMIT;\n" +
                        "END;";

        try {
            DB.dbExecuteQuery(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }

    public static void deleteEmpWithId (String empId) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                "BEGIN\n" +
                        "   DELETE FROM employees\n" +
                        "         WHERE employee_id ="+ empId +";\n" +
                        "   COMMIT;\n" +
                        "END;";
        //Execute UPDATE operation
        try {
            DB.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
    //*************************************
    //INSERT an employee
    //*************************************
    public static void insertEmp (String name, String email) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                "BEGIN\n" +
                        "INSERT INTO employees\n" +
                        "(EMPLOYEE_ID, NAME, EMAIL)\n" +
                        "VALUES\n" +
                        "(sequence_employee.nextval, '"+name+"', '"+email+"', SYSDATE, 'IT_PROG');\n" +
                        "END;";
        //Execute DELETE operation
        try {
            DB.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
}
