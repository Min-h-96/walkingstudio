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

    @Transient
    private boolean eventPublished = false;

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

    @PostPersist
    public void onPostPersist() {
        log.info("PostPersist called: Entity has been persisted. {}", this.toString());
        publishWalkEvent();
    }

    @PostUpdate
    public void onPostUpdate() {
        log.info("PostUpdate called: Entity has been updated. {}", this.toString());
        publishWalkEvent();
    }

    public static WalkingHstRepository repository() {
        WalkingHstRepository walkingHstRepository = WalkinghistoryApplication.applicationContext.getBean(
            WalkingHstRepository.class
        );
        return walkingHstRepository;
    }

    // 생성과 업데이트에서 공통으로 호출되는 이벤트 발행 메서드
    private void publishWalkEvent() {
        // 이벤트가 이미 발행된 경우, 중복 발행을 방지
        if (!eventPublished) {
            WalkUpdated walkUpdated = new WalkUpdated(this);
            walkUpdated.publishAfterCommit();
            eventPublished = true; // 이벤트가 발행된 후 플래그를 설정
        }
    }
}
//>>> DDD / Aggregate Root
