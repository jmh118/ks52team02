package ks52team02.member.honor.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.manager.honor.mapper.ManagerHonorMapper;
import ks52team02.member.honor.dto.hornorMentor;
import ks52team02.member.honor.mapper.MemberHonorMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberHonorServiceImpl implements MemberHonorService {
	
	private final ManagerHonorMapper managerHonorMapper;
	private final MemberHonorMapper memberHonorMapper;
	
	@Override
	public List<hornorMentor> getHonorMentorList() {
		
		List<String> honorMentorIdList = managerHonorMapper.getHonorMentorIdList();
		
		List<hornorMentor> honorMentorList = memberHonorMapper.getHonorMentorList(honorMentorIdList);
		
		return honorMentorList;
	}

}
