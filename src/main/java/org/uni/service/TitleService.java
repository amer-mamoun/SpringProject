package org.uni.service;

import org.uni.model.Title;

import java.util.List;

public interface TitleService {

    List<Title> getTitlesByEmployeeNumber(int employeeNumber);
}
