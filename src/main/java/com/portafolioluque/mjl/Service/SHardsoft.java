package com.portafolioluque.mjl.Service;

import com.portafolioluque.mjl.Entity.Hardsoft;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.portafolioluque.mjl.Repository.RHardsoft;

@Service
@Transactional
public class SHardsoft {
    @Autowired
    RHardsoft rhardsoft;
    
    public List<Hardsoft> list(){
        return rhardsoft.findAll();
    }
    
    public Optional<Hardsoft> getOne(int id){
        return rhardsoft.findById(id);
    }
    
    public Optional<Hardsoft> getByNombre(String nombre){
        return rhardsoft.findByNombre(nombre);
    }
    
    public void save(Hardsoft skill){
        rhardsoft.save(skill);
    }
    
    public void delete(int id){
        rhardsoft.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rhardsoft.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return rhardsoft.existsByNombre(nombre);
    }
}
