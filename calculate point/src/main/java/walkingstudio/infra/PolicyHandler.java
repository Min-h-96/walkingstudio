package walkingstudio.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import walkingstudio.config.kafka.KafkaProcessor;
import walkingstudio.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    PointStandardInfoRepository pointStandardInfoRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='UsrtFcstHstUpdated'"
    )
    public void wheneverUsrtFcstHstUpdated_UpdateWeatherInfo(
        @Payload UsrtFcstHstUpdated usrtFcstHstUpdated
    ) {
        UsrtFcstHstUpdated event = usrtFcstHstUpdated;
        System.out.println(
            "\n\n##### listener UpdateWeatherInfo : " +
            usrtFcstHstUpdated +
            "\n\n"
        );

        // Sample Logic //
        PointStandardInfo.updateWeatherInfo(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='WalkUpdated'"
    )
    public void wheneverWalkUpdated_CalculatePoint(
        @Payload WalkUpdated walkUpdated
    ) {
        WalkUpdated event = walkUpdated;
        System.out.println(
            "\n\n##### listener CalculatePoint : " + walkUpdated + "\n\n"
        );

        // Sample Logic //
        PointStandardInfo.calculatePoint(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
