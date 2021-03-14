package ntua.softeng28.evcharge.energy_provider;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnergyProviderRepository extends JpaRepository<EnergyProvider, Long> {
    Optional<EnergyProvider> findByBrandName(String brandName);

}
