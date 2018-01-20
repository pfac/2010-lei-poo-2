package poo.seguros;

//import java.util.Collection;
//import java.util.List;
//import java.util.LinkedList;
//import java.util.Scanner;
//import java.util.InputMismatchException;
//import java.util.Iterator;
//import java.util.NoSuchElementException;
import java.util.*;
import java.io.Serializable;
/**
 *
 * @author Pedro
 */
public class MainSeguradoras implements Serializable {
    private static Seguradora baseDados;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(">> GESTÃO DE SEGUROS <<");
        System.out.println("Programa de gestão de uma seguradora.");

       // int trimestreAno[] = {0,0,0,0};

        baseDados = new Seguradora();

        menuPrincipal();
    }

    //  MENUS
    private static void menuPrincipal(){
        LinkedList<String> opcoes = new LinkedList<String>();
        int opcao;
        boolean loop = true;
        

        opcoes.add("Manutenção");
        opcoes.add("Avaliação POO");
        opcoes.add("Importar dados");
        opcoes.add("Guardar dados");

        while(loop){
            opcao = geraMenu("Menu principal", "", opcoes, false, true);
            switch(opcao){
                case 1:
                    loop = menuManutencao();
                    break;
                case 2:
                    loop = menuPOO();
                    break;
                case 3:
                    loop = menuImportacao();
                    break;
                case 4:
                    loop = menuGuardar();
                    break;
                case 99:
                    loop = false;
            }
        }
    }

    private static boolean menuManutencao(){
        List<String> opcoes = new LinkedList<String>();
        int opcao;
        boolean loop = true, result = true;

        opcoes.add("Listar clientes");
        opcoes.add("Listar seguros");

        while(loop){
            opcao = geraMenu("Manutenção", "", opcoes, true, true);
            switch(opcao){
                case 1:
                    imprimeClientes();
                    break;
                case 2:
                    imprimeSeguros();
                    break;
                case 0:
                    result = true;
                    loop = false;
                    break;
                case 99:
                    if(formSair()){
                        result = false;
                        loop = false;
                    }
            }
        }
        return result;
    }

    private static boolean menuPOO(){
        LinkedList<String> opcoes = new LinkedList<String>();
        int opcao;
        boolean loop = true;

        opcoes.add("Número actual de titulares de seguros");//  1
        opcoes.add("Comparar com ano anterior");//  2
        opcoes.add("Tipos de seguro oferecidos");// 3
        opcoes.add("Consultar seguro - premio actual");//   4
        opcoes.add("Novo seguro");//    5
        opcoes.add("Remover cliente");//    6
        opcoes.add("Extinguir seguro");//   7
        opcoes.add("Titular do seguro mais caro");//    8
        opcoes.add("Ficha de cliente");//   9
        opcoes.add("Titulares de seguros (por tipo)");//    10
        opcoes.add("Consultar valor em bolsa");//   11
        opcoes.add("Modificar valor em bolsa");//   12
        opcoes.add("Modificar valor inicial em bolsa");//   13

        while(loop){
            opcao = geraMenu("Avaliação POO", "", opcoes, true, true);
            switch(opcao){
                case 1:
                    System.out.printf("\tClientes titulares: %d\n", baseDados.clientesTitulares());
                    break;
                case 2:
                    imprimeCompararAnoAnterior();
                    break;
                case 3:
                    imprimeSegurosOferecidos();
                    break;
                case 4:
                    formValorActualSeguro();
                    break;
                case 5:
                    menuNovoSeguro();
                    break;
                case 6:
                    formRemoveCliente();
                    break;
                case 7:
                    formExtingueSeguro();
                    break;
                case 8:
                    imprimeTitularSeguroMaisCaro();
                    break;
                case 9:
                    formFichaCliente();
                    break;
                case 10:
                    formTitularesPorTipo();
                    break;
                case 11:
                    imprimeValorBolsa();
                    break;
                case 12:
                    formValorBolsa();
                    break;
                case 13:
                    formValorInicio();
                    break;
                case 99:
                    if(formSair())
                        return false;
                    break;
                case 0:
                    loop = false;
            }
        }
        return true;
    }

    private static boolean menuImportacao(){
        List<String> opcoes = new LinkedList<String>();
        int opcao;
        boolean loop = true, result = false;

        opcoes.add("Importar \"clientes.txt\"");
        opcoes.add("Importar \"seguros.txt\"");
        opcoes.add("Importar \"clientes.bin\"");
        opcoes.add("Importar \"seguros.bin\"");
        boolean worked;
        while(loop){
            opcao = geraMenu("Importar dados", "", opcoes, true, true);
            switch(opcao){
                case 1:
                    worked=FileManager.readClientestxt(baseDados);
                    if(worked)
                        System.out.println("Ficheiro carregado com sucesso!");
                    else
                        System.out.println("Não foi possivel carregar o ficheiro!");
                    break;
                case 2:
                    worked=FileManager.readSegurostxt(baseDados);
                    if(worked)
                        System.out.println("Ficheiro carregado com sucesso!");
                    else
                        System.out.println("Não foi possivel carregar o ficheiro!");
                case 3:
                    FileManager.readClientes(baseDados);
                    break;
                case 4:
                    FileManager.readSeguros(baseDados);
                    break;
                case 0:
                    result = true;
                    loop = false;
                    break;
                case 99:
                    result = loop = false;
            }
        }
        return result;
    }

    private static boolean menuGuardar(){
        List<String> opcoes = new LinkedList<String>();
        int opcao;
        boolean loop = true, result = false;
        Collection<Object> dados = new ArrayList<Object>();

        opcoes.add("Guardar \"clientes.bin\"");
        opcoes.add("Guardar \"seguros.bin\"");
       
        while(loop){
            opcao = geraMenu("Guardar dados", "", opcoes, true, true);
            switch(opcao){
                case 1:
                    for(Cliente c : baseDados.getClientes().values()){
                        dados.add(c);
                    }
                    FileManager.write("clientes.bin", dados);
                    dados.clear();
                    break;
                case 2:
                    for(Seguro s : baseDados.getSeguros().values()){
                        dados.add(s);
                    }
                    FileManager.write("seguros.bin", dados);
                    dados.clear();
                    break;
                case 0:
                    result = true;
                    loop = false;
                    break;
                case 99:
                    result = loop = false;
            }
        }
        return result;
    }
    private static void menuNovoSeguro(){
        switch(subformTipoSeguro("Titulares por tipo de seguro")){
            case SeguroDeImovel:
                formNovoSeguroImovel(false);
                break;
            case SeguroDeImovelComRecheio:
                formNovoSeguroImovel(true);
                break;
            case SeguroDeVeiculo:
                formNovoSeguroVeiculo(false, false);
                break;
            case SeguroDeVeiculoDeMercadorias:
                formNovoSeguroVeiculo(true, false);
                break;
            case SeguroDeVeiculoDeTranspPublico:
                formNovoSeguroVeiculo(false, true);
                break;
            case SeguroDeVida:
                formNovoSeguroSaude(false);
                break;
            case SeguroMedico:
                formNovoSeguroSaude(true);
                break;
            default:
                System.out.println("Não existe um formulário disponível para o tipo de seguro escolhido.");
        }
    }

    //  FORMULÁRIOS
    private static boolean subformConfirmar(String titulo, String questao){
        Scanner reader = new Scanner(System.in);
        String input;
        boolean opcao = false, notAccepted = true;
        System.out.println(titulo);
        System.out.println(questao);
        while(notAccepted){
            System.out.print("[S]im ou [N]ão :\t");
            input = reader.next();
            if(input.charAt(0) == 'S' || input.charAt(0) == 's'){
                opcao = true;
                notAccepted = false;
            }else if(input.charAt(0) == 'N' || input.charAt(0) == 'n'){
                opcao = false;
                notAccepted = false;
            }else{
                System.out.print("Opção inválida. ");
            }
        }
        return opcao;
    }

    private static TipoSeguroOferecido subformTipoSeguro(String titulo){
        List<String> opcoes = new LinkedList<String>();
        int opcao;

        Collection<String> tipos = baseDados.tiposSeguroOferecidos();
        for(String s : tipos){
            opcoes.add(s);
        }

        opcao = geraMenu(   titulo,
                            "Escolha o tipo de seguro a considerar",
                            opcoes, false, false);

        TipoSeguroOferecido[] arr = TipoSeguroOferecido.values();
        TipoSeguroOferecido result = TipoSeguroOferecido.values()[opcao-1];
        return result;
    }

    private static TipoPagamento subformTipoPagamento(){
        List<String> opcoes = new LinkedList<String>();
        int opcao;

        for(TipoPagamento tp : TipoPagamento.values()){
            opcoes.add(tp.name());
        }

        opcao = geraMenu("Tipo de Pagamento", "Escolha o tipo de pagamento para o seguro", opcoes, false, false);

        return TipoPagamento.values()[opcao];
    }

    private static Residencia subformTipoResidencia(){
        List<String> opcoes = new LinkedList<String>();
        int opcao;

        for(Residencia r : Residencia.values()){
            opcoes.add(r.name());
        }

        opcao = geraMenu("Tipo de Residencia", "Escolha o tipo de residência a segurar", opcoes, false, false);

        return Residencia.values()[opcao];
    }

    private static EstadoSaude subformEstadoSaude(){
        List<String> opcoes = new LinkedList<String>();
        int opcao;

        for(EstadoSaude r : EstadoSaude.values()){
            opcoes.add(r.name());
        }

        opcao = geraMenu("Estado de Saude", "Escolha o estado de saúde do cliente a segurar", opcoes, false, false);

        return EstadoSaude.values()[opcao];
    }

    private static void formValorActualSeguro(){
        List<String> campos = new LinkedList<String>();
        List<String> legendas = new LinkedList<String>();
        List<String> questoes = new LinkedList<String>();
        List<Object> valores;
        Object valor;
        Integer codigoSeguro = 0;
        Float valorActual;

        campos.add("int");
        legendas.add("Código");
        questoes.add("Insira o código do seguro");

        valores = geraForm("Valor actual de Seguro", campos, legendas, questoes);

        valor = valores.get(0);
        if(valor!=null)
            codigoSeguro = (Integer) valor;

        try{
            valorActual = baseDados.valorAPagar(codigoSeguro);
            System.out.printf("Valor actual do seguro %d: %f\n", codigoSeguro, valorActual);
        }catch(NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }

    private static void formRemoveCliente(){
        List<String> campos = new LinkedList<String>();
        List<String> legendas = new LinkedList<String>();
        List<String> questoes = new LinkedList<String>();
        List<Object> valores;
        Object valor;
        int codigoCliente = 0;

        campos.add("int");
        legendas.add("Código");
        questoes.add("Insira o código do cliente a remover");

        valores = geraForm("Remover Cliente", campos, legendas, questoes);

        valor = valores.get(0);
        if(valor != null){
            codigoCliente = (Integer) valor;
        }

        try{
            baseDados.removerCliente(codigoCliente);
            System.out.printf("%d removido com sucesso.\n", codigoCliente);
        }catch(ClienteTitularException e){
            System.out.println(e.getMessage());
        }catch(NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }

    private static void formExtingueSeguro(){
        List<String> campos = new LinkedList<String>();
        List<String> legendas = new LinkedList<String>();
        List<String> questoes = new LinkedList<String>();
        List<Object> valores;
        Object valor;
        int codigoCliente = 0, codigoSeguro = 0;

        campos.add("int");
        legendas.add("Código");
        questoes.add("Insira o código do titular");

        campos.add("int");
        legendas.add("Código");
        questoes.add("Insira o código do seguro a extinguir");

        valores = geraForm("Extinguir Seguro", campos, legendas, questoes);

        valor = valores.get(0);
        if(valor != null){
            codigoCliente = (Integer) valor;
        }
        valor = valores.get(1);
        if(valor != null){
            codigoSeguro = (Integer) valor;
        }

        try{
            baseDados.extinguirSeguro(codigoCliente, codigoSeguro);
            System.out.println("Seguro extinto com sucesso.");
        }catch(NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }

    private static void formFichaCliente(){
        List<String> campos = new LinkedList<String>();
        List<String> legendas = new LinkedList<String>();
        List<String> questoes = new LinkedList<String>();
        List<Object> valores;
        Object valor;
        int codigoCliente = 0;
        String nomeCliente = "";
        boolean modo = subformConfirmar("Modo de identificação", "Deseja identificar o cliente por nome?");

        if(modo){
            campos.add("string");
            legendas.add("Nome");
            questoes.add("Insira o nome do cliente");
        }else{
            campos.add("int");
            legendas.add("Código");
            questoes.add("Insira o código do cliente");
        }

        valores = geraForm("Ficha de Cliente", campos, legendas, questoes);

        valor = valores.get(0);
        if(valor != null){
            if(modo){
                nomeCliente = (String) valor;
            }else{
                codigoCliente = (Integer) valor;
            }
        }

        try{
            if(modo){
                System.out.println(baseDados.fichaDeCliente(nomeCliente));
            }else{
                System.out.println(baseDados.fichaDeCliente(codigoCliente));
            }
        }catch(NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }

    private static void formTitularesPorTipo(){
        Collection<Integer> codigosTitulares = null;
        TipoSeguroOferecido tipo = subformTipoSeguro("Titulares por tipo de seguro");
        switch(tipo){
            case SeguroDeImovel:
                codigosTitulares = baseDados.titularesDeSeguroPorTipo(SeguroImovel.class.getSimpleName());
                break;
            case SeguroDeImovelComRecheio:
                codigosTitulares = baseDados.titularesDeSeguroPorTipo(SeguroImovelRecheio.class.getSimpleName());
                break;
            case SeguroDeVeiculo:
                codigosTitulares = baseDados.titularesDeSeguroPorTipo(SeguroVeiculo.class.getSimpleName());
                break;
            case SeguroDeVeiculoDeMercadorias:
                codigosTitulares = baseDados.titularesDeSeguroPorTipo(SeguroVeiculosMercadorias.class.getSimpleName());
                break;
            case SeguroDeVeiculoDeTranspPublico:
                codigosTitulares = baseDados.titularesDeSeguroPorTipo(SeguroVeiculosPublicos.class.getSimpleName());
                break;
            case SeguroDeVida:
                codigosTitulares = baseDados.titularesDeSeguroPorTipo(SeguroVida.class.getSimpleName());
                break;
            case SeguroMedico:
                codigosTitulares = baseDados.titularesDeSeguroPorTipo(SeguroMedico.class.getSimpleName());
                break;
            default:
                System.out.println("Não existe um filtro disponível para o tipo de seguro escolhido.");
        }
        if(codigosTitulares != null){
            System.out.print("Titulares de ");
            System.out.print(tipo.toString());
            System.out.println(":");
            for(Integer i : codigosTitulares){
                System.out.print("\t");
                System.out.println(i);
            }
        }
    }

    private static void formValorBolsa(){
        List<String> campos = new LinkedList<String>();
        List<String> legendas = new LinkedList<String>();
        List<String> questoes = new LinkedList<String>();
        List<Object> valores;
        Object valor;
        double valorBolsa;

        campos.add("double");
        legendas.add("Valor");
        questoes.add("Insira o novo valor da seguradora na Bolsa");

        valores = geraForm("Modificar Valor em Bolsa", campos, legendas, questoes);

        valor = valores.get(0);
        if(valor != null){
            valorBolsa = (Double) valor;
            baseDados.setValorBolsa(valorBolsa);
        }
    }

    private static void formValorInicio(){
        List<String> campos = new LinkedList<String>();
        List<String> legendas = new LinkedList<String>();
        List<String> questoes = new LinkedList<String>();
        List<Object> valores;
        Object valor;
        double valorInicio;

        campos.add("double");
        legendas.add("Valor");
        questoes.add("Insira o novo valor inicial da seguradora na Bolsa");

        valores = geraForm("Modificar Valor Inicial em Bolsa", campos, legendas, questoes);

        valor = valores.get(0);
        if(valor != null){
            valorInicio = (Double) valor;
            baseDados.setValorBolsa(valorInicio);
        }
    }

    private static void formNovoSeguroImovel(boolean comRecheio){
        List<String> campos = new LinkedList<String>();
        List<String> legendas = new LinkedList<String>();
        List<String> questoes = new LinkedList<String>();
        List<Object> valores;
        Object valor;
        int titular = 0;
        float premioBaseAnual = 0;
        float agravamento = 0;
        String segurado = "";
        TipoPagamento tipoPagamento;

        Residencia tipoResidencia;
        String morada = "";
        int anoConstrucao = 0;

        String titulo;

        campos.add("int");
        legendas.add("Titular");
        questoes.add("Insira o código de cliente do titular do seguro");

        campos.add("float");
        legendas.add("Premio");
        questoes.add("Insira o prémio base anual");

        campos.add("float");
        legendas.add("Agravamento");
        questoes.add("Insira o agravamento em caso de ocorrencia");

        campos.add("string");
        legendas.add("Segurado");
        questoes.add("Insira uma descrição do segurado");

        valores = geraForm("Novo Seguro", campos, legendas, questoes);

        valor = valores.get(0);
        if(valor != null){
            titular = (Integer) valor;
        }
        valor = valores.get(1);
        if(valor != null){
            premioBaseAnual = (Float) valor;
        }
        valor = valores.get(2);
        if(valor != null){
            agravamento = (Float) valor;
        }
        valor = valores.get(3);
        if(valor != null){
            segurado = (String) valor;
        }

        tipoPagamento = subformTipoPagamento();

        campos.clear();
        legendas.clear();
        questoes.clear();

        tipoResidencia = subformTipoResidencia();

        campos.add("string");
        legendas.add("Morada");
        questoes.add("Insira a morada do segurado");

        campos.add("int");
        legendas.add("Ano");
        questoes.add("Insira o ano de construção do segurado");

        titulo = "Novo Seguro de Imóvel";
        if(comRecheio){
            titulo += " com Recheio";
        }
        
        valores = geraForm(titulo, campos, legendas, questoes);

        valor = valores.get(0);
        if(valor != null){
            morada = (String) valor;
        }
        valor = valores.get(1);
        if(valor != null){
            anoConstrucao = (Integer) valor;
        }

        if(baseDados.novoSeguro(comRecheio, titular, premioBaseAnual, agravamento, segurado, tipoPagamento, tipoResidencia, morada, anoConstrucao) > 0){
            System.out.print(titulo);
            System.out.println(" criado com sucesso.");
        }else{
            System.out.print(titulo);
            System.out.println(" falhou.");
        }
    }

    private static void formNovoSeguroVeiculo(boolean mercadorias, boolean publico){
        List<String> campos = new LinkedList<String>();
        List<String> legendas = new LinkedList<String>();
        List<String> questoes = new LinkedList<String>();
        List<Object> valores;
        Object valor;
        //  Seguro
        int titular = 0;
        float premioBaseAnual = 0;
        float agravamento = 0;
        String segurado = "";
        TipoPagamento tipoPagamento;

        //  Seguro Veiculo
        String matricula = "";
        String modelo = "";
        String marca = "";
        int idade = 0;

        //  Seguro Veiculo Mercadorias e Publico
        int numeroLugares = 0;
        double peso = 0;
        float comprimento = 0;

        //  Seguro Veiculo Mercadorias
        double capacidade = 0;

        campos.add("int");
        legendas.add("Titular");
        questoes.add("Insira o código de cliente do titular do seguro");

        campos.add("float");
        legendas.add("Premio");
        questoes.add("Insira o prémio base anual");

        campos.add("float");
        legendas.add("Agravamento");
        questoes.add("Insira o agravamento em caso de ocorrencia");

        campos.add("string");
        legendas.add("Segurado");
        questoes.add("Insira uma descrição do segurado");

        valores = geraForm("Novo Seguro", campos, legendas, questoes);

        valor = valores.get(0);
        if(valor != null){
            titular = (Integer) valor;
        }
        valor = valores.get(1);
        if(valor != null){
            premioBaseAnual = (Float) valor;
        }
        valor = valores.get(2);
        if(valor != null){
            agravamento = (Float) valor;
        }
        valor = valores.get(3);
        if(valor != null){
            segurado = (String) valor;
        }

        tipoPagamento = subformTipoPagamento();

        campos.clear();
        legendas.clear();
        questoes.clear();

        campos.add("string");
        legendas.add("Matricula");
        questoes.add("Insira a matricula do Veiculo");

        campos.add("string");
        legendas.add("Marca");
        questoes.add("Insira a marca do Veiculo");

        campos.add("string");
        legendas.add("Modelo");
        questoes.add("Insira o modelo do Veiculo");

        campos.add("int");
        legendas.add("Idade");
        questoes.add("Insira a idade do Veiculo");

        valores = geraForm("Novo Seguro de Veiculo", campos, legendas, questoes);

        valor = valores.get(0);
        if(valor != null){
            matricula = (String) valor;
        }
        valor = valores.get(1);
        if(valor != null){
            marca = (String) valor;
        }
        valor = valores.get(2);
        if(valor != null){
            modelo = (String) valor;
        }
        valor = valores.get(3);
        if(valor != null){
            idade = (Integer) valor;
        }

        campos.clear();
        legendas.clear();
        questoes.clear();

        if(mercadorias || publico){
            campos.add("int");
            legendas.add("Lugares");
            questoes.add("Insira o número de lugares do Veículo");

            campos.add("double");
            legendas.add("Peso");
            questoes.add("Insira o peso do Veículo");

            campos.add("float");
            legendas.add("Comprimento");
            questoes.add("Insira o comprimento do Veículo");

            valores = geraForm("Novo Seguro de Veículo de Mercadorias ou de Veículo de Transporte Público", campos, legendas, questoes);

            valor = valores.get(0);
            if(valor != null){
                numeroLugares = (Integer) valor;
            }
            valor = valores.get(1);
            if(valor != null){
                peso = (Double) valor;
            }
            valor = valores.get(2);
            if(valor != null){
                comprimento = (Float) valor;
            }

            campos.clear();
            legendas.clear();
            questoes.clear();

            if(mercadorias){
                campos.add("double");
                legendas.add("Capacidade");
                questoes.add("Insira a capacidade de carga do Veículo de Mercadorias");

                valores = geraForm("Novo Seguro de Veículo de Mercadorias", campos, legendas, questoes);
                valor = valores.get(0);
                if(valor != null){
                    capacidade = (Double) valor;
                }

                if(baseDados.novoSeguro(titular, premioBaseAnual, agravamento, segurado, tipoPagamento, matricula, modelo, marca, idade, numeroLugares, peso, capacidade, comprimento) > 0){
                    System.out.println("Novo Seguro de Veículo de Mercadorias criado com sucesso");
                }else{
                    System.out.println("Novo Seguro de Veículo de Mercadorias falhou");
                }
            }else if(baseDados.novoSeguro(titular, premioBaseAnual, agravamento, segurado, tipoPagamento, matricula, modelo, marca, idade, numeroLugares, peso, comprimento) > 0){
                System.out.println("Novo Seguro de Veículo de Transporte Público criado com sucesso");
            }else{
                System.out.println("Novo Seguro de Veículo de Transporte Público falhou");
            }
        }else if(baseDados.novoSeguro(titular, premioBaseAnual, agravamento, segurado, tipoPagamento, matricula, modelo, marca, idade) > 0){
            System.out.println("Novo Seguro de Veículo criado com sucesso");
        }else{
            System.out.println("Novo Seguro de Veículo falhou");
        }
    }

    private static void formNovoSeguroSaude(boolean medico){
        List<String> campos = new LinkedList<String>();
        List<String> legendas = new LinkedList<String>();
        List<String> questoes = new LinkedList<String>();
        List<Object> valores;
        Object valor;

        int titular = 0;
        float premioBaseAnual = 0;
        float agravamento = 0;
        int segurado = 0;
        TipoPagamento tipoPagamento;

        //  SeguroSaude
        EstadoSaude es;

        //  SeguroVida
        int codigoClienteBeneficiario = 0;

        //  SeguroMedico
        String profissao = "";

        campos.add("int");
        legendas.add("Titular");
        questoes.add("Insira o código de cliente do titular do seguro");

        campos.add("float");
        legendas.add("Premio");
        questoes.add("Insira o prémio base anual");

        campos.add("float");
        legendas.add("Agravamento");
        questoes.add("Insira o agravamento em caso de ocorrencia");

        campos.add("int");
        legendas.add("Segurado");
        questoes.add("Insira o código de cliente do segurado");

        valores = geraForm("Novo Seguro", campos, legendas, questoes);

        valor = valores.get(0);
        if(valor != null){
            titular = (Integer) valor;
        }
        valor = valores.get(1);
        if(valor != null){
            premioBaseAnual = (Float) valor;
        }
        valor = valores.get(2);
        if(valor != null){
            agravamento = (Float) valor;
        }
        valor = valores.get(3);
        if(valor != null){
            segurado = (Integer) valor;
        }

        tipoPagamento = subformTipoPagamento();
        es = subformEstadoSaude();

        campos.clear();
        legendas.clear();
        questoes.clear();

        if(medico){
            campos.add("string");
            legendas.add("Profissão");
            questoes.add("Insira a profissão do segurado");

            valores = geraForm("Novo Seguro Médico", campos, legendas, questoes);
            valor = valores.get(0);
            if(valor != null){
                profissao = (String) valor;
            }

            if(baseDados.novoSeguro(titular, premioBaseAnual, agravamento, segurado, tipoPagamento, es, profissao) > 0){
                System.out.println("Novo Seguro Médico criado com sucesso");
            }else{
                System.out.println("Novo Seguro Médico falhou");
            }
        }else{
            campos.add("int");
            legendas.add("Beneficiário");
            questoes.add("Insira o código de cliente do beneficiário");

            valores = geraForm("Novo Seguro de Vida", campos, legendas, questoes);
            valor = valores.get(0);
            if(valor != null){
                codigoClienteBeneficiario = (Integer) valor;
            }

            if(baseDados.novoSeguro(titular, premioBaseAnual, agravamento, segurado, tipoPagamento, es, codigoClienteBeneficiario) > 0){
                System.out.println("Novo Seguro de Vida criado com sucesso");
            }else{
                System.out.println("Novo Seguro de Vida falhou");
            }
        }
    }

    private static boolean formSair(){
        return subformConfirmar("SAIR", "Tem certeza que deseja sair?");
    }

    //  IMPRESSORES
    private static void imprimeCompararAnoAnterior(){
        int dif = baseDados.compararAnoAnterior();
        if(dif > 0){
            System.out.println("\tEstá a ganhar clientes.");
        }else if(dif < 0){
            System.out.println("\tEstá a perder clientes.");
        }else{
            System.out.println("\tO número de clientes é igual.");
        }
    }

    private static void imprimeSegurosOferecidos(){
        for(String s : baseDados.tiposSeguroOferecidos()){
           System.out.print("\t");
           System.out.println(s);
        }
    }

    private static void imprimeTitularSeguroMaisCaro(){
        Cliente c = baseDados.titularDoSeguroMaisCaro();
        if(c != null){
            System.out.println(c.toString(false, true));
        }else{
            System.out.println("Existe um erro na base de dados e foi encontrado um seguro cujo código de titular não pertence a nenhum cliente.");
        }
    }

    private static void imprimeValorBolsa(){
        System.out.print("Valor actual da seguradora na Bolsa: ");
        System.out.println(baseDados.getValorBolsa());
    }

    private static void imprimeClientes(){
        Collection<Cliente> clientes = baseDados.getClientes().values();
        for(Cliente c : clientes){
            System.out.print(c.toString());
        }
    }

    private static void imprimeSeguros(){
        Collection<Seguro> seguros = baseDados.getSeguros().values();
        if(seguros.size() == 0){
            System.out.println("Não existem seguros na base de dados.");
        }else{
            System.out.println("Seguros:");
            for(Seguro s : seguros){
                System.out.print(s.toString());
            }
            System.out.println("=========");
        }
    }

    //  GERADORES
    private static int geraMenu(String titulo, String questao, List<String> opcoes, boolean temPai, boolean podeSair){
        int k = 1, result;
        Scanner reader;
        System.out.println(titulo);
        System.out.println(questao);
        for(String s : opcoes){
            System.out.print("[");
            if(k<10){
                System.out.print("0");
            }
            System.out.print(k);
            System.out.print("]\t");
            System.out.println(s);
            k++;
        }
        if(temPai){
            System.out.println("[00]\tVoltar ao anterior");
        }
        if(podeSair){
            System.out.println("[99]\tSair");
        }
        System.out.println("Escolha uma opção.");
        reader = new Scanner(System.in);
        do{
            System.out.print("Opção: ");
            try{
                result = reader.nextInt();
                if((result > 0 && result <= k) || (temPai && result == 0) || (podeSair && result == 99)){
                    //reader.close();
                    return result;
                }
            }catch(InputMismatchException e){
            }
            System.out.print("Opção inválida. ");
        }while(true);
    }

    private static List<Object> geraForm(String titulo, List<String> campos, List<String> legendas, List<String> questoes){
        Iterator<String> camposIt = campos.iterator();
        Iterator<String> legendasIt = legendas.iterator();
        Iterator<String> questoesIt = questoes.iterator();
        Object valor;
        List<Object> result = new LinkedList<Object>();
        String legenda;
        String questao;
        String campo;
        Scanner reader = new Scanner(System.in);
        boolean notAccepted = true;

        System.out.println(titulo);
        System.out.println();
        while(camposIt.hasNext() && legendasIt.hasNext() && questoesIt.hasNext()){
            campo = camposIt.next();
            questao = questoesIt.next();
            legenda = legendasIt.next();
            valor = null;
            System.out.println(questao);
            do{
                System.out.printf("%s: ", legenda);
                try{
                    if(campo.equals("int")){
                        valor = reader.nextInt();
                    }else if(campo.equals("float")){
                        valor = reader.nextFloat();
                    }else if(campo.equals("string")){
                        valor = reader.next();
                    }else if(campo.equals("double")){
                        valor = reader.nextDouble();
                    }
                    notAccepted = false;
                }catch(InputMismatchException e){
                    if(campo.equals("int") || campo.equals("double") || campo.equals("float")){
                        System.out.print("Número inválido");
                    }else{
                        System.out.print(e.getMessage());
                    }
                }
            }while(notAccepted);
            result.add(valor);
        }
        return result;
    }


}
