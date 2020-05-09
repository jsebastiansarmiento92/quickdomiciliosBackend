package com.quick.restController;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.http.HttpStatus;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.quick.DTO.JwtDTO;
import com.quick.DTO.LoginUser;
import com.quick.DTO.Mensaje;
import com.quick.DTO.NewUser;
import com.quick.model.Rol;
import com.quick.model.RolNombre;
import com.quick.model.Usuario;
import com.quick.repositories.IRepoRol;
import com.quick.repositories.IRepoUsuario;
import com.quick.security.JWT.JwtProvider;
/**
 * clase que maneja las autenticaciones de los usuarios y registro
 * @author Juan Sebastian Sarmiento jsebastiansarmiento92@gmail.com
 * @version 12/04/2020
 */
@CrossOrigin(origins = "*")//pendiente colcoar la url a la que debe tener acceso muy importante
@RestController
@RequestMapping(value="/auth", method = { RequestMethod.GET, RequestMethod.POST })
public class AuthController {
	//atributo de la clase la cual realiza una instacia cada vez que es requerida gracias a la anotacion @Autowired gracias a este atributo se encriptan las contraseñas
	@Autowired
    PasswordEncoder passwordEncoder;
	//atributo de la clase la cual realiza una instacia cada vez que es requerida gracias a la anotacion @Autowired 
    @Autowired
    AuthenticationManager authenticationManager;
    //atributo de la clase la cual realiza una instacia cada vez que es requerida gracias a la anotacion @Autowired usando la interfaz de repositorio de usuarios para hacer una instancia de 
    //ese repositorio
    @Autowired
    IRepoUsuario iRepoUser;
    //atributo de la clase la cual realiza una instacia cada vez que es requerida gracias a la anotacion @Autowired usando la interfaz de repositorio de roles  para hacer una instancia de 
    //ese repositorio
    @Autowired
    IRepoRol iRepoRol;
    //atributo donde realiza instancia por usuario para guardar token y sesion entro otras credenciales necesarias
    @Autowired
    JwtProvider jwtProvider;
    
    /**
     * metodo para crear nuevo usuario o usuario en registro
     * @param nuevoUsuario
     * @param bindingResult
     * @return
     */
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NewUser nuevoUsuario, BindingResult bindingResult){
    	
 
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos vacíos o email inválido"), HttpStatus.BAD_REQUEST);
       
        if(iRepoUser.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getEmail(),nuevoUsuario.getRol(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<String> rolesStr = nuevoUsuario.getRoles();
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
        usuario.setRoles(roles);
        iRepoUser.save(usuario);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }
    /**
     * metodo para verificar la sesion del usuario una vez este ha verificado retorna de tipo JwtDTO 
     * @param loginUsuario
     * @param bindingResult
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUser loginUsuario, BindingResult bindingResult){
    	System.out.println("usuario login "+ loginUsuario.getNombreUsuario()+" contraseña "+ loginUsuario.getPassword());
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos vacíos o email inválido"), HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("usuario que esta: "+ userDetails.getUsername());
        System.out.println("privilegios: "+ userDetails.getAuthorities());
        String idUser=iRepoUser.findByNombreUsuario(loginUsuario.getNombreUsuario()).get().getId_usuario()+"";
        System.out.println("id enviado es : "+ idUser);
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities(),idUser);
        
        return new ResponseEntity<JwtDTO>(jwtDTO, HttpStatus.OK);
    }
}
