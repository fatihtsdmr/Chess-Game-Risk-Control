package Chess_Game_Control;



public class Chess_game {

	// String cheess_board[];
	String cheess_board2D[][] = new String[8][8];

	// beyaz ve siyah takýmlarý tanýmladým.
	Team t1 = new Team("Beyaz");
	Team t2 = new Team("Siyah");

	// iki takýmýn güncel skorlarý
	double team_White_Score;
	double team_Black_Score;

	// constructor
	Chess_game() {

		cheess_board2D[0][0] = null;

	}

	public void set_2DchessBoard(String[] str) {

		String newSTR = "";

		// DOSYADAN LÝNE OLARAK ALDIÐIM 1 BOYUTLU STRÝNG ARRAY'Ý 2 BOYUTLU ARRAY'E
		// ÇEVÝRÝYORUM
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

		// Siyah ve beyaz takýmlarýn board üstündeki taþlarýnýn risk olmadan analizi ve
		// puanlamasý
		t1.board_Control(cheess_board2D);
		t2.board_Control(cheess_board2D);

		// Analiz ve puanlama sonrasý oyun baþlar ve riskler oluþur. Riskleri belirlemek
		// için start_Game metotu kullanýlýr.
		start_Game(cheess_board2D);

		team_White_Score = t1.score;
		team_Black_Score = t2.score;

	}

	public void start_Game(String[][] board) {

		// Oyun Baþlangýcý
		// Satranç tahtasý üstünde bulunan taþlarýn belirlenmesi ve karþýdan hangi
		// taþlarý risk altýna alacak olmasýný bulma
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				// Beyaz at
				if (board[i][j].equals("ab")) {

					// Beyaz at hareketleri için metot
					white_Kinght_Control(i, j, board);

				}
				// siyah at
				else if (board[i][j].equals("as")) {

					// Beyaz at hareketleri için metot
					black_Kinght_Control(i, j, board);

				}

				else if (board[i][j].equals("pb")) {

					// Beyaz piyon hareketleri için metot
					white_pawn_Control(i, j, board);

				}

				else if (board[i][j].equals("ps")) {

					// Siyah piyon hareketleri için metot
					black_pawn_Control(i, j, board);

				}

				else if (board[i][j].equals("vb")) {

					// Beyaz vezir hareketleri için metot
					white_queen_Control(i, j, board);
				}

				else if (board[i][j].equals("vs")) {
					// Siyah vezir hareketleri için metot
					black_queen_Control(i, j, board);
				}

			}

			System.out.println();
		}

		t1.Score_Control();
		t2.Score_Control();

	}

	// *****************
	// VEZÝR HAREKETLERÝ
	// *****************

	// beyaz vezirin control analizi
	public void white_queen_Control(int x, int y, String[][] board) {

		// Vezir saða,sola, yukarý, aþaðý ve çaprazlara dost taþ görmediði sürece
		// koþulsuz düþman taþa saldýrabilen bir taþ.
		// Hareket limiti olmadýðý için riskini belirlemek için döngü oluþturdum
		// dost hedefleri belirlemek için koþul
		// düþman hedefleri belirlemek için koþul.
		// saptanan düþman analizi için metot
		// taþýn tahtada kalmasý için koþul
		// X ve Y ekseni olarak düþünerek hareketleri saðladým

		// geçici koordinatlar için deðer oluþturma
		int tempX, tempY;
		tempX = x;
		tempY = y;

		// istenilen sonuç durumunda döngüden çýkmak için bayrak
		int flag = 0;

		// beyaz vezir yukarý hareketi
		while (flag != 1) {

			// X ekseninde yapýlan hareket
			tempX = tempX - 1;

			// taþ hareketinin tahtanýn dýþýna çýkmasýný önlemek için koþul
			if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {
				// taþýn karþýsýna çýkan dost taþlarý tespit etmesi
				if (board[tempX][tempY].equals("kb") || board[tempX][tempY].equals("ab")
						|| board[tempX][tempY].equals("fb") || board[tempX][tempY].equals("vb")
						|| board[tempX][tempY].equals("sb") || board[tempX][tempY].equals("pb")) {
					flag = 1;
				}
				// taþýn karþýsýna çýkan düþman taþlarý tespit etmesi
				if (board[tempX][tempY].equals("ks") || board[tempX][tempY].equals("as")
						|| board[tempX][tempY].equals("fs") || board[tempX][tempY].equals("vs")
						|| board[tempX][tempY].equals("ss") || board[tempX][tempY].equals("ps")) {
					// risk olduðunu bildirme ve kontrol etme metotdu
					// bu metot team objemizin içinde
					t2.risky_Scoring(board[tempX][tempY], tempX, tempY);

					// looptan çýkýþ bayraðý
					flag = 1;
				}

			}
			// looptan çýkýþ koþulu
			else
				flag = 1;

		}

		// yeni iþlem öncesi deðerleri sýfýrladýk
		tempX = x;
		tempY = y;
		flag = 0;

		// beyaz vezir sað hareket
		while (flag != 1) {

			// Y ekseninde yapýlan hareket
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

		// sað çapraz beyaz vezir hareket
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

		// sol çapraz beyaz vezir hareket
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

		// beyaz vezir sað alt çapraz
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

		// beyaz vezir sol alt çapraz
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

		// Vezir saða,sola, yukarý, aþaðý ve çaprazlara dost taþ görmediði sürece
		// koþulsuz düþman taþa saldýrabilen bir taþ.
		// Hareket limiti olmadýðý için riskini belirlemek için döngü oluþturdum
		// dost hedefleri belirlemek için koþul
		// düþman hedefleri belirlemek için koþul.
		// taþýn tahtada kalmasý için koþul
		// saptanan düþman analizi için metot
		// X ve Y ekseni olarak düþünerek hareketleri saðladým

		int tempX, tempY;
		tempX = x;
		tempY = y;
		int flag = 0;

		// siyah vezir aþaðý hareketi
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

		// siyah vezir sað hareket
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

		// siyah vezir sað aþaðý çapraz
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

		// siyah sol aþaðý çapraz vezir

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
	// PÝYON HAREKETLERÝ
	// *****************

	public void white_pawn_Control(int x, int y, String[][] board) {

		// Piyon düþman ataklarý dýþýnda düz hareket eden bir taþ
		// Düþman riski olduðunda çapraza gidebilir
		// taþýn tahtada kalmasý için koþul
		// düþman hedefleri belirlemek için koþul
		// saptanan düþman analizi için metot
		// X ve Y ekseni olarak düþünerek hareketleri saðladým

		int tempX, tempY;
		tempX = x;
		tempY = y;
		// beyaz piyonun sað çapraz hareket kontrolü

		tempX = tempX - 1;
		tempY = tempY + 1;
		if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {

			if (board[tempX][tempY].equals("ks") || board[tempX][tempY].equals("as") || board[tempX][tempY].equals("fs")
					|| board[tempX][tempY].equals("vs") || board[tempX][tempY].equals("ss")
					|| board[tempX][tempY].equals("ps")) {
				t2.risky_Scoring(board[tempX][tempY], tempX, tempY);

			}

		}

		// beyaz piyonun sol çapraz hareket kontrolü
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
		// siyah piyonun sað çapraz hareket kontrolü

		tempX = tempX + 1;
		tempY = tempY + 1;
		if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {

			if (board[tempX][tempY].equals("kb") || board[tempX][tempY].equals("ab") || board[tempX][tempY].equals("fb")
					|| board[tempX][tempY].equals("vb") || board[tempX][tempY].equals("sb")
					|| board[tempX][tempY].equals("pb")) {

				t1.risky_Scoring(board[tempX][tempY], tempX, tempY);

			}

		}

		// siyah piyonun sol çapraz hareket kontrolü
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
	// AT HAREKETLERÝ
	// ***************

	// beyaz atýn risk altýna aldýðý taþlarý bulma metodu
	public void white_Kinght_Control(int x, int y, String[][] board) {
		// At 'L' hareketi yaparak hareket edebilir
		// Dost hedef görünce üstünden atlayabilir ve düþmana saldýrabilir
		// taþýn tahtada kalmasý için koþul
		// düþman hedefleri belirlemek için koþul
		// saptanan düþman analizi için metot
		// X ve Y ekseni olarak düþünerek hareketleri saðladým

		int tempX, tempY;
		tempX = x;
		tempY = y;
		// beyaz atýn yukarý sað hareket kontrolü

		tempX = tempX - 2;
		tempY = tempY + 1;
		if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {

			if (board[tempX][tempY].equals("ks") || board[tempX][tempY].equals("as") || board[tempX][tempY].equals("fs")
					|| board[tempX][tempY].equals("vs") || board[tempX][tempY].equals("ss")
					|| board[tempX][tempY].equals("ps")) {
				t2.risky_Scoring(board[tempX][tempY], tempX, tempY);

			}

		}

		// beyaz atýn yukarý sol hareket kontrolü

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
		// beyaz atýn sol hareket kontrolü

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

		// beyaz atýn sað hareket kontrolü

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

	// siyah atýn risk altýna aldýðý taþlarý bulma metodu
	public void black_Kinght_Control(int x, int y, String[][] board) {
		// At 'L' hareketi yaparak hareket edebilir
		// Dost hedef görünce üstünden atlayabilir ve düþmana saldýrabilir
		// taþýn tahtada kalmasý için koþul
		// düþman hedefleri belirlemek için koþul
		// saptanan düþman analizi için metot
		// X ve Y ekseni olarak düþünerek hareketleri saðladým

		int tempX, tempY;
		tempX = x;
		tempY = y;
		// siyah atýn aþaðý saða hareket kontrolü

		tempX = tempX + 2;
		tempY = tempY + 1;
		if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {
			if (board[tempX][tempY].equals("kb") || board[tempX][tempY].equals("ab") || board[tempX][tempY].equals("fb")
					|| board[tempX][tempY].equals("vb") || board[tempX][tempY].equals("sb")
					|| board[tempX][tempY].equals("pb")) {
				t1.risky_Scoring(board[tempX][tempY], tempX, tempY);

			}
		}
		// siyah atýn aþaðý sola hareket kontrolü

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
		// siyah atýn saða hareket kontrolü

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
		// siyah atýn sol hareket kontrolü

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
