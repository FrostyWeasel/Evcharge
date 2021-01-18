package ntua.softeng28.evcharge.car;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargingCurvePointRepository extends JpaRepository<ChargingCurvePoint, Long> {
    
}
