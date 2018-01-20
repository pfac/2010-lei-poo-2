package poo.seguros;

import java.io.Serializable;

/**
 *
 * @author Andre
 */

public class SeguroVeiculosPublicos extends SeguroVeiculo implements Serializable{

    private int numeroLugares;
    private double peso;
    private float comprimento;
    private String tipo;

    public SeguroVeiculosPublicos (){
       super();
       numeroLugares=0;
       peso=0.0;
       comprimento=0.0f;
       tipo="";
       clausulasEspecificas();
    }

    public SeguroVeiculosPublicos (SeguroVeiculosPublicos alvo){
        super(alvo);
        numeroLugares = alvo.numeroLugares;
        peso = alvo.peso;
        comprimento = alvo.comprimento;
        tipo = alvo.getTipo();
        clausulasEspecificas();
    }
    public SeguroVeiculosPublicos(  int titular,
                                    float premio_base_anual,
                                    float agravamento,
                                    String segurado,
                                    TipoPagamento tipo,
                                    String matricula,
                                    String modelo,
                                    String marca,
                                    int idade,
                                    int numeroLugares,
                                    double peso,
                                    float comprimento
        ){
        super(  titular,
                premio_base_anual,
                agravamento,
                segurado,
                tipo,
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
        clausulasEspecificas();
    }
 /**
     *Constructor da class SeguroVeiculosPublicos recebe todos os elementos de um SeguroVeiculosPublicos
     */
    public SeguroVeiculosPublicos(  int codigo,
                                    int titular,
                                    float premio_base_anual,
                                    float agravamento,
                                    String segurado,
                                    TipoPagamento tipo,
                                    String matricula,
                                    String modelo,
                                    String marca,
                                    int idade,
                                    int numeroLugares,
                                    double peso,
                                    float comprimento
        ){
        super(  codigo,
                titular,
                premio_base_anual,
                agravamento,
                segurado,
                tipo,
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
        clausulasEspecificas();
    }

     public boolean equals(Object one)
    {
        if(one==this)
            return true;
        if(one==null || this.getClass()!=one.getClass())
            return false;

        SeguroVeiculosMercadorias svm = (SeguroVeiculosMercadorias) one;
        return (super.equals(one)&&this.getPeso()==svm.getPeso()&& this.getComprimento()==svm.getComprimento() && this.getNumeroLugares() == svm.getNumeroLugares());

    }

    public String getTipo()
    {
        if (peso<3500.0)
            tipo ="Ligeiro";
        else
            tipo ="Pesado";
        return tipo;
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
        }else{
            sb.append("\t");
            sb.append(numeroLugares);
            sb.append(",");
            sb.append(peso);
            sb.append(",");
            sb.append(comprimento);
            sb.append(",");
            sb.append(tipo);
        }
        sb.append("\n");
        return sb.toString();
    }

    private void clausulasEspecificas(){
        Clausula treze= new Clausula("Seguro para passageiros em transportes publicos", (0.01f*this.getNumeroLugares()), true);
        adicionaClausula(3013, treze);
        Clausula quator= new Clausula("Seguro contra vandalismo pelos passageiros", 0.1f, false);
        adicionaClausula(3014, quator);

        if (this.getTipo().equals("Pesado")){
            Clausula quinze= new Clausula("Seguro de veiculos pesados de passageiros", 0.2f, true);
            adicionaClausula(3015, quinze);
        }
    }
}
