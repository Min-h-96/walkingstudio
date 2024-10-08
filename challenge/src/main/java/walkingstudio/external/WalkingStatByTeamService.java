package walkingstudio.external;

import java.util.Date;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "teamwalkingstat", url = "${api.url.teamwalkingstat}")
public interface WalkingStatByTeamService {
    @GetMapping(path = "/walkingStatByTeams/{teamId}")
    public WalkingStatByTeam checkWalkingCount(
        @PathVariable("teamId") String teamId
    );
}
