package test;

//serialization/deserialization/transient, static fields behavior

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;



public class Main  {
	
	public static void main(String[] args) throws Exception {
		
		Class2 class2 = new Class2();		
		class2.varTransientClass2  = 1;
		Class2.varStaticClass2 = 1;
		class2.varClass2 = 1;
		
		//class2.level4.level=23;
		
		Class1 class1 = new Class1();
		class1.varTransientClass1 = 1;
		class2.varClass1InClass2 = class1;
		
		
		FileOutputStream outputStream = new FileOutputStream("test");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		objectOutputStream.writeObject(class2);
		objectOutputStream.close();
		
		class2.varTransientClass2=35;
		Class2.varStaticClass2=35;
		class2.varClass2=35;
		class2.varClass1InClass2.varTransientClass1=35;
		
		class2 = null;
	
		FileInputStream  fileInputStream = new FileInputStream("test");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		Class2 newClass2 = (Class2) objectInputStream.readObject();
		objectInputStream.close();
		
		StringBuilder outLineBuilder = new StringBuilder();
		outLineBuilder.append("transient: " + newClass2.varTransientClass2 +"\n");
		outLineBuilder.append("static field: " + Class2.varStaticClass2 + "\n");
		outLineBuilder.append("regular variable: " + newClass2.varClass2 + "\n");
		outLineBuilder.append("transient variable of Class1 in Class2: " + newClass2.varClass1InClass2.varTransientClass1 + "\n");
		System.out.println(outLineBuilder);
		
		//System.out.println(newClass2.varTransientClass2 + " " + newClass2.varStaticClass2 + " " + newClass2.varClass2 + " " + newClass2.varClass1InClass2.varTransientClass1);
			
	}
}


class Class2 implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1430049140200805012L;
	transient int  varTransientClass2;
	static int varStaticClass2;
	int varClass2;
	Class1 varClass1InClass2;
}

class Class1 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4170183401132882300L;
	transient int varTransientClass1;	
}
