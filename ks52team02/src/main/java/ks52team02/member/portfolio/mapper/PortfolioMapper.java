package ks52team02.member.portfolio.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.member.portfolio.dto.Portfolio;
import ks52team02.page.Pageable;

@Mapper
public interface PortfolioMapper {

	List<Portfolio> getPortfolioList(Pageable pageable);
	
	int getPortfolioListCount();
	
	
	
	Portfolio getPortfolioDetailByCode(String portfolioCode);
	
}
