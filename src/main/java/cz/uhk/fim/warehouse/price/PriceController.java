package cz.uhk.fim.warehouse.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PriceController {

    @Autowired
    private PriceServiceImpl priceService;

    private static final String VIEW_PRICE_FORM = "prices/createOrUpdatePrice";

    @GetMapping("/prices")
    public String viewList(Model model) {
        return findPaginated(1, "id", "asc", null, model);
    }

    @GetMapping("/prices/delete/{id}")
    public String deleteEntity(@PathVariable(value = "id") Integer id) {
        priceService.deletePriceById(id);
        return "redirect:/prices";
    }

    @GetMapping("/prices/page/{pageNo}")
    public String findPaginated(@PathVariable(name = "pageNo") int pageNo,
                                @RequestParam(name = "sortField", defaultValue = "id") String sortField,
                                @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir,
                                @RequestParam(name = "search", required = false) String search,
                                Model model) {
        int pageSize = 9;

        Page<PriceEntity> page;
        if (search != null && !search.isBlank())
        {
            page = priceService.findByName(search, pageNo, pageSize, sortField, sortDir);
        } else {
            page = priceService.findPaginated(pageNo, pageSize, sortField, sortDir);
        }
        List<PriceEntity> listPrices = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("search", search);
        model.addAttribute("listPrices", listPrices);
        return "prices/listPrices";
    }

    @GetMapping("/prices/update/{id}")
    public String updateForm(@PathVariable(value = "id") Integer id, Model model) {
        PriceEntity priceEntity = priceService.getPriceById(id);
        model.addAttribute("formTitle", "Editace ceny " + priceEntity.getName() + " - Id: " + priceEntity.getId());
        model.addAttribute("priceEntity", priceEntity);
        return VIEW_PRICE_FORM;
    }

    @PostMapping("/prices/save")
    public String saveEntity(@Valid PriceEntity priceEntity, BindingResult result, Model model) {
        if (result.hasErrors()) {
            String formTitle;
            formTitle = "Editace ceny " + priceEntity.getName() + " - Id: " + priceEntity.getId();
            model.addAttribute("formTitle", formTitle);
            return VIEW_PRICE_FORM;
        } else {
            priceService.savePrice(priceEntity);
            return "redirect:/prices";
        }
    }

    @GetMapping("/prices/create")
    public String createForm(Model model) {
        PriceEntity priceEntity = new PriceEntity();
        String formTitle = "Vytvoření nové cenové skupiny";
        model.addAttribute("formTitle", formTitle);
        model.addAttribute("priceEntity", priceEntity);
        return VIEW_PRICE_FORM;
    }
}
