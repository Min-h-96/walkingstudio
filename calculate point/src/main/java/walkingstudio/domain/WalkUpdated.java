package walkingstudio.domain;

import java.util.*;
import lombok.*;
import walkingstudio.domain.*;
import walkingstudio.infra.AbstractEvent;

@Data
@ToString
public class WalkUpdated extends AbstractEvent {

    private String pUserId;
    private String teamId;
    private String comId;
    private String baseDate;
    private String baseTime;
    private Integer walking;
}
