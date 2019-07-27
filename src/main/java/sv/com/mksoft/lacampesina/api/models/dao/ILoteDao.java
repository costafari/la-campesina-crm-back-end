package sv.com.mksoft.lacampesina.api.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sv.com.mksoft.lacampesina.api.models.entity.Lote;
import sv.com.mksoft.lacampesina.api.models.entity.Proveedor;

public interface ILoteDao extends JpaRepository<Lote, Long> {

	@Query("from Lote l where l.proveedor = ?1")
	public List<Lote> findByProveedor(Proveedor p);
	
}
