package app.tblgm.triominos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import app.tblgm.triominos.Players.Jugador;
import app.tblgm.triominos.Players.ListaJugadores;

public class GanadorActivity extends AppCompatActivity {
    ListaJugadores listaJugadores = MainActivity.listaJugadores;
    int gan;
    TextView txtGan;
    Button btnRei;
    TableLayout tblLay3;
    TableRow tblRow3;
    TextView txtJug3;
    TextView txtPun3;
    public int numJugs = listaJugadores.getNumJugs();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ganador_page);

        txtGan = findViewById(R.id.txtGan);
        btnRei = findViewById(R.id.btnRei);
        tblLay3 = findViewById(R.id.tblLay3);
        tblRow3 = findViewById(R.id.tblRow3);
        txtJug3 = findViewById(R.id.txtJug_3);
        txtPun3 = findViewById(R.id.txtPun3);

        creaTab();

        for (Jugador jugador : listaJugadores.getJugadores()) {
            for (int i = 0; i < listaJugadores.getNumJugs(); i++){
                if (jugador.getPuntos() > listaJugadores.getJugadores()[i].getPuntos()) {
                    gan = jugador.getNumJug();
                }
            }
        }

        txtGan.setText(listaJugadores.getJugadores()[gan - 1].getNombre());

        btnRei.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void creaTab() {
        numJugs = listaJugadores.getNumJugs();

        for (int i = 0; i < numJugs; i++) {
            tblRow3 = new TableRow(this);
            txtJug3 = new TextView(this);
            txtPun3 = new TextView(this);

            txtJug3.setText(listaJugadores.getJugador(i).getNombre() + ": ");
            txtPun3.setText(String.valueOf(listaJugadores.getJugador(i).getPuntos()));

            if (i == 0){
                txtJug3.setTextSize(25);
                txtPun3.setTextSize(25);
            }

            tblRow3.addView(txtJug3);
            tblRow3.addView(txtPun3);

            tblLay3.addView(tblRow3);
        }
    }
}
