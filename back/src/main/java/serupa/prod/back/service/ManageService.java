package serupa.prod.back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import serupa.prod.back.Repository.ManageRepository;
import serupa.prod.back.entites.Manage;

@Service
public class ManageService {
    @Autowired
    private ManageRepository manageRepository;

    public List<Manage> findAll() {
        return manageRepository.findAll();
    }
    
    public void alimenteBase() {
    	manageRepository.save(new Manage("OD","ORDREDEBIT","/home/jarvis/OrdreDebit"));
    	manageRepository.save(new Manage("TS","BOCAD","/home/jarvis/Bocad"));
    }
}
