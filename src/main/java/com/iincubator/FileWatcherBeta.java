package com.iincubator;
import java.io.IOException;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import javax.inject.Inject;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.iincubator.ParserRestaurant.AdressePostale;
import com.iincubator.ParserRestaurant.Contenu;
import com.iincubator.ParserRestaurant.Dessert;
import com.iincubator.ParserRestaurant.Entree;
import com.iincubator.ParserRestaurant.Fichier;
import com.iincubator.ParserRestaurant.MessageRestau;
import com.iincubator.ParserRestaurant.Messages;
import com.iincubator.ParserRestaurant.Plat;
import com.iincubator.ParserRestaurant.PresentationPrestation;
import com.iincubator.ParserRestaurant.Signature;
import com.iincubator.ParserRestaurant.Menu;

import jakarta.annotation.PostConstruct;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FileWatcherBeta {
    
    @Inject
    XmlFileTreater xtreater;
    
    private static final String DIRECTORY_PATH = "/home/hany/FAC/M1/S7/DDS/Projet/DDS"; //"C:\\Users\\Amin Akkouche\\Documents\\GitHub\\iincubator\\DDS";//DDS\iincubator\home\hany\FAC\M1\S7\DDS\Projet\DDS\Restau_UseIn2.xml
    private WatchService watchService;

    
    @PostConstruct
    public void init() {
        try {
            this.watchService = FileSystems.getDefault().newWatchService();
            Path directoryPath = Path.of(DIRECTORY_PATH);
            directoryPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    @Scheduled(cron = "*/30 * * * * *")
    public void checkDirectory() {
        System.err.println("JE RENTRE DANS CHECK");
        try {
            for (WatchKey key ; (key = watchService.poll()) != null; ) {
                System.err.println("------------------ Je rentre dans la key -----------------------------");
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                        // ICI ON VA PARSER LE FICHIER XML
                        System.out.println("Vérification du répertoire toutes les 30 sec.");
                        System.out.println("Nouveau fichier créé : " + event.context());
                        try {
                            JAXBContext jaxbContext = JAXBContext.newInstance(Fichier.class, Messages.class,MessageRestau.class, Contenu.class, AdressePostale.class, Dessert.class, Entree.class,Menu.class,Plat.class,PresentationPrestation.class,Signature.class);
                            // Créer un objet Unmarshaller
                            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                            // Spécifier le chemin du fichier XML de test
                            File xmlFile = new File(DIRECTORY_PATH + "/" + event.context().toString());
                            String filename = DIRECTORY_PATH + "/" + event.context().toString();
                            Path filePath = Paths.get(DIRECTORY_PATH, event.context().toString());

                            long fileSizeInBytes = Files.size(filePath);

                            if(fileSizeInBytes <= 10000){
                                // System.err.println("LA TAILLE EN KILO BYYYTE EST " + fileSizeInBytes);
                                Fichier fichier = (Fichier) unmarshaller.unmarshal(xmlFile);
                                // System.out.println("Objet désérialisé : " + fichier);
                                // System.err.println("ON PARSAME LE CONTENU DE " + event.context().toString());
                                // Access to deserialized data
                                if (fichier.getMessages() != null && fichier.getMessages().getMessageList() != null) {
                                    for (MessageRestau messageRestau : fichier.getMessages().getMessageList()) {
                                        if (messageRestau.getContenu() != null) {
                                            String res = ObjectToJson(messageRestau.getContenu());
                                            xtreater.xmlParser(filename, res);
                                            System.err.println("JAFFICHE LE JSON MON GARS \n" + res + "\n ET MTN J'AFFICHE LA TAILLE " + res.length());
                                        } 
                                        else {
                                            System.err.println("NOOOOOOOOOON");
                                        }
                                    }
                                }
                            }
                            else{
                                System.err.println("FILE IS TOO VOLUMINOUS");
                            }
                            xmlFile.delete();
                        } 
                        catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
                key.reset();
            }
        } 
        catch (ClosedWatchServiceException e) {
        }
    }


    public String ObjectToJson(Contenu content){
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContent = "";
        try {
            jsonContent = objectMapper.writeValueAsString(content);
        } 
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonContent;
    }

        public String ObjectToJson(PresentationPrestation content){
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContent = "";
        try {
            jsonContent = objectMapper.writeValueAsString(content);
        } 
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonContent;
    }
}    