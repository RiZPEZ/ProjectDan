import java.io.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.io.IOException;


public class Main {

    private static Scanner keyboard = new Scanner(System.in);
    private static List<Language> alllanguage = new ArrayList<>();

    private static    Java java = new Java("Java");
    private static    C c = new C("C");
    private static    Python python = new Python("Python");
    private static    Ruby ruby = new Ruby("Ruby");


    public static void main(String[] args) throws IOException {

        alllanguage.add(java);
        alllanguage.add(c);
        alllanguage.add(python);
        alllanguage.add(ruby);

        loadUser();

        System.out.print(" What's your favorite language ?  ");
        System.out.print("\n");


        for (int i = 0; i < alllanguage.size() ; i++)
        {
            System.out.println( (i+1) + "." + alllanguage.get(i).getName() );
        }

        int select = selection("Selection your language  : ", "Error", 0, alllanguage.size());

        System.out.println("You select " + alllanguage.get(select).getName() + " language");


        boolean found = false;

        do
        {

            System.out.print("Enter Your Name : ");
            String newMember = keyboard.nextLine();

            for (String member : alllanguage.get(select).getMember()) {

                if (Objects.equals(newMember, member)) {
                    found = true;
                    break;
                }

                else
                {
                    found = false;
                }

            }

            if (found == true) {
                System.out.println("Du");
            }

            else
            {
                System.out.println("Suc");
                alllanguage.get(select).addMember(newMember);
                writeUser();
                break;

            }

        } while (found == true);



    }


    public static int selection(String inPutMessage, String showError, int low, int hight) {

        int selection = -1;
        boolean again = true;


        while(again)
        {

            try
            {

                System.out.print(inPutMessage);
                selection = Integer.parseInt(keyboard.nextLine());

                selection = selection-1;

                if (selection < low || selection > hight)
                    throw new NumberFormatException();

                again = false;

            }

            catch (NumberFormatException e)
            {
                System.out.println(showError);

            }

        }

        return selection;
    }


    public static void loadUser() throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/User.txt"))))
        {
            String[] fields = null;
            String line = null;

            for (int i = 0 ; ((line = reader.readLine()) != null); i++) {
                fields = line.split(",");

                if (Objects.equals(fields[0], alllanguage.get(i).getName()))
                {
                    for (int j = 1; j < fields.length; j++)
                    {
                        alllanguage.get(i).addMember(fields[j]);

                    }
                }

            }

        }


        catch (IOException e)
        {
            System.out.println("Error: " + e.getMessage());
        }

    }




    public static void writeUser()
    {

        try(BufferedWriter bW = new BufferedWriter(new FileWriter(new File("src/User.txt"))))

        {
            for (Language language: alllanguage)
            {
                bW.write(language.getName());

                if (language.getMember().isEmpty() == false)
                {

                    for (int j = 0; j < language.getMember().size() ; j++)
                    {
                        bW.write(',');
                        bW.write(language.getMemberName(j));

                    }
                }

                bW.newLine();
            }

        }
        catch (IOException e)
        {
            System.out.println("Error" + e.getMessage());
        }

    }


//
//
////    public Deque<Book> listBook = loadBook();
//
//    public static void writeOn(Deque<Book> listBook)
//    {
//
//
//        try (BufferedWriter bW = new BufferedWriter(new FileWriter(new File("src/book.txt"))))
//        {
////            System.out.printf("\t\t\t%1$s  %2$s  %3$s  %4$s  %5$s  %6$s " + "\r\n",  newBook.getCode(), newBook.getName()
////                    , newBook.getPrice(), newBook.getRent_day(), newBook.getStatus(), newBook.getStatus());
//
//            for (Book loopBook: listBook)
//            {
//                bW.write(loopBook.getCode()+ "," + loopBook.getName()
//                        +  "," +loopBook.getPrice()+ "," + loopBook.getRent_day() + "," + loopBook.getStatus()+ "," + loopBook.getStatus());
//                bW.newLine();
//            }
//
//
//        }
//
//        catch (IOException e)
//        {
//            System.out.println("Error: " + e.getMessage());
//        }
//
//    }
//
//
//    public static Book checkTheBook(Deque<Book> listBook ,String aCode)
//    {
//
//        for (Book book : listBook)
//        {
//
//            if (Objects.equals(book.getCode(), aCode)) {
//
//                return book;
//            }
//
//        }
//
//        return new Book("Not found");
//
//    }

}