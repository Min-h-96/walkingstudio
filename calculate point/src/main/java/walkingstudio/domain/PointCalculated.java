package walkingstudio.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import walkingstudio.domain.*;
import walkingstudio.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class PointCalculated extends AbstractEvent {

    private String pUserId;
    private Double point;

    public PointCalculated(PointStandardInfo aggregate) {
        super(aggregate);
    }

    public PointCalculated() {
        super();
    }
}
//>>> DDD / Domain Event
