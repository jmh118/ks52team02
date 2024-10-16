package ks52team02.manager.member.service;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;
import ks52team02.manager.member.dto.Member;


public interface ManagerMemberService {

	// 관리자 - 전체 회원 조회
	List<Member> getMemberList();

}
