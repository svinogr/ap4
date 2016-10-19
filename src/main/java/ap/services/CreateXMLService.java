package ap.services;

import ap.entity.User;
import ap.entity.Xmlable;

import java.io.File;
import java.io.StringWriter;

public interface CreateXMLService {

    public StringWriter getXML(User user);
}
