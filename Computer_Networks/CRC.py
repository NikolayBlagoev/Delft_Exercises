from library import bit_count


class CRC:

    @staticmethod
    def calculate(bit_sequence: int, input_length: int, generator_sequence: int) -> int:
        size = generator_sequence.bit_length();
        bit_sequence=bit_sequence<<(size-1);
        while bit_sequence.bit_length()>(size-1):
            intermediate = generator_sequence<<(bit_sequence.bit_length()-size)
            bit_sequence=bit_sequence^intermediate
        print(bit_sequence)
        return bit_sequence


    @staticmethod
    def check(bit_sequence: int, input_length: int, generator_sequence: int, check_sequence: int) -> bool:
        size = generator_sequence.bit_length();
        bit_sequence=bit_sequence<<(size-1);
        while bit_sequence.bit_length()>(size-1):
            intermediate = generator_sequence<<(bit_sequence.bit_length()-size)
            bit_sequence=bit_sequence^intermediate
        if bit_sequence==check_sequence:
            return True
        
        return False
