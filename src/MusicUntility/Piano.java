package MusicUntility;

import processing.core.PApplet;

public class Piano {
	
	String pianoLayout= 
			 "|  |     |     |  |  |     |     |     |  |\n"
			+"|  |     |     |  |  |     |     |     |  |\n"
			+"|  |     |     |  |  |     |     |     |  |\n"
			+"|  |  %c  |  %c  |  |  |  %c  |  %c  |  %c  |  |\n"
			+"|  |     |     |  |  |     |     |     |  |\n"
			+"|  |     |     |  |  |     |     |     |  |\n"
			+"|  |__ __|_____|  |  |_____|_____|_____|  |\n"
			+"|     |     |     |     |     |     |     |\n"
			+"|     |     |     |     |     |     |     |\n"
			+"|  %c  |  %c  |  %c  |  %c  |  %c  |  %c  |  %c  |\n"
			+"|     |     |     |     |     |     |     |\n"
			+"|     |     |     |     |     |     |     |\n"
			+"|_____|_____|_____|_____|_____|_____|_____|";
			
	
	public String printScale(Scale scale) {
		char[] chars = new char[12];
		for(int i = 0; i < chars.length; ++i){
			chars[i] = (scale.inScale(i) ? Character.forDigit(scale.NoteInScale(i), 10) : ' ');
		}
		String s = String.format("Piano:\n" + pianoLayout, chars[1], chars[3], chars[6], chars[8], chars[10], chars[0], chars[2], chars[4], chars[5], chars[7], chars[9], chars[11]);
		return s;
	}
	
	
	private final int PIANO_KEY_HEIGHT = 300;
	private final int PIANO_KEY_WIDTH = 50;
	
	public void darw(PApplet applet, Scale scale){

		applet.pushStyle();
		applet.fill(0,0,100);
		applet.stroke(0);
		applet.strokeWeight(3);
		
		//hole keys
		applet.pushMatrix();
			int tune = 0;
			for(int i = 0; i < 14; i++){
				applet.fill(0, 0, 100);
				if(scale.inScale(tune)){
					applet.fill(Note.getHueOfNote(scale.NoteInScale(tune)), 80, 80);
				}
				applet.rect(0, 0, PIANO_KEY_WIDTH, PIANO_KEY_HEIGHT);
				applet.translate(PIANO_KEY_WIDTH, 0);
				tune += 2;
				if(i % 7 == 2 || i % 7 == 6){
					tune--;
				}
			}
		applet.popMatrix();
		
		//half keys
		applet.pushMatrix();
			applet.fill(0,0,0);
			applet.translate(PIANO_KEY_WIDTH/2, 0);
			tune = 1;
			for(int i = 0; i < 10; i++){
				applet.fill(0,0,0);
				if(scale.inScale(tune)){
					applet.fill(Note.getHueOfNote(scale.NoteInScale(tune)), 80, 80);
				}
				applet.rect(10, 0, PIANO_KEY_WIDTH * 3/5, PIANO_KEY_HEIGHT * 2f/3f);
				applet.translate(PIANO_KEY_WIDTH, 0);
				tune += 2;
				if(i % 5 == 1||i % 5 == 4){
					tune++;
					applet.translate(PIANO_KEY_WIDTH, 0);
				}
			}
		applet.popMatrix();
		
		applet.popStyle();
	}
	
	public int getWidth(){
		return PIANO_KEY_WIDTH * 14;
	}
	
	public int getKeyWidth(){
		return PIANO_KEY_WIDTH;
	}
	
	public int getHeight(){
		return PIANO_KEY_HEIGHT;
	}
}
