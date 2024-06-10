package com.final_project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.final_project.domain.dto.Criteria;
import com.final_project.domain.dto.ReplyDTO;

@Mapper
public interface ReplyMapper {
	int insertReply(ReplyDTO reply);
	
	ReplyDTO getDetail(long replynum);
	ReplyDTO getLastReply(String userid);
	List<ReplyDTO> getList(Criteria cri, long boardnum);
	long getTotal(long boardnum);
	
	
	int updateReply(ReplyDTO reply);
	
	int deleteReply(long replynum);

	int deleteRepliesByBoardnum(long boardnum);

	int getRecentReplyCnt(long boardnum);
}










