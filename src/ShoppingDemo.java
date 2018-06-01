import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

public class ShoppingDemo {

	public static Person person = new Person();
	public static List<Product> products = new ArrayList<>();
	public static List<ShoppingList> detail = new ArrayList<>();

	static class Person {
		protected String name;
		protected String sex;
		protected double money;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public double getMoney() {
			return money;
		}

		public void setMoney(double money) {
			this.money = money;
		}

		@Override
		public String toString() {
			return "Name:" + name + "," + "Sex:" + sex + "," + "Money:" + money;
		}
	}

	static class Product extends Person {
		protected String productName;
		protected double price;
		protected int num;

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

		@Override
		public String toString() {
			return "ProductName:" + productName + "," + "Price:" + price + "," + "Num:" + num;
		}
	}

	static class ShoppingList extends Product {
		private double totalPrice;
		private int totalNum;

		public double getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(double totalPrice) {
			this.totalPrice = totalPrice;
		}

		public int getTotalNum() {
			return totalNum;
		}

		public void setTotalNum(int totalNum) {
			this.totalNum = totalNum;
		}

		@Override
		public String toString() {
			return "Name :" + name + "\t" + "ProductName :" + productName + "\t" + "Price :" + price + "\t"
					+ "TotalPrice : " + totalPrice + "\t" + "ToatalNum : " + totalNum + "\n";
		}
	}

	public static Person scannerPerson() {
		Scanner scanner = new Scanner(System.in);
		String Name = scanner.nextLine();
		String Sex = scanner.nextLine();
		double Money = scanner.nextDouble();

		person.setName(Name);
		person.setSex(Sex);
		person.setMoney(Money);

		System.out.println("Name :" + Name + "\t" + "Sex :" + Sex + "\t" + "Money :" + Money);
		return person;
	}

	public static List<Product> scannerProduct() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("pls input products items:");
		int Items = scanner.nextInt();
		System.out.println("Items:" + Items);
		System.out.println("pls input products' details(productName,price,num): ");

		for (int i = 0; i < Items; i++) {
			String ProductName = scanner.next();
			double Price = scanner.nextDouble();
			int Num = scanner.nextInt();

			Product product = new Product();
			product.setProductName(ProductName);
			product.setPrice(Price);
			product.setNum(Num);
			products.add(product);

			System.out.println("ProductName :" + ProductName + "\t" + "Price :" + Price + "\t" + "Num :" + Num);
		}
		return products;
	}

	public static void showList(List<Product> list) {
		for (Product product : list) {
			System.out.println(product);
		}
	}

	public static void purchase(List<Product> list) {
		Scanner scanner = new Scanner(System.in);
		String ProName = scanner.nextLine();

		boolean flag = false;

		for (Product product : list) {
			if (product.productName.equals(ProName)) {
				flag = true;
				if (product.price <= person.getMoney()) {
					if (product.num > 0) {
						System.out.println("purchase successful ! :) ");
						product.num--;
						person.money -= product.price;

						ShoppingList shoppingList = new ShoppingList();
						shoppingList.setName(person.getName());
						shoppingList.setProductName(ProName);
						shoppingList.setPrice(product.price);
						shoppingList.setNum(1);
						shoppingList(shoppingList);

						continue;
					}
					System.out.println("sry ,the product has sold out ! :(");
					continue;
				}
				System.out.println("sry ,you dont hava enough money to pay for it ! :( ");
				continue;
			}
		}
		if (flag == false) {
			System.out.println("product name error ,pls input the right product name !");
		}
	}

	public static void shoppingList(ShoppingList shoppingList) {
		if (detail != null && detail.size() > 0) {
			boolean flag = false;
			for (ShoppingList sl : detail) {
				if (sl.getProductName().equals(shoppingList.getProductName())) {
					sl.totalNum++;
					sl.totalPrice += shoppingList.price;
					flag = true;
				}
			}
			if (!flag) {
				shoppingList.setTotalPrice(shoppingList.getPrice());
				shoppingList.setTotalNum(1);
				detail.add(shoppingList);
			}
		} else {
			shoppingList.setTotalPrice(shoppingList.getPrice());
			shoppingList.setTotalNum(1);
			detail.add(shoppingList);
		}
	}

	public static void shoppingCar() {
		System.out.println("pls choose the product that you want to buy !   pls follow those guidelines :"+ "\n" 
				+"\t"+"¢Ù£ºinput 'add' to add products to ur shoppingCar " + "\n"
				+"\t"+"¢Ú£ºinput 'delete' to detele wat u dont want to pruchase" + "\n"
				+"\t"+"¢Û£ºinput 'OK' to purchase those products :)");

		while (true) {
			System.out.println("pls input 'add' , 'delete' or 'OK' !");
			Scanner scanner = new Scanner(System.in);
			String fun = scanner.nextLine();
			if (fun.equals("add")) {
				// Ìí¼Ó
				boolean flag = true;
				while (flag) {
					System.out.println(
							"press 'enter' after each input 'name' to enter the next !,press ' return' to last menu");
					Scanner scanner1 = new Scanner(System.in);
					String name = scanner1.nextLine();
					if (!name.equals("return")) {
						if (products != null && products.size() > 0) {
							for (Product product : products) {
								if (product.getProductName().equals(name)) {
									ShoppingList list = new ShoppingList();
									list.setName(person.getName());
									list.setProductName(name);
									list.setPrice(product.price);
									list.setNum(1);
									shoppingList(list);
									System.out.println("----------------------------------- shopping car -----------------------------------");
									System.out.println(detail);
									System.out.println("----------------------------------- shopping car -----------------------------------");
								}
							}
						}
					} else {
						flag = false;
					}
				}
				continue;
			} else if (fun.equals("delete")) {
				// É¾³ý
				System.out.println("press 'enter' after each input 'name' to enter the next !");
				Scanner scanner2 = new Scanner(System.in);
				String name = scanner2.nextLine();

				Iterator<ShoppingList> i = detail.iterator();
				while (i.hasNext()) {
					ShoppingList s = i.next();
					if (s.getProductName().equals(name)) {
						i.remove();
					}
				}
				continue;
			} else if (fun.equals("OK")) {
				// È·ÈÏ¹ºÂò
				int carPrice = 0;
				boolean flag = true;
				if (detail != null && detail.size() > 0) {
					for (ShoppingList lsit : detail) {
						for (Product products : products) {
							if (products.productName.equals(lsit.productName)) {
								if (products.num >= lsit.totalNum) {
									carPrice += lsit.totalPrice;
								}else {
									flag = false;
									System.out.println("sry ,the product has sold out ! :(");
								}
							}
						}
					}
					if(flag) {
						if (carPrice <= person.money) {
							person.money -= carPrice;
							for (ShoppingList lsit : detail) {
								for (Product products : products) {
									if (products.productName.equals(lsit.productName)) {
										products.num -= lsit.totalNum;
									}
								}
							}

							System.out.println("purchase successful ! :) ");
						}else {
							System.out.println(" sry ,you dont hava enough money to pay for it ! :( ");
							System.out.println(" pls delete something !");
							continue;
						}
					}else {
						continue;
					}
				}
				break;
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("pls input your details(Name,Sex,Money): ");
		scannerPerson();
		System.out.println("================== welcome to supermarket !==================");
		List<Product> list = scannerProduct();
		System.out.println("====================== show products ========================");
		showList(list);
		System.out.println("====================== show products ========================");
		System.out.println("\n");
		System.out.println("input 'Car' using Shopping Car ; otherwise purchase products one by one .");
		Scanner scanner2 = new Scanner(System.in);
		String choose = scanner2.nextLine();
		if (choose.equals("Car")) {
			shoppingCar();
		}else {
			while (true) {
				System.out.println("pls choose the product that you want to buy ! :) ");
				purchase(list);
				System.out.println("do u want continue ? if u want , pls input Y ,otherwise N");
				Scanner scanner = new Scanner(System.in);
				String choice = scanner.next();
				if (choice.equals("N")) {
					break;
				}
			}
		}
		System.out.println("====================== show products ========================");
		showList(list);
		System.out.println("====================== show products ========================");
		System.out.println("\n");
		System.out.println("======================* shopping list *========================");
		System.out.println("details:" + detail);
		System.out.println("======================* shopping list *========================");
		System.out.println("\n");
		System.out.println("balance :" + person.money + "\t" + "tks for ur shopping ! :)");
	}

}
