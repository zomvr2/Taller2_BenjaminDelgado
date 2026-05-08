import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Pokedex {
    ArrayList<Pokemon> pokemones;
    public Pokedex() {
        this.pokemones = new ArrayList<>();
        cargarPokedex();
    }

    public Pokemon buscarPokemon(String nombre) {
        Pokemon found = null;
        for (int i = 0; i < pokemones.size(); i++) {
            if (pokemones.get(i).getNombre().equals(nombre)) {
                found = pokemones.get(i);
                break;
            }
        }
        return found;
    }

    public ArrayList<Pokemon> getPokemonesByHabitat(String habitat) {
        ArrayList<Pokemon> found = new ArrayList<>();

        for (int i = 0; i < pokemones.size(); i++) {
            if (pokemones.get(i).getHabitat().equals(habitat)) {
                found.add(pokemones.get(i));
            }
        }

        return found;
    }

    private void cargarPokedex() {
        try (Scanner scanner = new Scanner(new File("src/files/Pokedex.txt"))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] partes = linea.split(";");

                String nombre = partes[0];
                String habitat = partes[1];
                double probAparicion = Double.parseDouble(partes[2]);
                int hpBase = Integer.parseInt(partes[3]);
                int ataqueBase = Integer.parseInt(partes[4]);
                int defensaBase = Integer.parseInt(partes[5]);
                int ataqueEspecialBase = Integer.parseInt(partes[6]);
                int defensaEspecialBase = Integer.parseInt(partes[7]);
                int velocidadBase = Integer.parseInt(partes[8]);
                String tipo = partes[9];

                Pokemon actual = new Pokemon(nombre, habitat, probAparicion, hpBase, ataqueBase, defensaBase, ataqueEspecialBase, defensaEspecialBase, velocidadBase, tipo);
                this.pokemones.add(actual);
            }
        } catch (IOException err) {
            System.out.println("Error al cargar el archivo: " + err.getMessage());
        }
    }
}
