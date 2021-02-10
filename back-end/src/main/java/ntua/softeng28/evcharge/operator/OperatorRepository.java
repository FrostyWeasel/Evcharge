package ntua.softeng28.evcharge.operator;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OperatorRepository extends JpaRepository<Operator, Long> {
    Optional<Operator> findByName(String name);

}
