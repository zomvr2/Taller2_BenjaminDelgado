// Benjamín Delgado - 22.223.703-3 - ICCI

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Pokedex pokedex = new Pokedex();
        Jugador jugador = cargarPartida(pokedex);
        Menu menu = new Menu(jugador, pokedex);

        menu.mostrar();
    }

    public static Jugador cargarPartida(Pokedex pokedex) {
        try (Scanner scanner = new Scanner(new File("src/files/Registros.txt"))) {
            if (!scanner.hasNextLine()) return null;

            String lineaJugador = scanner.nextLine();
            String[] partesJugador = lineaJugador.split(";");
            int medallas = 0;
            try {
                medallas = Integer.parseInt(partesJugador[1]);
            } catch (NumberFormatException err) {
                medallas = 0;
            }

            Jugador j = new Jugador(partesJugador[0], medallas);

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                if (!linea.isEmpty()) {
                    String[] partes = linea.split(";");
                    String nombre = partes[0];
                    String estado = partes[1];

                    Pokemon match = pokedex.buscarPokemon(nombre);

                    if (match != null) {
                        Pokemon actual = new Pokemon(match, estado);
                        j.agregarPokemon(actual);
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
