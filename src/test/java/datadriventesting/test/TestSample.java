package datadriventesting.test;

import java.io.IOException;
import java.util.ArrayList;

public class TestSample {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		dataDriven d = new dataDriven();
		ArrayList<String> data = d.getData("Add Profile");
		int i = 0;
		while (i < data.size()) {
			System.out.println(data.get(i));
			i++;
		}
	}

}
