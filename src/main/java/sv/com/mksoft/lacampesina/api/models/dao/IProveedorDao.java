package sv.com.mksoft.lacampesina.api.models.dao;

import org.springframework.data.repository.CrudRepository;

import sv.com.mksoft.lacampesina.api.models.entity.Proveedor;

public interface IProveedorDao extends CrudRepository<Proveedor, Long> {

}
