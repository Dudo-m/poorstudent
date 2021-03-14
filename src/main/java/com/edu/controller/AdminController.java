package com.edu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.edu.entity.Resources;
import com.edu.entity.User;
import com.edu.service.AdminService;
import com.edu.service.ResourceService;
import com.edu.vo.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@CrossOrigin//跨域(ajax测试用)
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ResourceService resourceService;
    @Autowired
    AdminService adminService;

    //访问主页
    @RequestMapping("/main")
    public String main() {
        return "admin/Amain";
    }

    //访问用户信息
    @RequestMapping("/userInfo")
    public String userInfo() {
        return "admin/AuserInfo";
    }

    //访问学生信息
    @RequestMapping("/studentInfo")
    public String studentInfo() {
        return "admin/AstudentInfo";
    }

    //访问用户管理
    @RequestMapping("/userAdmin")
    public String userList() {
        return "admin/AuserList";
    }

    //访问资源管理
    @RequestMapping("/resource")
    public String resource(Model model, Integer currentPage, Integer totalPage, String resourceName) {
        if (currentPage == null) {
            currentPage = 1;
        }
        if (currentPage <= 0) {
            currentPage = 1;
        }
        if (totalPage != null && currentPage > totalPage) {
            currentPage = totalPage;
        }
        IPage<Resources> resourcesPage = resourceService.findAllResourcePage(currentPage, 5, resourceName);
        model.addAttribute("resourcesPage", resourcesPage);
        model.addAttribute("resourceName",resourceName);
        return "admin/Aresource";
    }

    //访问修改密码
    @RequestMapping("/changePassword")
    public String changePassword() {
        return "admin/AchangePassword";
    }

    //添加资源
    @PostMapping("/addResource")
    public String addResource(Resources resources, Model model) {
        int flag = resourceService.addResource(resources);
        if (flag != 0) {
            model.addAttribute("msg", "添加成功");
            return "forward:/admin/resource";
        } else {
            model.addAttribute("msg", "添加失败");
            return "forward:/admin/resource";
        }
    }

    //修改资源
    @PostMapping("/updateResource")
    public String updateResource(Resources resources, Model model) {
        int flag = resourceService.updateResource(resources);
        if (flag != 0) {
            model.addAttribute("msg", "修改成功");
            return "forward:/admin/resource";
        } else {
            model.addAttribute("msg", "修改失败");
            return "forward:/admin/resource";
        }
    }

    //删除资源
    @GetMapping("/delResource")
    public String delResource(String resourceId, Model model) {
        int flag = resourceService.delResource(resourceId);
        if (flag != 0) {
            model.addAttribute("msg", "删除成功");
            return "forward:/admin/resource";
        } else {
            model.addAttribute("msg", "删除失败");
            return "forward:/admin/resource";
        }
    }

    //编辑用户
    @PostMapping("/updateUser")
    public String updateUser(User user, Model model) {
        int flag = adminService.updateUser(user);
        if (flag != 0) {
            model.addAttribute("msg", "更新成功");
            return "forward:/admin/userAdmin";
        } else {
            model.addAttribute("msg", "更新失败");
            return "forward:/admin/userAdmin";
        }
    }

    //layui用户信息动态表格
    @ResponseBody
    @GetMapping("/userList")
    public DataVO<User> userList(Integer page, Integer limit, @RequestParam(required = false, value = "key[userName]") String userName) {
        return adminService.pageUserBySearch(page, limit, userName);
    }

    //ajax删除用户
    @ResponseBody
    @GetMapping("/deleteUser")
    public boolean deleteUser(String userId) {
        int i = adminService.deleteUserById(userId);
        return i != 0;
    }
}
