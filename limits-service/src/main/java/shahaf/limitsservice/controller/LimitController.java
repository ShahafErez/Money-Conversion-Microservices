package shahaf.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shahaf.limitsservice.dto.Limit;
import shahaf.limitsservice.configuration.Configuration;

@RestController
@RequestMapping("/limits")
public class LimitController {

    @Autowired
    Configuration configuration;

    @GetMapping
    public Limit retrieveLimits(){
        return new Limit(configuration.getMinimum(), configuration.getMaximum());
    }
}
