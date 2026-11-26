package etec.com.br.john.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //Iniciando variávels;
    EditText edNome;

    Button btInicio, btSobre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Referenciando variáveis
        edNome = findViewById(R.id.edtNome);
        btInicio = findViewById(R.id.btnInicio);
        btSobre = findViewById(R.id.btnSobre);



        btInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edNome.getText().toString().isEmpty()){
                    edNome.setError("Por favor, insira um nome");
                    edNome.requestFocus();
                } else {
                    iniciarQuiz(view);
                }


            }
        });

        btSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sobre(view);
            }
        });





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



    }


    //INICIAR QUIZ
    public void iniciarQuiz(View v){

        Intent pergunta1 = new Intent(MainActivity.this, Pergunta1.class);
        pergunta1.putExtra("nome", edNome.getText().toString());
        startActivity(pergunta1);
    }

    public void sobre(View v){
        Intent info = new Intent(MainActivity.this, Sobre.class);
        startActivity(info);

    }

}