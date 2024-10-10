package walkingstudio.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import walkingstudio.config.kafka.KafkaProcessor;
import walkingstudio.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    private final HttpClient client = HttpClient.newHttpClient();

    private List<WeatherStnInfo> weatherStnInfos = new ArrayList<WeatherStnInfo>() {
        {
            add(new WeatherStnInfo("1100000000", "서울특별시", null, 60, 127));
            add(new WeatherStnInfo("2600000000", "부산광역시", null, 98, 76));
            add(new WeatherStnInfo("2700000000", "대구광역시", null, 89, 90));
            add(new WeatherStnInfo("2800000000", "인천광역시", null, 55, 124));
            add(new WeatherStnInfo("2900000000", "광주광역시", null, 58, 74));
            add(new WeatherStnInfo("3000000000", "대전광역시", null, 67, 100));
            add(new WeatherStnInfo("3100000000", "울산광역시", null, 102, 84));
            add(new WeatherStnInfo("3600000000", "세종특별자치시", null, 66, 103));
            add(new WeatherStnInfo("4100000000", "경기도", null, 60, 120));
            add(new WeatherStnInfo("4300000000", "충청북도", null, 69, 107));
            add(new WeatherStnInfo("4400000000", "충청남도", null, 68, 100));
            add(new WeatherStnInfo("4600000000", "전라남도", null, 51, 67));
            add(new WeatherStnInfo("4700000000", "경상북도", null, 87, 106));
            add(new WeatherStnInfo("4800000000", "경상남도", null, 91, 77));
            add(new WeatherStnInfo("5000000000", "제주특별자치도", null, 52, 38));
            add(new WeatherStnInfo("5100000000", "강원특별자치도", null, 73, 134));
            add(new WeatherStnInfo("5200000000", "전북특별자치도", null, 63, 89));
        }
    };

    @Autowired
    UsrtFcstHstRepository usrtFcstHstRepository;

    @Autowired
    WeatherStnInfoRepository weatherStnInfoRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @Scheduled(fixedDelay = 10000)
    public void requestWeatherInfo() {
        String baseDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String baseTime = LocalTime.now().withMinute(0).format(DateTimeFormatter.ofPattern("HH00"));
        
        for (WeatherStnInfo w : weatherStnInfos) {
            WeatherRequest weatherRequest = new WeatherRequest();

            weatherRequest.setBaseDate(baseDate);
            weatherRequest.setBaseTime(baseTime);
            weatherRequest.setNx(w.getNx());
            weatherRequest.setNy(w.getNy());

            // 공공데이터 포털 날씨 정보 API 호출
            String url = String.format("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?serviceKey=%s&dataType=%s&numOfRows=%d&base_date=%s&base_time=%s&nx=%d&ny=%d",
                weatherRequest.getServiceKey(),
                weatherRequest.getDataType(),
                weatherRequest.getNumOfRows(),
                weatherRequest.getBaseDate(),
                weatherRequest.getBaseTime(),
                weatherRequest.getNx(),
                weatherRequest.getNy()
            );

            try {
                // HttpClient를 사용하여 API 요청
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
        
                // 4. 요청 보내고 응답 받기
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
                // 응답을 로그로 출력
                // log.info("Response received from API: {}", response.body());
        
                // 5. JSON 응답 파싱
                List<WeatherResponse> weatherResponses = parseWeatherResponse(response.body());
        
                if (weatherResponses != null && !weatherResponses.isEmpty()) {
                //   saveWeatherResponses(weatherResponses, w);
                    for (WeatherResponse weatherResponse : weatherResponses) {
                        UsrtFcstHst usrtFcstHst = usrtFcstHstRepository.findByNxAndNyAndCategory(weatherRequest.getNx(), weatherRequest.getNy(), weatherResponse.getCategory())
                            .orElse(new UsrtFcstHst());

                        usrtFcstHst.setFcstValue(weatherResponse.getObsrValue());
                        usrtFcstHst.setBaseDate(weatherResponse.getBaseDate());
                        usrtFcstHst.setBaseTime(weatherResponse.getBaseTime());
                        usrtFcstHst.setNx(weatherResponse.getNx());
                        usrtFcstHst.setNy(weatherResponse.getNy());
                        usrtFcstHst.setCategory(weatherResponse.getCategory());;
                        // usrtFcstHst.setCretDt(LocalDateTime.now());
                        // usrtFcstHst.setCretIp("127.0.0.1");
                        usrtFcstHstRepository.save(usrtFcstHst);
                    }
                    

                } else {
                //   log.warn("No data received for station (nx: {}, ny: {})", station.getNx(), station.getNy());
                }
            } catch (Exception e) {
                // log.error("Error fetching data from API for station (nx: {}, ny: {}): ", station.getNx(), station.getNy(), e);
            }
        }
        
    }

    // JSON 응답을 파싱하여 WeatherResponse 객체 리스트로 변환하는 메서드
    private List<WeatherResponse> parseWeatherResponse(String responseBody) {
        List<WeatherResponse> weatherResponses = new ArrayList<>();

        try {
            // JSON 응답 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseBody);

            // "response > body > items > item" 경로로 접근
            JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");

            // 각 item을 WeatherResponse 객체로 변환하여 리스트에 추가
            for (JsonNode itemNode : itemsNode) {
              WeatherResponse weatherResponse = objectMapper.treeToValue(itemNode, WeatherResponse.class);
              System.out.println(weatherResponse.toString());
              weatherResponses.add(weatherResponse);
            }
        } catch (Exception e) {
        //   log.error("Error parsing weather response: ", e);
        }

        return weatherResponses;
    }
}
//>>> Clean Arch / Inbound Adaptor
