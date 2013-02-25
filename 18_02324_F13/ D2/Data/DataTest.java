package Data;

public class DataTest 
{
	int j = 0;
	IData data = new DataList();
	public static void main(String[] args)
	{
		DataTest testClass = new DataTest();
		for(int i = 0; i < 10; i++)
		{
			testClass.test();
		}
		testClass.printList();
	}
	
	public void test()
	{
		j++;
		data.addDataset(null, "456", "Host", "Subhost" + j, "Chrome");
		data.setHost("Test Host: " + j, j-1);
	}
	
	public void printList()
	{
		data.setHost("End", j -1);
		
		for(int i = 0; i < data.getDataList().size(); i++)
		{
			System.out.println(data.getHost(i));
		}
	}
}
