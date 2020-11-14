package com.atguigu.ssm.controller;

import com.atguigu.ssm.bean.Department;
import com.atguigu.ssm.bean.Msg;
import com.atguigu.ssm.dao.DepartmentMapper;
import com.atguigu.ssm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**处理和部门有关的请求
 * @author : Administrator
 * @date : 2020-11-14 21:34
 * @description ：
 */
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    //返回所有的部门信息
    @ResponseBody
    @RequestMapping("/depts")
    public Msg getDepts(){
        //查处的所有部门信息
        List<Department> list = departmentService.getDepts();
        return Msg.success().add("depts",list);
    }

}