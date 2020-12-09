package co.com.ias.handyman.services;

import co.com.ias.handyman.domain.Servicio;
import co.com.ias.handyman.domain.Tecnico;
import co.com.ias.handyman.model.RelacionTecnicoServicio;
import co.com.ias.handyman.repository.ServicioRepositorio;
import co.com.ias.handyman.repository.TenicoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoServicio {
    private final TenicoRepositorio tenicoRepositorio;
    private final ServicioRepositorio servicioRepositorio;

    public TecnicoServicio(TenicoRepositorio tenicoRepositorio, ServicioRepositorio servicioRepositorio) {
        this.tenicoRepositorio = tenicoRepositorio;
        this.servicioRepositorio = servicioRepositorio;
    }

    public List<Tecnico> obtenerTodosTecnicos() {
        return tenicoRepositorio.findAll();
    }

    public Tecnico guardarTecnico(Tecnico tecnico) {
        Tecnico tecnicoBD = tenicoRepositorio.findByIdTecnico(tecnico.getIdTecnico());
        if( tecnicoBD != null ) {
            return null;
        }
        return tenicoRepositorio.save(tecnico);
    }

    public Tecnico obtenerUnTecnico(String idTecnico){
        return tenicoRepositorio.findByIdTecnico(idTecnico);
    }

//    public Nomina obtenerNomina(String idTecnico, String nomina){
//
//    }

    public Tecnico actualizarTecnicoServicios(RelacionTecnicoServicio relacion){
        Tecnico tecnicoBD = tenicoRepositorio.findByIdTecnico(relacion.getIdTecnico());
        Servicio servicioBD = servicioRepositorio.findByIdServicio(relacion.getIdServicio());
        List<Servicio> listServicios = tecnicoBD.getServicios();
        boolean existeServicio = listServicios.contains(servicioBD);
        if( tecnicoBD == null || servicioBD == null || existeServicio ) {
            return null;
        }
            servicioBD.setFechaInicio(relacion.getFechaInicio());
            servicioBD.setFechaFin(relacion.getFechaFin());
            listServicios.add(servicioBD);
            tecnicoBD.setServicios(listServicios);

        return tenicoRepositorio.save(tecnicoBD);
    }
}
