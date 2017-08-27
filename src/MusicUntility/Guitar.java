package MusicUntility;

public class Guitar {
	
	public final static String[] NORMAL_TUNING = { "E","A","D","G","B","E"}; 
	public final static String[] UKULELE_TUNING = { "G", "C", "E", "A"}; 

	Site[] sites;
	Scale scale;
	
	public Guitar(String[] s, Scale scale){
		sites = new Site[s.length];
		for(int i = 0; i < s.length; i++){
			sites[i] = new Site(s[i]);
		}
		this.scale = scale;
	}
	
	public Guitar( Scale scale){
		this(NORMAL_TUNING, scale);
	}
	
	public void setScale(Scale scale){
		this.scale = scale;
	}
	
	public int getLength(){
		return sites[0].getLenght();
	}
	
	public int getStringNum(){
		return sites.length;
	}
	
	public Site getString(int n){
		return sites[n];
	}
	
	@Override
	public String toString() {
		String s = "Guitar:\n";
		s += Site.printFret(12);
		for(Site si : sites){
			s += "\n" + si.printScale(scale);
		}
		return s;
	}
}
