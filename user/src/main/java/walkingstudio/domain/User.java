package walkingstudio.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import walkingstudio.UserApplication;
import walkingstudio.domain.RewardSuccessed;

@Entity
@Table(name = "user_info", schema= "walk")
@Data
@Getter
@Setter
//<<< DDD / Aggregate Root
public class User {

    @Id
    @Column(name = "p_user_id", length = 30, nullable = false)
    private String pUserId;

    @Column(name = "com_user_id", length = 30)
    private String comUserId;

    @Column(name = "com_id", length = 30)
    private String comId;

    @Column(name = "group_id", length = 30)
    private String groupId;

    @Column(name = "group_name", length = 30)
    private String groupName;

    @Column(name = "team_id", length = 30)
    private String teamId;

    @Column(name = "otp", length = 6)
    private String otp;

    @Column(name = "point")
    private Double point;

    @Column(name = "cret_ip", length = 30)
    private String cretIp;

    @Column(name = "cret_dt")
    private LocalDateTime cretDt;

    @Column(name = "mod_ip", length = 30)
    private String modIp;

    @Column(name = "mod_dt")
    private LocalDateTime modDt;

    @Column(name = "use_yn")
    private Boolean useYn;

    @Column(name = "p_user_name", length = 30)
    private String pUserName;

    @ManyToOne
    @JoinColumn(name = "com_id", insertable = false, updatable = false)
    private ComInfo comInfo;

    @ManyToOne
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private TeamInfo teamInfo;
    @PostPersist
    public void onPostPersist() {
        RewardSuccessed rewardSuccessed = new RewardSuccessed(this);
        rewardSuccessed.publishAfterCommit();
    }

    public static UserRepository repository() {
        UserRepository userRepository = UserApplication.applicationContext.getBean(
            UserRepository.class
        );
        return userRepository;
    }

    //<<< Clean Arch / Port Method
    public static void increasePoint(PointCalculated pointCalculated) {
        //implement business logic here:

        /** Example 1:  new item 
        User user = new User();
        repository().save(user);

        RewardSuccessed rewardSuccessed = new RewardSuccessed(user);
        rewardSuccessed.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(pointCalculated.get???()).ifPresent(user->{
            
            user // do something
            repository().save(user);

            RewardSuccessed rewardSuccessed = new RewardSuccessed(user);
            rewardSuccessed.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void increasePoint(ChallengeHstCreated challengeHstCreated) {
        //implement business logic here:

        /** Example 1:  new item 
        User user = new User();
        repository().save(user);

        RewardSuccessed rewardSuccessed = new RewardSuccessed(user);
        rewardSuccessed.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(challengeHstCreated.get???()).ifPresent(user->{
            
            user // do something
            repository().save(user);

            RewardSuccessed rewardSuccessed = new RewardSuccessed(user);
            rewardSuccessed.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
