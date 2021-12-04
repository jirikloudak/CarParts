package cz.uhk.fim.warehouse.price;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Integer> {
    List<PriceEntity> findAllByOrderByNameAsc();
    Page<PriceEntity> findByNameContaining(String name, Pageable pageable);
}
