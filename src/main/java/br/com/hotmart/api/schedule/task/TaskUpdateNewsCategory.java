package br.com.hotmart.api.schedule.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.hotmart.api.service.RankingService;
import br.com.hotmart.api.utils.SpringUtils;

@Component
public class TaskUpdateNewsCategory {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskUpdateNewsCategory.class);
	
	public void executeTask() {
		LOGGER.info("Schedule TASK - START job update News Category");

		RankingService service = SpringUtils.getBean(RankingService.class);
		service.updateNewsCategoryResults();
		
		LOGGER.info("Schedule TASK - END job update News Category");
	}
	
}
