package sv.com.mksoft.lacampesina.api.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sv.com.mksoft.lacampesina.api.models.dao.IPrecioDao;
import sv.com.mksoft.lacampesina.api.models.entity.Cliente;
import sv.com.mksoft.lacampesina.api.models.entity.Precio;

@Service
public class PrecioServiceImpl implements IPrecioService {

	@Autowired
	private IPrecioDao precioDao;	
	
	@Override
	@Transactional(readOnly = true)
	public List<Precio> findAll() {
		return (List<Precio>) this.precioDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Precio findById(Long id) {
		return this.precioDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Precio> findByCliente(Cliente c) {
		return (List<Precio>) this.precioDao.findByCliente(c);
	}

	@Override
	@Transactional
	public Precio save(Precio p) {
		return this.precioDao.save(p);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		this.precioDao.deleteById(id);
	}

}
