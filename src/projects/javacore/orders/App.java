package projects.javacore.orders;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import projects.javacore.orders.models.Order;
import projects.javacore.orders.models.Product;
import projects.javacore.orders.services.OrderService;
import projects.javacore.orders.services.ProductService;

/**
 * Hello world!
 *
 */
public class App {

	public static List<Product> PRODUCTS = new ArrayList<Product>();
	public static List<Order> ORDERS = new ArrayList<Order>();

	public static void menu() {
		System.out.println("-----------------------Function list-----------------------");
		System.out.println("1. For customers");
		System.out.println("2. For managements");
		System.out.println("3. Exit");
	}

	public static void main(String[] args) {

		for (int i = 0; i < 4; i++) {
			Float price = (float) (i + 4);
			Product p = new Product(i + 1, "Product " + (i + 1), i + 1, "Description " + (i + 1), price,
					"Owner " + (i + 1));

			App.PRODUCTS.add(p);
		}

		int function = 0;

		do {
			menu();
			Scanner inputs = new Scanner(System.in);
			try {
				System.out.print("Select option: ");
				function = inputs.nextInt();
				ProductService productService = new ProductService();
				OrderService orderService = new OrderService();

				switch (function) {
				case 1:
					orderService.execute();
					break;
				case 2:
					productService.execute();
					break;

				default:
					break;
				}
			} catch (InputMismatchException ei) {
				System.out.println("You have entered the wrong value, please try again!");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} while (function != 3);

		System.out.println("GOODBYE!!");

	}
}
