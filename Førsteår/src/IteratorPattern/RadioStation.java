package IteratorPattern;

public class RadioStation {
	
	public static void main(String[] args){
		
		SongsOfThe70s songs70s = new SongsOfThe70s();
		SongsOfThe80s songs80s = new SongsOfThe80s();
		SongsOfThe90s songs90s = new SongsOfThe90s();
		
		songs70s.addSong("Gucci", "mane", 0);

		songs80s.addSong("Hansi", "mane", 2);
		
		songs90s.addSong("Egon", "mane", 4);
		
		
		DiscJockey madMike = new DiscJockey(songs70s, songs80s, songs90s);
		
		madMike.showTheSongs2();
		
	}
	
}