package co.com.ias.handyman.services;

import co.com.ias.handyman.domain.Servicio;
import co.com.ias.handyman.repository.ServicioRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioServicio {
    private final ServicioRepositorio servicioRepositorio;

    public ServicioServicio(ServicioRepositorio servicioRepositorio) {
        this.servicioRepositorio = servicioRepositorio;
    }

    public List<Servicio> obtenerTodoServicios(){
        return servicioRepositorio.findAll();
    }

    public Servicio guardarServicio(Servicio servicio) {
        Servicio servicioBD = servicioRepositorio.findByIdServicio(servicio.getIdServicio());
        if ( servicioBD != null ) {
            return null;
        }
        servicio.setEstado("Creado");
        return servicioRepositorio.save(servicio);
    }
}
