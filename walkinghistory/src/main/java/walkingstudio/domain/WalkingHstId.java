package walkingstudio.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalkingHstId implements Serializable {
  private String pUserId;
  private String baseDate;
  private String baseTime;
}
