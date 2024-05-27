package com.proyecto.utec_roomie.publication.domain;

import com.proyecto.utec_roomie.request.domain.SolicitudStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class PublicacionStatusConverter implements AttributeConverter<PublicacionStatus, String> {
    @Override
    public String convertToDatabaseColumn(PublicacionStatus status) {
        if(status == null){
            return null;
        }
        return status.getCode();
    }

    @Override
    public PublicacionStatus convertToEntityAttribute(String code) {
        if(code == null){
            return null;
        }
        return Stream.of(PublicacionStatus.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
