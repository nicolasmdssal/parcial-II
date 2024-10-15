package Infraestructure;
import java.util.ArrayList;
import java.util.List;
import domain.Persona;
import interfaces.PersonaRepository;
import java.io.*;

public class PersonaRepositoryImpl implements PersonaRepository {
    private final String filePath = "personas.txt";

    public PersonaRepositoryImpl() {
        loadInitialData();
    }

    @Override
    public void save(Persona persona) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(persona.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Persona persona) {
        List<Persona> personas = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Persona p : personas) {
                if (p.getId() == persona.getId()) {
                    writer.write(persona.toString());
                } else {
                    writer.write(p.toString());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        List<Persona> personas = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Persona p : personas) {
                if (p.getId() != id) {
                    writer.write(p.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Persona> findAll() {
        List<Persona> personas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String nombre = parts[1];
                    int edad = Integer.parseInt(parts[2]);
                    personas.add(new Persona(id, nombre, edad));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personas;
    }

    @Override
    public Persona findById(int id) {
        for (Persona p : findAll()) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    private void loadInitialData() {
        findAll(); // Carga los datos al iniciar
    }
}


