import MusicUntility.Guitar;
import MusicUntility.Piano;
import MusicUntility.Scale;
import MusicUntility.Site;
import processing.core.PApplet;

public class GUI extends PApplet {
	private final int GUITAR_STRING_HEIGHT = 30;
	private final int GUITAR_BAR_WIDTH = 50;
	
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
		rect(0,0, (guitare.getString(0).getLenght() -1) * GUITAR_BAR_WIDTH, guitare.getStringNum() * GUITAR_STRING_HEIGHT);
		
		//Bars
		pushMatrix();
		translate(GUITAR_BAR_WIDTH, 0);
		for(int i = 1; i < guitare.getString(0).getLenght(); i++){
			line(0, 0, 0, guitare.getStringNum() * GUITAR_STRING_HEIGHT);
			translate(GUITAR_BAR_WIDTH, 0);
		}
		popMatrix();
		
		//Strings
		pushMatrix();
		translate(0, GUITAR_STRING_HEIGHT * 0.5f);
		fill(0);
		textSize(20);
		for(int i = 0; i < guitare.getStringNum(); i++){
			Site s = guitare.getString(i);
			tune = s.getTuneNummber();
			if(scale.inScale(tune)){
				fill(getHueOfNote(scale.NoteInScale(tune)), 80, 80);
				ellipse(-20, 0, 25, 25);
			}
			fill(0);
			text(s.getTune(),-28,7);
			line(0, 0, (guitare.getString(0).getLenght() -1) * GUITAR_BAR_WIDTH, 0);
			pushMatrix();
			pushStyle();
			for(int j = 1; j < s.getLenght(); j++){
				if (scale.inScale(tune + j)) {
					fill(getHueOfNote(scale.NoteInScale(tune + j)), 80, 80);
					ellipse(25, 0, 25, 25);
				}
				translate(50,0);
			}
			popStyle();
			popMatrix();
			translate(0, GUITAR_STRING_HEIGHT);
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
