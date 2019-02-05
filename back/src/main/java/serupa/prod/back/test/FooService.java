package serupa.prod.back.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class FooService {
    @Autowired
    private FooRepository fooRepository;

//    @Autowired
//    private SessionFactory sessionFactory;
    
    public List<Foo> findAll() {
        return fooRepository.findAll();
    }

    @Transactional
    public void create(String name) {
        fooRepository.findAll();
        // Set breakpoint here to confirm the fail over behaviour during a transaction
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
        Foo entity = new Foo();
        entity.setName(name);
//        session.save( entity );
//        session.getTransaction().commit();
//        session.close();

        fooRepository.save(entity);
    }
}