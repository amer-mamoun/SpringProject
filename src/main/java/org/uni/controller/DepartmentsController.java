package org.uni.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uni.EmployeesApplication;
import org.uni.model.Department;
import org.uni.model.DepartmentEmployee;
import org.uni.model.DepartmentManager;
import org.uni.services.DepartmentService;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = {"/departments"})
public class DepartmentsController {

    private final static String DEFAULT_RESULT_LIMIT = "50";

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = {"/get/all"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Department>> getDepartments(
            @RequestParam(defaultValue = DEFAULT_RESULT_LIMIT, required = false) String limit
    ) {

        try {
            int resultLimit = Integer.parseInt(limit);

            if (resultLimit < 1) throw new RuntimeException();

            return new ResponseEntity<>(departmentService.getDepartments(resultLimit), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = {"/get/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Department> getDepartmentById(@PathVariable String id) {

        try {
            Department department = departmentService.getDepartmentById(id);

            if (department == null) throw new RuntimeException();

            return new ResponseEntity<>(department, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = {"/get/{id}/employees"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<DepartmentEmployee>> getDepartmentEmployeesById(@PathVariable String id) {

        try {
            Department department = departmentService.getDepartmentById(id);

            if (department == null) throw new RuntimeException("No department found with ID provided");

            return new ResponseEntity<>(department.getDepartmentEmployees(), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @RequestMapping(value = {"/get/{id}/managers"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<DepartmentManager>> getDepartmentManagersById(@PathVariable String id) {

        try {
            Department department = departmentService.getDepartmentById(id);

            if (department == null) throw new RuntimeException();

            return new ResponseEntity<>(department.getDepartmentManagers(), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @RequestMapping(value = {"/add"}, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<DepartmentCreated> addDepartment(
            HttpServletRequest httpServletRequest,
            @RequestBody Department department
    ) {
        Department newDepartment = departmentService.addDepartment(department);

        if (newDepartment == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            String newLocation = EmployeesApplication.formatAddLocation(
                    httpServletRequest.getRequestURL().toString(),
                    newDepartment.getDepartmentId());
            URI uri = new URI(newLocation);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(uri);
            return new ResponseEntity<>(new DepartmentCreated(
                    newDepartment.getDepartmentId(), newLocation),
                    httpHeaders,
                    HttpStatus.CREATED);
        } catch (URISyntaxException e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = {"/edit/{id}"}, method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<DepartmentEdited> editDepartment(
            HttpServletRequest httpServletRequest,
            @RequestBody Department department,
            @PathVariable String id
    ) {
        Department theDepartment = departmentService.editDepartment(department);

        if (theDepartment == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            String theLocation = EmployeesApplication.formatEditLocation(
                    httpServletRequest.getRequestURL().toString());
            URI uri = new URI(theLocation);
            HttpHeaders httpResponseHeaders = new HttpHeaders();
            httpResponseHeaders.setLocation(uri);

            return new ResponseEntity(
                    new DepartmentCreated(theDepartment.getDepartmentId(), theLocation),
                    httpResponseHeaders,
                    HttpStatus.ACCEPTED);
        } catch (URISyntaxException e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = {"/delete/{id}"}, method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<DepartmentDeleted> deleteDepartment(@PathVariable String id) {
        String message = EmployeesApplication.formatMessage("Successfully deleted the department.");
        try {
            departmentService.deleteDepartment(id);
            DepartmentDeleted Department = new DepartmentDeleted(message);
            return new ResponseEntity((Department.getMessage()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

    class DepartmentCreated {
        private String departmentId;
        private String location;

        public DepartmentCreated(String departmentId, String location) {
            this.departmentId = departmentId;
            this.location = location;
        }

        public String getDepartmentId() {
            return departmentId;
        }

        public String getLocation() {
            return location;
        }
    }


    class DepartmentEdited {
        private String departmentId;
        private String location;

        public DepartmentEdited(String departmentId, String location) {
            this.departmentId = departmentId;
            this.location = location;
        }

        public String getDepartmentId() {
            return departmentId;
        }

        public String getLocation() {
            return location;
        }
    }


class DepartmentDeleted {
    private String message;

    public DepartmentDeleted(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}