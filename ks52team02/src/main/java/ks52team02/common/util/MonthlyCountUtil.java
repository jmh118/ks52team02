package ks52team02.common.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MonthlyCountUtil {

	public List<Integer> getMonthlyCounts(List<Map<String, Object>> resultList, String monthKey, String countKey) {
		
        List<Integer> monthlyCounts = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

        for (Map<String, Object> result : resultList) {
            Integer month = result.get(monthKey) != null ? ((Number) result.get(monthKey)).intValue() : null;
            Integer count = result.get(countKey) != null ? ((Number) result.get(countKey)).intValue() : null;

            if (month != null && count != null) {
                monthlyCounts.set(month - 1, count); 
            }
        }

        return monthlyCounts;
    }
	
}
