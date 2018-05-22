package com.example.yanirayanes.navbar_sqlite.objeto;

public class persona {
    private String carnet;
    private String nombre;
    private int nota;

    public persona(){

    }

    public persona(String carnet, String nombre, int nota){
        this.carnet = carnet;
        this.nombre = nombre;
        this.nota = nota;
    }

    public String getCarnet() {
        return carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNota() {
        return nota;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}
