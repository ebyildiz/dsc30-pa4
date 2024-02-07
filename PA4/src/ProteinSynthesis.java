/*
    Name: Elif Yildiz
    PID:  A16962992
 */

/**
 * This class translates a DNA into RNA, then, codon sequences to amino acids. Finally, it
 * appends the amino acids into a growing protein sequence using the MyQueue class to mimic
 * the process of protein synthesis.
 *
 * @author Elif Yildiz
 * @since 2/3/2024
 */
class ProteinSynthesis {
    private CodonMap codonMap = new CodonMap();

    private final static int CODON_LENGTH = 3;

    /**
     * This method takes in a dna sequence as string and translates it into an RNA sequence
     * by replacing "T"s with "U"s
     *
     * @param dna the dna nucleotide sequence as String
     * @return CharQueue: the translated DNA into RNA as a queue
     * @throws IllegalArgumentException if dna is not divisible by 3
     */
    public MyQueue transcribeDNA(String dna) {
        int sizeDna = dna.length();
        if (sizeDna % CODON_LENGTH != 0) {
            throw new IllegalArgumentException();
        }
        MyQueue rna = new MyQueue<Character>();
        String rnaStr = dna.replace('T', 'U');
        for (int i = 0; i < sizeDna; i++) {
            rna.enqueue(rnaStr.charAt(i));
        }
        return rna;
    }

    /**
     *This method takes an RNA sequence as a queue and if there is a protein sequence in it,
     * returns that by translating every three nucleotides into single codons (using CodonMap class)
     * Finally adds that codon to the protein sequence until there is a stop codon
     *
     * (if there is no start codon, it returns an empty queue)
     * (if there is no stop codon, it returns the full protein sequence from the starting codon)
     *
     * @param rna a Queue that imitates RNA
     * @return Queue: the protein sequence of the given RNA
     */
    public MyQueue translateRNA(MyQueue<Character> rna) {
        MyQueue proteinSeq = new MyQueue<Character>();

        // find AUG (starting codon)
        boolean start = false;
        while (!rna.isEmpty() && start==false) {
            boolean isA = rna.dequeue() == 'A';
            boolean isU = rna.dequeue() == 'U';
            boolean isG = rna.dequeue() == 'G';

            if (isA && isU && isG) {
                start = true;
                proteinSeq.enqueue('M');
            }
        }

        if(start) {
            char aminoAcid = 0;
            while (!rna.isEmpty() && aminoAcid!='*') {
                char first = rna.dequeue();
                char second = rna.dequeue();
                char third = rna.dequeue();
                //get codon
                String codon = String.valueOf(first) +
                        String.valueOf(second) +
                        String.valueOf(third);

                //translate to amino acid
                aminoAcid = codonMap.getAminoAcid(codon);

                //add the amino acid to the proteinSequence
                proteinSeq.enqueue(aminoAcid);
            }
        }

        return proteinSeq;
    }

}
