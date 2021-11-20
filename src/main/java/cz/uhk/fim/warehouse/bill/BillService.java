package cz.uhk.fim.warehouse.bill;

import org.springframework.data.domain.Page;

import java.util.List;

public interface BillService {
    List<BillEntity> getAllBills();
    void saveBill(BillEntity bill);
    BillEntity getBillById(Integer id);
    void deleteBillById(Integer id);
    Page<BillEntity> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
