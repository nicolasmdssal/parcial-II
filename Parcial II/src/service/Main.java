package service;

import Infraestructure.PersonaRepositoryImpl;
import domain.Persona;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final PersonaRepositoryImpl repository = new PersonaRepositoryImpl();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int option;
        do {
            System.out.println("1. Registrar nueva persona");
            System.out.println("2. Actualizar datos de la persona");
            System.out.println("3. Mostrar lista de personas registradas");
            System.out.println("4. Eliminar persona");
            System.out.println("5. Salir");
            System.out.print("Elija una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    registrarPersona();
                    break;
                case 2:
                    actualizarPersona();
                    break;
                case 3:
                    mostrarPersonas();
                    break;
                case 4:
                    eliminarPersona();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (option != 5);
    }

    private static void registrarPersona() {
        System.out.print("Ingrese ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese Edad: ");
        int edad = scanner.nextInt();

        Persona persona = new Persona(id, nombre, edad);
        repository.save(persona);
        System.out.println("Persona registrada con éxito.");
    }

    private static void actualizarPersona() {
        System.out.print("Ingrese ID de la persona a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        Persona persona = repository.findById(id);

        if (persona != null) {
            System.out.print("Nuevo Nombre (actual: " + persona.getNombre() + "): ");
            String nombre = scanner.nextLine();
            System.out.print("Nueva Edad (actual: " + persona.getEdad() + "): ");
            int edad = scanner.nextInt();

            persona.setNombre(nombre);
            persona.setEdad(edad);
            repository.update(persona);
            System.out.println("Persona actualizada con éxito.");
        } else {
            System.out.println("Persona no encontrada.");
        }
    }

    private static void mostrarPersonas() {
        List<Persona> personas = repository.findAll();
        if (personas.isEmpty()) {
            System.out.println("No hay personas registradas.");
        } else {
            System.out.println("Lista de personas registradas:");
            for (Persona persona : personas) {
                System.out.println(persona);
            }
        }
    }

    private static void eliminarPersona() {
        System.out.print("Ingrese ID de la persona a eliminar: ");
        int id = scanner.nextInt();
        repository.delete(id);
        System.out.println("Persona eliminada (si existía).");
    }
}

