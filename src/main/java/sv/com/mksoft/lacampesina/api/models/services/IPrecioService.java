package sv.com.mksoft.lacampesina.api.models.services;

import java.util.List;

import sv.com.mksoft.lacampesina.api.models.entity.Cliente;
import sv.com.mksoft.lacampesina.api.models.entity.Precio;

public interface IPrecioService {

	public List<Precio> findAll();
	public Precio findById(Long id);
	public Precio save(Precio p);
	public void delete(Long id);
	public List<Precio> findByCliente(Cliente c);
	
}
