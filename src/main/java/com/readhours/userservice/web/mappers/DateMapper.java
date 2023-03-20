package com.readhours.userservice.web.mappers;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class DateMapper {
    public LocalDateTime asOffsetDateTime(Timestamp ts){
        if (ts != null){
            return ts.toLocalDateTime();
        } else {
            return null;
        }
    }

    public Timestamp asTimestamp(LocalDateTime localDateTime){
        if(localDateTime != null) {
            return Timestamp.valueOf(localDateTime);
        } else {
            return null;
        }
    }
}
