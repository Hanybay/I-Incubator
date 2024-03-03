package com.iincubator;

// import java.util.ArrayList;
//import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
//import java.util.Random;

import javax.inject.Inject;

import com.iincubator.Entities.Entreprise;
import com.iincubator.Repositories.EntrepriseRepo;

import org.springframework.stereotype.Service;

/**
 * AddTestData
 */
/* ___GENERATETESTDATA___ */
@Service
public class AddTestData {

    @Inject
    EntrepriseRepo companyRepo; // This will be set automatically (injection) by spring data, with an implementation of UserService
    interface RandomElement {
         <T> T gen(List<T> l);
    }

    public void generateTestData() {
        /* ___GENERATETESTDATA___ */
        /*Adding all companies */
        companyRepo.save(new Entreprise((long)1,"Use'In"));
        companyRepo.save(new Entreprise((long)2,"Entreprise"));
        companyRepo.save(new Entreprise((long)3,"FST"));
        companyRepo.save(new Entreprise((long)4,"Restaurant"));
        companyRepo.save(new Entreprise((long)5,"Cite du design"));

    }

}