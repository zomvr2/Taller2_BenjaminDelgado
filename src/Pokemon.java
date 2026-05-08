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
        this.estado = "Vivo";
    }

    public Pokemon(Pokemon base, String estado) {
        this.nombre = base.nombre;
        this.habitat = base.habitat;
        this.probAparicion = base.probAparicion;
        this.hpBase = base.hpBase;
        this.ataqueBase = base.ataqueBase;
        this.defensaBase = base.defensaBase;
        this.ataqueEspecialBase = base.ataqueEspecialBase;
        this.defensaEspecialBase = base.defensaEspecialBase;
        this.velocidadBase = base.velocidadBase;
        this.tipo = base.tipo;
        this.estado = estado;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getTipo() {
        return this.tipo;
    }

    public double getProbAparicion() {
        return this.probAparicion;
    }

    public int getStatsTotales() {
        return hpBase + ataqueBase + defensaBase + ataqueEspecialBase + defensaEspecialBase + velocidadBase;
    }

    public String getEstado() {
        return this.estado;
    }

    public boolean isVivo() {
        return this.estado.equals("Vivo");
    }

    public String getHabitat() {
        return this.habitat;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}