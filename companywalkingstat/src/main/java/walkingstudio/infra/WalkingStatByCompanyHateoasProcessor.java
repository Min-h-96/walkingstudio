package walkingstudio.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import walkingstudio.domain.*;

@Component
public class WalkingStatByCompanyHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<WalkingStatByCompany>> {

    @Override
    public EntityModel<WalkingStatByCompany> process(
        EntityModel<WalkingStatByCompany> model
    ) {
        return model;
    }
}
