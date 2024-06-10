package com.final_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.final_project.service.FileService;

@RequestMapping("/file/*")
@Controller
public class FileController {
	@Autowired
	private FileService service;
	
	@GetMapping("thumbnail")
	public ResponseEntity<Resource> thumbnail(String ub_systemname) throws Exception {
		System.out.println("시스템 파일 이름: " + ub_systemname);
		return service.getThumbnailResource(ub_systemname);
	}
	
	@GetMapping("download")
	public ResponseEntity<Resource> download(String systemname, String orgname) throws Exception{
		return service.downloadFile(systemname, orgname);
	}
}





