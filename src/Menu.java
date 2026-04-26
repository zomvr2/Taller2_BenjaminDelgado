import java.util.Scanner;

public class Menu {
    private Jugador jugador;
    private boolean working = true;

    private Scanner scanner = new Scanner(System.in);

    public Menu(Jugador jugador) {
        this.jugador = jugador;
    }

    public void mostrar() {
        while (working) {
            System.out.println("1) Continuar.");
            System.out.println("2) Nueva Partida.");
            System.out.println("3) Salir.");
            int option = Integer.parseInt(procesarInput(""));

            switch (option) {
                case 1:
                    if (jugador != null) {
                        continuar();
                    } else {
                        System.out.println("No hay partida creada.");
                    }
                    break;
                case 2:
                    String nombre = procesarInput("Ingrese su apodo de jugador: ");
                    jugador = new Jugador(nombre, 0);
                    continuar();
                    break;
                case 3:
                    working = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }

    public void continuar() {
        boolean showing = true;
        while (showing) {
            System.out.println("1) Revisar equipo.");
            System.out.println("2) Salir a capturar.");
            System.out.println("3) Acceso al PC (cambiar Pokémon del equipo).");
            System.out.println("4) Retar un gimnasio.");
            System.out.println("5) Desafío al Alto Mando.");
            System.out.println("6) Curar Pokémon.");
            System.out.println("7) Guardar.");
            System.out.println("8) Guardar y Salir.");

            int option = Integer.parseInt(procesarInput(""));

            switch (option) {
                case 1:
                    System.out.println("Mostrando equipo...");
                    break;

                case 2:
                    System.out.println("Saliendo a capturar...");
                    break;

                case 3:
                    System.out.println("Accediendo al PC...");
                    break;

                case 4:
                    System.out.println("Retando gimnasio...");
                    break;

                case 5:
                    System.out.println("Desafío Alto Mando...");
                    break;

                case 6:
                    System.out.println("Curando Pokémon...");
                    break;

                case 7:
                    System.out.println("Guardando partida...");
                    break;

                case 8:
                    System.out.println("Guardando y saliendo...");
                    working = false;
                    showing = false;
                    break;

                case 9:
                    showing = false;
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    public String procesarInput(String out) {
        System.out.print(!out.isEmpty() ? out : "> ");
        String res = scanner.nextLine();

        return res;
    }
}
