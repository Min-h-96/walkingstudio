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
    WalkingStatByCompanyRepository walkingStatByCompanyRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='UserStatUpdated'"
    )
    public void wheneverUserStatUpdated_UpdateWalk(
        @Payload UserStatUpdated userStatUpdated
    ) {
        UserStatUpdated event = userStatUpdated;
        System.out.println(
            "\n\n##### listener UpdateWalk : " + userStatUpdated + "\n\n"
        );

        // Sample Logic //
        WalkingStatByCompany.updateWalk(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
