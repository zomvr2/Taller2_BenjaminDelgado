import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Jugador {
    private String nombreCuenta;
    private int medallas;
    ArrayList<Pokemon> pokemones;


    public Jugador(String nombreCuenta, int medallas) {
        this.nombreCuenta = nombreCuenta;
        this.medallas = medallas;
        updateData();
    }

    private void updateData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/files/Registros.txt"))) {
            bw.write(nombreCuenta + ";" + medallas);
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
