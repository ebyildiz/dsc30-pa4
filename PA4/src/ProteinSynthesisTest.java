import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProteinSynthesisTest {

    ProteinSynthesis ex1 = new ProteinSynthesis();
    ProteinSynthesis ex2 = new ProteinSynthesis();
    ProteinSynthesis ex3 = new ProteinSynthesis();

    //example DNAs

    String dna1 ="TACAAGCCG";
    String dna1Exp = "UACAAGCCG";

    String dna2 = "ATGTAGGGGGCTTCA";
    String dna2Exp = "AUGUAGGGGGCUUCA";

    String dna3 = "AGC";
    String dna3Exp = "AGC";

    String dna4 = "CCACATCGTCTC";
    String dna4Exp = "CCACAUCGUCUC";

    String dna6 = "";
    String dna6Exp = "";

    String dna5 = "CCACATCGTCT"; //invalid
    String dna7 = "AT"; //invalid
    String QueuetoString(MyQueue testQueue){
        if (testQueue.isEmpty()){
            return "";
        }
        return String.valueOf(testQueue.dequeue()) + QueuetoString(testQueue);

    }
    @Test
    void transcribeDNA() {
        MyQueue RNA1 = ex1.transcribeDNA(dna1);
        MyQueue RNA2 = ex2.transcribeDNA(dna2);
        MyQueue RNA3 = ex3.transcribeDNA(dna3);
        MyQueue RNA4 = ex3.transcribeDNA(dna4);
        MyQueue RNA5 = ex3.transcribeDNA(dna6);

        assertEquals(QueuetoString(RNA1), dna1Exp);
        assertEquals(QueuetoString(RNA2), dna2Exp);
        assertEquals(QueuetoString(RNA3), dna3Exp);
        assertEquals(QueuetoString(RNA4), dna4Exp);
        assertEquals(QueuetoString(RNA4), dna6Exp);


        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ex1.transcribeDNA(dna5);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ex1.transcribeDNA(dna7);
        });



    }

    @Test
    void translateRNA() {
        MyQueue RNA1 = ex1.transcribeDNA("ATGATCTCGTAA"); //AUGAUCUCGUAA as RNA MIS* as Protein
        MyQueue RNA2 = ex2.transcribeDNA("CCCCTGTCATAA"); // CCCCUGUCAUAA as RNA  empty protein
        MyQueue RNA3 = ex3.transcribeDNA("CTAATGCTATAA"); // CUAAUGCUAUAA as RNA ML* as Protein
        MyQueue RNA4 = ex3.transcribeDNA("AUGTGT"); // AUGUGU as RNA MC as Protein

        MyQueue Protein1 = ex1.translateRNA(RNA1);
        MyQueue Protein2 = ex1.translateRNA(RNA2);
        MyQueue Protein3 = ex1.translateRNA(RNA3);
        MyQueue Protein4 = ex1.translateRNA(RNA4);

        assertEquals(QueuetoString(Protein1), "MIS*");
        assertEquals(QueuetoString(Protein2), "");
        assertEquals(QueuetoString(Protein3), "ML*");
        assertEquals(QueuetoString(Protein4), "MC");


    }

}
