package walkingstudio.domain;

import java.util.*;
import lombok.*;
import walkingstudio.domain.*;
import walkingstudio.infra.AbstractEvent;

@Data
@ToString
public class UserStatUpdated extends AbstractEvent {

    private String pUserId;
    private Date baseDate;
    private Integer walking;
    private Integer ranking;
}
