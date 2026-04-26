public class Pokemon {
    private String nombre;
    private String habitat;
    private double probAparicion;
    private int hpBase;
    private int ataqueBase;
    private int defensaBase;
    private int ataqueEspecialBase;
    private int defensaEspecialBase;
    private int velocidadBase;
    private String tipo;
    private String estado;

    public Pokemon(
            String nombre,
            String habitat,
            double probAparicion,
            int hpBase,
            int ataqueBase,
            int defensaBase,
            int ataqueEspecialBase,
            int defensaEspecialBase,
            int velocidadBase,
            String tipo
    ) {
        this.nombre = nombre;
        this.habitat = habitat;
        this.probAparicion = probAparicion;
        this.hpBase = hpBase;
        this.ataqueBase = ataqueBase;
        this.defensaBase = defensaBase;
        this.ataqueEspecialBase = ataqueEspecialBase;
        this.defensaEspecialBase = defensaEspecialBase;
        this.velocidadBase = velocidadBase;
        this.tipo = tipo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getEstado() {
        return this.estado;
    }
}