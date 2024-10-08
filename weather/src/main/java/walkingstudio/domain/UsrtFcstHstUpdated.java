package walkingstudio.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import walkingstudio.domain.*;
import walkingstudio.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class UsrtFcstHstUpdated extends AbstractEvent {

    private Date baseDate;
    private String baseTime;
    private Integer nx;
    private Integer ny;
    private String category;
    private String fcstValue;

    public UsrtFcstHstUpdated(WeatherStnInfo aggregate) {
        super(aggregate);
    }

    public UsrtFcstHstUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
