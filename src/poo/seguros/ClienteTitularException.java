package poo.seguros;

/**
 *
 * @author Pedro
 */
public class ClienteTitularException extends Exception {

    public ClienteTitularException() { super("O cliente indicado é titular de um ou mais seguros."); }

    public ClienteTitularException(String msg) { super(msg); }
}
