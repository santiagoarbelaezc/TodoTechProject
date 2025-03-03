package repositorios;

import modelo.entidades.Cajero;
import modelo.entidades.Despachador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespachadorRepo extends JpaRepository<Despachador, Long> {
}
