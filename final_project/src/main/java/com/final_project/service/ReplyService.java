package com.final_project.service;

import com.final_project.domain.dto.Criteria;
import com.final_project.domain.dto.ReplyDTO;
import com.final_project.domain.dto.ReplyPageDTO;

public interface ReplyService {
	ReplyDTO regist(ReplyDTO reply);
	
	ReplyDTO getDetail(long replynum);
	//목록 가져가기
	ReplyPageDTO getList(Criteria cri, long boardnum);
	
	boolean modify(ReplyDTO reply);
	
	boolean remove(long replynum);

}
