import java.io.*;
import java.util.*;

public class AddressBook {
    private Map<String, String> contacts;
    private String fileName;

    // Constructor
    public AddressBook(String fileName) {
        this.contacts = new HashMap<>();
        this.fileName = fileName;
    }

    // Carga los contactos desde el archivo CSV
    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    contacts.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado, se creará uno nuevo al guardar.");
        } catch (IOException e) {
            System.out.println("Error al cargar los contactos: " + e.getMessage());
        }
    }

    // Guarda los contactos en el archivo CSV
    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los contactos: " + e.getMessage());
        }
    }

    // Lista todos los contactos
    public void list() {
        if (contacts.isEmpty()) {
            System.out.println("La agenda está vacía.");
            return;
        }
        System.out.println("Contactos:");
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    // Crea un nuevo contacto
    public void create(String phoneNumber, String name) {
        if (contacts.containsKey(phoneNumber)) {
            System.out.println("El número ya existe en la agenda.");
        } else {
            contacts.put(phoneNumber, name);
            System.out.println("Contacto agregado exitosamente.");
        }
    }

    // Elimina un contacto
    public void delete(String phoneNumber) {
        if (contacts.remove(phoneNumber) != null) {
            System.out.println("Contacto eliminado exitosamente.");
        } else {
            System.out.println("El número no existe en la agenda.");
        }
    }

    // Menú interactivo
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook("contacts.csv");
        addressBook.load(); // Carga los contactos al iniciar
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Agenda Telefónica ===");
            System.out.println("1. Listar contactos");
            System.out.println("2. Crear contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    addressBook.list();
                    break;
                case "2":
                    System.out.print("Ingrese el número telefónico: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Ingrese el nombre: ");
                    String name = scanner.nextLine();
                    addressBook.create(phoneNumber, name);
                    addressBook.save();
                    break;
                case "3":
                    System.out.print("Ingrese el número telefónico a eliminar: ");
                    phoneNumber = scanner.nextLine();
                    addressBook.delete(phoneNumber);
                    addressBook.save();
                    break;
                case "4":
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}