package com.edu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.edu.entity.Hard;
import com.edu.entity.Resources;
import com.edu.entity.Student;
import com.edu.entity.Support;
import com.edu.service.ResourceService;
import com.edu.service.TeacherService;
import com.edu.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin//跨域(ajax测试用)
@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @Autowired
    ResourceService resourceService;

    //访问主页
    @RequestMapping("/main")
    public String main() {
        return "teacher/Tmain";
    }

    //访问用户信息
    @RequestMapping("/userInfo")
    public String userInfo() {
        return "teacher/TuserInfo";
    }

    //访问学生信息
    @RequestMapping("/studentInfo")
    public String studentInfo() {
        return "teacher/TstudentInfo";
    }

    //访问困难认定
    @RequestMapping("/hard")
    public String hard(Model model, Integer currentPage, Integer totalPage,String studentName) {
        if (currentPage == null) {
            currentPage = 1;
        }
        if (currentPage <= 0) {
            currentPage = 1;
        }
        if (totalPage != null && currentPage > totalPage) {
            currentPage = totalPage;
        }
        IPage<StudentAndHardVO> studentAndHardVOPage = teacherService.studentAndHardVOPage(currentPage, 5, studentName);
        model.addAttribute("hardVOPage", studentAndHardVOPage);
        model.addAttribute("studentName", studentName);
        return "teacher/Thard";
    }

    //访问资源管理
    @RequestMapping("/resource")
    public String resource(Model model, Integer currentPage, Integer totalPage,Resources resources) {
        //id存在或者为""代表是保存和插入时带的参数
        if (resources.getResourceId() !=null){
            resources= new Resources();
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
        IPage<Resources> resourcesPage = resourceService.findAllResourcePage(currentPage, 5, resources.getResourceName());
        model.addAttribute("resourcesPage", resourcesPage);
        model.addAttribute("resourceName", resources.getResourceName());
        return "teacher/Tresource";
    }

    //访问资助申请审核
    @RequestMapping("/support")
    public String support(Model model, Integer currentPage, Integer totalPage,String resourceName) {
        if (currentPage == null) {
            currentPage = 1;
        }
        if (currentPage <= 0) {
            currentPage = 1;
        }
        if (totalPage != null && currentPage > totalPage) {
            currentPage = totalPage;
        }
        IPage<ResourceStudentSupportVO> supportVOPage = teacherService.findSupportVOPage(currentPage, 5, resourceName);
        model.addAttribute("supportVOPage", supportVOPage);
        model.addAttribute("resourceName",resourceName);
        return "teacher/TsupportApply";
    }

    //访问修改密码
    @RequestMapping("/changePassword")
    public String changePassword() {
        return "teacher/TchangePassword";
    }

    //资助申请通过，不通过或者撤销
    @GetMapping("/clickSupport")
    public String clickSupport(Support support, Model model) {
        int flag = teacherService.updateSupportById(support);
        if (flag != 0) {
            model.addAttribute("msg", "设置成功");
            return "forward:/teacher/support";
        } else {
            model.addAttribute("msg", "设置失败");
            return "forward:/teacher/support";
        }
    }

    //困难申请通过，不通过或者撤销
    @GetMapping("/clickHard")
    public String clickHard(Hard hard, Model model) {
        int flag = teacherService.updateHardById(hard);
        if (flag != 0) {
            model.addAttribute("msg", "设置成功");
            return "forward:/teacher/hard";
        } else {
            model.addAttribute("msg", "设置失败");
            return "forward:/teacher/hard";
        }
    }

    //添加资源
    @PostMapping("/addResource")
    public String addResource(Resources resources, Model model) {
        int flag = resourceService.addResource(resources);
        if (flag != 0) {
            model.addAttribute("msg", "添加成功");
            return "forward:/teacher/resource";
        } else {
            model.addAttribute("msg", "添加失败");
            return "forward:/teacher/resource";
        }
    }

    //修改资源
    @PostMapping("/updateResource")
    public String updateResource(Resources resources, Model model) {
        int flag = resourceService.updateResource(resources);
        if (flag != 0) {
            model.addAttribute("msg", "修改成功");
            return "forward:/teacher/resource";
        } else {
            model.addAttribute("msg", "修改失败");
            return "forward:/teacher/resource";
        }
    }

    //删除资源
    @GetMapping("/delResource")
    public String delResource(String resourceId, Model model) {
        int flag = resourceService.delResource(resourceId);
        if (flag != 0) {
            model.addAttribute("msg", "删除成功");
            return "forward:/teacher/resource";
        } else {
            model.addAttribute("msg", "删除失败");
            return "forward:/teacher/resource";
        }
    }

    //echars1柱形图
    @ResponseBody
    @GetMapping("/specialList")
    public List<Integer> specialList() {
        return teacherService.findSpecialList();
    }

    //echars2饼图
    @ResponseBody
    @GetMapping("/echarsDataList")
    public List<EcharsDataVO> echarsDataList() {
        return teacherService.findEcharsDataList();
    }

    //ajax根据id查资源
    @ResponseBody
    @GetMapping("/findResource")
    public Resources findResource(String resourceId) {
        return teacherService.findResourceById(resourceId);
    }

    //ajax困难认定访问查看
    @ResponseBody
    @GetMapping("/clickChakan")
    public StudentAndHardVO clickChakan(String hardId) {
        return teacherService.findStudentAndHardVOById(hardId);
    }

    //ajax资助申请访问查看
    @ResponseBody
    @GetMapping("/clickSupportChakan")
    public ResourceStudentSupportVO clickSupportChakan(String supportId) {
        return teacherService.findResourceStudentSupportVOById(supportId);
    }

    //layui学生信息动态表格
    @ResponseBody
    @GetMapping("/studentList")
    public DataVO<Student> studentList(Integer page, Integer limit, @RequestParam(required = false, value = "key[studentName]") String studentName,
                                       @RequestParam(required = false, value = "key[studentNumber]") String studentNumber) {
        return teacherService.pageStudentBySearch(page, limit, studentName, studentNumber);
    }
}
