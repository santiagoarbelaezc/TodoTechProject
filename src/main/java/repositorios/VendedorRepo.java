package repositorios;

import modelo.entidades.Cajero;
import modelo.entidades.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepo extends JpaRepository<Vendedor, Long> {

}
