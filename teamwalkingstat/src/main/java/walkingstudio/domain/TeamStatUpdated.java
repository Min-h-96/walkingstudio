package walkingstudio.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import walkingstudio.domain.*;
import walkingstudio.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class TeamStatUpdated extends AbstractEvent {

    private String teamId;
    private Date baseDate;
    private Integer walking;
    private Integer ranking;

    public TeamStatUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
