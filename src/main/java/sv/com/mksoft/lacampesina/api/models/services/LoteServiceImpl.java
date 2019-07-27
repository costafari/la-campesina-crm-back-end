package sv.com.mksoft.lacampesina.api.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sv.com.mksoft.lacampesina.api.models.dao.ILoteDao;
import sv.com.mksoft.lacampesina.api.models.entity.Lote;
import sv.com.mksoft.lacampesina.api.models.entity.Proveedor;

@Service
public class LoteServiceImpl implements ILoteService {

	@Autowired
	private ILoteDao loteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Lote> findAll() {
		return this.loteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Lote findById(Long id) {
		return this.loteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Lote save(Lote l) {
		return this.loteDao.save(l);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		this.loteDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Lote> findByProveedor(Proveedor p) {
		return this.loteDao.findByProveedor(p);
	}



}
