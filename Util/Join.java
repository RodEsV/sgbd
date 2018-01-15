package Util;

import impl.NupletInt;
import stockage.Nuplet;

public class Join {
    public Nuplet joinNuplets(Nuplet a, Nuplet b, int att){
        byte[] aux = new byte[a.size()+b.size()-1];
        int items = 0;
        for (int i = 0; i < a.size(); i++) {
            aux[i] = (byte)a.getAtt(i);
            items++;
        }
        for (int j = 0; j < b.size(); j++) {
            if (j != att) {
                aux[items] = (byte) b.getAtt(j);
                items++;
            }
        }
        return new NupletInt(aux);
    }

    void swap(NupletInt arr[],int i,int j)
    {
        NupletInt t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    int partition (NupletInt arr[], int att, int l, int h)
    {
        Byte x = (byte)arr[h].getAtt(att);
        int i = (l - 1);

        for (int j = l; j <= h- 1; j++)
        {
            if ((byte)arr[j].getAtt(att) <= x)
            {
                i++;
                swap(arr,i,j);
            }
        }
        swap(arr,i+1,h);
        return (i + 1);
    }

    public void sort(NupletInt arr[], int att, int l, int h) {
        int stack[] = new int[h - l + 1];

        int top = -1;

        stack[++top] = l;
        stack[++top] = h;

        while (top >= 0) {
            h = stack[top--];
            l = stack[top--];

            int p = partition(arr, att, l, h);
            if (p - 1 > l) {
                stack[++top] = l;
                stack[++top] = p - 1;
            }
            if (p + 1 < h) {
                stack[++top] = p + 1;
                stack[++top] = h;
            }
        }
    }

    /*
    public static void main(String[] args) {
        int datasetSize = 8;
        int nupletSize = 3;
        NupletInt[] tab = new NupletInt[datasetSize];
        for (int i = 0; i < datasetSize; i++) {
            tab[i] = new NupletInt(nupletSize);
            for (int j = 0; j < nupletSize; j++) {
                tab[i].putAtt(j, (byte) (j + Math.random()*3));
            }
        }

        Join join = new Join();
        for (Nuplet a : tab){
            System.out.println(a);
        }
        join.sort(tab,1,0, tab.length-1);
        System.out.println("....");
        for (Nuplet a : tab){
            System.out.println(a);
        }
        join.sort(tab,2,0, tab.length-1);
        System.out.println("....");
        for (Nuplet a : tab){
            System.out.println(a);
        }
    }*/
}
