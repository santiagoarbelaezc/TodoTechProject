package repositorios;

import modelo.entidades.Cajero;
import modelo.entidades.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepo extends JpaRepository<Pago, Long> {
}
