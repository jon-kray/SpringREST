package ru.server.service.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.server.service.app.model.Employee;
import ru.server.service.app.service.EmployeeService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> findAll() {
        log.info("Calling findAll endpoint...");
        return employeeService.findAll();
    }

    @GetMapping("/id")
    public Employee findById(@PathVariable("id") Long id) {
        log.info("Calling findById endpoint...");
        var result = employeeService.findById(id);
        if (result.getId() == -1L) {
            throw new NoSuchElementException(String.format("There is no employee with id = %s in store.", id));
        }else {
            return result;
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        log.info("Calling add endpoint...");
        employeeService.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Employee has been created.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id) {
        log.info("Calling delete andpoint...");
        var result = employeeService.findById(id);
        if(result.getId() == -1L) {
            throw new NoSuchElementException(String.format("There is no employee with id = %s in store.", id));
        }else {
            employeeService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("Employee with id has been deleted - %s", id));
        }
    }

}
