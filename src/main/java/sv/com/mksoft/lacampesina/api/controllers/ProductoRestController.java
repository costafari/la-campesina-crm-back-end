package sv.com.mksoft.lacampesina.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sv.com.mksoft.lacampesina.api.models.entity.Producto;
import sv.com.mksoft.lacampesina.api.models.services.IProductoService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:4200" })
public class ProductoRestController {

	@Autowired
	private IProductoService productoService;

	@GetMapping("/productos")
	public ResponseEntity<?> showAll() {

		List<Producto> producto = null;
		Map<String, Object> response = new HashMap<>();

		try {
			producto = this.productoService.findAll();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error SELECT a la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage().concat(" -> ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (producto == null) {
			response.put("mensaje", "No existen registros de productos en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Producto>>(producto, HttpStatus.OK);
	}

	@GetMapping("/productos/{id}")
	public ResponseEntity<?> showById(@PathVariable Long id) {

		Producto producto = null;
		Map<String, Object> response = new HashMap<>();

		try {
			producto = this.productoService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error SELECT a la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage().concat(" -> ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (producto == null) {
			response.put("mensaje",
					"El producto con ID: ".concat(id.toString()).concat(" no se encuentra en la base de datos."));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}
	
	@PostMapping("/productos")
	public ResponseEntity<?> createProducto(@Valid @RequestBody Producto producto, BindingResult result) {

		Producto p = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errores = result.getFieldErrors().stream()
					.map(err -> "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()))
					.collect(Collectors.toList());
			response.put("errores", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			p = this.productoService.save(producto);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error INSERT en la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage().concat(" -> ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El producto ha sido guardado correctamente.");
		response.put("producto", p);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	@PutMapping("/productos/{id}")
	public ResponseEntity<?> updateProducto(@Valid @RequestBody Producto producto, BindingResult result, @PathVariable Long id) {

		Producto p = null;
		Producto productoActual = this.productoService.findById(id);
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errores = result.getFieldErrors().stream()
					.map(err -> "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()))
					.collect(Collectors.toList());
			response.put("errores", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (producto == null) {
			response.put("mensaje", "Error UPDATE no realizado para el cliente: ".concat(id.toString())
					.concat(". No se encuentra en la base de datos."));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			productoActual.setDescripcion(producto.getDescripcion());
			productoActual.setNombre(producto.getNombre());
			productoActual.setNotas(producto.getNotas());
			p = this.productoService.save(productoActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error UPDATE en la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage().concat(" -> ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El producto ha sido actualizado correctamente.");
		response.put("producto", p);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	@DeleteMapping("/productos/{id}")
	public ResponseEntity<?> deleteProducto(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			this.productoService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error DEL en la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage().concat(" -> ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El producto ha sido eliminado correctamente.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
