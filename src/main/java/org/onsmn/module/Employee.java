package org.onsmn.module;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class Employee {
    private Integer id;
    private String name;
    private String profile;
    private Date date;
    private Long salary;
    private List<Employee> employees;
}