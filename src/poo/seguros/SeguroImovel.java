package poo.seguros;
/**
 *
 * @author fabio
 */
import java.util.Map;
import java.io.Serializable;

public class SeguroImovel extends Seguro implements Serializable
{
    private Residencia tipoResidencia;
    private String morada;
    private int anoConstrucao;


    /**
     * Constructor de omissão da Class de SeguroImovel
     */
    public SeguroImovel(){
        tipoResidencia = Residencia.Casa;
        morada = "";
        anoConstrucao = 0;
         condicoes();
    }

     /**
     * Construtor param�trico para SeguroImovel.
     *
     * @param   titular   instância de Cliente com a informação relativa ao titular do seguro.
     * @param   premio_base_anual    prémio base a pagar anualmente.
     * @param   agravamento aumento do PMA em caso de ocorrência registada.
     * @param   segurado    descrição do objecto do seguro.
     * @param   novo_tipo o tipo de residencia (apartamento, casa, terreno).
     * @param   nova_morada a morada da residencia
     * @param   novo_ano a data de construçaao
     */

    public SeguroImovel(    Integer titular,
                            float premio_base_anual,
                            float agravamento,
                            String segurado,
                            TipoPagamento tipo,
                            Residencia tipoResidencia,
                            String morada,
                            int anoConstrucao
                        ){
        super(titular, premio_base_anual, agravamento, segurado, tipo);
        this.tipoResidencia = tipoResidencia;
        this.morada = morada;
        this.anoConstrucao = anoConstrucao;
         condicoes();
    }

        public SeguroImovel(int novocodigo,
                            Integer titular,
                            float premio_base_anual,
                            float agravamento,
                            String segurado,
                            TipoPagamento tipo,
                            Residencia tipoResidencia,
                            String morada,
                            int anoConstrucao
                        ){
        super(novocodigo, titular, premio_base_anual, agravamento, segurado, tipo);
        this.tipoResidencia = tipoResidencia;
        this.morada = morada;
        this.anoConstrucao = anoConstrucao;
         condicoes();
    }


    /**
     * Construtor de copia para SeguroImovel.
     *
     * @param   modelo  instância de SeguroImovel a copiar.
     */

    public SeguroImovel(SeguroImovel alvo){
        super(  alvo.getTitular(),
                alvo.getPBA(),
                alvo.getAgravamento(),
                alvo.getSegurado(),
                alvo.getTipoDePagamento()
            );
        tipoResidencia = alvo.getTipoDeResidencia();
        morada = alvo.getMorada();
        anoConstrucao = alvo.getAnoConstrucao();
         condicoes();
    }

     //  M�TODOS DE INSTANCIA
    /**
    * getTipo - extrai o tipo de Residencia do SeguroImovel
    *
    * @return   o tipo de Resiencia no SeguroImovel
    */
    public Residencia getTipoDeResidencia(){
       return tipoResidencia;
    }

    /**
    * getMorada - extrai a Morada da residencia do SeguroImovel
    *
    * @return   a morada da residenica no SeguroImovel
    */
    public String getMorada(){
        return morada;
    }

    /**
    * getAnoConstrucao - extrai o ando de construçao da residencia do SeguroImovel
    *
    * @return   o ano de construçao da residenica no SeguroImovel
    */
    public int getAnoConstrucao(){
        return anoConstrucao;
    }

    /**
    * setTipo - insere um novo tipo de residencia ao Seguro Imovel
    *
    * @param  nova tipo de residencia  a inserir
    */
    public void setTipoDeResidencia(Residencia tipoResidencia){
          this.tipoResidencia = tipoResidencia;
    }

    /**
    * setMorada - insere uma nova morada da residencia ao Seguro Imovel
    *
    * @param  nova morada a inserir
    */
    public void setMorada(String morada){
          this.morada = morada;
    }
    /**
    * setAnoConstrucao - insere um novo ano de construçao de residencia ao Seguro Imovel
    *
    * @param  novo ano de construçao a inserir
    */
    public void setAnoConstrucao (int ano){
          anoConstrucao = ano;
    }


     /**
     * toString - converte um SeguroImovel numa String
     *
     * @return uma String com os campos do SeguroImovel e os valores actuais desses campos.
     */
     public String toString(){
         return toString(false, false);
     }

    public String toString(boolean verbose, boolean multiline)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString(verbose, multiline));
        if(multiline){
            sb.append("\n\tTipo de Residência: ");
            sb.append(tipoResidencia);
            sb.append("\n\tMorada: ");
            sb.append(morada);
            sb.append("\n\tAno de construção: ");
            sb.append(anoConstrucao);
        }else{
            sb.append("\n\t");
            sb.append(tipoResidencia);
            sb.append(",");
            sb.append(morada);
            sb.append(",");
            sb.append(anoConstrucao);
        }
        sb.append("\n");
        return sb.toString();
    }

     /**
     * Verifica os SegurosImovel ou objectos são iguais.
     * @param   obj Objecto a comparar com o receptor.
     *
     * @return  true se forem o mesmo objecto ou pertencerem a mesma
     * classe e todas as variaveis de instância possuirem valores
     * idênticos. false se obj é null ou se os dois objectos forem de
     * classes diferentes.
     */
    public boolean equals(Object obj){
        if(obj==this)
            return true;
        if(obj==null || (this.getClass() != obj.getClass()))
            return false;
        SeguroImovel alvo = (SeguroImovel) obj;
                return (super.equals(obj) &&
                alvo.tipoResidencia == this.tipoResidencia &&
                alvo.morada.equals(this.morada) &&
                alvo.anoConstrucao == this.anoConstrucao
                );
    }

     /**
     * Instrucao de clonagem do SeguroImovel
     *
     * @return  Uma nova instancia de SeguroImovel, cujas variaveis de instância
     * possuem os mesmos valores que a instância actual.
     */
    public SeguroImovel clone(){
        return new SeguroImovel(this);
    }

 private void condicoes(){
        adicionaClausula(5000, new Clausula("Tempestade.", 0.01f, false));
        adicionaClausula(5001, new Clausula("Deslizamento de terra", 0.05f, false));
        adicionaClausula(5002, new Clausula("Incêndio.", 0.01f, false));
        adicionaClausula(5003, new Clausula("Inundaçao.",0.01f, false));
        adicionaClausula(5004, new Clausula("Explosâo",0.01f, false));
        adicionaClausula(5005, new Clausula("Actividade Vulcânica", 0.5f, false));
    }
}

