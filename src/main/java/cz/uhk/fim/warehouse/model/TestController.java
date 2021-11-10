package cz.uhk.fim.warehouse.model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class TestController {
    @GetMapping("/")
    public String welcome() {return "welcome";}
}
