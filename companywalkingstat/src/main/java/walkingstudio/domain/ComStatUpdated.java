package walkingstudio.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import walkingstudio.domain.*;
import walkingstudio.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ComStatUpdated extends AbstractEvent {

    private String comId;
    private Date baseDate;
    private Integer walking;
    private Integer ranking;

    public ComStatUpdated(WalkingStatByCompany aggregate) {
        super(aggregate);
    }

    public ComStatUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
