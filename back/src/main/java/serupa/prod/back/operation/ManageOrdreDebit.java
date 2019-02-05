//package serupa.prod.back.operation;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import serupa.prod.back.entites.Commande;
//import serupa.prod.back.entites.Manage;
//import serupa.prod.back.entites.OrdreDebit;
//import serupa.prod.back.entites.OrdreDebitElement;
//import serupa.prod.back.service.CommandeService;
//
//public class ManageOrdreDebit implements I_Manage{
//
//    /**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//    
//    @Autowired
//    private CommandeService comService;
//    
//    private Manage manage;
//    
//    public ManageOrdreDebit(Manage m) {
//    	this.setManage(manage);
//    }
//    
//	@Override
//	public Commande traiter(File f) {
//		// TODO Auto-generated method stub
//        //Lecture des fichiers Ordre de débit
//		Commande maCommande = new Commande();
//        try {
//        	BufferedReader br = new BufferedReader(new FileReader("/home/jarvis/OrdreDebit/essai_od.txt"));
//        	String line;
//        	
//        	OrdreDebit od = new OrdreDebit();
//        	od.setNom("essai_od.txt");
//        	while ((line = br.readLine()) != null) {
//        	   // process the line.
//        		if (line.startsWith("!")) {
//        			String[] param = line.split(":");
//        			switch (param[0].trim()) {
//        			case "!CLIENT": maCommande.setNomClient(param[1]);break;
//        			case "!ADDRESSE": maCommande.setAdresse(param[1]);break;
//        			case "!AFFAIRE": maCommande.setAffaire(param[1]);break;
//        			case "!COMMANDE": maCommande.setNumero(param[1]);break;
//        			case "!SOCIETE": maCommande.setSociete(param[1]);break;
//        			case "!COMMENTAIRE": od.setCommentaire(param[1]);break;
//        			case "!DELAI": od.setDelai(new Date(param[1]));break;
//        			case "!ATELIER": od.setAtelier(param[1]);break;
//        			case "!DEMANDE_LE": od.setDemande_le(new Date(param[1]));break;
//        			case "!DEMANDE_PAR": od.setDemande_par(param[1]);break;
//        			}
//        					
//        		}else {
//        			if (!line.isEmpty()) {
//        				String[] param = line.split(";");
//        				OrdreDebitElement element = new OrdreDebitElement(param[0], param[1], new Integer(param[2]), new Integer(param[3]),new Integer(param[4]));
//        				od.addElement(element);
//        			}
//        		}
//        	}
//        	maCommande.addOrdreDebit(od);
//        	br.close();
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			maCommande = null;
//		}
//        return maCommande;
//	}
//	
//	@Override
//	public void traiter() {
//		// TODO Auto-generated method stub
//		File[] files = this.getFichiers();
//		for (File f: files) {
//        	comService.create(traiter(f));
//		}
//	}
//
//	@Override
//	public File[] getFichiers() {
//		// TODO Auto-generated method stub
//		File root = new File(getManage().getPath());
//		return (root.isFile())?root.listFiles():null;
//	}
//
//	public Manage getManage() {
//		return manage;
//	}
//
//	public void setManage(Manage manage) {
//		this.manage = manage;
//	}
//
//	@Override
//	public void archiver() {
//		// TODO Auto-generated method stub
//		
//	}
//}
