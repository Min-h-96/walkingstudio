package walkingstudio.external;

import java.util.Date;
import lombok.Data;

@Data
public class WalkingStatByTeam {

    private String teamId;
    private Date baseDate;
    private Integer walking;
    private Integer ranking;
}
