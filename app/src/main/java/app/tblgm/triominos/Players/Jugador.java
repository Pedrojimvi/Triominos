package app.tblgm.triominos.Players;

public class Jugador {
    private final String nombre;
    private int puntos;
    private final int numJug;

    public Jugador(String nombre, int numJug) {
        this.nombre = nombre;
        this.numJug = numJug;
        this.puntos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getNumJug() {
        return numJug;
    }

    public void setPuntos(int pun) {
        this.puntos = puntos + pun;
    }

    public void restarPuntos(int pun) {
        this.puntos = puntos - pun;
    }
}
