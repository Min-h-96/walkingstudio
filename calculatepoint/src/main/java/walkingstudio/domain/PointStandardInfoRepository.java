package walkingstudio.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import walkingstudio.domain.*;
import java.util.Date;
import java.util.Optional;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "pointStandardInfos",
    path = "pointStandardInfos"
)
public interface PointStandardInfoRepository
    extends PagingAndSortingRepository<PointStandardInfo, Date> {
        Optional<PointStandardInfo> findByNxAndNy(Integer nx, Integer ny);
    }
