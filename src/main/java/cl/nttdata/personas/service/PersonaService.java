/**
 * 
 */
package cl.nttdata.personas.service;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cl.nttdata.personas.model.Persona;
import cl.nttdata.personas.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;

/**
 * 
 */

@Service
@RequiredArgsConstructor 
public class PersonaService {

 private final PersonaRepository personaRepository;

	public Optional<Persona> findByRut(String rut) {
					
		return personaRepository.findById(rut);
	}
	
}
