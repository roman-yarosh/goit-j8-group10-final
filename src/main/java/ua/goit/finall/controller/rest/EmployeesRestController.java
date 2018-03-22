package ua.goit.finall.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.goit.finall.model.Employee;
import ua.goit.finall.service.EmployeeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/employees")
public class EmployeesRestController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employeeList = this.employeeService.getAll();

        if (employeeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{employeeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Employee> getEmployee(@PathVariable("employeeId") Long employeeId) {
        if (employeeId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Employee employee = this.employeeService.getById(employeeId);

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Employee> saveEmployee(@RequestBody @Valid Employee employee, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.employeeService.save(employee);
        headers.setLocation(builder.path("/api/employees/{employeeId}").buildAndExpand(employee.getId()).toUri());
        return new ResponseEntity<>(employee, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{employeeId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        Employee employee = this.employeeService.getById(employeeId);

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.employeeService.delete(employeeId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
