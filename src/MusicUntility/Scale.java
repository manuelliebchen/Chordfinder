package MusicUntility;
import java.util.InputMismatchException;

public class Scale {
	final static int SCALE_LENGHT = 8;
	public final static int OCTAVE_LENGHT = 12;
	
	final static int[] MAJOR = {2,2,1,2,2,2,1,2,2};
	final static int[] MINOR = {2,1,2,2,1,2,2,2,1};
	final static String[] NAMES = {
		"C",
		"C#/Db",
		"D",
		"D#/Eb",
		"E",
		"F",
		"F#/Gb",
		"G",
		"G#/Ab",
		"A",
		"A#/Bb",
		"B"
	};
	
	int root = 0;
	boolean isMajor = true;
	
	int[] scale = {1,0,2,0,3,4,0,5,0,6,0,7};
	
	public Scale(int root, boolean isMajor){
		this.root = root;
		this.isMajor = isMajor;
		
		scale = Scale.getScale(root, isMajor);
	}
	
	/**
	 * @param root the first tone of the Scale
	 * @param isMajor - whether or not it is a major or a minor scale.
	 */
	public Scale(String root, boolean isMajor){
		this(getNote(root), isMajor);
	}
	
	public int getRoot(){
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
		return Scale.getName(root) + "\'" + (isMajor ? "Major" : "Minor");
	}
	
	@Override
	public String toString() {
		String s = "Scale:\n" + getName(root) + "\'" + (isMajor ? "Major" : "Minor") + ": ";
		int note = root;
		for(int i = 0; i < SCALE_LENGHT-1; i++){
			s += String.format(" %s ", center(getName(note), 5));
			if((isMajor?MAJOR:MINOR)[i] == 2){
				s+= "^";
			} else {
				s+= "-";
			}
			note = (note + (isMajor ? MAJOR : MINOR)[i]) % OCTAVE_LENGHT;
		}
		return s;
	}
	
	public String printChord() {
		String s = "Chords:\n";
		int note = root;
		int[] halfsteps = (isMajor ? MAJOR : MINOR);
		for(int i = 0; i < SCALE_LENGHT-1; i++){
			s += String.format("%s ", getName(note));
			note = (note + halfsteps[i]) % OCTAVE_LENGHT;
			s += (halfsteps[i] + halfsteps[(i+1) % SCALE_LENGHT] == 4) ? "Maj" : "Min";
			s += " - ";
		}
		return s;
	}
	
	public static int[] getScale(int root, boolean isMajor){
		int[] scale = new int[OCTAVE_LENGHT];
		root = root % OCTAVE_LENGHT;
		int n = root;
		for(int i = 0; i+1 < SCALE_LENGHT ; i++){
			scale[n] = i+1;
			n = (n + (isMajor ? MAJOR : MINOR)[i]) % OCTAVE_LENGHT;
		}
		return scale;
	}
	
	public static String getName(int note){
		return NAMES[note%12];
	}
	
	public static int getNote(String s){
		for(int i = 0; i<NAMES.length; ++i){
			if(s.equals(NAMES[i])){
				return i;
			}
		}
		throw new InputMismatchException("This is no Note");
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
