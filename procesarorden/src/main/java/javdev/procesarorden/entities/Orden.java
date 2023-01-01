package javdev.procesarorden.entities;

import java.util.Date;
import java.util.List;

public class Orden {
    private String numero = "001";
    private List<Item> lista;
    private Cliente cliente;
    private String fecha;
    private double total;

    public Orden() {
    }

    public Orden(String numero,List<Item> lista, Cliente cliente, String fecha, double total) {
        this.numero = numero;
        this.lista = lista;
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public List<Item> getLista() {
        return lista;
    }

    public void setLista(List<Item> lista) {
        this.lista = lista;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
