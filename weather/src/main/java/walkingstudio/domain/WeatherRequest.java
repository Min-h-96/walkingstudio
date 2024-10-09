package walkingstudio.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class WeatherRequest {

  // 기본값을 필드에서 직접 할당
  @JsonProperty("serviceKey")
  private String serviceKey = "szJP8v9lD%2BgDQGOAmFhI30Kd%2BkbllGtCeqkPp3m36P5rsuRNcb32TH3ytN4ZIN56QltQJGdyyqCCy5w2xoxK4Q%3D%3D";

  @JsonProperty("dataType")
  private String dataType = "JSON";

  @JsonProperty("numOfRows")
  private int numOfRows = 1000;

  @JsonProperty("baseDate")
  private String baseDate;

  @JsonProperty("baseTime")
  private String baseTime;

  @JsonProperty("nx")
  private int nx;

  @JsonProperty("ny")
  private int ny;
}
