package com.quick.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quick.DTO.Mensaje;
import com.quick.model.Caja;
import com.quick.model.Empresa;

import com.quick.repositories.IRepoEmpresa;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/empresas")
public class RestEmpresa {
	
	@Autowired
	private  IRepoEmpresa iRepoEmpresa;
	
	
	
	@GetMapping
	public List<Empresa>listar(){
		return iRepoEmpresa.findAll();
	}
	
	@PostMapping
	public ResponseEntity insertar(@RequestBody Empresa empresa){
		System.out.println("datos que ingresan "+ empresa.toString());
	
		

		if (empresa.getEmail()==""||empresa.getEmail()==null) 
			return new ResponseEntity(new Mensaje("email obligatorio"), HttpStatus.BAD_REQUEST);
		//pendiente verificar y arreglar esta seccion donde retorne error 
		
		//usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

		iRepoEmpresa.save(empresa);
		return new ResponseEntity(new Mensaje("empresa guardada"), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity eliminar(@PathVariable("id") Integer id) {
		if(!iRepoEmpresa.existsById(id))
            return new ResponseEntity(new Mensaje("No existe esa empresa"), HttpStatus.NOT_FOUND);
		iRepoEmpresa.deleteById(id);
        return new ResponseEntity(new Mensaje("Empresa eliminada"), HttpStatus.OK);
		
	}
	
}
