package cz.uhk.fim.warehouse.price;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PriceRepository extends PagingAndSortingRepository<PriceEntity, Integer> {
}
