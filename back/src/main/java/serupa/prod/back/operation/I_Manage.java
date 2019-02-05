package serupa.prod.back.operation;

import java.io.File;

import serupa.prod.back.entites.Commande;

public interface I_Manage {
public Commande traiter(File f);
public void traiter();
public File[] getFichiers();
public void archiver();
}
