package co.com.ias.handyman.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Nomina {
    private String idTecnico;
    private Integer horasTrabajadasSemana;
    private Integer horasNormales;
    private Integer horasExtras;
    private Integer horasNocturnas;
    private Integer horasDominicales;
    private Long nomina;
    public Nomina(){ }

}
