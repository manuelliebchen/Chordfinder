import MusicUntility.Guitar;
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
				gitarre = new Guitar( scale);
				
				System.out.println(scale);
				System.out.println();
				System.out.println(scale.printChord());
				System.out.println();
				System.out.println(gitarre);
				
				piano = new Piano( scale);
				System.out.println();
				System.out.println(piano);
				i+=2;
				break;
			case "-a":
				for(int j = 0; j < Scale.OCTAVE_LENGHT; j++){
					scale = new Scale(j, true);
					gitarre = new Guitar( scale);
					System.out.println(scale);
					System.out.println(gitarre);
					System.out.println();
				}
				break;
			case "-g":
				PApplet.main("GUI");
				break;
			default:
				System.out.println("-s: [key][mode] like D#/Eb Major");
				System.out.println("-a: to print all scales with guitar");
				System.out.println("-g: to open the GUI");
				break;
			}
		}
	}
	
}

