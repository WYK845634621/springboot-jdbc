package com.yikai.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

//     *
//     * @Author wangyikai
//     * @Date 2018/9/18 - 9:59
//     * 使用原生的Jbdc来操作数据库

@Controller
public class JdbcController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @ResponseBody
    @GetMapping("/query")
    public Map<String, Object> map() {
        List<Map<String, Object>> li = jdbcTemplate.queryForList("SELECT * from department");
        return li.get(0);
    }

}
