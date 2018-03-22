package ua.goit.finall.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.goit.finall.model.Salary;
import ua.goit.finall.model.User;
import ua.goit.finall.service.EmployeeService;
import ua.goit.finall.service.SalaryService;
import ua.goit.finall.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "api/personalSalaries")
public class PersonalSalaryRestController {

    @Autowired
    SalaryService salaryService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    UserService userService;

    @PreAuthorize("#username == principal.username")
    @RequestMapping(path = "/{username}/{fromYear}/{fromMonth}/{toYear}/{toMonth}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Salary>> getPersonalSalaries(@PathVariable("username") String username, @PathVariable("fromYear") Integer fromYear, @PathVariable("fromMonth") Integer fromMonth,
                                                            @PathVariable("toYear") Integer toYear, @PathVariable("toMonth") Integer toMonth) {
        //String username = getLoggedinUserName();
        User user = userService.findUserByUsername(username);
        Long employeeId = employeeService.findEmployeeByUser(user).getId();
        List<Salary> salaryList = this.salaryService.getPersonalSalaries(employeeId, fromYear, fromMonth, toYear, toMonth);

        if (salaryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(salaryList, HttpStatus.OK);
    }
}
