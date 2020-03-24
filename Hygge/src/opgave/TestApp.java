package opgave;

import java.util.ArrayList;

public class TestApp {

	public static void main(String[] args) {
		Abonnement a1 = new Abonnement(200, 10);
		Abonnement a2 = new TimePakke(300, 100, 50);
		Abonnement a3 = new UdvidelsesPakke(400, 100, 100, 200);
		
		ArrayList<Abonnement> abonnementer = new ArrayList<>();
		
		abonnementer.add(a1);
		abonnementer.add(a2);
		abonnementer.add(a3);
		
		System.out.println(sumliste(abonnementer));
	}
	public static String sumliste (ArrayList<Abonnement> alist) {
		int result = 0;
		String abonnementer = "";
		for (int i = 0; i < alist.size(); i++) {
			result += alist.get(i).abonnementsPris();
			abonnementer += " " + "\n" + alist.get(i).abonnementsPris();
		}
		
		return result + abonnementer;
		
	}
}
