package dao;

import exception.ExceptionDAO;
import exception.ExceptionMetier;
import metier.Prospect;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 */
public class DAOProspect {
    /**
     *
     * @return
     * @throws ExceptionMetier
     * @throws ExceptionDAO
     */
    public static ArrayList<Prospect> findAll() throws ExceptionMetier, ExceptionDAO {
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
                Date datepros = res.getDate("datepros");
                String interesse = res.getString("interesse");
                prospects.add(new Prospect(id, raisoc, numrue, nomrue, cdpost, ville, tel, mail, comm, datepros,interesse));
            }
            return prospects;
        } catch (SQLException | IOException e){
            throw (new ExceptionDAO("Erreur : "+e.getMessage()));
        }

    }

    /**
     *
     * @param name
     * @return
     * @throws ExceptionMetier
     * @throws ExceptionDAO
     */
    public static Prospect findByName(String name) throws ExceptionMetier, ExceptionDAO {
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
            Date datepros = res.getDate("datepros");
            String interesse = res.getString("interesse");
            Prospect prospect = new Prospect(id, raisoc, numrue, nomrue, cdpost, ville, tel, mail, comm, datepros,interesse);
            return prospect;
        } catch (SQLException | IOException e){
            throw (new ExceptionDAO("Erreur : "+e.getMessage()));
        }
    }

    /**
     *
     * @param prospect
     * @throws ExceptionDAO
     */
    public static void create(Prospect prospect) throws ExceptionDAO {
        try {
            Connection connection = ConnexionManager.getConnexion();
            String query = "INSERT INTO prospect(prospect.idprospect, prospect.raisonsociale, prospect.numerorue, prospect.nomrue, prospect.codepostal, prospect.ville, prospect.telephone, prospect.mail, prospect.commentaire, prospect.dateprospection, prospect.interesse) "
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
            stmt.setString(10, prospect.getDateFormatSQL());
            stmt.setString(11,prospect.getInteresse());
            stmt.execute();
        } catch (SQLException | IOException e){
            throw (new ExceptionDAO("Erreur : "+e.getMessage()));
        }
    }

    /**
     *
     * @param prospect
     * @throws ExceptionDAO
     */
    public static void update(Prospect prospect) throws ExceptionDAO {
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
            stmt.setString(9, prospect.getDateFormatSQL());
            stmt.setString(10,prospect.getInteresse());
            stmt.execute();
        } catch (SQLException | IOException e){
            throw (new ExceptionDAO("Erreur : "+e.getMessage()));
        }
    }

    /**
     *
     * @param prospect
     * @throws ExceptionDAO
     */
    public static void delete(Prospect prospect) throws ExceptionDAO {
        try {
            Connection connection = ConnexionManager.getConnexion();
            String query = "DELETE FROM prospect WHERE prospect.idprospect = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,prospect.getIdentifiant());
            stmt.execute();
        } catch (SQLException | IOException e){
            throw (new ExceptionDAO("Erreur : "+e.getMessage()));
        }
    }
}