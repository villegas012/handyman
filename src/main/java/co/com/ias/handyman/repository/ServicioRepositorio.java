package co.com.ias.handyman.repository;

import co.com.ias.handyman.domain.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepositorio extends JpaRepository<Servicio, Long> {
    Servicio findByIdServicio(String idServicio);
}
