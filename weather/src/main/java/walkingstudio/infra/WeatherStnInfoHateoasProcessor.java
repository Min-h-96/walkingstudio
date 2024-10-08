package walkingstudio.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import walkingstudio.domain.*;

@Component
public class WeatherStnInfoHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<WeatherStnInfo>> {

    @Override
    public EntityModel<WeatherStnInfo> process(
        EntityModel<WeatherStnInfo> model
    ) {
        return model;
    }
}
