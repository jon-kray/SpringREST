package ru.client.service.app.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.client.service.app.model.Employee;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeTemplateService {

    private final RestTemplate restTemplate;

    @Value("$(services.employee)")
    private String urlEmployee;

    public List<Employee> findAll() {
        var findAllUrl = urlEmployee.concat(SubUrl.FIND_ALL_SUB_URL.getSubUrl());
        ResponseEntity<List<Employee>> response =
                restTemplate.exchange(
                    findAllUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {}
                 );
        return response.getBody();

    }

    public Employee findById(Long id) {
        var findByIdUrl = urlEmployee.concat(String.format(SubUrl.FIND_BY_ID_SUB_URL.getSubUrl(), id));

        Employee employee;

        try {
             employee = restTemplate.getForObject(
                    findByIdUrl,
                    Employee.class
            );

        } catch (HttpClientErrorException httpClientErrorException) {
            log.info(httpClientErrorException.getResponseBodyAsString());
            employee = new Employee();
            return employee;
        }
        return employee;
    }

    public void add(Employee employee) {
        var addUrl = urlEmployee.concat(SubUrl.ADD_SUB_URL.getSubUrl());
        ResponseEntity<String> response =
                restTemplate.postForEntity(
                        addUrl,
                        employee,
                        String.class
                );
        log.info("Add soccessful? HttpStatus - {}, Message - {}", response.getStatusCode(), response.getBody());
    }

    public void deleteById(Long id) {
        var deleteUrl = urlEmployee.concat(String.format(SubUrl.DELETE_SUB_URL.getSubUrl(), id));
        try {
             ResponseEntity<String> response = restTemplate.exchange(
                    deleteUrl,
                    HttpMethod.DELETE,
                    null,
                    String.class);
            log.info("Delete soccessful? HttpStatus - {}, Message - {}", response.getStatusCode(), response.getBody());
        }catch (HttpClientErrorException httpClientErrorException) {
            log.info(httpClientErrorException.getResponseBodyAsString());
        }
    }



    public enum SubUrl {
        FIND_ALL_SUB_URL("/employees"),
        FIND_BY_ID_SUB_URL("/employees/%s"),
        ADD_SUB_URL("/employees/add"),
        DELETE_SUB_URL("/employees/%s");

        @Getter
        private final String subUrl;

        SubUrl(String subUrl) {
            this.subUrl = subUrl;
        }
    }
}
