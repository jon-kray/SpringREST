package ru.server.service.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.server.service.app.model.Employee;
import ru.server.service.app.repository.EmployeeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.getAllEmployees();
    }

    public Employee findById(long id) {
        return employeeRepository.findById(id);
    }

    public void add(Employee employee) {
        employeeRepository.add(employee);
    }

    public void deleteById(long id) {
        employeeRepository.deleteById(id);
    }
}
