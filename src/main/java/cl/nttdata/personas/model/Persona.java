/**
 * 
 */
package cl.nttdata.personas.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 
 */
@Data
@Entity
@Table(name = "personas")
public class Persona {

	@Id 
	private String rut;	

	private String nombre;
	private String apellido;
	private LocalDate fechaNas;
	
}
