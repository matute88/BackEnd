package com.portafolioluque.mjl.Controller;

import com.portafolioluque.mjl.Dto.DtoHardSoft;
import com.portafolioluque.mjl.Entity.Hardsoft;
import com.portafolioluque.mjl.Security.Controller.Mensaje;
import com.portafolioluque.mjl.Service.SHardsoft;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"https://frontendmjl.web.app/","http://localhost:4200"})
@RequestMapping("/skill")
public class CHardsoft {

    @Autowired
    SHardsoft shardsoft;

    @GetMapping("/lista")
    public ResponseEntity<List<Hardsoft>> list() {
        List<Hardsoft> list = shardsoft.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Hardsoft> getById(@PathVariable("id") int id) {
        if (!shardsoft.existsById(id)) {
            return new ResponseEntity(new Mensaje("Inexistente"), HttpStatus.NOT_FOUND);
        }
        Hardsoft hardSoft = shardsoft.getOne(id).get();
        return new ResponseEntity(hardSoft, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!shardsoft.existsById(id)) {
            return new ResponseEntity(new Mensaje("Inexistente"), HttpStatus.NOT_FOUND);
        }
        shardsoft.delete(id);
        return new ResponseEntity(new Mensaje("Habilidad eliminada"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoHardSoft dtohardsoft) {
        if (StringUtils.isBlank(dtohardsoft.getNombre())) {
            return new ResponseEntity(new Mensaje("Nombre obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (shardsoft.existsByNombre(dtohardsoft.getNombre())) {
            return new ResponseEntity(new Mensaje("Habilidad existente"), HttpStatus.BAD_REQUEST);
        }

        Hardsoft hardSoft = new Hardsoft(dtohardsoft.getNombre(), dtohardsoft.getPorcentaje());
        shardsoft.save(hardSoft);

        return new ResponseEntity(new Mensaje("Habilidad agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoHardSoft dtohardsoft) {
        //Validamos si existe el ID
        if (!shardsoft.existsById(id)) {
            return new ResponseEntity(new Mensaje("ID Inexistente"), HttpStatus.BAD_REQUEST);
        }
        //Compara nombre de skills
        if (shardsoft.existsByNombre(dtohardsoft.getNombre()) && shardsoft.getByNombre(dtohardsoft.getNombre()).get()
                .getId() != id) {
            return new ResponseEntity(new Mensaje("Habilidad existente"), HttpStatus.BAD_REQUEST);
        }
        //No puede estar vacio
        if (StringUtils.isBlank(dtohardsoft.getNombre())) {
            return new ResponseEntity(new Mensaje("Nombre obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Hardsoft hardSoft = shardsoft.getOne(id).get();
        hardSoft.setNombre(dtohardsoft.getNombre());
        hardSoft.setPorcentaje(dtohardsoft.getPorcentaje());

        shardsoft.save(hardSoft);
        return new ResponseEntity(new Mensaje("Habilidad actualizada"), HttpStatus.OK);

    }
}
