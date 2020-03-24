package opgave2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainApp {

	public static void main(String[] args) {
		
		try {
			FileInputStream f_in = new FileInputStream("myobject.data");

			ObjectInputStream obj_in = new ObjectInputStream(f_in);

			MyTime op2 = null;
			try {
				op2 = (MyTime) obj_in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			System.out.println(op2.getTime());
			obj_in.close();
			
//         -----------------------------------------------------------------------------------------------------------------

//			FileOutputStream f_out = new FileOutputStream("myobject.data");
//
//			ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
//
//			
//			op2.increaseTime();
//			op2.increaseTime();
//			op2.increaseTime();
//			op2.increaseTime();
//			obj_out.writeObject(op2);
//			obj_out.close();

		} catch (IOException i) {
			i.printStackTrace();
		}

	}

}
