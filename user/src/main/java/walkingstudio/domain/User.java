package walkingstudio.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import walkingstudio.UserApplication;
import walkingstudio.domain.RewardSuccessed;

@Entity
@Table(name = "User_table")
@Data
//<<< DDD / Aggregate Root
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pUserId;

    private String pUserName;

    private String comId;

    private String teamId;

    private String otp;

    private String point;

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
