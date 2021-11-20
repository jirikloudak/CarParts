package cz.uhk.fim.warehouse.movement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementRepository extends JpaRepository<MovementEntity, Integer> {
}
