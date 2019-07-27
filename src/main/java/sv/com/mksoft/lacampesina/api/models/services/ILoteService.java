package sv.com.mksoft.lacampesina.api.models.services;

import java.util.List;

import sv.com.mksoft.lacampesina.api.models.entity.Lote;
import sv.com.mksoft.lacampesina.api.models.entity.Proveedor;

public interface ILoteService {
	
	public List<Lote> findAll();
	public Lote findById(Long id);
	public Lote save(Lote l);
	public void delete(Long id);
	public List<Lote> findByProveedor(Proveedor p);

}
