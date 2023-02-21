package app.tblgm.triominos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import app.tblgm.triominos.Players.ListaJugadores;

public class GameActivity extends AppCompatActivity {

    ListaJugadores listaJugadores = MainActivity.listaJugadores;
    TableLayout tblLay;
    TableRow tblRow;
    TextView txtJug_;
    TextView txtPun;
    Button btnAct;
    Button btnFin;
    TextView txtNJug;
    RadioButton rdb0;
    RadioButton rdb1;
    RadioButton rdb2;
    RadioButton rdb3_no;
    RadioButton rdb3_si;
    Spinner spnP1;
    Spinner spnP2;
    Spinner spnP3;
    RadioButton rdbNad;
    RadioButton rdbPue;
    RadioButton rdbHex;
    RadioButton rdb2Hex;
    RadioButton rdb3Hex;
    RadioGroup rdbGrp;
    int numJug = 0;
    public int numJugs = listaJugadores.getNumJugs();
    public int pun;
    public int p_1;
    public int p_2;
    public int p_3;
    public int bon;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_page);

        findViews();

        txtNJug.setText("Turno de " + listaJugadores.getJugador(numJug).getNombre() + ":");

        creaTab();

        rdb3_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no_col();
            }
        });

        rdb3_si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!spnP1.isEnabled()) si_col();
            }
        });

        rdb0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!spnP1.isEnabled()) si_col();
            }
        });

        rdb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!spnP1.isEnabled()) si_col();
            }
        });

        rdb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!spnP1.isEnabled()) si_col();
            }
        });

        btnAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roba_o_no();

                punFic();

                bonus();

                actPun();

                nueJug();
            }
        });

        btnFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, EndActivity.class);
                startActivity(intent);
            }
        });
    }

    private void si_col() {
        spnP1.setSelection(p_1);
        spnP2.setSelection(p_2);
        spnP3.setSelection(p_3);

        spnP1.setEnabled(true);
        spnP2.setEnabled(true);
        spnP3.setEnabled(true);

        rdbGrp.check(bon);

        rdbPue.setEnabled(true);
        rdbHex.setEnabled(true);
        rdb2Hex.setEnabled(true);
        rdb3Hex.setEnabled(true);
    }

    private void no_col() {
        spnP1.setEnabled(false);
        spnP2.setEnabled(false);
        spnP3.setEnabled(false);

        p_1 = spnP1.getSelectedItemPosition();
        p_2 = spnP2.getSelectedItemPosition();
        p_3 = spnP3.getSelectedItemPosition();

        spnP1.setSelection(0);
        spnP2.setSelection(0);
        spnP3.setSelection(0);

        bon = rdbGrp.getCheckedRadioButtonId();

        rdbPue.setEnabled(false);
        rdbHex.setEnabled(false);
        rdb2Hex.setEnabled(false);
        rdb3Hex.setEnabled(false);

        rdbNad.setChecked(true);
    }

    private void creaTab() {
        Intent intent = getIntent();
        numJugs = intent.getIntExtra("numJug", 0);

        for (int i = 0; i < numJugs; i++) {
            tblRow = new TableRow(this);
            txtJug_ = new TextView(this);
            txtPun = new TextView(this);

            txtJug_.setText("Puntuación de " + listaJugadores.getJugador(i).getNombre() + ": ");
            txtPun.setText(String.valueOf(listaJugadores.getJugador(i).getPuntos()));

            tblRow.addView(txtJug_);
            tblRow.addView(txtPun);

            tblLay.addView(tblRow);
        }
    }

    private void findViews() {
        tblLay = findViewById(R.id.tblLay3);
        tblRow = findViewById(R.id.tblRow3);
        txtJug_ = findViewById(R.id.txtJug_3);
        txtPun = findViewById(R.id.txtPun3);
        btnAct = findViewById(R.id.btnAct);
        btnFin = findViewById(R.id.btnFin);
        txtNJug = findViewById(R.id.txtJug);
        rdb0 = findViewById(R.id.rdBtn0);
        rdb1 = findViewById(R.id.rdBtn1);
        rdb2 = findViewById(R.id.rdBtn2);
        rdb3_si = findViewById(R.id.rdBtn3_si);
        rdb3_no = findViewById(R.id.rdBtn3_no);
        spnP1 = findViewById(R.id.spnP4);
        spnP2 = findViewById(R.id.spnP5);
        spnP3 = findViewById(R.id.spnP6);
        rdbNad = findViewById(R.id.rdBtnNad);
        rdbPue = findViewById(R.id.rdBtnPue);
        rdbHex = findViewById(R.id.rdBtnHex);
        rdb2Hex = findViewById(R.id.rdBtnDobHex);
        rdb3Hex = findViewById(R.id.rdBtnTriHex);
        rdbGrp = findViewById(R.id.rdbGrpBon);
    }

    private void actPun() {
        listaJugadores.getJugador(numJug).setPuntos(pun);
        tblRow = (TableRow) tblLay.getChildAt(numJug + 1);
        txtPun = (TextView) tblRow.getChildAt(1);
        txtPun.setText(String.valueOf(listaJugadores.getJugador(numJug).getPuntos()));
    }

    private void bonus() {
        if(rdbPue.isChecked()) {
            pun += 40;
        }
        else if(rdbHex.isChecked()) {
            pun += 50;
        }
        else if(rdb2Hex.isChecked()) {
            pun += 60;
        }
        else if(rdb3Hex.isChecked()) {
            pun += 70;
        }
    }

    private void punFic() {
        int p1 = spnP1.getSelectedItemPosition();
        int p2 = spnP2.getSelectedItemPosition();
        int p3 = spnP3.getSelectedItemPosition();

        pun += p1 + p2 + p3;
    }

    private void roba_o_no() {
        if (rdb1.isChecked()) {
            pun = -5;
        }
        else if (rdb2.isChecked()) {
            pun = -10;
        }
        else if (rdb3_si.isChecked()) {
            pun = -15;
        }
        else if (rdb3_no.isChecked()) {
            pun = -25;
        }
    }

    private void nueJug() {
        if (numJug == numJugs - 1) {
            numJug = 0;
        }
        else {
            numJug++;
        }

        txtNJug.setText("Turno de " + listaJugadores.getJugador(numJug).getNombre() + ":");

        spnP1.setEnabled(true);
        spnP2.setEnabled(true);
        spnP3.setEnabled(true);
        spnP1.setSelection(0);
        spnP2.setSelection(0);
        spnP3.setSelection(0);

        rdbPue.setEnabled(true);
        rdbHex.setEnabled(true);
        rdb2Hex.setEnabled(true);
        rdb3Hex.setEnabled(true);

        rdb0.setChecked(true);
        rdbNad.setChecked(true);

        pun = 0;
    }
}
