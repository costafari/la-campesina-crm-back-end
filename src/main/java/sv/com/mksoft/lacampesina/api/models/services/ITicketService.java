package sv.com.mksoft.lacampesina.api.models.services;

import java.util.List;

import sv.com.mksoft.lacampesina.api.models.entity.Ticket;

public interface ITicketService {
	
	public List<Ticket> findAll();
	public Ticket findById(Long id);
	public Ticket save(Ticket t);
	public void imprimirTicket(Ticket t);
	public Double getLecturaBascula(String s);

}
