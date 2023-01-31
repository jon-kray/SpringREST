package ru.server.service.app.repository;

import org.springframework.stereotype.Repository;
import ru.server.service.app.model.Employee;
import ru.server.service.app.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class EmployeeRepository {

    private final Map<Long, Employee> store = new ConcurrentHashMap<>();

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(store.values());
    }

    public Employee findById(long id) {
        return store.getOrDefault(id, Utils.getIncorrectEmployee());
    }

    public void add(Employee employee) {
        store.putIfAbsent(employee.getId(), employee);
    }

    public void deleteById(long id) {
        store.remove(id);
    }
}
