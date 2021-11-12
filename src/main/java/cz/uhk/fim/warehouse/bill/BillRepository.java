package cz.uhk.fim.warehouse.bill;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BillRepository extends PagingAndSortingRepository<BillEntity, Integer> {
}