package ntua.softeng28.evcharge.car;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, String> {
    List<Car> findByBrand(Brand brand);
}