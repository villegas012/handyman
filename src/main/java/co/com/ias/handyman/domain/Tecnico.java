package co.com.ias.handyman.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbl_tecnico")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Tecnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El Nit/Documento no puede ser vacio")
    @Column(name = "id_tecnico", unique = true, nullable = false)
    private String idTecnico;
    private String nombre;
    private String apellido;
    @ManyToMany(targetEntity = Servicio.class)
    private List<Servicio> servicios;

    public Tecnico() { }

}
