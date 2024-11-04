package com.example.utils;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class convertidorFecha implements JsonDeserializer<Date> {
    private static final String[] dateFormats = {
        "MMM d, yyyy, h:mm:ss a",  // Formato que esperas
        "dd-MM-yyyy",               // Otro formato por si acaso
        "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", // Formato ISO 8601 con milisegundos
        // Agrega otros formatos si es necesario
    };

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String dateStr = json.getAsString();
        
        for (String format : dateFormats) {
            try {
                return new SimpleDateFormat(format).parse(dateStr);
            } catch (ParseException ignored) {
                // Intentar con el siguiente formato
            }
        }

        throw new JsonParseException("Failed to parse Date: " + dateStr);
    }
}
