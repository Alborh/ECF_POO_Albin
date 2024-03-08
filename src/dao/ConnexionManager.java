package dao;

import log.LoggerPoo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

/**
 * Singleton de gestion de la connexion à la base de données
 */
public class ConnexionManager {
    private static Connection connexion;

    /**
     * Création de l'instance de connexion
     * @throws Exception remonte les exceptions
     */
    private ConnexionManager() throws Exception {
        Properties dataProperties = new Properties();
        File fichier = new File("database.properties");
        FileInputStream input = new FileInputStream(fichier);
        dataProperties.load(input);
        this.connexion = DriverManager.getConnection(
                dataProperties.getProperty("url"),
                dataProperties.getProperty("login"),
                dataProperties.getProperty("password"));
    }

    /**
     * Renvoie la connexion à la base de données
     * @return Connection
     * @throws Exception remponte les exceptions
     */
    public static Connection getConnexion() throws Exception {
        if (connexion==null){
            new ConnexionManager();
        }
        return connexion;
    }

    /*
      Gestion de la fermeture de la connection
     */
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                if (connexion !=null){
                    try {
                        LoggerPoo.LOGGER.log(Level.INFO,"Database fermée");
                        connexion.close();
                        System.out.println("Connection fermée");
                    } catch (SQLException e){
                        LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur : "+e.getMessage());
                    }
                }
            }
        });
    }
}
