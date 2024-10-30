package ks52team02.manager.mentoring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.manager.mentoring.dto.ManagerMetoringNotice;
import ks52team02.page.Pageable;

@Mapper
public interface ManagerMentoringMapper {
	
	//공고조회
	List<ManagerMetoringNotice> getManagerNoticeList(Pageable pageable);

	// 멘토링 공고 갯수 조회
	int getNoticeListCount();

}
