package sv.com.mksoft.lacampesina.api.models.dao;

import org.springframework.data.repository.CrudRepository;

import sv.com.mksoft.lacampesina.api.models.entity.Role;

public interface IRoleDao extends CrudRepository<Role, Long> {

}
