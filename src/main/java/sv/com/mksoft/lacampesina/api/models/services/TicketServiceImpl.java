package sv.com.mksoft.lacampesina.api.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sv.com.mksoft.lacampesina.api.models.dao.ITicketDao;
import sv.com.mksoft.lacampesina.api.models.entity.Ticket;

@Service
public class TicketServiceImpl implements ITicketService {

	@Autowired
	private ITicketDao ticketDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Ticket> findAll() {
		return (List<Ticket>) this.ticketDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Ticket findById(Long id) {
		return this.ticketDao.findById(id).orElse(null);
	}

	@Override
	public Ticket save(Ticket t) {
		return this.ticketDao.save(t);
	}

	@Override
	public void imprimirTicket(Ticket t) {
		// TODO Implementar este metodo...
	}

	@Override
	@Transactional(readOnly = true)
	public Double getLecturaBascula(String s) {
		// TODO Implementar este metodo...
		return null;
	}

}
