package MusicUntility;

public class Guitar {
	
	public final static String[] NORMAL_TUNING = { "E","A","D","G","B","E"}; 
	public final static String[] UKULELE_TUNING = { "G", "C", "E", "A"}; 

	Site[] sites;
	
	public Guitar(String[] s){
		sites = new Site[s.length];
		for(int i = 0; i < s.length; i++){
			sites[i] = new Site(s[i]);
		}
	}
	
	public Guitar(){
		this(NORMAL_TUNING);
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
	
	public String printScale(Scale scale) {
		String s = "Guitar:\n";
		s += Site.printFret(12);
		for(Site si : sites){
			s += "\n" + si.printScale(scale);
		}
		return s;
	}
}
