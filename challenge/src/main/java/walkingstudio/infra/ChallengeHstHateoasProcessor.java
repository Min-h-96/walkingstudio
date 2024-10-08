package walkingstudio.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import walkingstudio.domain.*;

@Component
public class ChallengeHstHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<ChallengeHst>> {

    @Override
    public EntityModel<ChallengeHst> process(EntityModel<ChallengeHst> model) {
        return model;
    }
}
