package com.final_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.final_project.domain.dto.MemberDTO;
import com.final_project.mapper.MemberMapper;

@Service
public class MemberServicelmpl implements MemberService {
	
	@Autowired
	private MemberMapper mmapper;

	@Override
	public MemberDTO getMember(String loginUser) { 
		MemberDTO mdto = mmapper.getMember(loginUser);
		return mdto;
	}

}
