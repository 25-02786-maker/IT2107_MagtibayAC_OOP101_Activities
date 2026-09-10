import java.util.Scanner;

public class CanteenOrderingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Menu Data
        String[] menuItems = { "Takoyaki", "Spinach Pizza", "Shawarma", "Chicken Biryani", "Potato Mojos",
                "Chicken Sisig" };
        double[] menuPrices = { 129.00, 199.00, 79.00, 229.00, 179.00, 1091.00 };

        // Order Tracking Variables
        int totalQuantity = 0;
        double totalAmount = 0.0;

        // Display Menu
        System.out.println("****************************************");
        System.out.println("         TINDAHAN NG MGA NAGLILIHI      ");
        System.out.println("****************************************");
        for (int i = 0; i < menuItems.length; i++) {
            System.out.printf("%d. %-10s $%.2f%n", (i + 1), menuItems[i], menuPrices[i]);
        }
        System.out.println("****************************************\n");

        char orderAgain;
        do {
            System.out.print("Enter item number: ");
            int itemNum = scanner.nextInt();

            System.out.print("Enter quantity (1-10): ");
            int qty = scanner.nextInt();

            System.out.print("Are you a student? (Y/N): ");
            char studentChoice = scanner.next().toUpperCase().charAt(0);
            boolean isStudent = (studentChoice == 'Y');

            // Validation
            boolean valid = true;
            if (itemNum < 1 || itemNum > menuItems.length) {
                System.out.println(" Invalid item number! Order skipped.");
                valid = false;
            }
            if (qty < 1 || qty > 10) {
                System.out.println(" Quantity must be 1–10! Order skipped.");
                valid = false;
            }

            if (!valid) {
                System.out.print("\nDo you want to order again? (Y/N): ");
                orderAgain = scanner.next().toUpperCase().charAt(0);
                System.out.println();
                continue;
            }

            // Calculate
            double price = menuPrices[itemNum - 1];
            double subtotal = price * qty;
            double discountRate = 0.0;

            if (isStudent && subtotal >= 500) {
                discountRate = 0.15;
            } else if (isStudent) {
                discountRate = 0.10;
            } else if (subtotal >= 500) {
                discountRate = 0.05;
            }

            double discountAmount = subtotal * discountRate;
            double finalAmount = subtotal - discountAmount;

            // Accumulate totals
            totalQuantity += qty;
            totalAmount += finalAmount;

            // Order Summary
            System.out.println("\n Order Placed!");
            System.out.printf("Item: %s  Qty: %d  Final:%.2f%n",
                    menuItems[itemNum - 1], qty, finalAmount);

            System.out.print("\nDo you want to order again? (Y/N): ");
            orderAgain = scanner.next().toUpperCase().charAt(0);
            System.out.println();

        } while (orderAgain == 'Y');

        // Final Receipt
        System.out.println("****************************************");
        System.out.println("            FINAL RECEIPT               ");
        System.out.println("****************************************");
        System.out.printf("Total Quantity Purchased : %d pcs%n", totalQuantity);
        System.out.printf("FINAL AMOUNT TO PAY      : $%.2f%n", totalAmount);
        System.out.println("****************************************");
        System.out.println("       Thank you for ordering!         ");
        System.out.println("****************************************");

        scanner.close();
    }
}