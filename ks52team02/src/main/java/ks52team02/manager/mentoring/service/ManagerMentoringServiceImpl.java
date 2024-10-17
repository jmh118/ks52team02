package ks52team02.manager.mentoring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.manager.mentoring.dto.ManagerMetoringNotice;
import ks52team02.manager.mentoring.mapper.ManagerMentoringMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ManagerMentoringServiceImpl implements ManagerMentoringService{
	
	private final ManagerMentoringMapper managerMentoringMapper;
	
	@Override
	public List<ManagerMetoringNotice> getManagerNoticeList() {
		List<ManagerMetoringNotice> noticeList = managerMentoringMapper.getManagerNoticeList();
		return noticeList;
	}
	

}
