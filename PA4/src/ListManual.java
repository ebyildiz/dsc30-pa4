import java.util.*;

/**
 * Answers for Questions in PA4:ListManual
 *
 * @author Elif Yildiz
 * @since  1/30/2024
 */

public class ListManual {

// No style for this file. How poetic :D

    public static ArrayList<String>  listManipulations() {

        ArrayList<String> answers = new ArrayList<>(11);
        // Each index corresponds to the task number. Example is
        // at index 0.

        answers.add("h = h.next;");
        answers.add("h.next = r;");
        answers.add("r = h;");
        answers.add("t = h.next;");
        answers.add("h.elem = t.elem;");
        answers.add("h.elem = h.next.next.elem;");
        answers.add("h.next.next.next = h;");
        answers.add("h.next = h.next.next;");
        answers.add("while ((r!=null) && (r.elem!='M')) {r = r.next;}");  //put your statements in one line, follow the column Structures
        answers.add("Node h = new Node('A', null); h.next = new Node('B', null); h.next = new Node('C', null); h.next = new Node('D', null));");  //put your statements in one line, follow the column Structures
        answers.add("h.next.next.next = new Node('D', null);");
        return answers;
    }
}
