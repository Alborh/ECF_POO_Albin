package dao;

import exception.ExceptionDAO;
import exception.ExceptionMetier;
import log.LoggerPoo;
import metier.Client;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Classe d'accès objet de Client
 */
public class DAOClient {
    /**
     * Liste de tout les cilents de la base de donnée
     * @return ArrayList de CLients
     * @throws Exception remonte les exceptions SQLException er IOException
     */
    public static ArrayList<Client> findAll() throws Exception {
        try {
            ArrayList<Client> clients = new ArrayList<>();
            Connection connection = ConnexionManager.getConnexion();
            String query = "SELECT client.idclient as id, client.raisonsociale as raisoc, client.numerorue as numrue, " +
                    "client.nomrue as nomrue, client.codepostal as cdpost, client.ville as ville, client.telephone as tel, " +
                    "client.mail as mail, client.commentaire as comm, client.chiffredaffaire as chaff, client.NBEMPLOYES as nbemp" +
                    " FROM client";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                int id = res.getInt("id");
                String raisoc = res.getString("raisoc");
                String numrue = res.getString("numrue");
                String nomrue = res.getString("nomrue");
                String cdpost = res.getString("cdpost");
                String ville = res.getString("ville");
                String tel = res.getString("tel");
                String mail = res.getString("mail");
                String comm = res.getString("comm");
                double chaff = res.getDouble("chaff");
                int nbemp = res.getInt("nbemp");
                clients.add(new Client(id, raisoc, numrue, nomrue, cdpost, ville, tel, mail, comm, chaff, nbemp));
            }
            return clients;
        } catch (SQLException | IOException e){
            LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur DAO : "+e.getMessage());
            throw (new ExceptionDAO("Erreur : "+e.getMessage()));
        }
    }

    /**
     * Retrouve un client de la base de donnée en renseignant sa raison sociale
     * @param name String raison sociale
     * @return Client recherché
     * @throws Exception remonte les exceptions SQLException er IOException
     */
    public static Client findByName(String name) throws Exception {
        try {
            Connection connection = ConnexionManager.getConnexion();
            String query = "SELECT client.idclient as id, client.raisonsociale as raisoc, client.numerorue as numrue, client.nomrue as nomrue," +
                    " client.codepostal as cdpost, client.ville as ville, client.telephone as tel, client.mail as mail, client.commentaire as comm," +
                    " client.chiffredaffaire as chaff, client.NBEMPLOYES as nbemp FROM client where client.RAISONSOCIALE like ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,name);
            ResultSet res = stmt.executeQuery();
            res.next();
            int id = res.getInt("id");
            String raisoc = res.getString("raisoc");
            String numrue = res.getString("numrue");
            String nomrue = res.getString("nomrue");
            String cdpost = res.getString("cdpost");
            String ville = res.getString("ville");
            String tel = res.getString("tel");
            String mail = res.getString("mail");
            String comm = res.getString("comm");
            double chaff = res.getDouble("chaff");
            int nbemp = res.getInt("nbemp");
            Client client = new Client(id, raisoc, numrue, nomrue, cdpost, ville, tel, mail, comm, chaff, nbemp);
            return client;
        } catch (SQLException | IOException e){
            LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur DAO : "+e.getMessage());
            throw (new ExceptionDAO("Erreur : "+e.getMessage()));
        }
    }

    /**
     * Crée le client dans la base de donnée
     * @param client Client
     * @throws Exception remonte les exceptions SQLException er IOException
     */
    public static void create(Client client) throws Exception{
        try {
            Connection connection = ConnexionManager.getConnexion();
            String query = "INSERT INTO client(client.idclient, client.raisonsociale, client.numerorue, client.nomrue, client.codepostal, " +
                    "client.ville, client.telephone, client.mail, client.commentaire, client.chiffredaffaire, client.NBEMPLOYES)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,client.getIdentifiant());
            stmt.setString(2,client.getRaisonSociale());
            stmt.setString(3,client.getNumeroRue());
            stmt.setString(4,client.getNomRue());
            stmt.setString(5,client.getCodePostal());
            stmt.setString(6,client.getVille());
            stmt.setString(7,client.getTelephone());
            stmt.setString(8,client.getMail());
            stmt.setString(9,client.getCommentaire());
            stmt.setDouble(10,client.getChiffreDAffaire());
            stmt.setInt(11,client.getNbEmploye());
            stmt.execute();
        } catch (SQLException | IOException e){
            LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur DAO : "+e.getMessage());
            throw (new ExceptionDAO("Erreur : "+e.getMessage()));
        }
    }

    /**
     * Modifie le client dans la base de données
     * @param client Client
     * @throws Exception remonte les exceptions SQLException er IOException
     */
    public static void update(Client client) throws Exception {
        try {
            Connection connection = ConnexionManager.getConnexion();
            String query = "UPDATE client SET client.raisonsociale = ?, client.numerorue = ?, client.nomrue = ?, " +
                    "client.codepostal = ?, client.ville = ?, client.telephone = ?, client.mail = ?, client.commentaire = ?, " +
                    "client.chiffredaffaire = ?, client.nbemployes = ? WHERE client.idclient = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(11,client.getIdentifiant());
            stmt.setString(1,client.getRaisonSociale());
            stmt.setString(2,client.getNumeroRue());
            stmt.setString(3,client.getNomRue());
            stmt.setString(4,client.getCodePostal());
            stmt.setString(5,client.getVille());
            stmt.setString(6,client.getTelephone());
            stmt.setString(7,client.getMail());
            stmt.setString(8,client.getCommentaire());
            stmt.setDouble(9,client.getChiffreDAffaire());
            stmt.setInt(10,client.getNbEmploye());
            stmt.execute();
        } catch (SQLException | IOException e){
            LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur DAO : "+e.getMessage());
            throw (new ExceptionDAO("Erreur : "+e.getMessage()));
        }
    }

    /**
     * Supprime le client dans la base de données
     * @param client Client
     * @throws Exception remonte les exceptions SQLException er IOException
     */
    public static void delete(Client client) throws Exception {
        try {
            Connection connection = ConnexionManager.getConnexion();
            String query = "DELETE FROM client WHERE client.idclient = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,client.getIdentifiant());
            stmt.execute();
        } catch (SQLException | IOException e){
            LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur DAO : "+e.getMessage());
            throw (new ExceptionDAO("Erreur : "+e.getMessage()));
        }
    }
}
