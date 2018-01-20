package poo.seguros;

import java.util.Set;
import java.util.TreeSet;
import java.io.Serializable;

/**
 *  Nesta classe é armazenada toda a informação associada a um determinado cliente,
 * nomeadamente, os seus dados pessoais e os seguros de que é titular.
 *
 * @author Pedro Costa (54736)
 * @version 05-2010
 */
public class Cliente implements Serializable{
    private static Integer proxCodigo = 0;//	gerador sequencial de códigos de cliente

    private Integer codigo;//	código de identificação
    private String nome;
    private String morada;
    private Long bi;//	Bilhete de identidade
    private Long nif;//	Número de Identificação Fiscal
    private Set<Integer> seguros;// códigos dos seguros de que é titular

    //	CONSTRUTORES
    /**
     * Construtor por omissão. Cria uma nova instância de cliente com os valores padrão.
     */
    public Cliente(){
	novoCodigo();
	nome = "";
	morada = "";
	bi = 0L;
	nif = 0L;
	seguros = new TreeSet<Integer>();
    }

    /**
     * Construtor paramétrico. Cria uma nova instância de cliente com os valores
     * escolhidos.
     * @param nome Nome do cliente
     * @param morada Morada do cliente
     * @param bi Número do Bilhete de Identidade
     * @param nif   Número de Identificação Fiscal
     */
    public Cliente(String nome, String morada, Long bi, Long nif){
	novoCodigo();
	this.nome = nome;
	this.morada = morada;
	this.bi = bi;
	this.nif = nif;
	seguros = new TreeSet<Integer>();
    }
    public Cliente(int codigo, String nome, String morada, Long bi, Long nif){
	this.codigo = codigo;
        if(proxCodigo <= codigo)
            proxCodigo = codigo+1;
	this.nome = nome;
	this.morada = morada;
	this.bi = bi;
	this.nif = nif;
	seguros = new TreeSet<Integer>();
    }

    /**
     * Construtor de cópia. Cria uma nova instância de cliente com os mesmos valores
     * do cliente escolhido.
     * @param model Instância de cliente a copiar
     */
    public Cliente(Cliente model){
	codigo = model.getCodigo();
	nome = model.getNome();
	morada = model.getMorada();
	bi = model.getBI();
	nif = model.getNIF();
	seguros = model.getSeguros();
    }

    //	MÉTODOS DE LEITURA
    /**
     * @return Codigo de identificação do cliente
     */
    public Integer getCodigo(){
	return codigo;
    }

    /**
     * @return Nome do cliente
     */
    public String getNome(){
	return nome;
    }

    /**
     * @return Morada do cliente
     */
    public String getMorada(){
	return morada;
    }

    /**
     * @return Número de Bilhete de Identidade do cliente
     */
    public Long getBI(){
	return bi;
    }

    /**
     * @return Número de Identificação Fiscal do cliente
     */
    public Long getNIF(){
	return nif;
    }

    /**
     * @return Conjunto (Set) com os códigos dos seguros dos quais o cliente é titular.
     */
    public Set<Integer> getSeguros(){
	Set<Integer> res = new TreeSet<Integer>();
	for(Integer i : seguros){
	    res.add(i);
	}
	return res;
    }

    //	MÉTODOS DE ESCRITA
    /**
     * Modifica a morada do cliente
     * @param morada Nova morada do cliente
     */
    public void setMorada(String morada){
	this.morada = morada;
    }

    /**
     * Modifica o conjunto de seguros associado ao cliente
     * @param seguros Novo conjunto de seguros a associar
     */
    public void setSeguros(Set<Integer> seguros){
	this.seguros.clear();
	for(Integer i : seguros){
	    this.seguros.add(i);
	}
    }

    //	MÉTODOS DE INTERACÇÃO
    public void adicionarSeguro(Integer codigo){
	seguros.add(codigo);
    }

    public void removerSeguro(Integer codigo){
	if(seguros.contains(codigo))
	seguros.remove(codigo);
    }

    public boolean titular(){
        return (seguros.size() > 0);
    }

    //	REDEFINIÇÕES DE OBJECT
    /**
     * Clona o receptor da mensagem.
     * @return Instância de cliente com os mesmos valores do receptor da mensagem.
     */
    @Override
    public Cliente clone(){
	return new Cliente(this);
    }

    /**
     * Compara o receptor da mensagem com outro Object.
     * @param obj   Objecto a comparar com o receptor da mensagem.
     * @return true se os valores de todos os campos, incluindo os códigos dos seguros
     * associados, do receptor da mensagem são exactamente iguais aos respectivos campos
     * no Object passado como parâmetro. Caso o Object passado como parâmetro seja null,
     * de uma classe diferente da do receptor, ou os valores difiram em algum campo,
     * retorna false.
     */
    @Override
    public boolean equals(Object obj){
	if(this==obj)
	    return true;
	if(obj==null || this.getClass()!=obj.getClass())
	    return false;
	Cliente cli = (Cliente) obj;
	return	(codigo == cli.getCodigo() &&
		nome.equals(cli.getNome()) &&
		morada.equals(cli.getMorada()) &&
		bi == cli.getBI() &&
		nif == cli.getNIF() &&
		seguros.containsAll(cli.getSeguros()));
    }

    /**
     * Contrói uma String com os valores das variáveis de instância do receptor.
     * @return A String com a descrição dos valores no objecto, separados por
     * vírgulas
     */
    @Override
    public String toString(){
	StringBuilder sb = new StringBuilder();
	sb.append("CLIENTE:\t");
	sb.append(codigo);
	sb.append(",");
	sb.append(nome);
	sb.append(",");
	sb.append(morada);
	sb.append(",");
	sb.append(bi);
	sb.append(",");
	sb.append(nif);
	sb.append("\n");
	return sb.toString();
    }

    /**
     * Contrói uma String com os valores das variáveis de instância do receptor.
     * @param verbose	Se true, é adicionada à String a listagem dos seguros
     * dos quais o cliente é titular.
     * @param multiline	Se true, os valores são organizados de forma a facilitar
     * a leitura ocupando diversas linhas.
     * @return	A String com a descrição dos valores no objecto. toString(false,false)
     * tem o mesmo resultado que toString()
     */
    public String toString(Boolean verbose, Boolean multiline){
	StringBuilder sb = new StringBuilder();
	if(multiline){
	    sb.append("[CLIENTE]");
	    sb.append("\n\tCódigo: ");
	    sb.append(codigo);
	    sb.append("\n\tNome: ");
	    sb.append(nome);
	    sb.append("\n\tMorada: ");
	    sb.append(morada);
	    sb.append("\n\tBI: ");
	    sb.append(bi);
	    sb.append("\n\tNIF: ");
	    sb.append(nif);
	    if(verbose){
		sb.append("\n\t> SEGUROS:\n");
		for(Integer i : seguros){
		    sb.append("\t\tCódigo ");
		    sb.append(i);
		    sb.append("\n");
		}
	    }else{
		sb.append("\n");
	    }
	    sb.append("=====");
	}else{
	    sb.append(toString());
	    if(verbose){
		sb.append("\t> SEGUROS: ");
		for(Integer i : seguros){
		    sb.append(i);
		    sb.append(", ");
		}
		sb.append("\n");
	    }
	}
	return sb.toString();
    }

    //	MÉTODOS AUXILIARES
    private void novoCodigo(){
	codigo = proxCodigo++;
    }
}
