package walkingstudio.domain;

import java.util.*;
import lombok.*;
import walkingstudio.domain.*;
import walkingstudio.infra.AbstractEvent;

@Data
@ToString
public class ChallengeHstCreated extends AbstractEvent {

    private Long id;
}
