/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

/**
 *
 * @author Admin
 */
public class ApplicationFilter {
    Filter filter = new Filter();

    public ApplicationFilter(){
        filter.put(ApplicationType.FREE, Boolean.TRUE);
        filter.put(ApplicationType.INDIVIDUAL, Boolean.TRUE);
        filter.put(ApplicationType.INNER, Boolean.TRUE);
        filter.put(ApplicationType.OPTIONAL, Boolean.TRUE);
        filter.put(ApplicationType.OTHERS, Boolean.TRUE);
    }

    public void setShowFree(boolean show){
        filter.put(ApplicationType.FREE, show);
    }

    public boolean isShowFree(){
        return filter.get(ApplicationType.FREE);
    }
    public void setShowIndividual(boolean show){
        filter.put(ApplicationType.INDIVIDUAL, show);
    }

    public boolean isShowIndividual(){
        return filter.get(ApplicationType.INDIVIDUAL);
    }
    public void setShowIner(boolean show){
        filter.put(ApplicationType.INNER, show);
    }

    public boolean isShowInner(){
        return filter.get(ApplicationType.INNER);
    }
    public void setShowOptional(boolean show){
        filter.put(ApplicationType.OPTIONAL, show);
    }

    public boolean isShowOptional(){
        return filter.get(ApplicationType.OPTIONAL);
    }
    public void setShowOthers(boolean show){
        filter.put(ApplicationType.OTHERS, show);
    }

    public boolean isShowOthers(){
        return filter.get(ApplicationType.OTHERS);
    }


}
