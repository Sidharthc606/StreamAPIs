import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Addition addition=new Addition();
        Subtraction subtraction = new Subtraction();
        Multiplication multiplication = new Multiplication();
        Division division = new Division();
        System.out.println("Sum: "+ addition.operate(10,5));
        System.out.println("Multiplication: "+ multiplication.operate(10,5));
        System.out.println("Subtraction: "+ subtraction.operate(10,5));
        System.out.println("Division: "+ division.operate(10,5));



        //Using Anonymous class
        MathOperation add = new MathOperation() {
            @Override
            public int operate(int a, int b) {
                return a+b;
            }
        };

        MathOperation mul = new MathOperation() {
            @Override
            public int operate(int a, int b) {
                return a*b;
            }
        };

        System.out.println("Addition using anonymous class: "+add.operate(20,30));
        System.out.println("Multiplication using anonymous class: "+mul.operate(20,30));


        //Using Lambda function
        MathOperation addi = (a,b) -> a+b;  //(params) -> {body};
        System.out.println("Addition using Lambda Function: " +addi.operate(50,90));


        MathOperation add2 = Integer::sum;  //best for optimisation
        System.out.println("Addition Using Wrapper class: "+add2.operate(20,25));


        //Using function in separate Additon class
        Addition add3 = new Addition();
        add3.usingWrapperInteger(10,80);

        //Consumer just accepts any one argument(Good for doing anything without expecting any return value)
        Consumer<String> printer = (name)-> System.out.println(name);
        printer.accept("Sidharth");

        Consumer<Integer> adder = (num)-> System.out.println(num+num);
        adder.accept(5);

        //adding elements inside an array
        Consumer<Integer[]> addElements = (numbers)-> {
            int sum=0;
            for(int i=0;i< numbers.length;i++){
                sum+=numbers[i];
            }
            System.out.println("Sum of all elements in an array : "+sum);
        };
        addElements.accept(new Integer[] {5,10,15,25});

        //returns true or false(good for testing)
        Predicate<Integer> isEven = (n)-> n%2==0;
        System.out.println(isEven.test(5));
        System.out.println(isEven.test(6));


        // Function takes <T,R> as param and consumes T while returning R
        Function<Integer,String> concatInt = (num)-> "Numer- "+num;
        System.out.println(concatInt.apply(120));


        //Supplier
        Supplier<Double> getRandomNumber = ()-> Math.random();
//        Supplier<Double> getRandomNumber = ()->Math::random;
                System.out.println(getRandomNumber.get());

        //Stream API
        List<String> names = List.of("Sidharth","Shawn","sid","ruhz","gg","jai");
        names.stream().filter(n-> n.startsWith("S"))
                .map(n -> n.toUpperCase())
                .sorted()
                .forEach(n -> System.out.println(n));


        List<String> newNames = names.stream()
                .filter(n -> n.startsWith("S"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(newNames);

        Long noOfNames = names.stream()
                .filter(n -> n.startsWith("S"))
                .map(String::toUpperCase)
                .sorted()
                .count();

        System.out.println("No of names: "+noOfNames);


        List<Integer> num=new ArrayList<>();
        num.add(5);
        num.add(4);
        num.add(8);
        num.add(3);

        List<Integer> newList = num.stream()
                .filter(n->n%2 == 0)
                .collect(Collectors.toList());
        System.out.println(newList);


        Employee emp1=new Employee("Sidharth",9,"Software Developer",25000);
        Employee emp2=new Employee("Jai",10,"BA",50000);
        Employee emp3=new Employee("Arjun",11,"Fullstack",60000);
        Employee emp4=new Employee("Sid",12,"Software Developer",80000);

        List<Employee> developers = new ArrayList<>();

        developers.add(emp1);
        developers.add(emp2);
        developers.add(emp3);
        developers.add(emp4);

        List<String> employee = developers.stream()
                .filter(d-> d.getName().startsWith("S"))
                .map(Employee::getName)
                .collect(Collectors.toList());

        System.out.println(employee);

        //Count words starting with "A"
        List<String> words = List.of("Apple","Avocado","banana","Ambience");
         Long count = words.stream()
                 .filter(w->w.startsWith("A"))
                 .count();
        System.out.println("No. of words starting with A: " + count);

        //Remove Duplcates from a List
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,1);
        List<Integer> unique = list.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Unique: "+unique);


        //Sort Employees by Salary
        List<Employee> salary = developers.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
//                .map(Employee::getName)
                .collect(Collectors.toList());

        System.out.println("Salary: "+salary);

    }
}