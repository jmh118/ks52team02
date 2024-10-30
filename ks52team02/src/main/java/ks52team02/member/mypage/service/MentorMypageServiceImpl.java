package ks52team02.member.mypage.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.common.mapper.CommonMapper;
import ks52team02.member.mypage.dto.MentorInfo;
import ks52team02.member.mypage.dto.MentorWork;
import ks52team02.member.mypage.mapper.MentorMypageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MentorMypageServiceImpl implements MentorMypageService{

	
	private final MentorMypageMapper mentorMypageMapper;
	private final CommonMapper commonMapper;
	
	@Override
	public void addWorkInfo(MentorWork mentorWork) {
		String nextCode = commonMapper.getPrimaryKey("mentor_work_history_details", "mentor_work_career_details_code", "mentor_work_career_details_code_");
		mentorWork.setMentorWorkCode(nextCode);			//테이블명, 자동증가 시킬 컬럼 명, 자동증가 시킬 컬럼 명_
		mentorMypageMapper.addWorkInfo(mentorWork);
	}
	
	@Override
	public void modifyMentor(MentorInfo mentorInfo) {
		mentorMypageMapper.modifyMentor(mentorInfo);
	}
}
