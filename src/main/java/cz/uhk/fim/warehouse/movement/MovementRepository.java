package cz.uhk.fim.warehouse.movement;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovementRepository extends PagingAndSortingRepository<MovementEntity, Integer> {
}
