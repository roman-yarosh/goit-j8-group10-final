package ua.goit.finall.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.goit.finall.model.Department;
import ua.goit.finall.service.DepartmentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/departments")
public class DepartmentRestController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departmentList = this.departmentService.getAll();

        if (departmentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(departmentList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{departmentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Department> getDepartment(@PathVariable("departmentId") Long departmentId) {
        if (departmentId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Department department = this.departmentService.getById(departmentId);

        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Department> saveDepartment(@RequestBody @Valid Department department, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (department == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.departmentService.save(department);
        headers.setLocation(builder.path("/api/departments/{departmentId}").buildAndExpand(department.getId()).toUri());
        return new ResponseEntity<>(department, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{departmentId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Department> deleteDepartment(@PathVariable("departmentId") Long departmentId) {
        Department department = this.departmentService.getById(departmentId);

        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.departmentService.delete(departmentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
