package walkingstudio.external;

import java.util.Date;
import lombok.Data;

@Data
public class WalkingStatByUser {

    private String pUserId;
    private Date baseDate;
    private Integer walking;
    private Integer ranking;
}
