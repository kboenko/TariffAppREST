package TariffApp.dao;

import TariffApp.domain.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TariffRepository extends JpaRepository<Tariff, Long>{
}
