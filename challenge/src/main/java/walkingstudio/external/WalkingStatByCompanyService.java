package walkingstudio.external;

import java.util.Date;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "company walking stat",
    url = "${api.url.company walking stat}"
)
public interface WalkingStatByCompanyService {
    @GetMapping(path = "/walkingStatByCompanies/{comId}")
    public WalkingStatByCompany checkWalkingCount(
        @PathVariable("comId") String comId
    );
}
