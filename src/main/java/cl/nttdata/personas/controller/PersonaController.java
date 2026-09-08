/**
 * 
 */
package cl.nttdata.personas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.nttdata.personas.model.Persona;
import cl.nttdata.personas.service.PersonaService;
import lombok.RequiredArgsConstructor;

/**
 * 
 */
@RestController
@RequiredArgsConstructor
public class PersonaController {

	private final PersonaService personaService;
	
	@GetMapping("/persona/v1/find")
	public Persona findByRut(@RequestParam String rut ) {
		 return personaService.findByRut(rut).orElse(null);
	}
	
}
