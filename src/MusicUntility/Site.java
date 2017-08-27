package MusicUntility;

public class Site {
	int tune = 0;
	int length = 12;
	
	Site(int tune, int length){
		this.tune = tune;
		this.length = length;
	}
	
	Site(String tune){
		this.length = 13;
		this.tune = Note.parseStringToInteger(tune);
	}
	
	public String printScale(Scale scale) {
		String s = Note.parseIntegerToString(tune);
		if(scale.inScale(tune)){
			s+= scale.NoteInScale(tune) + ":";
		} else{
			s+= " :";
		}
		int note = (tune+1)%Scale.OCTAVE_LENGHT;
		for(int i = 0; i < length; i++){
			if(scale.inScale(note)){
				s += String.format("  %1d |", scale.NoteInScale(note));
			} else {
				s += "    |";
			}
			note = (note +1)%Scale.OCTAVE_LENGHT;
		}
		return s;
	}
	
	public String getTune(){
		return Note.parseIntegerToString(tune);
	}
	
	public int getTuneNummber(){
		return tune;
	}
	
	public int getLenght(){
		return length;
	}
	
	@Override
	public String toString() {
		String s = Note.parseIntegerToString(tune) + ":  ";
		int note = tune+1;
		for(int i = 0; i < length; i++){
			s += String.format("%5s |  ", Note.parseIntegerToString(note));
			note = (note +1)%Scale.OCTAVE_LENGHT;
		}
		return s;
	}
	
	public static String printFret(int length){
		String s = "   ";
		for(int i = 0; i < length; i++){
			s += String.format("%3d  ", i+1);
		}
		return s;
	}
}
