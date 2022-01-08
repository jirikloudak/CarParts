package cz.uhk.fim.warehouse.bill;

import org.springframework.data.domain.Page;

public interface BillService {
    void saveBill(BillEntity bill);
    BillEntity getBillById(Integer id);
    void deleteBillById(Integer id);
    Page<BillEntity> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
    Page<BillEntity> findByIdOrPaired(String find, int pageNo, int pageSize, String sortField, String sortDirection);
}
