import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Jugador {
    private String nombreCuenta;
    private int medallas;

    public Jugador(String nombreCuenta, int medallas) {
        this.nombreCuenta = nombreCuenta;
        this.medallas = medallas;
        updateData();
    }

    private void updateData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/Registros.txt"))) {
            bw.write(nombreCuenta + ";" + medallas);
            bw.newLine();
        } catch (IOException err) {
            System.out.println("Error al cargar el archivo: " + err.getMessage());
        }
    }
}
