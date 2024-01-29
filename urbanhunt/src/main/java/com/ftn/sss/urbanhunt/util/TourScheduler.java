package com.ftn.sss.urbanhunt.util;

import com.ftn.sss.urbanhunt.service.interfaces.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class TourScheduler {

    private final TourService tourService;

    @Autowired
    public TourScheduler(TourService tourService) {
        this.tourService = tourService;
    }

    public static LocalDateTime convertTimestampToLocalDateTime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static long convertLocalDateTimeToTimestamp(LocalDateTime time) {
        Instant instant = time.atZone(ZoneId.systemDefault()).toInstant();
        return instant.toEpochMilli();
    }

    @Scheduled(fixedRate = 60000)
    public void updateTourStatus() {
        tourService.updateTourFinished();
    }
}

