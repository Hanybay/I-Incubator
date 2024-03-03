package com.iincubator;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.hibernate.mapping.List ;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.iincubator.Entities.Conversation;
import com.iincubator.Entities.Emetteur;
import com.iincubator.Entities.Destinataire;
import com.iincubator.Entities.Entreprise;
import com.iincubator.Entities.Message;
import com.iincubator.Repositories.ConversationRepo;
import com.iincubator.Repositories.DestinataireRepo;
import com.iincubator.Repositories.EmetteurRepo;
import com.iincubator.Repositories.EntrepriseRepo;
import com.iincubator.Repositories.MessageRepository;


@Component
public class XmlFileTreater {

    @Inject
    ConversationRepo convRepo;

    @Inject
    MessageRepository messRepo;

    @Inject
    EmetteurRepo emRepo;

    @Inject
    DestinataireRepo destRepo;

    @Inject
    EntrepriseRepo compRepo;

    private java.util.List <Message> messages;
    
    private Conversation conv;

    private Entreprise entrepriseExp = new Entreprise();

    private Entreprise entrepriseDest = new Entreprise();

    private Emetteur emetteur;
    private java.util.List <Destinataire> destinatairesList = new ArrayList<>();
    
    public void xmlParser(String fileName, String contenu) throws ParserConfigurationException, SAXException, IOException{
        File file = new File(fileName);  
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        document.getDocumentElement().normalize();
        Element root = document.getDocumentElement();
        parseEntete(root);
        parseMessages(root,contenu);
    }

    public String getTextFromElement(Element e,String tag){
        return e.getElementsByTagName(tag).item(0).getTextContent();
    }

    public void parseEntete(Element header){

        //Recupération des balises dans la balise <entete></entete>
        NodeList enteteList = header.getElementsByTagName("entete");
        Element entete = (Element) enteteList.item(0);

        String nbMessage = getTextFromElement(entete, "nbMessage");
        System.out.println("Nombre de messages: " + nbMessage);

        //Récupération de l'emetteur parmi les organisations
        String emetteur = getTextFromElement(entete, "emettrice");
        System.err.println("THE VALUE OF MY BOOLEAN IIIIS " + compRepo.existsByName(emetteur));
        if(!compRepo.existsByName(emetteur)){
            entrepriseExp = new Entreprise(emetteur);
            compRepo.save(entrepriseExp);
        }
        else{
            entrepriseExp = compRepo.findByName(emetteur);
        }
        System.out.println("L'emetteur est :"+emetteur);

        //Récupération du recepteur parmi les organisations
        String destinataire = getTextFromElement(entete, "receptrice");
        if(!compRepo.existsByName(destinataire)){
            entrepriseDest = new Entreprise(destinataire);
            compRepo.save(entrepriseDest);
        }
        else{
            entrepriseDest = compRepo.findByName(destinataire);
        }
        System.out.println("Le destinataire est :"+ destinataire);

        String date = getTextFromElement(entete, "date");
        System.out.println("La date d'envoi est :"+ date);
    }

    public void parseMessages(Element header,String contenu){

        //Recupération des balises dans la balise <messages></messages>
        NodeList enteteList = header.getElementsByTagName("message");
        System.err.println("LA TAILLE ESSSSST " + enteteList.getLength());
        for(int i=0; i< enteteList.getLength();i++){
            Element entete = (Element) enteteList.item(i);
            
            String date = getTextFromElement(entete, "date");
            System.out.println("La date d'envoi est :"+ date);

            // System.out.println("Le contenu est :"+ contenu);

            //On utilise un message temporaire pour l'ajouter à la liste des messages ensuite
            Message temp = new Message(contenu);
            temp.setDateDiffusion(ParseMessageDateOrValidity(header,false));
            temp.setSenderOrganization(entrepriseExp);
            temp.setReceiverOrganization(entrepriseDest);
            temp.setDateExpiration(ParseMessageDateOrValidity(header, true));
            emetteur = parseExpediteur(entete);
            temp.setEmetteurMessage(emetteur);
            destinatairesList = parseDestinataire(entete);
            temp.setDestinataires(destinatairesList);
            
            // On vérifie si le message existe déjà avant l'insertion
            Optional<Message> existingMessage = messRepo.findByDateDiffusionAndSenderOrganizationAndContent(
                temp.getDateDiffusion(),
                temp.getSenderOrganization(),
                temp.getContent());

            if(existingMessage.isEmpty()){
                emRepo.save(emetteur);
                messRepo.save(temp);
                emetteur.setMessage(temp);
                for (Destinataire destinataire : destinatairesList) {
                    destinataire.setMessage(temp);
                    destRepo.save(destinataire);
                }
            }
        }
    }


    public String parseContent(Element message) {
        String contenu = getTextFromElement(message, "Contenu");
        System.out.println("Contenu du message: " + contenu);
        return contenu;
    }


    public String parseCompanyReceiver(Element e){
        return e.getElementsByTagName("receptrice").item(0).getTextContent();
    }

    public String parseCompanySender(Element e){
        return e.getElementsByTagName("emettrice").item(0).getTextContent();
    }

    public Emetteur parseExpediteur(Element e){
        String email = getTextFromElement(e, "email");
        return new Emetteur(email);
    }

    public Date ParseMessageDateOrValidity(Element dateElement, boolean validity){
        // If boolean validity is true, we get the validity of the conversation, else we get the send date
        String dateString = validity ? getTextFromElement(dateElement, "validite") : getTextFromElement(dateElement, "date");
        // Utilisation de SimpleDateFormat pour parser la date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.parse(dateString);
        } 
        catch (ParseException e) {
            System.err.println("ERREUR EN PARSANT LA DATE");
            e.printStackTrace(); 
            return null;
        }
    }

    public java.util.List <Destinataire> parseDestinataire(Element e){
        java.util.List <Destinataire> temp = new ArrayList<>();
        NodeList destinatairesList = e.getElementsByTagName("destinataires");
        for(int i=0; i< destinatairesList.getLength();i++){
            Element destinataireElement = (Element) destinatairesList.item(i);
            String email = getTextFromElement(destinataireElement, "email");
            temp.add(new Destinataire(email));
        }
        return temp;
    }
}
