package walkingstudio.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import walkingstudio.WeatherApplication;

@Entity
@Table(name = "UsrtFcstHst_table")
@Data
//<<< DDD / Aggregate Root
public class UsrtFcstHst {

    @Id
    private String baseDate;

    private String baseTime;

    private Integer nx;

    private Integer ny;

    private String category;

    private String fcstDate;

    private String fcstTime;

    private String fcstValue;

    public static UsrtFcstHstRepository repository() {
        UsrtFcstHstRepository usrtFcstHstRepository = WeatherApplication.applicationContext.getBean(
            UsrtFcstHstRepository.class
        );
        return usrtFcstHstRepository;
    }
}
//>>> DDD / Aggregate Root
