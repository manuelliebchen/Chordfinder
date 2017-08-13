
public class Site {
	int tune = 0;
	int length = 12;
	
	Site(int tune, int length){
		this.tune = tune;
		this.length = length;
	}
	
	Site(String tune){
		this.length = 12;
		this.tune = Scale.getNote(tune);
	}
	
	public String printScale(Scale scale) {
		String s = Scale.getName(tune);
		if(scale.inScale(tune)){
			s+= scale.NoteInScale(tune) + ":";
		} else{
			s+= " :";
		}
		int node = (tune+1)%Scale.OCTAVE_LENGHT;
		for(int i = 0; i < length; i++){
			if(scale.inScale(node)){
				s += String.format("  %1d |", scale.NoteInScale(node));
			} else {
				s += "    |";
			}
			node = (node +1)%Scale.OCTAVE_LENGHT;
		}
		return s;
	}
	
	public int getLenght(){
		return length;
	}
	
	@Override
	public String toString() {
		String s = Scale.getName(tune) + ":  ";
		int node = tune+1;
		for(int i = 0; i < length; i++){
			s += String.format("%5s |  ", Scale.getName(node));
			node = (node +1)%Scale.OCTAVE_LENGHT;
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
