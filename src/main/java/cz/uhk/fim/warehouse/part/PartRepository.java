package cz.uhk.fim.warehouse.part;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PartRepository extends PagingAndSortingRepository<PartEntity, Integer> {
}
