package co.com.ias.handyman.services;

import co.com.ias.handyman.domain.Tecnico;
import co.com.ias.handyman.repository.TenicoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoServicio {
    private final TenicoRepositorio tenicoRepositorio;

    public TecnicoServicio(TenicoRepositorio tenicoRepositorio) {
        this.tenicoRepositorio = tenicoRepositorio;
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
}
