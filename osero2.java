
import java.util.Scanner;

public class osero2 {
	public static String[][] osero=new String[8][8];
	public static String w="w";
	public static String b="b";
	public static int wc=0;
	public static int bc=0;
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int count=0;//置ける場所判定用
		boolean position;//同じく
		String you;
		String e;
		int x;
		int y;
		//↓ボードの初期化
		for(int i=0;i<osero.length;i++){
			for(int j=0;j<osero[i].length;j++){
				osero[i][j]=" ";
			}
		}
		//↓初期位置の生成
		osero[3][3]=w;
		osero[4][4]=w;
		osero[4][3]=b;
		osero[3][4]=b;

		banmen();
		for(int i=0;endif();i++){//全てのマスが埋まるまで続く
			if(i%2==0){
				System.out.println("黒の手番");
				you=b;
				e=w;
			}
			else{
				System.out.println("白の手番");
				you=w;
				e=b;
			}
			System.out.println("黒-白");
			System.out.println(bc+" - "+wc);
			//コマを置ける場所があるか判定
			position = false;
			for(int j = 0;j < 8;j++){
				for(int k = 0;k < 8;k++){
					if(osero[j][k].equals(" ")){
						if(komaPosition(j, k, e, you)){
							position = true;
						}
					}
				}
			}
			if(!position){//変数positionがfalseだった場合
				System.out.println("置ける場所がありません");
				count++;
				if(count ==2){//どちらも置く場所なし
					break;
				}
				continue;//ターンスキップ
			}
			count=0;//置く場所があったら初期化
			System.out.print("X軸を入力");
			y=new Scanner(System.in).nextInt()-1;
			System.out.print("Y軸を入力");
			x=new Scanner(System.in).nextInt()-1;
			while(komaset(x,y,e,you)==false){
				System.out.println("その位置にはコマを置けません");
				System.out.print("X軸を入力");
				x=new Scanner(System.in).nextInt()-1;
				System.out.print("Y軸を入力");
				y=new Scanner(System.in).nextInt()-1;
			}

			banmen();

		}

		if(bc==wc){
			System.out.println("引き分け");
		}
		else if(bc>wc){
			System.out.println("黒の勝ち");
		}
		else{
			System.out.println("白の勝ち");
		}

		System.out.println("ゲーム終了");
	}
	public static boolean mainhelp(String you,String e,int x,int y,int ap,int bp,boolean check){
		if(x<0||y<0||x>7||y>7){//オーバー対策
			return false;
		}
		else if(osero[x][y].equals(" ")){//空白だったら68行目にfalseを返す
			return false;
		}
		else if(osero[x][y].equals(you)){//自分のコマを見つけたら68行目にtrueを返す
			return true;
		}
		else if(mainhelp(you,e,x+ap,y+bp,ap,bp,check)){//trueの場合再帰的に裏返す判定
			if(!check){//場所探しの時に裏返す処理をしないように
				osero[x][y] = you;
			}
			return true;
		}
		return false;

	}

	static public boolean komaset(int x, int y,String e,String you) {//fは相手の駒
		// 盤外を指定した場合
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			System.out.println("盤外です。");
			return false;
		} else {

			if(!(osero[x][y].equals(" "))){
				// 既に駒がおいてある位置を指定した場合
				System.out.println("既に配置されているマスにはコマを置けません");
				return false;
			} else {
				//コマを配置できるかもしれない...

				if(reverse(x-1,y,x,y,e,you,0,false)|reverse(x,y-1,x,y,e,you,0,false)|reverse(x+1,y,x,y,e,you,0,false)|reverse(x,y+1,x,y,e,you,0,false)|reverse(x+1,y+1,x,y,e,you,0,false)|reverse(x-1,y-1,x,y,e,you,0,false)|reverse(x+1,y-1,x,y,e,you,0,false)|reverse(x-1,y+1,x,y,e,you,0,false)){//短絡評価対策で|にしています 最後のbooleanは場所探し用のフラグなのでここでは気にしないでいいです
					osero[x][y]=you;
					return true;
				}
				else{
					return false;
				}

			}
		}

	}
	public static boolean reverse(int a,int b,int x,int y,String e,String you,int cnt,boolean check){//cntは消していいです
		int ap=a-x;//xの差分
		int bp=b-y;//yの差分
		if(a<0||b<0||a>7||b>7){
			return false;
		}
		if(osero[a][b].equals(e)&&a>-1&&a<8&&b>-1&&b<8){//相手の駒があれば
			return mainhelp(you,e,x+ap,y+bp,ap,bp,check);//再帰処理呼び出し
		}
		return false;

	}

	public static boolean endif(){//全てのマスにコマが置かれているか
		boolean tf=true;
		wc=0;
		bc=0;
		for(int i=0;i<osero.length;i++){
			for(int j=0;j<osero[i].length;j++){
				if(osero[i][j].equals(b)){
					bc++;
				}
				if(osero[i][j].equals(w)){
					wc++;
				}
			}
		}
		if(bc==0||wc==0){
			tf=false;
		}
			for(int i=0;i<osero.length;i++){
				for(int j=0;j<osero[i].length;j++){
					if(osero[i][j].equals(" ")){//空欄のマスがある
						tf=false;
					}

				}
			}
		//		}
		if(tf){
			return false;
		}
		else{
			return true;
		}
	}

	public static void banmen(){//現在のボードの位置を表示
		System.out.println(" |1|2|3|4|5|6|7|8|");
		System.out.println("-+-+-+-+-+-+-+-+-+");
		for(int i=0;i<osero.length*2;i++){
			if(i%2==1){

				for(int j=1;j<=osero.length*2+2;j++){
					if(j%2==1){
						System.out.print("-");
					}
					else{
						System.out.print("+");
					}
				}

			}
			else{
				System.out.print(i/2+1);

				for(int j=0;j<osero.length*2;j++){
					if(j%2==1){
						System.out.print(osero[i/2][j/2]);
					}
					else{
						System.out.print("|");
					}
				}
				System.out.print("|");
			}
			System.out.println();
		}
	}
	static public boolean komaPosition(int x, int y,String e,String you) {//置ける場所探し用
		if(reverse(x-1,y,x,y,e,you,0,true)||reverse(x,y-1,x,y,e,you,0,true)||reverse(x+1,y,x,y,e,you,0,true)||reverse(x,y+1,x,y,e,you,0,true)||reverse(x+1,y+1,x,y,e,you,0,true)||reverse(x-1,y-1,x,y,e,you,0,true)||reverse(x+1,y-1,x,y,e,you,0,true)||reverse(x-1,y+1,x,y,e,you,0,true)){//短絡評価対策はいりません
			return true;
		}else{
			return false;
		}
	}
}
