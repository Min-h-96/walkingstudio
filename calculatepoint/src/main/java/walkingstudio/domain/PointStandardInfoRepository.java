package walkingstudio.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import walkingstudio.domain.*;
import java.util.Date;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "pointStandardInfos",
    path = "pointStandardInfos"
)
public interface PointStandardInfoRepository
    extends PagingAndSortingRepository<PointStandardInfo, Date> {}
