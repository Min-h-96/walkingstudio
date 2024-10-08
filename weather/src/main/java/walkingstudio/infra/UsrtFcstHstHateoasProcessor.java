package walkingstudio.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import walkingstudio.domain.*;

@Component
public class UsrtFcstHstHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<UsrtFcstHst>> {

    @Override
    public EntityModel<UsrtFcstHst> process(EntityModel<UsrtFcstHst> model) {
        return model;
    }
}
