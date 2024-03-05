package dao;

import exception.ExceptionDAO;
import exception.ExceptionMetier;
import log.LoggerPoo;
import metier.Prospect;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;

/**
 *
 */
public class DAOProspect {
    /**
     *
     * @return
     * @throws Exception
     */
    public static ArrayList<Prospect> findAll() throws Exception {
        try {
            ArrayList<Prospect> prospects = new ArrayList<>();
            Connection connection = ConnexionManager.getConnexion();
            String query = "SELECT prospect.IDPROSPECT as id, prospect.raisonsociale as raisoc, prospect.numerorue as numrue, " +
                    "prospect.nomrue as nomrue, prospect.codepostal as cdpost, prospect.ville as ville, prospect.telephone as tel, " +
                    "prospect.mail as mail, prospect.commentaire as comm, prospect.DATEPROSPECTION as datepros, prospect.INTERESSE as interesse" +
                    " FROM prospect";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                int id = res.getInt("id");
                String raisoc = res.getString("raisoc");
                String numrue = res.getString("numrue");
                String nomrue = res.getString("nomrue");
                String cdpost = res.getString("cdpost");
                String ville = res.getString("ville");
                String tel = res.getString("tel");
                String mail = res.getString("mail");
                String comm = res.getString("comm");
                //Convertis la date en LocalDate (stockée en date dans la BDD)
                LocalDate datepros = LocalDate.parse(res.getString("datepros"));
                String interesse = res.getString("interesse");
                prospects.add(new Prospect(id, raisoc, numrue, nomrue, cdpost, ville, tel, mail, comm, datepros,interesse));
            }
            return prospects;
        } catch (SQLException | IOException e){
            LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur DAO : "+e.getMessage());
            throw (new ExceptionDAO("Erreur : "+e.getMessage()));
        }
    }

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    public static Prospect findByName(String name) throws Exception {
        try {
            Connection connection = ConnexionManager.getConnexion();
            String query = "SELECT prospect.IDPROSPECT as id, prospect.raisonsociale as raisoc, prospect.numerorue as numrue, " +
                    "prospect.nomrue as nomrue, prospect.codepostal as cdpost, prospect.ville as ville, prospect.telephone as tel, " +
                    "prospect.mail as mail, prospect.commentaire as comm, prospect.DATEPROSPECTION as datepros, prospect.INTERESSE as interesse" +
                    " FROM prospect WHERE prospect.raisonsociale like ?";
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
            //Convertis la date en LocalDate (stockée en date dans la BDD)
            LocalDate datepros = LocalDate.parse(res.getString("datepros"));
            String interesse = res.getString("interesse");
            Prospect prospect = new Prospect(id, raisoc, numrue, nomrue, cdpost, ville, tel, mail, comm, datepros,interesse);
            return prospect;
        } catch (SQLException | IOException e){
            LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur DAO : "+e.getMessage());
            throw (new ExceptionDAO("Erreur : "+e.getMessage()));
        }
    }

    /**
     *
     * @param prospect
     * @throws Exception
     */
    public static void create(Prospect prospect) throws Exception {
        try {
            Connection connection = ConnexionManager.getConnexion();
            String query = "INSERT INTO prospect(prospect.idprospect, prospect.raisonsociale, prospect.numerorue, prospect.nomrue, " +
                    "prospect.codepostal, prospect.ville, prospect.telephone, prospect.mail, prospect.commentaire, prospect.dateprospection, prospect.interesse) "
                    +"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,prospect.getIdentifiant());
            stmt.setString(2,prospect.getRaisonSociale());
            stmt.setString(3,prospect.getNumeroRue());
            stmt.setString(4,prospect.getNomRue());
            stmt.setString(5,prospect.getCodePostal());
            stmt.setString(6,prospect.getVille());
            stmt.setString(7,prospect.getTelephone());
            stmt.setString(8,prospect.getMail());
            stmt.setString(9,prospect.getCommentaire());
            //convertis la LocalDate en String du bon format pour la requête SQL
            stmt.setString(10,prospect.getDateProspection().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            stmt.setString(11,prospect.getInteresse());
            stmt.execute();
        } catch (SQLException | IOException e){
            LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur DAO : "+e.getMessage());
            throw (new ExceptionDAO("Erreur : "+e.getMessage()));
        }
    }

    /**
     *
     * @param prospect
     * @throws Exception
     */
    public static void update(Prospect prospect) throws Exception {
        try {
            Connection connection = ConnexionManager.getConnexion();
            String query = "UPDATE prospect SET prospect.raisonsociale = ?, prospect.numerorue = ?, prospect.nomrue = ?, "+
                    "prospect.codepostal = ?, prospect.ville = ?, prospect.telephone = ?, prospect.mail = ?, "+
                    "prospect.commentaire = ?, prospect.dateprospection = ?, prospect.interesse = ? WHERE prospect.idprospect = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(11,prospect.getIdentifiant());
            stmt.setString(1,prospect.getRaisonSociale());
            stmt.setString(2,prospect.getNumeroRue());
            stmt.setString(3,prospect.getNomRue());
            stmt.setString(4,prospect.getCodePostal());
            stmt.setString(5,prospect.getVille());
            stmt.setString(6,prospect.getTelephone());
            stmt.setString(7,prospect.getMail());
            stmt.setString(8,prospect.getCommentaire());
            //convertis la LocalDate en String du bon format pour la requête SQL
            stmt.setString(9, prospect.getDateProspection().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            stmt.setString(10,prospect.getInteresse());
            stmt.execute();
        } catch (SQLException | IOException e){
            LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur DAO : "+e.getMessage());
            throw (new ExceptionDAO("Erreur : "+e.getMessage()));
        }
    }

    /**
     *
     * @param prospect
     * @throws Exception
     */
    public static void delete(Prospect prospect) throws Exception {
        try {
            Connection connection = ConnexionManager.getConnexion();
            String query = "DELETE FROM prospect WHERE prospect.idprospect = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,prospect.getIdentifiant());
            stmt.execute();
        } catch (SQLException | IOException e){
            LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur DAO : "+e.getMessage());
            throw (new ExceptionDAO("Erreur : "+e.getMessage()));
        }
    }
}