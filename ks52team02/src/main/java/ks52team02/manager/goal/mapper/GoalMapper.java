package ks52team02.manager.goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.manager.goal.dto.MemberGoal;

@Mapper
public interface GoalMapper {

	// 회원(멘티)의 목표 설정 조회
	List<MemberGoal> getMemberGoalList();
}
