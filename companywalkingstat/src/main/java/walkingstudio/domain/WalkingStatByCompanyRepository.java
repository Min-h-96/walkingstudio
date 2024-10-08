package walkingstudio.domain;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import walkingstudio.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "walkingStatByCompanies",
    path = "walkingStatByCompanies"
)
public interface WalkingStatByCompanyRepository
    extends PagingAndSortingRepository<WalkingStatByCompany, String> {}
