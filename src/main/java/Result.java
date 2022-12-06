public class Result {

    String step1;
    String step2;

    public Result() {
        step1="-";
        step2="-";
    }

    public Result(String step1, String step2) {
        this.step1=step1;
        this.step2=step2;
    }

    public String getStep1() {
        return step1;
    }

    public String getStep2() {
        return step2;
    }

    public void setStep1(String step1) {
        this.step1=step1;
    }

    public void setStep2(String step2) {
        this.step2=step2;
    }

}
