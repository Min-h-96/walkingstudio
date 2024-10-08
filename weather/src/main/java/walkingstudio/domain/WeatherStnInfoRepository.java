package walkingstudio.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import walkingstudio.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "weatherStnInfos",
    path = "weatherStnInfos"
)
public interface WeatherStnInfoRepository
    extends PagingAndSortingRepository<WeatherStnInfo, String> {}
