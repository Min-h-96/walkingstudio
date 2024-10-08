package walkingstudio.domain;

import java.util.*;
import lombok.*;
import walkingstudio.domain.*;
import walkingstudio.infra.AbstractEvent;

@Data
@ToString
public class UsrtFcstHstUpdated extends AbstractEvent {

    private Date baseDate;
    private String baseTime;
    private Integer nx;
    private Integer ny;
    private String category;
    private String fcstValue;
}
