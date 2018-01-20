package poo.seguros;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.GregorianCalendar;
import java.util.NoSuchElementException;

import poo.Cotavel;

/**
 *
 * @author Pedro
 */
public class Seguradora implements Cotavel{
    private double valorBolsa = 0;
    private double valorInicio = 0;

    private Map<Integer,Cliente> clientes;
    private Map<Integer,Seguro> seguros;

    private int[] trimestresAnoAnterior;

    //  CONSTRUTORES
    public Seguradora(){
        valorBolsa = 0;
        valorInicio = 0;
        clientes = new HashMap<Integer,Cliente>();
        seguros = new HashMap<Integer,Seguro>();
        trimestresAnoAnterior = new int[4];
        for(int k = 0; k < trimestresAnoAnterior.length; k++){
            trimestresAnoAnterior[k] = 0;
        }
    }

    public Seguradora(double valorBolsa, double valorInicio, Map<Integer,Cliente> clientes, Map<Integer,Seguro> seguros, int[] trimestresAnoAnterior){
        int trimestres = (trimestresAnoAnterior.length < 4) ? trimestresAnoAnterior.length : 4;
        this.valorBolsa = valorBolsa;
        this.valorInicio = valorInicio;
        this.clientes = new HashMap<Integer,Cliente>();
        for(Map.Entry<Integer,Cliente> me : clientes.entrySet()){
            this.clientes.put(me.getKey(), me.getValue());
        }
        this.seguros = new HashMap<Integer,Seguro>();
        for(Map.Entry<Integer,Seguro> me : seguros.entrySet()){
            this.seguros.put(me.getKey(), me.getValue());
        }
        this.trimestresAnoAnterior = new int[4];
        for(int k = 0; k < 4; k++){
            this.trimestresAnoAnterior[k] = (k < trimestres) ? trimestresAnoAnterior[k] : 0;
        }
    }

    public Seguradora(Seguradora modelo){
        valorBolsa = modelo.getValorBolsa();
        valorInicio = modelo.getValorInicio();
        clientes = modelo.getClientes();
        seguros = modelo.getSeguros();
        trimestresAnoAnterior = modelo.getTrimestresAnoAnterior();
    }

    //  MÉTODOS DE INTERACÇÃO
    public int clientesTitulares(){
        int count = 0;
        for(Cliente c : clientes.values()){
            if(c.titular())
                count++;
        }
        return count;
    }

    public int compararAnoAnterior(){
        GregorianCalendar agora = new GregorianCalendar();
        int trimestre = agora.get(GregorianCalendar.MONTH) / 3, anoPassado = 0, esteAno = clientes.values().size();

        for(int k = 0; k <= trimestre; k++){
            anoPassado += trimestresAnoAnterior[k];
        }
        
        return esteAno - anoPassado;
    }

    public Collection<String> tiposSeguroOferecidos(){
        return TipoSeguroOferecido.tiposOferecidos();
    }

    public float valorAPagar(int codigoSeguro) throws NoSuchElementException{
        if(seguros.containsKey(codigoSeguro)){
            Seguro s = seguros.get(codigoSeguro);
            return s.calculaPremioAnual();
        }else throw new NoSuchElementException("Não existe nenhum seguro com o código indicado.");
    }

    public Set<Integer> segurosEmAtraso(){
        Set<Integer> result = new TreeSet<Integer>();
        for(Seguro s : seguros.values()){
            if(s.getEstado() == Estado.Atrasado)
                result.add(s.getCodigo());
        }
        return result;
    }

    //  Seguro de Vida
    public int novoSeguro(int codigoCliente, float premioBaseAnual, float agravamento, int segurado, TipoPagamento tipo, EstadoSaude es, int beneficiario){
        if(clienteAprovado(codigoCliente)){
            Seguro s = new SeguroVida(codigoCliente, premioBaseAnual, agravamento, segurado, tipo, es, beneficiario);
            seguros.put(s.getCodigo(), s.clone());
            return s.getCodigo();
        }else
            return 0;
    }

    //  Seguro Médico
    public int novoSeguro(int codigoCliente, float premioBaseAnual, float agravamento, int segurado, TipoPagamento tipo, EstadoSaude es, String profissao){
        if(clienteAprovado(codigoCliente)){
            Seguro s = new SeguroMedico(codigoCliente, premioBaseAnual, agravamento, segurado, tipo, es, profissao);
            seguros.put(s.getCodigo(), s.clone());
            return s.getCodigo();
        }else
            return 0;
    }

    //  Seguro de Veiculo
    public int novoSeguro(int codigoCliente, float premioBaseAnual, float agravamento, String segurado, TipoPagamento tipo, String matricula, String modelo, String marca, int idade){
        if(clienteAprovado(codigoCliente)){
            Seguro s = new SeguroVeiculo(codigoCliente, premioBaseAnual, agravamento, segurado, tipo, matricula, modelo, marca, idade);
            seguros.put(s.getCodigo(), s.clone());
            return s.getCodigo();
        }else
            return 0;
    }
    
    //  Seguro Veiculo Mercadorias
    public int novoSeguro(int codigoCliente, float premioBaseAnual, float agravamento, String segurado, TipoPagamento tipo, String matricula, String modelo, String marca, int idade, int numeroLugares, double peso, double capacidade, float comprimento){
        if(clienteAprovado(codigoCliente)){
            Seguro s = new SeguroVeiculosMercadorias(codigoCliente, premioBaseAnual, agravamento, segurado, tipo, matricula, modelo, marca, idade, numeroLugares, peso, capacidade, comprimento);
            seguros.put(s.getCodigo(), s.clone());
            return s.getCodigo();
        }else
            return 0;
    }

    //  Seguro Veiculo Transp Publico
    public int novoSeguro(int codigoCliente, float premioBaseAnual, float agravamento, String segurado, TipoPagamento tipo, String matricula, String modelo, String marca, int idade, int numeroLugares, double peso, float comprimento){
        if(clienteAprovado(codigoCliente)){
            Seguro s = new SeguroVeiculosPublicos(codigoCliente, premioBaseAnual, agravamento, segurado, tipo, matricula, modelo, marca, idade, numeroLugares, peso, comprimento);
            seguros.put(s.getCodigo(), s.clone());
            return s.getCodigo();
        }else{
            return 0;
        }
    }

    //  Seguro de imóvel com e sem recheio
    public int novoSeguro(boolean comRecheio, int codigoCliente, float premioBaseAnual, float agravamento, String segurado, TipoPagamento tipo, Residencia novo_tipo, String nova_morada, int novo_ano){
        if(clienteAprovado(codigoCliente)){
            Seguro s;
            if(comRecheio){
                s = new SeguroImovelRecheio(codigoCliente, premioBaseAnual, agravamento, segurado, tipo, novo_tipo, nova_morada, novo_ano);
            }else{
                s = new SeguroImovel(codigoCliente, premioBaseAnual, agravamento, segurado, tipo, novo_tipo, nova_morada, novo_ano);
            }
            seguros.put(s.getCodigo(), s.clone());
            return s.getCodigo();
        }else
            return 0;
    }

    public void removerCliente(int codigoCliente) throws NoSuchElementException, ClienteTitularException{
        if(clientes.containsKey(codigoCliente)){
            if(clientes.get(codigoCliente).getSeguros().size() == 0){
                clientes.remove(codigoCliente);
            }throw new ClienteTitularException();
        }else throw new NoSuchElementException("O cliente indicado não existe.");
    }

    public void extinguirSeguro(int codigoCliente, int codigoSeguro) throws NoSuchElementException{
        if(clientes.containsKey(codigoCliente)){
            Set<Integer> seguros = clientes.get(codigoCliente).getSeguros();
            if(seguros.contains(codigoSeguro)){
                seguros.remove(codigoSeguro);
                clientes.get(codigoCliente).setSeguros(seguros);
                if(this.seguros.containsKey(codigoSeguro))
                    this.seguros.remove(codigoSeguro);
            }else throw new NoSuchElementException("O cliente indicado não é titular do seguro "+codigoSeguro);
        }else throw new NoSuchElementException("O cliente indicado não existe.");
    }

    public Cliente titularDoSeguroMaisCaro(){
        float valorSeguroMaisCaro = 0;
        int codigoTitular = 0;
        for(Seguro s : seguros.values()){
            if(s.calculaPremioAnual() > valorSeguroMaisCaro){
                valorSeguroMaisCaro = s.calculaPremioAnual();
                codigoTitular = s.getTitular();
            }
        }
        if(codigoTitular != 0 && clientes.containsKey(codigoTitular))
            return clientes.get(codigoTitular).clone();
        else
            return null;
    }

    public String fichaDeCliente(int codigoCliente) throws NoSuchElementException{
        if(clientes.containsKey(codigoCliente))
            return clientes.get(codigoCliente).toString(true, true);
        else throw new NoSuchElementException("O cliente indicado não existe.");
    }

    public String fichaDeCliente(String nomeCliente) throws NoSuchElementException{
        Cliente c = null;
        Iterator<Cliente> it = clientes.values().iterator();
        boolean notFound = true;
        while(it.hasNext() && notFound){
            c = it.next();
            if(c.getNome().equals(nomeCliente)){
                notFound = false;
            }
        }
        if(notFound)
            throw new NoSuchElementException("O cliente indicado não existe.");
        else
            return c.toString(true,true);
    }

    public Collection<Integer> titularesDeSeguroPorTipo(String tipo){
        Collection<Integer> result = new TreeSet<Integer>();
        Iterator<Integer> it;
        boolean notFound;
        int codigoSeguro;
        Seguro s;
        
        for(Cliente c : clientes.values()){
            it = c.getSeguros().iterator();
            notFound = true;
            while(it.hasNext() && notFound){
                codigoSeguro = it.next();
                if(seguros.containsKey(codigoSeguro)){
                    s = seguros.get(codigoSeguro).clone();
                    String className = s.getClass().getSimpleName();
                    if(className.equals(tipo)){
                        notFound = false;
                    }
                }
            }
            if(!notFound)
                result.add(c.getCodigo());
        }
        return result;
    }

    public boolean adicionarCliente(Cliente c){
        if(clientes.containsKey(c.getCodigo())){
            return false;
        }else{
            clientes.put(c.getCodigo(), c.clone());
            return true;
        }
    }

    public boolean adicionarSeguro(Seguro s){
        if(this.seguros.containsKey(s.getCodigo())){
            return false;
        }else{
            int codigoCliente = s.getTitular();
            if(clientes.containsKey(codigoCliente)){
                Cliente c = clientes.get(codigoCliente);
                Set<Integer> seguros = c.getSeguros();
                if(!seguros.contains(s.getCodigo())){
                    seguros.add(s.getCodigo());
                    c.setSeguros(seguros);
                    clientes.put(codigoCliente, c);
                }
                this.seguros.put(s.getCodigo(), s.clone());
                return true;
            }else
                return false;
        }
    }
    //  MÉTODOS DE ESCRITA

    //  MÉTODOS DE LEITURA
    public double getValorInicio(){
        return valorInicio;
    }

    public Map<Integer,Cliente> getClientes(){
        Map<Integer,Cliente> result = new HashMap<Integer,Cliente>();
        for(Map.Entry<Integer,Cliente> me : clientes.entrySet()){
            result.put(me.getKey(), me.getValue());
        }
        return result;
    }

    public Map<Integer,Seguro> getSeguros(){
        Map<Integer,Seguro> result = new HashMap<Integer,Seguro>();
        for(Map.Entry<Integer,Seguro> me : seguros.entrySet()){
            result.put(me.getKey(), me.getValue());
        }
        return result;
    }

    public int[] getTrimestresAnoAnterior(){
        int[] result = new int[4];
        for(int k = 0; k < 4; k++){
            result[k] = trimestresAnoAnterior[k];
        }
        return result;
    }

    //  MÉTODOS DE INTERFACE - COTAVEL
    public double getValorBolsa(){
        return valorBolsa;
    }

    public void setValorBolsa(double valor){
        valorBolsa = valor;
    }

    public void setValInicio(double valor){
        valorInicio = valor;
    }

    //  MÉTODOS AUXILIARES
    private boolean clienteAprovado(int codigoCliente) throws NoSuchElementException{
        if(clientes.containsKey(codigoCliente)){
            Set<Integer> seguros = clientes.get(codigoCliente).getSeguros();
            for(Integer i : seguros){
                if(this.seguros.containsKey(i) && this.seguros.get(i).getEstado() == Estado.Atrasado){
                    return false;
                }
            }
            return true;
        }else throw new NoSuchElementException("Não foi encontrado nenhum cliente com o código indicado.");
    }
}
