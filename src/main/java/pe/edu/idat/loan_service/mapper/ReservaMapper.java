package pe.edu.idat.loan_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import pe.edu.idat.loan_service.dto.request.ReservaRequestDTO;
import pe.edu.idat.loan_service.dto.response.ReservaResponseDTO;
import pe.edu.idat.loan_service.entity.Reserva;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)

public interface ReservaMapper {

	@Mapping(target = "idReserva", ignore = true)
    @Mapping(target = "fechaReserva", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "nombreUsuario", ignore = true)
    @Mapping(target = "tituloLibro", ignore = true)
    Reserva requestToEntity(ReservaRequestDTO dto);

    ReservaResponseDTO toResponseDTO(Reserva reserva);
    List<ReservaResponseDTO> toResponseDTOList(List<Reserva> reservas);
}