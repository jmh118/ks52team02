package ks52team02.manager.honor.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.manager.honor.dto.CriteriaHonorMentor;
import ks52team02.manager.honor.mapper.ManagerHonorMapper;
import ks52team02.manager.member.dto.Member;
import ks52team02.manager.review.dto.MentorReviewData;
import ks52team02.manager.review.mapper.ManagerReviewMapper;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class ManagerHonorServiceImpl implements ManagerHonorService {

	private final ManagerHonorMapper managerHonorMapper;
	private  final ManagerReviewMapper managerReviewMapper; 
	
	@Override
	public int honorMentorCancel(String memeberId) {

		return managerHonorMapper.honorMentorCancel(memeberId);
	}
	
	
	@Override
	public int honorMentorApprove(String memeberId) {
		
		return managerHonorMapper.honorMentorApprove(memeberId);
	}

	
	@Override
	public PageInfo<MentorReviewData> getMentorReviewsDataList(Pageable pageable) {

		CriteriaHonorMentor criteria = managerHonorMapper.getCriteriaHonorMentor();
		
		int reveiwCntCriteria = criteria.getReveiwCntCriteria();
		double reviewAvgCriteria = criteria.getReviewAvgCriteria();
		
		int rowCnt = managerReviewMapper.getMentorReviewsDataListCnt();
		List<MentorReviewData> contents = managerReviewMapper.getMentorReviewsDataList(pageable);
		
		for(MentorReviewData data : contents) {
			if(data.getMentorReviewAvg() >= reviewAvgCriteria && data.getMentorReviewCnt() >= reveiwCntCriteria) {
				data.setIsapprove(true);
				data.setCancel(false);
			} else {
				data.setIsapprove(false);
				data.setCancel(true);
			}
		}
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}
	
	@Override
	public PageInfo<Member> getHornorMentorList(Pageable pageable) {
		
		List<String> honorMentorIdList = managerHonorMapper.getHonorMentorIdList();
		int rowCnt = managerHonorMapper.getHornorMentorListCnt(honorMentorIdList);
		
		Map<String, Object> params = new HashMap<>();
	    params.put("honorMenotrId", honorMentorIdList);
	    params.put("rowPerPage", pageable.getRowPerPage());  
	    params.put("offset", pageable.getOffset());
	    
	    
	    List<Member> contents = managerHonorMapper.getHornorMentorList(params);
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}
}
