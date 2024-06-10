package com.final_project.service;

import com.final_project.domain.dto.MemberDTO;

public interface MemberService {
	MemberDTO getMember(String loginUser);
}
