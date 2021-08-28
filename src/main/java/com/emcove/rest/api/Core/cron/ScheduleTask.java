package com.emcove.rest.api.Core.cron;

import com.emcove.rest.api.Core.response.Entrepreneurship;
import com.emcove.rest.api.Core.service.EntrepreneurshipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class ScheduleTask {
    private static final Logger log = LoggerFactory.getLogger(ScheduleTask.class);

    @Autowired
    EntrepreneurshipService entrepreneurshipService;

    // Todos los dias a las 3 am
    @Scheduled(cron = "0 0 3 * * *")
    //@Scheduled(cron = "0 * * * * *") cada un minuto
    public void checkExpiredSubscriptions() {
        log.info("Checking for expired subscriptions");
        entrepreneurshipService.checkExpiredSubscriptions(Calendar.getInstance().getTime());
    }
}
