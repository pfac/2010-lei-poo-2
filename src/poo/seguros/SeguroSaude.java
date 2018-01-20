package poo.seguros;

import java.io.Serializable;

/**
 *
 * @author fabio
 */
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public abstract class SeguroSaude extends Seguro implements Serializable{
    private int segurado;
    private EstadoSaude es;
    private Set<String> observacoes;

    //  CONSTRUTORES
    /**
     * Construtor por omissao para SeguroSaude.
     */
    public SeguroSaude(){
        super();
        segurado = 0;
        setSegurado("sem segurado");
        es = EstadoSaude.Saudavel;
        observacoes = new TreeSet<String>();
    }
    /**
     * Construtor parametrico para SeguroSaude.
     *
     * @param   titular   instancia de Cliente com a informaçao relativa ao titular do seguro.
     * @param   premio_base_anual    premio base a pagar anualmente.
     * @param   agravamento aumento do PMA em caso de ocorrencia registada.
     * @param   segurado    instancia de Cliente com a informaçao relativa ao segurado.
     * @param   es  Estado de saude do segurado.
     */
    public SeguroSaude( Integer titular,
                        float premio_base_anual,
                        float agravamento,
                        int codigoClienteSegurado,
                        TipoPagamento tipo,
                        EstadoSaude es){
        super(titular, premio_base_anual, agravamento, "cliente n.º "+codigoClienteSegurado, tipo);
        this.segurado = codigoClienteSegurado;
        this.es = es;
        observacoes = new TreeSet<String>();
    }

        public SeguroSaude(int codigo,
                        Integer titular,
                        float premio_base_anual,
                        float agravamento,
                        int codigoClienteSegurado,
                        TipoPagamento tipo,
                        EstadoSaude es){
        super(codigo,titular, premio_base_anual, agravamento, "cliente n.º "+codigoClienteSegurado, tipo);
        this.segurado = codigoClienteSegurado;
        this.es = es;
        observacoes = new TreeSet<String>();
    }
    /**
     * Construtor de copia para SeguroSaude.
     *
     * @param   modelo  instancia de SeguroSaude a copiar.
     */
    public SeguroSaude(SeguroSaude model){
        super(model);
        this.segurado = model.getCodigoClienteSegurado();
        es = model.es;
        observacoes = new TreeSet<String>();
        for(String s : model.observacoes){
            observacoes.add(s);
        }
    }

    //  MÉTODOS DE LEITURA
    /**
     * @return  texto com a informaçao do segurado.
     */
    public String getSegurado(){
        return ((Integer) segurado).toString();
    }
    /**
     * @return  instancia de Cliente com a informaçao relativa ao segurado.
     */
    public int getCodigoClienteSegurado(){
        return segurado;
    }
    /**
     * @return  valor representativo do estado de saude do segurado.
     */
    public EstadoSaude getEstadoSaude(){
        return es;
    }
    /**
     * @return  observaçoes adicionais relativas ao estado de saude do segurado.
     */
    public Set<String> getObservacoes(){
        Set<String> ts = new TreeSet<String>();
        for(String s : observacoes){
            ts.add(s);
        }
        return ts;
    }

    //  M�TODOS DE ESCRITA
    /**
     * @param   novo estado de saude do segurado.
     */
    public void setEstadoSaude(EstadoSaude es){
        this.es = es;
    }
    /**
     * @param   observaçao relativa ao estado de saude do segurado a adicionar.
     */
    public void adicionarObservacao(String obs){
        observacoes.add(obs);
    }

    //  REDEFINIÇES DE OBJECT
    /**
     * Clona o receptor da mensagem.
     *
     * @return  Uma nova instancia de SeguroSaude, cujas variaveis de instancia
     * possuem os mesmos valores que a instancia actual.
     */
    public abstract SeguroSaude clone();
    

    /**
     * Verifica o receptor da mensagem e outro objecto sao iguais.
     *
     * @param   obj Objecto a comparar com o receptor.
     *
     * @return  true se forem o mesmo objecto ou pertencerem a mesma
     * classe e todas as variaveis de instancia possuirem valores
     * identicos. false se obj a null ou se os dois objectos forem de
     * classes diferentes.
     */
    @Override
    public boolean equals(Object o){
        if(o==this)
            return true;
        if(o==null || (o.getClass()!=this.getClass()))
            return false;
        SeguroSaude ss = (SeguroSaude) o;
        return (super.equals(ss)
                && es==ss.getEstadoSaude()
                && equalObservacoes(ss.getObservacoes()));
    }

    /**
     * Converte os valores das variaveis de instancia do receptor da
     * mensagem para String.
     *
     * @return  A string com os valores das variaveis listados.
     */
     public String toString(){
         return toString(false,false);
     }
    public String toString(boolean verbose, boolean multiline){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString(verbose, multiline));
        if(multiline){
            sb.append("\tEstado de Saude: ");
            sb.append(es);
            if(verbose){
                sb.append("\ntObservaçoes:\n");
                for(String s : observacoes){
                    sb.append("\t");
                    sb.append(s);
                    sb.append("\n");
                }
            }
        }else{
            sb.append("\t");
            sb.append(es);
            if(verbose){
                for(String s : observacoes){
                    sb.append(",[Obs]\"");
                    sb.append(s);
                    sb.append("\"");
                }
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    //  MÉTODOS AUXILIARES
    /**
     * Dado um conjunto com observaçoeses relativas ao estado de saúde do segurado,
     * avalia se este cont�m as mesmas observaçoes que o receptor da mensagem.
     *
     * @param   stringSet   conjunto com as observaçoes a avaliar.
     *
     * @return  true caso stringSet contenha todas e apenas as observacoes
     * contidas no receptor da mensagem.
     */
    protected boolean equalObservacoes(Set<String> ss){
        if(observacoes.size() != ss.size())
            return false;
        for(String s : observacoes){
            if(!ss.contains(s))
                return false;
        }
        return true;
    }
}


