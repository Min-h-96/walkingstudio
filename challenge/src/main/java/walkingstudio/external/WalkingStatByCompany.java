package walkingstudio.external;

import java.util.Date;
import lombok.Data;

@Data
public class WalkingStatByCompany {

    private String comId;
    private Date baseDate;
    private Integer walking;
    private Integer ranking;
}
