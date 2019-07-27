package sv.com.mksoft.lacampesina.api.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sv.com.mksoft.lacampesina.api.models.dao.IGeneralidadDao;
import sv.com.mksoft.lacampesina.api.models.entity.Generalidad;

@Service
public class GeneralidadServiceImpl implements IGeneralidadService {

	@Autowired
	private IGeneralidadDao generalidadDao;	
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Generalidad> findAll() {
		return (List<Generalidad>) this.generalidadDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Generalidad findById(Long id) {
		return this.generalidadDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Generalidad save(Generalidad g) {
		return this.generalidadDao.save(g);
	}

	@Override
	@Transactional(readOnly = true)
	public void delete(Long id) {
		this.generalidadDao.deleteById(id);
	}

}
