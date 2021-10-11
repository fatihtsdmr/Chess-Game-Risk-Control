package Chess_Game_Control;

//Dosya i�lemleri i�in k�t�phane
import java.io.*;

public class main {

	public static void main(String[] args) throws IOException {

		// Mainde yap�lan i�lemler genellikle dosya i�lemleri
		// File_Reader metodu ismi verilen dosyan�n verilerini String array olarak
		// d�nd�r�r.
		// Elde edilen String array Chess_game objesindeki metoda g�nderilir.
		// objede yap�lan i�lemler sonucu chess_game objesine kaydedilen veriler al�n�r.
		// Al�nan veriler(tak�m puanlar�) en son File_Writer metodu ile sonu�lar.txt
		// dosyas�na yaz�l�r.

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

		// chess_game bjesinden al�nan veriler dosyaya yazd�r�lmak �zere metoda
		// g�nderiliyor.

		File_Writer(cg.team_Black_Score, cg.team_White_Score, cg2.team_Black_Score, cg2.team_White_Score,
				cg3.team_Black_Score, cg3.team_White_Score);

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

	public static void File_Writer(double str, double str2, double str3, double str4, double str5, double str6)
			throws IOException {

		File w_file = new File("sonu�lar.txt");
		if (!w_file.exists()) {
			w_file.createNewFile();

		}
		String newstr;
		newstr = "Tahta Dosya Ad�		Sonu�lar\n" + "board1.txt		Siyah:" + (int) str + "		Beyaz:" + str2
				+ "\n" + "board2.txt		Siyah:" + (int) str3 + "		Beyaz:" + (int) str4 + "\n"
				+ "board3.txt		Siyah:" + (int) str5 + "		Beyaz:" + (int) str6 + "\n";

		FileWriter f_Writer = new FileWriter(w_file, false);
		BufferedWriter b_Writer = new BufferedWriter(f_Writer);
		b_Writer.write(newstr);
		b_Writer.close();

	}

}
