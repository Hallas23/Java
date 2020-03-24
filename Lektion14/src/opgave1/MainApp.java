package opgave1;

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

			Opgave1 op1 = null;
			try {
				op1 = (Opgave1) obj_in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			System.out.println(op1.getTime());
			obj_in.close();
			
//         -----------------------------------------------------------------------------------------------------------------

			FileOutputStream f_out = new FileOutputStream("myobject.data");

			ObjectOutputStream obj_out = new ObjectOutputStream(f_out);

			
			op1.increaseTime();
			op1.increaseTime();
			op1.increaseTime();
			op1.increaseTime();
			obj_out.writeObject(op1);
			obj_out.close();

		} catch (IOException i) {
			i.printStackTrace();
		}

	}

}
