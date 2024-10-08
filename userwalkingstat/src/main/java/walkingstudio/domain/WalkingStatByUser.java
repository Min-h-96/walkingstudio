package walkingstudio.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import walkingstudio.UserwalkingstatApplication;
import walkingstudio.domain.UserStatUpdated;

@Entity
@Table(name = "WalkingStatByUser_table")
@Data
//<<< DDD / Aggregate Root
public class WalkingStatByUser {

    @Id
    private String pUserId;

    private Date baseDate;

    private Integer walking;

    private Integer ranking;

    @PostPersist
    public void onPostPersist() {
        UserStatUpdated userStatUpdated = new UserStatUpdated(this);
        userStatUpdated.publishAfterCommit();
    }

    public static WalkingStatByUserRepository repository() {
        WalkingStatByUserRepository walkingStatByUserRepository = UserwalkingstatApplication.applicationContext.getBean(
            WalkingStatByUserRepository.class
        );
        return walkingStatByUserRepository;
    }
}
//>>> DDD / Aggregate Root
