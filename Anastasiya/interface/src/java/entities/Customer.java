/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import db.ConnectionProvider;
import db.NoDataFoundException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import service.CustomerFilter;

/**
 *
 * @author Admin
 */
public class Customer {

    public static final String SELECT_CUSTOMER_INFO =
            "  SELECT id, " +
            "         name, " +
            "         phone, " +
            "         depart, " +
            "         maintancer_id, " +
            "         maintancer_name " +
            "    FROM customers_extended " +
            "   WHERE id = ?";
    public static final String SELECT_ALL_CUSTOMERS_DATA =
            "  SELECT id, " +
            "         name, " +
            "         phone, " +
            "         depart, " +
            "         maintancer_id, " +
            "         maintancer_name " +
            "    FROM customers_extended " +
            "   WHERE (1 = ? and maintancer_id is null) " +
            "         OR (1 = ?) " +
            "         OR (1 = ? and maintancer_id = ?) " +
            "ORDER BY name";

    public static final String SELECT_INDIVIDUAL_APPLICATIONS =
            "SELECT application_id AS ID, application_name AS NAME " +
            "  FROM indiviaual_applic_customer " +
            " WHERE customer_id = ?";
    public static final String SELECT_INSTALLED_APPLICATIONS =
            "SELECT application_id AS ID, application_name AS NAME " +
            "  FROM application_customer " +
            " WHERE customer_id = ?";
    private BigDecimal id;
    private String name;
    private String phone;
    private int departments;
    private Pair maintancer;

    public Customer() {
    }

    public Customer(BigDecimal id, String name, String phone, int departments, Pair maintancer) {
        this.id = id;
        this.name = name == null ? "" : name;
        this.phone = phone == null ? "" : phone;
        this.departments = departments;
        this.maintancer = maintancer;
    }

    public int getDepartments() {
        return departments;
    }

    public void setDepartments(int departments) {
        this.departments = departments;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Pair getMaintancer() {
        return maintancer;
    }

    public void setMaintancer(Pair maintancer) {
        this.maintancer = maintancer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? "" : phone;
    }

    public static Vector<Customer> load(CustomerFilter filter) throws SQLException {
        Vector<Customer> list = new Vector<Customer>();
        Connection conn = ConnectionProvider.getConnection();
        PreparedStatement s = conn.prepareStatement(SELECT_ALL_CUSTOMERS_DATA);
        s.setString(1, filter.getMaintancerId().equals(CustomerFilter.MAINTANCER_WITHOUT) ? "1" : "0");
        s.setString(2, filter.getMaintancerId().equals(CustomerFilter.MAINTANCER_ALL) ? "1" : "0");
        boolean isId = !filter.getMaintancerId().equals(CustomerFilter.MAINTANCER_ALL) && !filter.getMaintancerId().equals(CustomerFilter.MAINTANCER_WITHOUT);
        if (isId) {
            s.setString(3, "1");
            s.setString(4, filter.getMaintancerId());
        } else {
            s.setString(3, "0");
            s.setString(4, "0");
        }
        ResultSet res = s.executeQuery();
        while (res.next()) {
            BigDecimal id = res.getBigDecimal("ID");
            String name = res.getString("NAME");
            String phone = res.getString("PHONE");
            int departments = res.getInt("DEPART");
            BigDecimal maintancerId = res.getBigDecimal("MAINTANCER_ID");
            String maintancerName = res.getString("MAINTANCER_NAME");
            Pair maintancer = null;
            if (maintancerId != null) {
                maintancer = new Pair(maintancerId, maintancerName);
            }

            list.add(new Customer(id, name, phone, departments, maintancer));
        }
        return list;
    }

    public Vector<Pair> getIndividualApplications() throws SQLException{
        Vector<Pair> applications = new Vector<Pair>();
        Connection conn = ConnectionProvider.getConnection();
        PreparedStatement s = conn.prepareStatement(SELECT_INDIVIDUAL_APPLICATIONS);
        s.setBigDecimal(1, id);
        ResultSet res = s.executeQuery();
        while (res.next()) {
            applications.add(new Pair(res.getBigDecimal("ID"), res.getString("NAME")));
        }
        return applications;
    }

    public Vector<Pair> getInstalledApplications() throws SQLException{
        Vector<Pair> applications = new Vector<Pair>();
        Connection conn = ConnectionProvider.getConnection();
        PreparedStatement s = conn.prepareStatement(SELECT_INSTALLED_APPLICATIONS);
        s.setBigDecimal(1, id);
        ResultSet res = s.executeQuery();
        while (res.next()) {
            applications.add(new Pair(res.getBigDecimal("ID"), res.getString("NAME")));
        }
        return applications;

    }

    public void loadById() throws SQLException, NoDataFoundException {
        if (id == null) {
            throw new NoDataFoundException("null");
        }
        Connection conn = ConnectionProvider.getConnection();
        PreparedStatement s = conn.prepareStatement(SELECT_CUSTOMER_INFO);
        s.setBigDecimal(1, id);
        ResultSet res = s.executeQuery();
        if (res.next()) {
            setName(res.getString("NAME"));
            setPhone(res.getString("PHONE"));
            setDepartments(res.getInt("DEPART"));
            BigDecimal mId = res.getBigDecimal("MAINTANCER_ID");
            String mName = res.getString("MAINTANCER_NAME");
            Pair maintancer = null;
            if(mId != null){
                maintancer = new Pair(mId, mName);
            }
            setMaintancer(maintancer);
            setMaintancer(maintancer);
        } else {
            throw new NoDataFoundException(id.toString());
        }
    }

}
