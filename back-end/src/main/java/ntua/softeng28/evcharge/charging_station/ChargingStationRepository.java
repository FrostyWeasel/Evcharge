package ntua.softeng28.evcharge.charging_station;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ntua.softeng28.evcharge.operator.Operator;
import ntua.softeng28.evcharge.charging_point.ChargingPoint;

public interface ChargingStationRepository extends JpaRepository<ChargingStation, Long>{
    List<ChargingStation> findAllByOperator(Operator operator);
    Optional<ChargingStation> findByChargingPoints(ChargingPoint chargingPoint);

}