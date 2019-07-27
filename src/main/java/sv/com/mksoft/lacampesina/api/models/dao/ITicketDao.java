package sv.com.mksoft.lacampesina.api.models.dao;

import org.springframework.data.repository.CrudRepository;

import sv.com.mksoft.lacampesina.api.models.entity.Ticket;

public interface ITicketDao extends CrudRepository<Ticket, Long>{

}
