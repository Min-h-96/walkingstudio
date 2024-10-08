package walkingstudio.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import walkingstudio.TeamwalkingstatApplication;

@Entity
@Table(name = "WalkingStatByTeam_table")
@Data
//<<< DDD / Aggregate Root
public class WalkingStatByTeam {

    @Id
    private String teamId;

    private Date baseDate;

    private Integer walking;

    private Integer ranking;

    public static WalkingStatByTeamRepository repository() {
        WalkingStatByTeamRepository walkingStatByTeamRepository = TeamwalkingstatApplication.applicationContext.getBean(
            WalkingStatByTeamRepository.class
        );
        return walkingStatByTeamRepository;
    }
}
//>>> DDD / Aggregate Root
