package walkingstudio.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import walkingstudio.domain.*;

@Component
public class ChallengeInfoHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<ChallengeInfo>> {

    @Override
    public EntityModel<ChallengeInfo> process(
        EntityModel<ChallengeInfo> model
    ) {
        return model;
    }
}
