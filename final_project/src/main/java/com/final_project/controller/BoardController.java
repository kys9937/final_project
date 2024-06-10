package com.final_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.final_project.domain.dto.BoardDTO;
import com.final_project.domain.dto.Criteria;
import com.final_project.domain.dto.PageDTO;
import com.final_project.domain.dto.write_twoDTO;
import com.final_project.service.BoardService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/board/*")
public class BoardController {
	@Autowired
	private BoardService service;
	
	@GetMapping("list")
	public void list(Criteria cri, Model model) {
		System.out.println(cri);
		List<BoardDTO> list = service.getList(cri);
		model.addAttribute("list",list);			//총 개시글의 수
		model.addAttribute("pageMaker",new PageDTO(service.getTotal(cri), cri));
//		model.addAttribute("hot_board",service.getHotBoardList(list));
		model.addAttribute("reply_cnt_list",service.getReplyCntList(list));
	}
	
	@GetMapping("write")
				//model.addAttribute() 를 바로 해줌
	public void write(@ModelAttribute("cri") Criteria cri, Model model) { }
	
	@PostMapping("write")
	public String write(BoardDTO board, MultipartFile[] files, Criteria cri) throws Exception {
		if(service.regist(board, files)) {
			long boardnum = service.getLastNum(board.getUserid());
			return "redirect:/board/get"+cri.getListLink()+"&boardnum="+boardnum;
		}
		else {
			return "redirect:/board/list"+cri.getListLink();
		}
	}
	@GetMapping(value={"get", "modify"})
	public String get(Criteria cri, long boardnum, HttpServletRequest req, HttpServletResponse resp, Model model) {
		String requestURI = req.getRequestURI();
		model.addAttribute("cri",cri);
		HttpSession session = req.getSession();
		//boardnum의 모든 데이터 대입
		BoardDTO board = service.getDetail(boardnum);
		model.addAttribute("board",board);
		System.out.print("board: "+ boardnum);
		model.addAttribute("files",service.getFiles(boardnum));
		String loginUser = (String)session.getAttribute("loginUser");
		
		if(requestURI.contains("modify")) {
			return "board/modify";
		}
		
		if(!board.getUserid().equals(loginUser)) {
			Cookie[] cookies = req.getCookies();
			Cookie read_board = null;
			if(cookies != null) {
				for(Cookie cookie : cookies) {
					//ex) 1번 게시글을 조회하고자 클릭했을 때에는 "read_board1" 쿠키를 찾음
					if(cookie.getName().equals("read_board"+boardnum)) {
						read_board = cookie;
						break;
					}
				}
			}
			
			//read_board가 null이라는 뜻은 위에서 쿠키를 찾았을 때 존재하지 않았다는 뜻
			//첫 조회거나 조회한지 1시간이 지난 후
			if(read_board == null) {
				service.increaseReadCount(boardnum);
				//read_board1 이름의 쿠키(유효기간:3600초)를 생성해서 클라이언트에 저장
				Cookie cookie = new Cookie("read_board"+boardnum, "r");
				cookie.setMaxAge(3600);
				resp.addCookie(cookie);
			}
			
			
		}
		return "board/get";
	}
	@PostMapping("modify")
	public String modify(BoardDTO board, MultipartFile[] files, String updateCnt, Criteria cri, Model model) throws Exception {
		System.out.println("Controller : "+updateCnt);
		if(service.modify(board, files, updateCnt)) {
			return "redirect:/board/get"+cri.getListLink()+"&boardnum="+board.getBoardnum();
		}
		else {
			return "redirect:/board/get"+cri.getListLink()+"&boardnum="+board.getBoardnum();
		}
	}
	
	@GetMapping("remove")
	public String remove(Criteria cri, long boardnum,HttpServletRequest req) {
		String loginUser = (String)req.getSession().getAttribute("loginUser");
		BoardDTO board = service.getDetail(boardnum);
		if(loginUser.equals(board.getUserid())) {
			if(service.remove(boardnum)) {
				return "redirect:/board/list"+cri.getListLink();
			}
		}
		return "redirect:/board/get"+cri.getListLink()+"&boardnum="+boardnum;
		
	}
	
	@GetMapping("text_area")
	public void input_text() {
	}
	
	@PostMapping("write_two")
	@ResponseBody
	public String write_two(@RequestBody write_twoDTO htmlRequest) {
        String html = htmlRequest.getHtml();
        System.out.println("Received HTML: " + html);
        // 추가적인 처리 로직
        return "HTML received successfully";
    }
		
	
}












