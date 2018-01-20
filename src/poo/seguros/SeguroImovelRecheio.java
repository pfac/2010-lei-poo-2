package poo.seguros;

import java.io.Serializable;

/**
 *
 * @author fabio
 */
public class SeguroImovelRecheio extends SeguroImovel implements Serializable
{

   /**
  * Constructor de omissão da Class de SeguroImovelRecheio
  */
    public SeguroImovelRecheio()
    {
        super();
         condicoes();
    }

     /**
     * Construtor param�trico para SeguroImovelRecheio.
     *
     * @param   titular   instancia de Cliente com a informação relativa ao titular do seguro.
     * @param   premio_base_anual    prémio base a pagar anualmente.
     * @param   agravamento aumento do PMA em caso de ocorrência registada.
     * @param   segurado    descrição do objecto do seguro.
     * @param   novo_tipo o tipo de residencia (apartamento, casa, terreno).
     * @param   nova_morada a morada da residencia
     * @param   novo_ano a data de construçao
     */

    public SeguroImovelRecheio (int codigo, Integer titular, float premio_base_anual, float agravamento, String segurado, TipoPagamento tipo, Residencia novo_tipo, String nova_morada, int novo_ano)
    {
        super(  codigo,
                titular,
                premio_base_anual,
                agravamento,
                segurado,
                tipo,
                novo_tipo,
                nova_morada,
                novo_ano
            );
         condicoes();
    }

    public SeguroImovelRecheio (Integer titular, float premio_base_anual, float agravamento, String segurado, TipoPagamento tipo, Residencia novo_tipo, String nova_morada, int novo_ano)
    {
        super(  titular,
                premio_base_anual,
                agravamento,
                segurado,
                tipo,
                novo_tipo,
                nova_morada,
                novo_ano
            );
         condicoes();
    }

    /**
     * Construtor de copia para SeguroImovelRecheio.
     *
     * @param   modelo  instancia de SeguroImovel a copiar.
     */

    public SeguroImovelRecheio (SeguroImovelRecheio alvo)
    {
        super(  alvo.getTitular(),
                alvo.getPBA(),
                alvo.getAgravamento(),
                alvo.getSegurado(),
                alvo.getTipoDePagamento(),
                alvo.getTipoDeResidencia(),
                alvo.getMorada(),
                alvo.getAnoConstrucao()
            );
         condicoes();
    }


   private void condicoes(){
        adicionaClausula(5500, new Clausula("Vandalizaçao.", 0.02f, false));
        adicionaClausula(5501, new Clausula("Roubo.", 0.2f, false));
    }

}



