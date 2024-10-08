package walkingstudio.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import walkingstudio.CalculatePointApplication;
import walkingstudio.domain.PointCalculated;
import walkingstudio.domain.WeatherInfoUpdated;

@Entity
@Table(name = "PointStandardInfo_table")
@Data
//<<< DDD / Aggregate Root
public class PointStandardInfo {

    @Id
    private Date baseDate;

    private String baseTime;

    private Integer nx;

    private Integer ny;

    private Double weight;

    @PostPersist
    public void onPostPersist() {
        PointCalculated pointCalculated = new PointCalculated(this);
        pointCalculated.publishAfterCommit();

        WeatherInfoUpdated weatherInfoUpdated = new WeatherInfoUpdated(this);
        weatherInfoUpdated.publishAfterCommit();
    }

    public static PointStandardInfoRepository repository() {
        PointStandardInfoRepository pointStandardInfoRepository = CalculatePointApplication.applicationContext.getBean(
            PointStandardInfoRepository.class
        );
        return pointStandardInfoRepository;
    }

    //<<< Clean Arch / Port Method
    public static void updateWeatherInfo(
        UsrtFcstHstUpdated usrtFcstHstUpdated
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        PointStandardInfo pointStandardInfo = new PointStandardInfo();
        repository().save(pointStandardInfo);

        */

        /** Example 2:  finding and process
        
        repository().findById(usrtFcstHstUpdated.get???()).ifPresent(pointStandardInfo->{
            
            pointStandardInfo // do something
            repository().save(pointStandardInfo);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void calculatePoint(WalkUpdated walkUpdated) {
        //implement business logic here:

        /** Example 1:  new item 
        PointStandardInfo pointStandardInfo = new PointStandardInfo();
        repository().save(pointStandardInfo);

        */

        /** Example 2:  finding and process
        
        repository().findById(walkUpdated.get???()).ifPresent(pointStandardInfo->{
            
            pointStandardInfo // do something
            repository().save(pointStandardInfo);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
