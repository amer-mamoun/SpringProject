package org.uni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uni.model.Title;
import org.uni.service.TitleService;

import java.util.List;

@RestController
public class TitlesController {

    @Autowired
    private TitleService titleService;

    @RequestMapping(value = "/employees/{id}/titles", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Title>> getTitlesById(@PathVariable(value = "id") String id) {
        try {
            int employeeNumber = Integer.parseInt(id);
            return new ResponseEntity<>(titleService.getTitlesByEmployeeNumber(employeeNumber), HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
