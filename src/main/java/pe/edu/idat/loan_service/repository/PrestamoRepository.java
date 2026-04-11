package pe.edu.idat.loan_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.idat.loan_service.entity.Prestamo;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

    List<Prestamo> findByIdUsuario(Integer idUsuario);
    List<Prestamo> findByEstado(String estado);

    @Query("SELECT p FROM Prestamo p WHERE p.idUsuario = :idUsuario AND p.estado = 'ACTIVO'")
    List<Prestamo> findPrestamosActivosByUsuario(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT COUNT(p) FROM Prestamo p WHERE p.idUsuario = :idUsuario AND p.estado = 'ACTIVO'")
    long countPrestamosActivosByUsuario(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT p FROM Prestamo p WHERE p.idLibro = :idLibro AND p.estado = 'ACTIVO'")
    Optional<Prestamo> findPrestamoActivoByLibro(@Param("idLibro") Integer idLibro);
}