package walkingstudio.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import walkingstudio.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "usrtFcstHsts",
    path = "usrtFcstHsts"
)
public interface UsrtFcstHstRepository
    extends PagingAndSortingRepository<UsrtFcstHst, String> {}
