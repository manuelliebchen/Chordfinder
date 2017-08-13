
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
			default:
				System.out.println("[key][mode]\nlike\tD#/Eb Major");
				break;
			}
		}
	}
	
}

