import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private int medallas;
    private ArrayList<Pokemon> pokemones;


    public Jugador(String nombre, int medallas) {
        this.nombre = nombre;
        this.medallas = medallas;
        this.pokemones = new ArrayList<>();
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getMedallas() {
        return this.medallas;
    }

    public void setMedallas(int medallas) {
        if (medallas < 0) medallas = 0;
        this.medallas = medallas;
    }

    public void sumarMedalla() {
        this.medallas++;
    }

    public ArrayList<Pokemon> getPokemones() {
        return this.pokemones;
    }

    public ArrayList<Pokemon> getEquipo() {
        ArrayList<Pokemon> equipo = new ArrayList<>();
        int limite = Math.min(6, pokemones.size());
        for (int i = 0; i < limite; i++) {
            equipo.add(pokemones.get(i));
        }
        return equipo;
    }

    public Pokemon getPrimerPokemonVivo() {
        int limite = Math.min(6, pokemones.size());
        for (int i = 0; i < limite; i++) {
            if (pokemones.get(i).isVivo()) {
                return pokemones.get(i);
            }
        }
        return null;
    }

    public boolean tienePokemon(String nombrePokemon) {
        for (int i = 0; i < pokemones.size(); i++) {
            if (pokemones.get(i).getNombre().equals(nombrePokemon)) {
                return true;
            }
        }
        return false;
    }

    public void agregarPokemon(Pokemon pokemon) {
        if (tienePokemon(pokemon.getNombre())) return;
        this.pokemones.add(pokemon);
    }

    public void intercambiarPokemones(int idxA, int idxB) {
        if (idxA < 0 || idxA >= pokemones.size()) return;
        if (idxB < 0 || idxB >= pokemones.size()) return;
        if (idxA == idxB) return;

        Pokemon tmp = pokemones.get(idxA);
        pokemones.set(idxA, pokemones.get(idxB));
        pokemones.set(idxB, tmp);
    }

    public void curarPokemones() {
        for (int i = 0; i < pokemones.size(); i++) {
            pokemones.get(i).setEstado("Vivo");
        }
    }

    public void updateData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/files/Registros.txt"))) {
            bw.write(nombre + ";" + medallas);
            bw.newLine();
            if (!pokemones.isEmpty()) {
                for (int i = 0; i < pokemones.size(); i++) {
                    Pokemon actual = pokemones.get(i);
                    bw.write(actual.getNombre() + ";" + actual.getEstado());
                    bw.newLine();
                }
            }
        } catch (IOException err) {
            System.out.println("Error al cargar el archivo: " + err.getMessage());
        }
    }
}
