package ap.services;

import ap.entity.Xmlable;

import java.io.StringWriter;

public interface CreateExerciseXMLService {
    StringWriter getXML(Xmlable xmlable);
}
