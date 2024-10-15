package interfaces;
import java.util.List;
import domain.Persona;

public interface PersonaRepository {
        void save(Persona persona);
        void update(Persona persona);
        void delete(int id);
        List<Persona> findAll();
        Persona findById(int id);
}



