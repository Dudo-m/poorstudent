package com.edu;

import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

public class Test1 {
    @Test
    public void t(){
        //md5加盐加密
        String passwordSalt = "123"+"x@7faqgjw";
        String md5UserPassword = DigestUtils.md5DigestAsHex(passwordSalt.getBytes());
        System.out.println("md5Str = " + md5UserPassword);
    }
}
