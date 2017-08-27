package core;
import MusicUntility.Guitar;
import MusicUntility.Note;
import MusicUntility.Piano;
import MusicUntility.Scale;
import processing.core.PApplet;

public class Chordfinder{

	public static void main(String[] args) {
		Scale scale; 
		Guitar gitarre;
		Piano piano;
		for(int i = 0; i < args.length; ++i){
			switch(args[i]){
			case "-s":
				scale = new Scale(args[i+1], (args[i+2].equals("Minor")?false:true));
				gitarre = new Guitar();
				
				System.out.println(scale);
				System.out.println();
				System.out.println(scale.printChord());
				System.out.println();
				System.out.println(gitarre.printScale(scale));
				
				piano = new Piano();
				System.out.println();
				System.out.println(piano.printScale(scale));
				i+=2;
				return;
			case "-a":
				for(int j = 0; j < Scale.OCTAVE_LENGHT; j++){
					scale = new Scale(Note.parseInteger(j), true);
					gitarre = new Guitar();
					System.out.println(scale);
					System.out.println(gitarre.printScale(scale));
					System.out.println();
				}
				return;
			case "-h":
				System.out.println("-h: for this massage.");
				System.out.println("-s: [key][mode] like D#/Eb Major");
				System.out.println("-a: to print all scales with guitar");
				System.out.println("default: to open the GUI");
				System.out.println("To change the tunes of the guitar got to ~/.chordfinder/tune.txt");
				return;
			}
		}
		PApplet.main("core.GUI");
	}
	
}

