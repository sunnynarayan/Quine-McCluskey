/*
 * Name - Sunny Narayan
 * Problem - minimization using Quine MacCloskey method...
 */


import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author sunny
 */
public class qm {

    static ArrayList<Terms> primeImp = new ArrayList<>();

    public static void main(String[] args) {
        //Declaration of Variables
        String  terms, dcTerms = "";
        int var = 0;
        ArrayList<String> term = new ArrayList<>();
        ArrayList<String> dcTerm = new ArrayList<>();

        boolean check1 = true;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the min terms :");
        terms = in.nextLine();
        System.out.println("Is there any don't care terms(y/n) :");
        String dc = in.nextLine();
        if (dc.equalsIgnoreCase("y")) {
            System.out.println("Enter the don't care terms :");
            dcTerms = in.nextLine();
        } else {
            check1 = false;
        }

        //Storing terms in the arraylists
        String str1[] = terms.split(" ");
        for (int i = 0; i < str1.length; i++) {
            if (str1[i] != null) {
                if (term.contains(str1[i])) {
                    System.out.println("Errors ! repitition of terms found ");
                    System.exit(0);
                } else {
                    term.add(str1[i]);
                }
            }
        }
        if (check1 == true) {
            String str2[] = dcTerms.split(" ");
            for (int i = 0; i < str2.length; i++) {
                if (str2[i] != null) {
                    if (term.contains(str2[i]) || dcTerm.contains(str2[i])) {
                        System.out.println("Errors ! repitition of terms found ");
                        System.exit(0);
                    } else {
                        dcTerm.add(str2[i]);
                        term.add(str2[i]);
                    }
                }
            }
        }
        

        //finding the largest element...
        int max = 0;
        for (String temp : term) {
            if (max < Integer.parseInt(temp)) {
                max = Integer.parseInt(temp);
            }
        }
        for (int i=0 ; i<8 ;i++){
            if ( max< Math.pow(2, i)){
                var = i;
                break;
            }
        }
        if (term.size() == (Math.pow(2, var))){
            System.out.println("The minimized expression is using QuineMcClusky Method is: 1");
        }
        else{

        //Arranging the terms binary number
        ArrayList<Terms> noOne = new ArrayList<>();
        ArrayList<Terms> oneOne = new ArrayList<>();
        ArrayList<Terms> twoOne = new ArrayList<>();
        ArrayList<Terms> threeOne = new ArrayList<>();
        ArrayList<Terms> fourOne = new ArrayList<>();
        ArrayList<Terms> fiveOne = new ArrayList<>();
        ArrayList<Terms> sixOne = new ArrayList<>();
        ArrayList<Terms> sevenOne = new ArrayList<>();

        ArrayList<Terms> col1 = new ArrayList<>();
        ArrayList<Terms> col2 = new ArrayList<>();
        ArrayList<Terms> col3 = new ArrayList<>();
        ArrayList<Terms> col4 = new ArrayList<>();
        ArrayList<Terms> col5 = new ArrayList<>();
        ArrayList<Terms> col6 = new ArrayList<>();

        for (int i = 0; i < term.size(); i++) {
            String temp7 = binary(Integer.parseInt(term.get(i)));
            int temp = (temp7.length()) - (temp7.replace("1", "").length());

            if (temp == 0) {
                noOne.add(new Terms(temp7, term.get(i)));
            }
            if (temp == 1) {
                oneOne.add(new Terms(temp7, term.get(i)));
            }
            if (temp == 2) {
                twoOne.add(new Terms(temp7, term.get(i)));
            }
            if (temp == 3) {
                threeOne.add(new Terms(temp7, term.get(i)));
            }
            if (temp == 4) {
                fourOne.add(new Terms(temp7, term.get(i)));
            }
            if (temp == 5) {
                fiveOne.add(new Terms(temp7, term.get(i)));
            }
            if (temp == 6) {
                sixOne.add(new Terms(temp7, term.get(i)));
            }
            if (temp == 7) {
                sevenOne.add(new Terms(temp7, term.get(i)));
            }
        }

        ArrayList<Terms> sorted = new ArrayList<>();
        for (Terms temp : noOne) {
            sorted.add(new Terms(temp.binary, temp.num));
        }
        for (Terms temp : oneOne) {
            sorted.add(new Terms(temp.binary, temp.num));
        }
        for (Terms temp : twoOne) {
            sorted.add(new Terms(temp.binary, temp.num));
        }
        for (Terms temp : threeOne) {
            sorted.add(new Terms(temp.binary, temp.num));
        }
        for (Terms temp : fourOne) {
            sorted.add(new Terms(temp.binary, temp.num));
        }
        for (Terms temp : fiveOne) {
            sorted.add(new Terms(temp.binary, temp.num));
        }
        for (Terms temp : sixOne) {
            sorted.add(new Terms(temp.binary, temp.num));
        }
        for (Terms temp : sevenOne) {
            sorted.add(new Terms(temp.binary, temp.num));
        }
        System.out.println("Printing numbers with their binary");
        for (Terms temp : sorted) {
            System.out.println(temp.binary + " " + temp.num);
        }

        //making columns...
        addingDash(sorted, col1);
        addingDash(col1, col2);
        addingDash(col2, col3);
        addingDash(col3, col4);
        addingDash(col4, col5);
        addingDash(col5, col6);

        //adding prime implicants
        addPI(sorted);
        addPI(col1);
        addPI(col2);
        addPI(col3);
        addPI(col4);
        addPI(col5);
        addPI(col5);
        addPI(col6);

        for (int i = 0; i < primeImp.size(); i++) {
            for (int j = i + 1; j < primeImp.size(); j++) {
                if (primeImp.get(i).binary.equals(primeImp.get(j).binary)) {
                    primeImp.remove(j);
                }
            }
        }

//removing don't care terms
        for (String temp : dcTerm) {
            for (Terms p : primeImp) {
                if (p.num.contains(temp)) {
                    p.num.replace(temp, "");
                    p.num1.remove(temp);
                }
            }
        }

        for (int i = 0; i < primeImp.size(); i++) {
            if (primeImp.get(i).num1.size() == 0) {
                primeImp.remove(i);
            }
        }

//finding the minimized solution..
        ArrayList<String> noOfPITerm = new ArrayList<>();
        ArrayList<String> answer = new ArrayList<>();
        String fanswer = "";
        for (int i = 0; i < primeImp.size(); i++) {
            for (int j = 0; j < primeImp.get(i).num1.size(); j++) {
                if (!(noOfPITerm.contains(primeImp.get(i).num1.get(j)))) {
                    noOfPITerm.add(primeImp.get(i).num1.get(j));
                }
            }
        }
        String[][] matrix = new String[primeImp.size() + 1][noOfPITerm.size() + 1];
//remember the term matrix[0][0] = null;
        for (int i = 1; i <= primeImp.size(); i++) {
            matrix[i][0] = primeImp.get(i - 1).binary;
        }
        matrix[0][0] = "#######";
        for (int i = 1; i <= noOfPITerm.size(); i++) {
            matrix[0][i] = noOfPITerm.get(i - 1);
        }
        for (int i = 1; i <= primeImp.size(); i++) {
            for (int j = 1; j <= noOfPITerm.size(); j++) {
                for (int k = 0; k < primeImp.get(i - 1).num1.size(); k++) {
                    if (primeImp.get(i - 1).num1.get(k).equals(matrix[0][j])) {
                        matrix[i][j] = "*";
                    }
                }
            }
        }
        for (int i = 1; i <= primeImp.size(); i++) {
            for (int j = 1; j <= noOfPITerm.size(); j++) {
                if (matrix[i][j] == null) {
                    matrix[i][j] = "0";
                }
            }
        }
//printing
        for (int i = 0; i <= primeImp.size(); i++) {
            for (int j = 0; j <= noOfPITerm.size(); j++) {
                System.out.print(matrix[i][j] + "     ");
            }
            System.out.println();
        }
//---finding essential prime implicants

        for (int j = 1; j <= noOfPITerm.size(); j++) {
            int count = 0;
            int index = 0;
            for (int i = 1; i <= primeImp.size(); i++) {
                if (matrix[i][j].equals("*")) {
                    index = i;
                    count++;
                }
            }
            if (count == 1) {
                if (!answer.contains(matrix[index][0])) {
                    answer.add(matrix[index][0]);
                }
            }
        }
        for (String temp : answer) {
            System.out.println(answer.toString());
        }

        ArrayList<String> colNo = new ArrayList<>();
        for (Terms temp : primeImp) {
            for (String temp1 : answer) {
                if (temp1.equals(temp.binary)) {
                    for (String temp2 : temp.num1) {
                        if (!colNo.contains(temp2)) {
                            colNo.add(temp2);
                        }
                    }
                }
            }
        }
        System.out.println(colNo.toString());

        for (int i = 0; i < answer.size(); i++) {
            for (int j = 0; j < primeImp.size(); j++) {
                if (primeImp.get(j).binary.equals(answer.get(i))) {
                    primeImp.remove(j);
                }
            }
        }

        for (String temp1 : colNo) {
            for (int i = 0; i < primeImp.size(); i++) {
                for (int j = 0; j < primeImp.get(i).num1.size(); j++) {
                    if (primeImp.get(i).num1.get(j).equals(temp1)) {
                        primeImp.get(i).num1.remove(j);
                    }
                }
            }
        }


//checking dominance..
        for (int i = 0; i < primeImp.size(); i++) {
            for (int l = i + 1; l < primeImp.size(); l++) {
                int count = 0;
                if (primeImp.get(i).num1.size() > primeImp.get(l).num1.size()) {
                    for (int j = 0; j < primeImp.get(i).num1.size(); j++) {
                        for (int k = 0; k < primeImp.get(l).num1.size(); k++) {
                            if (primeImp.get(i).num1.get(j).equals(primeImp.get(l).num1.get(k))) {
                                count++;
                            }
                        }
                        if (count == primeImp.get(l).num1.size()) {
                            if (!answer.contains(primeImp.get(i).binary)) {
                                answer.add(primeImp.get(i).binary);
                                primeImp.remove(l);
                                
                            }
                        }
                    }
                } else if (primeImp.get(i).num1.size() < primeImp.get(l).num1.size()) {
                    for (int j = 0; j < primeImp.get(i).num1.size(); j++) {
                        for (int k = 0; k < primeImp.get(l).num1.size(); k++) {
                            if (primeImp.get(i).num1.get(j).equals(primeImp.get(l).num1.get(k))) {
                                count++;
                            }
                        }
                        if (count == primeImp.get(i).num1.size()) {
                            if (!answer.contains(primeImp.get(l).binary)) {
                                answer.add(primeImp.get(l).binary);
                                primeImp.remove(i);
                                
                            }
                        }
                    }
                } else if (primeImp.get(i).num1.size() == primeImp.get(l).num1.size()) {
                    if (!answer.contains(primeImp.get(l).binary)) {
                        answer.add(primeImp.get(l).binary);
                        primeImp.remove(l);
                        
                    }

                }
            }
        }
        System.out.println(answer.toString());
        System.out.println("The minimized expression is using QuineMcClusky Method is:   ");
        for (String temp : answer) {
            System.out.print(converter(temp, var) + " +");
        }
        }
    }
    
//function to add dashes...
    public static void addingDash(ArrayList<Terms> a1, ArrayList<Terms> a2) {
        //if (a1.size() != 0 && a2.size() != 0) {
        for (int i = 0; i < a1.size(); i++) {
            for (int j = i + 1; j < a1.size(); j++) {
                int count1 = 0;
                for (int k = 0; k < 7; k++) {
                    if (a1.get(i).binary.charAt(k) != a1.get(j).binary.charAt(k)) {
                        count1++;
                    }
                }
                if (count1 == 1) {
                    String dashed = null;
                    for (int k = 0; k < 7; k++) {
                        if (a1.get(i).binary.charAt(k) != a1.get(j).binary.charAt(k)) {
                            char[] bArray = a1.get(i).binary.toCharArray();
                            bArray[k] = '-';
                            dashed = String.valueOf(bArray);
                        }
                    }
                    String combo = a1.get(i).num + "," + a1.get(j).num;
                    a2.add(new Terms(dashed, combo));
                    a1.get(i).check = true;
                    a1.get(j).check = true;
                }
            }

            
        }

    }

    public static String converter(String b, int v) {
        String[] alpha = {"A", "B", "C", "D", "E", "F", "G"};
        String[] alphaC = {"A'", "B'", "C'", "D'", "E'", "F'", "G'"};
        String min = "";
        for (int i = (7 - v); i < 7; i++) {
            if (b.charAt(i) == '0') {
                min = min + alphaC[i - 7 + v];
            }
            if (b.charAt(i) == '1') {
                min = min + alpha[i - 7 + v];
            }
        }
        return min;
    }

    public static String binary(int n) {
        String b = Integer.toBinaryString(n);
        for (int i = b.length(); i < 7; i++) {
            b = "0" + b;
        }
        return b;
    }

    public static void addPI(ArrayList<Terms> a1) {
        for (Terms temp : a1) {
            if (!temp.check) {
                primeImp.add(new Terms(temp.binary, temp.num));
            }
        }
    }
}

class Terms {

    boolean check;
    String binary;
    ArrayList<String> num1 = new ArrayList<>();
    String num;

    public Terms(String binary, String n) {
        this.binary = binary;
        num = n;
        check = false;
        make();
    }

    public void make() {
        String str1[] = num.split(",");
        for (int i = 0; i < str1.length; i++) {
            if (str1[i] != null) {
                num1.add(str1[i]);
            }
        }
    }
}
