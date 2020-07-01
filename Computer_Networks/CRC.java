package weblab;

class CRC {
  static int size(long input){
    int output = 0;
    while(input>=1){
      output++;
      input=input/2;
    }
    return output;
  }
  

  /**
   * Calculates the CRC check value
   *
   * @param bitSequence       The input bit sequence
   * @param inputLength       The length of the input bit sequence (including possible leading zeros)
   * @param generatorSequence The generator bit sequence
   * @return The CRC check value
   */
  static long calculate(long bitSequence, int inputLength, long generatorSequence) {

    int size1=size(generatorSequence);
    size1--;
    bitSequence = bitSequence<<size1;

    while(size(bitSequence)>size1){
      long chislochislo=generatorSequence<<(size(bitSequence)-size1-1);
      bitSequence = bitSequence ^ chislochislo;
    }
    return bitSequence;
  }

  /**
   * Checks the correctness of the bit sequence.
   *
   * @param bitSequence       The CRC bit sequence excluding the CRC check value
   * @param inputLength       The length of the input bit sequence (including possible leading zeros)
   * @param generatorSequence The generator bit sequence used
   * @param checkSequence     The CRC check value to check against
   * @return true if the sequence is valid, false otherwise
   */
  static boolean check(long bitSequence, int inputLength, long generatorSequence, long checkSequence) {
    
    if(calculate(bitSequence,inputLength, generatorSequence)!=checkSequence) return false;
    return true;
  }
}

