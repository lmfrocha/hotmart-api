package br.com.hotmart.api.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import br.com.hotmart.api.service.RankingService;

/**
 * 
 * @author l.rocha
 *
 */
@Configuration
@EnableScheduling
public class TaskSchedule {
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskSchedule.class);
	@Autowired
	private RankingService rankingService;
	
	/**
	 * Repeat each 6 hours
	 */
	@Scheduled(cron = "0 0 0/6 * * *")
	public void taskUpdateNewsCategory() {
		LOGGER.info("START: Consuming https://newsapi.org/docs/endpoints/top-headlines ");
		rankingService.updateNewsCategoryResults();
		LOGGER.info("END: Consuming https://newsapi.org/docs/endpoints/top-headlines ");
	}
	
	/**
	 * Repeat after every day at 01:00
	 */
	@Scheduled(cron = "0 0 0/1 * * *")
	public void taskUpdateProductScore() {
		LOGGER.info("START: Update Product Score by category ");
		rankingService.updateProductScoreByCategory();
		LOGGER.info("END: Update Product Score by category ");
	}
	
}
