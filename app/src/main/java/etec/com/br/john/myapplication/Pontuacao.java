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

public class Pontuacao extends AppCompatActivity {

    //OBJETOS DAS TELA
    TextView txtPontos;
    TextView txtQuestoesErradas;

    Button btVoltarMenu;

    //VARIÁVEIS AUXILIARES
    String nomeRecebido;
    int pontosRecebidos;
    //Lista de erros
    ArrayList<Integer> erroQuestoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pontuacao);
        //referencias

        txtPontos = findViewById(R.id.txtPontos);
        txtQuestoesErradas = findViewById(R.id.txtQuestoesErradas);
        btVoltarMenu = findViewById(R.id.btnVoltarMenu);


        //RECUPERAÇÃO DOS VALORES
        Intent telaAtual = getIntent(); // cria objeto parece receber os valores
        erroQuestoes = getIntent().getIntegerArrayListExtra("erroQuestao");
        //se a lista n existe ele cria dnv
        if (erroQuestoes == null) {
            erroQuestoes = new ArrayList<>();
        }

        Bundle valores = telaAtual.getExtras(); //Recupera os valores

        //PEGANDO OS VALORES PASSADOS
        nomeRecebido = valores.getString("nome");
        pontosRecebidos = valores.getInt("pontos");

        String pontosFormatados = String.valueOf(pontosRecebidos);

        txtPontos.setText("Parabéns " + nomeRecebido + " você acertou " + pontosFormatados + "|10 pergunta(s)");


        //Recebendo e exibindo os valores na tela
        String questoesErradas = "";

        //estrutura do foreach
        for (int numeroQuestao : erroQuestoes){
            questoesErradas += numeroQuestao + " ";
        }

        //exibindo questões na tela
        txtQuestoesErradas.setText("Você errou as questões: " + questoesErradas);


        //FUNÇÃO BOTÃO

        btVoltarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity(); //fecha tudo
                startActivity(new Intent(Pontuacao.this, MainActivity.class));

            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Botão desabilitado. Para voltar clique no botão acima para reiniciar o teste", Toast.LENGTH_SHORT).show();
    };
}