package walkingstudio.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import walkingstudio.domain.*;
import walkingstudio.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class WeatherInfoUpdated extends AbstractEvent {

    private Date baseDate;
    private String baseTime;
    private Integer nx;
    private Integer ny;
    private Double weight;

    public WeatherInfoUpdated(PointStandardInfo aggregate) {
        super(aggregate);
    }

    public WeatherInfoUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
