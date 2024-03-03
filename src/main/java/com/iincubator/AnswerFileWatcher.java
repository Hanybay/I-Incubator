package com.iincubator;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;


@Component
@Data
public class AnswerFileWatcher {

    // = Paths.get("/home/hany/FAC/M1/S7/DDS/Projet/DDS");

    @Inject
    XmlFileTreater parser;

    // This list is to allow the controller to get the data found in the json file
    private List<Map<String, Object>> jsonData; 

//     public void watchDirectory(String path) throws ParserConfigurationException, SAXException{
//         try {
//             WatchService watchService = FileSystems.getDefault().newWatchService();
//             Path directoryPath = Path.of(path);

//             directoryPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

//             while(true){
//                 WatchKey key = watchService.take();
//                 if(key != null){
//                 for(WatchEvent<?> event : key.pollEvents()){
//                     if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
//                         System.out.println("Nouveau fichier créé : " + event.context());
//                         String newFileName = event.context().toString();
//                         String fullFilePath = directoryPath.resolve(newFileName).toString();
//                         jsonData = readJsonFromFile(fullFilePath);
//                     }
//                     key.reset();
//                 }
//             }
//         }    
//     } 
//     catch (IOException | InterruptedException e) {
//         e.printStackTrace();
//     }
// }


public List<Map<String, Object>> watchDirectory(String path) throws IOException {
    WatchService watchService = FileSystems.getDefault().newWatchService();
    Path directoryPath = Path.of(path);

    directoryPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

    while (true) {
        WatchKey key;
        try {
            key = watchService.take();
        } 
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return Collections.emptyList();
        }
        if (key != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                    String newFileName = event.context().toString();
                    String fullFilePath = directoryPath.resolve(newFileName).toString();
                    System.err.println("FROM ANSWER FILE WATCHER "+fullFilePath);

                    if (newFileName.endsWith(".json")) {
                        try {
                            Thread.sleep(500);
                        } 
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return readJsonFromFile(fullFilePath);
                    }
                }
            }
            key.reset();
        }
    }
}

public List<Map<String, Object>> getJsonData() {
    return jsonData;
}

public static List<Map<String, Object>> readJsonFromFile(String filePath) {
        // ObjectMapper from Jackson to read JSON
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Reading data from JSON file
            File file = new File(filePath);
            System.err.println("LE FICHIER SAPPELLE " + filePath);
            if(file.canRead()){
                System.err.println("LE FICHIER EST LISIBLE");
            }
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            System.err.println("THIS IS THE SIIIZE " + lines.size());
            byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
            if (fileBytes.length == 0) {
                System.err.println("File is empty: " + filePath);
                return Collections.emptyList();
            }
            else{
                System.err.println("SIZE IN BYTES IS " + fileBytes.length);
            }
            List<Map<String, Object>> data = objectMapper.readValue(file, new TypeReference<List<Map<String, Object>>>() {});
            if(data.isEmpty()){
                System.err.println("THE LIST DATA IS EMPTY");
            }
            else{
                System.err.println("DATA HAS BEEN FILLED");
                return data;
            }
            return data;
        } 
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
