package org.uni.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uni.EmployeesApplication;
import org.uni.model.Employee;
import org.uni.service.EmployeeService;


import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private static final String DEFAULT_RESULT_LIMIT = "50";

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/get/all",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Employee>> getEmployees(
            @RequestParam(defaultValue = DEFAULT_RESULT_LIMIT, required = false) String limit
    ) {
        try {
            int resultLimit = Integer.parseInt(limit);
            return new ResponseEntity<>(employeeService.getEmployees(resultLimit), HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") String id) {
        Employee employee = employeeService.getEmployeeById(Integer.parseInt(id));

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/get/fname/{firstName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Employee>> getEmployeesByFirstName(@PathVariable(value = "firstName") String name) {
        List<Employee> employee = employeeService.findByFirstName(name);

        if (employee == null) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
           return new ResponseEntity<>(employee, HttpStatus.OK);
      }
   }


    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<EmployeeCreated> addEmployee(
            HttpServletRequest httpServletRequest,
            @RequestBody Employee employee
    ) {
        Employee newEmployee = employeeService.addEmployee(employee);

        String newLocation = EmployeesApplication.formatLocation(
                httpServletRequest.getRequestURL().toString(),
                Integer.toString(newEmployee.getEmployeeNumber()));
        try {
            URI uri = new URI(newLocation);
            HttpHeaders httpResponseHeaders = new HttpHeaders();
            httpResponseHeaders.setLocation(uri);

            return new ResponseEntity<>(
                    new EmployeeCreated(newEmployee.getEmployeeNumber(), newLocation),
                    httpResponseHeaders,
                    HttpStatus.CREATED);

        } catch (URISyntaxException e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }




    @RequestMapping(value = "/edit/{id}",method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<EmployeeEdited> editEmployee(
            HttpServletRequest httpServletRequest,
            @RequestBody  Employee employee,
            @PathVariable Integer id
            ) {
        Employee theEmployee = employeeService.editEmployee(employee);

        String theLocation = EmployeesApplication.formatLocation(
                httpServletRequest.getRequestURL().toString(),
                Integer.toString(theEmployee.getEmployeeNumber()));
        try {
            URI uri = new URI(theLocation);
            HttpHeaders httpResponseHeaders = new HttpHeaders();
            httpResponseHeaders.setLocation(uri);

            return new ResponseEntity(
                    new EmployeeEdited(theEmployee.getEmployeeNumber(), theLocation),
                    httpResponseHeaders,
                    HttpStatus.ACCEPTED);

        } catch (URISyntaxException e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Employee> deleteEmployee(@PathVariable String id) {
        try {
            employeeService.deleteEmployee(Integer.parseInt(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}



class EmployeeCreated {
    private int employeeNumber;
    private String location;

    public EmployeeCreated(int employeeNumber, String location) {
        this.employeeNumber = employeeNumber;
        this.location = location;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public String getLocation() {
        return location;
    }
}


class EmployeeEdited {
    private int employeeNumber;
    private String location;

    public EmployeeEdited(int employeeNumber, String location) {
        this.employeeNumber = employeeNumber;
        this.location = location;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public String getLocation() {
        return location;
    }
}
