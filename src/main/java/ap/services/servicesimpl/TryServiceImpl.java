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
        try {
            if (tryDAO.checkItBD(tryXML.getTryId())){
                Try tries= tryDAO.getById(tryXML.getTryId());
                double weight = tryXML.getWeight();
                int repeat  = tryXML.getRepeat();
                if (userServices.allow(tries.getParentid().getParentid().getParentid().getId())){
                    if(weight>0){
                        tries.setWeight(weight);
                    }
                    if(repeat>0){
                        tries.setRepeat(repeat);
                    }
                    tryDAO.update(tries);
                    return true;
                }
            }
        } catch (HibernateException e) {
            return false;
        }
        return false;
    }



    @Override
    @Transactional
    public boolean deleteTry(int id) {
        try {
            if (tryDAO.checkItBD(id)) {
                Try tries = tryDAO.getById(id);
                if (userServices.allow(tries.getParentid().getParentid().getParentid().getId())) {
                    tryDAO.delete(tries);
                    return true;
                }
            }
        }catch (HibernateException e){
            return false;
        }
        return false;
    }

    @Override
    @Transactional
    public TryXML getTry(int id) {
        if (tryDAO.checkItBD(id)) {
            Try tries = tryDAO.getById(id);
            return new TryXML(tries);
        }
        return null;
    }

    @Override
    public TryXML validTryXML(TryXML tryXML) {
        String done = String.valueOf(tryXML.isDone());
        String weigth = String.valueOf(tryXML.getWeight());
        String repeat = String.valueOf(tryXML.getRepeat());
        if(!weigth.matches("-?[0-9]{1,13}(\\.[0-9]*)?")){
            tryXML.setWeight(0);
        }
        if(!repeat.matches("-?\\d+")){
            tryXML.setRepeat(0);
        }
        return tryXML;
    }

    @Override
    public TryXML validDoTryXML(TryXML tryXML) {
        try {
            boolean done = new Boolean(tryXML.isDone());
            return tryXML;
        }catch (Exception e){
            return null;
        }


    }

    @Override
    @Transactional
    public boolean done(TryXML tryXML) {
        if(tryDAO.checkItBD(tryXML.getTryId())) {
            boolean done = tryXML.isDone();
            Try tries = tryDAO.getById(tryXML.getTryId());
            if(userServices.allow(tries.getParentid().getParentid().getParentid().getId())){
               tries.setDone(done);
                tryDAO.update(tries);
                return true;
            }

        }
        return false;
    }
}
