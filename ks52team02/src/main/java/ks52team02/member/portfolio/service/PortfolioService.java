package ks52team02.member.portfolio.service;

import java.util.List;

import ks52team02.member.portfolio.dto.Portfolio;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;

public interface PortfolioService {

	PageInfo<Portfolio> getPortfolioList(Pageable pageable);
	
}
