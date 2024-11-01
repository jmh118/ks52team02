package ks52team02.manager.honor.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.manager.honor.dto.CriteriaHonorMentor;
import ks52team02.manager.honor.mapper.ManagerHonorMapper;
import ks52team02.manager.member.dto.Member;
import ks52team02.manager.review.dto.MentorReviewData;
import ks52team02.manager.review.mapper.ManagerReviewMapper;
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
	public List<MentorReviewData> getMentorReviewsDataList() {

		CriteriaHonorMentor criteria = managerHonorMapper.getCriteriaHonorMentor();
		
		int reveiwCntCriteria = criteria.getReveiwCntCriteria();
		double reviewAvgCriteria = criteria.getReviewAvgCriteria();
		
		List<MentorReviewData> mentorReviewsDataList = managerReviewMapper.getMentorReviewsDataList();
		
		for(MentorReviewData data : mentorReviewsDataList) {
			if(data.getMentorReviewAvg() >= reviewAvgCriteria && data.getMentorReviewCnt() >= reveiwCntCriteria) {
				data.setIsapprove(true);
				data.setCancel(false);
			} else {
				data.setIsapprove(false);
				data.setCancel(true);
			}
		}
		
		log.info("확인 : {}", mentorReviewsDataList);
		
		return mentorReviewsDataList;
	}
	
	@Override
	public List<Member> getHornorMentorList() {
		
		List<String> honorMentorIdList = managerHonorMapper.getHonorMentorIdList();
		List<Member> honorMentorList = managerHonorMapper.getHornorMentorList(honorMentorIdList);
		
		return honorMentorList;
	}
}
