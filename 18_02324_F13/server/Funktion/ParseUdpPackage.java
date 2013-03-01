package Funktion;

public class ParseUdpPackage implements IParseUdpPackage {
    int index;
	
    public void parse(String input) {
        String ip1 = getIP1(input);
        String ip2 = getIP2(input);
        
        String fullHost = getFullHost(input);
        String host = getHost(fullHost);
        String subHost = getSubHost(fullHost);
        
        System.out.println("IP1 = " + ip1);
        System.out.println("IP2 = " + ip2);
        System.out.println("Host = " + host);
        System.out.println("Subhost = " + subHost);
    }

    private String getIP1(String input) {
        index = input.indexOf(":", 9);
        String ip = input.substring(2, index);
        return ip;
    }

    private String getIP2(String input) {
        index = input.indexOf(" ", index);
        int pos = input.indexOf(":", (index) + 11);
        String ip = input.substring(index + 4, pos);
        return ip;
    }
    
    private String getFullHost(String input) {
        index = input.indexOf("Host: ", index);
        int pos = input.indexOf("..", index);
        String host = input.substring(index+6, pos);
        return host;
    }
    
    private String getHost(String fullHost) {
        int pos = fullHost.lastIndexOf(".");
        index = fullHost.lastIndexOf(".", pos-1);
        String host = fullHost.substring(index+1);
        return host;
    }
    
    private String getSubHost(String fullHost) {
        int pos = fullHost.lastIndexOf(".", index-1);
        String subHost = fullHost.substring(pos+1, index);
        return subHost;
    }
}
