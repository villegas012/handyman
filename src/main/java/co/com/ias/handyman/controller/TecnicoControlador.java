package co.com.ias.handyman.controller;

import co.com.ias.handyman.domain.Tecnico;
import co.com.ias.handyman.model.ErrorMessage;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoControlador {
    private final TecnicoServicio tecnicoServicio;

    public TecnicoControlador(TecnicoServicio tecnicoServicio) {
        this.tecnicoServicio = tecnicoServicio;
    }

    @GetMapping
    public ResponseEntity<List<Tecnico>> listaTecnicos() {
        List<Tecnico> tecnicos = tecnicoServicio.obtenerTodosTecnicos();
        if (tecnicos == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tecnicos);
    }

    @PostMapping
    public ResponseEntity<Tecnico> guardarTecnico(@Valid @RequestBody Tecnico tecnico, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Tecnico tecnicoBD = tecnicoServicio.guardarTecnico(tecnico);
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
