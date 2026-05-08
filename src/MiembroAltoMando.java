import java.util.ArrayList;

public class MiembroAltoMando {
    private int numero;
    private String nombre;
    private ArrayList<Pokemon> pokemones;

    public MiembroAltoMando(int numero, String nombre, ArrayList<Pokemon> pokemones) {
        this.numero = numero;
        this.nombre = nombre;
        this.pokemones = pokemones;
    }

    public int getNumero() {
        return numero;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Pokemon> getPokemones() {
        return pokemones;
    }
}

