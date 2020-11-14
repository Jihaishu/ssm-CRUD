package com.atguigu.ssm.service;

import com.atguigu.ssm.bean.Employee;
import com.atguigu.ssm.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Administrator
 * @date : 2020-11-12 22:23
 * @description ：
 */
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;


    public List<Employee> getAll(){

        return employeeMapper.selectByExampleWithDept(null);
    }

    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }
}
