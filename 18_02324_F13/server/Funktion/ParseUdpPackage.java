package Funktion;

public class ParseUdpPackage implements IParseUdpPackage {
	int index;
	
	public void parse(String input) {
		String ip1 = getIP1(input);
		String ip2 = getIP2(input);
//        int pos1, pos2, pos3;
//        
//        pos1 = line.indexOf(":", 9);
//        pos2 = line.indexOf(" ", pos1);
//        pos3 = line.indexOf(":", (pos2)+11);
//        
//        if(pos1<0 || pos2<0 || pos3<0) {
//            return false;
//        }
//        
//        ip1 = line.substring(2, pos1);
//        ip2 = line.substring(pos2+4, pos3);
		
	}
	
	private String getIP1(String input){
		index = input.indexOf(":", 9);
		String ip = input.substring(2, index);
		return ip;
	}
	
	private String getIP2(String input){
		String ip = null;
		return ip;
	}

	
}
