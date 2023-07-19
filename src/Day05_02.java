import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Day05_02 {


    // Advent of Code 2022 Day 5   Part 2
    //  Read about the problem here  https://adventofcode.com/2022/day/5#part2

    public static void main(String[] args) throws IOException, FileNotFoundException {
        Path current = Paths.get(".");
        String s = current.toAbsolutePath().toString();
        System.out.println("Path" + s);

        File inputTxt = new File("src/2022/day01/input.txt");
        BufferedReader input = new BufferedReader(new FileReader(inputTxt));
        String sections;

        ArrayList<String> xyzList = new ArrayList<>();
        int pointsValue = 0;
        boolean contained = false;
        String[] stacks = new String[9];
        stacks[0] = "";
        stacks[1] = "";
        stacks[2] = "";
        stacks[3] = "";
        stacks[4] = "";
        stacks[5] = "";
        stacks[6] = "";
        stacks[7] = "";

        stacks[8] = "";


        int currentStack = 0;
        boolean stillInStacks = true;
        int moveHowMany = 0, moveFrom = 0, moveTo = 0;


        while ((sections = input.readLine()) != null) {

            //  Read in the data
            currentStack = 0;
            System.out.println("Line of data: " + sections);

            if (stillInStacks == true) {


                for (int i = 0; i < sections.length(); i++) {
                    // read three characters
                    char char1 = sections.charAt(i++);
                    System.out.println("char 1" + char1);

                    char char2 = sections.charAt(i++);
                    System.out.println("char 2" + char2);

                    char char3 = sections.charAt(i++);
                    System.out.println("char 3" + char3);


                    if (char2 == '1') {
                        stillInStacks = false;
                        // Get rid of the next two lines
                        sections = input.readLine();
                        sections = input.readLine();

                        break;
                    }
                    if (char2 != ' ') {
                        System.out.println("Adding" + char2);
                        System.out.println("Before : " + stacks[currentStack]);
                        stacks[currentStack] = stacks[currentStack] + char2;
                        System.out.println("After : " + stacks[currentStack]);

                    }
                    currentStack++;
                }
            }

            // Now, to get the instructions

            if (!stillInStacks) {
                System.out.println(" This is not still in stacks: " + sections);
                String whatsNext = sections.substring(0, 4);
                System.out.println("Whats next : " + whatsNext);
                if (sections.substring(0, 4).equals("move")) {
                    System.out.println(" Time to move: " + sections.substring(4));
                    String result = GetNumbers(sections.substring(5));
                    System.out.println(" Result: " + result);
                    moveHowMany = Integer.parseInt(result);
                    int shift = 0;
                    if (moveHowMany >9)
                        shift = 1;
                    moveFrom = Character.getNumericValue(sections.charAt(12+shift));
                    moveTo = Character.getNumericValue(sections.charAt(17+shift));
                    System.out.println("Move " + moveHowMany + " from " + moveFrom + " to " + moveTo);
                    //+ moveHowMany + "  from " + moveFrom " to " + moveTo);

                }

                System.out.println("Pre Stack 1: " + stacks[0]);
                System.out.println("Pre Stack 2: " + stacks[1]);
                System.out.println("Pre Stack 3: " + stacks[2]);
                //  Start moving stuff
                for (int i = moveHowMany; i > 0; i--) {
                    // Remove from stack
                    char crate = stacks[moveFrom - 1].charAt(i-1);
                    // Put it in the new stack
                    stacks[moveTo - 1] = crate + stacks[moveTo - 1];
                    System.out.println("Now Stack 1: " + stacks[0]);
                    System.out.println("Now Stack 2: " + stacks[1]);
                    System.out.println("Now Stack 3: " + stacks[2]);

                }
                stacks[moveFrom - 1] = stacks[moveFrom - 1].substring(moveHowMany);
                System.out.println("After Stack 1: " + stacks[0]);
                System.out.println("After Stack 2: " + stacks[1]);
                System.out.println("After Stack 3: " + stacks[2]);

            }


        }
        input.close();

        System.out.println("Stack 1: " + stacks[0]);
        System.out.println("Stack 2: " + stacks[1]);
        System.out.println("Stack 3: " + stacks[2]);
        System.out.println("Stack 4: " + stacks[3]);
        System.out.println("Stack 5: " + stacks[4]);
        System.out.println("Stack 6: " + stacks[5]);
        System.out.println("Stack 7: " + stacks[6]);
        System.out.println("Stack 8: " + stacks[7]);
        System.out.println("Stack 9: " + stacks[8]);



    }


    private static String GetNumbers(String InputString) {
        String Numbers = "0123456789";
        int i = 0;
        char[] chars = InputString.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if(Character.isDigit(c))
                sb.append(c);
            else
                break;
        }
        return sb.toString();
    }

}