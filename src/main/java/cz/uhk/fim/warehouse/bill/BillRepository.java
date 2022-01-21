package cz.uhk.fim.warehouse.bill;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, Integer> {

    @Query("SELECT b FROM BillEntity b WHERE b.paired LIKE :find")
    @Transactional(readOnly = true)
    Page<BillEntity> findByPaired(String find, Pageable pageable);
}