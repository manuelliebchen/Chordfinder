import MusicUntility.Guitar;
import MusicUntility.Piano;
import MusicUntility.Scale;
import MusicUntility.Site;
import processing.core.PApplet;

public class GUI extends PApplet {
	Scale scale;
	Guitar guitare;
	Piano piano;
	
	public GUI() {
		super();
		scale = new Scale(0, true);
		guitare = new Guitar(scale);
		piano = new Piano(scale);
	}
	
	@Override
	public void settings() {
		size(800, 600);
		super.settings();
	}
	
	@Override
	public void setup() {
		colorMode(HSB, 360, 100, 100);
		noLoop();
		super.setup();
	}
	
	@Override
	public void draw() {
		background(224);
		
		//Draw Piano
		pushStyle();
		fill(255);
		stroke(0);
		strokeWeight(3);
		
		pushMatrix();
		translate(50,0);
		
		//hole keys
		pushMatrix();
		int tune = 0;
		for(int i = 0; i < 14; i++){
			fill(0, 0, 100);
			if(scale.inScale(tune)){
				fill(getHueOfNote(scale.NoteInScale(tune)), 80, 80);
			}
			rect(0, 0, 50, 300);
			translate(50, 0);
			tune += 2;
			if(i % 7 == 2 || i % 7 == 6){
				tune--;
			}
		}
		popMatrix();
		
		//half keys
		pushMatrix();
		fill(0,0,0);
		translate(35, 0);
		tune = 1;
		for(int i = 0; i < 10; i++){
			fill(0);
			if(scale.inScale(tune)){
				fill(getHueOfNote(scale.NoteInScale(tune)), 80, 80);
			}
			rect(0, 0, 30, 200);
			translate(50, 0);
			tune += 2;
			if(i % 5 == 1||i % 5 == 4){
				tune++;
				translate(50, 0);
			}
		}
		popMatrix();
		popMatrix();
		popStyle();
		
		
		//Draw Guitar
		pushStyle();
		fill(0, 0, 100);
		stroke(0,0,0);
		strokeWeight(3);
		
		pushMatrix();
		translate(150,350);
		rect(0,0,600, 180);
		
		//Bars
		pushMatrix();
		translate(50, 0);
		for(int i = 0; i < 14; i++){
			line(0, 0, 0, 180);
			translate(50, 0);
		}
		popMatrix();
		
		//Strings
		pushMatrix();
		translate(0, 15);
		fill(0);
		textSize(20);
		for(int i = 0; i < 6; i++){
			Site s = guitare.getString(i);
			tune = s.getTuneNummber();
			if(scale.inScale(tune)){
				fill(getHueOfNote(scale.NoteInScale(tune)), 80, 80);
				ellipse(-20, 0, 25, 25);
			}
			fill(0);
			text(s.getTune(),-28,7);
			line(0, 0, 600, 0);
			pushMatrix();
			pushStyle();
			fill(128);
			for(int j = 1; j < s.getLenght(); j++){
				fill(128);
				if (scale.inScale(tune + j)) {
					fill(getHueOfNote(scale.NoteInScale(tune + j)), 80, 80);
					ellipse(25, 0, 25, 25);
				}
				translate(50,0);
			}
			popStyle();
			popMatrix();
			translate(0, 30);
		}
		popMatrix();
		
		popMatrix();
		popStyle();
		
	}
	
	@Override
	public void mouseClicked() {
		//half keys
		int tune = 1;
		int xpos = 35+50;
		for(int i = 0; i < 10; i++){
			if(mouseX > xpos && mouseX < xpos + 30 && mouseY > 0 && mouseY < 200){
				scale = new Scale(tune, true);
				guitare.setScale(scale);
				piano.setScale(scale);
				redraw();
				return;
			}
			xpos += 50;
			tune += 2;
			if(i % 5 == 1||i % 5 == 4){
				tune++;
				xpos += 50;
			}
		}
		
		//hole keys
		tune = 0;
		xpos = 50;
		for(int i = 0; i < 14; i++){
			if(mouseX > xpos && mouseX < xpos + 50 && mouseY > 0 && mouseY < 300){
				scale = new Scale(tune, true);
				guitare.setScale(scale);
				piano.setScale(scale);
				redraw();
				return;
			}
			xpos += 50;
			tune += 2;
			if(i % 7 == 2 || i % 7 == 6){
				tune--;
			}
		}
		
		
	}
	
	private final float[] toneColor = {0,30,60,120,180,240,300};
	
	public float getHueOfNote(int inScale){
		return toneColor[(inScale - 1)%7];
	}
}
