package edu.ieu.EquipoDeFut.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.ieu.EquipoDeFut.entities.Equipo;

public interface EquipoRepository extends CrudRepository<Equipo, Integer>{
	 
		@Query("SELECT equipo FROM Equipo equipo WHERE equipo.nombre_equipo = ?1  ")
		public  Equipo findByModelo(String nombre_equipo);

}