package walkingstudio.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import walkingstudio.domain.*;

@Component
public class WalkingStatByTeamHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<WalkingStatByTeam>> {

    @Override
    public EntityModel<WalkingStatByTeam> process(
        EntityModel<WalkingStatByTeam> model
    ) {
        return model;
    }
}
