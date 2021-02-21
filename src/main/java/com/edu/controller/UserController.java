package com.edu.controller;

import com.edu.entity.Student;
import com.edu.entity.User;
import com.edu.service.StudentService;
import com.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;

    @PostMapping("/login")
    public String userLogin(String userName, String userPassword, String code, Model model, HttpSession session) {
        User user = userService.finUserByUserName(userName);
        //md5加盐加密
        String passwordSalt = userPassword + "x@7faqgjw";
        String md5UserPassword = DigestUtils.md5DigestAsHex(passwordSalt.getBytes());

        String piccode = (String) session.getAttribute("piccode");
        if (piccode == null) {
            //如果页面没被刷新
            return "redirect:/";
        } else if (!code.toUpperCase().equals(piccode.toUpperCase())) {
            //转换成大写比较
            model.addAttribute("msg", "验证码错误");
            return "login";
        } else if (user == null) {
            model.addAttribute("msg", "用户名不存在");
            return "login";
        } else if (!user.getUserPassword().equals(md5UserPassword)) {
            model.addAttribute("msg", "密码错误");
            return "login";
        } else if (user.getUserRole().equals("学生")) {
            session.setAttribute("user", user);
            //userid查学生信息
            Student student = studentService.FindStudentByUserId(user.getUserId());
            session.setAttribute("student", student);
            return "redirect:/userInfo";
        } else if (user.getUserRole().equals("资助管理员")) {
            session.setAttribute("user", user);
            return "redirect:/teacher/main";
        } else if (user.getUserRole().equals("系统管理员")) {
            session.setAttribute("user", user);
            return "redirect:/admin/main";
        } else {
            return "login";
        }
    }

    @GetMapping("/loginOut")
    public String userLoginOut(HttpSession session) {
        session.removeAttribute("user");
        session.removeAttribute("student");
        return "redirect:/";
    }

    //生成验证码
    @GetMapping("/codeImg")
    public void codeImg(HttpSession session, ServletResponse response) throws IOException {
        BufferedImage bi = new BufferedImage(70, 25, BufferedImage.TYPE_INT_RGB);
        //画图片
        Graphics g = bi.getGraphics();
        //颜色
        Color c = new Color(201, 200, 75);
        //给Graphics对象设置颜色
        g.setColor(c);
        //画框  （横坐标，纵坐标，宽度，高度
        g.fillRect(0, 0, 100, 38);
        //内容
        char[] ch = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        //获取随机四个字符
        Random r = new Random();
        int len = ch.length, index;
        //用于保存字符
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            //返回一个数，0<index<len,这个数是ch数组中某个数的下标
            index = r.nextInt(len);
            //设置随机的颜色
            g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
            //把字符画到图片上去 drawString(字符串，横坐标，纵坐标)，字符转为字符串：字符+""
            g.drawString(ch[index] + "", (i * 15) + 3, 18);

            addRandomLine(g, 70, 25, 1);

            sb.append(ch[index]);
        }
        //把字符保存到session里，用于等会进行验证
        session.setAttribute("piccode", sb.toString());
        System.out.println("验证码 = " + sb.toString());
        //输出图片  (图片，格式，输出到哪里)
        ImageIO.write(bi, "JPG", response.getOutputStream());
    }

    //访问注册页面
    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    //提交注册
    @PostMapping("/submitRegister")
    public String addRegister(User user, String rePassword, Model model) {
        if (!rePassword.equals(user.getUserPassword())) {
            model.addAttribute("msg", "注册失败:两次密码不同");
            return "forward:/register";
        } else {
            int flag = userService.register(user);
            if (flag == 0) {
                model.addAttribute("msg", "注册失败");
                return "forward:/register";
            } else {
                model.addAttribute("msg", "注册成功");
                return "forward:/";
            }
        }
    }

    //提交修改密码
    @PostMapping("/submitPassword")
    public String submitPassword(String password, String rePassword, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (!password.equals(rePassword)) {
            model.addAttribute("msg", "修改失败：两次输入密码不同");
        } else {
            user.setUserPassword(password);
            int flag = userService.changePassword(user);
            if (flag != 0) {
                model.addAttribute("msg", "修改成功");
            } else {
                model.addAttribute("msg", "修改失败");
            }
        }
        switch (user.getUserRole()) {
            case "学生":
                return "forward:/changePassword";
            case "资助管理员":
                return "forward:/teacher/changePassword";
            case "系统管理员":
                return "forward:/admin/changePassword";
            default:
                return null;
        }
    }

    //提交修改用户信息
    @PostMapping("/updateUserInfo")
    public String updateUserInfo(String userName, String userPhone, String userEmail, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        user.setUserName(userName);
        user.setUserPhone(userPhone);
        user.setUserEmail(userEmail);
        int flag = userService.updateUser(user);
        if (flag != 0) {
            session.setAttribute("user", user);
            model.addAttribute("msg", "修改成功");
        } else {
            model.addAttribute("msg", "修改失败");
        }
        switch (user.getUserRole()) {
            case "学生":
                return "forward:/userInfo";
            case "资助管理员":
                return "forward:/teacher/userInfo";
            case "系统管理员":
                return "forward:/admin/userInfo";
            default:
                return null;
        }
    }

    //注册界面ajax用户名判重
    @ResponseBody
    @GetMapping("/userName")
    public boolean userName(String userName) {
        User user = userService.finUserByUserName(userName);
        return user == null;
    }

    //添加干扰线
    private void addRandomLine(Graphics g, int width, int height, int numbers) {
        Random r = new Random();
        for (int i = 0; i < numbers; i++) {
            int x1 = r.nextInt(width);
            int x2 = r.nextInt(width);
            int y1 = r.nextInt(height);
            int y2 = r.nextInt(height);
            g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
            g.drawLine(x1, y1, x2, y2);
        }
    }
}
