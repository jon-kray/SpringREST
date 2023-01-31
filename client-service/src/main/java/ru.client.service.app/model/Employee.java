package ru.client.service.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {

    private long id;

    private String name;

    private String surname;

    private String department;

    private int salary;
}
