package poo.seguros;

import java.util.Collection;
import java.util.ArrayList;

/**
 *
 * @author Pedro
 */
public enum TipoSeguroOferecido {
    SeguroDeImovel,
    SeguroDeImovelComRecheio,
    SeguroDeVeiculo,
    SeguroDeVeiculoDeMercadorias,
    SeguroDeVeiculoDeTranspPublico,
    SeguroDeVida,
    SeguroMedico;

    public String toUserFriendlyString(){
        switch(this){
            case SeguroDeVida:
                return "Seguro de Vida";
            case SeguroMedico:
                return "Seguro Médico";
            case SeguroDeVeiculo:
                return "Seguro de Veículo";
            case SeguroDeVeiculoDeMercadorias:
                return "Seguro de Veículo de Mercadorias";
            case SeguroDeVeiculoDeTranspPublico:
                return "Seguro de Veículo de Transporte Público";
            case SeguroDeImovel:
                return "Seguro de Imóvel";
            case SeguroDeImovelComRecheio:
                return "Seguro de Imóvel com recheio";
            default:
                return this.name();
        }
    }

    public static Collection<String> tiposOferecidos(){
        Collection<String> result = new ArrayList<String>(TipoSeguroOferecido.values().length);
        for(TipoSeguroOferecido tso : TipoSeguroOferecido.values()){
            result.add(tso.toUserFriendlyString());
        }
        return result;
    }
}
