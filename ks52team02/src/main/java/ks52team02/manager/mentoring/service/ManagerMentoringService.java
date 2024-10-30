package ks52team02.manager.mentoring.service;

import java.util.List;

import ks52team02.manager.mentoring.dto.ManagerMetoringNotice;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;



public interface ManagerMentoringService {
	
	PageInfo<ManagerMetoringNotice> getManagerNoticeList(Pageable pageable);

}
