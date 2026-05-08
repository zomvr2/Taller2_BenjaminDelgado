import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Jugador jugador;
    private boolean working = true;

    private Scanner scanner = new Scanner(System.in);

    private Pokedex pokedex;
    private LigaPokemon liga;

    public Menu(Jugador jugador, Pokedex pokedex) {
        this.jugador = jugador;
        this.pokedex = pokedex;
        this.liga = new LigaPokemon(pokedex);
    }

    public void mostrar() {
        while (working) {
            System.out.println("1) Continuar.");
            System.out.println("2) Nueva Partida.");
            System.out.println("3) Salir.");
            int option = leerNumero("Ingrese Opcion: ", 1, 3);

            switch (option) {
                case 1:
                    if (jugador != null) {
                        continuar();
                    } else {
                        System.out.println("No hay partida creada.");
                    }
                    break;
                case 2:
                    String nombre = procesarInput("Ingrese Apodo: ");
                    jugador = new Jugador(nombre, 0);
                    jugador.updateData();
                    liga.resetearGimnasios();
                    continuar();
                    break;
                case 3:
                    working = false;
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        }
    }

    public void continuar() {
        jugador.setMedallas(liga.getMedallasSincronizadas());

        boolean showing = true;
        System.out.printf("%nBienvenido %s!!%n", jugador.getNombre());
        System.out.println();

        while (showing) {
            System.out.printf("%s, que deseas hacer?%n%n", jugador.getNombre());
            System.out.println("1) Revisar equipo.");
            System.out.println("2) Salir a capturar.");
            System.out.println("3) Acceso al PC (cambiar Pokémon del equipo).");
            System.out.println("4) Retar un gimnasio.");
            System.out.println("5) Desafío al Alto Mando.");
            System.out.println("6) Curar Pokémon.");
            System.out.println("7) Guardar.");
            System.out.println("8) Guardar y Salir.");

            int opcion = leerNumero("Ingrese Opcion: ", 1, 8);

            switch (opcion) {
                case 1:
                    revisarEquipo();
                    break;

                case 2:
                    Explore explore = new Explore(jugador, pokedex, scanner);
                    explore.showMenu();
                    break;

                case 3:
                    accesoPC();
                    break;

                case 4:
                    retarGimnasio();
                    break;

                case 5:
                    desafioAltoMando();
                    break;

                case 6:
                    jugador.curarPokemones();
                    System.out.println("Tu equipo se ha recuperado!");
                    break;

                case 7:
                    jugador.updateData();
                    break;

                case 8:
                    jugador.updateData();
                    System.out.println("Nos vemos entrenador...");
                    working = false;
                    showing = false;
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }

    public String procesarInput(String out) {
        System.out.print(!out.isEmpty() ? out : "> ");
        return scanner.nextLine();
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

    private void revisarEquipo() {
        ArrayList<Pokemon> equipo = jugador.getEquipo();

        System.out.println("Equipo Actual:");
        if (equipo.isEmpty()) {
            System.out.println("(Sin pokemons)");
            return;
        }

        for (int i = 0; i < equipo.size(); i++) {
            Pokemon p = equipo.get(i);
            System.out.printf("%d) %s|%s|Stats totales: %d|%s%n", i + 1, p.getNombre(), p.getTipo(), p.getStatsTotales(), p.getEstado());
        }
    }

    private void accesoPC() {
        if (jugador.getPokemones().isEmpty()) {
            System.out.println("No tienes pokemons capturados.");
            return;
        }

        while (true) {
            System.out.println("Pokemons capturados:");
            for (int i = 0; i < jugador.getPokemones().size(); i++) {
                Pokemon p = jugador.getPokemones().get(i);
                String marcaEquipo = i < 6 ? "(Equipo)" : "(PC)";
                System.out.printf("%d) %s %s - %s%n", i + 1, p.getNombre(), marcaEquipo, p.getEstado());
            }

            System.out.println();
            System.out.println("1) Cambiar Pokemon.");
            System.out.println("2) Salir.");
            int opcion = leerNumero("Ingrese Opcion: ", 1, 2);

            if (opcion == 2) return;

            if (jugador.getPokemones().size() < 2) {
                System.out.println("No hay suficientes pokemons para intercambiar.");
                continue;
            }

            int a = leerNumero("Ingrese primer numero: ", 1, jugador.getPokemones().size()) - 1;
            int b = leerNumero("Ingrese segundo numero: ", 1, jugador.getPokemones().size()) - 1;

            jugador.intercambiarPokemones(a, b);
            System.out.println("Cambio realizado.");
        }
    }

    private void retarGimnasio() {
        ArrayList<Gimnasio> gimnasios = liga.getGimnasios();

        while (true) {
            System.out.println("A cual Lider deseas retar??");
            System.out.println();
            for (int i = 0; i < gimnasios.size(); i++) {
                Gimnasio g = gimnasios.get(i);
                System.out.printf("%d) %s - Estado: %s%n", i + 1, g.getLider(), g.getEstado());
            }
            System.out.printf("%d) Volver al menu.%n", gimnasios.size() + 1);

            int opcion = leerNumero("Ingrese Opcion: ", 1, gimnasios.size() + 1) - 1;
            if (opcion == gimnasios.size()) return;

            Gimnasio elegido = gimnasios.get(opcion);

            if (elegido.isDerrotado()) {
                System.out.println("Ese gimnasio ya fue derrotado.");
                continue;
            }

            if (elegido.getNumero() > jugador.getMedallas() + 1) {
                System.out.printf("Calmado Entrenador!!! No puedes retar a %s sin haber derrotado a los lideres anteriores!!%n", elegido.getLider());
                continue;
            }

            boolean gano = Combate.desafiar(scanner, jugador, elegido.getLider(), elegido.getPokemones());
            if (gano) {
                System.out.printf("Has derrotado a %s!%n", elegido.getLider());
                elegido.setEstado("Derrotado");
                liga.guardarGimnasios();
                jugador.setMedallas(liga.getMedallasSincronizadas());
            } else {
                System.out.println("Volviendo al menu...");
            }

            return;
        }
    }

    private void desafioAltoMando() {
        jugador.setMedallas(liga.getMedallasSincronizadas());
        if (jugador.getMedallas() < 8) {
            System.out.println("Para desafiar al Alto Mando, debes tener los 8 gimnasios derrotados.");
            return;
        }

        ArrayList<MiembroAltoMando> miembros = liga.getAltoMando();
        for (int i = 0; i < miembros.size(); i++) {
            MiembroAltoMando m = miembros.get(i);
            boolean gano = Combate.desafiar(scanner, jugador, m.getNombre(), m.getPokemones());
            if (!gano) {
                System.out.println("Volviendo al menu...");
                return;
            }
        }

        System.out.println("Felicitaciones! Has derrotado al Alto Mando y te has coronado como campeon!");
    }
}
