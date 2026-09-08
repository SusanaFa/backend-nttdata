package cl.nttdata.personas.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import cl.nttdata.personas.model.Persona;

public interface  PersonaRepository extends JpaRepository<Persona, String> {
  public Persona findByRut(String rut);  
}
