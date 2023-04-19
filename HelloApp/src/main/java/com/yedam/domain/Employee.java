package com.yedam.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class Employee {
	private int employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String jobId;
	private Date hireDate;
}
