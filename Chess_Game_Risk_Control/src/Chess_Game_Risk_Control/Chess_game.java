package cstech_soru;

public class Chess_game {

	// String cheess_board[];
	String cheess_board2D[][] = new String[8][8];

	// beyaz ve siyah tak�mlar� tan�mlad�m.
	Team t1 = new Team("Beyaz");
	Team t2 = new Team("Siyah");

	// iki tak�m�n g�ncel skorlar�
	double team_White_Score;
	double team_Black_Score;

	// constructor
	Chess_game() {

		cheess_board2D[0][0] = null;

	}

	public void set_2DchessBoard(String[] str) {

		String newSTR = "";

		// DOSYADAN L�NE OLARAK ALDI�IM 1 BOYUTLU STR�NG ARRAY'� 2 BOYUTLU ARRAY'E
		// �EV�R�YORUM
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				for (int k = 0; k < str[i].length(); k++) {
					// System.out.print(str[i].charAt(k));

					if (str[i].charAt(k) != ' ') {

						newSTR = newSTR + str[i].charAt(k);

					}

					else {
						cheess_board2D[i][j] = newSTR;
						newSTR = "";
						j++;

					}

				}
				cheess_board2D[i][j] = newSTR;
				newSTR = "";
				j++;

			}

		}

		// Siyah ve beyaz tak�mlar�n board �st�ndeki ta�lar�n�n risk olmadan analizi ve
		// puanlamas�
		t1.board_Control(cheess_board2D);
		t2.board_Control(cheess_board2D);

		// Analiz ve puanlama sonras� oyun ba�lar ve riskler olu�ur. Riskleri belirlemek
		// i�in start_Game metotu kullan�l�r.
		start_Game(cheess_board2D);

		team_White_Score = t1.score;
		team_Black_Score = t2.score;

	}

	public void start_Game(String[][] board) {

		// Oyun Ba�lang�c�
		// Satran� tahtas� �st�nde bulunan ta�lar�n belirlenmesi ve kar��dan hangi
		// ta�lar� risk alt�na alacak olmas�n� bulma
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				// Beyaz at
				if (board[i][j].equals("ab")) {

					// Beyaz at hareketleri i�in metot
					white_Kinght_Control(i, j, board);

				}
				// siyah at
				else if (board[i][j].equals("as")) {

					// Beyaz at hareketleri i�in metot
					black_Kinght_Control(i, j, board);

				}

				else if (board[i][j].equals("pb")) {

					// Beyaz piyon hareketleri i�in metot
					white_pawn_Control(i, j, board);

				}

				else if (board[i][j].equals("ps")) {

					// Siyah piyon hareketleri i�in metot
					black_pawn_Control(i, j, board);

				}

				else if (board[i][j].equals("vb")) {

					// Beyaz vezir hareketleri i�in metot
					white_queen_Control(i, j, board);
				}

				else if (board[i][j].equals("vs")) {
					// Siyah vezir hareketleri i�in metot
					black_queen_Control(i, j, board);
				}

			}

			System.out.println();
		}

		t1.Score_Control();
		t2.Score_Control();

	}

	// *****************
	// VEZ�R HAREKETLER�
	// *****************

	// beyaz vezirin control analizi
	public void white_queen_Control(int x, int y, String[][] board) {

		// Vezir sa�a,sola, yukar�, a�a�� ve �aprazlara dost ta� g�rmedi�i s�rece
		// ko�ulsuz d��man ta�a sald�rabilen bir ta�.
		// Hareket limiti olmad��� i�in riskini belirlemek i�in d�ng� olu�turdum
		// dost hedefleri belirlemek i�in ko�ul
		// d��man hedefleri belirlemek i�in ko�ul.
		// saptanan d��man analizi i�in metot
		// ta��n tahtada kalmas� i�in ko�ul
		// X ve Y ekseni olarak d���nerek hareketleri sa�lad�m

		// ge�ici koordinatlar i�in de�er olu�turma
		int tempX, tempY;
		tempX = x;
		tempY = y;

		// istenilen sonu� durumunda d�ng�den ��kmak i�in bayrak
		int flag = 0;

		// beyaz vezir yukar� hareketi
		while (flag != 1) {

			// X ekseninde yap�lan hareket
			tempX = tempX - 1;

			// ta� hareketinin tahtan�n d���na ��kmas�n� �nlemek i�in ko�ul
			if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {
				// ta��n kar��s�na ��kan dost ta�lar� tespit etmesi
				if (board[tempX][tempY].equals("kb") || board[tempX][tempY].equals("ab")
						|| board[tempX][tempY].equals("fb") || board[tempX][tempY].equals("vb")
						|| board[tempX][tempY].equals("sb") || board[tempX][tempY].equals("pb")) {
					flag = 1;
				}
				// ta��n kar��s�na ��kan d��man ta�lar� tespit etmesi
				if (board[tempX][tempY].equals("ks") || board[tempX][tempY].equals("as")
						|| board[tempX][tempY].equals("fs") || board[tempX][tempY].equals("vs")
						|| board[tempX][tempY].equals("ss") || board[tempX][tempY].equals("ps")) {
					// risk oldu�unu bildirme ve kontrol etme metotdu
					// bu metot team objemizin i�inde
					t2.risky_Scoring(board[tempX][tempY], tempX, tempY);

					// looptan ��k�� bayra��
					flag = 1;
				}

			}
			// looptan ��k�� ko�ulu
			else
				flag = 1;

		}

		// yeni i�lem �ncesi de�erleri s�f�rlad�k
		tempX = x;
		tempY = y;
		flag = 0;

		// beyaz vezir sa� hareket
		while (flag != 1) {

			// Y ekseninde yap�lan hareket
			tempY = tempY + 1;

			if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {
				if (board[tempX][tempY].equals("kb") || board[tempX][tempY].equals("ab")
						|| board[tempX][tempY].equals("fb") || board[tempX][tempY].equals("vb")
						|| board[tempX][tempY].equals("sb") || board[tempX][tempY].equals("pb")) {
					flag = 1;
				}

				if (board[tempX][tempY].equals("ks") || board[tempX][tempY].equals("as")
						|| board[tempX][tempY].equals("fs") || board[tempX][tempY].equals("vs")
						|| board[tempX][tempY].equals("ss") || board[tempX][tempY].equals("ps")) {
					t2.risky_Scoring(board[tempX][tempY], tempX, tempY);

					flag = 1;
				}

			}

			else
				flag = 1;
		}

		// beyaz vezir sola hareket
		tempX = x;
		tempY = y;
		flag = 0;

		while (flag != 1) {

			tempY = tempY - 1;

			if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {
				if (board[tempX][tempY].equals("kb") || board[tempX][tempY].equals("ab")
						|| board[tempX][tempY].equals("fb") || board[tempX][tempY].equals("vb")
						|| board[tempX][tempY].equals("sb") || board[tempX][tempY].equals("pb")) {
					flag = 1;
				}

				if (board[tempX][tempY].equals("ks") || board[tempX][tempY].equals("as")
						|| board[tempX][tempY].equals("fs") || board[tempX][tempY].equals("vs")
						|| board[tempX][tempY].equals("ss") || board[tempX][tempY].equals("ps")) {
					t2.risky_Scoring(board[tempX][tempY], tempX, tempY);

					flag = 1;
				}

			}

			else
				flag = 1;

		}

		// sa� �apraz beyaz vezir hareket
		tempY = y;
		flag = 0;

		while (flag != 1) {

			tempX = tempX - 1;
			tempY = tempY + 1;

			if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {
				if (board[tempX][tempY].equals("kb") || board[tempX][tempY].equals("ab")
						|| board[tempX][tempY].equals("fb") || board[tempX][tempY].equals("vb")
						|| board[tempX][tempY].equals("sb") || board[tempX][tempY].equals("pb")) {
					flag = 1;
				}

				if (board[tempX][tempY].equals("ks") || board[tempX][tempY].equals("as")
						|| board[tempX][tempY].equals("fs") || board[tempX][tempY].equals("vs")
						|| board[tempX][tempY].equals("ss") || board[tempX][tempY].equals("ps")) {
					t2.risky_Scoring(board[tempX][tempY], tempX, tempY);

					flag = 1;
				}

			} else
				flag = 1;

		}

		// sol �apraz beyaz vezir hareket
		tempX = x;
		tempY = y;
		flag = 0;

		while (flag != 1) {

			tempX = tempX - 1;
			tempY = tempY - 1;

			if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {
				if (board[tempX][tempY].equals("kb") || board[tempX][tempY].equals("ab")
						|| board[tempX][tempY].equals("fb") || board[tempX][tempY].equals("vb")
						|| board[tempX][tempY].equals("sb") || board[tempX][tempY].equals("pb")) {
					flag = 1;
				}

				if (board[tempX][tempY].equals("ks") || board[tempX][tempY].equals("as")
						|| board[tempX][tempY].equals("fs") || board[tempX][tempY].equals("vs")
						|| board[tempX][tempY].equals("ss") || board[tempX][tempY].equals("ps")) {
					t2.risky_Scoring(board[tempX][tempY], tempX, tempY);

					flag = 1;
				}

			} else
				flag = 1;

		}

		// beyaz vezir sa� alt �apraz
		tempX = x;
		tempY = y;
		flag = 0;

		while (flag != 1) {

			tempX = tempX + 1;
			tempY = tempY + 1;

			if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {
				if (board[tempX][tempY].equals("kb") || board[tempX][tempY].equals("ab")
						|| board[tempX][tempY].equals("fb") || board[tempX][tempY].equals("vb")
						|| board[tempX][tempY].equals("sb") || board[tempX][tempY].equals("pb")) {
					flag = 1;
				}

				if (board[tempX][tempY].equals("ks") || board[tempX][tempY].equals("as")
						|| board[tempX][tempY].equals("fs") || board[tempX][tempY].equals("vs")
						|| board[tempX][tempY].equals("ss") || board[tempX][tempY].equals("ps")) {
					t2.risky_Scoring(board[tempX][tempY], tempX, tempY);

					flag = 1;
				}

			}

			else
				flag = 1;

		}

		// beyaz vezir alta hareket
		tempX = x;
		tempY = y;
		flag = 0;

		while (flag != 1) {

			tempX = tempX + 1;

			if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {
				if (board[tempX][tempY].equals("kb") || board[tempX][tempY].equals("ab")
						|| board[tempX][tempY].equals("fb") || board[tempX][tempY].equals("vb")
						|| board[tempX][tempY].equals("sb") || board[tempX][tempY].equals("pb")) {
					flag = 1;
				}

				if (board[tempX][tempY].equals("ks") || board[tempX][tempY].equals("as")
						|| board[tempX][tempY].equals("fs") || board[tempX][tempY].equals("vs")
						|| board[tempX][tempY].equals("ss") || board[tempX][tempY].equals("ps")) {
					t2.risky_Scoring(board[tempX][tempY], tempX, tempY);

					flag = 1;
				}

			}

			else
				flag = 1;

		}

		// beyaz vezir sol alt �apraz
		tempX = x;
		tempY = y;
		flag = 0;

		while (flag != 1) {

			tempX = tempX + 1;
			tempY = tempY - 1;

			if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {
				if (board[tempX][tempY].equals("kb") || board[tempX][tempY].equals("ab")
						|| board[tempX][tempY].equals("fb") || board[tempX][tempY].equals("vb")
						|| board[tempX][tempY].equals("sb") || board[tempX][tempY].equals("pb")) {
					flag = 1;
				}

				if (board[tempX][tempY].equals("ks") || board[tempX][tempY].equals("as")
						|| board[tempX][tempY].equals("fs") || board[tempX][tempY].equals("vs")
						|| board[tempX][tempY].equals("ss") || board[tempX][tempY].equals("ps")) {
					t2.risky_Scoring(board[tempX][tempY], tempX, tempY);

					flag = 1;
				}

			}

			else
				flag = 1;

		}

	}// beyaz vezir son

	public void black_queen_Control(int x, int y, String[][] board) {

		// Vezir sa�a,sola, yukar�, a�a�� ve �aprazlara dost ta� g�rmedi�i s�rece
		// ko�ulsuz d��man ta�a sald�rabilen bir ta�.
		// Hareket limiti olmad��� i�in riskini belirlemek i�in d�ng� olu�turdum
		// dost hedefleri belirlemek i�in ko�ul
		// d��man hedefleri belirlemek i�in ko�ul.
		// ta��n tahtada kalmas� i�in ko�ul
		// saptanan d��man analizi i�in metot
		// X ve Y ekseni olarak d���nerek hareketleri sa�lad�m

		int tempX, tempY;
		tempX = x;
		tempY = y;
		int flag = 0;

		// siyah vezir a�a�� hareketi
		while (flag != 1) {

			tempX = tempX + 1;

			if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {
				if (board[tempX][tempY].equals("ks") || board[tempX][tempY].equals("as")
						|| board[tempX][tempY].equals("fs") || board[tempX][tempY].equals("vs")
						|| board[tempX][tempY].equals("ss") || board[tempX][tempY].equals("ps"))

				{
					flag = 1;
				}
				if (board[tempX][tempY].equals("kb") || board[tempX][tempY].equals("ab")
						|| board[tempX][tempY].equals("fb") || board[tempX][tempY].equals("vb")
						|| board[tempX][tempY].equals("sb") || board[tempX][tempY].equals("pb"))

				{
					t1.risky_Scoring(board[tempX][tempY], tempX, tempY);

					flag = 1;
				}

			}

			else
				flag = 1;

		}

		// siyah vezir sa� hareket
		tempX = x;
		tempY = y;
		flag = 0;

		while (flag != 1) {

			tempY = tempY + 1;

			if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {
				if (board[tempX][tempY].equals("ks") || board[tempX][tempY].equals("as")
						|| board[tempX][tempY].equals("fs") || board[tempX][tempY].equals("vs")
						|| board[tempX][tempY].equals("ss") || board[tempX][tempY].equals("ps"))

				{
					flag = 1;
				}
				if (board[tempX][tempY].equals("kb") || board[tempX][tempY].equals("ab")
						|| board[tempX][tempY].equals("fb") || board[tempX][tempY].equals("vb")
						|| board[tempX][tempY].equals("sb") || board[tempX][tempY].equals("pb"))

				{
					t1.risky_Scoring(board[tempX][tempY], tempX, tempY);

					flag = 1;
				}

			}

			else
				flag = 1;

		}

		// siyah vezir sola hareket
		tempX = x;
		tempY = y;
		flag = 0;

		while (flag != 1) {

			tempY = tempY - 1;

			if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {
				if (board[tempX][tempY].equals("ks") || board[tempX][tempY].equals("as")
						|| board[tempX][tempY].equals("fs") || board[tempX][tempY].equals("vs")
						|| board[tempX][tempY].equals("ss") || board[tempX][tempY].equals("ps"))

				{
					flag = 1;
				}
				if (board[tempX][tempY].equals("kb") || board[tempX][tempY].equals("ab")
						|| board[tempX][tempY].equals("fb") || board[tempX][tempY].equals("vb")
						|| board[tempX][tempY].equals("sb") || board[tempX][tempY].equals("pb"))

				{
					t1.risky_Scoring(board[tempX][tempY], tempX, tempY);

					flag = 1;
				}

			}

			else
				flag = 1;

		}

		// siyah vezir sa� a�a�� �apraz
		tempX = x;
		tempY = y;
		flag = 0;

		while (flag != 1) {

			tempX = tempX + 1;

			tempY = tempY + 1;

			if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {
				if (board[tempX][tempY].equals("ks") || board[tempX][tempY].equals("as")
						|| board[tempX][tempY].equals("fs") || board[tempX][tempY].equals("vs")
						|| board[tempX][tempY].equals("ss") || board[tempX][tempY].equals("ps"))

				{
					flag = 1;
				}
				if (board[tempX][tempY].equals("kb") || board[tempX][tempY].equals("ab")
						|| board[tempX][tempY].equals("fb") || board[tempX][tempY].equals("vb")
						|| board[tempX][tempY].equals("sb") || board[tempX][tempY].equals("pb"))

				{
					t1.risky_Scoring(board[tempX][tempY], tempX, tempY);

					flag = 1;
				}

			}

			else
				flag = 1;

		}

		// siyah sol a�a�� �apraz vezir

		tempX = x;
		tempY = y;
		flag = 0;

		while (flag != 1) {

			tempX = tempX + 1;

			tempY = tempY - 1;

			if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {
				if (board[tempX][tempY].equals("ks") || board[tempX][tempY].equals("as")
						|| board[tempX][tempY].equals("fs") || board[tempX][tempY].equals("vs")
						|| board[tempX][tempY].equals("ss") || board[tempX][tempY].equals("ps"))

				{
					flag = 1;
				}
				if (board[tempX][tempY].equals("kb") || board[tempX][tempY].equals("ab")
						|| board[tempX][tempY].equals("fb") || board[tempX][tempY].equals("vb")
						|| board[tempX][tempY].equals("sb") || board[tempX][tempY].equals("pb"))

				{
					t1.risky_Scoring(board[tempX][tempY], tempX, tempY);

					flag = 1;
				}

			}

			else
				flag = 1;

		}

	}// siyah vezir son

	// *****************
	// P�YON HAREKETLER�
	// *****************

	public void white_pawn_Control(int x, int y, String[][] board) {

		// Piyon d��man ataklar� d���nda d�z hareket eden bir ta�
		// D��man riski oldu�unda �apraza gidebilir
		// ta��n tahtada kalmas� i�in ko�ul
		// d��man hedefleri belirlemek i�in ko�ul
		// saptanan d��man analizi i�in metot
		// X ve Y ekseni olarak d���nerek hareketleri sa�lad�m

		int tempX, tempY;
		tempX = x;
		tempY = y;
		// beyaz piyonun sa� �apraz hareket kontrol�

		tempX = tempX - 1;
		tempY = tempY + 1;
		if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {

			if (board[tempX][tempY].equals("ks") || board[tempX][tempY].equals("as") || board[tempX][tempY].equals("fs")
					|| board[tempX][tempY].equals("vs") || board[tempX][tempY].equals("ss")
					|| board[tempX][tempY].equals("ps")) {
				t2.risky_Scoring(board[tempX][tempY], tempX, tempY);

			}

		}

		// beyaz piyonun sol �apraz hareket kontrol�
		tempX = x;
		tempY = y;

		tempX = tempX - 1;
		tempY = tempY - 1;
		if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {

			if (board[tempX][tempY].equals("ks") || board[tempX][tempY].equals("as") || board[tempX][tempY].equals("fs")
					|| board[tempX][tempY].equals("vs") || board[tempX][tempY].equals("ss")
					|| board[tempX][tempY].equals("ps")) {
				t2.risky_Scoring(board[tempX][tempY], tempX, tempY);

			}

		}

	}

	// siyah piyon hareketleri
	public void black_pawn_Control(int x, int y, String[][] board) {

		int tempX, tempY;
		tempX = x;
		tempY = y;
		// siyah piyonun sa� �apraz hareket kontrol�

		tempX = tempX + 1;
		tempY = tempY + 1;
		if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {

			if (board[tempX][tempY].equals("kb") || board[tempX][tempY].equals("ab") || board[tempX][tempY].equals("fb")
					|| board[tempX][tempY].equals("vb") || board[tempX][tempY].equals("sb")
					|| board[tempX][tempY].equals("pb")) {

				t1.risky_Scoring(board[tempX][tempY], tempX, tempY);

			}

		}

		// siyah piyonun sol �apraz hareket kontrol�
		tempX = x;
		tempY = y;

		tempX = tempX + 1;
		tempY = tempY - 1;
		if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {

			if (board[tempX][tempY].equals("kb") || board[tempX][tempY].equals("ab") || board[tempX][tempY].equals("fb")
					|| board[tempX][tempY].equals("vb") || board[tempX][tempY].equals("sb")
					|| board[tempX][tempY].equals("pb")) {

				t1.risky_Scoring(board[tempX][tempY], tempX, tempY);

			}

		}

	}

	// ***************
	// AT HAREKETLER�
	// ***************

	// beyaz at�n risk alt�na ald��� ta�lar� bulma metodu
	public void white_Kinght_Control(int x, int y, String[][] board) {
		// At 'L' hareketi yaparak hareket edebilir
		// Dost hedef g�r�nce �st�nden atlayabilir ve d��mana sald�rabilir
		// ta��n tahtada kalmas� i�in ko�ul
		// d��man hedefleri belirlemek i�in ko�ul
		// saptanan d��man analizi i�in metot
		// X ve Y ekseni olarak d���nerek hareketleri sa�lad�m

		int tempX, tempY;
		tempX = x;
		tempY = y;
		// beyaz at�n yukar� sa� hareket kontrol�

		tempX = tempX - 2;
		tempY = tempY + 1;
		if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {

			if (board[tempX][tempY].equals("ks") || board[tempX][tempY].equals("as") || board[tempX][tempY].equals("fs")
					|| board[tempX][tempY].equals("vs") || board[tempX][tempY].equals("ss")
					|| board[tempX][tempY].equals("ps")) {
				t2.risky_Scoring(board[tempX][tempY], tempX, tempY);

			}

		}

		// beyaz at�n yukar� sol hareket kontrol�

		tempX = x;
		tempY = y;

		tempX = tempX - 2;
		tempY = tempY - 1;
		if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {
			if (board[tempX][tempY].equals("ks") || board[tempX][tempY].equals("as") || board[tempX][tempY].equals("fs")
					|| board[tempX][tempY].equals("vs") || board[tempX][tempY].equals("ss")
					|| board[tempX][tempY].equals("ps"))

			{
				t2.risky_Scoring(board[tempX][tempY], tempX, tempY);

			}
		}
		// beyaz at�n sol hareket kontrol�

		tempX = x;
		tempY = y;

		tempX = tempX - 1;
		tempY = tempY - 2;
		if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {

			if (board[tempX][tempY].equals("ks") || board[tempX][tempY].equals("as") || board[tempX][tempY].equals("fs")
					|| board[tempX][tempY].equals("vs") || board[tempX][tempY].equals("ss")
					|| board[tempX][tempY].equals("ps")) {
				t2.risky_Scoring(board[tempX][tempY], tempX, tempY);

			}

		}

		// beyaz at�n sa� hareket kontrol�

		tempX = x;
		tempY = y;
		tempX = tempX - 1;
		tempY = tempY + 2;
		if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {

			if (board[tempX][tempY].equals("ks") || board[tempX][tempY].equals("as") || board[tempX][tempY].equals("fs")
					|| board[tempX][tempY].equals("vs") || board[tempX][tempY].equals("ss")
					|| board[tempX][tempY].equals("ps")) {
				t2.risky_Scoring(board[tempX][tempY], tempX, tempY);

			}

		}

	}

	// siyah at�n risk alt�na ald��� ta�lar� bulma metodu
	public void black_Kinght_Control(int x, int y, String[][] board) {
		// At 'L' hareketi yaparak hareket edebilir
		// Dost hedef g�r�nce �st�nden atlayabilir ve d��mana sald�rabilir
		// ta��n tahtada kalmas� i�in ko�ul
		// d��man hedefleri belirlemek i�in ko�ul
		// saptanan d��man analizi i�in metot
		// X ve Y ekseni olarak d���nerek hareketleri sa�lad�m

		int tempX, tempY;
		tempX = x;
		tempY = y;
		// siyah at�n a�a�� sa�a hareket kontrol�

		tempX = tempX + 2;
		tempY = tempY + 1;
		if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {
			if (board[tempX][tempY].equals("kb") || board[tempX][tempY].equals("ab") || board[tempX][tempY].equals("fb")
					|| board[tempX][tempY].equals("vb") || board[tempX][tempY].equals("sb")
					|| board[tempX][tempY].equals("pb")) {
				t1.risky_Scoring(board[tempX][tempY], tempX, tempY);

			}
		}
		// siyah at�n a�a�� sola hareket kontrol�

		tempX = x;
		tempY = y;

		tempX = tempX + 2;
		tempY = tempY - 1;
		if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {
			if (board[tempX][tempY].equals("kb") || board[tempX][tempY].equals("ab") || board[tempX][tempY].equals("fb")
					|| board[tempX][tempY].equals("vb") || board[tempX][tempY].equals("sb")
					|| board[tempX][tempY].equals("pb")) {
				t1.risky_Scoring(board[tempX][tempY], tempX, tempY);

			}
		}
		// siyah at�n sa�a hareket kontrol�

		tempX = x;
		tempY = y;

		tempX = tempX + 1;
		tempY = tempY + 2;
		if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {
			if (board[tempX][tempY].equals("kb") || board[tempX][tempY].equals("ab") || board[tempX][tempY].equals("fb")
					|| board[tempX][tempY].equals("vb") || board[tempX][tempY].equals("sb")
					|| board[tempX][tempY].equals("pb")) {
				t1.risky_Scoring(board[tempX][tempY], tempX, tempY);

			}
		}
		// siyah at�n sol hareket kontrol�

		tempX = x;
		tempY = y;

		tempX = tempX + 1;
		tempY = tempY - 2;
		if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {
			if (board[tempX][tempY].equals("kb") || board[tempX][tempY].equals("ab") || board[tempX][tempY].equals("fb")
					|| board[tempX][tempY].equals("vb") || board[tempX][tempY].equals("sb")
					|| board[tempX][tempY].equals("pb")) {
				t1.risky_Scoring(board[tempX][tempY], tempX, tempY);

			}
		}

	}

}// class sonu
