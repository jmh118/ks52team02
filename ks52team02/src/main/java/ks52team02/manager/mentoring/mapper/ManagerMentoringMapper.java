package ks52team02.manager.mentoring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.manager.mentoring.dto.ManagerMetoringNotice;

@Mapper
public interface ManagerMentoringMapper {
	
	//공고조회
	List<ManagerMetoringNotice> getManagerNoticeList();

}
