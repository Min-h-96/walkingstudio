package walkingstudio.external;

import java.util.Date;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user walking stat", url = "${api.url.user walking stat}")
public interface WalkingStatByUserService {
    @GetMapping(path = "/walkingStatByUsers/{pUserId}")
    public WalkingStatByUser checkWalkingCount(
        @PathVariable("pUserId") String pUserId
    );
}
