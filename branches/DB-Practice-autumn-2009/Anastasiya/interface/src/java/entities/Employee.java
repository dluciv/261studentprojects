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
import java.sql.Statement;
import java.util.Vector;
import service.EmployeeFilter;

/**
 *
 * @author Admin
 */
public class Employee {

    public static final String SELECT_ALL_EMPLOYEE_DATA = 
            "SELECT * " +
            "FROM employee_extended " +
            "WHERE " +
            "is_maintancer = ? or is_tester = ? or is_programmer = ? or is_tech_writer = ? " +
            "or " +
            "(is_maintancer = 0 and is_tester = 0 and is_programmer = 0 and is_tech_writer = 0 and 1 = ?) " +
            "order by name ";
    public static final String SELECT_EMPLOYEE_INFO = "SELECT * FROM employee_extended WHERE id=?";
    public static final String SELECT_MAINTANCER_CUSTOMERS = "select c.id, c.name from customer c where c.MAINTANCE = ? order by name";
    public static final String SELECT_DEVELOP_LANGUAGES = "select lang_name from programmer_languages where id = ? order by lang_name";
    public static final String SELECT_WRITER_LANGUAGES = "select lang_name from writer_languages where id = ? order by lang_name";
    public static final String SELECT_EXPERIENCE = "select experience from programmer where id = ?";
    public static final String SELECT_APPLICATIONS = "select application_id as id, application_name as name from application_employee where employee_id = ?";
    private BigDecimal id;
    private String name = "";
    private String phone = "";
    private String icq = "";
    private String jabber = "";
    private String email = "";
    private boolean tester;
    private boolean maintancer;
    private boolean programmer;
    private boolean techWriter;

    public Employee() {
    }

    public Employee(BigDecimal id, String name, String phone, String icq, String jabber, String email) {
        this.id = id;
        this.name = name == null ? "" : name;
        this.phone = phone == null ? "" : phone;
        this.icq = icq == null ? "" : icq;
        this.jabber = jabber == null ? "" : jabber;
        this.email = email == null ? "" : email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? "" : email;
    }

    public String getIcq() {
        return icq;
    }

    public void setIcq(String icq) {
        this.icq = icq == null ? "" : icq;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getJabber() {
        return jabber;
    }

    public void setJabber(String jabber) {
        this.jabber = jabber == null ? "" : jabber;
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

    public boolean isMaintancer() {
        return maintancer;
    }

    public void setMaintancer(boolean maintancer) {
        this.maintancer = maintancer;
    }

    public boolean isProgrammer() {
        return programmer;
    }

    public void setProgrammer(boolean programmer) {
        this.programmer = programmer;
    }

    public boolean isTechWriter() {
        return techWriter;
    }

    public void setTechWriter(boolean techWriter) {
        this.techWriter = techWriter;
    }

    public boolean isTester() {
        return tester;
    }

    public void setTester(boolean tester) {
        this.tester = tester;
    }

    public static Vector<Employee> load(EmployeeFilter filter) throws SQLException {
        Vector<Employee> list = new Vector<Employee>();
        Connection conn = ConnectionProvider.getConnection();
        PreparedStatement s = conn.prepareStatement(SELECT_ALL_EMPLOYEE_DATA);
        if(filter.isShowMaintancer()){
            s.setInt(1, 1);
        }else{
            s.setString(1, "");
        }
        if(filter.isShowTester()){
            s.setInt(2, 1);
        }else{
            s.setString(2, "");
        }
        if(filter.isShowProgrammer()){
            s.setInt(3, 1);
        }else{
            s.setString(3, "");
        }
        if(filter.isShowTechnicalWriter()){
            s.setInt(4, 1);
        }else{
            s.setString(4, "");
        }
        if(filter.isShowOthers()){
            s.setInt(5, 1);
        }else{
            s.setString(5, "");
        }
        ResultSet res = s.executeQuery();
        while (res.next()) {
            BigDecimal id = res.getBigDecimal("ID");
            String name = res.getString("NAME");
            String phone = res.getString("PHONE");
            String icq = res.getString("ICQ");
            String email = res.getString("EMAIL");
            String jabber = res.getString("JABBER");

            Employee em = new Employee(id, name, phone, icq, jabber, email);
            list.add(em);
            em.setMaintancer(res.getBoolean("IS_MAINTANCER"));
            em.setTester(res.getBoolean("IS_TESTER"));
            em.setProgrammer(res.getBoolean("IS_PROGRAMMER"));
            em.setTechWriter(res.getBoolean("IS_TECH_WRITER"));
        }

        return list;
    }

    public void loadById() throws SQLException, NoDataFoundException {
        if (id == null) {
            throw new NoDataFoundException("null");
        }
        Connection conn = ConnectionProvider.getConnection();
        PreparedStatement s = conn.prepareStatement(SELECT_EMPLOYEE_INFO);
        s.setBigDecimal(1, id);
        ResultSet res = s.executeQuery();
        if (res.next()) {
            setName(res.getString("NAME"));
            setPhone(res.getString("PHONE"));
            setIcq(res.getString("ICQ"));
            setEmail(res.getString("EMAIL"));
            setJabber(res.getString("JABBER"));
            setMaintancer(res.getBoolean("IS_MAINTANCER"));
            setTester(res.getBoolean("IS_TESTER"));
            setProgrammer(res.getBoolean("IS_PROGRAMMER"));
            setTechWriter(res.getBoolean("IS_TECH_WRITER"));
        } else {
            throw new NoDataFoundException(id.toString());
        }
    }

    public Vector<Pair> getCustomers() throws SQLException {
        Vector<Pair> customers = new Vector<Pair>();
        Connection conn = ConnectionProvider.getConnection();
        PreparedStatement s = conn.prepareStatement(SELECT_MAINTANCER_CUSTOMERS);
        s.setBigDecimal(1, id);
        ResultSet res = s.executeQuery();
        while (res.next()) {
            customers.add(new Pair(res.getBigDecimal("ID"), res.getString("NAME")));
        }
        return customers;

    }
    public Vector<String> getDevelopingLanguages() throws SQLException {
        Vector<String> languages = new Vector<String>();
        Connection conn = ConnectionProvider.getConnection();
        PreparedStatement s = conn.prepareStatement(SELECT_DEVELOP_LANGUAGES);
        s.setBigDecimal(1, id);
        ResultSet res = s.executeQuery();
        while (res.next()) {
            languages.add(res.getString("LANG_NAME"));
        }
        return languages;

    }
    public Vector<String> getWriterLanguages() throws SQLException {
        Vector<String> languages = new Vector<String>();
        Connection conn = ConnectionProvider.getConnection();
        PreparedStatement s = conn.prepareStatement(SELECT_WRITER_LANGUAGES);
        s.setBigDecimal(1, id);
        ResultSet res = s.executeQuery();
        while (res.next()) {
            languages.add(res.getString("LANG_NAME"));
        }
        return languages;

    }
    public String getExperience() throws SQLException {
        String exp = "";
        Connection conn = ConnectionProvider.getConnection();
        PreparedStatement s = conn.prepareStatement(SELECT_EXPERIENCE);
        s.setBigDecimal(1, id);
        ResultSet res = s.executeQuery();
        while (res.next()) {
            exp = res.getString("EXPERIENCE");
        }
        return exp;

    }

    public Vector<Pair> selectApplications() throws SQLException {
        Vector<Pair> v = new Vector<Pair>();

        Connection conn = ConnectionProvider.getConnection();
        PreparedStatement s = conn.prepareStatement(SELECT_APPLICATIONS);
        s.setBigDecimal(1, id);
        ResultSet res = s.executeQuery();
        while (res.next()) {
            v.add(new Pair(res.getBigDecimal("ID"), res.getString("NAME")));
        }


        return v;
    }
}
