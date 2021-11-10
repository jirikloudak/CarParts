package cz.uhk.fim.warehouse.bill;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface BillRepository extends Repository<BillEntity, Integer>{

    void save(BillEntity bill);

    @Query("SELECT bill FROM BillEntity bill")
    @Transactional(readOnly = true)
    Page<BillEntity> findAll(Pageable pageable);
}