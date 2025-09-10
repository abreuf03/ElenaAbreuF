package es.ucm.fdi.iw.business.mapper;

import es.ucm.fdi.iw.business.dto.MessageDTO;
import es.ucm.fdi.iw.business.model.Message;
import es.ucm.fdi.iw.business.model.User;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-26T09:35:44+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
public class MessageMapperImpl implements MessageMapper {

    @Override
    public MessageDTO entityToDto(Message message) {
        if ( message == null ) {
            return null;
        }

        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setSenderId( messageSenderId( message ) );
        messageDTO.setRecipientId( messageRecipientId( message ) );
        messageDTO.setSenderName( messageSenderUsername( message ) );
        messageDTO.setRecipientName( messageRecipientUsername( message ) );
        messageDTO.setDateRead( message.getDateRead() );
        messageDTO.setDateSent( message.getDateSent() );
        messageDTO.setId( message.getId() );
        messageDTO.setText( message.getText() );

        return messageDTO;
    }

    private long messageSenderId(Message message) {
        User sender = message.getSender();
        if ( sender == null ) {
            return 0L;
        }
        return sender.getId();
    }

    private long messageRecipientId(Message message) {
        User recipient = message.getRecipient();
        if ( recipient == null ) {
            return 0L;
        }
        return recipient.getId();
    }

    private String messageSenderUsername(Message message) {
        User sender = message.getSender();
        if ( sender == null ) {
            return null;
        }
        return sender.getUsername();
    }

    private String messageRecipientUsername(Message message) {
        User recipient = message.getRecipient();
        if ( recipient == null ) {
            return null;
        }
        return recipient.getUsername();
    }
}
