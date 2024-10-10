package walkingstudio.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import walkingstudio.CalculatepointApplication;
import walkingstudio.domain.PointCalculated;
import walkingstudio.domain.WeatherInfoUpdated;

@Entity
@Table(name = "PointStandardInfo_table")
@Data
//<<< DDD / Aggregate Root
public class PointStandardInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date baseDate;

    private String baseTime;

    private Integer nx;

    private Integer ny;

    private Boolean t1h = false;

    private Boolean wsd = false;

    private Boolean reh = false;

    private Boolean rn1 = false;

    private Double weight;

    @PostPersist
    public void onPostPersist() {
        // PointCalculated pointCalculated = new PointCalculated(this);
        // pointCalculated.publishAfterCommit();
    }

    public static PointStandardInfoRepository repository() {
        PointStandardInfoRepository pointStandardInfoRepository = CalculatepointApplication.applicationContext.getBean(
            PointStandardInfoRepository.class
        );
        return pointStandardInfoRepository;
    }

    //<<< Clean Arch / Port Method
    public static void updateWeatherInfo(
        UsrtFcstHstUpdated usrtFcstHstUpdated
    ) {
        //implement business logic here:
        repository().findByNxAndNy(usrtFcstHstUpdated.getNx(), usrtFcstHstUpdated.getNy())
            .ifPresentOrElse(pointStandardInfo -> {
                if (usrtFcstHstUpdated.getCategory() == "T1H") {
                    if (pointStandardInfo.getT1h()) {
                        if (Double.valueOf(usrtFcstHstUpdated.getFcstValue()) >= 20.0) {
                            pointStandardInfo.setT1h(true);
                            pointStandardInfo.setWeight(pointStandardInfo.getWeight() + 0.1);
                        }
                    } else {
                        if (Double.valueOf(usrtFcstHstUpdated.getFcstValue()) < 20.0) {
                            pointStandardInfo.setT1h(false);
                            pointStandardInfo.setWeight(pointStandardInfo.getWeight() - 0.1);
                        }
                    }
                    repository().save(pointStandardInfo);
                
                    WeatherInfoUpdated weatherInfoUpdated = new WeatherInfoUpdated(pointStandardInfo);
                    weatherInfoUpdated.publishAfterCommit();
                }
            }, () -> {
                PointStandardInfo pointStandardInfo = new PointStandardInfo();

                pointStandardInfo.setBaseDate(usrtFcstHstUpdated.getBaseDate());
                pointStandardInfo.setBaseTime(usrtFcstHstUpdated.getBaseTime());
                pointStandardInfo.setNx(usrtFcstHstUpdated.getNx());
                pointStandardInfo.setNy(usrtFcstHstUpdated.getNy());
                Double weight = 1.0;

                if (usrtFcstHstUpdated.getCategory() == "T1H") {
                    if (!pointStandardInfo.getT1h()) {
                        if (Double.valueOf(usrtFcstHstUpdated.getFcstValue()) >= 20.0) {
                            pointStandardInfo.setT1h(true);
                            weight += 0.1;
                        }
                    }
                }

                pointStandardInfo.setWeight(weight);

                repository().save(pointStandardInfo);

                WeatherInfoUpdated weatherInfoUpdated = new WeatherInfoUpdated(pointStandardInfo);
                weatherInfoUpdated.publishAfterCommit();
        });
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
