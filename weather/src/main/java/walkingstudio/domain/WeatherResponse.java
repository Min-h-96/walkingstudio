package walkingstudio.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class WeatherResponse {
  private String baseDate;
  private String baseTime;
  private String category;
  private int nx;
  private int ny;
  private String obsrValue;
}
