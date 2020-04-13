package com.gobs.oauthservice.domain.entities.oauth;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fasterxml.jackson.databind.ObjectMapper;

@Converter(autoApply = true)
public class OAuthClientAdditionalInformationConverter
        implements AttributeConverter<OAuthClientAdditionalInformation, String> {

    @Override
    public String convertToDatabaseColumn(OAuthClientAdditionalInformation entityValue) {
        ObjectMapper mapper = new ObjectMapper();
        String additionalInformation = "{}";
        if (entityValue == null)
            return null;
        try {
            additionalInformation = mapper.writeValueAsString(entityValue);
        } catch (Exception e) {
            additionalInformation = "{}";
        }
        return additionalInformation;
    }

    @Override
    public OAuthClientAdditionalInformation convertToEntityAttribute(String databaseValue) {
        ObjectMapper mapper = new ObjectMapper();
        OAuthClientAdditionalInformation additionalInformation = null;
        if (databaseValue == null)
            return null;
        try {
            additionalInformation = mapper.readValue(databaseValue, OAuthClientAdditionalInformation.class);
        } catch (Exception e) {
        }
        return additionalInformation;

    }
}