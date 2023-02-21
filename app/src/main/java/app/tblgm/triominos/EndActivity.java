package app.tblgm.triominos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import app.tblgm.triominos.Players.Jugador;
import app.tblgm.triominos.Players.ListaJugadores;

public class EndActivity extends AppCompatActivity {
    ListaJugadores listaJugadores = MainActivity.listaJugadores;
    ArrayList<String> nombJugs = new ArrayList<>();
    Spinner spnJug;
    TextView txtFicJug;
    Button btnCon;
    Button btnSum;
    Button btnBor;
    Button btnSig;
    Spinner spnP1;
    Spinner spnP2;
    Spinner spnP3;
    TextView txtUltFic;
    int ultFic;
    public int numJugs = listaJugadores.getNumJugs();
    TableLayout tblLay;
    TableRow tblRow;
    TextView txtJug_;
    TextView txtPun;
    int numJug = 0;
    RadioButton rdbtnJug;
    RadioButton rdbtnFic;
    TextView txtTFic;
    int total = 0;
    Button btnGan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_page);

        findViews();

        spnP1.setEnabled(false);
        spnP2.setEnabled(false);
        spnP3.setEnabled(false);

        creaTab();

        for (Jugador jugador : listaJugadores.getJugadores()) {
            nombJugs.add(jugador.getNombre());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombJugs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnJug.setAdapter(adapter);

        txtFicJug.setText("Introduce las fichas de " + listaJugadores.getJugador(0).getNombre() + ":");

        rdbtnFic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spnJug.setEnabled(false);
            }
        });

        rdbtnJug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spnJug.setEnabled(true);
            }
        });

        btnCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCon.setEnabled(false);
                rdbtnFic.setEnabled(false);
                rdbtnJug.setEnabled(false);
                spnJug.setEnabled(false);

                if (rdbtnFic.isChecked()) {
                    actElem();
                    btnSum.setText(R.string.restar);
                    btnBor.setText(R.string.devolver);
                }
                else if (rdbtnJug.isChecked()) {
                    actElem();
                }
            }
        });

        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int p1 = spnP1.getSelectedItemPosition();
                int p2 = spnP2.getSelectedItemPosition();
                int p3 = spnP3.getSelectedItemPosition();

                ultFic = p1 + p2 + p3;

                txtUltFic.setText(" " + p1 + " + " + p2 + " + " + p3 + " = " + ultFic);

                total += ultFic;
                txtTFic.setText(" " + total);

                if (rdbtnFic.isChecked()){
                    listaJugadores.getJugador(numJug).restarPuntos(ultFic);
                    tblRow = (TableRow) tblLay.getChildAt(numJug + 1);
                    txtPun = (TextView) tblRow.getChildAt(1);
                    txtPun.setText(String.valueOf(listaJugadores.getJugador(numJug).getPuntos()));
                }

                if (!btnBor.isEnabled()) {
                    btnBor.setEnabled(true);
                }
            }
        });

        btnBor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total -= ultFic;
                txtTFic.setText(" " + total);


                if (rdbtnFic.isChecked()){
                    txtUltFic.setText(txtUltFic.getText() + " (devuelta)");
                    listaJugadores.getJugador(numJug).setPuntos(ultFic);
                    tblRow = (TableRow) tblLay.getChildAt(numJug + 1);
                    txtPun = (TextView) tblRow.getChildAt(1);
                    txtPun.setText(String.valueOf(listaJugadores.getJugador(numJug).getPuntos()));
                }
                else {
                    txtUltFic.setText(txtUltFic.getText() + " (borrada)");
                }

                btnBor.setEnabled(false);
            }
        });

        btnSig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numJug++;
                txtFicJug.setText("Introduce las fichas de " + listaJugadores.getJugador(numJug).getNombre() + ":");

                if (rdbtnFic.isChecked()) {
                    txtTFic.setText("");
                    total = 0;
                }

                if (numJug == numJugs - 1) {
                    btnSig.setEnabled(false);
                    btnGan.setEnabled(true);
                }
            }
        });

        btnGan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rdbtnJug.isChecked()) {
                    listaJugadores.getJugador(spnJug.getSelectedItemPosition()).setPuntos(total + 25);
                }

                Intent intent = new Intent(getApplicationContext(), GanadorActivity.class);
                startActivity(intent);
            }
        });
    }

    private void findViews() {
        spnJug = findViewById(R.id.spnJug);
        txtFicJug = findViewById(R.id.txtFicJug);
        btnCon = findViewById(R.id.btnCon);
        btnSum = findViewById(R.id.btnAFic);
        btnBor = findViewById(R.id.btnBUFic);
        btnSig = findViewById(R.id.btnSJug);
        spnP1 = findViewById(R.id.spnP4);
        spnP2 = findViewById(R.id.spnP5);
        spnP3 = findViewById(R.id.spnP6);
        txtUltFic = findViewById(R.id.txtNUFic);
        tblLay = findViewById(R.id.tblLay3);
        tblRow = findViewById(R.id.tblRow3);
        txtJug_ = findViewById(R.id.txtJug_3);
        txtPun = findViewById(R.id.txtPun3);
        rdbtnJug = findViewById(R.id.rdbtnJug);
        rdbtnFic = findViewById(R.id.rdbtnFic);
        txtTFic = findViewById(R.id.txtTFic);
        btnGan = findViewById(R.id.btnGan);
    }

    private void actElem() {
        btnSum.setEnabled(true);
        btnBor.setEnabled(true);
        btnSig.setEnabled(true);
        spnP1.setEnabled(true);
        spnP2.setEnabled(true);
        spnP3.setEnabled(true);
    }

    private void creaTab() {
        numJugs = listaJugadores.getNumJugs();

        for (int i = 0; i < numJugs; i++) {
            tblRow = new TableRow(this);
            txtJug_ = new TextView(this);
            txtPun = new TextView(this);

            txtJug_.setText(listaJugadores.getJugador(i).getNombre() + ": ");
            txtPun.setText(String.valueOf(listaJugadores.getJugador(i).getPuntos()));

            tblRow.addView(txtJug_);
            tblRow.addView(txtPun);

            tblLay.addView(tblRow);
        }
    }
}
