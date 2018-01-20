package poo.seguros;

import java.io.Serializable;

/**
 *
 * @author fabio
 */
public class SeguroMedico extends SeguroSaude implements Serializable{
    private String profissao;

    //  CONSTRUTORES
    /**
     * Construtor por omissao para SeguroMedico.
     */
    public SeguroMedico(){
        super();
        profissao="";
        condicoes();
    }
    /**
     * Construtor parametrico para SeguroMedico.
     *

     * @param   titular   instância de Cliente com a informaçao relativa ao titular do seguro.
     * @param   premio_base_anual    premio base a pagar anualmente.
     * @param   agravamento aumento do PMA em caso de ocorrencia registada.
     * @param   segurado    instancia de Cliente com a informaçao relativa ao segurado.
     * @param   es  estado de saude do segurado.
     * @param   profissao   profissao do segurado.

     */
    public SeguroMedico(    Integer titular,
                            float premioBaseAnual,
                            float agravamento,
                            int segurado,
                            TipoPagamento tipo,
                            EstadoSaude es,
                            String profissao
                        ){
        super(titular, premioBaseAnual, agravamento, segurado, tipo, es);
        this.profissao = profissao;
        condicoes();
    }

        public SeguroMedico(int codigo,
                            Integer titular,
                            float premioBaseAnual,
                            float agravamento,
                            int segurado,
                            TipoPagamento tipo,
                            EstadoSaude es,
                            String profissao
                        ){
        super(codigo, titular, premioBaseAnual, agravamento, segurado, tipo, es);
        this.profissao = profissao;
        condicoes();
    }
    /**
     * Construtor de copia para SeguroMedico.
     *
     * @param   modelo  instancia de SeguroMedico a copiar.
     */
    public SeguroMedico(SeguroMedico modelo){
        super(modelo);
        profissao = modelo.getProfissao();
    }

    //  REDEFINIÇOES DE OBJECT
    /**
     * Clona o receptor da mensagem.
     *
     * @return  Uma nova instancia de SeguroMedico, cujas variaveis de instancia
     * possuem os mesmos valores que a instancia actual.
     */
    public SeguroMedico clone(){
        return new SeguroMedico(this);
    }

    /**
     * Verifica o receptor da mensagem e outro objecto sao iguais.
     *
     * @param   obj Objecto a comparar com o receptor.
     *
     * @return  true se forem o mesmo objecto ou pertencerem a mesma
     * classe e todas as variaveis de instancia possuirem valores
     * identicos. false se obj é null ou se os dois objectos forem de
     * classes diferentes.
     */
    public boolean equals(Object obj){
        if(obj==this)
            return true;
        if(obj==null || obj.getClass()!=this.getClass())
            return false;
        SeguroMedico sm = (SeguroMedico) obj;
        return (super.equals(obj) &&
                sm.getProfissao().equals(profissao)
                );
    }

    /**
     * Converte os valores das variaveis de instancia do receptor da
     * mensagem para String.
     *
     * @return  A string com os valores das variaveis listados.
     */
    public String toString(boolean verbose, boolean multiline){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString(verbose,multiline));
        if(multiline){
            sb.append("\tProfissao: ");
            sb.append(profissao);
        }else{
            sb.append("\t");
            sb.append(profissao);
        }
        sb.append("\n");
        return sb.toString();
    }

    //  MÉTODOS DE INSTÂNCIA - LEITURA
    /**
     * @return  a profissao do segurado.
     */
    public String getProfissao(){
        return profissao;
    }

    //  MÉTODOS DE INSTÂNCIA - ESCRITA
    /**
     * @param   profissao   nova profissao do segurado.
     */
    public void setProfissao(String profissao){
        this.profissao = profissao;
    }

    //  MÉTODOS AUXILIARES
    private void condicoes(){
        adicionaClausula(2250, new Clausula("Hospitalizaçao voluntaria.", 0.2f, false));
        adicionaClausula(2251, new Clausula("Hospitalizaçao e cuidados de parto.", 0.05f, false));
        adicionaClausula(2252, new Clausula("Hospitalizaçao e cuidados cir�rgicos em caso de acidente.", 0.01f, false));
        adicionaClausula(2253, new Clausula("Cirurgia correctora.",0.15f, false));
    }
}

