package walkingstudio.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import walkingstudio.CompanyWalkingStatApplication;
import walkingstudio.domain.ComStatUpdated;

@Entity
@Table(name = "WalkingStatByCompany_table")
@Data
//<<< DDD / Aggregate Root
public class WalkingStatByCompany {

    @Id
    private String comId;

    private Date baseDate;

    private Integer walking;

    private Integer ranking;

    @PostPersist
    public void onPostPersist() {
        ComStatUpdated comStatUpdated = new ComStatUpdated(this);
        comStatUpdated.publishAfterCommit();
    }

    public static WalkingStatByCompanyRepository repository() {
        WalkingStatByCompanyRepository walkingStatByCompanyRepository = CompanyWalkingStatApplication.applicationContext.getBean(
            WalkingStatByCompanyRepository.class
        );
        return walkingStatByCompanyRepository;
    }

    //<<< Clean Arch / Port Method
    public static void updateWalk(UserStatUpdated userStatUpdated) {
        //implement business logic here:

        /** Example 1:  new item 
        WalkingStatByCompany walkingStatByCompany = new WalkingStatByCompany();
        repository().save(walkingStatByCompany);

        */

        /** Example 2:  finding and process
        
        repository().findById(userStatUpdated.get???()).ifPresent(walkingStatByCompany->{
            
            walkingStatByCompany // do something
            repository().save(walkingStatByCompany);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
