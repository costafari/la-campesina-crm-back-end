package sv.com.mksoft.lacampesina.api.models.services;

import sv.com.mksoft.lacampesina.api.models.entity.Usuario;

public interface IUsuarioService {
	
	public Usuario findByUsername(String username);
	
}
