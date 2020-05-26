package com.truist.client.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truist.client.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	
	Department findByDeptAndAccountAndUserId(String dept, BigDecimal account, Long userID);
	
}