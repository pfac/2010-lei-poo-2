package poo.seguros;

import java.io.Serializable;

/**
 *
 * @author fabio
 */
public class SeguroVeiculosMercadorias extends SeguroVeiculo implements Serializable
{

    private int numeroLugares;
    private double peso;
    private double capacidade;
    private float comprimento;
    private String tipo;


    /**
     *
     * Constructor da class SeguroVeiculosMercadorias por omissão
     *
     */
    public SeguroVeiculosMercadorias()
    {
        numeroLugares=0;
        peso=0.0;
        comprimento=0.0f;
        capacidade =0.0;
        tipo="";
        clausulasEspecificas();
    }

    /**
     * Constructor da class SeguroVeiculosMercadorias recebe um SeguroVeiculosMercadorias
     */

    public SeguroVeiculosMercadorias(SeguroVeiculosMercadorias alvo){
       super(   alvo.getTitular(),
                alvo.getPBA(),
                alvo.getAgravamento(),
                alvo.getSegurado(),
                alvo.getTipoDePagamento(),
                alvo.getMatricula(),
                alvo.getModelo(),
                alvo.getMarca(),
                alvo.getIdade()
            );
        numeroLugares=alvo.numeroLugares;
        peso=alvo.peso;
        comprimento=alvo.comprimento;
        capacidade = alvo.capacidade;
        tipo=alvo.getTipo();
        clausulasEspecificas();
    }

    /**
     *Constructor da class SeguroVeiculosMercadorias recebe todos os elementos de um SeguroVeiculosMercadorias
     */


    public SeguroVeiculosMercadorias(   int titular,
                                        float premio_base_anual,
                                        float agravamento,
                                        String segurado,
                                        TipoPagamento novo_tipo,
                                        String matricula,
                                        String modelo,
                                        String marca,
                                        int idade,
                                        int numeroLugares,
                                        double peso,
                                        double capacidade,
                                        float comprimento
                                    ){
        super(  titular,
                premio_base_anual,
                agravamento,
                segurado,
                novo_tipo,
                matricula,
                modelo,
                marca,
                idade
            );
        this.numeroLugares=numeroLugares;
        this.peso=peso;
        this.comprimento=comprimento;
        if (peso<3500.0)
            this.tipo ="Ligeiro";
        else
            this.tipo ="Pesado";
        this.capacidade = capacidade;
        clausulasEspecificas();
    }


     public SeguroVeiculosMercadorias(  int codigo,
                                        int titular,
                                        float premio_base_anual,
                                        float agravamento,
                                        String segurado,
                                        TipoPagamento novo_tipo,
                                        String matricula,
                                        String modelo,
                                        String marca,
                                        int idade,
                                        int numeroLugares,
                                        double peso,
                                        double capacidade,
                                        float comprimento
                                    ){
        super(  codigo,
                titular,
                premio_base_anual,
                agravamento,
                segurado,
                novo_tipo,
                matricula,
                modelo,
                marca,
                idade
            );
        this.numeroLugares=numeroLugares;
        this.peso=peso;
        this.comprimento=comprimento;
        if (peso<3500.0)
            this.tipo ="Ligeiro";
        else
            this.tipo ="Pesado";
        this.capacidade = capacidade;
        clausulasEspecificas();
    }

    /**
     * Metodo que retorna o numero de lugares do veiculo
     */
    public int getNumeroLugares()
    {
        return numeroLugares;
    }
     /**
     * Metodo que retorna o peso total do veiculo
     */
    public double getPeso()
    {
        return peso;
    }
     /**
     * Metodo que retorna o comprimento do veiculo
     */
    public float getComprimento()
    {
        return comprimento;
    }

     /**
     * Metodo que imprime o seguro do veiculo
     */
     public String toString(){
         return toString(false,false);
     }
    public String toString(boolean verbose, boolean multiline){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString(verbose, multiline));
        if(multiline){
            sb.append("\tLugares: ");
            sb.append(numeroLugares);
            sb.append("\n\tPeso: ");
            sb.append(peso);
            sb.append("\n\tComprimento: ");
            sb.append(comprimento);
            sb.append("\n\tTipo: ");
            sb.append(tipo);
            sb.append("\n\tCapacidade: ");
            sb.append(capacidade);
        }else{
            sb.append("\t");
            sb.append(numeroLugares);
            sb.append(",");
            sb.append(peso);
            sb.append(",");
            sb.append(comprimento);
            sb.append(",");
            sb.append(tipo);
            sb.append(",");
            sb.append(capacidade);
        }
        sb.append("\n");
        return sb.toString();
    }
     /**
     * Metodo que clona o seguro do veiculo
     */
    public SeguroVeiculosMercadorias clone()
    {
        return new SeguroVeiculosMercadorias(this);
    }

     /**
     * Metodo que compara o seguro do veiculo
     */
    public boolean equals(Object one)
    {
        if(one==this)
            return true;
        if(one==null || this.getClass()!=one.getClass())
            return false;

        SeguroVeiculosMercadorias svm = (SeguroVeiculosMercadorias) one;
        return (super.equals(one)&&this.getPeso()==svm.getPeso()&& this.getCapacidade()==svm.getCapacidade()&& this.getComprimento()==svm.getComprimento() && this.getNumeroLugares() == svm.getNumeroLugares());

    }

     /**
     * Metodo que determina a categoria do veiculo (pesado ou ligeiro)
     */
    public String getTipo()
    {
        if (peso<3500.0)
            tipo ="Ligeiro";
        else
            tipo ="Pesado";
        return tipo;
    }

     /**
     * Metodo que retorna a capacidade do veiculo
     */

    public double getCapacidade ()
    {
       return capacidade;
    }
    /**
     * Adiciona Clausulas especificas para Seguros de veiculos de Mercadorias
     */
    private void clausulasEspecificas(){
        Clausula onze= new Clausula("Seguro contra danos na carga", 0.1f, false);
        adicionaClausula(3011, onze);
        Clausula doze= new Clausula("Seguro para 50% do valor da carga", 0.1f, true);
        adicionaClausula(3012, doze);
    }
}


