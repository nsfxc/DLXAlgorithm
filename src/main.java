import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class main {
	public static void main (String[] args){
		BufferedReader br = null;
		int m,n;
		boolean[][] g;
		try{
			String CurrentLine;
			br = new BufferedReader(new FileReader("input"));
			CurrentLine = br.readLine();
			n = CurrentLine.charAt(0) - 48;
			m = CurrentLine.charAt(2) - 48;
			g = new boolean[n][m];
			int i = 0;
			while((CurrentLine = br.readLine()) != null){
				String[] st = CurrentLine.split(" ");
				for(int j = 0; j < m; j++){
					g[i][j] = st[j]=="1" ? true : false;
				}
				i++;
			}
			DLX dl = new DLX(n,m,g);
			dl.search();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
