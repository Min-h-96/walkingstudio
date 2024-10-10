package walkingstudio.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import walkingstudio.WalkinghistoryApplication;
import walkingstudio.domain.WalkUpdated;

@Entity
@Table(name = "walking_hst", schema = "walk")
@IdClass(WalkingHstId.class)
@Getter
@Setter
@Slf4j
//<<< DDD / Aggregate Root
public class WalkingHst {

    @Id
    @Column(name = "p_user_id", length = 30)
    private String pUserId;

    @Id
    @Column(name = "base_date", length = 30)
    private String baseDate;

    @Id
    @Column(name = "base_time", length = 30)
    private String baseTime;

    @Column(name = "walking")
    private Integer walking;

    @Column(name = "point")
    private Double point = 0.0;

    @Column(name = "use_yn")
    private Boolean useYn;

    @Column(name = "ordinal")
    private Integer ordinal;

    @Column(name = "cret_id", length = 30)
    private String cretId;

    @Column(name = "cret_dt")
    private LocalDateTime cretDt;

    @Column(name = "mod_id", length = 30)
    private String modId;

    @Column(name = "mod_dt")
    private LocalDateTime modDt;


    @PrePersist
    public void onPrePersist() {
        log.info("PrePersist called: Entity is about to be persisted. {}", this.toString());
    }

    @PostPersist
    public void onPostPersist() {
        WalkUpdated walkUpdated = new WalkUpdated(this);
        walkUpdated.publishAfterCommit();
    }


    public static WalkingHstRepository repository() {
        WalkingHstRepository walkingHstRepository = WalkinghistoryApplication.applicationContext.getBean(
            WalkingHstRepository.class
        );
        return walkingHstRepository;
    }
}
//>>> DDD / Aggregate Root
