package com.iincubator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
// import org.xml.sax.SAXException;
import org.springframework.web.bind.annotation.RequestParam;

import com.iincubator.AnswerFileWatcher;
import com.iincubator.FileWatcherBeta;
import com.iincubator.JsonToKeyValueConverter;
import com.iincubator.Entities.Entreprise;
import com.iincubator.Entities.Message;
import com.iincubator.ParserRestaurant.Contenu;
import com.iincubator.Repositories.EntrepriseRepo;
import com.iincubator.Repositories.MessageRepository;

import java.io.File;
// import com.iincubator.FileWatcher;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
// import javax.inject.Inject;
// import javax.xml.parsers.ParserConfigurationException;
import java.util.Map;

import javax.inject.Inject;

@Controller
public class IndexController{

    @Inject
    AnswerFileWatcher directoryWatcher;

    @Inject
    EntrepriseRepo companyRepo;

    @Inject
    MessageRepository messRepo;

    @Inject
    JsonToKeyValueConverter jsonConverter;

    // @Inject FileWatcherBeta fw;

    @GetMapping("/")
    public String root(Model m) {
         List<Entreprise> entreprises = companyRepo.findAll();

         if (!entreprises.isEmpty()) {
            entreprises.remove(0);
        }
        m.addAttribute("entreprises", entreprises);
        return "index";
    }

    @GetMapping("/entreprise")
    public String details(@RequestParam("id") Long entrepriseId, Model model) {

        Entreprise entreprise = companyRepo.findById(entrepriseId).orElse(null);

        List <Message> MessageList = messRepo.findContentBySenderOrganization(entreprise);
        List<String> contentList = new ArrayList<>();
        List<Contenu> listeContenus = new ArrayList<>();
        
        for (Message message : MessageList) {
            contentList.add(message.getContent());
        }
        System.err.println("SIZE OF LIST = " + contentList.size()); 
        for (String jsonContent : contentList) {
            try {
                Contenu contenu = jsonConverter.convertJsonToObject(jsonContent);
                listeContenus.add(contenu);
            } catch (Exception e) {
                // Gérer les éventuelles erreurs de conversion JSON
                e.printStackTrace();
            }
        }
        model.addAttribute("listeContenus", listeContenus);
        // List<Map.Entry<String, Object>> keyValueList = jsonConverter.convertJsonToKeyValue(contentList.get(0));


        // System.out.println("IMPRIME LE MODEL" + model.asMap()); // Imprimez le modèle pour le débogage

        // model.addAttribute("contentList", contentList);

        return "messagescantine";
    }




    // @GetMapping("/")
    // public String root() {
    //     return "index";
    // }

    // @PostMapping("/")
    // public String treatRequest(@RequestParam String email, @RequestParam String choix){
    //     // On ouvre le Writer dans le try pour qu'il soit fermé automatiquement après
    //     try (FileWriter writer = new FileWriter("../requetes.txt") ){
    //         System.err.println(email);
    //         writer.write(email);
    //         writer.write("\n");
    //         writer.write(choix);
    //         writer.close();    
    //     } 
    //     catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return "redirect:/answer";
    // }



//     @GetMapping("/answer")
//     public String waitRequestAnswer(Model m) {
//     List<Map<String, Object>> jsonData = null;
//     try {
//         jsonData = directoryWatcher.watchDirectory("/home/hany/FAC/M1/S7/DDS/Projet/DDS/RequestAnswer");

//     } catch (Exception e) {
//         e.printStackTrace();
//     }

//     if (jsonData != null) {
//         System.err.println("ITS NOT NULL");
//         m.addAttribute("jsonData", jsonData);
//         File f= new File("/home/hany/FAC/M1/S7/DDS/Projet/DDS/RequestAnswer/result.json");
//         f.delete();
//     } else {
//         System.err.println("ITS EMPTY G");
//     }

//     return "answer";
// }
    
}
