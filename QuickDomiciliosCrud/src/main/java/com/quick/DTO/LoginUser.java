package com.quick.DTO;

import javax.validation.constraints.NotBlank;
/**Clase donde guarda el modelo las credenciales del usuario
 * 
 * @author Juan Sebastian Sarmiento jsebastiansarmiento92@gmail.com
 * @version 12/04/2020
 */
public class LoginUser {
	//atributo donde guarda el nombre de usuario
		@NotBlank
		private String nombreUsuario;
		//atributo donde guarda contraseña del usuario (teniendo en cuenta que viene previamente encriptada)
		@NotBlank
		private String password;


		//respectivos get y set de atributos
		/**
		 * 
		 * @return
		 */

		public String getNombreUsuario() {
			return nombreUsuario;
		}
		/**
		 * 
		 * @param nombreUsuario
		 */
		public void setNombreUsuario(String nombreUsuario) {
			this.nombreUsuario = nombreUsuario;
		}
		/**
		 * 
		 * @return
		 */
		public String getPassword() {
			return password;
		}
		/**
		 * 
		 * @param password
		 */
		public void setPassword(String password) {
			this.password = password;
		}
}
