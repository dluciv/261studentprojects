/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import db.ConnectionProvider;
import db.InconstistencyException;
import db.NoDataFoundException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import service.ApplicationFilter;
import service.ApplicationType;
import static service.ApplicationType.*;

/**
 *
 * @author Admin
 */
public class Application {

    public static final int DB_INDIVIDUAL_TYPE_VALUE = 4;
    public static final int DB_INNER_TYPE_VALUE = 1;
    public static final int DB_FREE_TYPE_VALUE = 2;
    public static final int DB_OPTIONAL_TYPE_VALUE = 3;
    public static final int DB_OTHER_TYPE_VALUE = 0;

    public static final String SELECT_APPLICATION_INFO =
            "SELECT id, name, description, " +
            "        is_inner + 2 * is_free + 3 * is_optional + 4 * is_individual as type " +
            "  FROM application_extended " +
            " WHERE id = ?";
    public static final String SELECT_ALL_APPLICATION_DATA =
            "SELECT id, name, description, " +
            "        is_inner + 2 * is_free + 3 * is_optional + 4 * is_individual as type " +
            "  FROM application_extended " +
            " WHERE is_free = ? " +
            "       OR is_individual = ? " +
            "       OR is_inner = ? " +
            "       OR is_optional = ? " +
            "       OR (1 = ? " +
            "           AND is_free = 0 " +
            "           AND is_individual = 0 " +
            "           AND is_inner = 0 " +
            "           AND is_optional = 0)" +
            "ORDER BY name";

    public static final String SELECT_IS_REQUIRED =
            "SELECT required " +
            "  FROM free_application " +
            " WHERE id = ?";
    public static final String SELECT_HAVE_ACCESS =
            "SELECT outer_access AS have_access " +
            "  FROM inner_application " +
            " WHERE id = ?";
    public static final String SELECT_PRICE_FOR_OPTIONAL =
            "SELECT price " +
            " FROM optional_application " +
            " WHERE id = ?";
    public static final String SELECT_PRICE_FOR_INDIVIDUAL =
            "SELECT price " +
            " FROM individual_application " +
            " WHERE id = ?";

    public static final String SELECT_INNER_APPLICATION_CUSTOMER =
            "SELECT customer_id AS id, customer_name AS name " +
            "  FROM indiviaual_applic_customer " +
            " WHERE application_id = ?";
    public static final String SELECT_APPLICATION_DEVELOPERS =
            "SELECT employee_id AS id, eployee_name AS name " +
            "  FROM application_employee " +
            " WHERE application_id = ?";
    public static final String SELECT_APPLICATION_CUSTOMERS =
            "SELECT customer_id AS id, customer_name AS name " +
            "  FROM application_customer " +
            " WHERE application_id = ?";

    private BigDecimal id;
    private String name;
    private String description;
    private ApplicationType type;

    public Application() {
    }

    public Application(BigDecimal id, String name, String description, ApplicationType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ApplicationType getType() {
        return type;
    }

    public void setType(ApplicationType type) {
        this.type = type;
    }

    public static Vector<Application> load(ApplicationFilter filter) throws SQLException {
        Vector<Application> list = new Vector<Application>();
        Connection conn = ConnectionProvider.getConnection();
        PreparedStatement s = conn.prepareStatement(SELECT_ALL_APPLICATION_DATA);
        if (filter.isShowFree()) {
            s.setInt(1, 1);
        } else {
            s.setString(1, "");
        }
        if (filter.isShowIndividual()) {
            s.setInt(2, 1);
        } else {
            s.setString(2, "");
        }
        if (filter.isShowInner()) {
            s.setInt(3, 1);
        } else {
            s.setString(3, "");
        }
        if (filter.isShowOptional()) {
            s.setInt(4, 1);
        } else {
            s.setString(4, "");
        }
        if (filter.isShowOthers()) {
            s.setInt(5, 1);
        } else {
            s.setString(5, "");
        }
        ResultSet res = s.executeQuery();
        while (res.next()) {
            BigDecimal id = res.getBigDecimal("ID");
            String name = res.getString("NAME");
            String description = res.getString("DESCRIPTION");
            ApplicationType type;
            try {
                type = convertType(res.getInt("TYPE"));
            } catch (InconstistencyException ex) {
                continue;
            }
            list.add(new Application(id, name, description, type));
        }
        return list;
    }

    private static ApplicationType convertType(int dbValue) throws InconstistencyException {
        switch (dbValue) {
            case DB_INNER_TYPE_VALUE:
                return INNER;
            case DB_FREE_TYPE_VALUE:
                return FREE;
            case DB_OPTIONAL_TYPE_VALUE:
                return OPTIONAL;
            case DB_INDIVIDUAL_TYPE_VALUE:
                return INDIVIDUAL;
            case DB_OTHER_TYPE_VALUE:
                return OTHERS;
        }
        throw new InconstistencyException("Unknown application type");
    }

    public void loadById() throws SQLException, NoDataFoundException, InconstistencyException {
        if (id == null) {
            throw new NoDataFoundException("null");
        }
        Connection conn = ConnectionProvider.getConnection();
        PreparedStatement s = conn.prepareStatement(SELECT_APPLICATION_INFO);
        s.setBigDecimal(1, id);
        ResultSet res = s.executeQuery();
        if (res.next()) {
            setName(res.getString("NAME"));
            setDescription(res.getString("DESCRIPTION"));
            setType(convertType(res.getInt("TYPE")));
        } else {
            throw new NoDataFoundException(id.toString());
        }
    }

    public boolean isRequired() throws SQLException {
        if (type != FREE) {
            return false;
        }
        Connection conn = ConnectionProvider.getConnection();
        PreparedStatement s = conn.prepareStatement(SELECT_IS_REQUIRED);
        s.setBigDecimal(1, id);
        ResultSet res = s.executeQuery();
        if (res.next()) {
            return res.getBoolean("REQUIRED");
        }
        return false;
    }

    public boolean haveOuterAccess() throws SQLException {
        if (type != INNER) {
            return false;
        }
        Connection conn = ConnectionProvider.getConnection();
        PreparedStatement s = conn.prepareStatement(SELECT_HAVE_ACCESS);
        s.setBigDecimal(1, id);
        ResultSet res = s.executeQuery();
        if (res.next()) {
            return res.getBoolean("HAVE_ACCESS");
        }
        return false;
    }

    public BigDecimal getPrice() throws SQLException {
        if (type == OPTIONAL) {
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement s = conn.prepareStatement(SELECT_PRICE_FOR_OPTIONAL);
            s.setBigDecimal(1, id);
            ResultSet res = s.executeQuery();
            if (res.next()) {
                return res.getBigDecimal("PRICE");
            }
            // Здесь как бы ошибка. но пока что ноль
            return BigDecimal.ZERO;
        }else if(type == INDIVIDUAL){
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement s = conn.prepareStatement(SELECT_PRICE_FOR_INDIVIDUAL);
            s.setBigDecimal(1, id);
            ResultSet res = s.executeQuery();
            if (res.next()) {
                return res.getBigDecimal("PRICE");
            }
            // Тут может не быть цены
            return null;
        }else{
            // И тут как бы ошибка
            return null;
        }
    }

    public Pair getIndividualCustomer() throws SQLException {
        Pair customer = null;
        if(type == INDIVIDUAL){
        Connection conn = ConnectionProvider.getConnection();
        PreparedStatement s = conn.prepareStatement(SELECT_INNER_APPLICATION_CUSTOMER);
        s.setBigDecimal(1, id);
        ResultSet res = s.executeQuery();
        while (res.next()) {
            customer = new Pair(res.getBigDecimal("ID"), res.getString("NAME"));
        }
        }
        return customer;
    }

    public Vector<Pair> getDevelopers() throws SQLException {
        Vector<Pair> developers = new Vector<Pair>();
        Connection conn = ConnectionProvider.getConnection();
        PreparedStatement s = conn.prepareStatement(SELECT_APPLICATION_DEVELOPERS);
        s.setBigDecimal(1, id);
        ResultSet res = s.executeQuery();
        while (res.next()) {
            developers.add(new Pair(res.getBigDecimal("ID"), res.getString("NAME")));
        }
        return developers;
    }

    public Vector<Pair> getCustomers() throws SQLException {
        Vector<Pair> customers = new Vector<Pair>();
        // И тут должна быть ошибка
        if(type != FREE && type != OPTIONAL) return customers;
        Connection conn = ConnectionProvider.getConnection();
        PreparedStatement s = conn.prepareStatement(SELECT_APPLICATION_CUSTOMERS);
        s.setBigDecimal(1, id);
        ResultSet res = s.executeQuery();
        while (res.next()) {
            customers.add(new Pair(res.getBigDecimal("ID"), res.getString("NAME")));
        }
        return customers;
    }
}
