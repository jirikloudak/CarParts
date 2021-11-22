package cz.uhk.fim.warehouse.bill;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<BillEntity, Integer> {
    Page<BillEntity> findAllByOrderByIdAsc(Pageable pageable);
    Page<BillEntity> findByTypeOrderByIdAsc(Character type, Pageable pageable);
    Page<BillEntity> findByIdOrderByIdAsc(Integer id, Pageable pageable);
}