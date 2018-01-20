package poo.seguros;

import java.io.Serializable;

/**
 *
 * @author fabio
 */
public class SeguroVida extends SeguroSaude implements Serializable{
    private int beneficiario;

    //  CONSTRUTORES
    /**
     * Construtor por omissão para SeguroVida.
     */
    public SeguroVida(){
        super();
        beneficiario = 0;
    }
    /**
     * Construtor paramatrico para SeguroVida.
     *
     * @param   titular   instância de Cliente com a informaçao relativa ao titular do seguro.
     * @param   premio_base_anual    premio base a pagar anualmente.
     * @param   agravamento aumento do PMA em caso de ocorrencia registada.
     * @param   segurado    instancia de Cliente com a informaçao relativa ao segurado.
     * @param   es  Estado de saude do segurado.
     */
    public SeguroVida(  Integer titular,
                        float premioBaseAnual,
                        float agravamento,
                        int segurado,
                        TipoPagamento tipo,
                        EstadoSaude es,
                        int beneficiario){
        super(titular, premioBaseAnual, agravamento, segurado, tipo, es);
        this.beneficiario = beneficiario;
    }

        public SeguroVida(int codigo,
                        Integer titular,
                        float premioBaseAnual,
                        float agravamento,
                        int segurado,
                        TipoPagamento tipo,
                        EstadoSaude es,
                        int beneficiario){
        super(codigo, titular, premioBaseAnual, agravamento, segurado, tipo, es);
        this.beneficiario = beneficiario;
    }

    /**
     * Construtor de copia para SeguroVida.
     *
     * @param   modelo  instancia de SeguroVida a copiar.
     */
    public SeguroVida(SeguroVida modelo){
        super(modelo);
        for(String s : modelo.getObservacoes()){
            adicionarObservacao(s);
        }
        beneficiario = modelo.getBeneficiario();
    }

    //  METODOS DE LEITURA
    /**
     * @return  Instancia de Cliente com os dados relativos ao beneficiario do seguro.
     */
    public int getBeneficiario(){
        return beneficiario;
    }

    //  METODOS DE ESCRITA
    /**
     * @param   beneficiario    instancia de Cliente com os dados relativos ao novo
     * benefici�rio do seguro.
     */
    public void setBeneficiario(int beneficiario){
        this.beneficiario = beneficiario;
    }

    //  REDEFINICOES DE OBJECT
    /**
     * Converte os valores das variaveis de instancia do receptor da
     * mensagem para String.
     *
     * @return  A string com os valores das variAveis listados.
     */
    @Override
    public String toString(){
        return toString(false,false);
    }
    @Override
    public String toString(boolean verbose, boolean multiline){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString(verbose, multiline));
        if(multiline){
            sb.append("\tBeneficiário: ");
            sb.append(beneficiario);
        }else{
            sb.append("\t");
            sb.append(beneficiario);
        }
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Clona o receptor da mensagem.
     *
     * @return  Uma nova instancia de SeguroVida, cujas variaveis de instancia
     * possuem os mesmos valores que a instancia actual.
     */
    public SeguroVida clone(){
        return new SeguroVida(this);
    }

    /**
     * Verifica o receptor da mensagem e outro objecto sao iguais.
     *
     * @param   obj Objecto a comparar com o receptor.
     *
     * @return  true se forem o mesmo objecto ou pertencerem a mesma
     * classe e todas as variaveis de instancia possuirem valores
     * identicos. false se obj e null ou se os dois objectos forem de
     * classes diferentes.
     */
    public boolean equals(Object obj){
        if(obj==this)
            return true;
        if(obj==null || obj.getClass()!=this.getClass())
            return false;
        SeguroVida sv = (SeguroVida) obj;
        return (super.equals(obj) &&
                beneficiario == sv.getBeneficiario()
                );
    }
}


