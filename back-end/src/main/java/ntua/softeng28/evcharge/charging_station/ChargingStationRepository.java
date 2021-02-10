package ntua.softeng28.evcharge.charging_station;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ntua.softeng28.evcharge.operator.Operator;

public interface ChargingStationRepository extends JpaRepository<ChargingStation, Long>{
    List<ChargingStation> findAllByOperator(Operator operator);
}