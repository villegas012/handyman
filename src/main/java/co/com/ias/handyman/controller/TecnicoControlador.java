package co.com.ias.handyman.controller;

import co.com.ias.handyman.domain.Servicio;
import co.com.ias.handyman.domain.Tecnico;
import co.com.ias.handyman.model.ErrorMessage;
import co.com.ias.handyman.model.Nomina;
import co.com.ias.handyman.model.RelacionTecnicoServicio;
import co.com.ias.handyman.services.NominaServicio;
import co.com.ias.handyman.services.TecnicoServicio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RestController
@RequestMapping(value = "/tecnico")
public class TecnicoControlador {
    private final TecnicoServicio tecnicoServicio;
    private final NominaServicio nominaServicio;


    public TecnicoControlador(TecnicoServicio tecnicoServicio, NominaServicio nominaServicio) {
        this.tecnicoServicio = tecnicoServicio;
        this.nominaServicio = nominaServicio;
    }


    @GetMapping
    public ResponseEntity<List<Tecnico>> listaTecnicos() {
        List<Tecnico> tecnicos = tecnicoServicio.obtenerTodosTecnicos();
        if (tecnicos == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tecnicos);
    }

    @GetMapping(value = "/{idTecnico}")
    public ResponseEntity<Tecnico> obtenerUnTecnico(@PathVariable("idTecnico") String idTecnico){
        Tecnico tecnicoBD = tecnicoServicio.obtenerUnTecnico(idTecnico);
        if(tecnicoBD == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tecnicoBD);
    }

    @GetMapping(value = "/{idTecnico}/{semana}")
    public ResponseEntity<List<Servicio>> filtrarSemana(@PathVariable("idTecnico") String idTecnico, @PathVariable("semana") Integer semana){
        List<Servicio> listFechas = nominaServicio.filtrarSemana(idTecnico,semana);
        if(listFechas == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listFechas);
    }

    @PostMapping
    public ResponseEntity<Tecnico> guardarTecnico(@Valid @RequestBody Tecnico tecnico, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Tecnico tecnicoBD = tecnicoServicio.guardarTecnico(tecnico);
        return ResponseEntity.ok(tecnicoBD);
    }

    @PutMapping
    public ResponseEntity<Tecnico> actualizarTecnicoServicios(@RequestBody RelacionTecnicoServicio relacion){
        Tecnico tecnicoBD = tecnicoServicio.actualizarTecnicoServicios(relacion);
        if (tecnicoBD == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tecnicoBD);
    }


    private String formatMessage(BindingResult result) {
        List<Map<String, String>> messages = result.getFieldErrors().stream()
                .map( err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(messages).build();
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

}
