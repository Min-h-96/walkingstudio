package walkingstudio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WalkCollectRequest {
  private String puserId;
  private String baseDate;
  private String baseTime;
  private Integer walking;
}