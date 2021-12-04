package cz.uhk.fim.warehouse.part;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<PartEntity, Integer> {

    List<PartEntity> findAllByOrderByCodeAsc();

    @Query("SELECT p FROM PartEntity p WHERE p.qty < p.min")
    @Transactional(readOnly = true)
    Page<PartEntity> findPartsToOrder(Pageable pageable);

    @Query("SELECT p FROM PartEntity p WHERE p.code LIKE :find OR p.name LIKE :find")
    @Transactional(readOnly = true)
    Page<PartEntity> findByCodeOrName(String find, Pageable pageable);
}
