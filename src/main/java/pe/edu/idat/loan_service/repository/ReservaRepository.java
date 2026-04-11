package pe.edu.idat.loan_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.idat.loan_service.entity.Reserva;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    List<Reserva> findByIdUsuario(Integer idUsuario);
    List<Reserva> findByEstado(String estado);

    @Query("SELECT r FROM Reserva r WHERE r.idUsuario = :idUsuario AND r.idLibro = :idLibro")
    Optional<Reserva> findByUsuarioAndLibro(@Param("idUsuario") Integer idUsuario, 
                                            @Param("idLibro") Integer idLibro);

    @Query("SELECT r FROM Reserva r WHERE r.idLibro = :idLibro AND r.estado = 'PENDIENTE' ORDER BY r.fechaReserva ASC")
    List<Reserva> findReservasPendientesByLibro(@Param("idLibro") Integer idLibro);
}