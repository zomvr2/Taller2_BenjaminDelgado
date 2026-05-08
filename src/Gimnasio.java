import java.util.ArrayList;

public class Gimnasio {
    private int numero;
    private String lider;
    private String estado;
    private ArrayList<Pokemon> pokemones;

    public Gimnasio(int numero, String lider, String estado, ArrayList<Pokemon> pokemones) {
        this.numero = numero;
        this.lider = lider;
        this.estado = estado;
        this.pokemones = pokemones;
    }

    public int getNumero() {
        return numero;
    }

    public String getLider() {
        return lider;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isDerrotado() {
        return estado.equals("Derrotado");
    }

    public ArrayList<Pokemon> getPokemones() {
        return pokemones;
    }
}

