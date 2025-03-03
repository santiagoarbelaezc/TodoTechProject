package repositorios;

import modelo.entidades.Cajero;
import modelo.entidades.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleOrdenRepo extends JpaRepository<DetalleOrden, Long> {
}
