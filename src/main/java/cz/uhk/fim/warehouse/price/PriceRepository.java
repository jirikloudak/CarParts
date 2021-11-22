package cz.uhk.fim.warehouse.price;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceRepository extends JpaRepository<PriceEntity, Integer> {
    List<PriceEntity> findAllByOrderByNameAsc();
    Page<PriceEntity> findAllByOrderByNameAsc(Pageable pageable);
    Page<PriceEntity> findByNameContainingOrderByName(String name, Pageable pageable);
}
