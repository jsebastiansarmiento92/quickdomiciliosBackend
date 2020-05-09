package com.quick.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.quick.model.Rol;
import com.quick.model.Usuario;
import com.quick.repositories.IRepoRol;


@CrossOrigin(origins = "*",maxAge = 3600,methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("/roles")
public class RestRolController {

	@Autowired
	private IRepoRol repoRol;
	
	@GetMapping
	public List<Rol>listar(){
		return repoRol.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public Optional<Rol> getRol(@PathVariable("id") Integer id){
		return repoRol.findById(id);
	}
	@CrossOrigin
	@PostMapping
	public Rol insertar(@RequestBody Rol rol){
	
		return repoRol.save(rol);
	}
	
	@PutMapping
	public void modificar(@RequestBody Rol rol){
		
		repoRol.save(rol);
	}
	
	
	
	
	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		repoRol.deleteById(id);
	}
	
}
