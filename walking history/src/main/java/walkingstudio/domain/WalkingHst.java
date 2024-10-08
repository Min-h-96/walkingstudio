package walkingstudio.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import walkingstudio.WalkingHistoryApplication;
import walkingstudio.domain.WalkUpdated;

@Entity
@Table(name = "WalkingHst_table")
@Data
//<<< DDD / Aggregate Root
public class WalkingHst {

    @Id
    private String pUserId;

    private String teamId;

    private String comId;

    private String baseDate;

    private String baseTime;

    private Integer walking;

    @PostPersist
    public void onPostPersist() {
        WalkUpdated walkUpdated = new WalkUpdated(this);
        walkUpdated.publishAfterCommit();
    }

    public static WalkingHstRepository repository() {
        WalkingHstRepository walkingHstRepository = WalkingHistoryApplication.applicationContext.getBean(
            WalkingHstRepository.class
        );
        return walkingHstRepository;
    }
}
//>>> DDD / Aggregate Root
