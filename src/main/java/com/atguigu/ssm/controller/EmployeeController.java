package com.atguigu.ssm.controller;

import com.atguigu.ssm.bean.Employee;
import com.atguigu.ssm.bean.Msg;
import com.atguigu.ssm.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 处理和员工有关的请求
 * @author : Administrator
 * @date : 2020-11-13 11:41
 * @description ：
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


/*
 *@description: 员工更新方法
 *@author: Administrator
 *@date: 2020/11/16 20:58
* @param: employee
*@return: com.atguigu.ssm.bean.Msg
*/
    @ResponseBody
    @RequestMapping(value = "/emp/{empId}",method = RequestMethod.PUT)
    public Msg saveEmp(Employee employee){

        employeeService.updateEmp(employee);
        return Msg.success();
    }



/**
 *@description: 根据id查询员工
 *@author: Administrator
 *@date: 2020/11/16 20:35
* @param: id
*@return: com.atguigu.ssm.bean.Msg
*/
    @ResponseBody
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    public Msg getEmp(@PathVariable("id") Integer id){
        Employee employee = employeeService.getEmp(id);
        return Msg.success().add("emp",employee);
    }

/**
 *@description: 校验用户名是否可用
 *@author: Administrator
 *@date: 2020/11/16 14:06
* @param: empName
*@return: com.atguigu.ssm.bean.Msg
*/
    @ResponseBody
    @RequestMapping("/checkuser")
    public Msg checkuser(@RequestParam("empName") String empName){
        //先判断用户名是否是合法的表达式
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})" ;
        if (!empName.matches(regx)) {
            return Msg.fail().add("va_msg","用户名必须是6-16位英文和数字的组合或者是2-5位中文");
        }

        //用户名数据库重复校验
        boolean b = employeeService.checkUser(empName);
        if (b) {
            return Msg.success();
        }else {
            return Msg.fail().add("va_msg","用户名不可用");

        }
    }


//员工保存 ：POST请求

    @RequestMapping(value = "/emp",method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result){
        if (result.hasErrors()) {
            //校验失败，应该返回失败，在模态框中显示校验失败的提示信息

            Map<String, Object> map = new HashMap<>();

            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                System.out.println("错误的字段名："+fieldError.getField());
                System.out.println("错误信息："+fieldError.getDefaultMessage());
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields",map);
        }else {
            employeeService.saveEmp(employee);
            return Msg.success();
        }
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