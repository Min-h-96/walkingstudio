package walkingstudio.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import walkingstudio.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "challengeInfos",
    path = "challengeInfos"
)
public interface ChallengeInfoRepository
    extends PagingAndSortingRepository<ChallengeInfo, Long> {}
