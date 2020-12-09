package co.com.ias.handyman.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RelacionTecnicoServicio {
    private String idTecnico;
    private String idServicio;
    private Long fechaInicio;
    private Long fechaFin;

    public RelacionTecnicoServicio(){ }
}
