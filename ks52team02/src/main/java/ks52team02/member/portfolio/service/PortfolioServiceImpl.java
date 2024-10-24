package ks52team02.member.portfolio.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.member.portfolio.dto.Portfolio;
import ks52team02.member.portfolio.mapper.PortfolioMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PortfolioServiceImpl implements PortfolioService {

	private final PortfolioMapper portfolioMapper;
	
	@Override
	public List<Portfolio> getPortfolioList(){
		
		List<Portfolio> PortfolioList= portfolioMapper.getPortfolioList();
		
		return PortfolioList;
	}
	
}
