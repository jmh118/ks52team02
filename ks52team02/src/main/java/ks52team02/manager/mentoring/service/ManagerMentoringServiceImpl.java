package ks52team02.manager.mentoring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.manager.mentoring.dto.ManagerMetoringNotice;
import ks52team02.manager.mentoring.mapper.ManagerMentoringMapper;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ManagerMentoringServiceImpl implements ManagerMentoringService{
	
	private final ManagerMentoringMapper managerMentoringMapper;
	
	@Override
	public PageInfo<ManagerMetoringNotice> getManagerNoticeList(Pageable pageable) {
		
		int rowCnt = managerMentoringMapper.getNoticeListCount();
		
		List<ManagerMetoringNotice> contents = managerMentoringMapper.getManagerNoticeList(pageable);
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}

}
