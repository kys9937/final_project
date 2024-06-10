package com.final_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.final_project.domain.dto.Criteria;
import com.final_project.domain.dto.ReplyDTO;
import com.final_project.domain.dto.ReplyPageDTO;
import com.final_project.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	private ReplyMapper rmapper;

	@Override
	public ReplyDTO regist(ReplyDTO reply) {
		//등록 데이터 로직
		if(rmapper.insertReply(reply) == 1) {
			//검색 데이터 로직
			return rmapper.getLastReply(reply.getUserid());
		}
		return null;
	}

	@Override
	public ReplyDTO getDetail(long replynum) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ReplyPageDTO getList(Criteria cri, long boardnum) {
		return new ReplyPageDTO(rmapper.getTotal(boardnum), rmapper.getList(cri, boardnum));
	}

	@Override
	public boolean modify(ReplyDTO reply) {
		return rmapper.updateReply(reply) == 1;
	}

	@Override
	public boolean remove(long replynum) {
		return rmapper.deleteReply(replynum) == 1;
	}
	
}





