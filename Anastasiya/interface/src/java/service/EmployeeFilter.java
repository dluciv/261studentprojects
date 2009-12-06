/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

/**
 *
 * @author Admin
 */
public class EmployeeFilter {
    Filter filter = new Filter();

    public EmployeeFilter(){
        filter.put(EmployeeType.MAINTANCER, Boolean.TRUE);
        filter.put(EmployeeType.PROGRAMMER, Boolean.TRUE);
        filter.put(EmployeeType.TECHNICAL_WRITER, Boolean.TRUE);
        filter.put(EmployeeType.TESTER, Boolean.TRUE);
        filter.put(EmployeeType.OTHERS, Boolean.TRUE);
    }

    public void setShowMaintancer(boolean show){
        filter.put(EmployeeType.MAINTANCER, show);
    }

    public void setShowTester(boolean show){
        filter.put(EmployeeType.TESTER, show);
    }

    public void setShowProgrammer(boolean show){
        filter.put(EmployeeType.PROGRAMMER, show);
    }

    public void setShowTechnicalWriter(boolean show){
        filter.put(EmployeeType.TECHNICAL_WRITER, show);
    }
    public void setShowOthers(boolean show){
        filter.put(EmployeeType.OTHERS, show);
    }

    public boolean isShowMaintancer(){
        return filter.get(EmployeeType.MAINTANCER);
    }

    public boolean isShowTester(){
        return filter.get(EmployeeType.TESTER);
    }

    public boolean isShowProgrammer(){
        return filter.get(EmployeeType.PROGRAMMER);
    }

    public boolean isShowTechnicalWriter(){
        return filter.get(EmployeeType.TECHNICAL_WRITER);
    }

    public boolean isShowOthers(){
        return filter.get(EmployeeType.OTHERS);
    }


}
