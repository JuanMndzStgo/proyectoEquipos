package edu.ieu.EquipoDeFut.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ieu.EquipoDeFut.entities.Equipo;
import edu.ieu.EquipoDeFut.repositorios.EquipoRepository;

@Service
public class EquipoServiceImp implements EquipoService {
	@Autowired
	private EquipoRepository equipoDao;

	//buscar por id
	@Override
	public Equipo findById(Integer id) {
		return equipoDao.findById( id)
				.orElse(null);
	
	}

	//buscar por nombre
	@Override
	public Equipo findByNombre_equipo(String nombre) {
		return equipoDao.findByModelo(nombre);
	
	}

	
	@Override
	public List<Equipo> findAll() {
		List<Equipo> lista = new ArrayList<>();
		equipoDao.findAll()
			.forEach( lista::add );
		return lista;
	}

	
	@Override
	public boolean isEquipoExist(Equipo cel) {
		return equipoDao.existsById(cel.getId() );
	}

	
	@Override @Transactional
	public void saveEquipo(Equipo equipo) {
		equipoDao.save(equipo);
		
	}

	
	@Override @Transactional
	public void updateEquipo(Equipo equipo) {
		Equipo equipodb = equipoDao.findById(equipo.getId() ).orElse(null);
		if(equipodb != null) {
			equipodb.setNombre_equipo( equipo.getNombre_equipo() );
			equipodb.setCiudad( equipo.getCiudad() );
			equipodb.setEstadio( equipo.getEstadio() );
			equipodb.setIntegrantes( equipo.getIntegrantes() );
			
			
			equipoDao.save(equipodb);
		}
	}

	@Override @Transactional
	public void deleteEquipoById(Integer id) {
		equipoDao.deleteById(  id );	
	}
}