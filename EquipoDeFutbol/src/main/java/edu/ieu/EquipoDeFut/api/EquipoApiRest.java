package edu.ieu.EquipoDeFut.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ieu.EquipoDeFut.entities.Equipo;
import edu.ieu.EquipoDeFut.servicios.EquipoService;

@RestController
@RequestMapping("/api/equipo")
public class EquipoApiRest {
	@Autowired
	private EquipoService service; 
	
	@GetMapping
	public ResponseEntity<List<Equipo>> listAll(){
		List<Equipo> listaEquipo = service.findAll();
		if(listaEquipo.isEmpty()) {
			return new ResponseEntity<List<Equipo>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Equipo>>(listaEquipo, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Equipo> getEquipo(@PathVariable("id") int id) {
        System.out.println("buscando equipo con id " + id);
        Equipo equipo = service.findById(id);
        if (equipo == null) {
            System.out.println("Equpo con id " + id + " not found");
            return new ResponseEntity<Equipo>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Equipo>(equipo, HttpStatus.OK);
    }
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createCel(@RequestBody Equipo equipo){
		System.out.println("creando equipo " + equipo.getNombre_equipo());
	
		service.saveEquipo(equipo);
		 return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Equipo> updateEquipo(@PathVariable("id") int id,
			@RequestBody Equipo equipo){
		 System.out.println("actualizando equipo " + id);
		 Equipo equipobd = service.findById(id);
		 if(equipobd == null) {
			 System.out.println("Equipo con id " + id + " not found");
			 return new ResponseEntity<Equipo>(HttpStatus.NOT_FOUND);
		 }
		 equipobd.setNombre_equipo( equipo.getNombre_equipo() );
		 equipobd.setCiudad( equipo.getCiudad() );
		 equipobd.setEstadio( equipo.getEstadio() );
		 equipobd.setIntegrantes( equipo.getIntegrantes() );
		 
		 service.updateEquipo(equipobd);
		 return new ResponseEntity<Equipo>(equipobd, HttpStatus.OK );
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteEquipo(@PathVariable("id") int id){
		System.out.println("Buscando y borrando equiop con id " + id);
		Equipo equipo = service.findById(id);
		if(equipo == null) {
			 System.out.println("No se puede borrra equipo con id " + id + " not found");
			 return new ResponseEntity<Void>(HttpStatus.NOT_FOUND); // 404
		}
		service.deleteEquipoById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); // 204 http
	}
}
