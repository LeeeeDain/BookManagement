package BookManagement;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

//DB 처리
public class MadangDAO {
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  private static final String URL = "jdbc:mysql://localhost:3306/madang?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; 
  private static final String USER = "root"; //DB ID
  private static final String PASS = "0000"; //DB 패스워드
  MainView mList;
 
  public MadangDAO() {
	  
  }
 
 
  /**DB연결 메소드*/
  public Connection getConn(){
      Connection con = null;
      try {
          Class.forName(DRIVER); //1. 드라이버 로딩
          con = DriverManager.getConnection(URL,USER,PASS); //2. 드라이버 연결
         
      } catch (Exception e) {
          e.printStackTrace();
      }  
      return con;
  }
 
 
  /**고객리스트 출력*/
  public Vector getCustomerList(){
      Vector data = new Vector();
      Connection con = null;       
      PreparedStatement ps = null; 
      ResultSet rs = null;         
     
      try{
          con = getConn();
          String sql = "select * from Customer";
          ps = con.prepareStatement(sql);
          rs = ps.executeQuery();
         
          while(rs.next()){
              String custid = rs.getString("custid");
              String name = rs.getString("name");
              String address = rs.getString("address");
              String phone = rs.getString("phone");
              
              Vector row = new Vector();
              row.add(custid);
              row.add(name);
              row.add(address);
              row.add(phone);
             
              data.add(row);             
          }
      }catch(Exception e){
          e.printStackTrace();
      }
      return data;
  }
 
  
  /**책리스트 출력*/
  public Vector getBookList(){
      Vector data = new Vector();  
      Connection con = null;       
      PreparedStatement ps = null;
      ResultSet rs = null;         
     
      try{
          con = getConn();
          String sql = "select * from Book";
          ps = con.prepareStatement(sql);
          rs = ps.executeQuery();
         
          while(rs.next()){
              String bookid = rs.getString("bookid");
              String bookname = rs.getString("bookname");
              String publisher = rs.getString("publisher");
              String price = rs.getString("price");
              
              Vector row = new Vector();
              row.add(bookid);
              row.add(bookname);
              row.add(publisher);
              row.add(price);
             
              data.add(row);             
          }//while
      }catch(Exception e){
          e.printStackTrace();
      }
      return data;
  }//getMemberList()
  
  
  /**주문리스트 출력*/
  public Vector getOrderList(){
      Vector data = new Vector();  
      Connection con = null;      
      PreparedStatement ps = null; 
      ResultSet rs = null;        
     
      try{
          con = getConn();
          String sql = "select * from Orders";
          ps = con.prepareStatement(sql);
          rs = ps.executeQuery();
         
          while(rs.next()){
              String orderid = rs.getString("orderid");
              String custid = rs.getString("custid");
              String bookid = rs.getString("bookid");
              String saleprice = rs.getString("saleprice");
              String orderdate = rs.getString("orderdate");
              
              Vector row = new Vector();
              row.add(orderid);
              row.add(custid);
              row.add(bookid);
              row.add(saleprice);
              row.add(orderdate);
             
              data.add(row);             
          }//while
      }catch(Exception e){
          e.printStackTrace();
      }
      return data;
  }//getMemberList()
  

  /**주문리스트 초기화*/
  public Vector resetOrderList(){
      Vector data = new Vector();    
      Connection con = null;      
      PreparedStatement ps = null; 
      ResultSet rs = null;         
     
      try{
          con = getConn();
          String sql = "TRUNCATE orders";
          ps = con.prepareStatement(sql);
          ps.executeUpdate();         
          sql ="INSERT INTO Orders VALUES (1, 1, 1, 6000, STR_TO_DATE('2014-07-01','%Y-%m-%d'))," +
          		  "(2, 1, 3, 21000, STR_TO_DATE('2014-07-03','%Y-%m-%d'))," +
            		"(3, 2, 5, 8000, STR_TO_DATE('2014-07-03','%Y-%m-%d')),"+ 
            		"(4, 3, 6, 6000, STR_TO_DATE('2014-07-04','%Y-%m-%d'))," + 
            		"(5, 4, 7, 20000, STR_TO_DATE('2014-07-05','%Y-%m-%d'))," + 
            		"(6, 1, 2, 12000, STR_TO_DATE('2014-07-07','%Y-%m-%d'))," + 
            		"(7, 4, 8, 13000, STR_TO_DATE( '2014-07-07','%Y-%m-%d'))," + 
            		"(8, 3, 10, 12000, STR_TO_DATE('2014-07-08','%Y-%m-%d'))," + 
            		"(9, 2, 10, 7000, STR_TO_DATE('2014-07-09','%Y-%m-%d'))," + 
            		"(10, 3, 8, 13000, STR_TO_DATE('2014-07-10','%Y-%m-%d'))," + 
            		"(11, 4, 11, 16200, STR_TO_DATE('2014-07-09','%Y-%m-%d'))," + 
            		"(12, 7, 12, 10800, STR_TO_DATE('2014-07-09','%Y-%m-%d'))," + 
            		"(13, 6, 13, 11700, STR_TO_DATE('2014-07-09','%Y-%m-%d'))," + 
            		"(14, 9, 14, 16020, STR_TO_DATE('2014-07-09','%Y-%m-%d'))," + 
            		"(15, 10, 15, 10800, STR_TO_DATE('2014-07-09','%Y-%m-%d'))," + 
            		"(16, 3, 16, 14400, STR_TO_DATE('2014-07-09','%Y-%m-%d'))," + 
            		"(17, 5, 17, 13500, STR_TO_DATE('2014-07-09','%Y-%m-%d'))," + 
            		"(18, 6, 18, 13500, STR_TO_DATE('2014-07-09','%Y-%m-%d'))," + 
            		"(19, 2, 19, 11700, STR_TO_DATE('2014-07-09','%Y-%m-%d'))," + 
            		"(20, 8, 20, 7000, STR_TO_DATE('2014-07-09','%Y-%m-%d'))";
          ps = con.prepareStatement(sql);
          ps.executeUpdate();
      }catch(Exception e){
          e.printStackTrace();
      }
      return data;
  }
  
  
  /**고객번호 조회*/
  public boolean isCustomer(String id){
	  Vector data = new Vector();
	  Connection con = null; 
      PreparedStatement ps = null; 
      ResultSet rs = null;
      boolean ok = false;
     
      try{
          con = getConn();
          String sql = "select * from customer where custid='"+id+"';";
          ps = con.prepareStatement(sql);
          rs = ps.executeQuery();
         
          if(rs.next()){
        	ok = true;  
          }
      }catch(Exception e){
          e.printStackTrace();
      }
      return ok;
  }
  
  
  /**책 번호 조회*/
  public boolean isBook(String id){
	  Connection con = null; 
      PreparedStatement ps = null; 
      ResultSet rs = null;
      boolean ok = false;
     
      try{
          con = getConn();
          String sql = "select * from book where bookid='"+id+"';";
          ps = con.prepareStatement(sql);
          rs = ps.executeQuery();
         
          if(rs.next()){
        	ok = true;  
          }
      }catch(Exception e){
          e.printStackTrace();
      }
      return ok;
  }
  
  /**책 가격 반환*/
  public String getBookPrice(String id){
	  String price = null;
	  Connection con = null; 
      PreparedStatement ps = null; 
      ResultSet rs = null;
      
      try{
          con = getConn();
          String sql = "select * from book where bookid='"+id+"';";
          ps = con.prepareStatement(sql);
          rs = ps.executeQuery();
         
          while(rs.next()){
        	  price = rs.getString("price");
          }//while
      }catch(Exception e){
          e.printStackTrace();
      }
      return price;
  }
  
  
  /**새로운 주문번호 반환*/
  public int getNewOrderId(){
	  int newid = 0;
	  Connection con = null; 
      PreparedStatement ps = null; 
      ResultSet rs = null;
      
      try{
          con = getConn();
          String sql = "select MAX(orderid) from orders;";
          ps = con.prepareStatement(sql);
          rs = ps.executeQuery();
         
          while(rs.next()){
        	  newid = Integer.parseInt(rs.getString(1)) + 1;
          }//while
      }catch(Exception e){
          e.printStackTrace();
      }
      return newid;
  }
  
  
  
  /**주문 등록*/
  public boolean insertMember(OrderDTO dto){
      boolean ok = false;
      Connection con = null;       //연결
      PreparedStatement ps = null; //명령
     
      try{
          con = getConn();
          String sql = "insert into orders(" +
                      "orderid,custid,bookid,saleprice,orderdate) "+
                      "values(?,?,?,?,?)";
         
          ps = con.prepareStatement(sql);
          ps.setString(1, Integer.toString(dto.getOrderId()));
          ps.setString(2, dto.getCustId());
          ps.setString(3, dto.getBookId());
          ps.setString(4, dto.getSalePrice());
          ps.setString(5, dto.getOrderDate());
          
          int r = ps.executeUpdate();

          if(r>0){
              System.out.println("주문 성공");   
              ok=true;
          }else{
              System.out.println("주문 실패");
          }
      }catch(Exception e){
          e.printStackTrace();
      }
     
      return ok;
  }
}
