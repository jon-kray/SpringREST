package ru.client.service.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.client.service.app.client.EmployeeTemplateService;
import ru.client.service.app.model.Employee;

@Component
@Slf4j
public class Runner implements CommandLineRunner {

    private EmployeeTemplateService employeeTemplateService;

    @Override
    public void run(String... args) throws Exception {

        //add employee

        /*var employee = new Employee();
        employee.setId(1L);
        employee.setDepartment("VTB");
        employee.setName("Evgeniy");
        employee.setSurname("Krainyuk");
        employee.setSalary(50_000);
        employeeTemplateService.add(employee);*/

        //findAll employee

       /* var result = employeeTemplateService.findAll();
        if (result.isEmpty()) {
            log.info("Store of employee is empty.");
        }else {
            result.forEach(System.out::println);*/

        //findById

        /*var result = employeeTemplateService.findById(1L);
        System.out.println(result);*/

        //deleteById

        /*employeeTemplateService.deleteById(1L);*/


    }

    @Autowired
    public void setEmployeeTemplateService(EmployeeTemplateService employeeTemplateService) {
        this.employeeTemplateService = employeeTemplateService;
    }
}
