
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
		boolean gi=true;
		boolean su=true;
		boolean de=true;
		boolean si=true;
		char nodilà='z';
		char nodilà2='v';
		
		
		//punto d'inizio
		do {

			x = (int) (Math.random() * (n-0.1));
			y = (int) (Math.random() * (n-0.1));
			
			if(pos[y][x]== '#') {
			
				pos[y][x]= ' ';
				System.out.println(pos[y][x]);
				inizio=false;
				
			}
		}while(inizio==true);
		
		if(y==0) {
			su = false;		
			de = false;	
			si = false;
		}
		if(y==n-1) {
			gi = false;		
			de = false;	
			si = false;
		}
		if(x==0) {
			gi = false;
			su = false;		
			si = false;
		}
		if(x==n-1) {
			gi = false;
			su = false;
			de = false;
		}
		System.out.println("la x "+x+" e la y -"+y+" in questa posizione");
		
		int ciclo=2;
		
		while(fine) {
			//int numero=0;
			if(ciclo%2==0) {
				for(int i=0;i<n;i++) {
					
					for(int j=0;j<n;j++) {
						
						if(pos[i][j]=='%') {
							pos[i][j]='#';
						}
					}
				}
			}
			if(ciclo%2==0) {
				for(int i=0;i<n;i++) {
					
					for(int j=0;j<n;j++) {
						
						if(pos[i][j]=='+') {
							pos[i][j]='%';
						}
					}
				}
			}
			
			
			if(gi) {
				if(y==n-2) {
					y+=1;
					pos[y][x]=' ';
					nodilà2=nodilà;
					nodilà='a';
					ciclo++;
					System.out.println("la x "+x+" e la y -"+y+" in questa posizione");
				}else{
					y+=1;
					if(pos[y+1][x]!='#') {
						pos[y][x]=' ';
						pos[y][x+1]='+';
						pos[y][x-1]='+';
						pos[y+1][x]='+';
						pos[y+1][x+1]='+';
						pos[y+1][x-1]='+';
						gi=false;
						nodilà2=nodilà;
						nodilà='a';
						ciclo++;
						System.out.println("la x "+x+" e la y -"+y+" in questa posizione");
					}else {
						y-=1;
						gi=false;
						System.out.println("qualcosa non va1");
					}
				}
			}
			
			
			if(su) {
				if(y==1) {
					y-=1;
					pos[y][x]=' ';
					nodilà2=nodilà;
					nodilà='b';
					ciclo++;
					System.out.println("la x "+x+" e la y -"+y+" in questa posizione");
				}else{
					y-=1;
					if(pos[y-1][x]!='#') {
						pos[y][x]=' ';
						pos[y][x+1]='+';
						pos[y][x-1]='+';
						pos[y-1][x]='+';
						pos[y-1][x+1]='+';
						pos[y-1][x-1]='+';
						su=false;
						nodilà2=nodilà;
						nodilà='b';
						ciclo++;
						System.out.println("la x "+x+" e la y -"+y+" in questa posizione");
					}else {
						y+=1;
						su=false;
						System.out.println("qualcosa non va2");
						
					}
				}	
			}
			
			
			if(de) {
				if(x==n-2) {
					x+=1;
					pos[y][x]=' ';
					nodilà2=nodilà;
					nodilà='c';
					ciclo++;
					System.out.println("la x "+x+" e la y -"+y+" in questa posizione");
				}else{
					x+=1;
					if(pos[y][x+1]!='#') {
						pos[y][x]=' ';
						pos[y+1][x]='+';
						pos[y-1][x]='+';
						pos[y+1][x+1]='+';
						pos[y-1][x+1]='+';
						pos[y][x+1]='+';
						de=false;
						nodilà2=nodilà;
						nodilà='c';
						ciclo++;
						System.out.println("la x "+x+" e la y -"+y+" in questa posizione");
					}else {
						x-=1;
						de=false;
						System.out.println("qualcosa non va3");
					}
				}
			}
			
			
			if(si) {
				if(x==1) {
					x-=1;
					pos[y][x]=' ';
					nodilà2=nodilà;
					nodilà='d';
					ciclo++;
					System.out.println("la x "+x+" e la y -"+y+" in questa posizione");
				}else{
					x-=1;
					if(pos[y][x-1]!='#') {
						pos[y][x]=' ';
						pos[y+1][x]='+';
						pos[y-1][x]='+';
						pos[y+1][x-1]='+';
						pos[y-1][x-1]='+';
						pos[y][x-1]='+';
						si=false;
						nodilà2=nodilà;
						nodilà='d';
						ciclo++;
						System.out.println("la x "+x+" e la y -"+y+" in questa posizione");
					}else {
						x+=1;
						si=false;
						System.out.println("qualcosa non va4");
					}
				}
			}
			
			/*
			 * controlli da fare:
			 * che non torni indietro§
			 * che non faccia percorsi larghi due§
			 * che non rompa muri già costruiti
			 * oppure che passi attraverso muri già costruiti, anche se, se oltre il muro c'è la fine è un problema
			 * 
			 * */
			boolean ok =false;
			
			int direzione=0;
			
			do {
				direzione= (int) (Math.random() * 4) +1;
				
				if(direzione==1 && nodilà!='b' && nodilà2!='b') {
					ok=true;
					gi=true;
				}
				if(direzione==2 && nodilà!='a' && nodilà2!='a') {
					ok=true;
					su=true;
				}
				if(direzione==3 && nodilà!='d' && nodilà2!='d') {
					ok=true;
					de=true;
				}
				if(direzione==4 && nodilà!='c' && nodilà2!='c') {
					ok=true;
					si=true;
				}
				
			}while(ok==false);
			
			
			if(y==0 || y==n-1 || x==0 || x==n-1) {
				fine=false;
			}
			//numero++;
		}
		
			for(int i=0;i<n;i++) {
				
				for(int j=0;j<n;j++) {
					
					if(pos[i][j]=='%') {
						pos[i][j]='#';
					}
				}
			}
		aggiornaTabella(pos, n);
		
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
		
		int n=20;
		char[][] pos = new char[n][n];
		
		creaTabella(pos, n);
		
		percorso(pos, n);

	}

}
