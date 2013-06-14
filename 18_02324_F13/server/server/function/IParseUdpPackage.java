package server.function;

public interface IParseUdpPackage 
{

	/**
	 * Parses the raw data into the dataobjects, and sorts the data
	 * @param input the raw data gathered from the client in the format:
	 * (scourceIP)	(destinationIP)	(URL)	(Useragent string)
	 * seperated by tabs
	 */
	void parse(String input);
}