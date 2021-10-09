package cstech_soru;

// Dosya iþlemleri için kütüphane
import java.io.*;

public class main {

	public static void main(String[] args) throws IOException {

		// Mainde yapýlan iþlemler genellikle dosya iþlemleri
		// File_Reader metodu ismi verilen dosyanýn verilerini String array olarak
		// döndürür.
		// Elde edilen String array Chess_game objesindeki metoda gönderilir.
		// objede yapýlan iþlemler sonucu chess_game objesine kaydedilen veriler alýnýr.
		// Alýnan veriler(takým puanlarý) en son File_Writer metodu ile sonuçlar.txt
		// dosyasýna yazýlýr.

		String[] newSTR = new String[8];

		// board1.txt okuma
		newSTR = File_Reader("board1.txt");
		System.out.println();

		// chess_game objesi
		Chess_game cg = new Chess_game();

		cg.set_2DchessBoard(newSTR);

		// board2.txt okuma
		newSTR = File_Reader("board2.txt");

		// chess_game2 objesi
		Chess_game cg2 = new Chess_game();

		cg2.set_2DchessBoard(newSTR);

		// board3.txt okuma
		newSTR = File_Reader("board3.txt");

		// chess_game3 objesi
		Chess_game cg3 = new Chess_game();

		cg3.set_2DchessBoard(newSTR);

		// chess_game bjesinden alýnan veriler dosyaya yazdýrýlmak üzere metoda
		// gönderiliyor.

		File_Writer("Tahta Dosya Adý		Sonuçlar\n");
		File_Writer("board1.txt		Siyah:" + (int)cg.team_Black_Score + "		Beyaz:" + cg.team_White_Score + "\n");
		File_Writer("board2.txt		Siyah:" + (int)cg2.team_Black_Score + "		Beyaz:" + (int)cg2.team_White_Score + "\n");
		File_Writer("board3.txt		Siyah:" + (int)cg3.team_Black_Score + "		Beyaz:" + (int)cg3.team_White_Score + "\n");

	}

	public static String[] File_Reader(String file_Name) throws IOException {

		String str = "";
		String[] buffer_chessBoard = new String[8];
		File file = new File(file_Name);
		FileReader fReader = new FileReader(file);
		String line;

		BufferedReader bReader = new BufferedReader(fReader);

		int i = 0;
		while ((str = bReader.readLine()) != null) {

			// System.out.println(str);
			buffer_chessBoard[i] = str;
			i++;
		}

		bReader.close();

		return buffer_chessBoard;

	}

	public static void File_Writer(String str) throws IOException {

		File w_file = new File("sonuçlar.txt");
		if (!w_file.exists()) {
			w_file.createNewFile();

		}

		FileWriter f_Writer = new FileWriter(w_file, true);
		BufferedWriter b_Writer = new BufferedWriter(f_Writer);
		b_Writer.write(str);
		b_Writer.close();

	}

}
