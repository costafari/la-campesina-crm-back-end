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

import sv.com.mksoft.lacampesina.api.models.entity.Cliente;
import sv.com.mksoft.lacampesina.api.models.entity.Precio;
import sv.com.mksoft.lacampesina.api.models.services.IPrecioService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200"})
public class PrecioRestController {
	
	@Autowired
	private IPrecioService precioService;
	
	@GetMapping("/precios")
	public ResponseEntity<?> showAll() {

		List<Precio> precios = null;
		Map<String, Object> response = new HashMap<>();

		try {
			precios = this.precioService.findAll();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error SELECT a la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage().concat(" -> ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (precios == null) {
			response.put("mensaje", "No existen registros de precios en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Precio>>(precios, HttpStatus.OK);
	}
	
	@GetMapping("/precios/{id}")
	public ResponseEntity<?> showById(@PathVariable Long id) {

		Precio precio = null;
		Map<String, Object> response = new HashMap<>();

		try {
			precio = this.precioService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error SELECT a la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage().concat(" -> ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (precio == null) {
			response.put("mensaje",
					"El precio con ID: ".concat(id.toString()).concat(" no se encuentra en la base de datos."));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Precio>(precio, HttpStatus.OK);
	}
	
	@GetMapping("/precios/cliente/{c}")
	public ResponseEntity<?> showByCliente(@PathVariable Cliente c){
		
		List<Precio> precio = null;
		Map<String, Object> response = new HashMap<>();

		try {
			precio = this.precioService.findByCliente(c);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error SELECT a la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage().concat(" -> ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (precio == null) {
			response.put("mensaje",
					"El cliente: ".concat(!c.getNombreContacto().equals("") && !c.getNombreContacto().isEmpty()
							? c.getNombreContacto()
							: c.getNombreEmpresa()).concat(" no tiene precios en la base de datos."));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Precio>>(precio, HttpStatus.OK);
	}
	
	@PostMapping("/precios")
	public ResponseEntity<?> createPrecios(@Valid @RequestBody Precio precio, BindingResult result) {

		Precio p = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errores = result.getFieldErrors().stream()
					.map(err -> "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()))
					.collect(Collectors.toList());
			response.put("errores", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			p = this.precioService.save(precio);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error INSERT en la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage().concat(" -> ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El precio ha sido guardado correctamente.");
		response.put("precio", p);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	@PutMapping("/precios/{id}")
	public ResponseEntity<?> updatePrecio(@Valid @RequestBody Precio precio, BindingResult result, @PathVariable Long id) {

		Precio p = null;
		Precio precioActual = this.precioService.findById(id);
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errores = result.getFieldErrors().stream()
					.map(err -> "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()))
					.collect(Collectors.toList());
			response.put("errores", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (precio == null) {
			response.put("mensaje", "Error UPDATE no realizado para el cliente: ".concat(id.toString())
					.concat(". No se encuentra en la base de datos."));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			precioActual.setCliente(precio.getCliente());
			precioActual.setFechaFin(precio.getFechaFin());
			precioActual.setFechaInicio(precio.getFechaInicio());
			precioActual.setLote(precio.getLote());
			precioActual.setPrecio(precio.getPrecio());
			p = this.precioService.save(precioActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error UPDATE en la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage().concat(" -> ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El precio ha sido actualizado correctamente.");
		response.put("precio", p);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	@DeleteMapping("/precios/{id}")
	public ResponseEntity<?> deletePrecio(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			this.precioService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error DEL en la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage().concat(" -> ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El precio ha sido eliminado correctamente.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}


}
