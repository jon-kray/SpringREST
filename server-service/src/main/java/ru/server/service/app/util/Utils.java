package ru.server.service.app.util;

import lombok.experimental.UtilityClass;
import ru.server.service.app.model.Employee;

@UtilityClass
public class Utils {

    public Employee getIncorrectEmployee() {
        var employee = new Employee();
        employee.setId(-1l);
        return employee;
    }
}
