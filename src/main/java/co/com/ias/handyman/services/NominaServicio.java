package co.com.ias.handyman.services;

import co.com.ias.handyman.domain.Servicio;
import co.com.ias.handyman.domain.Tecnico;
import co.com.ias.handyman.model.Nomina;
import co.com.ias.handyman.repository.TenicoRepositorio;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class NominaServicio {
    private final TenicoRepositorio tenicoRepositorio;
    private Nomina nomina;

    public NominaServicio(TenicoRepositorio tenicoRepositorio){
        this.tenicoRepositorio = tenicoRepositorio;
        this.nomina = new Nomina();
    }



    public List<Servicio> filtrarSemana(String idTecnico, Integer semana){
        Calendar calHoraFin = Calendar.getInstance(Locale.US);
        Tecnico tecnicoBD = tenicoRepositorio.findByIdTecnico(idTecnico);
        List<Servicio> listServicios = tecnicoBD.getServicios();
        List<Servicio> listaFechasSemana = new ArrayList<>();
        listServicios.forEach(servicio -> {
            Date horaFin = new Date(servicio.getFechaFin());
            calHoraFin.setTime(horaFin);
            if(calHoraFin.get(Calendar.WEEK_OF_YEAR) == semana){
                listaFechasSemana.add(servicio);
            }
        });
        return listaFechasSemana;
    }



}
