package BookManagement;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

//DB 처리
public class MadangDAO {
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  private static final String URL = "jdbc:mysql://localhost:3306/madang?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; 
  private static final String USER = "madang"; //DB ID
  private static final String PASS = "madang"; //DB 패스워드
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
          String sql = "delete from orders";
          ps = con.prepareStatement(sql);
          ps.executeUpdate();
          sql = "delete from book";
          ps = con.prepareStatement(sql);
          ps.executeUpdate();
          sql = "delete from customer";
          ps = con.prepareStatement(sql);
          ps.executeUpdate();
     
          sql ="INSERT INTO Book VALUES(1, '축구의 역사', '굿스포츠', 7000),"
            		+ "(2, '축구아는 여자', '나무수', 13000),"
            		+ "(3, '축구의 이해', '대한미디어', 22000),"
            		+ "(4, '골프 바이블', '대한미디어', 35000),"
            		+ "(5, '피겨 교본', '굿스포츠', 8000),"
            		+ "(6, '역도 단계별기술', '굿스포츠', 6000),"
            		+ "(7, '야구의 추억', '이상미디어', 20000),"
            		+ "(8, '야구를 부탁해', '이상미디어', 13000),"
            		+ "(9, '올림픽 이야기', '삼성당', 7500),"
            		+ "(10, 'Olympic Champions', 'Pearson', 13000),"
            		+ "(11, '이방인 페스트', '민음사', 16200),"
            		+ "(12, '데미안', '더스토리', 10800),"
            		+ "(13, '페스트', '민음사', 11700),"
            		+ "(14, '녹나무의 파수꾼', '소미미디어', 16020),"
            		+ "(15, '아몬드', '창비', 10800),"
            		+ "(16, '어둠의 눈', '다산책방', 14400),"
            		+ "(17, '세상의 주인', '메이븐', 13500),"
            		+ "(18, '마음을 읽는 아이 오로르', '밝은세상', 13500),"
            		+ "(19, '사서함 110호의 우편물', '시공사', 11700),"
            		+ "(20, '김의 나라', '피람북', 13500);";
          ps = con.prepareStatement(sql);
          ps.executeUpdate();
          sql ="INSERT INTO Customer VALUES (1, '박지성', '영국 맨체스타', '000-5000-0001'),"
            		+ "(2, '김연아', '대한민국 서울', '000-6000-0001'),"
            		+ "(3, '장미란', '대한민국 강원도', '000-7000-0001'),"
            		+ "(4, '추신수', '미국 클리블랜드', '000-8000-0001'),"
            		+ "(5, '박세리', '대한민국 대전',  NULL),"
            		+ "(6, '이다인', '호주 시드니', '000-4000-0001'),"
            		+ "(7, '이아인', '대한민국 강원도', '000-9000-0001'),"
            		+ "(8, '박미경', '일본 도쿄', '000-1000-0001'),"
            		+ "(9, '이기헌', '중국 왕푸징', '000-2000-0001'),"
            		+ "(10, '오뿌똥', '영국 런던', '000-3000-0001');";
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
          ps.setString(1, dto.getOrderId());
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
      }
     
      return ok;
  }
}
