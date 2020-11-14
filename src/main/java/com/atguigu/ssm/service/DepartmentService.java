package com.atguigu.ssm.service;

import com.atguigu.ssm.bean.Department;
import com.atguigu.ssm.dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Administrator
 * @date : 2020-11-14 21:36
 * @description ：
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;
    public List<Department> getDepts() {
        List<Department> list = departmentMapper.selectByExample(null);

        return list;
    }
}