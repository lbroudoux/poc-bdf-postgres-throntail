package fr.bdf.exa.poc.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.opentracing.Traced;

import fr.bdf.exa.poc.dao.PocDao;
import fr.bdf.exa.poc.domain.PocEntiry;

@ApplicationScoped
public class PostgresqlService {

    @Inject
    private PocDao pocDao;

    public int getCount() {

        List<PocEntiry> pocEntiries = pocDao.findAll();

        if (null != pocEntiries && !pocEntiries.isEmpty()) {
            return pocEntiries.size();
        } else {
            return 0;
        }

    }

    public void createPoc(PocEntiry poc) {
        pocDao.create(poc);
    }

    @Traced
    public void deletePoc(Integer entityId) {

        pocDao.deleteById(entityId);
    }

    public void updatePoc(PocEntiry poc) {
        pocDao.update(poc);

    }

    public PocEntiry getPoc(Integer id){

        PocEntiry pocEntiry = pocDao.findOne(id);

        return pocEntiry;

    }

}
