package repositorios;

import modelo.entidades.Cajero;
import modelo.entidades.OrdenVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenVentaRepo extends JpaRepository<OrdenVenta, Long> {
}
