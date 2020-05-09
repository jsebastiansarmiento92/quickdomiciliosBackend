package com.quick.restController;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import com.quick.DTO.Mensaje;
import com.quick.DTO.NewUser;
import com.quick.model.Rol;
import com.quick.model.RolNombre;
import com.quick.model.Usuario;
import com.quick.repositories.IRepoRol;
import com.quick.repositories.IRepoUsuario;



@CrossOrigin(origins = "*",maxAge = 3600)//pendiente colcoar la url a la que debe tener acceso muy importante
@RestController
@RequestMapping("/usuarios")
public class RestUsuarios {

	@Autowired
	private IRepoUsuario repoUsuario;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Autowired
	private IRepoRol iRepoRol;
	
	
	@GetMapping
	public List<Usuario>listar(){
		return repoUsuario.findAll();
	}
	
	@PostMapping
	public ResponseEntity insertar(@RequestBody NewUser usuario){
		
		
		System.out.println("usuario y roles que llegan "+ usuario.toString());
		
		
		
		if (usuario.getRol().getId()==0||usuario.getRol().getId()+""==null) 
			return new ResponseEntity(new Mensaje("por favor seleccionar un rol para el usuario"), HttpStatus.BAD_REQUEST);
		if(usuario.getNombreUsuario()==null||usuario.getNombreUsuario()=="")
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(repoUsuario.existsByEmail(usuario.getEmail()))
            return new ResponseEntity(new Mensaje("ese nombre o email ya existe"), HttpStatus.BAD_REQUEST);
       
		
		//System.out.println("el nombre que llega desde el front es ;"+usuario.getNombreUsuario());
        
        Usuario usuarioNuevo =
                new Usuario(usuario.getEmail(),usuario.getRol(),
                        passwordEncoder.encode(usuario.getPassword()));
        usuarioNuevo.setNombreUsuario(usuario.getNombreUsuario());
        
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		  Set<String> rolesStr = usuario.getRoles();
	        Set<Rol> roles = new HashSet<>();
	        
	        for (String rol : rolesStr) {
	            switch (rol) {
	            case "ADMINISTRADOR":
                    Rol rolAdmin = iRepoRol.findByRolNombre(RolNombre.ADMINISTRADOR).get();
                    roles.add(rolAdmin);
                    break;
                case "RECEPCIONISTA":
                    Rol rolAdmin2 = iRepoRol.findByRolNombre(RolNombre.RECEPCIONISTA).get();
                    roles.add(rolAdmin2);
                    break;
                case "DOMICILIARIO":
                    Rol rolAdmin3 = iRepoRol.findByRolNombre(RolNombre.DOMICILIARIO).get();
                    roles.add(rolAdmin3);
                    break;
                case "LOGISTICO":
                    Rol rolAdmin4 = iRepoRol.findByRolNombre(RolNombre.LOGISTICO).get();
                    roles.add(rolAdmin4);
                    break;
                case "USUARIO_FINAL":
                    Rol rolAdmin5 = iRepoRol.findByRolNombre(RolNombre.USUARIO_FINAL).get();
                    roles.add(rolAdmin5);
                    break;
                    
                default:
                    Rol rolUser = iRepoRol.findByRolNombre(RolNombre.USUARIO_FINAL).get();
                    roles.add(rolUser);
	            }
	        }
	        usuarioNuevo.setRoles(roles);
		repoUsuario.save(usuarioNuevo);
		return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
	}
	
	
	@GetMapping(value = "/{id}")
	public Optional<Usuario> getUsuario(@PathVariable("id") Integer id){
		return repoUsuario.findById(id);
	}
	
	//@GetMapping(value = "/finalCaja/{id}")
//	public List<Usuario> getUsuarioCaja(@PathVariable("id") Integer id){
//		return repoUsuario.findByIdRol(id);
//	}
	
	@PutMapping("/{id}")
	public ResponseEntity modificar(@RequestBody Usuario usuario,@PathVariable("id")Integer id){
		System.out.println("id que llega desde el frontend es "+id);
		System.out.println(usuario.toString());
		if (usuario.getRol().getId()==0||usuario.getRol().getId()+""==null) 
			return new ResponseEntity(new Mensaje("por favor seleccionar un rol para el usuario"), HttpStatus.BAD_REQUEST);
		 if(!repoUsuario.existsById(id))
			 return new ResponseEntity(new Mensaje("usuario no existe"), HttpStatus.NOT_FOUND);
		 if(usuario.getNombreUsuario()==null||usuario.getNombreUsuario()=="")
			 return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		 if(usuario.getEmail() == null || usuario.getEmail()=="")
			 return new ResponseEntity(new Mensaje("el email es obligaroio"), HttpStatus.BAD_REQUEST);
		// if(repoUsuario.existsByEmail(usuario.getEmail()))
		//	 return new ResponseEntity(new Mensaje("el email ya esta en el sistema"), HttpStatus.BAD_REQUEST); pendiente por aplicar en caso de que se requiera
		 if(repoUsuario.existsByEmail(usuario.getEmail())&&repoUsuario.findByEmail(usuario.getEmail()).get().getId_usuario()!= id)
	            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
		 Usuario usuarioUpdate = repoUsuario.findById(id).get();
		 usuarioUpdate.setRol(usuario.getRol());
		 usuarioUpdate.setNombreUsuario(usuario.getNombreUsuario());
		 usuarioUpdate.setEmail(usuario.getEmail());
		 usuarioUpdate.setPassword(usuario.getPassword());
		
		 repoUsuario.save(usuarioUpdate);
	        return new ResponseEntity(new Mensaje("usuario actualizado"), HttpStatus.CREATED);
		 
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity eliminar(@PathVariable("id") Integer id) {
		if(!repoUsuario.existsById(id))
            return new ResponseEntity(new Mensaje("no existe ese usuario"), HttpStatus.NOT_FOUND);
		repoUsuario.deleteById(id);
        return new ResponseEntity(new Mensaje("Usuario eliminado"), HttpStatus.OK);
		
	}
	
	
}

