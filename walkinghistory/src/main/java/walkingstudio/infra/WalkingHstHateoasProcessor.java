package walkingstudio.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import walkingstudio.domain.*;

@Component
public class WalkingHstHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<WalkingHst>> {

    @Override
    public EntityModel<WalkingHst> process(EntityModel<WalkingHst> model) {
        return model;
    }
}
