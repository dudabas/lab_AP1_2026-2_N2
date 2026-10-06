
/**
 * Classificação de engajamento do assinante.
 * Cada classificação tem um fator aplicado sobre a tarifa base:
 * INICIANTE 1,00; REGULAR 1,00; ENGAJADO 0,95; BINGE 1,10.
 */
public enum Engajamento {
    INICIANTE, REGULAR, ENGAJADO, BINGE;

    static final double TARIFA_INICIANTE = 1.00;
    static final double TARIFA_REGULAR = 1.00;
    static final double TARIFA_ENGAJADO = 0.95;
    static final double TARIFA_BINGE = 1.10;

    double fator;

    private Engajamento(Assinante a){
        int tempoTotal = a.creditoDeTempo() + a.tempoTotalAssistido();
        double proporcao = a.tempoTotalAssistido()/tempoTotal;
        if (a.classificacaoEngajamento() == null || proporcao < 0.1){
            fator = TARIFA_INICIANTE;
        }else if (proporcao < 0.5){
            fator = TARIFA_REGULAR;
        }else if (proporcao < 0.75){
            fator = TARIFA_ENGAJADO;
        }else{
            fator = TARIFA_BINGE;
        }

    }

    public double getFator(){
        return fator;
    }


    //TODO Tarefa 1: associar a cada constante seu fator de tarifa
    // (atributo, construtor e método getFator())
}
