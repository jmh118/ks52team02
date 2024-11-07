package ks52team02.common.util;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DateFormatterUtil {
	
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 (E)");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("H시");


    public String formatDate(String inputDate) {
        try {
            LocalDate date = LocalDate.parse(inputDate, DateTimeFormatter.ISO_DATE);
            return date.format(DATE_FORMATTER);
        } catch (Exception e) {
            log.error("날짜 포맷팅 중 오류 발생: ", e);
            return inputDate; 
        }
    }

    public String formatTime(String inputTime) {
        try {
            String[] times = inputTime.split(" ~ ");
            LocalTime startTime = LocalTime.parse(times[0], DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime endTime = LocalTime.parse(times[1], DateTimeFormatter.ofPattern("HH:mm"));
            return startTime.format(TIME_FORMATTER) + " ~ " + endTime.format(TIME_FORMATTER);
        } catch (Exception e) {
            log.error("시간 포맷팅 중 오류 발생: ", e);
            return inputTime; 
        }
    }

}
