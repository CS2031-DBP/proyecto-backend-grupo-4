package com.proyecto.utec_roomie.request.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class SolicitudStatusConverter implements AttributeConverter<SolicitudStatus, String> {
    @Override
    public String convertToDatabaseColumn(SolicitudStatus status) {
        if(status == null){
            return null;
        }
        return status.getCode();
    }

    @Override
    public SolicitudStatus convertToEntityAttribute(String code) {
        if(code == null){
            return null;
        }
        return Stream.of(SolicitudStatus.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

}
