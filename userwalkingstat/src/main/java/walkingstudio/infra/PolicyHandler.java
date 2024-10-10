package walkingstudio.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import walkingstudio.config.kafka.KafkaProcessor;
import walkingstudio.domain.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    WalkingStatByUserRepository walkingStatByUserRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='WalkUpdated'"
    )
    public void wheneverWalkUpdated_UpdateWalk(
        @Payload WalkUpdated walkUpdated
    ) {
        System.out.println("##### listener UpdateWalk : " + walkUpdated);

        // pUserId와 baseDate가 일치하는 레코드 조회
        String pUserId = walkUpdated.getPUserId();
        // baseDate는 월 기준으로 고정 (해당 월의 첫 날로 설정)
        LocalDate firstDayOfMonth = LocalDate.parse(walkUpdated.getBaseDate(), DateTimeFormatter.ofPattern("yyyyMMdd"))
            .withDayOfMonth(1);
        Date baseDate = java.sql.Date.valueOf(firstDayOfMonth);

        Optional<WalkingStatByUser> existingStat = walkingStatByUserRepository.findBypUserIdAndBaseDate(pUserId, baseDate);

        if (existingStat.isPresent()) {
            // 기존 데이터가 있을 경우 walking 값 업데이트
            WalkingStatByUser stat = existingStat.get();
            stat.setWalking(stat.getWalking() + walkUpdated.getWalking());
            walkingStatByUserRepository.save(stat);  // 업데이트된 값 저장
            System.out.println("Updated walking stat for user " + pUserId + ": " + stat.getWalking());

        } else {
            // 기존 데이터가 없을 경우 새로운 데이터 생성
            WalkingStatByUser newStat = new WalkingStatByUser();
            newStat.setPUserId(pUserId);
            newStat.setBaseDate(baseDate);  // 해당 월의 첫 날
            newStat.setWalking(walkUpdated.getWalking());  // walking 값 설정
            walkingStatByUserRepository.save(newStat);  // 새로운 레코드 저장
            System.out.println("Created new walking stat for user " + pUserId + ": " + newStat.getWalking());
        }
        // Sample Logic //

    }
}
//>>> Clean Arch / Inbound Adaptor
