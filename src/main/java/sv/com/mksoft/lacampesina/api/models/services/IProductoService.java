package sv.com.mksoft.lacampesina.api.models.services;

import java.util.List;

import sv.com.mksoft.lacampesina.api.models.entity.Producto;

public interface IProductoService {

	public List<Producto> findAll();
	public Producto findById (Long id);
	public Producto save(Producto p);
	public void delete(Long id);
	
}
