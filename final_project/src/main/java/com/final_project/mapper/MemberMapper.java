package com.final_project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.final_project.domain.dto.BoardDTO;
import com.final_project.domain.dto.Criteria;
import com.final_project.domain.dto.MemberDTO;

@Mapper
public interface MemberMapper {
	MemberDTO getMember(String loginUser);
}
