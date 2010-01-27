/*
 * (c) Stefan Bojarovski 2009
 * */
package fathers_sons;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomListGenerator {
	 
	private static final Random rand = new Random();
	 
	 private static final String[] MALE_NAMES = new String[]{
		 "Stefan", "Vladimir", "Ivan", "Boris", "Yaroslav"
	 };
	 private static final String[] FEMALE_NAMES = new String[]{
		 "Lena", "Olya", "Natasha", "Sveta", "Tanya"
	 };
	 private static final String[] SURNAMES = new String[]{
		 "Smirnov", "Ivanov", "Kuznecov", "Sokolov", "Popov" 
	 };
	 private static final String[] FACULTIES = new String[]{
		 "Mat-Meh", "PM-PU", "Him-Fak", "Fiz-Fak", "Meh-Mat"
	 };
	 
	 public static String generateName(Sex sex) {
	        if (sex == Sex.FEMALE) {
	            return FEMALE_NAMES[rand.nextInt(FEMALE_NAMES.length)];
	        } else {
	            return MALE_NAMES[rand.nextInt(MALE_NAMES.length)];
	        }
	    }
	 
	 public static String generateSurname(Sex sex) {
	        if (sex == Sex.FEMALE) {
	            return SURNAMES[rand.nextInt(SURNAMES.length)] + "a";
	        } else {
	            return SURNAMES[rand.nextInt(SURNAMES.length)];
	        }
	    }
	 
	 public static String generatePatronymic(String parentsName, Sex sex) {
	        if (sex == Sex.FEMALE) {
	            return parentsName + "ovna";
	        } else {
	            return parentsName + "ovich";
	        }
	    }
	 
	 public static String generateFaculty() {
	        return FACULTIES[rand.nextInt(FACULTIES.length)];
	    }

	 public static Student generateStudent(String parentsName, String surname) {
		 
		 Sex sex = Sex.values()[rand.nextInt(2)];
         String name = generateName(sex);
         String patronymic = generatePatronymic(parentsName, sex);
         String faculty = generateFaculty();
         int age = 17 + rand.nextInt(10);

	     if (rand.nextBoolean()){
	    	 return new Student(name, surname, patronymic, sex, age, faculty);
	        } else {
	         return new Botan(name, surname, patronymic, sex, age, faculty);
	        }
	    }

	 public static Parent generateParent() {
		 
		 Sex sex = Sex.MALE;
		 String name = generateName(sex);
	     String surname = generateSurname(sex);
	     String patronymic = generatePatronymic(generateName(Sex.MALE), sex);
	     int age = 30 + rand.nextInt(10);
	     
	     int numberOfChildren = 1 + rand.nextInt(5);
	     Student[] children = new Student[numberOfChildren];
         for (int i = 0; i < numberOfChildren; i++) {
        	 children[i] = generateStudent(name, surname);
         }

         if (rand.nextBoolean()) {
        	 return new Parent(name, surname, patronymic, sex, age, children);
         } else {
        	 return new CoolParent(name, surname, patronymic, sex, age, children);
         }
	 }

     public static List<IHuman> generateCollection(){
    	 
         LinkedList<IHuman> people = new LinkedList<IHuman>();

         int numberOfParents = rand.nextInt(20);
         for (int i = 0; i < numberOfParents; i++) {
            Parent p = generateParent();
            people.add(p);

            for (int j = 0; j < p.getNumberOfChildren(); j++){
            	people.add(p.getChild(j));
            }
         }
         return people;
     }
}
