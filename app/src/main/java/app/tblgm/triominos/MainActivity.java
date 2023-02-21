package app.tblgm.triominos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import app.tblgm.triominos.Players.Jugador;
import app.tblgm.triominos.Players.ListaJugadores;

public class MainActivity extends AppCompatActivity {

    Button btn2Jug;
    Button btn3Jug;
    Button btn4Jug;

    static ListaJugadores listaJugadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        btn2Jug = findViewById(R.id.btn2J);
        btn3Jug = findViewById(R.id.btn3J);
        btn4Jug = findViewById(R.id.btn4J);

        btn2Jug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp(2);
            }
        });

        btn3Jug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp(3);
            }
        });

        btn4Jug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp(4);
            }
        });
    }

    private void showPopUp(int num) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Introduce el nombre de los " + num +  " jugadores");

        LinearLayout layout = new LinearLayout(this);
        layout.setGravity(Gravity.CENTER);
        layout.setOrientation(LinearLayout.VERTICAL);

        EditText input1 = null;
        EditText input2 = null;
        EditText input3 = null;
        EditText input4 = null;
        TextView txt1 = new TextView(this);
        TextView txt2 = new TextView(this);
        TextView txt3 = new TextView(this);
        TextView txt4 = new TextView(this);

        txt1.setText(R.string.jug_1);
        txt2.setText(R.string.jug_2);
        txt3.setText(R.string.jug_3);
        txt4.setText(R.string.jug_4);

        if (num == 2){
            input1 = new EditText(this);
            input2 = new EditText(this);
            builder.setView(input1);
            builder.setView(input2);

            layout.addView(txt1);
            layout.addView(input1);
            layout.addView(txt2);
            layout.addView(input2);
        }
        else if (num == 3){
            input1 = new EditText(this);
            input2 = new EditText(this);
            input3 = new EditText(this);
            builder.setView(input1);
            builder.setView(input2);
            builder.setView(input3);

            layout.addView(txt1);
            layout.addView(input1);
            layout.addView(txt2);
            layout.addView(input2);
            layout.addView(txt3);
            layout.addView(input3);
        }
        else if (num == 4){
            input1 = new EditText(this);
            input2 = new EditText(this);
            input3 = new EditText(this);
            input4 = new EditText(this);
            builder.setView(input1);
            builder.setView(input2);
            builder.setView(input3);
            builder.setView(input4);

            layout.addView(txt1);
            layout.addView(input1);
            layout.addView(txt2);
            layout.addView(input2);
            layout.addView(txt3);
            layout.addView(input3);
            layout.addView(txt4);
            layout.addView(input4);
        }

        builder.setView(layout);

        EditText finalInput1 = input1;
        EditText finalInput2 = input2;
        EditText finalInput3 = input3;
        EditText finalInput4 = input4;

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                listaJugadores = new ListaJugadores(num);
                boolean error = true;

                if (num == 2){
                    String nombre1 = finalInput1.getText().toString();
                    String nombre2 = finalInput2.getText().toString();

                    if (nombre1.isEmpty() || nombre2.isEmpty()){
                        Toast.makeText(MainActivity.this, R.string.error_vac, Toast.LENGTH_SHORT).show();
                    }
                    else if (nombre1.equals(nombre2)) {
                        Toast.makeText(MainActivity.this, R.string.error_igu, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Jugador jugador1 = new Jugador(nombre1, 1);
                        Jugador jugador2 = new Jugador(nombre2, 2);
                        listaJugadores.addJugador(jugador1);
                        listaJugadores.addJugador(jugador2);
                        error = false;
                    }
                }
                else if (num == 3){
                    String nombre1 = finalInput1.getText().toString();
                    String nombre2 = finalInput2.getText().toString();
                    String nombre3 = finalInput3.getText().toString();

                    if (nombre1.isEmpty() || nombre2.isEmpty() || nombre3.isEmpty()){
                        Toast.makeText(MainActivity.this, R.string.error_vac, Toast.LENGTH_SHORT).show();
                    }
                    else if (nombre1.equals(nombre2) || nombre1.equals(nombre3) || nombre2.equals(nombre3)) {
                        Toast.makeText(MainActivity.this, R.string.error_igu, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Jugador jugador1 = new Jugador(nombre1, 1);
                        Jugador jugador2 = new Jugador(nombre2, 2);
                        Jugador jugador3 = new Jugador(nombre3, 3);
                        listaJugadores.addJugador(jugador1);
                        listaJugadores.addJugador(jugador2);
                        listaJugadores.addJugador(jugador3);
                        error = false;
                    }
                }
                else if (num == 4){
                    String nombre1 = finalInput1.getText().toString();
                    String nombre2 = finalInput2.getText().toString();
                    String nombre3 = finalInput3.getText().toString();
                    String nombre4 = finalInput4.getText().toString();

                    if (nombre1.isEmpty() || nombre2.isEmpty() || nombre3.isEmpty() || nombre4.isEmpty()){
                        Toast.makeText(MainActivity.this, R.string.error_vac, Toast.LENGTH_SHORT).show();
                    }
                    else if (nombre1.equals(nombre2) || nombre1.equals(nombre3) || nombre1.equals(nombre4) || nombre2.equals(nombre3) || nombre2.equals(nombre4) || nombre3.equals(nombre4)) {
                        Toast.makeText(MainActivity.this, R.string.error_igu, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Jugador jugador1 = new Jugador(nombre1, 1);
                        Jugador jugador2 = new Jugador(nombre2, 2);
                        Jugador jugador3 = new Jugador(nombre3, 3);
                        Jugador jugador4 = new Jugador(nombre4, 4);
                        listaJugadores.addJugador(jugador1);
                        listaJugadores.addJugador(jugador2);
                        listaJugadores.addJugador(jugador3);
                        listaJugadores.addJugador(jugador4);
                        error = false;
                    }
                }

                if (!error){
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    intent.putExtra("numJug", num);
                    startActivity(intent);
                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}