public class Addition implements MathOperation{

    @Override
    public int operate(int a, int b) {
        return a+b;
    }

    public void usingWrapperInteger(int a, int b){
        MathOperation mathOperation = Integer::sum;
        System.out.println("Wrapper class inside Addition: "+mathOperation.operate(a,b));
    }
}
