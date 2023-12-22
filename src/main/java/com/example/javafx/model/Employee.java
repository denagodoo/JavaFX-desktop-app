package com.example.javafx.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee {


    private IntegerProperty employee_id;
    private StringProperty email;
    private StringProperty name;

    public Employee() {
        this.employee_id = new SimpleIntegerProperty();

        this.email = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
    }


    public void setEmployee_id(int employee_id) {
        this.employee_id.set(employee_id);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setName(String name) {
        this.name.set(name);
    }



    public int getEmployee_id() {
        return employee_id.get();
    }

    public IntegerProperty employee_idProperty() {
        return employee_id;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }
}
