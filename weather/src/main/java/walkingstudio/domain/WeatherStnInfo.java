package walkingstudio.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import walkingstudio.WeatherApplication;
import walkingstudio.domain.UsrtFcstHstUpdated;

@Entity
@Table(name = "WeatherStnInfo_table")
@Data
@AllArgsConstructor
//<<< DDD / Aggregate Root
public class WeatherStnInfo {

    @Id
    private String id;

    private String divisionLevel1;

    private String divisionLevel2;

    private Integer nx;

    private Integer ny;

    @PostPersist
    public void onPostPersist() {
        UsrtFcstHstUpdated usrtFcstHstUpdated = new UsrtFcstHstUpdated(this);
        usrtFcstHstUpdated.publishAfterCommit();
    }

    public static WeatherStnInfoRepository repository() {
        WeatherStnInfoRepository weatherStnInfoRepository = WeatherApplication.applicationContext.getBean(
            WeatherStnInfoRepository.class
        );
        return weatherStnInfoRepository;
    }
}
//>>> DDD / Aggregate Root
