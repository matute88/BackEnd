package com.portafolioluque.mjl.Repository;

import com.portafolioluque.mjl.Entity.Hardsoft;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RHardsoft extends JpaRepository<Hardsoft, Integer>{
    Optional<Hardsoft> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
