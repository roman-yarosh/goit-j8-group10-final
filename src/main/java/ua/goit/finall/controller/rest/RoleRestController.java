package ua.goit.finall.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.goit.finall.model.Role;
import ua.goit.finall.service.RoleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/roles")
public class RoleRestController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roleList = this.roleService.getAll();

        if (roleList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(roleList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{roleId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Role> getRole(@PathVariable("roleId") Long roleId) {
        if (roleId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Role role = this.roleService.getById(roleId);

        if (role == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Role> saveRole(@RequestBody @Valid Role role, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (role == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.roleService.save(role);
        headers.setLocation(builder.path("/api/roles/{roleId}").buildAndExpand(role.getId()).toUri());
        return new ResponseEntity<>(role, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{roleId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Role> deleteRole(@PathVariable("roleId") Long roleId) {
        Role role = this.roleService.getById(roleId);

        if (role == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.roleService.delete(roleId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
