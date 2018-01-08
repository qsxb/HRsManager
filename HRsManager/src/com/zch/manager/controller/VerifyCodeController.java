package com.zch.manager.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zch.manager.util.VerifyCode;

/**
 * Created by ch.zhang on 2017年10月6日 下午1:19:56
 */
@Controller
public class VerifyCodeController {

	@RequestMapping("/vCode")
	public void vCode(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException {
		VerifyCode vc = new VerifyCode();
		BufferedImage image = vc.getImage();
		// 将图片的文本保存到session中
		request.getSession().setAttribute("vCode", vc.getText());
		System.out.println("vccode" + vc.getText());
		VerifyCode.output(image, response.getOutputStream());
	}
}
