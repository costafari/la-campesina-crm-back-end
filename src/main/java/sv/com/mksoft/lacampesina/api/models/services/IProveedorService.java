package sv.com.mksoft.lacampesina.api.models.services;

import java.util.List;

import sv.com.mksoft.lacampesina.api.models.entity.Proveedor;

public interface IProveedorService {

	public List<Proveedor> findAll();
	public Proveedor findById(Long id);
	public Proveedor save(Proveedor p);
	public void delete(Long id);
	
}
