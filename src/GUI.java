import java.io.File;
import java.util.Scanner;

import MusicUntility.Guitar;
import MusicUntility.Piano;
import MusicUntility.Scale;
import MusicUntility.Site;
import processing.core.PApplet;
import sun.misc.Queue;

public class GUI extends PApplet {
	private final int GUITAR_STRING_HEIGHT = 30;
	private final int GUITAR_BAR_WIDTH = 50;
	private final int PIANO_KEY_HEIGHT = 300;
	private final int PIANO_KEY_WIDTH = 50;
	
	Scale scale;
	Guitar guitare;
	Piano piano;
	
	private int pianoX = 30;
	private int pianoY = 0;
	
	private int guitarX = 130;
	private int guitarY = 330;
	
	boolean major = true;
	
	public GUI() {
		super();
		guitare = new Guitar(scale);
		readFile();
		piano = new Piano(scale);
	}
	
	@Override
	public void settings() {
		super.settings();
		size(14 * PIANO_KEY_WIDTH + 2 * 30, PIANO_KEY_HEIGHT + (guitare.getStringNum() + 2) * GUITAR_STRING_HEIGHT);
	}
	
	@Override
	public void setup() {
		colorMode(HSB, 360, 100, 100);
		noLoop();
		
		setScale(0);
		
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
			translate(pianoX,pianoY);
			
			//hole keys
			pushMatrix();
				int tune = 0;
				for(int i = 0; i < 14; i++){
					fill(0, 0, 100);
					if(scale.inScale(tune)){
						fill(getHueOfNote(scale.NoteInScale(tune)), 80, 80);
					}
					rect(0, 0, PIANO_KEY_WIDTH, PIANO_KEY_HEIGHT);
					translate(PIANO_KEY_WIDTH, 0);
					tune += 2;
					if(i % 7 == 2 || i % 7 == 6){
						tune--;
					}
				}
			popMatrix();
			
			//half keys
			pushMatrix();
				fill(0,0,0);
				translate(PIANO_KEY_WIDTH/2, 0);
				tune = 1;
				for(int i = 0; i < 10; i++){
					fill(0,0,0);
					if(scale.inScale(tune)){
						fill(getHueOfNote(scale.NoteInScale(tune)), 80, 80);
					}
					rect(10, 0, PIANO_KEY_WIDTH * 3/5, PIANO_KEY_HEIGHT * 2f/3f);
					translate(PIANO_KEY_WIDTH, 0);
					tune += 2;
					if(i % 5 == 1||i % 5 == 4){
						tune++;
						translate(PIANO_KEY_WIDTH, 0);
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
			translate(guitarX,guitarY);
			rect(0,0, (guitare.getString(0).getLenght() -1) * GUITAR_BAR_WIDTH, guitare.getStringNum() * GUITAR_STRING_HEIGHT);
			
			//Dots
			pushStyle();
			stroke(0,0,50);
			noFill();
			ellipse( (3f - 0.5f) * GUITAR_BAR_WIDTH, -GUITAR_STRING_HEIGHT * 0.5f,3,3);
			ellipse( (5f - 0.5f) * GUITAR_BAR_WIDTH, -GUITAR_STRING_HEIGHT * 0.5f,7,7);
			ellipse( (7f - 0.5f) * GUITAR_BAR_WIDTH, -GUITAR_STRING_HEIGHT * 0.5f,3,3);
			ellipse( (9f - 0.5f) * GUITAR_BAR_WIDTH, -GUITAR_STRING_HEIGHT * 0.5f,3,3);
			ellipse( (12f - 0.5f) * GUITAR_BAR_WIDTH, -GUITAR_STRING_HEIGHT * 0.5f + 5,3,3);
			ellipse( (12f - 0.5f) * GUITAR_BAR_WIDTH, -GUITAR_STRING_HEIGHT * 0.5f - 5,3,3);
			popStyle();
			
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
//					line(0, 0, (guitare.getString(0).getLenght() -1) * GUITAR_BAR_WIDTH, 0);
					pushMatrix();
						pushStyle();
						noStroke();
						for(int j = 1; j < s.getLenght(); j++){
							if (scale.inScale(tune + j)) {
								fill(getHueOfNote(scale.NoteInScale(tune + j)), 80, 80);
//								ellipse(25, 0, 25, 25);
								rect(0f, -15f, 50, 30);
							}
							translate(50,0);
						}
						popStyle();
					popMatrix();
					line(0, 0, (guitare.getString(0).getLenght() -1) * GUITAR_BAR_WIDTH, 0);
					translate(0, GUITAR_STRING_HEIGHT);
				}
			popMatrix();
			
			noFill();
			rect(0,0, (guitare.getString(0).getLenght() -1) * GUITAR_BAR_WIDTH, guitare.getStringNum() * GUITAR_STRING_HEIGHT);
			
			stroke(0,0,0);

			//Bars
			pushMatrix();
				translate(GUITAR_BAR_WIDTH, 0);
				for(int i = 1; i < guitare.getString(0).getLenght(); i++){
					line(0, 0, 0, guitare.getStringNum() * GUITAR_STRING_HEIGHT);
					translate(GUITAR_BAR_WIDTH, 0);
				}
			popMatrix();
		
		popMatrix();
		
		pushMatrix();
			translate(PIANO_KEY_WIDTH * 3/5,PIANO_KEY_HEIGHT + GUITAR_STRING_HEIGHT);
			fill(0,0,50);
			stroke(0,0,0);
			rect(0,0,50,50);
			pushMatrix();
				if(!major){
					translate(0,25);
				}
				fill(0,0,100);
				rect(0,0,50,25);
			popMatrix();
			fill(0,0,0);
			textSize(14);
			text("Major",6,18);
			text("Minor",6,43);
		popMatrix();
		
		popStyle();
	}
	
	@Override
	public void mouseClicked() {
		//half keys
		if(mouseX > PIANO_KEY_WIDTH && mouseX < PIANO_KEY_WIDTH * 15 && mouseY < PIANO_KEY_HEIGHT){
			int tune = 1;
			int xpos = 35+pianoX;
			for(int i = 0; i < 10; i++){
				if(mouseX > xpos && mouseX < xpos + 30 && mouseY > pianoY && mouseY < pianoY + PIANO_KEY_HEIGHT * 2/3){
					setScale(tune);				
					return;
				}
				xpos += PIANO_KEY_WIDTH;
				tune += 2;
				if(i % 5 == 1||i % 5 == 4){
					tune++;
					xpos += PIANO_KEY_WIDTH;
				}
			}
			
			//hole keys
			tune = 0;
			xpos = pianoX;
			for(int i = 0; i < 14; i++){
				if(mouseX > xpos && mouseX < xpos + PIANO_KEY_WIDTH && mouseY > pianoY && mouseY < pianoY + PIANO_KEY_HEIGHT){
					setScale(tune);				
					return;
				}
				xpos += PIANO_KEY_WIDTH;
				tune += 2;
				if(i % 7 == 2 || i % 7 == 6){
					tune--;
				}
			}
		}
		
		if(mouseX > 30 && mouseX < 80 && mouseY > 330 && mouseY < 380){
			major = !major;
			setScale(scale.getRoot());
		}
	}
	
	private void setScale(int tune){
		this.scale = new Scale(tune, major);
		guitare.setScale(scale);
		piano.setScale(scale);
		getSurface().setTitle(scale.getName());
		redraw();
	}
	
	private void readFile(){
		File f = new File("tune.txt");
		if(f.isFile()){
			Queue<String> tunes = new Queue<>();
			int num = 0;
			try{
				Scanner sc = new Scanner(f);
				while(sc.hasNextLine()){
					tunes.enqueue(sc.nextLine());
					num++;
				}
				sc.close();
			} catch (Exception e) {
			}
			if(num > 0){
				String[] sTunes = new String[num];
				for(int i = 0; i < sTunes.length; i++){
					try {
						sTunes[i] = tunes.dequeue();
					} catch (InterruptedException e) {
					}
				}
				guitare = new Guitar(sTunes, scale);
			}
		}
	}
	
//	private final float[] toneColor = {0,30,60,120,180,240,300};
	private final float[] toneColor = {0,180,30,240,60,300,120};
	
	public float getHueOfNote(int inScale){
		return toneColor[(inScale - 1)%7];
	}
}
