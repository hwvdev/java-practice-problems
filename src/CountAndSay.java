class CountAndSay {
    public String countAndSay(int n) {
        if(n == 1) return "1";

        String prevAns = countAndSay(n-1);
        int cnt = 1;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<prevAns.length(); i++){
            if(i+1<prevAns.length() && prevAns.charAt(i)==prevAns.charAt(i+1)){
                cnt++;
            } else {
                sb.append(cnt).append(prevAns.charAt(i));
                cnt = 1;
            }
        }
        return sb.toString();
    }
}