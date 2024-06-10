package com.final_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.final_project.domain.dto.MemberDTO;
import com.final_project.domain.dto.UserDTO;
import com.final_project.service.BoardService;
import com.final_project.service.FileService;
import com.final_project.service.MemberService;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/member/*")
@Controller
public class MemberController {
	@Autowired
	private MemberService Mservice;
	@Autowired
	private BoardService bservice;
	
	@GetMapping("main")
	public void main(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);// 기존 세션이 있을 경우 가져옴, 없으면 null 반환
		if(session != null) {
			String loginUser = (String) session.getAttribute("loginUser");
			MemberDTO udto = Mservice.getMember(loginUser);
			model.addAttribute("loginUser", loginUser);
			model.addAttribute("boardCnt", bservice.getUserTotal(loginUser));
			model.addAttribute("user", udto);
			
		}
	}
}
