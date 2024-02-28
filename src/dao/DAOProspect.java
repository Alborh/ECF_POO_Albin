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
            throw (new ExceptionDAO("Erreur DAO : "+e.getMessage()));
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
            throw (new ExceptionDAO("Erreur DAO : "+e.getMessage()));
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
            String query = "INSERT INTO prospect(prospect.idprospect, prospect.raisoncociale, prospect.numerorue, prospect.nomrue, prospect.codepostal, prospect.ville, prospect.telephone, prospect.mail, prospect.commentaire, prospect.dateprospection, prospect.interesse) "
                    +"VALUES ("+prospect.getIdentifiant()+", '"+prospect.getRaisonSociale()+"', '"+prospect.getNumeroRue()+"', '"+
                    prospect.getNomRue()+"', '"+prospect.getCodePostal()+"', '"+prospect.getVille()+"', '"+prospect.getTelephone()+"', '"+
                    prospect.getMail()+"', '"+prospect.getCommentaire()+"', '"+prospect.getDateFormatSQL()+"', '"+prospect.getInteresse()+')';
            Statement stmt = connection.createStatement();
            stmt.executeQuery(query);
        } catch (SQLException | IOException e){
            throw (new ExceptionDAO("Erreur DAo : "+e.getMessage()));
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
            String query = "UPDATE prospect SET prospect.raisonsociale = '"+prospect.getRaisonSociale()+"', "+
                    "prospect.numerorue = '"+prospect.getNumeroRue()+"', "+
                    "prospect.nomrue = '"+prospect.getNumeroRue()+"', "+
                    "prospect.codepostal = '"+prospect.getCodePostal()+"', "+
                    "prospect.ville = '"+prospect.getVille()+"', "+
                    "prospect.telephone = '"+prospect.getTelephone()+"' "+
                    "prospect.mail ='"+prospect.getMail()+"', "+
                    "prospect.commentaire = '"+prospect.getCommentaire()+"', "+
                    "prospect.dateprospection = '"+prospect.getDateFormatSQL()+"', "+
                    "prospect.interesse = '"+prospect.getInteresse()+"' "+
                    "WHERE prospect.idprospect = "+prospect.getIdentifiant();
            Statement stmt = connection.createStatement();
            stmt.executeQuery(query);
        } catch (SQLException | IOException e){
            throw (new ExceptionDAO("Erreur DAO : "+e.getMessage()));
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
            String query = "DELETE FROM prospect WHERE prospect.idprospect = "+prospect.getIdentifiant();
            Statement stmt = connection.createStatement();
            stmt.executeQuery(query);
        } catch (SQLException | IOException e){
            throw (new ExceptionDAO("Erreur DAO : "+e.getMessage()));
        }
    }
}