package poo.seguros;

import java.util.Comparator;

/**
 *
 * @author pedro
 */
public class ClienteComparatorPorCodigo implements Comparator<Cliente>{
    public int compare(Cliente c1, Cliente c2){
	return (c1.getCodigo() - c2.getCodigo());
    }
}
