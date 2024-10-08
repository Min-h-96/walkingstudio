package walkingstudio.domain;

import java.util.*;
import lombok.*;
import walkingstudio.domain.*;
import walkingstudio.infra.AbstractEvent;

@Data
@ToString
public class PointCalculated extends AbstractEvent {

    private String pUserId;
    private Double point;
}
