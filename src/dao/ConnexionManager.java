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
 *
 */
public class ConnexionManager {
    private static Connection connexion;

    /**
     * Création de l'instance de connexion
     * @throws IOException
     * @throws SQLException
     */
    private ConnexionManager() throws IOException, SQLException {
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
     * renvoie la connexion à la base de données
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public static Connection getConnexion() throws SQLException, IOException {
        if (connexion==null){
            new ConnexionManager();
        }
        return connexion;
    }

    //Gestion de la fermeture de la connection
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                if (connexion !=null){
                    try {
                        connexion.close();
                        LoggerPoo.LOGGER.log(Level.INFO,"Database fermée");
                        System.out.println("Connection fermée");
                    } catch (SQLException e){
                        LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur : "+e.getMessage());
                    }
                }
            }
        });
    }
}
