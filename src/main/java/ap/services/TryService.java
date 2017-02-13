package ap.services;

import ap.entity.EntityForXML.TryXML;
import org.springframework.validation.BindingResult;

public interface TryService {
    TryXML  createTry(TryXML tryXML);
    boolean changeTry(TryXML tryXML);
    boolean deleteTry(int id);
    TryXML getTry(int id);
    TryXML validTryXML(TryXML tryXML);

}
