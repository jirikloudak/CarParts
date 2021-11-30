package cz.uhk.fim.warehouse.part;

import cz.uhk.fim.warehouse.bill.BillEntity;
import cz.uhk.fim.warehouse.price.PriceServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
class PartController {
    private final PartServiceImpl partService;

    private final PriceServiceImpl priceService;

    public PartController(PartServiceImpl partService, PriceServiceImpl priceService) {
        this.partService = partService;
        this.priceService = priceService;
    }

    @GetMapping("/parts")
    public String viewList(Model model) {
        return findPaginated(1, "id", "asc", model);
    }

    @GetMapping("/parts/delete/{id}")
    public String deleteEntity(@PathVariable (value = "id") Integer id) {
        partService.deletePart(id);
        return "redirect:/bills";
    }

    @GetMapping("/parts/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 9;

        Page<PartEntity> page = partService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<PartEntity> listParts = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listParts", listParts);
        return "/parts/listParts";
    }

}
