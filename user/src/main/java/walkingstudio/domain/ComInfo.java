package walkingstudio.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "com_info", schema = "walk")
@Getter
@Setter
public class ComInfo {

  @Id
  @Column(name = "com_id", length = 30, nullable = false)
  private String comId;

  @Column(name = "com_name", length = 50)
  private String comName;

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
}
