public class Piano {
	
	Scale scale;
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
			

	Piano(Scale scale){
		this.scale = scale;
	}
	
	@Override
	public String toString() {
		char[] chars = new char[12];
		for(int i = 0; i < chars.length; ++i){
			chars[i] = (scale.inScale(i) ? Character.forDigit(scale.NoteInScale(i), 10) : ' ');
		}
		String s = String.format("Piano:\n" + pianoLayout, chars[1], chars[3], chars[6], chars[8], chars[10], chars[0], chars[2], chars[4], chars[5], chars[7], chars[9], chars[11]);
		return s;
	}
}
