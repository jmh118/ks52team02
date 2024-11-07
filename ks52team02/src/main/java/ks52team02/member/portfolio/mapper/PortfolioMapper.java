package ks52team02.member.portfolio.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.member.portfolio.dto.Portfolio;

@Mapper
public interface PortfolioMapper {

	List<Portfolio> getPortfolioList();
	
	Portfolio getPortfolioDetailByCode(String portfolioCode);
	
}
