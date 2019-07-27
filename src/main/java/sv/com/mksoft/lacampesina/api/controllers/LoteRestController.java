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

import sv.com.mksoft.lacampesina.api.models.entity.Lote;
import sv.com.mksoft.lacampesina.api.models.entity.Proveedor;
import sv.com.mksoft.lacampesina.api.models.services.ILoteService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:4200" })
public class LoteRestController {

	@Autowired
	private ILoteService loteService;

	@GetMapping("/lotes")
	public ResponseEntity<?> showAll() {
		
		List<Lote> lote = null;
		Map<String, Object> response = new HashMap<>();

		try {
			lote = this.loteService.findAll();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error SELECT a la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage().concat(" -> ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (lote == null) {
			response.put("mensaje", "No existen registros de lotes en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Lote>>(lote, HttpStatus.OK);
		
	}

	@GetMapping("/lotes/{id}")
	public ResponseEntity<?> showById(@PathVariable Long id) {

		Lote lote = null;
		Map<String, Object> response = new HashMap<>();

		try {
			lote = this.loteService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error SELECT a la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage().concat(" -> ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (lote == null) {
			response.put("mensaje",
					"El lote con ID: ".concat(id.toString()).concat(" no se encuentra en la base de datos."));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Lote>(lote, HttpStatus.OK);

	}

	@GetMapping("/lotes/proveedor/{p}")
	public ResponseEntity<?> showByProveedor(@PathVariable Proveedor p) {

		List<Lote> lotes = null;
		Map<String, Object> response = new HashMap<>();

		try {
			lotes = this.loteService.findByProveedor(p);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error SELECT a la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage().concat(" -> ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (lotes == null  || lotes.isEmpty() || lotes.size() == 0) {
			response.put("mensaje",
					"El proveedor: ".concat(!p.getNombreContacto().equals("") && !p.getNombreContacto().isEmpty()
							? p.getNombreContacto()
							: p.getNombreEmpresa()).concat(" no se encuentra en la base de datos."));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Lote>>(lotes, HttpStatus.OK);

	}

	@PostMapping("/lotes")
	public ResponseEntity<?> createLote(@Valid @RequestBody Lote lote, BindingResult result) {

		Lote l = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errores = result.getFieldErrors().stream()
					.map(err -> "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()))
					.collect(Collectors.toList());
			response.put("errores", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			l = this.loteService.save(lote);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error INSERT en la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage().concat(" -> ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El lote ha sido guardado correctamente.");
		response.put("lote", l);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@PutMapping("/lotes/{id}")
	public ResponseEntity<?> updateLote(@Valid @RequestBody Lote lote, BindingResult result, @PathVariable Long id) {

		Lote l = null;
		Lote loteActual = this.loteService.findById(id);
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errores = result.getFieldErrors().stream()
					.map(err -> "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()))
					.collect(Collectors.toList());
			response.put("errores", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (lote == null) {
			response.put("mensaje", "Error UPDATE no realizado para el cliente: ".concat(id.toString())
					.concat(". No se encuentra en la base de datos."));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			loteActual.setId(lote.getId());
			loteActual.setCantidad(lote.getCantidad());
			loteActual.setFechaEntrada(lote.getFechaEntrada());
			loteActual.setLote(lote.getLote());
			loteActual.setProducto(lote.getProducto());
			loteActual.setProveedor(lote.getProveedor());
			l = this.loteService.save(loteActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error UPDATE en la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage().concat(" -> ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El lote ha sido actualizado correctamente.");
		response.put("lote", l);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@DeleteMapping("/lotes/{id}")
	public ResponseEntity<?> deleteLote(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			this.loteService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error DEL en la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage().concat(" -> ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El lote ha sido eliminado correctamente.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
