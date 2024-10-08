package walkingstudio.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import walkingstudio.domain.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/walkingStatByCompanies")
@Transactional
public class WalkingStatByCompanyController {

    @Autowired
    WalkingStatByCompanyRepository walkingStatByCompanyRepository;
}
//>>> Clean Arch / Inbound Adaptor
