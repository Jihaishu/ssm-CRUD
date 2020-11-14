package com.atguigu.ssm.controller;

import com.atguigu.ssm.bean.Employee;
import com.atguigu.ssm.bean.Msg;
import com.atguigu.ssm.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/** 处理和员工有关的请求
 * @author : Administrator
 * @date : 2020-11-13 11:41
 * @description ：
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

//员工保存 ：POST请求

    @RequestMapping(value = "/emp",method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(Employee employee){
        employeeService.saveEmp(employee);
        return Msg.success();
    }
    @ResponseBody
    @RequestMapping("/emps")
    public Msg getEmpsWithJson(@RequestParam(value = "pn",defaultValue="1")Integer pn){

        //引入pagehelper分页查询插件
        //查询之前调用，传入页码，以及每页数量
        PageHelper.startPage(pn,5);
        List<Employee> emps = employeeService.getAll();

        //pageInfo包装查询后的结果，只需要将pageInfo交给页面就行
        PageInfo page = new PageInfo(emps,5);
        return Msg.success().add("pageInfo",page);
    }



//    @RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pn",defaultValue="1")Integer pn,Model  model){

        //引入pagehelper分页查询插件
        //查询之前调用，传入页码，以及每页数量
        PageHelper.startPage(pn,5);
        List<Employee> emps = employeeService.getAll();

        //pageInfo包装查询后的结果，只需要将pageInfo交给页面就行
        PageInfo page = new PageInfo(emps,5);
        model.addAttribute("pageInfo",page);

        return "list";
    }

}