import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import dao.BedDAOImpl;
import dao.BeddetailsDAOImpl;
import entity.Customer;
import entity.Bed;
import entity.Beddetails;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CustomerDAO dao = new CustomerDAOImpl();

        BedDAOImpl bedDAO = new BedDAOImpl();
        BeddetailsDAOImpl detailsDAO = new BeddetailsDAOImpl();

        while (true) {
            System.out.println("\n===== 客户管理系统 =====");
            System.out.println("1 查看所有客户");
            System.out.println("2 新增客户");
            System.out.println("3 删除客户");
            System.out.println("4 查看所有床位");     // 新加
            System.out.println("5 查看床位使用记录"); // 新加
            System.out.println("0 退出");

            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.println("\n客户列表：");
                    for (Customer c : dao.findAll()) {
                        System.out.println(c);
                    }
                    break;

                case 2:
                    System.out.print("姓名：");
                    String name = sc.nextLine();
                    System.out.print("年龄：");
                    int age = sc.nextInt(); sc.nextLine();
                    System.out.print("身份证：");
                    String idCard = sc.nextLine();
                    System.out.print("电话：");
                    String tel = sc.nextLine();
                    System.out.print("床位号：");
                    int bedId = sc.nextInt(); sc.nextLine();

                    dao.add(new Customer(name, age, idCard, tel, bedId));
                    System.out.println("新增成功！");
                    break;

                case 3:
                    System.out.print("输入要删除的ID：");
                    int id = sc.nextInt(); sc.nextLine();
                    dao.delete(id);
                    System.out.println("删除成功！");
                    break;

                case 4:
                    System.out.println("\n--- 床位列表 ---");
                    List<Bed> beds = bedDAO.findAll();
                    for (Bed b : beds) {
                        System.out.println(b);
                    }
                    break;

                case 5:
                    System.out.println("\n--- 床位使用记录 ---");
                    List<Beddetails> details = detailsDAO.findAll();
                    for (Beddetails d : details) {
                        System.out.println(d);
                    }
                    break;

                case 0:
                    System.out.println("退出");
                    return;

                default:
                    System.out.println("输入错误");
            }
        }
    }
}
