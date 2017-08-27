package MusicUntility;

public class Scale {
	final static int SCALE_LENGHT = 8;
	public final static int OCTAVE_LENGHT = 12;
	
	final static int[] MAJOR = {2,2,1,2,2,2,1,2,2};
	final static int[] MINOR = {2,1,2,2,1,2,2,2,1};
	
	Tune root = Tune.C;
	boolean isMajor = true;
	
	int[] scale = {1,0,2,0,3,4,0,5,0,6,0,7};
	
	public Scale(Tune root, boolean isMajor){
		this.root = root;
		this.isMajor = isMajor;
		
		scale = Scale.getScale(this.root, isMajor);
	}
	
	/**
	 * @param root the first tone of the Scale
	 * @param isMajor - whether or not it is a major or a minor scale.
	 */
	public Scale(String root, boolean isMajor){
		this(Note.parseString(root), isMajor);
	}
	
	public Tune getRoot(){
		return root;
	}
	
	public int NoteInScale(int note){
		return scale[note%OCTAVE_LENGHT];
	}
	
	public boolean inScale(int note){
		note = note % OCTAVE_LENGHT;
		if(note >= OCTAVE_LENGHT || note < 0) {
			return false;
		}
		return scale[note] != 0;
	}
	
	public String getName(){
		return Note.toString(root) + "\'" + (isMajor ? "Major" : "Minor");
	}
	
	@Override
	public String toString() {
		String s = "Scale:\n" + Note.toString(root) + "\'" + (isMajor ? "Major" : "Minor") + ": ";
		Tune note = root;
		for(int i = 0; i < SCALE_LENGHT-1; i++){
			s += String.format(" %s ", center(Note.toString(note), 5));
			if((isMajor?MAJOR:MINOR)[i] == 2){
				s+= "^";
			} else {
				s+= "-";
			}
			note = Note.parseInteger((Note.toInteger(note) + (isMajor ? MAJOR : MINOR)[i]));
		}
		return s;
	}
	
	public String printChord() {
		String s = "Chords:\n";
		Tune note = root;
		int[] halfsteps = (isMajor ? MAJOR : MINOR);
		for(int i = 0; i < SCALE_LENGHT-1; i++){
			s += String.format("%s ", Note.toString(note));
			note = Note.parseInteger((Note.toInteger(note) + (isMajor ? MAJOR : MINOR)[i]));
			s += (halfsteps[i] + halfsteps[(i+1) % SCALE_LENGHT] == 4) ? "Maj" : "Min";
			s += " - ";
		}
		return s;
	}
	
	public static int[] getScale(Tune root, boolean isMajor){
		int[] scale = new int[OCTAVE_LENGHT];
		int n = Note.toInteger(root);
		for(int i = 0; i+1 < SCALE_LENGHT ; i++){
			scale[n] = i+1;
			n = (n + (isMajor ? MAJOR : MINOR)[i]) % OCTAVE_LENGHT;
		}
		return scale;
	}
	
	public static String center(String input, int lenght){
		String out = "";
		int spaces = input.length() - lenght;
		for(int i = 0; i < Math.ceil((float)spaces/2f); i++){
			out += " ";
		}
		out += input;
		for(int i = 0; i < Math.floor((float)spaces/2f); i++){
			out += " ";
		}
		return out;
	}
}
