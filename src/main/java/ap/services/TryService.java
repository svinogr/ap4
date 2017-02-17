package ap.services;

import ap.entity.EntityForXML.TryXML;

public interface TryService {
    TryXML createTry(TryXML tryXML);

    boolean changeTry(TryXML tryXML);

    boolean deleteTry(int id);

    TryXML getTry(int id);

    TryXML validTryXML(TryXML tryXML);

    TryXML validDoTryXML(TryXML tryXML);

    boolean done(TryXML tryXML);

}
