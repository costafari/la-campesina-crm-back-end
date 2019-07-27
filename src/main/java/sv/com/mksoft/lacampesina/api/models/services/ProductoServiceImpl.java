package sv.com.mksoft.lacampesina.api.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sv.com.mksoft.lacampesina.api.models.dao.IProductoDao;
import sv.com.mksoft.lacampesina.api.models.entity.Producto;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private IProductoDao productoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) this.productoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		return this.productoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Producto save(Producto p) {
		return this.productoDao.save(p);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		this.productoDao.deleteById(id);
	}

}
