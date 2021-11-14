package cz.uhk.fim.warehouse.bill;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BillController {

    private final BillRepository bills;

    public BillController(BillRepository bills) {
        this.bills = bills;
    }

    @GetMapping("/bills.html")
    public String showBillList(@RequestParam(defaultValue = "1") int page, Model model) {
        Page<BillEntity> paginated = findPaginated(page);
        return addPaginationModel(page, paginated, model);
    }

    private String addPaginationModel(int page, Page<BillEntity> paginated, Model model) {
        List<BillEntity> listBills = paginated.getContent();
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", paginated.getTotalPages());
        model.addAttribute("totalItems", paginated.getTotalElements());
        model.addAttribute("listBills", listBills);
        return "bills/billList.html";
    }

    private Page<BillEntity> findPaginated(int page) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return bills.findAll(pageable);
    }
/**
    @GetMapping({ "/bills" })
    public @ResponseBody  showResourcesBillList() {
        List<BillEntity> bills = new Vets();
        bills.addAll(this.bills.findAll());
        return bills;
    }
    */
}
