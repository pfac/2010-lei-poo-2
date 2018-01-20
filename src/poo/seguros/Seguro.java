package poo.seguros;

/**
 *
 * @author fabio
 */
import java.util.Map;
import java.util.TreeMap;
import java.util.GregorianCalendar;
import java.io.Serializable;

public abstract class Seguro implements Serializable{
    private static int ultimo_seguro = 0;

    private int codigo;
    private Integer titular;
    private float premioBaseAnual;
    private float agravamento;
    private GregorianCalendar dataInicio;
    private String segurado;
    private Map<Integer,Clausula> condicoes;
    private TipoPagamento tipoPagamento;
    private Estado estado;

    //  CONSTRUTORES
    /**
     * Construtor por omissão para Seguro.
     */
    public Seguro(){
        codigo = ++ultimo_seguro;
        titular = 0;
        premioBaseAnual = agravamento = 0;
        dataInicio = new GregorianCalendar();
        segurado = " ";
        condicoes = new TreeMap<Integer,Clausula>();
        clausulasGerais();
        tipoPagamento = TipoPagamento.Dinheiro;
        estado = Estado.Pago;
    }
    /**
     * Construtor paramétrico para Seguro.
     *
<
     * @param   titular   instância de Cliente com a informação relativa ao titular do seguro.
     * @param   premioBaseAnual    prémio base a pagar anualmente.
     * @param   agravamento aumento do PMA em caso de ocorr�ncia registada.
     * @param   segurado    descricao do objecto do seguro.
     */

    public Seguro(  Integer titular,
                    float premioBaseAnual,
                    float agravamento,
                    String segurado,
                    TipoPagamento tipoPagamento
        ){
        codigo = ++ultimo_seguro;
        this.titular = titular;
        this.premioBaseAnual = premioBaseAnual;
        this.agravamento = agravamento;
        this.dataInicio = new GregorianCalendar ();
        this.segurado = segurado;
        condicoes = new TreeMap<Integer,Clausula>();
        clausulasGerais();
        this.tipoPagamento = tipoPagamento;
        this.estado = Estado.Pago;
    }

     public Seguro( int codigo,
                    Integer titular,
                    float premioBaseAnual,
                    float agravamento,
                    String segurado,
                    TipoPagamento tipoPagamento
        ){

        this.codigo = codigo;
        if(ultimo_seguro < codigo)
            ultimo_seguro = codigo;
        this.titular = titular;
        this.premioBaseAnual = premioBaseAnual;
        this.agravamento = agravamento;
        this.dataInicio = new GregorianCalendar ();
        this.segurado = segurado;
        condicoes = new TreeMap<Integer,Clausula>();
        clausulasGerais();
        this.tipoPagamento = tipoPagamento;
        this.estado = Estado.Pago;
     }


    /**
     * Construtor de cópia para Seguro.
     *
     * @param   modelo ->instância de Seguro a copiar.
     */
    public Seguro(Seguro modelo){
        codigo = modelo.getCodigo();
        titular = modelo.getTitular();
        premioBaseAnual = modelo.getPBA();
        agravamento = modelo.getAgravamento();
        dataInicio = modelo.getDataInicio();
        segurado = modelo.getSegurado();
        tipoPagamento = modelo.getTipoDePagamento();
        estado = modelo.getEstado();
        condicoes = modelo.getCondicoes();
    }

    //  REDEFINICOES DE OBJECT
    /**
     * Verifica o receptor da mensagem e outro objecto são iguais.
     *
     * @param   obj Objecto a comparar com o receptor.
     *
     * @return  true se forem o mesmo objecto ou pertencerem à mesma
     * classe e todas as variáveis de instância possuirem valores
     * idênticos. false se obj é null ou se os dois objectos forem de
     * diferentes.
     */
    public boolean equals(Object obj){
        if(obj==this)
            return true;
        if(obj==null || (this.getClass() != obj.getClass()))
            return false;
        Seguro seg = (Seguro) obj;
        return (    codigo == seg.getCodigo()
                    && titular == seg.getTitular()
                    && premioBaseAnual == seg.getPBA()
                    && agravamento == seg.getAgravamento()
                    && segurado.equals(seg.getSegurado())
                    && tipoPagamento == seg.getTipoDePagamento()
                    && estado == seg.getEstado()
                    && dataInicio.equals(seg.getDataInicio())
                );
    }

    /**
     * Clona o receptor da mensagem.
     *
     * @return  Uma nova instância de Seguro, cujas variáveis de instância
     * possuem os mesmos valores que a instância actual.
     */
    public abstract Seguro clone();

    /**
     * Converte os valores das variáveis de instância do receptor da
     * mensagem para String.
     *
     * @return  A string com os valores das variáveis listados.
     */
    public String toString(){
        return toString(false, false);
    }

    public String toString(boolean verbose, boolean multiline){
        StringBuilder sb = new StringBuilder();
        sb.append("[SEGURO]");
        if(multiline){
            sb.append("\n\tCódigo: ");
            sb.append(codigo);
            sb.append("\n\tTitular: ");
            sb.append(titular);
            sb.append("\n\tPrémio: ");
            sb.append(premioBaseAnual);
            sb.append("\n\tAgravamento: ");
            sb.append(agravamento);
            sb.append("\n\tTipo de Pagamento: ");
            sb.append(tipoPagamento);
            sb.append("\n\tData de Início: ");
            sb.append(dataInicio.get(GregorianCalendar.DAY_OF_MONTH));
            sb.append("-");
            sb.append(dataInicio.get(GregorianCalendar.MONTH));
            sb.append("-");
            sb.append(dataInicio.get(GregorianCalendar.YEAR));
            sb.append("\n\tEstado: ");
            sb.append(estado);
            sb.append("\n\tSegurado: ");
            sb.append(segurado);
            if(verbose){
                for(Clausula c : condicoes.values()){
                    sb.append("\n\t");
                    sb.append(c.toString());
                }
            }
        }else{
            sb.append("\t");
            sb.append(codigo);
            sb.append(",");
            sb.append(titular);
            sb.append(",");
            sb.append(premioBaseAnual);
            sb.append(",");
            sb.append(agravamento);
            sb.append(",");
            sb.append(tipoPagamento);
            sb.append(",");
            sb.append(dataInicio.get(GregorianCalendar.DAY_OF_MONTH));
            sb.append("-");
            sb.append(dataInicio.get(GregorianCalendar.MONTH));
            sb.append("-");
            sb.append(dataInicio.get(GregorianCalendar.YEAR));
            sb.append(",");
            sb.append(estado);
            sb.append("\n\t");
            sb.append(segurado);
            if(verbose){
                for(Clausula c : condicoes.values()){
                    sb.append("\n\t");
                    sb.append(c.toString());
                }
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    //  MÉTODOS DE INSTÂNCIA - INTERACÇÃO
    /**
     * Regista uma ocorrência de activação do seguro, aumentando o PMA segundo
     * o agravamento por ocorrência registada.
     */
    public void registarOcorrencia(){
        premioBaseAnual *= (1+agravamento);
    }

    /**
     * Adiciona uma nova cláusula as condições do seguro.
     *
     * @param   cod_clausula    código de identificação da nova cláusula. Se
     * existir uma cláusula já com este código, então é utilizado o primeiro
     * maior código livre.
     * @param   nova_clausula   cláusula cuja cópia será inserida nas condições.
     *
     * @return  O código com que a nova cláusula foi inserida.
     */
    public int adicionaClausula(int codigoClausula, Clausula novaClausula){
        while(condicoes.containsKey(codigoClausula))
            codigoClausula++;
        condicoes.put(codigoClausula, novaClausula.clone());
        return codigoClausula;
    }

    /**
     * Calcula o prémio anual a pagar.
     *
     * @return  Valor do prémio a pagar.
     */
    public float calculaPremioAnual(){
        float premio = premioBaseAnual;
        
        for(Clausula c : condicoes.values()){
            premio += (premioBaseAnual * c.getAcrescimo());
        }
        return premio;
    }

    //  M�TODOS DE INSTÂNCIA - LEITURA
    /**
     * @return  O código do seguro.
     */
    public int getCodigo(){
        return codigo;
    }
    /**
     * @return  O titular do seguro.
     */
    public int getTitular(){
        return titular;
    }
    /**
     * @return  O prémio base anual do seguro.
     */
    public float getPBA(){
        return premioBaseAnual;
    }
    /**
     * @return  O agravamento por ocorrência registada do seguro.
     */
    public float getAgravamento(){
        return agravamento;
    }
    /**
     * @return  A descrição do objecto do seguro.
     */
    public String getSegurado(){
        return segurado;
    }

    public TipoPagamento getTipoDePagamento(){
        return tipoPagamento;
    }

    public Estado getEstado(){
        return estado;
    }

    public Map<Integer,Clausula> getCondicoes(){
        Map<Integer,Clausula> result = new TreeMap<Integer,Clausula>();
        for(Map.Entry<Integer,Clausula> me : condicoes.entrySet()){
            result.put(me.getKey(), me.getValue());
        }
        return result;
    }

    public GregorianCalendar getDataInicio(){
        return new GregorianCalendar(   dataInicio.get(GregorianCalendar.YEAR),
                                        dataInicio.get(GregorianCalendar.MONTH),
                                        dataInicio.get(GregorianCalendar.DAY_OF_MONTH)
                                    );
    }

    /**
     * @return  O TreeMap<Integer,Clausula> apenas com as cláusulas activadas.
     */
    public Map<Integer,Clausula> getClausulasActivadas(){
        TreeMap<Integer,Clausula> result = new TreeMap<Integer,Clausula>();
        for(Map.Entry<Integer,Clausula> me : condicoes.entrySet()){
            if(me.getValue().getActivado())
                result.put(me.getKey(), me.getValue().clone());
        }
        return result;
    }

    //  MÉTODOS DE INSTÂNCIA - ESCRITA
    public void setTitular(int titular){
        this.titular = titular;
    }
    /**
     * Modifica o titular.
     *
     * @param   Novo titular do seguro.
     */
    public void setTitular(Cliente titular){
        this.titular = titular.getCodigo();
    }
    /**
     * Modifica o prémio base anual.
     *
     * @param   Novo valor do PBA do seguro.
     */
    public void setPBA(float premioBaseAnual){
        this.premioBaseAnual = premioBaseAnual;
    }
    /**
     * Modifica o agravamento por ocorrência registada.
     *
     * @param   novo_agravamento    Novo valor do agravamento do seguro.
     */
    public void setAgravamento(float agravamento){
        this.agravamento = agravamento;
    }

    public void setEstado(Estado estado){
        this.estado = estado;
    }

    public void setTipodePagamento(TipoPagamento tipoPagamento){
        this.tipoPagamento = tipoPagamento;
    }


    /**
     * Modifica a descrição do segurado.
     *
     * @param   segurado Nova descrição.
     */
    protected void setSegurado(String segurado){
        this.segurado = segurado;
    }

    //  M�TODOS DE INSTÂNCIA AUXILIARES
    private void clausulasGerais(){
        condicoes.put(0,new Clausula("Incêndio", 0.01f, false));
        condicoes.put(1,new Clausula("Queda de Raio", 0.01f, false));
        condicoes.put(2,new Clausula("Explosão", 0.01f, false));
    }
}


