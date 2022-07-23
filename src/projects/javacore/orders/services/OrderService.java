package projects.javacore.orders.services;

import java.util.InputMismatchException;
import java.util.Scanner;

import projects.javacore.orders.App;
import projects.javacore.orders.models.Order;
import projects.javacore.orders.models.OrderDetail;
import projects.javacore.orders.models.Product;

public class OrderService {
	static Scanner sc = new Scanner(System.in);
	public static float sum;

	public static void execute() {
		do {
			System.out.println("\n-------Welcome customer-------");
			System.out.println("\t1. Product display");
			System.out.println("\t2. Product searching");
			System.out.println("\t3. Add to cart");
			System.out.println("\t4. Remove from cart");
			System.out.println("\t5. Show order list");
			System.out.println("\t6. Purchase");
			System.out.println("\t0. Exit");
			System.out.print("Select option: ");
			int chon = Integer.parseInt(sc.nextLine());
			ProductService productService = new ProductService();
			switch (chon) {
			case 1:
				productService.show();
				break;
			case 2:
				productService.search();
				break;
			case 3:
				while (true) {
					try {
						order();
						break;
					} catch (Exception e) {
						System.out.println("\t Wrong format");
					}
				}
				;
				break;
			case 4:
				remove();
				break;
			case 5:
				show();
				break;
			case 6:
				purchase();
				break;
			case 0:
				System.out.println("Exit");
				return;
			default:
				System.out.println("Invalid selection");
			}
		} while (true);
	}

	public static void purchase() {
		for (Order order : App.ORDERS) {
			for (OrderDetail od : order.getOrderDetails()) {
				sum = sum + od.getPrice() * od.getQuantity();
			}
		}
		System.out.println("Your total price is: " + sum);
		ProductService.profit += sum / 10;
		App.ORDERS.clear();
		sum = 0;
	}

	public static void remove() {
		System.out.println("Enter the product to be remove:");

		try {
			System.out.println("Enter id: ");
			Integer id = sc.nextInt();
			sc.nextLine();

			for (int i = 0; i < App.ORDERS.size(); i++) {
				if (id.equals(App.ORDERS.get(i).getId())) {

					App.ORDERS.remove(i);

				}
			}

		} catch (InputMismatchException ei) {
			System.out.println("You have entered the wrong value, please try again!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void order() {

		System.out.println("-------------------Personal infomation first:----------------");

		try {
			System.out.print("Your id: ");
			int id = sc.nextInt();

			sc.nextLine();

			System.out.println("Your name: ");
			String customerName = sc.nextLine();

			System.out.println("Your number: ");
			String phone = sc.nextLine();

			System.out.println("Your email: ");
			String email = sc.nextLine();

			Order order = new Order(id, customerName, phone, email);

			Integer productId = -1;

			while (true) {

				System.out.println("Enter product id (-1 to stop): ");
				productId = sc.nextInt();
				sc.nextLine();

				if (productId.equals(-1)) {
					break;
				}
				Product product = null;
				for (Product p : App.PRODUCTS) {
					if (p.getId().equals(productId)) {
						product = p;
						break;
					}
				}

				if (product.getQuantity() == 0) {
					break;
				}

				System.out.println("Enter quantity: ");
				int quantity = sc.nextInt();
				sc.nextLine();

				if (0 < quantity && quantity <= product.getQuantity()) {
					product.setQuantity(product.getQuantity() - quantity);
				} else {

					System.out.println("Not enough stock");
					System.out.println("Enter quantity: ");
					quantity = sc.nextInt();
					sc.nextLine();

				}

				if (product == null) {
					System.out.println("Product id does not exist");
				}

				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setId(1);
				orderDetail.setOrderId(order.getId());
				orderDetail.setPrice(product.getPrice());
				orderDetail.setProductId(productId);
				orderDetail.setQuantity(quantity);

				order.getOrderDetails().add(orderDetail);

			}

			App.ORDERS.add(order);

		} catch (InputMismatchException ei) {
			System.out.println("You have entered the wrong value, please try again!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void show() {
		System.out.println("Order list:");
		String header = String.format("%s%15s%30s%30s", "Id", "Name", "Number", "Email");
		System.out.println(header);
		ProductService productService = new ProductService();

		for (Order order : App.ORDERS) {
			String row = String.format("%d%15s%30s%30s", order.getId(), order.getCustomerName(), order.getPhone(),
					order.getEmail());
			System.out.println(row);

			String orderDetailHeader = String.format("%30s%10s%30s%30s", "Id", "Name", "Price", "Quantity");
			System.out.println(orderDetailHeader);

			int i = 1;
			for (OrderDetail od : order.getOrderDetails()) {

				Product p = productService.getById(od.getProductId());

				String orderDetailRow = String.format("%30s%10s%30s%30s", i, p.getName(), od.getPrice(),
						od.getQuantity());
				System.out.println(orderDetailRow);
				i++;
			}
		}

	}
}
