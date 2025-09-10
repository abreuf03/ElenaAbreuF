package es.ucm.fdi.iw.business.mapper;

import es.ucm.fdi.iw.business.dto.PujaDTO;
import es.ucm.fdi.iw.business.model.Puja;
import es.ucm.fdi.iw.business.model.PujaEmbed;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-26T09:35:44+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
public class PujaMapperImpl implements PujaMapper {

    @Override
    public PujaDTO pujaToPujaDTO(Puja puja) {
        if ( puja == null ) {
            return null;
        }

        PujaDTO.PujaDTOBuilder pujaDTO = PujaDTO.builder();

        pujaDTO.usuarioId( pujaIdUsuarioId( puja ) );
        pujaDTO.subastaId( pujaIdSubastaId( puja ) );
        pujaDTO.comentario( puja.getComentario() );
        pujaDTO.dineroPujado( puja.getDineroPujado() );
        pujaDTO.fecha( puja.getFecha() );
        pujaDTO.puntuacion( puja.getPuntuacion() );

        return pujaDTO.build();
    }

    @Override
    public Puja pujaDTOToPuja(PujaDTO pujaDTO) {
        if ( pujaDTO == null ) {
            return null;
        }

        Puja puja = new Puja();

        puja.setComentario( pujaDTO.getComentario() );
        puja.setDineroPujado( pujaDTO.getDineroPujado() );
        puja.setFecha( pujaDTO.getFecha() );
        puja.setPuntuacion( pujaDTO.getPuntuacion() );

        return puja;
    }

    private long pujaIdUsuarioId(Puja puja) {
        PujaEmbed id = puja.getId();
        if ( id == null ) {
            return 0L;
        }
        return id.getUsuarioId();
    }

    private long pujaIdSubastaId(Puja puja) {
        PujaEmbed id = puja.getId();
        if ( id == null ) {
            return 0L;
        }
        return id.getSubastaId();
    }
}
