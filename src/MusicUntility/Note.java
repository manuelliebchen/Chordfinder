package MusicUntility;

import java.util.InputMismatchException;

public final class Note{
	
	private Note(){}
	
	public static int toInteger(Tune tune){
		switch (tune) {
		case C:		return 0;
		case Csharp:return 1;
		case Dflat:	return 1;
		case D: 	return 2;
		case Dsharp:return 3;
		case Eflat: return 3;
		case E: 	return 4;
		case F: 	return 5;
		case Fsharp:return 6;
		case Gflat: return 6;
		case G: 	return 7;
		case Gsharp:return 8;
		case Aflat: return 8;
		case A: 	return 9;
		case Asharp:return 10;
		case Bflat: return 10;
		case B: 	return 11;
		}
		return -1;
	}
	
	public static String toString(Tune tune){
		switch (tune) {
		case C:		return "C";
		case Csharp:return "C#";
		case Dflat:	return "Db";
		case D: 	return "D";
		case Dsharp:return "D#";
		case Eflat: return "Eb";
		case E: 	return "E";
		case F: 	return "F";
		case Fsharp:return "F#";
		case Gflat: return "Gb";
		case G: 	return "G";
		case Gsharp:return "G#";
		case Aflat: return "Ab";
		case A: 	return "A";
		case Asharp:return "A#";
		case Bflat: return "Bb";
		case B: 	return "B";
		}
		return "";
	}
	
	public static Tune parseInteger(int tune){
		tune %= 12;
		switch(tune){
		case 0:		return Tune.C;
		case 1:		return Tune.Csharp;
		case 2:		return Tune.D;
		case 3:		return Tune.Dsharp;
		case 4:		return Tune.E;
		case 5:		return Tune.F;
		case 6:		return Tune.Fsharp;
		case 7:		return Tune.G;
		case 8:		return Tune.Gsharp;
		case 9:		return Tune.A;
		case 10:	return Tune.Asharp;
		case 11:	return Tune.B;
		}
		return null;
	}

	public static Tune parseString(String tune){
		switch(tune){
		case "C":		return Tune.C;
		case "C#":		return Tune.Csharp;
		case "Db":		return Tune.Dflat;
		case "D":		return Tune.D;
		case "D#":		return Tune.Dsharp;
		case "Eb":		return Tune.Eflat;
		case "E":		return Tune.E;
		case "F":		return Tune.F;
		case "F#":		return Tune.Fsharp;
		case "Gb":		return Tune.Gflat;
		case "G":		return Tune.G;
		case "G#":		return Tune.Gsharp;
		case "Ab":		return Tune.Aflat;
		case "A":		return Tune.A;
		case "A#":		return Tune.Asharp;
		case "Bb":		return Tune.Bflat;
		case "B":		return Tune.B;
		case "C#/Db":	return Tune.Csharp;
		case "D#/Eb":	return Tune.Dsharp;
		case "F#/Gb":	return Tune.Fsharp;
		case "G#/Ab":	return Tune.Gsharp;
		case "A#/Bb":	return Tune.Asharp;
		}
		throw new InputMismatchException("This is no Note");
	}
	
	public static int getMIDI(Tune tune, int octave){
		return toInteger(tune) + (octave + 1) * 12;
	}
	
	public static float getFrequency(int MIDI){
		return (float) (440f * Math.pow(2f, (MIDI-69f)/12f));
	}
	
	public static String parseIntegerToString(int tune){
		return toString(parseInteger(tune));
	}
	
	public static int parseStringToInteger(String tune){
		return toInteger(parseString(tune));
	}
	
	private final static float[] TUNE_HUE = {0,180,30,240,60,300,120};

	public static float getHueOfNote(int inScale){
		return TUNE_HUE[(inScale - 1)%7];
	}
}
