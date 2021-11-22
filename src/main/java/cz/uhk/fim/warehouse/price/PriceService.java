package cz.uhk.fim.warehouse.price;

import org.springframework.data.domain.Page;

import java.util.List;

public interface PriceService {
    void savePrice(PriceEntity price);
    PriceEntity getPriceById(Integer id);
    void deletePriceById(Integer id);
    List<PriceEntity> findAllPrices();
    Page<PriceEntity> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
    Page<PriceEntity> findByName(String Name, int pageNo, int pageSize, String sortField, String sortDirection);
}