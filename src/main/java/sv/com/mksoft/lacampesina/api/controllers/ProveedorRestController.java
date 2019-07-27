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

import sv.com.mksoft.lacampesina.api.models.entity.Proveedor;
import sv.com.mksoft.lacampesina.api.models.services.IProveedorService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:4200" })
public class ProveedorRestController {
	
	@Autowired
	private IProveedorService proveedorService;
	
	@GetMapping("/proveedores")
	public ResponseEntity<?> showAll() {

		List<Proveedor> proveedor = null;
		Map<String, Object> response = new HashMap<>();

		try {
			proveedor = this.proveedorService.findAll();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error SELECT a la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage().concat(" -> ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (proveedor == null) {
			response.put("mensaje", "No existen registros de proveedores en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Proveedor>>(proveedor, HttpStatus.OK);
	}
	
	@GetMapping("/proveedores/{id}")
	public ResponseEntity<?> showById(@PathVariable Long id) {

		Proveedor proveedor = null;
		Map<String, Object> response = new HashMap<>();

		try {
			proveedor = this.proveedorService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error SELECT a la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage().concat(" -> ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (proveedor == null) {
			response.put("mensaje",
					"El proveedor con ID: ".concat(id.toString()).concat(" no se encuentra en la base de datos."));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Proveedor>(proveedor, HttpStatus.OK);
	}
	
	@PostMapping("/proveedores")
	public ResponseEntity<?> createProveedor(@Valid @RequestBody Proveedor proveedor, BindingResult result) {

		Proveedor p = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errores = result.getFieldErrors().stream()
					.map(err -> "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()))
					.collect(Collectors.toList());
			response.put("errores", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			p = this.proveedorService.save(proveedor);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error INSERT en la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage().concat(" -> ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El proveedor ha sido guardado correctamente.");
		response.put("proveedor", p);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	@PutMapping("/proveedores/{id}")
	public ResponseEntity<?> updateProveedor(@Valid @RequestBody Proveedor proveedor, BindingResult result, @PathVariable Long id) {

		Proveedor p = null;
		Proveedor proveedorActual = this.proveedorService.findById(id);
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errores = result.getFieldErrors().stream()
					.map(err -> "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()))
					.collect(Collectors.toList());
			response.put("errores", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (proveedor == null) {
			response.put("mensaje", "Error UPDATE no realizado para el cliente: ".concat(id.toString())
					.concat(". No se encuentra en la base de datos."));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			proveedorActual.setDireccion(proveedor.getDireccion());
			proveedorActual.setNombreContacto(proveedor.getNombreContacto());
			proveedorActual.setNombreEmpresa(proveedor.getNombreEmpresa());
			proveedorActual.setNotas(proveedor.getNotas());
			proveedorActual.setTelefonoFijo(proveedor.getTelefonoFijo());
			proveedorActual.setTelefonoFijo2(proveedor.getTelefonoFijo2());
			proveedorActual.setTelefonoMovil(proveedor.getTelefonoMovil());
			proveedorActual.setTelefonoMovil2(proveedor.getTelefonoMovil2());
			p = this.proveedorService.save(proveedorActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error UPDATE en la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage().concat(" -> ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El proveedor ha sido actualizado correctamente.");
		response.put("proveedor", p);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	@DeleteMapping("/proveedores/{id}")
	public ResponseEntity<?> deleteProveedor(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			this.proveedorService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error DEL en la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage().concat(" -> ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El proveedor ha sido eliminado correctamente.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
