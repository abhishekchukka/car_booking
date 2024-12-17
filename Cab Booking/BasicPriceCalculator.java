public class BasicPriceCalculator implements PriceCalculator {
    private static final double RATE_PER_KM = 10.0;

    @Override
    public double calculatePrice(double distance) {

        return distance * RATE_PER_KM;
    }
}
