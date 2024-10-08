package walkingstudio.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import walkingstudio.domain.*;
import walkingstudio.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class UserStatUpdated extends AbstractEvent {

    private String pUserId;
    private Date baseDate;
    private Integer walking;
    private Integer ranking;

    public UserStatUpdated(WalkingStatByUser aggregate) {
        super(aggregate);
    }

    public UserStatUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
