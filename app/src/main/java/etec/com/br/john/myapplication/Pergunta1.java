package etec.com.br.john.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Pergunta1 extends AppCompatActivity {

    //OBJETOS DAS TEXTVIEWS

    TextView txtNome;
    RadioGroup rgOpcoes;
    Button btConfirmar;
    //VARIAVEIS AUXILIARES
    String nomeRecebido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pergunta1);


        //RECUPERAÇÃO DOS VALORES da PAGINA DE INICIO
        Intent telaAtual = getIntent();
        Bundle valores = telaAtual.getExtras();

        //ASSOCIANDO OBJETOS PARA ESCREVER NOS TEXTVIEWS
        txtNome = (TextView) findViewById(R.id.txtNome);

        //OBJETO RADIOGROUP ASSOCIADO AO GRUPOBOX DO LAYOUT
        rgOpcoes = (RadioGroup) findViewById(R.id.rgpOpcoes);

        //PEGANDO OS VALORES PASSADOS NO PUT EXTRA
        nomeRecebido = valores.getString("nome");

        //MOSTRANDO OS VALORES NOS TEXTVIEWS
        txtNome.setText(String.valueOf(nomeRecebido));

        btConfirmar = findViewById(R.id.btnConfirmar);

        btConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmarP1(view);
            }
        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void confirmarP1(View v){

            //VARIAVEIS AUXILIARES
            int op = rgOpcoes.getCheckedRadioButtonId();


            //verifica se algum input ta marcado
            if (op == -1){
                Toast.makeText(this, "Marque uma resposta antes de prosseguir", Toast.LENGTH_SHORT).show();
                //encerra
                return;
            }

            int pontosAtuais = 0;
            ArrayList<Integer> erroQuestoes = new ArrayList<>();

        Intent pergunta2 = new Intent(Pergunta1.this, Pergunta2.class);

        //VERIFICANDO QUAL RADIO FOI SELECIONADA E EFETUANDO OS CÁLCULOS
        if (op == R.id.rdb2p1){
            pontosAtuais += 1;
        } else {
            pontosAtuais += 0;

            erroQuestoes.add(1);
        }

        pergunta2.putExtra("nome", nomeRecebido);
        pergunta2.putExtra("pontos", pontosAtuais);
        pergunta2.putIntegerArrayListExtra("erroQuestao", erroQuestoes);

        startActivity(pergunta2);

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Botão desabilitado durante as perguntas. Responda todas as perguntas ou feche o aplicativo", Toast.LENGTH_SHORT).show();
    };


}