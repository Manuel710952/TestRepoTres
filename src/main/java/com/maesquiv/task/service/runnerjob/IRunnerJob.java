package com.maesquiv.task.service.runnerjob;

import org.quartz.Scheduler;

public interface IRunnerJob {
	void execute(Scheduler scheduler);
}
