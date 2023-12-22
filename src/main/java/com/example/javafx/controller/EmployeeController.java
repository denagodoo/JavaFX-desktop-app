package com.example.javafx.controller;

import com.example.javafx.model.Employee;
import com.example.javafx.model.EmployeeDAO;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class EmployeeController {
    public Button searchEmpBtn;
    @FXML
    private TextField empIdText;
    @FXML
    private TextField empNameText;
    @FXML
    private TextField empEmailText;
    @FXML
    private TextArea textArea;
    @FXML
    private TableView employeeTable;

    @FXML
    private TableColumn<Employee, Integer> IdColumn;
    @FXML
    private TableColumn<Employee, String> nameColumn;
    @FXML
    private TableColumn<Employee, String> emailColumn;

    @FXML
    private void searchEmployee(ActionEvent event) throws ClassNotFoundException, SQLException {
        try {
            Employee emp = EmployeeDAO.searchEmployee(empIdText.getText());

            populateAndShowEmployee(emp);
        } catch (SQLException e) {
            e.printStackTrace();
            textArea.setText("Error occurred while getting employee info from DB. \n" + e);
            throw e;
        }
    }
    @FXML
    private void searchEmployees(ActionEvent event) throws SQLException, ClassNotFoundException{
        try {
            ObservableList<Employee> empData = EmployeeDAO.searchEmployees();

            populateEmployees(empData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting info from employees in DB \n" + e);
            throw e;
        }
    }
    @FXML
    private void initialize() {
        IdColumn.setCellValueFactory(cellData -> cellData.getValue().employee_idProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
    }
    @FXML
    private void populateEmployee(Employee emp) throws ClassNotFoundException {
        ObservableList<Employee> empData = FXCollections.observableArrayList();
        empData.add(emp);
        employeeTable.setItems(empData);
    }
    @FXML
    private void setEmpInfoToTextArea(Employee emp) {
        textArea.setText("Name: " + emp.getName() + "\n" +
                "Email: " + emp.getEmail());
    }

    @FXML
    private void populateAndShowEmployee(Employee emp) throws ClassNotFoundException {
        if (emp != null) {
            populateEmployee(emp);
            setEmpInfoToTextArea(emp);
        } else {
            textArea.setText("This employee does not exist!\n");
        }
    }
    //Populate Employees for TableView
    @FXML
    private void populateEmployees (ObservableList<Employee> empData) throws ClassNotFoundException {
        //Set items to the employeeTable
        employeeTable.setItems(empData);
    }



    //Insert an employee to the DB
    @FXML
    private void insertEmployee (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            EmployeeDAO.insertEmp(empNameText.getText(),empEmailText.getText());
            textArea.setText("Employee inserted! \n");
        } catch (SQLException e) {
            textArea.setText("Problem occurred while inserting employee " + e);
            throw e;
        }
    }
    //Delete an employee with a given employee Id from DB
    @FXML
    private void deleteEmployee (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            EmployeeDAO.deleteEmpWithId(empIdText.getText());
            textArea.setText("Employee deleted! Employee id: " + empIdText.getText() + "\n");
        } catch (SQLException e) {
            textArea.setText("Problem occurred while deleting employee " + e);
            throw e;
        }
    }



}