package com.project.help.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.help.dao.StudentMapper;
import com.project.help.dao.UserMapper;
import com.project.help.poi.WriteExcel;
import com.project.help.pojo.StuGrid;
import com.project.help.pojo.Student;
import com.project.help.pojo.StudentExample;
import com.project.help.pojo.UserExample;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/stu")
public class StuController {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    UserMapper userMapper;

    @RequestMapping(value ="/student/stuList",method = RequestMethod.GET)
    public String stuList(){
        return "/student/stuList";
    }

    @RequestMapping(value = "/addStu",method = RequestMethod.POST)
    public String add(@RequestParam("stuNum") String stuNum,
                      @RequestParam("stuName") String stuName,
                      @RequestParam("stuSex") String stuSex,
                      @RequestParam("stuAge") Integer stuAge,
                      @RequestParam("stuMajor") String StuMajor,
                      @RequestParam("poorLevel") String poorLevel
                      ){
        Student student=new Student();
        student.setStuNum(stuNum);
        student.setStuName(stuName);
        student.setStuSex(stuSex);
        student.setStuAge(stuAge);
        student.setStuMajor(StuMajor);
        student.setPoorLevel(poorLevel);
        studentMapper.insert(student);
        return "redirect:student/stuList";
    }

    @RequestMapping(value ="/delStu",method = RequestMethod.POST)
    public String delete(@RequestParam("stuId") Integer stuId){
        System.out.println("stuId"+stuId);
        studentMapper.deleteByPrimaryKey(stuId);
        return "redirect:student/stuList";
    }

    //更新
    @RequestMapping(value = "/updateStu",method = RequestMethod.POST)
    public String update(@RequestParam("stuId") Integer stuId,
                         @RequestParam("stuNum") String stuNum,
                         @RequestParam("stuName") String stuName,
                         @RequestParam("stuSex") String stuSex,
                         @RequestParam("stuAge") Integer stuAge,
                         @RequestParam("stuMajor") String StuMajor,
                         @RequestParam("poorLevel") String poorLevel){
        Student student=new Student();
        student.setStuId(stuId);
        student.setStuNum(stuNum);
        student.setStuName(stuName);
        student.setStuSex(stuSex);
        student.setStuAge(stuAge);
        student.setStuMajor(StuMajor);
        student.setPoorLevel(poorLevel);
        studentMapper.updateByPrimaryKeySelective(student);
        return "redirect:student/stuList";
    }


    @RequestMapping(value="/getStuInfo",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Student getStuById(@RequestParam("stuId") int stuId) {
        Student student = studentMapper.selectByPrimaryKey(stuId);
//        User user = userService.getUserByUserName("hlk1135");
//        stu.setUser(user);
        return student;
    }

    //分页
    @RequestMapping(value="/stuList",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public StuGrid getStuList(@RequestParam("current") int current,
                                 @RequestParam("rowCount") int rowCount){


        StudentExample example = new StudentExample();
        int total = (int)studentMapper.countByExample(example);//计算出总数
        PageHelper.startPage(current, rowCount,true,false);
        List<Student> Studentlist=studentMapper.selectByExample(example);//选出所有学生
        PageInfo<Student> page=new PageInfo<>(Studentlist);
        StuGrid stuGrid = new StuGrid();
        stuGrid.setCurrent(current);
        stuGrid.setRowCount(rowCount);
        stuGrid.setRows(page.getList());
        stuGrid.setTotal(total);
        return stuGrid;
    }


    @RequestMapping(value="/stulistxml",produces = {"application/xml;charset=UTF-8"})
    @ResponseBody
    public StuGrid getstulistxml(@RequestParam("current") int current,
                                 @RequestParam("rowCount") int rowCount){
        StudentExample example = new StudentExample();
        int total = (int)studentMapper.countByExample(example);//计算出总数
        PageHelper.startPage(current, rowCount,true,false);
        List<Student> Studentlist=studentMapper.selectByExample(example);//选出所有学生
        PageInfo<Student> page=new PageInfo<>(Studentlist);
        StuGrid stuGrid;
        stuGrid = new StuGrid();
        stuGrid.setCurrent(current);
        stuGrid.setRowCount(rowCount);
        stuGrid.setRows(page.getList());
        stuGrid.setTotal(total);
        return stuGrid;
    }

    @RequestMapping("/exportStu")//导出表格
    public void export(HttpServletResponse response) throws Exception{
        String[] title=new String[]{"Id","学号","姓名","性别","年龄","专业","等级"};//改属性
        StudentExample example = new StudentExample();
        List<Student> Studentlist=studentMapper.selectByExample(example);
        List<Object[]>  dataList = new ArrayList<Object[]>();
        for(int i=0;i<Studentlist.size();i++){
            Object[] obj=new Object[7];//
            obj[0]=Studentlist.get(i).getStuId();
            obj[1]=Studentlist.get(i).getStuNum();
            obj[2]=Studentlist.get(i).getStuName();
            obj[3]=Studentlist.get(i).getStuSex();
            obj[4]=Studentlist.get(i).getStuAge();
            obj[5]=Studentlist.get(i).getStuMajor();
            obj[6]=Studentlist.get(i).getPoorLevel();
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
