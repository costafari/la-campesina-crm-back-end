package sv.com.mksoft.lacampesina.api.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sv.com.mksoft.lacampesina.api.models.entity.Cliente;
import sv.com.mksoft.lacampesina.api.models.entity.Precio;

public interface IPrecioDao extends JpaRepository<Precio, Long> {

	@Query("from Precio p where p.cliente = ?1")
	public List<Precio> findByCliente(Cliente c);
	
}
