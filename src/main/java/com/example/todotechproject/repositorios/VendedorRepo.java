package com.example.todotechproject.repositorios;

import com.example.todotechproject.modelo.entidades.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepo extends JpaRepository<Vendedor, Long> {

}
