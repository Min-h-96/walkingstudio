package walkingstudio.domain;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import walkingstudio.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "walkingStatByUsers",
    path = "walkingStatByUsers"
)
public interface WalkingStatByUserRepository
    extends PagingAndSortingRepository<WalkingStatByUser, String> {

  Optional<WalkingStatByUser> findBypUserIdAndBaseDate(@Param("pUserId") String pUserId, @Param("baseDate") Date baseDate);

  @Query("SELECT COUNT(w) + 1 FROM WalkingStatByUser w WHERE w.baseDate = :baseDate AND w.walking > (SELECT u.walking FROM WalkingStatByUser u WHERE u.pUserId = :pUserId AND u.baseDate = :baseDate)")
  int calculateUserRanking(@Param("pUserId") String pUserId, @Param("baseDate") Date baseDate);

}

