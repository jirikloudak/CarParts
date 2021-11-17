package cz.uhk.fim.warehouse.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class BillController {

    @Autowired
    private BillService billService;

    private static final String VIEW_BILL_FORM = "bills/createOrUpdateBill";

    @GetMapping("/bills")
    public String viewList(Model model) {
        return findPaginated(1, "id", "asc", model);
    }

    @GetMapping("/bills/create")
    public String createForm(@RequestParam Character type, Model model) {
        BillEntity bill = new BillEntity(type);
        String formTitle = null;
        if (type == 'P') {
            formTitle = "Vytvoření příjemky";
        } else if (type == 'V') {
            formTitle = "Vytvoření výdejky";
        }
        model.addAttribute("formTitle", formTitle);
        model.addAttribute("bill", bill);
        return VIEW_BILL_FORM;
    }

    @GetMapping("/bills/update/{id}")
    public String updateForm(@PathVariable(value = "id") Integer id, Model model) {
        BillEntity bill = billService.getBillById(id);
        model.addAttribute("formTitle", "Editace dokladu " + bill.getType() + bill.getId());
        model.addAttribute("bill", bill);
        return VIEW_BILL_FORM;
    }

    @PostMapping("/bills/save")
    public String saveEntity(@ModelAttribute("bill") BillEntity bill) {
        billService.saveBill(bill);
        return "redirect:/bills";
    }

   @GetMapping("/deleteBill/{id}")
    public String deleteEntity(@PathVariable (value = "id") Integer id) {
        billService.deleteBillById(id);
        return "redirect:/bills";
    }

    @GetMapping("/bills/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 9;

        Page<BillEntity> page = billService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<BillEntity> listBills = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listBills", listBills);
        return "/bills/listBills";
    }
}