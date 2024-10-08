package walkingstudio.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import walkingstudio.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "walkingHsts",
    path = "walkingHsts"
)
public interface WalkingHstRepository
    extends PagingAndSortingRepository<WalkingHst, String> {}
