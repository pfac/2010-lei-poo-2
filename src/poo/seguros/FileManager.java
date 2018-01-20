/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package poo.seguros;
import java.util.*;
import java.io.*;
/**
 *
 * @author Andre
 */
public class FileManager implements Serializable {

    public static void readClientes(Seguradora z){
         ObjectInputStream inputStream = null;
               
         try {

            inputStream = new ObjectInputStream(new FileInputStream("clientes.bin"));
            
            Object obj = null;
            
            while ((obj = inputStream.readObject()) != null) {
                
                if (obj instanceof Cliente) {
                    
                    z.adicionarCliente((Cliente)obj);                    
                }

            }
            inputStream.close();
        }
        
         catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
         catch (FileNotFoundException ex) {
            System.out.println("O ficheiro especificado não foi encontrado!");
        }
         catch (IOException ex) {
            System.out.println("Fim do Ficheiro, carregado com sucesso!");
         }
     
    }
    
    public static void readSeguros(Seguradora z){
         ObjectInputStream inputStream = null;

         try {

            inputStream = new ObjectInputStream(new FileInputStream("seguros.bin"));

            Object obj = null;

            while ((obj = inputStream.readObject()) != null) {

                if (obj instanceof Seguro) {

                    z.adicionarSeguro((Seguro)obj);
                }

            }
            inputStream.close();
        }

         catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
         catch (FileNotFoundException ex) {
            System.out.println("O ficheiro especificado não foi encontrado!");
        }
         catch (IOException ex) {
            System.out.println("Fim do Ficheiro, carregado com sucesso!");
         }

    }

    public static void write(String fileName, Collection<Object> values){
        ObjectOutputStream outputStream;
        try{
            outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            for(Object obj : values){
                outputStream.writeObject(obj);
            }
            outputStream.close();
        }catch(IOException e){
            System.out.print("Erro: ");
            System.out.println(e.getMessage());
        }
    }

   public static boolean readClientestxt(Seguradora z){
       Cliente inp;
       
       
       String strLine="";
       try{

          BufferedReader in = new BufferedReader(new FileReader("c://cliente.txt"));
            while((strLine = in.readLine())!=null){
                inp=stringToCliente(strLine);
                z.adicionarCliente(inp);
            }
                in.close();
       }
             catch (Exception e){
                System.err.println("Erro: " + e.getMessage());
                System.out.println("Falha ao carregar o ficheiro!");
                return false;
            }
             
       
       
       System.out.println("Ficheiro Carregado com sucesso");
       return true;
   }

    public static Cliente stringToCliente(String x){
        String[] frase;
        String nome, morada;
        int cod=0;
        long bi, nif;
        frase = x.split(",");
        cod=Integer.parseInt(frase[0]);
        nome=(String)frase[1];
        morada=(String)frase[2];
        bi=Long.parseLong(frase[3]);
        nif=Long.parseLong(frase[4]);

        Cliente inp=new Cliente(cod, nome, morada,bi,nif);
        return inp;
    }
     public static boolean readSegurostxt(Seguradora z){
       Seguro inp=null;
       String strLine="";
       try{
            //File file = new File("C:\\seguro.txt");
            BufferedReader in = new BufferedReader(new FileReader("c://seguro.txt"));
            while((strLine = in.readLine())!=null){
                 inp=stringToSeguro(strLine, z);
                 z.adicionarSeguro(inp);
            }
            in.close();
            return true;
            }
            catch (Exception e){
                System.err.println("Erro: " + e.getMessage());
                return false;
            }
        
   }
      public static Seguro stringToSeguro(String x, Seguradora z){
        String []frase;
        Seguro inp= null;
        String TipoSeguroOferecido, tipoRes, stipo, segurado,morada;
        int titular, anoConstrucao, codigoSeg;
        TipoPagamento tipo;
        Residencia tipoResidencia;
        float premio_base_anual,agravamento;
        frase = x.split(",");

        TipoSeguroOferecido=(String)frase[0];
        
        
        if (TipoSeguroOferecido.equals("SeguroDeImovel")){
            codigoSeg=Integer.parseInt(frase[1]);
            titular=Integer.parseInt(frase[2]);
            premio_base_anual=Float.parseFloat(frase[3]);
            agravamento=Float.parseFloat(frase[4]);
            segurado=(String)frase[5];
            stipo=(String)frase[6];
            
            if(stipo.equals("Dinheiro"))
                       tipo=TipoPagamento.Dinheiro;
                else
                        tipo=TipoPagamento.TransfBancaria;

            tipoRes=(String)frase[7];
                if(tipoRes.equals("Casa"))
                       tipoResidencia=Residencia.Casa;
                else if(tipoRes.equals("Apartamento"))
                           tipoResidencia=Residencia.Apartamento;
                     else
                         tipoResidencia=Residencia.Terreno;
            morada=(String)frase[8];
            anoConstrucao=Integer.parseInt(frase[9]);
            inp = new SeguroImovel(codigoSeg, titular,premio_base_anual,agravamento,segurado, tipo, tipoResidencia,morada, anoConstrucao );
            
        }
        else if(TipoSeguroOferecido.equals("SeguroDeImovelComRecheio")){
            codigoSeg=Integer.parseInt(frase[1]);
            titular=Integer.parseInt(frase[2]);
            premio_base_anual=Float.parseFloat(frase[3]);
            agravamento=Float.parseFloat(frase[4]);
            segurado=(String)frase[5];
            stipo=(String)frase[6];

            if(stipo.equals("Dinheiro"))
                       tipo=TipoPagamento.Dinheiro;
                else
                        tipo=TipoPagamento.TransfBancaria;

            tipoRes=(String)frase[7];
                if(tipoRes.equals("Casa"))
                       tipoResidencia=Residencia.Casa;
                else if(tipoRes.equals("Apartamento"))
                           tipoResidencia=Residencia.Apartamento;
                     else
                         tipoResidencia=Residencia.Terreno;
            morada=(String)frase[8];
            anoConstrucao=Integer.parseInt(frase[9]);
            inp = new SeguroImovelRecheio(codigoSeg,titular,premio_base_anual,agravamento,segurado, tipo, tipoResidencia,morada, anoConstrucao );
            
        }
            else if(TipoSeguroOferecido.equals("SeguroDeVeiculo")){
                String matricula, modelo, marca;
                int idade;
                codigoSeg=Integer.parseInt(frase[1]);
                titular=Integer.parseInt(frase[2]);
                premio_base_anual=Float.parseFloat(frase[3]);
                agravamento=Float.parseFloat(frase[4]);
                segurado=(String)frase[5];
                stipo=(String)frase[6];
                if(stipo.equals("Dinheiro"))
                       tipo=TipoPagamento.Dinheiro;
                else
                        tipo=TipoPagamento.TransfBancaria;
                matricula=(String)frase[7];
                modelo=(String)frase[8];
                marca=(String)frase[9];
                idade=Integer.parseInt(frase[10]);
                inp= new SeguroVeiculo(codigoSeg,titular, premio_base_anual, agravamento, segurado,tipo, matricula, modelo, marca, idade );
               
            }
                else if(TipoSeguroOferecido.equals("SeguroDeVeiculoDeMercadorias")){
                    int numeroLugares;
                    double peso;
                    double capacidade;
                    float comprimento;
                    String matricula, modelo, marca;
                    int idade;
                    codigoSeg=Integer.parseInt(frase[1]);
                    titular=Integer.parseInt(frase[2]);
                    premio_base_anual=Float.parseFloat(frase[3]);
                    agravamento=Float.parseFloat(frase[4]);
                    segurado=(String)frase[5];
                    stipo=(String)frase[6];
                    if(stipo.equals("Dinheiro"))
                           tipo=TipoPagamento.Dinheiro;
                    else
                           tipo=TipoPagamento.TransfBancaria;
                    matricula=(String)frase[7];
                    modelo=(String)frase[8];
                    marca=(String)frase[9];
                    idade=Integer.parseInt(frase[10]);
                    numeroLugares=Integer.parseInt(frase[11]);
                    peso=Double.parseDouble(frase[12]);
                    capacidade=Double.parseDouble(frase[13]);
                    comprimento=Float.parseFloat(frase[14]);
                    inp= new SeguroVeiculosMercadorias(codigoSeg,titular, premio_base_anual, agravamento, segurado,tipo, matricula, modelo, marca, idade, numeroLugares,peso,capacidade,comprimento);
                    

                }
                        else if(TipoSeguroOferecido.equals("SeguroDeVeiculoDeTranspPublico")){
                           int numeroLugares;
                           double peso;
                            
                           float comprimento;
                           String matricula, modelo, marca;
                           int idade;
                           codigoSeg=Integer.parseInt(frase[1]);
                           titular=Integer.parseInt(frase[2]);
                           premio_base_anual=Float.parseFloat(frase[3]);
                           agravamento=Float.parseFloat(frase[4]);
                           segurado=(String)frase[5];
                           stipo=(String)frase[6];
                           if(stipo.equals("Dinheiro"))
                               tipo=TipoPagamento.Dinheiro;
                           else
                               tipo=TipoPagamento.TransfBancaria;
                           matricula=(String)frase[7];
                           modelo=(String)frase[8];
                           marca=(String)frase[9];
                           idade=Integer.parseInt(frase[10]);
                           numeroLugares=Integer.parseInt(frase[11]);
                           peso=Double.parseDouble(frase[12]);
                           comprimento=Float.parseFloat(frase[13]);
                           inp= new SeguroVeiculosPublicos(codigoSeg, titular, premio_base_anual, agravamento, segurado,tipo, matricula, modelo, marca, idade, numeroLugares,peso,comprimento);
                           
                        }
                           else if(TipoSeguroOferecido.equals("SeguroDeVida")){
                           EstadoSaude es;
                            String ess;
                            int beneficiario, codSegurado;
                            codigoSeg=Integer.parseInt(frase[1]);
                            titular=Integer.parseInt(frase[2]);
                            premio_base_anual=Float.parseFloat(frase[3]);
                            agravamento=Float.parseFloat(frase[4]);
                            codSegurado=Integer.parseInt(frase[5]);
                            stipo=(String)frase[6];
                            if(stipo.equals("Dinheiro"))
                                   tipo=TipoPagamento.Dinheiro;
                            else
                                   tipo=TipoPagamento.TransfBancaria;
                            ess=(String)frase[7];
                            if(ess.equals("Saudavel"))
                                    es=EstadoSaude.Saudavel;
                            else if(ess.equals("Terminal"))
                                    es=EstadoSaude.Terminal;
                            else
                                    es=EstadoSaude.Cronico;
                            beneficiario=Integer.parseInt(frase[8]);
                            inp=new SeguroVida(codigoSeg,titular, premio_base_anual, agravamento, codSegurado, tipo, es, beneficiario);
                            
                    }
                            else if(TipoSeguroOferecido.equals("SeguroMedico")){
                                EstadoSaude es;
                                String ess, prof;
                                int codSegurado;
                                codigoSeg=Integer.parseInt(frase[1]);
                                titular=Integer.parseInt(frase[2]);
                                premio_base_anual=Float.parseFloat(frase[3]);
                                agravamento=Float.parseFloat(frase[4]);
                                codSegurado=Integer.parseInt(frase[5]);
                                stipo=(String)frase[6];
                                if(stipo.equals("Dinheiro"))
                                       tipo=TipoPagamento.Dinheiro;
                                else
                                       tipo=TipoPagamento.TransfBancaria;
                                ess=(String)frase[7];
                                if(ess.equals("Saudavel"))
                                        es=EstadoSaude.Saudavel;
                                else if(ess.equals("Terminal"))
                                        es=EstadoSaude.Terminal;
                                else
                                        es=EstadoSaude.Cronico;
                                prof=(String)frase[8];
                                inp= new SeguroMedico(codigoSeg,titular, premio_base_anual, agravamento, codSegurado, tipo, es, prof);
                               
                            }

        

        return inp;
      
    }
}