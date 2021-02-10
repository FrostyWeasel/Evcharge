package ntua.softeng28.evcharge.session;

import org.springframework.data.jpa.repository.JpaRepository;
import javax.persistence.Query;

import java.sql.Timestamp;


import org.springframework.data.repository.query.Param;

import ntua.softeng28.evcharge.charging_point.ChargingPoint;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByChargingPointAndStartedOnBetween(@Param(value = "chargingPoint") ChargingPoint chargingPoint, @Param(value = "date_from") Timestamp date_from, @Param(value = "date_to") Timestamp date_to);
}
