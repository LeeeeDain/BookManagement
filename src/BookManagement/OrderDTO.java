package BookManagement;

public class OrderDTO {
   private int orderid;
   private String custid;
   private String bookid;
   private String saleprice;
   private String orderdate;
  
   public int getOrderId() {
       return orderid;
   }
   public void setOrderId(int orderid) {
       this.orderid = orderid;
   }
   public String getCustId() {
       return custid;
   }
   public void setCustId(String custid) {
       this.custid = custid;
   }   
   public String getBookId() {
       return bookid;
   }
   public void setBookId(String bookid) {
       this.bookid = bookid;
   }   
   public String getSalePrice() {
       return saleprice;
   }
   public void setSalePrice(String saleprice) {
       this.saleprice = saleprice;
   }   
   public String getOrderDate() {
       return orderdate;
   }
   public void setOrderDate(String orderdate) {
       this.orderdate = orderdate;
   }
   
  
   //DTO 객체 확인
   //이클립스팁 : toString() 자동생성: 우클릭 -> source -> Generate toString->[OK]
   @Override
   public String toString() {
       return "OrderDTO [orderid=" + Integer.toString(orderid) + ", custid=" + custid + ", bookid=" + bookid
               + ", saleprice=" + saleprice + ", orderdate=" + orderdate + "]";
   }
}