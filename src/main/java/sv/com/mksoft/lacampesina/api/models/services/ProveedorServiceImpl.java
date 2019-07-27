package sv.com.mksoft.lacampesina.api.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sv.com.mksoft.lacampesina.api.models.dao.IProveedorDao;
import sv.com.mksoft.lacampesina.api.models.entity.Proveedor;

@Service
public class ProveedorServiceImpl implements IProveedorService {

	@Autowired
	private IProveedorDao proveedorDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Proveedor> findAll() {
		return (List<Proveedor>) this.proveedorDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Proveedor findById(Long id) {
		return this.proveedorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Proveedor save(Proveedor p) {
		return this.proveedorDao.save(p);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		this.proveedorDao.deleteById(id);
	}

}
