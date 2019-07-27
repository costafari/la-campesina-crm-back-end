package sv.com.mksoft.lacampesina.api.models.dao;

import org.springframework.data.repository.CrudRepository;

import sv.com.mksoft.lacampesina.api.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long> {

}
