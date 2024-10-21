package ks52team02.manager.goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.manager.goal.dto.MemberGoal;
import ks52team02.manager.goal.dto.MemberGoalRecord;

@Mapper
public interface GoalMapper {

	// 회원(멘티)의 목표 진행 조회
	List<MemberGoalRecord> getMenteeGoalRecordList();
	
	// 회원(멘티)의 목표 설정 조회
	List<MemberGoal> getMenteeGoalList();
	
}
