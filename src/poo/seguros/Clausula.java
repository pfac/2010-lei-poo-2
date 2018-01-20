package poo.seguros;

import java.io.Serializable;

/**
 *  Classe que contem a informação relativa a cláusulas de um contrato tipo
 * seguro.
 *
 * @author Fábio Morais (54815)
 * @version 05-2010
 */
public class Clausula implements Serializable{
    private String texto;
    private float acrescimo;
    private boolean activado;

    /**
     * Constructor por omissão.
     */
    public Clausula(){
        texto = "";
        acrescimo = 0.00f;
        activado = false;
    }

     /**
     * Constructor parametrico da Class de Clausula
     * @param novo_texto a definição da clausula
     * @param novo_acrescimo o valor de acrescimo da clausula
     * @param novo_activo o estado da clausula
     */

    public Clausula(String novo_texto, float novo_acrescimo, boolean novo_activo)
    {
        texto = novo_texto;
        acrescimo = novo_acrescimo;
        activado = novo_activo;
    }

     /**
     * Constructor de copia da Class de Clausula
     * @param novo_clausula a clausula que se quer copiar
     */

    public Clausula(Clausula novo_clausula)
    {
        texto = novo_clausula.texto;
        acrescimo = novo_clausula.acrescimo;
        activado = novo_clausula.activado;
    }

    /**
     * toggleActivado - altera o estado de uma clausula (desactivado -> activado e vice-versa)
     *
    */

     public void toggleActivado () {
        if (activado) {activado=false;}
        else {activado = true;}
     }

     /**
     * setAcescimo - insere um novo valor ao acrescimo da clausula
     *
     * @param  novo_acresimo o valor do acrescimo a inserir
     */

    public void setActivo (boolean novo_activo)
    {
         activado=novo_activo;
    }

     /**
     * getAcescimo - extrai o valor do acrescimo da clausula
     *
     * @return     o valor do acrescimo da clausula
     */

    public float getAcrescimo ()
    {
          return (acrescimo);
    }

     /**
     * getTexto - extrai a definição da clausula
     *
     * @return     o valor do acrescimo da clausula
     */

    public String getTexto ()
    {
          return (texto);
    }

     /**
     * getActivo - extrai o valor do estado da clausula
     *
     * @return o valor do estado da clausula
     */

    public boolean getActivado ()
    {
          return (activado);
    }

     /**
     * setAcescimo - insere um novo valor ao acrescimo da clausula
     *
     * @param  novo_acresimo o valor do acrescimo a inserir
     */

    public void setAcrescimo (float novo_acrescimo)
    {
          acrescimo=novo_acrescimo;
    }

     /**
     * setTexto - insere uma nova defini�ao da clausula
     *
     * @param  novo_texto a definição a inserir
     */

    public void setTexto (String novo_texto)
    {
          texto=novo_texto;
    }

     /**
     * equals - verifica se duas clausulas são iguais
     *
     * @param  alvo-> a clausula a qual se quer comparar
     * @return um boolean a dizer se são iguais ou não
     */

    public boolean equals(Object x)
    {
        if(x==this)
            return true;
        if(x==null || (this.getClass()!=x.getClass()))
            return false;
        // comparação normal
        Clausula alvo = (Clausula) x;
        return ((texto == alvo.getTexto()) && (acrescimo == alvo.getAcrescimo()) && (activado = alvo.getActivado()));
    }

     /**
     * clone - faz uma copia de uma clausula
     *
     * @return  copia da clausula desejada
     */

    public Clausula clone ()
    {
       return new Clausula();
    }

     /**
     * toString - converte uma clausula numa String
     *
     * @return uma String com os campos da Clausula e os valores actuais desses campos.
     */

    public String toString ()
    {   StringBuilder sb = new StringBuilder();
        sb.append("Clausula: " + texto);
        sb.append("\n\tAcrescimo: " + acrescimo);
        sb.append("\n\tEstado: " + activado);
        return sb.toString ();
    }
}

