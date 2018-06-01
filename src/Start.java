public class Start {

    public static void main(String[] args) {

        ExtraReader r = new ExtraReader();

        try {
            r.doWork();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
