class HammingCode {
 private static double log2 (int input){
   return Math.log((double) input)/Math.log(2.0);
 }
  /**
   * Calculates the hamming code of the given bit sequence.
   *
   * @param bitSequence  The input bit sequence
   * @param inputLength  The length of the input bit sequence (including possible leading zeros)
   * @param isEvenParity Boolean indicating if the hamming algorithm should use even parity or not
   * @return The Hamming code sequence
   */
  static long calculate(long bitSequence, int inputLength, boolean isEvenParity) {
    long extra = 1;
    if(isEvenParity) extra=0;
    long output=0;
    if(bitSequence==0&&isEvenParity) return 0;
    
    for(int i=1;inputLength>0;i++){
      
      if(Integer.bitCount(i)==1){
        output=output<<1;
        output+=extra;
      }else{
        long cur=bitSequence>>(inputLength-1);
        long add=cur%2;
        if(add==1){
          for(int c=1;c<i;c=c*2){
            int intermediate=c&i;
            if(intermediate!=0){
              //System.out.println("Flipping bit "+c);
              long change=1L<<(i-(c)-1);
              output=output^change;
            }
          }
        }
        output=output<<1;
        output+=add;
        inputLength--;
      }
      System.out.println(Long.toBinaryString(output)+" "+i);
    }
    System.out.println(Long.toBinaryString(output));
  return output;
  }

  /**
   * Returns the corrected (if needed) hamming code of the given bit sequence.
   *
   * @param bitSequence  The Hamming code bit sequence
   * @param inputLength  The length of the input bit sequence (including possible leading zeros)
   * @param isEvenParity Boolean indicating if the hamming algorithm should use even parity or not
   * @return The correct Hamming code sequence
   */
  static long check(long bitSequence, int inputLength, boolean isEvenParity) {
    if(bitSequence==0&&!isEvenParity) return 1;
    if(bitSequence==0||inputLength==0) return bitSequence;
    
    System.out.println((int)log2(inputLength));
    int wrong=0;
    long extra = 1;
    if(isEvenParity) extra=0;
    int i = 1;
    while(i<=inputLength){
      
      if(Integer.bitCount(i)==1){
        
        long curr=bitSequence>>(inputLength-i);
        curr=curr%2;
        System.out.println("Found at "+curr);
        int copy=i;
        long sum=0;
        while(copy<=inputLength){
          if((copy&i)!=0){
            System.out.println("In here "+copy+" for "+i);
            sum+=(bitSequence>>(inputLength-copy))%2;
          }
          copy++;
        }
        System.out.println("sum "+sum);
        if((sum%2)!=extra) {
          wrong+=i;
          System.out.println("wrong at "+i);
        }
      }
      i++;
    }
    if(wrong!=0){
       long change=1L<<(inputLength-wrong);
              bitSequence=bitSequence^change;
    }
    System.out.println(Long.toBinaryString(bitSequence));
  return bitSequence;
  }
}
