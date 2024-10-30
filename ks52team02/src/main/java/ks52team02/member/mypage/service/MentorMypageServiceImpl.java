package ks52team02.member.mypage.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.member.mypage.dto.MentorInfo;
import ks52team02.member.mypage.mapper.MentorMypageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MentorMypageServiceImpl implements MentorMypageService{

	
	private final MentorMypageMapper mentorMypageMapper;
	
	@Override
	public void modifyMentor(MentorInfo mentorInfo) {
		mentorMypageMapper.modifyMentor(mentorInfo);
	}
}
