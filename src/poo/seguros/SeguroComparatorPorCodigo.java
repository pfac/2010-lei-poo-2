package poo.seguros;

import java.util.Comparator;

/**
 *  Comparador de Seguros por código.
 * 
 * @author Pedro
 * @version 06-2010
 */
public class SeguroComparatorPorCodigo implements Comparator<Seguro>{
    /**
     * Compara duas instâncias de Seguro.
     * @param s1    Seguro a comparar
     * @param s2    Seguro a utilizar como referência de comparação
     * @return  Menor, igual, ou maior que zero, respectivamente, se s1 é menor,
     * igual ou maior que s2
     */
    public int compare(Seguro s1, Seguro s2){
        return (s1.getCodigo() - s2.getCodigo());
    }
}
