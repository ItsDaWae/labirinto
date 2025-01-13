
public class Main {
	static void percorso(char[][] pos, int n) {
		//forse è meglio usare dei metodi per richiamare le funzioni gi su de si, così
		//non si allunga troppo il codice e non devo vedere i false o i true, ma solo le funzioni
		//per esempio per fare il controllo di un muro nella direzione selezionata
		//sarebbe utile poter avere delle info da ricercare e mettere dentro un'altra funzione
		//senza dover fare il caso particolare per ogniuna delle 4
		boolean fine=true;
		boolean inizio=true;
		
		int x, y;
		int xV1=-1;
		int yV1=-1;
		int xV2=-1;
		int yV2=-1;
		int xV3=-1;
		int yV3=-1;
		
		boolean gi=false;
		boolean su=false;
		boolean de=false;
		boolean si=false;
		
		char nodilà='z';
		char nodilà2='v';
		char nodilà3='u';
		
		
		//punto d'inizio
		do {

			x = (int) (Math.random() * (n-0.1));
			y = (int) (Math.random() * (n-0.1));
			
			if(pos[y][x]=='#' && x!=y && (x%2==1 || y%2==1)) {
			
				pos[y][x]=' ';
				System.out.println(pos[y][x]);
				inizio=false;
				
			}
		}while(inizio==true);
		
		if(y==0) {
			gi=true;
		}
		if(y==n-1) {
			su=true;
		}
		if(x==0) {
			de=true;
		}
		if(x==n-1) {
			si=true;
		}
		System.out.println("la x "+x+" e la y -"+y+" in questa posizione");
		
		int ciclo=0;
		int cicloVecchio=0;
		
		
		while(fine) {
			
			
			
			/*i muri devono essre eretti due cicli dopo che è stato scelto il percorso, nello stesso modo
			 * probabilmente, così che si possa effettuare il controllo di un altro mmuro nella casella avanti.
			 * si può tenere il valore di quale direzione usare, e quindi quali muri costruire in una stringa o
			 * come al solito scegliendo tra 1 2 3 4. e poi fare degli if che attivano la funzione giusta a seconda
			 * dei tipi di muri che vanno costruiti, lasciando quindi invariati i dintorni, senza aggiungere i "più"*/
			
			//creazione muri
			if(ciclo!=cicloVecchio) {
			pos = creaMuri(pos, xV3, yV3, nodilà3);
			}
			
			if(gi) {
				cicloVecchio=ciclo;
				if(y==n-2) {
					pos = creaMuri(pos, xV1, yV1, nodilà);
					y+=1;
					pos[y][x]='f';
					System.out.println("la x "+x+" e la y -"+y+" in questa posizione");
				}else{
					if(pos[y+1][x]!='#') {
						y+=1;
						pos[y][x]=' ';
						xV3=xV2;
						xV2=xV1;
						xV1=x;
						yV3=yV2;
						yV2=yV1;
						yV1=y;
						gi=false;
						nodilà3=nodilà2;
						nodilà2=nodilà;
						nodilà='a';
						ciclo++;
						System.out.println("la x "+x+" e la y -"+y+" in questa posizione");	
					}else {
						gi=false;
					}
				}
			}
			
			
			if(su) {
				cicloVecchio=ciclo;
				if(y==1) {
					pos = creaMuri(pos, xV1, yV1, nodilà);
					y-=1;
					pos[y][x]='f';
					System.out.println("la x "+x+" e la y -"+y+" in questa posizione");
				}else{
					if(pos[y-1][x]!='#') {
						y-=1;
						pos[y][x]=' ';
						xV3=xV2;
						xV2=xV1;
						xV1=x;
						yV3=yV2;
						yV2=yV1;
						yV1=y;
						su=false;
						nodilà3=nodilà2;
						nodilà2=nodilà;
						nodilà='b';
						ciclo++;
						System.out.println("la x "+x+" e la y -"+y+" in questa posizione");
					}else {
						su=false;
					}
				}	
			}
			
			
			if(de) {
				cicloVecchio=ciclo;
				if(x==n-2) {
					pos = creaMuri(pos, xV1, yV1, nodilà);
					x+=1;
					pos[y][x]='f';
					System.out.println("la x "+x+" e la y -"+y+" in questa posizione");
				}else{
					if(pos[y][x+1]!='#') {
						x+=1;
						pos[y][x]=' ';
						xV3=xV2;
						xV2=xV1;
						xV1=x;
						yV3=yV2;
						yV2=yV1;
						yV1=y;
						de=false;
						nodilà3=nodilà2;
						nodilà2=nodilà;
						nodilà='c';
						ciclo++;
						System.out.println("la x "+x+" e la y -"+y+" in questa posizione");
					}else {
						de=false;
					}
				}
			}
			
			
			if(si) {
				cicloVecchio=ciclo;
				if(x==1) {
					pos = creaMuri(pos, xV1, yV1, nodilà);
					x-=1;
					pos[y][x]='f';
					System.out.println("la x "+x+" e la y -"+y+" in questa posizione");
				}else{
					
					if(pos[y][x-1]!='#') {
						x-=1;
						pos[y][x]=' ';
						xV3=xV2;
						xV2=xV1;
						xV1=x;
						yV3=yV2;
						yV2=yV1;
						yV1=y;
						si=false;
						nodilà3=nodilà2;
						nodilà2=nodilà;
						nodilà='d';
						ciclo++;
						System.out.println("la x "+x+" e la y -"+y+" in questa posizione");
					}else {
						si=false;
					}
				}
			}
			
			/*
			 * controlli da fare:
			 * che non torni indietro§
			 * che non faccia percorsi larghi due§
			 * che non rompa muri già costruiti§
			 * */
			boolean ok =false;
			
			int direzione=0;
			
			do {
				direzione= (int) (Math.random() * 4) +1;
				
				if(direzione==1 && nodilà!='b' && x%2==1) {
					ok=true;
					gi=true;
				}
				if(direzione==2 && nodilà!='a' && x%2==1) {
					ok=true;
					su=true;
				}
				if(direzione==3 && nodilà!='d' && y%2==1) {
					ok=true;
					de=true;
				}
				if(direzione==4 && nodilà!='c' && y%2==1) {
					ok=true;
					si=true;
				}
				
			}while(ok==false);
			
			
			if(y==0 || y==n-1 || x==0 || x==n-1) {
				
				fine=false;
			}	
		}
		
		aggiornaTabella(pos, n);
		
	}
	static char[][] creaMuri(char[][] pos, int xV3, int yV3, char nodilà3) {
		
			if(nodilà3=='a') {
				if(pos[yV3][xV3+1]!=' ') {
					pos[yV3][xV3+1]='#';
				}
				if(pos[yV3][xV3-1]!=' ') {
				pos[yV3][xV3-1]='#';
				}
				if(pos[yV3+1][xV3]!=' ') {
					pos[yV3+1][xV3]='#';
				}
				if(pos[yV3+1][xV3+1]!=' ') {
					pos[yV3+1][xV3+1]='#';
				}
				if(pos[yV3+1][xV3-1]!=' ') {
					pos[yV3+1][xV3-1]='#';
				}
			}
			if(nodilà3=='b') {
				
				if(pos[yV3][xV3+1]!=' ') {
					pos[yV3][xV3+1]='#';
				}
				if(pos[yV3][xV3-1]!=' ') {
				pos[yV3][xV3-1]='#';
				}
				if(pos[yV3-1][xV3]!=' ') {
					pos[yV3-1][xV3]='#';
				}
				if(pos[yV3-1][xV3+1]!=' ') {
					pos[yV3-1][xV3+1]='#';
				}
				if(pos[yV3-1][xV3-1]!=' ') {
					pos[yV3-1][xV3-1]='#';
				}
			}
			if(nodilà3=='c') {
				if(pos[yV3+1][xV3]!=' ') {
					pos[yV3+1][xV3]='#';
				}
				if(pos[yV3-1][xV3]!=' ') {
					pos[yV3-1][xV3]='#';
				}
				if(pos[yV3+1][xV3+1]!=' ') {
					pos[yV3+1][xV3+1]='#';	
				}
				if(pos[yV3-1][xV3+1]!=' ') {
					pos[yV3-1][xV3+1]='#';
				}
				if(pos[yV3][xV3+1]!=' ') {
					pos[yV3][xV3+1]='#';
				}
			}
			if(nodilà3=='d') {
				if(pos[yV3+1][xV3]!=' ') {
					pos[yV3+1][xV3]='#';
				}
				if(pos[yV3-1][xV3]!=' ') {
					pos[yV3-1][xV3]='#';
				}
				if(pos[yV3+1][xV3-1]!=' ') {
					pos[yV3+1][xV3-1]='#';	
				}
				if(pos[yV3-1][xV3-1]!=' ') {
					pos[yV3-1][xV3-1]='#';
				}
				if(pos[yV3][xV3-1]!=' ') {
					pos[yV3][xV3-1]='#';
				}
			}
		
		return pos;
	}

	
	static void creaTabella(char [][] pos, int n) {

		for(int i=0;i<n;i++) {
			
			for(int j=0;j<n;j++) {
				
				if(i==0||i==n-1||( j==0 ||j==n-1)) {
					pos[i][j]='#';
					
				}else {
					pos[i][j]='-';
					
				}
				
			}
		}
	}
	
	static void aggiornaTabella(char [][] pos, int n) {
		for(int i=0;i<n;i++) {
			
			for(int j=0;j<n;j++) {
				
				System.out.print(pos[i][j]);
				System.out.print(" ");
				
			}
			System.out.println("");
		}
	}

	public static void main(String[] args) {
		
		int n=31;
		char[][] pos = new char[n][n];
		
		creaTabella(pos, n);
		
		percorso(pos, n);

	}

}
