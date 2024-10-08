package walkingstudio.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_info", schema = "walk")
@Getter
@Setter
public class UserInfo {

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


}
