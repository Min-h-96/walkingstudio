package walkingstudio.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import walkingstudio.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "usrtFcstHsts",
    path = "usrtFcstHsts"
)
public interface UsrtFcstHstRepository
    extends PagingAndSortingRepository<UsrtFcstHst, String> {
        Optional<UsrtFcstHst> findByNxAndNy(Integer nx, Integer ny);
    }
