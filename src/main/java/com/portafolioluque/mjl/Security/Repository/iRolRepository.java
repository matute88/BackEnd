package com.portafolioluque.mjl.Security.Repository;

import com.portafolioluque.mjl.Security.Entity.Rol;
import com.portafolioluque.mjl.Security.Enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iRolRepository extends JpaRepository<Rol, Integer>{
 Optional<Rol> findByRolNombre(RolNombre rolNombre);   
   }