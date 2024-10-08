package walkingstudio.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import walkingstudio.ChallengeApplication;
import walkingstudio.domain.ChallengeHstCreated;

@Entity
@Table(name = "ChallengeHst_table")
@Data
//<<< DDD / Aggregate Root
public class ChallengeHst {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long challengeId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String pUserId;

    private Date rewardDt;

    private Boolean rewardYn;

    @PostPersist
    public void onPostPersist() {
        ChallengeHstCreated challengeHstCreated = new ChallengeHstCreated(this);
        challengeHstCreated.publishAfterCommit();
    }

    public static ChallengeHstRepository repository() {
        ChallengeHstRepository challengeHstRepository = ChallengeApplication.applicationContext.getBean(
            ChallengeHstRepository.class
        );
        return challengeHstRepository;
    }

    public void checkWalkingCount(
        CheckWalkingCountCommand checkWalkingCountCommand
    ) {
        walkingstudio.external.CheckWalkingCountQuery checkWalkingCountQuery = new walkingstudio.external.CheckWalkingCountQuery();
        ChallengeHstApplication.applicationContext
            .getBean(walkingstudio.external.WalkingStatByCompanyService.class)
            .checkWalkingCount(checkWalkingCountQuery);
    }
}
//>>> DDD / Aggregate Root
