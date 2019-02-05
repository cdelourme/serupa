package serupa.prod.back.batch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import serupa.prod.back.entites.Bocad;
import serupa.prod.back.entites.BocadElement;
import serupa.prod.back.entites.Commande;
import serupa.prod.back.entites.Manage;
import serupa.prod.back.entites.OrdreDebit;
import serupa.prod.back.entites.OrdreDebitElement;
import serupa.prod.back.service.CommandeService;
import serupa.prod.back.service.ManageService;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    @Autowired
    private CommandeService comService;
    @Autowired
    private ManageService manageService;
    
    @Scheduled(fixedRate = 20000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        if(manageService.findAll().isEmpty()) {
        	manageService.alimenteBase();
        }
        List<Manage> operations = manageService.findAll();
        for (Manage m: operations) {
        	if (m != null) {
        		switch (m.getTypeOperation()) {
        		case "ORDREDEBIT": 
        			File[] files = m.getFichiers();
        			for (File f: files) {
        				Commande maCommande = new Commande();
        		        try {
        		        	BufferedReader br = new BufferedReader(new FileReader(f));//"/home/jarvis/OrdreDebit/essai_od.txt"));
        		        	String line;
        		        	
        		        	OrdreDebit od = new OrdreDebit();
        		        	od.setNom(f.getCanonicalPath());
        		        	while ((line = br.readLine()) != null) {
        		        	   // process the line.
        		        		if (line.startsWith("!")) {
        		        			String[] param = line.split(":");
        		        			switch (param[0].trim()) {
        		        			case "!CLIENT": maCommande.setNomClient(param[1]);break;
        		        			case "!ADDRESSE": maCommande.setAdresse(param[1]);break;
        		        			case "!AFFAIRE": maCommande.setAffaire(param[1]);break;
        		        			case "!COMMANDE": maCommande.setNumero(param[1]);break;
        		        			case "!SOCIETE": maCommande.setSociete(param[1]);break;
        		        			case "!COMMENTAIRE": od.setCommentaire(param[1]);break;
        		        			case "!DELAI": od.setDelai(new Date(param[1]));break;
        		        			case "!ATELIER": od.setAtelier(param[1]);break;
        		        			case "!DEMANDE_LE": od.setDemande_le(new Date(param[1]));break;
        		        			case "!DEMANDE_PAR": od.setDemande_par(param[1]);break;
        		        			}
        		        					
        		        		}else {
        		        			if (!line.isEmpty()) {
        		        				String[] param = line.split(";");
        		        				OrdreDebitElement element = new OrdreDebitElement(param[0], param[1], new Integer(param[2]), new Integer(param[3]),new Integer(param[4]));
        		        				od.addElement(element);
        		        			}
        		        		}
        		        	}
        		        	Commande comToSave = comService.findByNumero(maCommande.getNumero());
        		        	if (comToSave == null) {
        		        		comToSave = maCommande ;
        		        	}
        		        	comToSave.addOrdreDebit(od);
        		        	comService.create(comToSave);
        		        	br.close();
        					
        				} catch (Exception e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        					maCommande = null;
        				}
        			}
        			break;
        		case "BOCAD":
        			File[] boFiles = m.getFichiers();
        			for (File f: boFiles)
        			{
	        			try
	        			{
	        				System.out.println("FILE : " + f.getCanonicalPath());
							DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
							DocumentBuilder builder = factory.newDocumentBuilder();
							Document xml = builder.parse(f);
							Element root = xml.getDocumentElement();
					        XPathFactory xpf = XPathFactory.newInstance();
					        XPath path = xpf.newXPath();
					         
					        String expression = "/Workbook/Worksheet/Table/Row[4]/Cell[2]";
					        String str = (String)path.evaluate(expression, root);

					        Commande maCommande = comService.findByNumero(str);
		 		        	if (maCommande == null) {
				        		maCommande = new Commande();
				        		maCommande.setNumero(str);
				        		
						        expression = "/Workbook/Worksheet/Table/Row/Cell";
						        maCommande.setSociete((String)path.evaluate(expression, root));
						         
						        expression = "/Workbook/Worksheet/Table/Row[5]/Cell[2]";
						        maCommande.setNomClient((String)path.evaluate(expression, root));
		
						        expression = "/Workbook/Worksheet/Table/Row[7]/Cell[2]";
						        maCommande.setAdresse((String)path.evaluate(expression, root));
				        	}
		
		 		        	Bocad bo = new Bocad();
				         
					        expression = "/Workbook/Worksheet/Table/Row[4]/Cell[4]";
					        bo.setPhase((String)path.evaluate(expression, root));
					         
					        expression = "/Workbook/Worksheet/Table/Row[6]/Cell[4]";
					        bo.setRevision((String)path.evaluate(expression, root));
					         
					        expression = "/Workbook/Worksheet/Table/Row[5]/Cell[4]";
					        bo.setPoids(Float.valueOf((String)path.evaluate(expression, root)));
		
					        expression = "/Workbook/Worksheet/Table/Row[8]/Cell[4]";
					        bo.setLongueur(Integer.valueOf((String)path.evaluate(expression, root)));
					         
					        expression = "/Workbook/Worksheet/Table/Row[11]/Cell[4]";
					        bo.setPeinture((String)path.evaluate(expression, root));
					         
			         
					        //Recherche des elements Bocad
					        int i =0;
					        String repere = "repere";
					        while(! repere.isEmpty())
					        { 
					        	BocadElement boe = new BocadElement();
						        expression = "/Workbook/Worksheet/Table/Row["+(15+i)+"]/Cell[1]";
						        repere = (String)path.evaluate(expression, root);
						        if (! repere.isEmpty())
						        {
							        boe.setRepere(repere);
							         
							        expression = "/Workbook/Worksheet/Table/Row["+(15+i)+"]/Cell[2]";
							        boe.setQuantite(Integer.valueOf((String)path.evaluate(expression, root)));
		
							        expression = "/Workbook/Worksheet/Table/Row["+(15+i)+"]/Cell[3]";
							        boe.setDesignation((String)path.evaluate(expression, root));
							         
							        expression = "/Workbook/Worksheet/Table/Row["+(15+i)+"]/Cell[5]";
							        boe.setDimension((String)path.evaluate(expression, root));
							         
							        expression = "/Workbook/Worksheet/Table/Row["+(15+i)+"]/Cell[6]";
							        boe.setPoids(Float.valueOf((String)path.evaluate(expression, root)));
							         
							        expression = "/Workbook/Worksheet/Table/Row["+(15+i)+"]/Cell[7]";
							        boe.setLongueur(Integer.valueOf((String)path.evaluate(expression, root)));
							         
							        expression = "/Workbook/Worksheet/Table/Row["+(15+i)+"]/Cell[8]";
							        boe.setPoidsTotal(Float.valueOf((String)path.evaluate(expression, root)));
							         
							        expression = "/Workbook/Worksheet/Table/Row["+(15+i)+"]/Cell[9]";
							        boe.setPieceMenante((String)path.evaluate(expression, root));
							        bo.addElement(boe);
							        i++;
						        }
					        }
					         
					        maCommande.addBocad(bo);
					        comService.create(maCommande);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.getMessage();
						}
        			}
        	        break;
        		}
        	}
        }

        
//        Commande maCommande = new Commande("12345","Toto","yo");
//        
//        //OrdreDebit od = new OrdreDebit("nom", new Date(),"commentaire","Martin",new Date(),"Pliage");
//        OrdreDebitElement element = new OrdreDebitElement("P30","poutre métal",1000,500,1);
//        od.addElement(element);
//        
//        Bocad bo = new Bocad("phase 1", new Float(1034), new Integer(1), new Integer(520), "Galva");
//        BocadElement boe = new BocadElement("part1", 1, "designation", "dimension", new Float(1234),
//    			new Float(12345), "pieceMenante");
//        bo.addElement(boe);
//        
//        maCommande.addBocad(bo);
//        maCommande.addOrdreDebit(od);
//        
//        comService.create(maCommande);
    }


}
