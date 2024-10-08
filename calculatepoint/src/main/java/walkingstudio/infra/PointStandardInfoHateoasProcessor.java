package walkingstudio.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import walkingstudio.domain.*;

@Component
public class PointStandardInfoHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<PointStandardInfo>> {

    @Override
    public EntityModel<PointStandardInfo> process(
        EntityModel<PointStandardInfo> model
    ) {
        return model;
    }
}
