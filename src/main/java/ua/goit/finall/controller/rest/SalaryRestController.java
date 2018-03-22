package ua.goit.finall.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.goit.finall.model.Salary;
import ua.goit.finall.service.SalaryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/salaries")
public class SalaryRestController {

    @Autowired
    SalaryService salaryService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Salary>> getAllSalaries() {
        List<Salary> salaryList = this.salaryService.getAll();

        if (salaryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(salaryList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{salaryId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Salary> getSalary(@PathVariable("salaryId") Long salaryId) {
        if (salaryId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Salary salary = this.salaryService.getById(salaryId);

        if (salary == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(salary, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Salary> saveSalary(@RequestBody @Valid Salary salary, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (salary == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.salaryService.save(salary);
        headers.setLocation(builder.path("/api/salarys/{salaryId}").buildAndExpand(salary.getId()).toUri());
        return new ResponseEntity<>(salary, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{salaryId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Salary> deleteSalary(@PathVariable("salaryId") Long salaryId) {
        Salary salary = this.salaryService.getById(salaryId);

        if (salary == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.salaryService.delete(salaryId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
