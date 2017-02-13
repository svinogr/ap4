package ap.services.servicesimpl;

import ap.dao.ExersiceDAO;
import ap.dao.TryDAO;
import ap.entity.EntityForXML.TryXML;
import ap.entity.Exercise;
import ap.entity.Try;
import ap.services.TryService;
import ap.services.UserServices;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public class TryServiceImpl implements TryService {
    @Autowired
    TryDAO tryDAO;
    @Autowired
    ExersiceDAO exersiceDAO;
    @Autowired
    UserServices userServices;


    @Override
    @Transactional
    public TryXML createTry(TryXML tryXML) throws HibernateException {
        if (exersiceDAO.checkItBD(tryXML.getExerciseId())) {
            Exercise exercise = exersiceDAO.getById(tryXML.getExerciseId());
            if (userServices.allow(exercise.getParentid().getParentid().getId())){
                Try tries = new Try();
                tries.setDone(false);
                tries.setWeight(tryXML.getWeight());
                tries.setRepeat(tryXML.getRepeat());
                tries.setParentid(exercise);
                try {
                    tries.setPosition(exercise.getTryList().size());
                }catch (NullPointerException e){
                    tries.setPosition(0);
                }
                tryXML.setTryId(tryDAO.add(tries));
                return tryXML;
            }
        }
        return null;
    }

    @Override
    public boolean changeTry(TryXML tryXML) {
        return false;
    }

    @Override
    public boolean deleteTry(int id) {
        return false;
    }

    @Override
    public TryXML getTry(int id) {
        return null;
    }

    @Override
    public TryXML validTryXML(TryXML tryXML) {
        String weigth = String.valueOf(tryXML.getWeight());
        System.out.println("wei1"+weigth);
        String repeat = String.valueOf(tryXML.getRepeat());
        System.out.println("rep1"+repeat);
        if(!weigth.matches("[0-9]{1,13}(\\.[0-9]*)?")){
            tryXML.setWeight(0);
            System.out.println("wei1"+tryXML.getWeight());
        }
        if(!repeat.matches("\\d+")){
            tryXML.setRepeat(0);
            System.out.println("rep2"+tryXML.getRepeat());
        }

        return tryXML;
    }
}
