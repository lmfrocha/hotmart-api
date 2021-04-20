package br.com.hotmart.api.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.hotmart.api.schedule.task.TaskUpdateNewsCategory;
import br.com.hotmart.api.schedule.task.TaskUpdateProductScore;

@Component
public class TaskSchedule {
	
	@Autowired
	private TaskUpdateNewsCategory taskUpdateNewsCategory;
	
	@Autowired
	private TaskUpdateProductScore taskUpdateProductScore;
	
	/**
	 * Repeat each 6 hours
	 */
	@Scheduled(cron="0 0 0/6 * * ?")
	public void taskUpdateNewsCategory() {
		if (!checkIfLeader()) {
			return;
		}
		taskUpdateNewsCategory.executeTask();
	}
	
	/**
	 * Repeat after every day at 00:01
	 */
	@Scheduled(cron="0 1 0 * * ?")
	public void taskUpdateProductScore() {
		if (!checkIfLeader()) {
			return;
		}
		taskUpdateProductScore.executeTask();
	}
	
	private boolean checkIfLeader() {
		return true;
	}
	
}
