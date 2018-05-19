package com.project.help.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.help.dao.PoorStudentMapper;
import com.project.help.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/data")
public class PoorStudentController {
    @Autowired
    PoorStudentMapper poorStudentMapper;


    @RequestMapping(value = "/poorstu",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public PoorStuGrid getPoorStuList(@RequestParam("current") int current,
                                  @RequestParam("rowCount") int rowCount){

        PageHelper.startPage(current, rowCount,true,false);
        List<PoorStudent> poorStudentList=poorStudentMapper.selectPoorStudent();
        PageInfo<PoorStudent> page=new PageInfo<>(poorStudentList);
        PoorStuGrid poorStuGrid = new PoorStuGrid();
        poorStuGrid .setCurrent(current);
        poorStuGrid .setRowCount(rowCount);
        poorStuGrid .setRows(page.getList());
        return poorStuGrid ;
    }



}
