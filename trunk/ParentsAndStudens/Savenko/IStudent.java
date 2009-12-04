package msavenko.parentsandstudens;


public interface IStudent extends IHuman {
    
    public String getFaculty();
    
    public int getMarkForExam(int index);
    
    public int getNumberOfExams();

}
