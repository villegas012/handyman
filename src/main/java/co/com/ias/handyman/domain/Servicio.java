package co.com.ias.handyman.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbl_servicio")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_servicio", unique = true, nullable = false)
    private String idServicio;
    private String descripcion;
    private String estado;
    @ManyToMany(targetEntity = Tecnico.class)
    private List<Tecnico> tecnicos;


    @JsonCreator
    public Servicio() {}


}
