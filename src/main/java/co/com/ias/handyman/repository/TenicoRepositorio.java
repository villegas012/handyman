package co.com.ias.handyman.repository;

import co.com.ias.handyman.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenicoRepositorio extends JpaRepository<Tecnico, Long> {
    Tecnico findByIdTecnico(String id);
}
