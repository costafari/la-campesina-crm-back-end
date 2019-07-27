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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sv.com.mksoft.lacampesina.api.models.entity.Ticket;
import sv.com.mksoft.lacampesina.api.models.services.ITicketService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:4200" })
public class TicketRestController {
	
	@Autowired
	private ITicketService ticketService;

	@GetMapping("/tickets")
	public ResponseEntity<?> showAll() {
		
		List<Ticket> tickets = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			tickets = this.ticketService.findAll();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error SELECT a la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage().concat(" -> ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(tickets == null) {
			response.put("mensaje", "No existen registros de tickets en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);		
	}
	
	@GetMapping("/tickets/{id}")
	public ResponseEntity<?> showById(@PathVariable Long id) {
		
		Ticket ticket = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			ticket = this.ticketService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error SELECT a la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage().concat(" -> ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(ticket == null) {
			response.put("mensaje", "El ticket con ID: ".concat(id.toString()).concat(" no se encuentra en la base de datos."));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
	}
	
	@PostMapping("/tickets")
	public ResponseEntity<?> createTicket(@Valid @RequestBody Ticket ticket, BindingResult result) {
		
		Ticket t = null;
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			List<String> errores = result.getFieldErrors().stream()
					.map(err -> "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()))
					.collect(Collectors.toList());
			response.put("errores", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			t = this.ticketService.save(ticket);
		} catch (DataAccessException e) {	
			response.put("mensaje", "Error INSERT en la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage().concat(" -> ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El ticket ha sido guardado correctamente.");
		response.put("ticket", t);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
}
