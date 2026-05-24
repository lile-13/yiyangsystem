import dao.*;
import entity.*;
import util.DBUtil;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static User currentUser = null;

    // DAO instances
    private static CustomerDAO customerDAO = new CustomerDAOImpl();
    private static BedDAO bedDAO = new BedDAOImpl();
    private static BeddetailsDAO beddetailsDAO = new BeddetailsDAOImpl();
    private static RoomDAO roomDAO = new RoomDAOImpl();
    private static OutwardDAO outwardDAO = new OutwardDAOImpl();
    private static BackdownDAO backdownDAO = new BackdownDAOImpl();
    private static NurseContentDAO nurseContentDAO = new NurseContentDAOImpl();
    private static NurseLevelDAO nurseLevelDAO = new NurseLevelDAOImpl();
    private static NurseLevelItemDAO nurseLevelItemDAO = new NurseLevelItemDAOImpl();
    private static CustomerNurseItemDAO customerNurseItemDAO = new CustomerNurseItemDAOImpl();
    private static UserDAO userDAO = new UserDAOImpl();
    private static RoleDAO roleDAO = new RoleDAOImpl();
    private static NurseRecordDAO nurseRecordDAO = new NurseRecordDAOImpl();

    public static void main(String[] args) {
        // 登录
        if (!login()) {
            System.out.println("登录失败，系统退出。");
            sc.close();
            return;
        }

        // 根据角色显示不同菜单
        if (currentUser.getRoleId() == 1) {
            adminMainMenu();
        } else if (currentUser.getRoleId() == 2) {
            stewardMainMenu();
        } else {
            System.out.println("未知角色，系统退出。");
        }

        System.out.println("系统已退出，再见！");
        sc.close();
    }

    // ==================== 登录 ====================
    private static boolean login() {
        System.out.println("\n" + boxTop());
        System.out.println(boxRow("  东软颐养中心管理系统"));
        System.out.println(boxBot());
        System.out.print("请输入账号：");
        String username = sc.nextLine();
        System.out.print("请输入密码：");
        String password = sc.nextLine();

        currentUser = userDAO.login(username, password);
        if (currentUser != null) {
            System.out.println("欢迎您，" + currentUser.getNickname() + "！");
            return true;
        } else {
            System.out.println("账号或密码错误！");
            return false;
        }
    }

    // ==================== 管理员主菜单 ====================
    private static void adminMainMenu() {
        while (true) {
            System.out.println("\n" + boxTop());
            System.out.println(boxRow("  东软颐养中心管理系统(管理员)"));
            System.out.println(boxMid());
            System.out.println(boxRow("  1. 客户管理"));
            System.out.println(boxRow("  2. 床位与房间管理"));
            System.out.println(boxRow("  3. 退住/外出管理"));
            System.out.println(boxRow("  4. 护理管理"));
            System.out.println(boxRow("  5. 健康管家管理"));
            System.out.println(boxRow("  6. 用户管理"));
            System.out.println(boxRow("  0. 退出系统"));
            System.out.println(boxBot());
            System.out.print("请输入功能编号：");

            int op = readInt();
            switch (op) {
                case 1: customerMenu(); break;
                case 2: bedRoomMenu(); break;
                case 3: approvalMenu(); break;
                case 4: nurseMenu(); break;
                case 5: stewardManageMenu(); break;
                case 6: userManageMenu(); break;
                case 0: return;
                default: System.out.println("输入有误，请重新选择");
            }
        }
    }

    // ==================== 健康管家主菜单 ====================
    private static void stewardMainMenu() {
        while (true) {
            System.out.println("\n" + boxTop());
            System.out.println(boxRow("  东软颐养中心管理系统(健康管家)"));
            System.out.println(boxMid());
            System.out.println(boxRow("  1. 日常护理"));
            System.out.println(boxRow("  2. 护理记录查询"));
            System.out.println(boxRow("  3. 外出申请"));
            System.out.println(boxRow("  4. 退住申请"));
            System.out.println(boxRow("  0. 退出系统"));
            System.out.println(boxBot());
            System.out.print("请输入功能编号：");

            int op = readInt();
            switch (op) {
                case 1: stewardDailyNurse(); break;
                case 2: stewardNurseRecord(); break;
                case 3: stewardOutwardApply(); break;
                case 4: stewardBackdownApply(); break;
                case 0: return;
                default: System.out.println("输入有误，请重新选择");
            }
        }
    }

    // ==================== 1. 客户管理 ====================
    private static void customerMenu() {
        while (true) {
            System.out.println("\n===== 客户管理 =====");
            System.out.println("1. 查看所有客户");
            System.out.println("2. 按姓名搜索客户");
            System.out.println("3. 新增客户(入住登记)");
            System.out.println("4. 修改客户信息");
            System.out.println("5. 删除客户");
            System.out.println("0. 返回主菜单");
            System.out.print("请输入：");

            int op = readInt();
            switch (op) {
                case 1:
                    List<Customer> customers = customerDAO.findAll();
                    if (customers.isEmpty()) {
                        System.out.println("暂无客户数据");
                    } else {
                        System.out.println("\n客户列表(" + customers.size() + "人)：");
                        for (Customer c : customers) {
                            System.out.println("  " + c);
                        }
                    }
                    break;
                case 2:
                    System.out.print("输入姓名关键词：");
                    String keyword = sc.nextLine();
                    List<Customer> result = customerDAO.findByName(keyword);
                    if (result.isEmpty()) {
                        System.out.println("未找到匹配的客户");
                    } else {
                        for (Customer c : result) {
                            System.out.println("  " + c);
                        }
                    }
                    break;
                case 3:
                    addCustomer();
                    break;
                case 4:
                    updateCustomer();
                    break;
                case 5:
                    System.out.print("输入要删除的客户ID：");
                    int delId = readInt();
                    customerDAO.delete(delId);
                    System.out.println("删除成功！");
                    break;
                case 0: return;
                default: System.out.println("输入有误");
            }
        }
    }

    private static void addCustomer() {
        System.out.println("\n--- 入住登记 ---");
        System.out.print("姓名：");
        String name = sc.nextLine();
        System.out.print("年龄：");
        int age = readInt();
        System.out.print("身份证号：");
        String idCard = sc.nextLine();
        System.out.print("联系电话：");
        String tel = sc.nextLine();

        System.out.println("空闲床位：");
        List<Bed> freeBeds = bedDAO.findByStatus(0);
        for (Bed b : freeBeds) {
            System.out.println("  " + b);
        }
        System.out.print("选择床位ID：");
        int bedId = readInt();

        Customer c = new Customer(name, age, idCard, tel, bedId);
        customerDAO.add(c);
        bedDAO.updateStatus(bedId, 1);
        System.out.println("入住登记成功！");
    }

    private static void updateCustomer() {
        System.out.print("输入要修改的客户ID：");
        int id = readInt();
        Customer c = customerDAO.findById(id);
        if (c == null) {
            System.out.println("未找到该客户");
            return;
        }
        System.out.println("当前信息：" + c);
        System.out.print("新姓名(" + c.getName() + ")：");
        String name = sc.nextLine();
        if (!name.isEmpty()) c.setName(name);
        System.out.print("新年龄(" + c.getAge() + ")：");
        String ageStr = sc.nextLine();
        if (!ageStr.isEmpty()) c.setAge(Integer.parseInt(ageStr));
        System.out.print("新电话(" + c.getContactTel() + ")：");
        String tel = sc.nextLine();
        if (!tel.isEmpty()) c.setContactTel(tel);
        System.out.print("新床位ID(" + c.getBedId() + ")：");
        String bedStr = sc.nextLine();
        if (!bedStr.isEmpty()) c.setBedId(Integer.parseInt(bedStr));

        customerDAO.update(c);
        System.out.println("修改成功！");
    }

    // ==================== 2. 床位与房间管理 ====================
    private static void bedRoomMenu() {
        while (true) {
            System.out.println("\n===== 床位与房间管理 =====");
            System.out.println("1. 查看所有床位");
            System.out.println("2. 查看空闲床位");
            System.out.println("3. 查看床位使用记录");
            System.out.println("4. 查看所有房间");
            System.out.println("5. 按楼层查询房间");
            System.out.println("6. 新增房间");
            System.out.println("7. 修改房间");
            System.out.println("8. 删除房间");
            System.out.println("0. 返回主菜单");
            System.out.print("请输入：");

            int op = readInt();
            switch (op) {
                case 1:
                    List<Bed> allBeds = bedDAO.findAll();
                    System.out.println("\n床位列表(" + allBeds.size() + "张)：");
                    for (Bed b : allBeds) {
                        System.out.println("  " + b);
                    }
                    break;
                case 2:
                    List<Bed> freeBeds = bedDAO.findByStatus(0);
                    System.out.println("\n空闲床位(" + freeBeds.size() + "张)：");
                    for (Bed b : freeBeds) {
                        System.out.println("  " + b);
                    }
                    break;
                case 3:
                    List<Beddetails> details = beddetailsDAO.findAll();
                    if (details.isEmpty()) {
                        System.out.println("暂无床位使用记录");
                    } else {
                        for (Beddetails d : details) {
                            System.out.println("  " + d);
                        }
                    }
                    break;
                case 4:
                    List<Room> rooms = roomDAO.findAll();
                    System.out.println("\n房间列表(" + rooms.size() + "间)：");
                    for (Room r : rooms) {
                        System.out.println("  " + r);
                    }
                    break;
                case 5:
                    System.out.print("输入楼层：");
                    String floor = sc.nextLine();
                    List<Room> byFloor = roomDAO.findByFloor(floor);
                    if (byFloor.isEmpty()) {
                        System.out.println("该楼层暂无房间");
                    } else {
                        for (Room r : byFloor) {
                            System.out.println("  " + r);
                        }
                    }
                    break;
                case 6:
                    System.out.print("楼层：");
                    String f = sc.nextLine();
                    System.out.print("房间号：");
                    int rNo = readInt();
                    Room newRoom = new Room();
                    newRoom.setRoomFloor(f);
                    newRoom.setRoomNo(rNo);
                    int ret = roomDAO.add(newRoom);
                    System.out.println(ret > 0 ? "新增成功" : "新增失败");
                    break;
                case 7:
                    System.out.print("输入要修改的房间号：");
                    int oldNo = readInt();
                    System.out.print("新楼层：");
                    String nf = sc.nextLine();
                    System.out.print("新房间号：");
                    int nNo = readInt();
                    Room upRoom = new Room();
                    upRoom.setRoomFloor(nf);
                    upRoom.setRoomNo(nNo);
                    roomDAO.delete(oldNo);
                    roomDAO.add(upRoom);
                    System.out.println("修改成功");
                    break;
                case 8:
                    System.out.print("输入要删除的房间号：");
                    int dNo = readInt();
                    int dr = roomDAO.delete(dNo);
                    System.out.println(dr > 0 ? "删除成功" : "删除失败");
                    break;
                case 0: return;
                default: System.out.println("输入有误");
            }
        }
    }

    // ==================== 3. 退住/外出管理 ====================
    private static void approvalMenu() {
        while (true) {
            System.out.println("\n===== 退住/外出管理 =====");
            System.out.println("1. 外出申请");
            System.out.println("2. 外出审批");
            System.out.println("3. 查看外出记录");
            System.out.println("4. 退住申请");
            System.out.println("5. 退住审批");
            System.out.println("6. 查看退住记录");
            System.out.println("0. 返回主菜单");
            System.out.print("请输入：");

            int op = readInt();
            switch (op) {
                case 1: addOutward(); break;
                case 2: auditOutward(); break;
                case 3:
                    List<Outward> outs = outwardDAO.findAll();
                    if (outs.isEmpty()) {
                        System.out.println("暂无外出记录");
                    } else {
                        for (Outward o : outs) {
                            System.out.println("  " + o);
                        }
                    }
                    break;
                case 4: addBackdown(); break;
                case 5: auditBackdown(); break;
                case 6:
                    List<Backdown> backs = backdownDAO.findAll();
                    if (backs.isEmpty()) {
                        System.out.println("暂无退住记录");
                    } else {
                        for (Backdown b : backs) {
                            System.out.println("  " + b);
                        }
                    }
                    break;
                case 0: return;
                default: System.out.println("输入有误");
            }
        }
    }

    private static void addOutward() {
        System.out.println("\n--- 外出申请 ---");
        System.out.print("床位ID：");
        int bedId = readInt();
        System.out.print("老人姓名：");
        String name = sc.nextLine();
        System.out.print("外出日期(yyyy-MM-dd)：");
        String outDate = sc.nextLine();
        System.out.print("预计回院日期(yyyy-MM-dd)：");
        String retDate = sc.nextLine();
        System.out.print("外出事由：");
        String remark = sc.nextLine();

        Outward o = new Outward();
        o.setBedId(bedId);
        o.setCustomerName(name);
        o.setOutwardDate(java.sql.Date.valueOf(outDate));
        o.setReturnDate(java.sql.Date.valueOf(retDate));
        o.setRemark(remark);

        int ret = outwardDAO.add(o);
        if (ret > 0) {
            bedDAO.updateStatus(bedId, 2);
            System.out.println("外出申请已提交，等待审批");
        } else {
            System.out.println("申请失败");
        }
    }

    private static void auditOutward() {
        System.out.println("\n--- 外出审批 ---");
        List<Outward> list = outwardDAO.findAll();
        for (Outward o : list) {
            System.out.println("  " + o);
        }
        System.out.print("输入要审批的记录ID：");
        int id = readInt();
        System.out.print("审批(1同意/2拒绝)：");
        int status = readInt();
        System.out.print("审批人ID：");
        int auditorId = readInt();

        int ret = outwardDAO.audit(id, status, auditorId);
        System.out.println(ret > 0 ? "审批完成" : "审批失败");
    }

    private static void addBackdown() {
        System.out.println("\n--- 退住申请 ---");
        System.out.print("床位ID：");
        int bedId = readInt();
        System.out.print("老人姓名：");
        String name = sc.nextLine();
        System.out.print("退住日期(yyyy-MM-dd)：");
        String dateStr = sc.nextLine();
        System.out.print("退住原因：");
        String remark = sc.nextLine();

        Backdown b = new Backdown();
        b.setBedId(bedId);
        b.setCustomerName(name);
        b.setBackdownDate(java.sql.Date.valueOf(dateStr));
        b.setRemark(remark);

        int ret = backdownDAO.add(b);
        System.out.println(ret > 0 ? "退住申请已提交，等待审批" : "申请失败");
    }

    private static void auditBackdown() {
        System.out.println("\n--- 退住审批 ---");
        List<Backdown> list = backdownDAO.findAll();
        for (Backdown b : list) {
            System.out.println("  " + b);
        }
        System.out.print("输入要审批的记录ID：");
        int id = readInt();
        System.out.print("审批(1同意/2拒绝)：");
        int status = readInt();
        System.out.print("审批人ID：");
        int auditorId = readInt();

        int ret = backdownDAO.audit(id, status, auditorId);
        if (ret > 0 && status == 1) {
            bedDAO.updateStatus(id, 0);
        }
        System.out.println(ret > 0 ? "审批完成" : "审批失败");
    }

    // ==================== 4. 护理管理 ====================
    private static void nurseMenu() {
        while (true) {
            System.out.println("\n===== 护理管理 =====");
            System.out.println("1. 护理项目管理");
            System.out.println("2. 护理级别管理");
            System.out.println("3. 客户护理项目管理");
            System.out.println("4. 查看级别-项目关联");
            System.out.println("0. 返回主菜单");
            System.out.print("请输入：");

            int op = readInt();
            switch (op) {
                case 1: nurseContentSubMenu(); break;
                case 2: nurseLevelSubMenu(); break;
                case 3: customerNurseItemSubMenu(); break;
                case 4:
                    List<NurseLevelItem> items = nurseLevelItemDAO.findAll();
                    if (items.isEmpty()) {
                        System.out.println("暂无关联数据");
                    } else {
                        for (NurseLevelItem item : items) {
                            System.out.println("  " + item);
                        }
                    }
                    break;
                case 0: return;
                default: System.out.println("输入有误");
            }
        }
    }

    private static void nurseContentSubMenu() {
        while (true) {
            System.out.println("\n--- 护理项目管理 ---");
            System.out.println("1. 查看所有");
            System.out.println("2. 新增");
            System.out.println("3. 修改");
            System.out.println("4. 删除(逻辑)");
            System.out.println("0. 返回");
            System.out.print("请输入：");

            int op = readInt();
            switch (op) {
                case 1:
                    List<NurseContent> list = nurseContentDAO.findAll();
                    if (list.isEmpty()) {
                        System.out.println("暂无数据");
                    } else {
                        for (NurseContent n : list) System.out.println("  " + n);
                    }
                    break;
                case 2:
                    NurseContent nc = new NurseContent();
                    System.out.print("项目编码："); nc.setSerialNumber(sc.nextLine());
                    System.out.print("项目名称："); nc.setNursingName(sc.nextLine());
                    System.out.print("价格："); nc.setServicePrice(sc.nextLine());
                    System.out.print("执行周期："); nc.setExecutionCycle(sc.nextLine());
                    System.out.print("状态(1启用/2停用)："); nc.setStatus(readInt());
                    int r1 = nurseContentDAO.add(nc);
                    System.out.println(r1 > 0 ? "新增成功" : "新增失败");
                    break;
                case 3:
                    System.out.print("输入要修改的项目ID：");
                    int uid = readInt();
                    NurseContent exist = nurseContentDAO.findById(uid);
                    if (exist == null) { System.out.println("未找到"); break; }
                    System.out.print("新编码(" + exist.getSerialNumber() + ")：");
                    String s = sc.nextLine(); if (!s.isEmpty()) exist.setSerialNumber(s);
                    System.out.print("新名称(" + exist.getNursingName() + ")：");
                    s = sc.nextLine(); if (!s.isEmpty()) exist.setNursingName(s);
                    System.out.print("新价格(" + exist.getServicePrice() + ")：");
                    s = sc.nextLine(); if (!s.isEmpty()) exist.setServicePrice(s);
                    System.out.print("新周期(" + exist.getExecutionCycle() + ")：");
                    s = sc.nextLine(); if (!s.isEmpty()) exist.setExecutionCycle(s);
                    System.out.print("新状态(1/2)：");
                    s = sc.nextLine(); if (!s.isEmpty()) exist.setStatus(Integer.parseInt(s));
                    int r2 = nurseContentDAO.update(exist);
                    System.out.println(r2 > 0 ? "修改成功" : "修改失败");
                    break;
                case 4:
                    System.out.print("输入要删除的项目ID：");
                    int did = readInt();
                    int r3 = nurseContentDAO.delete(did);
                    System.out.println(r3 > 0 ? "已作废(隐藏)" : "删除失败");
                    break;
                case 0: return;
                default: System.out.println("输入有误");
            }
        }
    }

    private static void nurseLevelSubMenu() {
        while (true) {
            System.out.println("\n--- 护理级别管理 ---");
            System.out.println("1. 查看所有");
            System.out.println("2. 新增");
            System.out.println("3. 修改");
            System.out.println("4. 删除(逻辑)");
            System.out.println("0. 返回");
            System.out.print("请输入：");

            int op = readInt();
            switch (op) {
                case 1:
                    List<NurseLevel> list = nurseLevelDAO.findAll();
                    if (list.isEmpty()) {
                        System.out.println("暂无数据");
                    } else {
                        for (NurseLevel n : list) System.out.println("  " + n);
                    }
                    break;
                case 2:
                    NurseLevel nl = new NurseLevel();
                    System.out.print("级别名称："); nl.setLevelName(sc.nextLine());
                    System.out.print("级别描述："); nl.setLevelDesc(sc.nextLine());
                    System.out.print("月费："); nl.setMonthlyFee(new java.math.BigDecimal(sc.nextLine()));
                    int r1 = nurseLevelDAO.add(nl);
                    System.out.println(r1 > 0 ? "新增成功" : "新增失败");
                    break;
                case 3:
                    System.out.print("输入要修改的级别ID：");
                    int uid = readInt();
                    NurseLevel exist = nurseLevelDAO.findById(uid);
                    if (exist == null) { System.out.println("未找到"); break; }
                    System.out.print("新名称(" + exist.getLevelName() + ")：");
                    String s = sc.nextLine(); if (!s.isEmpty()) exist.setLevelName(s);
                    System.out.print("新描述(" + exist.getLevelDesc() + ")：");
                    s = sc.nextLine(); if (!s.isEmpty()) exist.setLevelDesc(s);
                    System.out.print("新月费(" + exist.getMonthlyFee() + ")：");
                    s = sc.nextLine(); if (!s.isEmpty()) exist.setMonthlyFee(new java.math.BigDecimal(s));
                    int r2 = nurseLevelDAO.update(exist);
                    System.out.println(r2 > 0 ? "修改成功" : "修改失败");
                    break;
                case 4:
                    System.out.print("输入要删除的级别ID：");
                    int did = readInt();
                    int r3 = nurseLevelDAO.delete(did);
                    System.out.println(r3 > 0 ? "已作废(隐藏)" : "删除失败");
                    break;
                case 0: return;
                default: System.out.println("输入有误");
            }
        }
    }

    private static void customerNurseItemSubMenu() {
        while (true) {
            System.out.println("\n--- 客户护理项目管理 ---");
            System.out.println("1. 查看所有");
            System.out.println("2. 按客户查询");
            System.out.println("3. 新增");
            System.out.println("4. 修改");
            System.out.println("5. 作废(逻辑删除)");
            System.out.println("0. 返回");
            System.out.print("请输入：");

            int op = readInt();
            switch (op) {
                case 1:
                    List<CustomerNurseItem> list = customerNurseItemDAO.findAll();
                    if (list.isEmpty()) {
                        System.out.println("暂无数据");
                    } else {
                        for (CustomerNurseItem item : list) System.out.println("  " + item);
                    }
                    break;
                case 2:
                    System.out.print("输入客户ID：");
                    int cid = readInt();
                    List<CustomerNurseItem> byCust = customerNurseItemDAO.findByCustomerId(cid);
                    if (byCust.isEmpty()) {
                        System.out.println("该客户暂无护理项目");
                    } else {
                        for (CustomerNurseItem item : byCust) System.out.println("  " + item);
                    }
                    break;
                case 3:
                    CustomerNurseItem add = new CustomerNurseItem();
                    System.out.print("客户ID："); add.setCustomerId(readInt());
                    System.out.print("护理项目ID："); add.setItemId(readInt());
                    System.out.print("护理级别ID："); add.setLevelId(readInt());
                    System.out.print("护理次数："); add.setNurseNumber(readInt());
                    add.setBuyTime(LocalDate.now());
                    add.setMaturityTime(LocalDate.now().plusMonths(3));
                    int r1 = customerNurseItemDAO.add(add);
                    System.out.println(r1 > 0 ? "新增成功" : "新增失败");
                    break;
                case 4:
                    System.out.print("输入要修改的记录ID：");
                    int uid = readInt();
                    CustomerNurseItem exist = customerNurseItemDAO.findById(uid);
                    if (exist == null) { System.out.println("未找到"); break; }
                    System.out.print("新客户ID(" + exist.getCustomerId() + ")：");
                    String s = sc.nextLine(); if (!s.isEmpty()) exist.setCustomerId(Integer.parseInt(s));
                    System.out.print("新项目ID(" + exist.getItemId() + ")：");
                    s = sc.nextLine(); if (!s.isEmpty()) exist.setItemId(Integer.parseInt(s));
                    System.out.print("新级别ID(" + exist.getLevelId() + ")：");
                    s = sc.nextLine(); if (!s.isEmpty()) exist.setLevelId(Integer.parseInt(s));
                    System.out.print("新次数(" + exist.getNurseNumber() + ")：");
                    s = sc.nextLine(); if (!s.isEmpty()) exist.setNurseNumber(Integer.parseInt(s));
                    int r2 = customerNurseItemDAO.update(exist);
                    System.out.println(r2 > 0 ? "修改成功" : "修改失败");
                    break;
                case 5:
                    System.out.print("输入要作废的记录ID：");
                    int did = readInt();
                    int r3 = customerNurseItemDAO.delete(did);
                    System.out.println(r3 > 0 ? "已作废(隐藏)" : "失败");
                    break;
                case 0: return;
                default: System.out.println("输入有误");
            }
        }
    }

    // ==================== 5. 健康管家管理(管理员) ====================
    private static void stewardManageMenu() {
        while (true) {
            System.out.println("\n===== 健康管家管理 =====");
            System.out.println("1. 设置服务对象(分配管家)");
            System.out.println("2. 查看管家-客户关系");
            System.out.println("3. 服务关注(查看客户护理项目)");
            System.out.println("0. 返回主菜单");
            System.out.print("请输入：");

            int op = readInt();
            switch (op) {
                case 1: setServiceTarget(); break;
                case 2: viewStewardRelations(); break;
                case 3: serviceFocus(); break;
                case 0: return;
                default: System.out.println("输入有误");
            }
        }
    }

    // 设置服务对象：为管家分配客户
    private static void setServiceTarget() {
        System.out.println("\n--- 设置服务对象 ---");

        // 显示所有健康管家
        List<User> stewards = userDAO.findByRoleId(2);
        if (stewards.isEmpty()) {
            System.out.println("暂无健康管家账号，请先创建！");
            return;
        }
        System.out.println("健康管家列表：");
        for (User u : stewards) {
            System.out.println("  " + u);
        }

        System.out.print("选择管家ID：");
        int stewardId = readInt();
        User steward = userDAO.findById(stewardId);
        if (steward == null || steward.getRoleId() != 2) {
            System.out.println("无效的管家ID！");
            return;
        }

        // 显示所有客户
        List<Customer> customers = customerDAO.findAll();
        if (customers.isEmpty()) {
            System.out.println("暂无客户数据");
            return;
        }
        System.out.println("\n客户列表：");
        for (Customer c : customers) {
            System.out.println("  " + c);
        }

        System.out.print("选择要分配的客户ID：");
        int customerId = readInt();

        // 一个客户只能有一个管家
        int ret = customerDAO.updateSteward(customerId, stewardId);
        System.out.println(ret > 0 ? "分配成功！管家 " + steward.getNickname() + " 现在负责客户ID:" + customerId : "分配失败");
    }

    // 查看管家-客户关系
    private static void viewStewardRelations() {
        System.out.println("\n--- 管家-客户关系 ---");
        List<User> stewards = userDAO.findByRoleId(2);
        if (stewards.isEmpty()) {
            System.out.println("暂无健康管家");
            return;
        }
        for (User u : stewards) {
            System.out.println("\n管家：" + u.getNickname() + "(ID:" + u.getId() + ")");
            List<Customer> myCustomers = customerDAO.findByUserId(u.getId());
            if (myCustomers.isEmpty()) {
                System.out.println("  暂无服务对象");
            } else {
                for (Customer c : myCustomers) {
                    System.out.println("  → " + c.getName() + "(ID:" + c.getId() + ")");
                }
            }
        }
    }

    // 服务关注：查看客户已购护理项目
    private static void serviceFocus() {
        System.out.println("\n--- 服务关注 ---");
        System.out.print("输入客户ID：");
        int customerId = readInt();
        Customer c = customerDAO.findById(customerId);
        if (c == null) {
            System.out.println("客户不存在");
            return;
        }
        System.out.println("客户：" + c.getName() + " | 管家ID：" + (c.getUserId() != null ? c.getUserId() : "无"));

        List<CustomerNurseItem> items = customerNurseItemDAO.findByCustomerId(customerId);
        if (items.isEmpty()) {
            System.out.println("该客户暂无已购护理项目");
        } else {
            System.out.println("已购护理项目：");
            for (CustomerNurseItem item : items) {
                NurseContent nc = nurseContentDAO.findById(item.getItemId());
                String itemName = nc != null ? nc.getNursingName() : "未知项目";
                System.out.println("  记录ID:" + item.getId() + " | 项目:" + itemName
                        + " | 数量:" + item.getNurseNumber()
                        + " | 购买日期:" + item.getBuyTime()
                        + " | 到期日:" + item.getMaturityTime());
            }
        }

        System.out.println("\n1. 续费项目 | 2. 购买新项目 | 3. 移除此客户项目 | 0. 返回");
        System.out.print("请选择：");
        int op = readInt();
        switch (op) {
            case 1:
                System.out.print("输入要续费的记录ID：");
                int renewId = readInt();
                CustomerNurseItem exist = customerNurseItemDAO.findById(renewId);
                if (exist == null) { System.out.println("记录不存在"); break; }
                System.out.print("续费月数(默认3个月)：");
                String monthsStr = sc.nextLine();
                int months = monthsStr.isEmpty() ? 3 : Integer.parseInt(monthsStr);
                exist.setMaturityTime(exist.getMaturityTime().plusMonths(months));
                System.out.print("追加数量：");
                String countStr = sc.nextLine();
                if (!countStr.isEmpty()) {
                    exist.setNurseNumber(exist.getNurseNumber() + Integer.parseInt(countStr));
                }
                int r1 = customerNurseItemDAO.update(exist);
                System.out.println(r1 > 0 ? "续费成功" : "续费失败");
                break;
            case 2:
                // 显示可购买的护理项目
                List<NurseContent> contents = nurseContentDAO.findAll();
                System.out.println("可选护理项目：");
                for (NurseContent nc : contents) {
                    System.out.println("  " + nc);
                }
                CustomerNurseItem newItem = new CustomerNurseItem();
                newItem.setCustomerId(customerId);
                System.out.print("选择项目ID："); newItem.setItemId(readInt());
                System.out.print("护理级别ID："); newItem.setLevelId(readInt());
                System.out.print("购买数量(默认1)：");
                String numStr = sc.nextLine();
                newItem.setNurseNumber(numStr.isEmpty() ? 1 : Integer.parseInt(numStr));
                newItem.setBuyTime(LocalDate.now());
                newItem.setMaturityTime(LocalDate.now().plusMonths(3));
                int r2 = customerNurseItemDAO.add(newItem);
                System.out.println(r2 > 0 ? "购买成功" : "购买失败");
                break;
            case 3:
                System.out.print("输入要移除的记录ID：");
                int delId = readInt();
                int r3 = customerNurseItemDAO.delete(delId);
                System.out.println(r3 > 0 ? "已移除" : "移除失败");
                break;
            case 0: break;
            default: System.out.println("输入有误");
        }
    }

    // ==================== 6. 用户管理(管理员) ====================
    private static void userManageMenu() {
        while (true) {
            System.out.println("\n===== 用户管理 =====");
            System.out.println("1. 查看所有用户");
            System.out.println("2. 按角色查看用户");
            System.out.println("3. 新增用户");
            System.out.println("4. 修改用户");
            System.out.println("5. 删除用户(逻辑)");
            System.out.println("6. 角色列表");
            System.out.println("0. 返回主菜单");
            System.out.print("请输入：");

            int op = readInt();
            switch (op) {
                case 1:
                    List<User> users = userDAO.findAll();
                    if (users.isEmpty()) {
                        System.out.println("暂无用户");
                    } else {
                        System.out.println("\n用户列表(" + users.size() + "人)：");
                        for (User u : users) System.out.println("  " + u);
                    }
                    break;
                case 2:
                    System.out.print("角色(1管理员/2健康管家)：");
                    int roleId = readInt();
                    List<User> byRole = userDAO.findByRoleId(roleId);
                    if (byRole.isEmpty()) {
                        System.out.println("该角色暂无用户");
                    } else {
                        for (User u : byRole) System.out.println("  " + u);
                    }
                    break;
                case 3:
                    addUser();
                    break;
                case 4:
                    updateUser();
                    break;
                case 5:
                    System.out.print("输入要删除的用户ID：");
                    int delId = readInt();
                    int r = userDAO.delete(delId);
                    System.out.println(r > 0 ? "已作废(隐藏)" : "删除失败");
                    break;
                case 6:
                    List<Role> roles = roleDAO.findAll();
                    if (roles.isEmpty()) {
                        System.out.println("暂无角色数据");
                    } else {
                        for (Role role : roles) System.out.println("  " + role);
                    }
                    break;
                case 0: return;
                default: System.out.println("输入有误");
            }
        }
    }

    private static void addUser() {
        System.out.println("\n--- 新增用户 ---");
        User u = new User();
        System.out.print("系统账号："); u.setUsername(sc.nextLine());
        System.out.print("密码："); u.setPassword(sc.nextLine());
        System.out.print("真实姓名："); u.setNickname(sc.nextLine());
        System.out.print("性别(0女/1男)："); u.setSex(readInt());
        System.out.print("电话："); u.setPhoneNumber(sc.nextLine());
        System.out.print("邮箱："); u.setEmail(sc.nextLine());
        System.out.print("角色(1管理员/2健康管家)："); u.setRoleId(readInt());
        u.setCreateBy(currentUser.getId());
        int ret = userDAO.add(u);
        System.out.println(ret > 0 ? "新增成功" : "新增失败");
    }

    private static void updateUser() {
        System.out.print("输入要修改的用户ID：");
        int id = readInt();
        User u = userDAO.findById(id);
        if (u == null) { System.out.println("未找到该用户"); return; }
        System.out.println("当前信息：" + u);
        System.out.print("新账号(" + u.getUsername() + ")：");
        String s = sc.nextLine(); if (!s.isEmpty()) u.setUsername(s);
        System.out.print("新密码(" + u.getPassword() + ")：");
        s = sc.nextLine(); if (!s.isEmpty()) u.setPassword(s);
        System.out.print("新姓名(" + u.getNickname() + ")：");
        s = sc.nextLine(); if (!s.isEmpty()) u.setNickname(s);
        System.out.print("新性别(0/1)(" + u.getSex() + ")：");
        s = sc.nextLine(); if (!s.isEmpty()) u.setSex(Integer.parseInt(s));
        System.out.print("新电话(" + u.getPhoneNumber() + ")：");
        s = sc.nextLine(); if (!s.isEmpty()) u.setPhoneNumber(s);
        System.out.print("新邮箱(" + u.getEmail() + ")：");
        s = sc.nextLine(); if (!s.isEmpty()) u.setEmail(s);
        System.out.print("新角色(1/2)(" + u.getRoleId() + ")：");
        s = sc.nextLine(); if (!s.isEmpty()) u.setRoleId(Integer.parseInt(s));
        u.setUpdateBy(currentUser.getId());
        int ret = userDAO.update(u);
        System.out.println(ret > 0 ? "修改成功" : "修改失败");
    }

    // ==================== 健康管家功能 ====================

    // 1. 日常护理
    private static void stewardDailyNurse() {
        System.out.println("\n--- 日常护理 ---");

        // 查询当前管家的服务客户
        List<Customer> myCustomers = customerDAO.findByUserId(currentUser.getId());
        if (myCustomers.isEmpty()) {
            System.out.println("您当前没有服务对象，请联系管理员分配！");
            return;
        }
        System.out.println("您的服务对象：");
        for (int i = 0; i < myCustomers.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + myCustomers.get(i));
        }

        System.out.print("选择客户序号(0返回)：");
        int idx = readInt();
        if (idx == 0) return;
        if (idx < 1 || idx > myCustomers.size()) {
            System.out.println("无效序号");
            return;
        }
        Customer selectedCustomer = myCustomers.get(idx - 1);

        // 显示客户已购护理项目
        List<CustomerNurseItem> items = customerNurseItemDAO.findByCustomerId(selectedCustomer.getId());
        if (items.isEmpty()) {
            System.out.println("该客户暂无已购护理项目，无法进行护理");
            return;
        }
        System.out.println("\n该客户已购护理项目：");
        for (int i = 0; i < items.size(); i++) {
            CustomerNurseItem item = items.get(i);
            NurseContent nc = nurseContentDAO.findById(item.getItemId());
            String itemName = nc != null ? nc.getNursingName() : "未知项目";
            System.out.println("  " + (i + 1) + ". " + itemName + " | 剩余次数:" + item.getNurseNumber()
                    + " | 到期:" + item.getMaturityTime());
        }

        System.out.print("选择护理项目序号(0返回)：");
        int itemIdx = readInt();
        if (itemIdx == 0) return;
        if (itemIdx < 1 || itemIdx > items.size()) {
            System.out.println("无效序号");
            return;
        }
        CustomerNurseItem selectedItem = items.get(itemIdx - 1);

        if (selectedItem.getNurseNumber() <= 0) {
            System.out.println("该项目护理次数已用完，请续费！");
            return;
        }

        System.out.print("本次护理数量(剩余" + selectedItem.getNurseNumber() + "次)：");
        int count = readInt();
        if (count <= 0 || count > selectedItem.getNurseNumber()) {
            System.out.println("无效数量");
            return;
        }

        // 生成护理记录
        NurseContent nc = nurseContentDAO.findById(selectedItem.getItemId());
        NurseRecord record = new NurseRecord();
        record.setCustomerId(selectedCustomer.getId());
        record.setItemId(selectedItem.getItemId());
        record.setNursingContent(nc != null ? nc.getNursingName() : "");
        record.setNursingCount(count);
        record.setUserId(currentUser.getId());
        int ret = nurseRecordDAO.add(record);

        // 更新护理项目剩余次数
        selectedItem.setNurseNumber(selectedItem.getNurseNumber() - count);
        customerNurseItemDAO.update(selectedItem);

        System.out.println(ret > 0 ? "护理记录已生成，剩余次数:" + selectedItem.getNurseNumber() : "操作失败");
    }

    // 2. 护理记录查询
    private static void stewardNurseRecord() {
        while (true) {
            System.out.println("\n--- 护理记录查询 ---");
            System.out.println("1. 查看所有我的护理记录");
            System.out.println("2. 按客户查看");
            System.out.println("3. 移除护理记录");
            System.out.println("0. 返回");
            System.out.print("请输入：");

            int op = readInt();
            switch (op) {
                case 1:
                    List<NurseRecord> all = nurseRecordDAO.findByUserId(currentUser.getId());
                    if (all.isEmpty()) {
                        System.out.println("暂无护理记录");
                    } else {
                        for (NurseRecord r : all) {
                            Customer c = customerDAO.findById(r.getCustomerId());
                            String custName = c != null ? c.getName() : "未知";
                            System.out.println("  " + r + " | 客户:" + custName);
                        }
                    }
                    break;
                case 2:
                    List<Customer> myCustomers = customerDAO.findByUserId(currentUser.getId());
                    if (myCustomers.isEmpty()) {
                        System.out.println("您没有服务对象");
                        break;
                    }
                    System.out.println("您的服务对象：");
                    for (Customer c : myCustomers) {
                        System.out.println("  " + c);
                    }
                    System.out.print("输入客户ID：");
                    int cid = readInt();
                    List<NurseRecord> byCust = nurseRecordDAO.findByCustomerId(cid);
                    if (byCust.isEmpty()) {
                        System.out.println("该客户暂无护理记录");
                    } else {
                        for (NurseRecord r : byCust) System.out.println("  " + r);
                    }
                    break;
                case 3:
                    System.out.print("输入要移除的护理记录ID：");
                    int delId = readInt();
                    int ret = nurseRecordDAO.delete(delId);
                    System.out.println(ret > 0 ? "已移除" : "移除失败");
                    break;
                case 0: return;
                default: System.out.println("输入有误");
            }
        }
    }

    // 3. 外出申请(健康管家为客户提交)
    private static void stewardOutwardApply() {
        System.out.println("\n--- 外出申请 ---");
        List<Customer> myCustomers = customerDAO.findByUserId(currentUser.getId());
        if (myCustomers.isEmpty()) {
            System.out.println("您没有服务对象");
            return;
        }
        System.out.println("您的服务对象：");
        for (Customer c : myCustomers) {
            System.out.println("  " + c);
        }

        System.out.print("选择客户ID：");
        int customerId = readInt();

        System.out.print("外出日期(yyyy-MM-dd)：");
        String outDate = sc.nextLine();
        System.out.print("预计回院日期(yyyy-MM-dd)：");
        String retDate = sc.nextLine();
        System.out.print("外出事由：");
        String reason = sc.nextLine();

        Customer c = customerDAO.findById(customerId);
        if (c == null) {
            System.out.println("客户不存在");
            return;
        }

        Outward o = new Outward();
        o.setBedId(c.getBedId());
        o.setCustomerName(c.getName());
        o.setOutwardDate(java.sql.Date.valueOf(outDate));
        o.setReturnDate(java.sql.Date.valueOf(retDate));
        o.setRemark(reason);

        int ret = outwardDAO.add(o);
        if (ret > 0) {
            bedDAO.updateStatus(c.getBedId(), 2);
            System.out.println("外出申请已提交，等待审批");
        } else {
            System.out.println("申请失败");
        }
    }

    // 4. 退住申请(健康管家为客户提交)
    private static void stewardBackdownApply() {
        System.out.println("\n--- 退住申请 ---");
        List<Customer> myCustomers = customerDAO.findByUserId(currentUser.getId());
        if (myCustomers.isEmpty()) {
            System.out.println("您没有服务对象");
            return;
        }
        System.out.println("您的服务对象：");
        for (Customer c : myCustomers) {
            System.out.println("  " + c);
        }

        System.out.print("选择客户ID：");
        int customerId = readInt();

        System.out.print("退住日期(yyyy-MM-dd)：");
        String dateStr = sc.nextLine();
        System.out.print("退住原因：");
        String reason = sc.nextLine();

        Customer c = customerDAO.findById(customerId);
        if (c == null) {
            System.out.println("客户不存在");
            return;
        }

        Backdown b = new Backdown();
        b.setBedId(c.getBedId());
        b.setCustomerName(c.getName());
        b.setBackdownDate(java.sql.Date.valueOf(dateStr));
        b.setRemark(reason);

        int ret = backdownDAO.add(b);
        System.out.println(ret > 0 ? "退住申请已提交，等待审批" : "申请失败");
    }

    // ==================== 工具方法 ====================
    private static int readInt() {
        int val = sc.nextInt();
        sc.nextLine();
        return val;
    }

    // 计算字符串在终端中的显示宽度（中文=2，ASCII=1）
    private static int visualWidth(String s) {
        int w = 0;
        for (char c : s.toCharArray()) {
            if ((c >= 0x4E00 && c <= 0x9FFF)
                    || (c >= 0x3000 && c <= 0x303F)
                    || (c >= 0xFF00 && c <= 0xFFEF)) {
                w += 2;
            } else {
                w += 1;
            }
        }
        return w;
    }

    // 框线宽度（内部宽度，不含左右边框）
    private static final int BOX_W = 34;

    // 使用 ASCII 字符画框，避免字体对 Unicode 框线字符宽度不一致
    private static String boxTop() {
        return "+" + "-".repeat(BOX_W) + "+";
    }

    private static String boxMid() {
        return "+" + "-".repeat(BOX_W) + "+";
    }

    private static String boxBot() {
        return "+" + "-".repeat(BOX_W) + "+";
    }

    private static String boxRow(String text) {
        int pad = BOX_W - visualWidth(text);
        return "|" + text + " ".repeat(Math.max(0, pad)) + "|";
    }
}

