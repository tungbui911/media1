package projects.javacore.orders.services;

import java.util.InputMismatchException;
import java.util.Scanner;

import projects.javacore.orders.App;
import projects.javacore.orders.models.Product;

public class ProductService {
	static Scanner sc = new Scanner(System.in);
	public static float profit;

	public static void execute() {
		do {
			System.out.println("\n-------Welcome management-------");
			System.out.println("\t1. Insert product");
			System.out.println("\t2. Edit product info");
			System.out.println("\t3. Delete product");
			System.out.println("\t4. Product display");
			System.out.println("\t5. Product searching");
			System.out.println("\t6. Revenue statistics");
			System.out.println("\t0. Exit");
			System.out.print("Select option: ");
			int chon = Integer.parseInt(sc.nextLine());
			OrderService orderService = new OrderService();
			switch (chon) {
			case 1:
				while (true) {
					try {
						insert();
						break;
					} catch (Exception e) {
						System.out.println("\t Wrong format");
					}
				}
				;
				break;
			case 2:
				update();
				break;
			case 3:
				delete();
				break;
			case 4:
				show();
				break;
			case 5:
				search();
				break;
			case 6:
				statistic();
				break;
			case 0:
				System.out.println("Exit");
				return;
			default:
				System.out.println("Invalid selection");
			}
		} while (true);
	}

	public static void statistic() {
		System.out.println("Doanh thu cua hang: " + profit);

	}

	public static void search() {

		try {
			System.out.println("Enter the name you want to search:");
			String name = sc.nextLine();
			String header = String.format("%s%15s%30s%15s%15s%15s", "Id", "Name", "Quantity", "Price", "Description",
					"Owner");
			System.out.println(header);

			for (int i = 0; i < App.PRODUCTS.size(); i++) {
				if (name.equals(App.PRODUCTS.get(i).getName())) {

					String row = String.format("%d%15s%30d%15f%15s%15s", App.PRODUCTS.get(i).getId(),
							App.PRODUCTS.get(i).getName(), App.PRODUCTS.get(i).getQuantity(),
							App.PRODUCTS.get(i).getPrice(), App.PRODUCTS.get(i).getDescription(),
							App.PRODUCTS.get(i).getOwner());
					System.out.println(row);

				}
			}

		} catch (InputMismatchException ei) {
			System.out.println("You have entered the wrong value, please try again!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void delete() {
		System.out.println("Enter the product to be delete:");

		try {
			System.out.println("Enter id: ");
			Integer id = sc.nextInt();
			sc.nextLine();

			for (int i = 0; i < App.PRODUCTS.size(); i++) {
				if (id.equals(App.PRODUCTS.get(i).getId())) {

					App.PRODUCTS.remove(i);

				}
			}

		} catch (InputMismatchException ei) {
			System.out.println("You have entered the wrong value, please try again!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void insert() {
		System.out.println("Enter product information:");

		try {
			System.out.print("Id: ");
			int id = sc.nextInt();

			sc.nextLine();

			System.out.println("Name: ");
			String name = sc.nextLine();

			System.out.println("Quantity: ");
			int quantity = sc.nextInt();

			sc.nextLine();

			System.out.println("Description:");
			String description = sc.nextLine();

			System.out.println("Price:");
			Float price = sc.nextFloat();

			sc.nextLine();

			System.out.println("Owner:");
			String owner = sc.nextLine();

			Product p = new Product(id, name, quantity, description, price, owner);
			App.PRODUCTS.add(p);

		} catch (InputMismatchException ei) {
			System.out.println("You have entered the wrong value, please try again!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void show() {
		System.out.println("List of product:");
		String header = String.format("%s%15s%30s%15s%15s%15s", "Id", "Name", "Quantity", "Price", "Description",
				"Owner");
		System.out.println(header);
		for (Product p : App.PRODUCTS) {
			String row = String.format("%d%15s%30d%15f%15s%15s", p.getId(), p.getName(), p.getQuantity(), p.getPrice(),
					p.getDescription(), p.getOwner());
			System.out.println(row);
		}

	}

	public static void update() {

		try {
			System.out.println("Enter id:");
			Integer id = sc.nextInt();
			Product product = null;

			for (Product p : App.PRODUCTS) {
				if (p.getId().equals(id)) {
					product = p;
					break;
				}
			}

			if (product == null) {
				System.out.println("Product code does not exist. Please try again!");
			}

			System.out.println("Product information");
			String row = String.format("%d%15s%30d%15f%15s%15s", product.getId(), product.getName(),
					product.getQuantity(), product.getPrice(), product.getDescription(), product.getOwner());
			System.out.println(row);

			for (int i = 0; i < App.PRODUCTS.size(); i++) {
				if (id.equals(App.PRODUCTS.get(i).getId())) {

					sc.nextLine();

					System.out.println("Name: ");
					String name = sc.nextLine();

					System.out.println("Quantity: ");
					int quantity = sc.nextInt();

					sc.nextLine();

					System.out.println("Description: ");
					String description = sc.nextLine();

					System.out.println("Price: ");
					Float price = sc.nextFloat();

					System.out.println("Owner: ");
					String owner = sc.nextLine();

					App.PRODUCTS.get(i).setName(name);
					App.PRODUCTS.get(i).setQuantity(quantity);
					App.PRODUCTS.get(i).setDescription(description);
					App.PRODUCTS.get(i).setPrice(price);
					App.PRODUCTS.get(i).setOwner(owner);

				}
			}

		} catch (InputMismatchException ei) {
			System.out.println("You have entered the wrong value, please try again!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Product getById(int id) {
		Product product = new Product();

		for (Product p : App.PRODUCTS) {
			if (p.getId().equals(id)) {
				product = p;
				break;
			}
		}
		return product;
	}
}
