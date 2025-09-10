package es.ucm.fdi.iw.business.mapper;

import es.ucm.fdi.iw.business.dto.CreateProductDTO;
import es.ucm.fdi.iw.business.dto.ProductDTO;
import es.ucm.fdi.iw.business.dto.SubastaDTO;
import es.ucm.fdi.iw.business.model.Subasta;
import es.ucm.fdi.iw.business.model.User;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-26T09:35:43+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
public class SubastaMapperImpl implements SubastaMapper {

    @Override
    public ProductDTO subastaToProductDTO(Subasta subasta) {
        if ( subasta == null ) {
            return null;
        }

        ProductDTO.ProductDTOBuilder productDTO = ProductDTO.builder();

        productDTO.creadorUserId( subastaCreadorId( subasta ) );
        productDTO.creadorUsername( subastaCreadorUsername( subasta ) );
        productDTO.estadoSubasta( subasta.getEstado() );
        productDTO.idUserGanador( subastaGanadorId( subasta ) );
        productDTO.comentarioGanador( subasta.getComentarioGanador() );
        productDTO.descripcion( subasta.getDescripcion() );
        productDTO.enabled( subasta.isEnabled() );
        productDTO.fechaFin( subasta.getFechaFin() );
        productDTO.fechaInicio( subasta.getFechaInicio() );
        productDTO.id( subasta.getId() );
        productDTO.maximoPujador( subasta.getMaximoPujador() );
        productDTO.nombre( subasta.getNombre() );
        productDTO.precio( subasta.getPrecio() );
        productDTO.precioActual( subasta.getPrecioActual() );
        productDTO.precioInicial( subasta.getPrecioInicial() );
        productDTO.repartoSubasta( subasta.getRepartoSubasta() );
        productDTO.rutaImagen( subasta.getRutaImagen() );
        productDTO.valoracionGanador( subasta.getValoracionGanador() );

        return productDTO.build();
    }

    @Override
    public Subasta productDTOToSubasta(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Subasta subasta = new Subasta();

        subasta.setComentarioGanador( productDTO.getComentarioGanador() );
        subasta.setDescripcion( productDTO.getDescripcion() );
        subasta.setEnabled( productDTO.isEnabled() );
        subasta.setFechaFin( productDTO.getFechaFin() );
        subasta.setFechaInicio( productDTO.getFechaInicio() );
        subasta.setId( productDTO.getId() );
        subasta.setMaximoPujador( productDTO.getMaximoPujador() );
        subasta.setNombre( productDTO.getNombre() );
        subasta.setPrecio( productDTO.getPrecio() );
        subasta.setPrecioActual( productDTO.getPrecioActual() );
        subasta.setPrecioInicial( productDTO.getPrecioInicial() );
        subasta.setRepartoSubasta( productDTO.getRepartoSubasta() );
        subasta.setRutaImagen( productDTO.getRutaImagen() );
        subasta.setValoracionGanador( productDTO.getValoracionGanador() );

        return subasta;
    }

    @Override
    public ProductDTO createProductDTOToProductDTO(CreateProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        ProductDTO.ProductDTOBuilder productDTO1 = ProductDTO.builder();

        productDTO1.descripcion( productDTO.getDescripcion() );
        if ( productDTO.getFechaFin() != null ) {
            productDTO1.fechaFin( LocalDateTime.parse( productDTO.getFechaFin() ) );
        }
        if ( productDTO.getFechaInicio() != null ) {
            productDTO1.fechaInicio( LocalDateTime.parse( productDTO.getFechaInicio() ) );
        }
        productDTO1.nombre( productDTO.getNombre() );
        productDTO1.precio( productDTO.getPrecio() );

        return productDTO1.build();
    }

    @Override
    public SubastaDTO entityToDto(Subasta subasta) {
        if ( subasta == null ) {
            return null;
        }

        SubastaDTO subastaDTO = new SubastaDTO();

        subastaDTO.setIdUserCreator( subastaCreadorId1( subasta ) );
        subastaDTO.setIdUserGanador( subastaGanadorId( subasta ) );
        subastaDTO.setComentarioGanador( subasta.getComentarioGanador() );
        subastaDTO.setDescripcion( subasta.getDescripcion() );
        subastaDTO.setEnabled( subasta.isEnabled() );
        subastaDTO.setEstado( subasta.getEstado() );
        subastaDTO.setFechaFin( subasta.getFechaFin() );
        subastaDTO.setFechaInicio( subasta.getFechaInicio() );
        subastaDTO.setId( subasta.getId() );
        subastaDTO.setMaximoPujador( subasta.getMaximoPujador() );
        subastaDTO.setNombre( subasta.getNombre() );
        subastaDTO.setPrecio( subasta.getPrecio() );
        subastaDTO.setPrecioActual( subasta.getPrecioActual() );
        subastaDTO.setPrecioInicial( subasta.getPrecioInicial() );
        subastaDTO.setRepartoSubasta( subasta.getRepartoSubasta() );
        subastaDTO.setRutaImagen( subasta.getRutaImagen() );
        subastaDTO.setValoracionGanador( subasta.getValoracionGanador() );

        return subastaDTO;
    }

    private long subastaCreadorId(Subasta subasta) {
        User creador = subasta.getCreador();
        if ( creador == null ) {
            return 0L;
        }
        return creador.getId();
    }

    private String subastaCreadorUsername(Subasta subasta) {
        User creador = subasta.getCreador();
        if ( creador == null ) {
            return null;
        }
        return creador.getUsername();
    }

    private Long subastaGanadorId(Subasta subasta) {
        User ganador = subasta.getGanador();
        if ( ganador == null ) {
            return null;
        }
        return ganador.getId();
    }

    private Long subastaCreadorId1(Subasta subasta) {
        User creador = subasta.getCreador();
        if ( creador == null ) {
            return null;
        }
        return creador.getId();
    }
}
