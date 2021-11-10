package cz.uhk.fim.warehouse.part;

import org.springframework.stereotype.Controller;

@Controller
class PartController {

    private final PartRepository parts;

    public PartController(PartRepository parts) {
        this.parts = parts;
    }
}
