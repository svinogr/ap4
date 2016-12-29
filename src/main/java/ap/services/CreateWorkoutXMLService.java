package ap.services;


import ap.entity.Xmlable;
import java.io.StringWriter;

public interface CreateWorkoutXMLService {
StringWriter getXML(Xmlable xmlable);

}
