package pe.edu.idat.loan_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import pe.edu.idat.loan_service.dto.request.PrestamoRequestDTO;
import pe.edu.idat.loan_service.dto.response.PrestamoResponseDTO;
import pe.edu.idat.loan_service.entity.Prestamo;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PrestamoMapper {

    @Mapping(target = "idPrestamo", ignore = true)
    @Mapping(target = "fechaPrestamo", ignore = true)
    @Mapping(target = "fechaLimite", ignore = true)
    @Mapping(target = "fechaDevolucion", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "multa", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "nombreUsuario", ignore = true)
    @Mapping(target = "tituloLibro", ignore = true)
    Prestamo requestToEntity(PrestamoRequestDTO dto);

    PrestamoResponseDTO toResponseDTO(Prestamo prestamo);
    List<PrestamoResponseDTO> toResponseDTOList(List<Prestamo> prestamos);
}