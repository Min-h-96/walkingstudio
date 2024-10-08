package walkingstudio.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import walkingstudio.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "challengeHsts",
    path = "challengeHsts"
)
public interface ChallengeHstRepository
    extends PagingAndSortingRepository<ChallengeHst, String> {}
