import java.util.Scanner;

    public class Menu {
        public double TiempoB, TiempoQ, TiempoS, TiempoR;
        public void MBurbuja(int[] A) {
            int i, j, aux;
            long startTime = System.nanoTime();
            for (i = 0; i < A.length - 1; i++) {
                for(j = 0; j < A.length - i - 1; j++){
                    if (A[j + 1] < A[j]) {
                        aux = A[j + 1];
                        A[j + 1] = A[j];
                        A[j] = aux;
                    }
                }
            }
            long estimatedTime = System.nanoTime() - startTime;
            TiempoB=estimatedTime;
            System.out.println("Los numeros quedan ordenados de la siguiente manera.");
            for(i=0;i<A.length;i++)
                System.out.print(A[i]+"\t");
        }
        public void QuickSort(int A[], int izq, int der){
            int pivote=A[izq]; //Tomamos primer elemento como pivote
            int i=izq; //I Realiza la búsqueda de izquierda a derecha
            int j=der; //J Realiza la búsqueda de derecha a izquierda
            int aux;
            long startTime = System.nanoTime();
            while (i<j){ //Mientras no se cruzen las búsquedas
                while(A[i]<=pivote && i<j) i++; //Busca elementos mayor que pivote
                while(A[j]>pivote) j--; //Busca elemantos menor qie pivote
                if (i<j) { // Si no se han cruzado
                    aux= A[i]; //Los intercambia
                    A[i]=A[i];
                    A[j]=aux;
                }
            }
            A[izq]=A[j]; //Se coloca el pivote en su lugar de forma que integramos
            A[j]=pivote; //Los menores a su izquierda y los mayores a su derecha
            if (izq<j-1)
                QuickSort(A, izq,j-1); //Ordenamos subarray izquierdo
            if (j+1 < der)
                QuickSort(A,j+1,der); //Ordenamos subarray derecho
            long estimatedTime = System.nanoTime() - startTime;
            TiempoQ=estimatedTime;
            System.out.println("Se ordeno la lista.");
        }
        public void Shell(int A[]) {
            int salto, aux, i;
            boolean cambios;
            long startTime = System.nanoTime();
            for (salto=A.length/2; salto!=0; salto/=2) {
                cambios=true;
                while (cambios) { //Mientras se intercambie algun elemento
                    cambios=false;
                    for (i=salto; i< A.length; i++) //Se da una pasada
                        if(A[i-salto]>A[i]) { //Y si estan desordeados
                            aux=A[i]; //Se reordenan
                            A[i]=A[i-salto];
                            A[i-salto]=aux;

                            cambios=true; //Y se marca como cambio
                        }
                }
            }
            Long estimatedTime = System.nanoTime() - startTime;
            TiempoS=estimatedTime;
            System.out.println("Se ordeno la lista.");
        }
        public void RadixSort (int[] A){
            long startTime = System.nanoTime();
            if (A.length == 0)
                return;
            int[] [] np = new int[A.length][2];
            int[] q = new int[0x100];
            int i,j,k,l,f = 0;
            for (k=0;k<4;k++) {
                for (i=0; i<(np.length-1);i++)
                    np[i][1] = i+1;
                np[i][1]=-1;
                for(i=0;i<q.length;i++)
                    q[i] = -1;
                for (f=i=0;i<A.length;i++){
                    j=((0xFF<<(k<<3))&A[i])>>(k<<3);
                    if(q[j] == -1)
                        l=q[j]=f;
                    else{
                        l = q[j];
                        while (np[l][1] !=-1)
                            l = np[l][1];
                        np[l][1] = f;
                        l = np[l][1];
                    }
                    f=np[f][1];
                    np[l][0] = A[i];
                    np[l][1] = -1;
                }
                for (l=q[i=j=0];i<0x100;i++)
                    for(l=q[i];l!=-1;l=np[l][1])
                        A[j++] = np[l][0];
            }
            for (int x=0;x<A.length;x++){
                System.out.print(A[x]+"\t");
            }
            for (i=0;i<A.length;i++)
                System.out.print(A[i] + " ");
            long estimatedTime = System.nanoTime() - startTime;
            TiempoR=estimatedTime;
        }

        public void Tiempo(){
            System.out.println("Tiempo de Burbuja= "+TiempoB);
            System.out.println("Tiempo de Quicksort= "+TiempoQ);
            System.out.println("Tiempo de Shell= "+TiempoS);
            System.out.println("Tiempo de RadixSort= "+TiempoR);
        }
        public static void main(String[] args){
            //TODO Auto-generated method stub
            Scanner tecla= new Scanner(System.in);
            boolean Fin=true;
            int i, Num, opc;
            int F[]=new int[1000];
            Menu Obj=new Menu();
            for (i=0;i<F.length;i++){
                Num=(int) (Math.random()*1000)+1;
                F[i]=Num;
            }
            do{
                System.out.println("");
                System.out.println("Seleccione una opcion");
                System.out.println("1) Mostrar lista generada.");
                System.out.println("2) Ordenar con metodo de ordenamiento Burbuja.");
                System.out.println("3) Ordenar con metodo de ordenamiento Quick Sort.");
                System.out.println("4) Ordenar con metodo de ordenamiento Shell.");
                System.out.println("5) Ordenar con metodo de ordenamiento RadixSort.");
                System.out.println("6) Estadisticas.");
                System.out.println("7) Salir.");

                opc=tecla.nextInt();
                switch (opc){
                    case 1:
                        for (i=0;i<F.length;i++){
                            System.out.print(F[i]+"\t");
                        }
                        break;
                    case 2:
                        int A[]=F.clone();
                        Obj.MBurbuja(A);
                        break;
                    case 3:
                        int B[]=F.clone();
                        Obj.QuickSort(B,B.length/2,3);
                        break;
                    case 4:
                        int C[]=F.clone();
                        Obj.Shell(C);
                        break;
                    case 5:
                        int D[]=F.clone();
                        Obj.RadixSort(D);
                        break;
                    case 6:
                        Obj.Tiempo();
                        break;
                    case 7:
                        Fin=false;
                        System.out.println("El programa ha finalizado.");
                        break;
                    default:
                        break;
                }
            }while (Fin);
        }
    }

