package vn.bluit.tvv.contest.model;

import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import vn.bluit.tvv.contest.controller.DbConnectController;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by tvviem on 02/04/2017.
 * Định nghĩa các phương thức cho phép thao tác với cơ sở dữ liệu
 * Các thao tác có thể là:
 *      + Thêm/sửa/xóa mẫu tin vào bảng
 *      + Tìm kiếm (nên thực hiện đa luồng)
 *      + Lấy về tất cả mẫu tin (nên thực hiện đa luồng)
 * @author tvviem
 * @version 0.1
 */
public class UtilityInteractDb {
    /**
     * Phương thức cho phép lấy các tên cơ sở dữ liệu khi biết máy chủ database và người dùng
     *
     * @param server (tên hoặc địa chỉ IP máy chủ)
     * @param port cổng phục vụ của server
     * @param username người dùng cơ sở dữ liệu
     * @param pass mật khẩu
     * @return trả về danh sách tên dữ liệu
     * */
    public static ArrayList<String> getListDatabase(String server, int port, String username, String pass) {
        ArrayList<String> result = null;
        ResultSet rs;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbUrlNotDbName = "jdbc:mysql://"+server+":"+port;
            Connection connection = DriverManager.getConnection(dbUrlNotDbName, username, pass);
            result = new ArrayList<>();
            rs = connection.getMetaData().getCatalogs();
            while (rs.next()) {
                //System.out.println("TABLE_CAT = " + rs.getString("TABLE_CAT") );
                result.add(rs.getString("TABLE_CAT"));
            }
            //System.out.println("Trong phuong thuc getList");
        } catch (ClassNotFoundException e) {
            String title = "Error Info Connect";
            String message = e.getMessage();
            NotificationType notification = NotificationType.WARNING;

            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndDismiss(Duration.seconds(5));
        } catch (SQLException e) {
            //e.printStackTrace();
            String title = "Error Info Connect";
            String message = e.getMessage();
            NotificationType notification = NotificationType.WARNING;

            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndDismiss(Duration.seconds(5));
        }
        return result;
    }

    /**
     * Sinh viên hoàn thiện các phương thức tĩnh khác
     * */
    // THUC THI Insert-Update-Delete VOI SQL thuan
    static int exeIUD(String sql) throws SQLException  {
        Connection conn = null;
        Statement statement = null;
        int recordCounter = 0; // tra ve so mau tin duoc them
        try {
            conn = DbConnectController.conPool.getConnectionFromPool();
            statement = conn.createStatement();
            recordCounter = statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            // Tra lai connection
            DbConnectController.conPool.returnConnectionToPool(conn);
        }
        return recordCounter;
    }
}
