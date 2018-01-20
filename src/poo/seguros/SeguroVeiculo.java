package poo.seguros;

import java.io.Serializable;

/**
 *
 * @author fabio
 */
import java.util.Map;


public class SeguroVeiculo extends Seguro implements Serializable
{
    private String matricula;
    private String modelo;
    private String marca;
    private int idade;

    /**
     * Constructor por omissao para SeguroVeiculos
     */
    public SeguroVeiculo()
    {
        matricula = "";
        modelo = "";
        marca = "";
        idade = 0;
        clausulasGerais();
    }

    /**
     * Constructor para SeguroVeiculos recebe um seguro.
     */

    public SeguroVeiculo (SeguroVeiculo alvo)
    {
        super(alvo);
        matricula = alvo.matricula;
        modelo = alvo.modelo;
        marca = alvo.marca;
        idade = alvo.idade;
        clausulasGerais();
    }

    /**
     * Constructor para SeguroVeiculos, recebe todos os argumentos
     */
    public SeguroVeiculo(   int titular,
                            float premio_base_anual,
                            float agravamento,
                            String segurado,
                            TipoPagamento tipo,
                            String matricula,
                            String modelo,
                            String marca,
                            int idade
        ){
        super(titular, premio_base_anual, agravamento, segurado, tipo);
        this.matricula = matricula;
        this.modelo = modelo;
        this.marca = marca;
        this.idade = idade;
        clausulasGerais();
    }


        public SeguroVeiculo(int codigo,
                            int titular,
                            float premio_base_anual,
                            float agravamento,
                            String segurado,
                            TipoPagamento tipo,
                            String matricula,
                            String modelo,
                            String marca,
                            int idade
        ){
        super(codigo, titular, premio_base_anual, agravamento, segurado, tipo);
        this.matricula = matricula;
        this.modelo = modelo;
        this.marca = marca;
        this.idade = idade;
        clausulasGerais();
    }

    /**
     * Metodo que retorna a Matricula do veiculo
     */
    public String getMatricula(){
       return matricula;
    }

    /**
     * Metodo que retorna o Modelo do veiculo
     */
    public String getModelo(){
       return modelo;
    }

     /**
     * Metodo que retorna a Marca do veiculo
     */
    public String getMarca(){
       return marca;
    }
     /**
     * Metodo que retorna a Idade do veiculo
     */
    public int  getIdade(){
       return idade;
    }

    /**
    * Metodo que imprime o seguro do  veiculo
    */
    public String toString(){
        return toString(false,false);
    }
    public String toString(boolean verbose, boolean multiline){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString(verbose,multiline));
        if(multiline){
            sb.append("\tMatricula: ");
            sb.append(matricula);
            sb.append("\n\tModelo: ");
            sb.append(modelo);
            sb.append("\n\tMarca: ");
            sb.append(marca);
            sb.append("\n\tIdade: ");
            sb.append(idade);
        }else{
            sb.append("\t");
            sb.append(matricula);
            sb.append(",");
            sb.append(modelo);
            sb.append(",");
            sb.append(marca);
            sb.append(",");
            sb.append(idade);
        }
        sb.append("\n");
        return sb.toString();
    }
    
    /**
    * Metodo que clona um seguro
    */
    public SeguroVeiculo clone(){
        return new SeguroVeiculo(this);
    }
    
    /**
     * Metodo que compara dois seguros
     */
    public boolean equals(Object one){
        if(one==this)
            return true;
        if(one==null || this.getClass()!=one.getClass())
            return false;

        SeguroVeiculo sv = (SeguroVeiculo) one;
        return( super.equals(sv) &&
                matricula == sv.getMatricula() &&
                idade == sv.getIdade() &&
                marca == sv.getMarca() &&
                modelo == sv.getModelo()
            );
    }
    /**
     * Adiciona as clausulas que possivelmente se encontram num seguro para veiculos
     */
    
    private void clausulasGerais(){
        adicionaClausula(3000, new Clausula("Seguro contra terceiros", 0.1f, true));
        adicionaClausula(3001, new Clausula("Seguro contra Vidros Partidos", 0.1f, false));
        adicionaClausula(3003, new Clausula("Seguro contra furto do veiculo", 0.1f, false));
        adicionaClausula(3004, new Clausula("Seguro contra furto do recheio", 0.1f, false));
        adicionaClausula(3005, new Clausula("Seguro para ocupantes", 0.1f, true));
        adicionaClausula(3006, new Clausula("Seguro para condutor", 0.1f, false));
        adicionaClausula(3007, new Clausula("Assistência em Viagem", 0.1f, true));
        adicionaClausula(3008, new Clausula("Protecção Jurídica", 0.1f, true));
        adicionaClausula(3009, new Clausula("Choque, Colisão e Capotamento", 0.1f, true));
    }
   
}

