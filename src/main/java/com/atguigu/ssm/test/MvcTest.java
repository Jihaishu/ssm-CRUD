package com.atguigu.ssm.test;

import com.atguigu.ssm.bean.Employee;
import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.EmptyStackException;
import java.util.List;

/**
 * @author : Administrator
 * @date : 2020-11-13 12:12
 * @description ：
 */

@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:springmvc.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class MvcTest {

    //传入springMVC的ioc
    @Autowired
    WebApplicationContext context;

    MockMvc mockMvc;

    @Before
    public void initMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testPage() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/com/emps").param("pn", "5")).andReturn();

        //请求成功后
        MockHttpServletRequest request = result.getRequest();
        PageInfo pi = (PageInfo) request.getAttribute("pageInfo");
        System.out.println("当前页码：" + pi.getPageNum());
        System.out.println("总页码：" + pi.getPages());
        System.out.println("总记录数：" + pi.getTotal());
        System.out.println("在页面需要连续显示的页码");
        int[] nums = pi.getNavigatepageNums();
        for (int i : nums) {
            System.out.print(" " + i);
        }
        List<Employee> list = pi.getList();
        for (Employee employee:list) {
            System.out.println("ID:" + employee.getEmpId() + "===>Name:" + employee.getEmpName());
        }

    }
}