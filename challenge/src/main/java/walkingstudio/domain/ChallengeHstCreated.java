package walkingstudio.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import walkingstudio.domain.*;
import walkingstudio.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ChallengeHstCreated extends AbstractEvent {

    private Long id;

    public ChallengeHstCreated(ChallengeHst aggregate) {
        super(aggregate);
    }

    public ChallengeHstCreated() {
        super();
    }
}
//>>> DDD / Domain Event
