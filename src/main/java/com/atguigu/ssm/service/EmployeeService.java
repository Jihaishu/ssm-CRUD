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

    /**
     *@description: 按照员工id查询员工
     *@author: Administrator
     *@date: 2020/11/16 17:37
    * @param: id
    *@return: com.atguigu.ssm.bean.Employee
    */
    public Employee getEmp(Integer id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }


    /**
     *@description: 员工更新
     *@author: Administrator
     *@date: 2020/11/16 20:38
    * @param: employee
    *@return: void
    */
    public void updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /**
     *@description: 单个员工删除
     *@author: Administrator
     *@date: 2020/11/16 22:21
    * @param: id
    *@return: void
    */
    public void deleteEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    /**
     *@description: 批量员工删除方法
     *@author: Administrator
     *@date: 2020/11/17 0:07
    * @param: ids
    *@return: void
    */
    public void deleteBatch(List<Integer> ids) {
        EmployeeExample example = new EmployeeExample();

        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpIdIn(ids);
        employeeMapper.deleteByExample(example);
    }
}
