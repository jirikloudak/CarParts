package cz.uhk.fim.warehouse.unit;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitServiceImpl implements UnitService{

    private final UnitRepository unitRepository;

    public UnitServiceImpl(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public List<UnitEntity> getAllUnits(){
        return unitRepository.findAll();
    }

    public List<UnitEntity> getUnitsByType(Character type) {
        return unitRepository.findUnitsByType(type.toString());
    }
}
