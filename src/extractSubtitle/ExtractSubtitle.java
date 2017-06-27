package extractSubtitle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ExtractSubtitle {
	private String fileAddress;
	private String Subtitle;
	
	public ExtractSubtitle() {
	}
	
	public ExtractSubtitle(String fileAddress) {
		this.fileAddress = fileAddress;
		
		try {
			// 파일에서 스트림을 통해 텍스트를 읽어온다
			BufferedReader br = new BufferedReader(new FileReader(fileAddress));
				
			// br에 저장된 데이터를 \n 기준으로 s에 저장
			String s;

			String script[] = new String[5];
			int i = 0;
			// 줄바꿈이 나올 때 까지
			while ((s = br.readLine()) != null)
			{
				if (s.length() >= 1)
				{
					script[i] = s;
					i++;
				}
				else// 줄바꿈 문자 제거
				{
					i = 0;
					for (int j = 2; script[j] != null; j++)
					{
						if (j == 2) Subtitle += script[0] + "\t"+ script[j] + "\n";
						else Subtitle += "\t"+ script[j] + "\n";
					}
						
					for (int j = 0; j < 5; j++) script[j] = null;
				}
			}
			br.close();
				
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getSubtitle()
	{
		return Subtitle;
	}
	
	/*public static void main(String[] args) {
		try {
			// 파일에서 스트림을 통해 텍스트를 읽어온다
			//BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Good.Bye.Lenin.srt"), "UTF-8"));
			BufferedReader br = new BufferedReader(new FileReader("Good.Bye.Lenin.srt"));
			
			// br에 저장된 데이터를 \n 기준으로 s에 저장
			String s;
			
			BufferedWriter fw = new BufferedWriter(new FileWriter("C:\\Users\\Hyo-Jin\\Desktop\\GoodByeLenin.txt", true));

			String script[] = new String[5];
			int i = 0;
			// 줄바꿈이 나올 때 까지
			while ((s = br.readLine()) != null)
			{
				
				if (s.length() >= 1)
				{
					script[i] = s;
					i++;
				}
				else// 줄바꿈 문자 제거
				{
					i = 0;
					for (int j = 2; script[j] != null; j++)
					{
						if (j == 2)
						{
							System.out.println(script[0] + "\t"+ script[j]);
							fw.write(script[0] + "\t"+ script[j] + "\n");
						}
						else
						{
							System.out.println("\t"+ script[j]);
							fw.write("\t"+ script[j] + "\n");
						}
					}
					
					for (int j = 0; j < 5; j++) script[j] = null;
				}
			}
			

            // 파일안에 문자열 쓰기
            fw.flush();
            // 객체 닫기
            fw.close();
            br.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/

}
