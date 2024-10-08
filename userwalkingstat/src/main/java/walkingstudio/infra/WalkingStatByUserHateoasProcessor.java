package walkingstudio.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import walkingstudio.domain.*;

@Component
public class WalkingStatByUserHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<WalkingStatByUser>> {

    @Override
    public EntityModel<WalkingStatByUser> process(
        EntityModel<WalkingStatByUser> model
    ) {
        return model;
    }
}
