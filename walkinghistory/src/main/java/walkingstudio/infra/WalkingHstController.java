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
import java.util.Map;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/walkingHsts")
//@Transactional
@CrossOrigin(origins = "http://localhost:8081")  // local front
public class WalkingHstController {

    @Autowired
    WalkingHstService walkingHstService;

    @PostMapping("/walkingHsts/collect")
    public ResponseEntity<?> collectWalkData(@RequestBody WalkCollectRequest walkCollectRequest) {
        try {
            walkingHstService.saveOrUpdateWalkData(walkCollectRequest);
            return ResponseEntity.ok("Good");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("error");
        }
    }

    @PostMapping("/walkingHsts/todayStat")
    public ResponseEntity<?> getTodayStat(@RequestBody Map<String, String> requestBody) {
        String pUserId = requestBody.get("pUserId");
        String baseDate = requestBody.get("baseDate");

        Map<String, Object> result = walkingHstService.getTodayStat(pUserId, baseDate);

        if (result.containsKey("allWalking")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(404).body("No data found for the user.");
        }
    }


}
//>>> Clean Arch / Inbound Adaptor
