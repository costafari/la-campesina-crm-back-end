package sv.com.mksoft.lacampesina.api.models.dao;

import org.springframework.data.repository.CrudRepository;

import sv.com.mksoft.lacampesina.api.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

}
