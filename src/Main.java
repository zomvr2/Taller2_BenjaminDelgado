import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Jugador jugador = cargarPartida();
        Menu menu = new Menu(jugador);

        menu.mostrar();
    }

    public static Jugador cargarPartida() {
        Pokedex pokedex = new Pokedex();
        try (Scanner scanner = new Scanner(new File("src/files/Registros.txt"))) {
            if (!scanner.hasNextLine()) return null;

            String lineaJugador = scanner.nextLine();
            String[] partesJugador = lineaJugador.split(";");
            Jugador j = new Jugador(partesJugador[0], Integer.parseInt(partesJugador[1]));

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                if (!linea.isEmpty()) {
                    String[] partes = linea.split(";");
                    String nombrePokemon = partes[0];
                    String estadoPokemon = partes[1];

                    Pokemon match = pokedex.buscarPokemon(nombrePokemon);

                    if (match != null) {
                        match.setEstado(estadoPokemon);
                        j.agregarPokemon(match);
                    }
                }
            }

            return j;
        } catch (IOException err) {
            System.out.println("Error al cargar el archivo: " + err.getMessage());
            return null;
        }
    }
}
