package com.quick.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quick.DTO.Mensaje;
import com.quick.model.Caja;
import com.quick.model.Usuario;
import com.quick.repositories.IRepoCaja;

@CrossOrigin(origins = "*",maxAge = 3600)//http://localhost:4200
@RestController
@RequestMapping("/cajas")
public class RestCaja {

	@Autowired
	private IRepoCaja iRepoCaja;
	
	
	
	@GetMapping
	public List<Caja>listar(){
		return iRepoCaja.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public Optional<Caja> getCaja(@PathVariable("id") Integer id){
		return iRepoCaja.findById(id);
	}
	
	@PostMapping
	public ResponseEntity insertar(@RequestBody Caja caja){
		System.out.println("datos que ingresan "+ caja.toString());
	
		
		if (caja.getIdAsignaDinero()==0||caja.getIdAsignaDinero()+""==null) 
			return new ResponseEntity(new Mensaje("por favor verificar la sesion de usuario"), HttpStatus.BAD_REQUEST);
		
		//if (caja.getUsuario().getId_usuario()==0||caja.getUsuario()==null) 
		//	return new ResponseEntity(new Mensaje("por favor seleccionar un usuario"), HttpStatus.BAD_REQUEST);
		
		if (caja.getBaseAsignadaCaja()==0||caja.getBaseAsignadaCaja()+""==null) 
			return new ResponseEntity(new Mensaje("por favor ingresar un valor a la base"), HttpStatus.BAD_REQUEST);
		
		if (caja.getDineroInicialCaja()==0||caja.getDineroInicialCaja()+""==null) 
			return new ResponseEntity(new Mensaje("por favor ingresar un valor diferente de cero al dinero inicial"), HttpStatus.BAD_REQUEST);
		
		if (caja.getEstadoCaja()==""||caja.getEstadoCaja()==null) 
			return new ResponseEntity(new Mensaje("por favor verificar, estado de caja no valido"), HttpStatus.BAD_REQUEST);
       
		
		
		//usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

		iRepoCaja.save(caja);
		return new ResponseEntity(new Mensaje("caja guardada"), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity modificar(@RequestBody Caja caja,@PathVariable("id")Integer id){
			
		Caja cajaUpdate= iRepoCaja.findById(id).get();
		cajaUpdate.setBaseAsignadaCaja(caja.getBaseAsignadaCaja());
		cajaUpdate.setDineroInicialCaja(caja.getDineroInicialCaja());
		cajaUpdate.setEstadoCaja(caja.getEstadoCaja());
		
		iRepoCaja.save(cajaUpdate);
	        return new ResponseEntity(new Mensaje("caja actualizada"), HttpStatus.CREATED);
		 
		
	}
	
	
	
}
