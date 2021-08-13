package edu.ieu.EquipoDeFut.servicios;

import java.util.List;

import edu.ieu.EquipoDeFut.entities.Equipo;

public interface EquipoService {
	Equipo findById(Integer id);
	Equipo findByNombre_equipo(String nombre_equipo);
    List<Equipo> findAll(); 
    boolean isEquipoExist(Equipo equipo);
    //CREAR 
    void saveEquipo(Equipo equipo);
     //Actualizar
    void updateEquipo(Equipo equipo);
    //Borrar
    void deleteEquipoById(Integer id);
}