package com.atguigu.ssm.service;

import com.atguigu.ssm.bean.Employee;
import com.atguigu.ssm.bean.EmployeeExample;
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


    /**
     *@description: 校验用户名是否可用
     *@author: Administrator
     *@date: 2020/11/16 14:04
    * @param: empName
    *@return: boolean
    */
    public boolean checkUser(String empName) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);

        long count = employeeMapper.countByExample(example);
        
        return count == 0;
    }
}
