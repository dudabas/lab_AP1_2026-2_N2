
import java.util.ArrayList;
import java.util.List;

/**
 * Assinante que agrega episódios.
 * Complete os métodos marcados com //TODO (Tarefas 2 e 3).
 */
public class Assinante {
    public static final double TARIFA_BASE = 29.90;
    public static final int MINUTOS_ISENCAO = 600;

    private String nome;
    private List<Episodio> episodios;

    public Assinante(String nome) {
        this.nome = nome == null ? "" : nome;
        this.episodios = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public boolean adicionar(Episodio e) {
        if (e == null) {
            return false;
        }
        episodios.add(e);
        return true;
    }

    public int quantidadeEpisodios() {
        return episodios.size();
    }

    /**
     * Marca como assistido o primeiro episódio com o título que ainda não foi assistido.
     */
    public boolean registrarAssistido(String titulo) {
        if (titulo == null) {
            return false;
        }
        for (Episodio e : episodios) {
            if (titulo.equals(e.getTitulo()) && !e.estaAssistido()) {
                e.marcarAssistido();
                return true;
            }
        }
        return false;
    }

    public int tempoTotalAssistido() {
        int soma = 0;
        for (Episodio e : episodios) {
            if (e.estaAssistido()) {
                soma += e.getMinutos();
            }
        }
        return soma;
    }

    public int creditoDeTempo() {
        int soma = 0;
        for (Episodio e : episodios) {
            if (!e.estaAssistido()) {
                soma += e.getMinutos();
            }
        }
        return soma;
    }

    /**
     * Pela proporção de episódios assistidos sobre o total:
     * até 10% INICIANTE; até 50% REGULAR; até 75% ENGAJADO; acima BINGE.
     * Lista vazia → INICIANTE.
     */
    public Engajamento classificacaoEngajamento() {
        //TODO Tarefa 2
        int tempoTotal = creditoDeTempo() + tempoTotalAssistido();
        double proporcao = tempoTotalAssistido()/tempoTotal;

        if (proporcao > 0.75){
            return Engajamento.BINGE;
        }else if (proporcao > 0.5){
            return Engajamento.ENGAJADO;
        }else if (proporcao > 0.1){
            return Engajamento.REGULAR;
        }else {
            return Engajamento.INICIANTE;
        }

    }

    /**
     * 0 se tempo assistido &gt; 600 minutos;
     * caso contrário, TARIFA_BASE multiplicada pelo fator da classificação.
     */
    public double tarifaMensal(Engajamento x) {
        //TODO Tarefa 3
        double tarifa;

        tarifa = TARIFA_BASE * x.getFator();

        return tarifa;
    }

    public String resumo() {
        return nome
                + " | eps=" + quantidadeEpisodios()
                + " | assistido=" + tempoTotalAssistido() + "min"
                + " | credito=" + creditoDeTempo() + "min"
                + " | " + classificacaoEngajamento();
    }
}
