package core;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import MusicUntility.*;

import processing.core.PApplet;

public class GUI extends PApplet {
	private final int MARGIN = 30;
	private final int BUTTON_SIZE = 50;
	
	Scale scale;
	Guitar guitare;
	Piano piano;
	
	boolean major = true;
	
	public GUI() {
		super();
		guitare = new Guitar();
		readFile();
		piano = new Piano();
	}
	
	@Override
	public void settings() {
		super.settings();
		size(piano.getWidth() + 2 * MARGIN, piano.getHeight() + guitare.getHeight() + 2 * MARGIN);
	}
	
	@Override
	public void setup() {
		colorMode(HSB, 360, 100, 100);
		noLoop();
		
		setScale(Tune.C);
	}
	
	@Override
	public void draw() {
		background(224);	
		
		pushMatrix();
			translate(MARGIN,0);
			piano.darw(this, scale);
		popMatrix();
		
		pushStyle();
		textSize(20);
		strokeWeight(3);
		pushMatrix();
			translate(MARGIN,piano.getHeight() + MARGIN);
			fill(0,0,50);
			stroke(0,0,0);
			rect(0,0,BUTTON_SIZE,BUTTON_SIZE);
			pushMatrix();
				if(!major){
					translate(0,BUTTON_SIZE/2);
				}
				fill(0,0,100);
				rect(0,0,BUTTON_SIZE,BUTTON_SIZE/2);
			popMatrix();
			fill(0,0,0);
			textSize(14);
			text("Major",6,18);
			text("Minor",6,43);
		popMatrix();
		popStyle();
		
		pushMatrix();
			translate(MARGIN * 2 + BUTTON_SIZE + 20, piano.getHeight() + MARGIN);
			guitare.draw(this, scale);
		popMatrix();
	}
	
	@Override
	public void mouseClicked() {
		//half keys
		if(mouseX > MARGIN && mouseX <  piano.getWidth() + MARGIN && mouseY < piano.getHeight()){
			int tune = 1;
			int xpos = 35+MARGIN;
			for(int i = 0; i < 10; i++){
				if(mouseX > xpos && mouseX < xpos + 30 && mouseY > 0 && mouseY < piano.getHeight() * 2/3){
					setScale(Note.parseInteger(tune));				
					return;
				}
				xpos += piano.getKeyWidth();
				tune += 2;
				if(i % 5 == 1||i % 5 == 4){
					tune++;
					xpos += piano.getKeyWidth();
				}
			}
			
			//hole keys
			tune = 0;
			xpos = MARGIN;
			for(int i = 0; i < 14; i++){
				if(mouseX > xpos && mouseX < xpos + piano.getKeyWidth() && mouseY > 0 && mouseY < piano.getHeight()){
					setScale(Note.parseInteger(tune));				
					return;
				}
				xpos += piano.getKeyWidth();
				tune += 2;
				if(i % 7 == 2 || i % 7 == 6){
					tune--;
				}
			}
		}
		
		if(mouseX > MARGIN && mouseX < MARGIN + BUTTON_SIZE && mouseY > piano.getHeight() + MARGIN && mouseY < piano.getHeight() + MARGIN + BUTTON_SIZE){
			major = !major;
			setScale(scale.getRoot());
		}
	}
	
	private void setScale(Tune tune){
		this.scale = new Scale( tune, major);
		getSurface().setTitle(scale.getName());
		redraw();
	}
	
	private void readFile(){
		if(System.getProperty("os.name").equals("Linux")){
			File f = new File(System.getenv("HOME") + "/.chordfinder/tune.txt");
			parseTunesFile(f);
		}
		if(guitare != null){
			return;
		}
		File f = new File("tune.txt");
		parseTunesFile(f);
	}
	
	private void parseTunesFile(File f) {
		if(f.isFile()){
			System.out.println("Found Tunes");
			ArrayList<String> tunes = new ArrayList<>();
			int num = 0;
			try {
				Scanner sc = new Scanner(f);
				while(sc.hasNextLine()){
					String nextNote = sc.nextLine();
					try {
						Note.parseString(nextNote);
					} catch (InputMismatchException e) {
						sc.close();
						throw new InputMismatchException("tune.txt is wrongly formated.");
					} 
					tunes.add(nextNote);
					num++;
				}
				sc.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			if(num > 0){
				String[] sTunes = new String[0];
				sTunes = tunes.toArray(sTunes);
				guitare = new Guitar(sTunes);
			}
		}
	}
	
//	private final float[] toneColor = {0,30,60,120,180,240,300};
	private final float[] toneColor = {0,180,30,240,60,300,120};
	
	public float getHueOfNote(int inScale){
		return toneColor[(inScale - 1)%7];
	}
}
