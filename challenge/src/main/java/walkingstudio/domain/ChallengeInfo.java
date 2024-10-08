package walkingstudio.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import walkingstudio.ChallengeApplication;

@Entity
@Table(name = "ChallengeInfo_table")
@Data
//<<< DDD / Aggregate Root
public class ChallengeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long challengeId;

    private Date missionStDt;

    private Date missionFnDt;

    private String range;

    private Double point;

    public static ChallengeInfoRepository repository() {
        ChallengeInfoRepository challengeInfoRepository = ChallengeApplication.applicationContext.getBean(
            ChallengeInfoRepository.class
        );
        return challengeInfoRepository;
    }
}
//>>> DDD / Aggregate Root
