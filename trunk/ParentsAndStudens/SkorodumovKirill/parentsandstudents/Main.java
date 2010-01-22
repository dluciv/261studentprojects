//by Skorodumov Kirill gr.: 261
//parentsandstudents task

package parentsandstudents;

import java.util.LinkedList;

public class Main {
	
	private static void printHumanInfo(IHuman human)
	{
		System.out.println(human.getSurname());
		System.out.println(human.getName());
		System.out.println(human.getPatronymic());
		System.out.println(human.getAge());
		System.out.println(human.getSex());
		System.out.println("");
	}
	
	public static void main(String[] args)
	{
		LinkedList<IHuman> humans = generator.genCollection();
		
		for (IHuman human : humans){
			printHumanInfo(human);
		}
		
		System.out.println("Количество денег у крутых атцов:");
		System.out.println(Calculator.calcMoney(humans));
		
		System.out.println("Средняя оценка у ботанов:");
		System.out.println(Calculator.calcMark(humans));
	}
}
