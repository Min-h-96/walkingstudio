package walkingstudio.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import walkingstudio.domain.*;
import walkingstudio.dto.WalkCollectRequest;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/walkingHsts")
@Transactional
public class WalkingHstController {

    @Autowired
    WalkingHstService walkingHstService;

    @PostMapping("/collect")
    public ResponseEntity<?> collectWalkData(@RequestBody WalkCollectRequest walkCollectRequest) {
        try {
            walkingHstService.saveOrUpdateWalkData(walkCollectRequest);
            return ResponseEntity.ok("Good");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("error");
        }
    }
}
//>>> Clean Arch / Inbound Adaptor
