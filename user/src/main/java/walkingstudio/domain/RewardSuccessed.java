package walkingstudio.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import walkingstudio.domain.*;
import walkingstudio.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class RewardSuccessed extends AbstractEvent {

    private String pUserId;
    private Date rewardDt;
    private Boolean rewardYn;
    private Double point;

    public RewardSuccessed(User aggregate) {
        super(aggregate);
    }

    public RewardSuccessed() {
        super();
    }
}
//>>> DDD / Domain Event
