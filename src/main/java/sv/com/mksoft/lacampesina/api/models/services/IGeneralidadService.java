package sv.com.mksoft.lacampesina.api.models.services;

import java.util.List;

import sv.com.mksoft.lacampesina.api.models.entity.Generalidad;

public interface IGeneralidadService {

	public List<Generalidad> findAll();
	public Generalidad findById(Long id);
	public Generalidad save(Generalidad g);
	public void delete(Long id);
	
}
