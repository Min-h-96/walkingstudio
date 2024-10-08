package walkingstudio.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import walkingstudio.domain.*;
import walkingstudio.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class WalkUpdated extends AbstractEvent {

    private String pUserId;
    private String teamId;
    private String comId;
    private String baseDate;
    private String baseTime;
    private Integer walking;

    public WalkUpdated(WalkingHst aggregate) {
        super(aggregate);
    }

    public WalkUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
