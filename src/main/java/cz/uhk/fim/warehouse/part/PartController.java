package cz.uhk.fim.warehouse.part;

import cz.uhk.fim.warehouse.bill.BillEntity;
import cz.uhk.fim.warehouse.price.PriceEntity;
import cz.uhk.fim.warehouse.price.PriceServiceImpl;
import cz.uhk.fim.warehouse.unit.UnitEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
class PartController {
    private final PartServiceImpl partService;

    private final PriceServiceImpl priceService;

    private static final String VIEW_PART_FORM = "parts/createOrUpdatePart";

    public PartController(PartServiceImpl partService, PriceServiceImpl priceService) {
        this.partService = partService;
        this.priceService = priceService;
    }

    @GetMapping("/parts")
    public String viewList(Model model) {
        return findPaginated(1, "id", "asc", model);
    }

    @GetMapping("/parts/delete/{id}")
    public String deleteEntity(@PathVariable(value = "id") Integer id) {
        partService.deletePart(id);
        return "redirect:/parts";
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

    @GetMapping("/parts/update/{id}")
    public String updateForm(@PathVariable(value = "id") Integer id, Model model) {
        PartEntity partEntity = partService.getPartById(id);
        List<PriceEntity> prices = priceService.findAllPrices();
        model.addAttribute("formTitle", "Editace dílu " + partEntity.getName() + " - Id: " + partEntity.getId());
        model.addAttribute("partEntity", partEntity);
        model.addAttribute("prices", prices);
        return VIEW_PART_FORM;
    }

    @PostMapping("/parts/save")
    public String saveEntity(@Valid PartEntity partEntity, BindingResult result, Model model) {
        if (result.hasErrors()) {
            String formTitle;
            formTitle = "Editace dokladu " + partEntity.getName() + partEntity.getId();
            List<PriceEntity> prices = priceService.findAllPrices();
            model.addAttribute("formTitle", formTitle);
            model.addAttribute("prices", prices);
            return VIEW_PART_FORM;
        } else {
            partService.savePart(partEntity);
            return "redirect:/parts";
        }
    }

    @GetMapping("/parts/create")
    public String createForm(Model model) {
        PartEntity partEntity = new PartEntity();
        String formTitle = "Vytvoření nového dílu";
        List<PriceEntity> prices = priceService.findAllPrices();
        model.addAttribute("formTitle", formTitle);
        model.addAttribute("partEntity", partEntity);
        model.addAttribute("prices", prices);
        return VIEW_PART_FORM;
    }

}
