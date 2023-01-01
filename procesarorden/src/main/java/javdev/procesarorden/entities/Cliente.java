package javdev.procesarorden.entities;

public class Cliente {
    private String codigo = "00C1";
    private String nombre;
    private String ruc;
    public Cliente(){}

    public Cliente(String codigo, String nombre, String ruc) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.ruc = ruc;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
}
