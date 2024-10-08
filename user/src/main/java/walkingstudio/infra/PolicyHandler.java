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
    UserRepository userRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PointCalculated'"
    )
    public void wheneverPointCalculated_IncreasePoint(
        @Payload PointCalculated pointCalculated
    ) {
        PointCalculated event = pointCalculated;
        System.out.println(
            "\n\n##### listener IncreasePoint : " + pointCalculated + "\n\n"
        );

        // Sample Logic //
        User.increasePoint(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ChallengeHstCreated'"
    )
    public void wheneverChallengeHstCreated_IncreasePoint(
        @Payload ChallengeHstCreated challengeHstCreated
    ) {
        ChallengeHstCreated event = challengeHstCreated;
        System.out.println(
            "\n\n##### listener IncreasePoint : " + challengeHstCreated + "\n\n"
        );

        // Sample Logic //
        User.increasePoint(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
