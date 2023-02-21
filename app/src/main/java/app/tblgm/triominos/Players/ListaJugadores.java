package app.tblgm.triominos.Players;

public class ListaJugadores {
    private final Jugador[] jugadores;

    public ListaJugadores(int numJug) {
        jugadores = new Jugador[numJug];
    }

    public void addJugador(Jugador jugador) {
        jugadores[jugador.getNumJug() - 1] = jugador;
    }

    public Jugador getJugador(int numJug) {
        return jugadores[numJug];
    }

    public int getNumJugs() {
        return jugadores.length;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }
}
