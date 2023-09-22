package com.site.blog.my.core.controller.common;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CommonController {

    @GetMapping("/common/kaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
    	// 设置http响应头控制浏览器禁止缓存当前文档内容
    	
    	// HTTP1.1通过Cache-Control控制浏览器缓存。
    	httpServletResponse.setHeader("Cache-Control", "no-store");
    	
    	// HTTP1.0通过Pragma控制浏览器缓存。
        httpServletResponse.setHeader("Pragma", "no-cache");
            
        httpServletResponse.setDateHeader("Expires", 0);
        // 将响应输出流的内容类型设置为PNG格式的图像数据，告诉浏览器接收到的数据是PNG格式的图像数据，这样浏览器就会根据相应的解码器对图像进行解码显示
        httpServletResponse.setContentType("image/png");

        // 创建扭曲干扰的验证码
        ShearCaptcha shearCaptcha= CaptchaUtil.createShearCaptcha(150, 30, 3, 3);
        
        String shearCaptchaCode = shearCaptcha.getCode();

        // 验证码存入session
        httpServletRequest.getSession().setAttribute("verifyCode", shearCaptcha);

        // 输出图片流
        shearCaptcha.write(httpServletResponse.getOutputStream());
    }
}

