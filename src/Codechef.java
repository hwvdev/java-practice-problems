import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef {
	public static void main (String[] args) throws java.lang.Exception{
		
		System.out.println("Message");
		Map<Character, Integer> m = new HashMap<>();
		String s="vijayijy";
		String t ="ijyy";
		
		for (char ch: t.toCharArray()) {
		    m.put(ch, m.getOrDefault(ch, 0)+1);
		} 
		int n = s.length();
       	int i=0;
       	int j=0;

        int length = Integer.MAX_VALUE;
        int ansi=0, ansj=0;
        Map<Character, Integer> temp = new HashMap<>();
        while(j<n) {
            char ch = s.charAt(j);
            temp.put(ch, temp.getOrDefault(ch, 0)+1);
            boolean equal = compare(temp, m);
            if(equal) {
                if(length>j-i+1) {
                    length = j-i+1;
                    ansi=i;
                    ansj=j;
                }
                while(i<=j && compare(temp, m)) {
                    if(length>j-i+1) {
                        length = j-i+1;
                        ansi=i;
                        ansj=j;
                    }
                    char c = s.charAt(i);
                    temp.put(c, temp.get(c)-1);
                    int cnt = temp.get(c);
                    if (cnt==0)
                        temp.remove(c);
                    i++;
                }
            }
            j++;
        }
        System.out.println("Message: "+ansi +" "+ ansj);
	}
	
	public static boolean compare(Map<Character, Integer> temp, Map<Character, Integer> map) {
	    for(Map.Entry<Character, Integer> es : map.entrySet()) {
            if (temp.get(es.getKey())==null) {
                return false;
            }
	        if(es.getValue()>temp.get(es.getKey()))
	            return false;
	    }
	    return true;
	}
}
