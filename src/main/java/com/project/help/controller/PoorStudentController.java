package com.project.help.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.help.dao.PoorStudentMapper;
import com.project.help.poi.WriteExcel;
import com.project.help.pojo.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/data")
public class PoorStudentController {
    @Autowired
    PoorStudentMapper poorStudentMapper;

    @RequestMapping(value ="/poorStuList",method = RequestMethod.GET)
    public String stuList(){
        return "/student/poorStuList";
    }


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
       // poorStuGrid.setTotal(total);
        return poorStuGrid ;
    }


    @RequestMapping("/exportPoorStu")//导出表格
    public void export(HttpServletResponse response) throws Exception{
        String[] title=new String[]{"学号","姓名","专业","每日平均食堂消费","标准差","食堂消费频率"};//改属性
        List<PoorStudent> PoorStudentlist=poorStudentMapper.selectPoorStudent();
        List<Object[]>  dataList = new ArrayList<Object[]>();
        for(int i=0;i<PoorStudentlist.size();i++){
            Object[] obj=new Object[6];//
            obj[0]=PoorStudentlist.get(i).getStuNum();
            obj[1]=PoorStudentlist.get(i).getStuName();
            obj[2]=PoorStudentlist.get(i).getStuMajor();
            obj[3]=PoorStudentlist.get(i).getAvgMoney();
            obj[4]=PoorStudentlist.get(i).getVariance();
            obj[5]=PoorStudentlist.get(i).getRate();
            dataList.add(obj);
        }
        WriteExcel ex = new WriteExcel(title, dataList);
        InputStream in;
        in = ex.export();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("contentDisposition", "attachment;filename=AllUsers.xls");
        ServletOutputStream output = response.getOutputStream();
        IOUtils.copy(in,output);
    }

}
