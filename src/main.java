import java.util.ArrayList;
import java.util.Arrays;

public class main {
    private final static int nCards=52;
    private final static int nCardHand=5;
    private final static int nTrys=1;

    public static void main(String[] args) {
        int[] cards=new int[nCards];
        for(int i=0;i<nCards;i++){
            cards[i]=i;
        }
        int[] hand=getCards(nCardHand,cards);
        Arrays.sort(hand);
        System.out.println(Arrays.toString(hand));

        int nPairs=0;
        int nTwoPairs=0;
        int nDrilling=0;

        for(int i=0;i<nTrys;i++){
            if(onePair(hand)){
                nPairs++;
            }
            if (twoPairs(hand)){
                nTwoPairs++;
            }
            if(drilling(hand)){
                nDrilling++;
            }
        }
        System.out.println("nPair: "+nPairs);
        System.out.println("nTwoPairs: "+nTwoPairs);
        System.out.println("nDrilling: "+nDrilling);

    }

    private static int[] getCards(int nCardHands, int[] cards){
        int[] a=new int[nCardHands];
        for(int i=0;i<nCardHands;i++){
           int x=(int)(Math.random()*cards.length);
            a[i]=cards[x];
           int y=cards[cards.length-i-1];
           cards[cards.length-i-1]=cards[x];
           cards[x]=y;
        }
        return a;
    }

    private static boolean onePair(int[] hand){
        for(int i=0;i<hand.length-1;i++){
            if(hand[i]%13==hand[i+1]%13){
                return true;
            }
        }
        return false;
    }

    private static boolean twoPairs(int[] hand){
        ArrayList<Integer> a=new ArrayList<>();
        for (int i=0;i<hand.length;i++){
            a.add(hand[i]);
        }

        int anz=0;
        for(int i=0;i<hand.length-1;i++){
            if(hand[i]%13==hand[i+1]%13){
                hand[i]=-i;
                hand[i+1]=-(i+1);
                anz++;
            }
        }
        if(anz==2){
            return true;
        }else{
            return false;
        }
    }

    private static boolean drilling(int[] hand){
        for(int i=0;i<hand.length-2;i++){
            if(hand[i]==hand[i+1] && hand[i]==hand[i+2]){
                return true;
            }
        }
        return false;
    }
}
