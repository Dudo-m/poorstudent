package com.edu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.edu.entity.Hard;
import com.edu.entity.Student;
import com.edu.entity.User;
import com.edu.service.StudentService;
import com.edu.service.UserService;
import com.edu.vo.ResourceApplyVO;
import com.edu.vo.ResourceAndSupportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    UserService userService;

    //访问我的申请
    @GetMapping("/myApply")
    public String myApply(Model model, Integer currentPage, Integer totalPage, HttpSession session, String resourceName) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            model.addAttribute("msg", "请先完善信息");
            return "forward:/studentInfo";
        }

        if (currentPage == null) {
            currentPage = 1;
        }
        if (currentPage <= 0) {
            currentPage = 1;
        }
        if (totalPage != null && currentPage > totalPage) {
            currentPage = totalPage;
        }
        IPage<ResourceAndSupportVO> resourceAndSupportVO = studentService.FindResourceVOByStudentIdPage(currentPage, 5, student.getStudentId(), resourceName);
        model.addAttribute("resourceVOPage", resourceAndSupportVO);

        Hard hard = studentService.FindHardByStudentId(student.getStudentId());
        model.addAttribute("hardApply", hard);
        model.addAttribute("resourceName", resourceName);
        return "myApply";
    }

    //访问用户信息
    @RequestMapping("/userInfo")
    public String userInfo() {
        return "userInfo";
    }

    //访问学生信息
    @RequestMapping("/studentInfo")
    public String studentInfo(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Student student = studentService.FindStudentByUserId(user.getUserId());
        model.addAttribute("student", student);
        return "studentInfo";
    }

    //访问经济困难认定申请
    @RequestMapping("/hardApply")
    public String hardApply(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            model.addAttribute("msg", "请先完善信息");
            return "forward:/studentInfo";
        }
        Hard hard = studentService.FindHardByStudentId(student.getStudentId());
        model.addAttribute("hard", hard);
        return "hardApply";
    }

    //访问申请资助
    @GetMapping("/supportApply")
    public String supportApply(Model model, Integer currentPage, Integer totalPage, HttpSession session, String resourceName) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            model.addAttribute("msg", "请先完善信息");
            return "forward:/studentInfo";
        }
        if (currentPage == null) {
            currentPage = 1;
        }
        if (currentPage <= 0) {
            currentPage = 1;
        }
        if (totalPage != null && currentPage > totalPage) {
            currentPage = totalPage;
        }

        IPage<ResourceApplyVO> resourceApplyVOPage = studentService.ResourceListByPage(currentPage, 5, student.getStudentId(), resourceName);
        model.addAttribute("resourceApplyVOPage", resourceApplyVOPage);
        model.addAttribute("resourceName", resourceName);
        return "supportApply";
    }

    //访问修改密码
    @RequestMapping("/changePassword")
    public String changePassword() {
        return "changePassword";
    }

    //接受前端学生信息数据
    @PostMapping("/addStudentInfo")
    public String addStudentInfo(Student student, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        student.setUserId(user.getUserId());
        int flag = studentService.SaveOrUpdateStudentInfo(student);
        if (flag == 0) {
            model.addAttribute("msg", "保存失败");
        } else {
            model.addAttribute("msg", "保存成功");
            //重新放student信息到session
            Student findStudent = studentService.FindStudentByUserId(user.getUserId());
            session.setAttribute("student", findStudent);
        }
        return "forward:/studentInfo";
    }

    //点击申请
    @GetMapping("/clickSupportApply/{resourceId}")
    public String clickSupportApply(@PathVariable("resourceId") String resourceId, HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        int flag = studentService.SupportApplyAdd(resourceId, student.getStudentId());
        if (flag == 0) {
            model.addAttribute("msg", "申请失败:未完成困难认定或认定未通过");
        } else {
            model.addAttribute("msg", "申请成功");
        }
        return "forward:/supportApply";
    }

    //点击撤销
    @GetMapping("/deleteMyApply/{supportId}")
    public String deleteMyApply(@PathVariable("supportId") String supportId, Model model) {
        int flag = studentService.SupportApplyDel(supportId);
        if (flag == 0) {
            model.addAttribute("msg", "撤销失败");
        } else {
            model.addAttribute("msg", "撤销成功");
        }
        return "forward:/myApply";
    }

    //困难申请提交
    @PostMapping("/addHardApply")
    public String addHardApply(String hardText, Model model, HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        Hard hard = new Hard();
        hard.setHardReason(hardText);
        hard.setStudentId(student.getStudentId());
        int flag = studentService.SaveOrUpdateHard(hard);
        if (flag == 0) {
            model.addAttribute("msg", "保存失败");
        } else {
            model.addAttribute("msg", "保存成功");
        }
        return "forward:/hardApply";
    }

    //困难申请撤销
    @GetMapping("/deleteMyHardApply/{hardId}")
    public String deleteMyHardApply(@PathVariable("hardId") String hardId, Model model) {
        int flag = studentService.HardApplyDel(hardId);
        if (flag == 0) {
            model.addAttribute("msg", "撤销失败");
        } else {
            model.addAttribute("msg", "撤销成功");
        }
        return "forward:/myApply";
    }

}

