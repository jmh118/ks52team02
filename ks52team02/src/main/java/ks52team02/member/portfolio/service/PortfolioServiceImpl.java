package ks52team02.member.portfolio.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.member.portfolio.dto.Portfolio;
import ks52team02.member.portfolio.mapper.PortfolioMapper;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PortfolioServiceImpl implements PortfolioService {

	private final PortfolioMapper portfolioMapper;
	
	@Override
	public PageInfo<Portfolio> getPortfolioList(Pageable pageable){
		int rowCnt = portfolioMapper.getPortfolioListCount();
		List<Portfolio> contents= portfolioMapper.getPortfolioList(pageable);
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}
	
}
