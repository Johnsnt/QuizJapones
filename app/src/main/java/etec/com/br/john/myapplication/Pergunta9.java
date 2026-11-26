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

public class Pergunta9 extends AppCompatActivity {

    //OBJETOS DAS TELA
    TextView txtNome;
    RadioGroup rgOpcoes;

    Button btConfirmar;

    //VARIÁVEIS AUXILIARES
    String nomeRecebido;
    int pontosRecebidos;
    //Lista de erros
    ArrayList<Integer> erroQuestoes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pergunta9);

        //referenciando botão
        btConfirmar = findViewById(R.id.btnConfirmar);

        //RECUPERAÇÃO DOS VALORES
        Intent telaAtual = getIntent(); // cria objeto parece receber os valores
        erroQuestoes = getIntent().getIntegerArrayListExtra("erroQuestao");
        //se a lista n existe ele cria dnv
        if (erroQuestoes == null) {
            erroQuestoes = new ArrayList<>();
        }

        Bundle valores = telaAtual.getExtras(); //Recupera os valores

        //ASSOCIANDO OBJETOS PARA ESCREVER NOS TEXTVIEWS
        txtNome = (TextView) findViewById(R.id.txtNome);

        //OBJETO RADIOGROUP ASSOCIADO AO GROUPBOX DO LAYOUT
        rgOpcoes = (RadioGroup) findViewById(R.id.rgpOpcoes);

        //PEGANDO OS VALORES PASSADOS
        nomeRecebido = valores.getString("nome");
        pontosRecebidos = valores.getInt("pontos");


        //MOSTRANDO OS VALORES NOS TEXT VIEWS
        txtNome.setText(String.valueOf(nomeRecebido)); //coloca o dado do nome no Txt nome

        //MOSTRANDO OS VALORES NOS TEXT VIEWS
        txtNome.setText(String.valueOf(nomeRecebido)); //coloca o dado do nome no Txt nome

        btConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmarP9(v);
            }
        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void ConfirmarP9(View v){
        int op = rgOpcoes.getCheckedRadioButtonId();
        if (op == -1){
            Toast.makeText(this, "Marque uma resposta antes de prosseguir", Toast.LENGTH_SHORT).show();
            //encerra
            return;
        }
        //Isso quer dizer que a variável pergunta3 sempre vai apontar para essa mesma Intent. Se mais abaixo você tentasse fazer pergunta3 = new Intent(...) de novo, o Android Studio acusaria erro.

        final Intent pergunta10 = new Intent(Pergunta9.this, Pergunta10.class);

        if (op == R.id.rdb1p9){
            pontosRecebidos += 1;

        } else {
            pontosRecebidos += 0;

            erroQuestoes.add(9);
        }

        pergunta10.putExtra("nome", nomeRecebido);
        pergunta10.putExtra("pontos", pontosRecebidos);
        pergunta10.putIntegerArrayListExtra("erroQuestao", erroQuestoes);
        startActivity(pergunta10);

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Botão desabilitado durante as perguntas. Responda todas as perguntas ou feche o aplicativo", Toast.LENGTH_SHORT).show();
    };
}