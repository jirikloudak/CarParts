package cz.uhk.fim.warehouse.bill;

import org.springframework.data.domain.Page;

import java.util.List;

public interface BillService {
    void saveBill(BillEntity bill);
    BillEntity getBillById(Integer id);
    void deleteBillById(Integer id);
    Page<BillEntity> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
    Page<BillEntity> findByType(Character type, int pageNo, int pageSize, String sortField, String sortDirection);
    Page<BillEntity> findById(Integer id, int pageNo, int pageSize, String sortField, String sortDirection);
}
