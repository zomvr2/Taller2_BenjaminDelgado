import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Explore {
    private ArrayList<String> habitats;
    private Scanner scanner;
    private Pokedex pokedex;
    private Jugador jugador;
    private Random random;

    public Explore(Jugador jugador, Pokedex pokedex, Scanner scanner) {
        this.habitats = new ArrayList<>();
        this.jugador = jugador;
        this.pokedex = pokedex;
        this.scanner = scanner;
        this.random = new Random();
        cargarHabitats();
    }

    public void showMenu() {
        while (true) {
            System.out.println("Donde deseas ir a explorar?");
            System.out.println();
            System.out.println("Zonas disponibles:");
            int i;

            for (i = 0; i < habitats.size(); i++) {
                System.out.printf("%d) %s%n", i + 1, habitats.get(i));
            }
            System.out.printf("%d) Volver al menu.%n", i + 1);

            int opcion = leerNumero("Ingrese Zona: ", 1, habitats.size() + 1) - 1;

            if (opcion == i) return;
            if (opcion >= 0 && opcion < i) {
                String habitat = habitats.get(opcion);
                explore(habitat);
                return;
            }

            System.out.println("Zona no existente.");
        }
    }

    private void explore(String habitat) {
        ArrayList<Pokemon> foundInZone = pokedex.getPokemonesByHabitat(habitat);

        if (foundInZone.isEmpty()) {
            System.out.println("No se han encontrado pokemones en esta zona.");
            return;
        }

        Pokemon encontrado = obtenerPokemonAleatorio(foundInZone);

        System.out.printf("Oh!! Ha aparecido un increible %s!!%n%n", encontrado.getNombre());

        System.out.println("Que deseas hacer?");
        System.out.println();
        System.out.println("1) Capturar");
        System.out.println("2) Huir");

        int opcion = leerNumero("Ingrese Opcion: ", 1, 2);

        if (opcion == 2) {
            System.out.println("Has huido...");
            return;
        }

        if (jugador.tienePokemon(encontrado.getNombre())) {
            System.out.println("Ya tienes este pokemon, no lo puedes capturar de nuevo.");
            return;
        }

        Pokemon capturado = new Pokemon(encontrado, "Vivo");
        jugador.agregarPokemon(capturado);

        System.out.printf("%s capturado con exito!!%n", capturado.getNombre());
        if (jugador.getPokemones().size() <= 6) {
            System.out.printf("%s ha sido agregado a tu equipo!%n", capturado.getNombre());
        } else {
            System.out.printf("%s ha sido enviado al PC!%n", capturado.getNombre());
        }

    }

    private Pokemon obtenerPokemonAleatorio(ArrayList<Pokemon> pokemones) {
        double total = 0;
        for (int i = 0; i < pokemones.size(); i++) {
            total += pokemones.get(i).getProbAparicion();
        }

        if (total <= 0) {
            return pokemones.get(random.nextInt(pokemones.size()));
        }

        double r = random.nextDouble() * total;
        double acumulado = 0;

        for (int i = 0; i < pokemones.size(); i++) {
            acumulado += pokemones.get(i).getProbAparicion();
            if (r <= acumulado) {
                return pokemones.get(i);
            }
        }

        return pokemones.get(pokemones.size() - 1);
    }

    private void cargarHabitats() {
        try (Scanner scanner = new Scanner(new File("src/files/Habitats.txt"))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().trim();
                if (!linea.isEmpty()) {
                    this.habitats.add(linea);
                }
            }
        } catch (IOException err) {
            System.out.println("Error al cargar el archivo: " + err.getMessage());
        }
    }

    private String procesarInput(String out) {
        System.out.print(!out.isEmpty() ? out : "> ");
        String res = scanner.nextLine();

        return res;
    }

    private int leerNumero(String out, int min, int max) {
        while (true) {
            String entrada = procesarInput(out);
            try {
                int val = Integer.parseInt(entrada);
                if (val < min || val > max) {
                    System.out.printf("Opcion invalida. (%d-%d)%n", min, max);
                    continue;
                }
                return val;
            } catch (NumberFormatException err) {
                System.out.println("Entrada invalida, intenta de nuevo.");
            }
        }
    }
}
