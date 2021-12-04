package cz.uhk.fim.warehouse.bill;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, Integer> {
    Page<BillEntity> findByType(Character type, Pageable pageable);
    Page<BillEntity> findById(Integer id, Pageable pageable);
}