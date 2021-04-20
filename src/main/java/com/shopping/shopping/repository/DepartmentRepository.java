package com.shopping.shopping.repository;


//import com.shopping.shopping.entity.Department;

import com.shopping.shopping.entity.Department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
