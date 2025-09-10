package es.ucm.fdi.iw.business.mapper;

import es.ucm.fdi.iw.business.dto.UserDTO;
import es.ucm.fdi.iw.business.model.User;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-26T09:35:44+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO entityToDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        if ( entity.getAvailableMoney() != null ) {
            userDTO.availableMoney( entity.getAvailableMoney() );
        }
        userDTO.deliveryAddress( entity.getDeliveryAddress() );
        userDTO.enabled( entity.isEnabled() );
        userDTO.firstName( entity.getFirstName() );
        userDTO.id( entity.getId() );
        userDTO.lastName( entity.getLastName() );
        userDTO.password( entity.getPassword() );
        userDTO.roles( entity.getRoles() );
        userDTO.username( entity.getUsername() );

        return userDTO.build();
    }
}
