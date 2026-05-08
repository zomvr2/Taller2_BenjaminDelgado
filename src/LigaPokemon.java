import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LigaPokemon {
    private ArrayList<Gimnasio> gimnasios;
    private ArrayList<MiembroAltoMando> altoMando;
    private Pokedex pokedex;

    public LigaPokemon(Pokedex pokedex) {
        this.pokedex = pokedex;
        this.gimnasios = new ArrayList<>();
        this.altoMando = new ArrayList<>();
        cargarGimnasios();
        cargarAltoMando();
    }

    public ArrayList<Gimnasio> getGimnasios() {
        return gimnasios;
    }

    public ArrayList<MiembroAltoMando> getAltoMando() {
        return altoMando;
    }

    public int getMedallasSincronizadas() {
        int medallas = 0;
        for (int i = 0; i < gimnasios.size(); i++) {
            if (gimnasios.get(i).isDerrotado()) {
                medallas++;
            } else {
                break;
            }
        }
        return medallas;
    }

    public void guardarGimnasios() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/files/Gimnasios.txt"))) {
            for (int i = 0; i < gimnasios.size(); i++) {
                Gimnasio g = gimnasios.get(i);
                bw.write(g.getNumero() + ";" + g.getLider() + ";" + g.getEstado() + ";" + g.getPokemones().size());
                for (int j = 0; j < g.getPokemones().size(); j++) {
                    bw.write(";" + g.getPokemones().get(j).getNombre());
                }
                bw.newLine();
            }
        } catch (IOException err) {
            System.out.println("Error al guardar gimnasios: " + err.getMessage());
        }
    }

    public void resetearGimnasios() {
        for (int i = 0; i < gimnasios.size(); i++) {
            gimnasios.get(i).setEstado("Sin derrotar");
        }
        guardarGimnasios();
    }

    private void cargarGimnasios() {
        try (Scanner scanner = new Scanner(new File("src/files/Gimnasios.txt"))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().trim();
                if (linea.isEmpty()) continue;

                String[] partes = linea.split(";");
                int numero = Integer.parseInt(partes[0]);
                String lider = partes[1];
                String estado = partes[2];
                int cantPokemons = Integer.parseInt(partes[3]);

                ArrayList<Pokemon> equipo = new ArrayList<>();
                for (int i = 0; i < cantPokemons; i++) {
                    String nombrePokemon = partes[4 + i];
                    Pokemon base = pokedex.buscarPokemon(nombrePokemon);
                    if (base != null) {
                        equipo.add(new Pokemon(base, "Vivo"));
                    }
                }

                gimnasios.add(new Gimnasio(numero, lider, estado, equipo));
            }
        } catch (IOException err) {
            System.out.println("Error al cargar el archivo: " + err.getMessage());
        }
    }

    private void cargarAltoMando() {
        try (Scanner scanner = new Scanner(new File("src/files/Alto Mando.txt"))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().trim();
                if (linea.isEmpty()) continue;

                String[] partes = linea.split(";");
                int numero = Integer.parseInt(partes[0]);
                String nombre = partes[1];

                ArrayList<Pokemon> equipo = new ArrayList<>();
                for (int i = 2; i < partes.length; i++) {
                    String nombrePokemon = partes[i];
                    Pokemon base = pokedex.buscarPokemon(nombrePokemon);
                    if (base != null) {
                        equipo.add(new Pokemon(base, "Vivo"));
                    }
                }

                altoMando.add(new MiembroAltoMando(numero, nombre, equipo));
            }
        } catch (IOException err) {
            System.out.println("Error al cargar el archivo: " + err.getMessage());
        }
    }
}



