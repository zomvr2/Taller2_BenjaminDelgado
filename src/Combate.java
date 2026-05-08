import java.util.ArrayList;
import java.util.Scanner;

public class Combate {

    public static boolean desafiar(Scanner scanner, Jugador jugador, String nombreRival, ArrayList<Pokemon> pokemonesRival) {
        System.out.printf("Desafiando a %s!!%n", nombreRival);

        if (jugador.getPrimerPokemonVivo() == null) {
            System.out.println("Te has quedado sin pokemons en tu equipo!");
            return false;
        }

        Pokemon actualJugador = jugador.getPrimerPokemonVivo();

        for (int i = 0; i < pokemonesRival.size(); i++) {
            Pokemon rival = pokemonesRival.get(i);
            System.out.printf("%s saca a %s!%n", nombreRival, rival.getNombre());

            if (actualJugador == null || !actualJugador.isVivo()) {
                actualJugador = jugador.getPrimerPokemonVivo();
            }

            if (actualJugador == null) {
                System.out.println("Te has quedado sin pokemons en tu equipo!");
                return false;
            }

            System.out.printf("%s saca a %s!%n", jugador.getNombre(), actualJugador.getNombre());

            boolean rivalDerrotado = false;
            while (!rivalDerrotado) {
                if (jugador.getPrimerPokemonVivo() == null) {
                    System.out.println("Te has quedado sin pokemons en tu equipo!");
                    return false;
                }

                System.out.println("Que deseas hacer?");
                System.out.println("1) Atacar");
                System.out.println("2) Cambiar de pokemon");
                System.out.println("3) Rendirse");

                int opcion = leerNumero(scanner, "Ingrese Opcion: ", 1, 3);

                if (opcion == 1) {
                    double puntosJugador = actualJugador.getStatsTotales();
                    double puntosRival = rival.getStatsTotales();

                    System.out.printf("%s -> %d puntos%n", actualJugador.getNombre(), actualJugador.getStatsTotales());
                    System.out.printf("%s -> %d puntos%n", rival.getNombre(), rival.getStatsTotales());

                    double multJugador = TablaTipos.getMultiplicador(actualJugador.getTipo(), rival.getTipo());

                    if (multJugador > 1.0) {
                        System.out.printf("%s es efectivo contra %s!%n", actualJugador.getNombre(), rival.getNombre());
                    } else if (multJugador < 1.0) {
                        System.out.printf("%s no es efectivo contra %s!%n", actualJugador.getNombre(), rival.getNombre());
                    }

                    puntosJugador *= multJugador;

                    if (multJugador != 1.0) {
                        System.out.println("Nuevo puntaje:");
                        System.out.printf("%s -> %d puntos%n", actualJugador.getNombre(), (int) Math.round(puntosJugador));
                        System.out.printf("%s -> %d puntos%n", rival.getNombre(), (int) Math.round(puntosRival));
                    }

                    if (puntosJugador > puntosRival || puntosJugador == puntosRival) {
                        if (puntosJugador == puntosRival) {
                            System.out.println("Empate! Por ventaja del atacante, ganas el combate!");
                        }

                        System.out.printf("Ha ganado %s! %s ha sido derrotado...%n", actualJugador.getNombre(), rival.getNombre());
                        rivalDerrotado = true;
                    } else {
                        System.out.printf("Ha ganado %s! %s ha sido derrotado...%n", rival.getNombre(), actualJugador.getNombre());
                        actualJugador.setEstado("Debilitado");

                        if (jugador.getPrimerPokemonVivo() == null) {
                            System.out.println("Te has quedado sin pokemons en tu equipo!");
                            return false;
                        }

                        actualJugador = elegirPokemon(scanner, jugador);
                        System.out.printf("%s saca a %s!%n", jugador.getNombre(), actualJugador.getNombre());
                    }
                }

                if (opcion == 2) {
                    actualJugador = elegirPokemon(scanner, jugador);
                    System.out.printf("%s saca a %s!%n", jugador.getNombre(), actualJugador.getNombre());
                }

                if (opcion == 3) {
                    System.out.println("Te has rendido...");
                    return false;
                }
            }
        }

        return true;
    }

    private static Pokemon elegirPokemon(Scanner scanner, Jugador jugador) {
        while (true) {
            ArrayList<Pokemon> equipo = jugador.getEquipo();
            System.out.println("Elige tu pokemon:");
            for (int i = 0; i < equipo.size(); i++) {
                Pokemon p = equipo.get(i);
                System.out.printf("%d) %s (%s)%n", i + 1, p.getNombre(), p.getEstado());
            }

            int opcion = leerNumero(scanner, "Ingrese Opcion: ", 1, equipo.size()) - 1;
            Pokemon elegido = equipo.get(opcion);

            if (!elegido.isVivo()) {
                System.out.println("Ese pokemon esta debilitado.");
                continue;
            }

            return elegido;
        }
    }

    private static int leerNumero(Scanner scanner, String out, int min, int max) {
        while (true) {
            System.out.print(!out.isEmpty() ? out : "> ");
            String entrada = scanner.nextLine();

            try {
                int val = Integer.parseInt(entrada);
                if (val < min || val > max) {
                    System.out.printf("Opcion invalida. (" + min + "-" + max + ")%n");
                    continue;
                }
                return val;
            } catch (NumberFormatException err) {
                System.out.println("Entrada invalida, intenta de nuevo.");
            }
        }
    }
}


