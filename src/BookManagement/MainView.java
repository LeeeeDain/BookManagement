package BookManagement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class MainView extends JFrame implements MouseListener,ActionListener{
   Vector v;  
   Vector cols;
   DefaultTableModel model;
   JTable jTable;
   JScrollPane pane;
   JPanel pbtn;
   JButton btnInsert;
   JButton btnReset;
   JButton btnSearch1;
   JButton btnSearch2;
   JButton btnSearch3;
      
  
   public MainView(){
       super("16011020 이다인");
       MadangDAO dao = new MadangDAO();
       v = dao.getBookList();
       cols = getBookColumn();
      
       model = new DefaultTableModel(v, cols);

       jTable = new JTable(model);
       pane = new JScrollPane(jTable);
       add(pane);
      
       pbtn = new JPanel();
       btnInsert = new JButton("입력1");
       pbtn.add(btnInsert);
       btnReset = new JButton("초기화");
       pbtn.add(btnReset);
       btnSearch1 = new JButton("검색1");
       pbtn.add(btnSearch1);
       btnSearch2 = new JButton("검색2");
       pbtn.add(btnSearch2);
       btnSearch3 = new JButton("검색3");
       pbtn.add(btnSearch3);
       add(pbtn,BorderLayout.NORTH);  
      
      
       jTable.addMouseListener(this); //리스너 등록
       btnInsert.addActionListener(this); //입력1버튼 리스너 등록
       btnReset.addActionListener(this); //초기화버튼 리스너 등록
       btnSearch1.addActionListener(this); //검색1버튼 리스너 등록
       btnSearch2.addActionListener(this); //검색2버튼 리스너 등록
       btnSearch3.addActionListener(this); //검색3버튼 리스너 등록
       
       Dimension frameSize = this.getSize(); // 프레임 사이즈
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈
       this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2 - 300); // 화면 중앙
       
       setSize(600,500);
       setVisible(true);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }//end 생성자
  
  
   //JTable의 컬럼
   public Vector getOrderColumn(){
       Vector col = new Vector();
       col.add("주문번호");
       col.add("고객번호");
       col.add("책번호");
       col.add("세일가격");
       col.add("주문날짜");
       return col;
   }
  
   //JTable의 컬럼
   public Vector getCustomerColumn(){
       Vector col = new Vector();
       col.add("회원번호");
       col.add("이름");
       col.add("주소");
       col.add("전화번호");
       return col;
   }
  
   //JTable의 컬럼
   public Vector getBookColumn(){
       Vector col = new Vector();
       col.add("책번호");
       col.add("제목");
       col.add("출판사");
       col.add("가격");
       return col;
   }
  
  
   //Jtable 내용 갱신 메서드
   public void jTableRefresh(Vector select_list, Vector select_column){
       DefaultTableModel model= new DefaultTableModel(select_list, select_column);
       jTable.setModel(model);
   }
  
   public static void main(String[] args) {
       new MainView();
   }//main
   
   
   @Override
   public void mouseClicked(MouseEvent e) {
   }
   @Override
   public void mouseEntered(MouseEvent e) {
       // TODO Auto-generated method stub
   }
   @Override
   public void mouseExited(MouseEvent e) {
       // TODO Auto-generated method stub
   }
   @Override
   public void mousePressed(MouseEvent e) {
       // TODO Auto-generated method stub 
   }
   @Override
   public void mouseReleased(MouseEvent e) {
       // TODO Auto-generated method stub
   }
   @Override
   public void actionPerformed(ActionEvent e) {
       //입력1버튼을 클릭하면
       if(e.getSource() == btnInsert ){
           new OrderView();
       }
       //검색1버튼을 클릭하면
       if(e.getSource() == btnSearch1 ) {
    	   MadangDAO dao = new MadangDAO();
    	   jTableRefresh(dao.getBookList(), getBookColumn());
       }
       //검색2버튼을 클릭하면
       if(e.getSource() == btnSearch2 ) {
    	   MadangDAO dao = new MadangDAO();
    	   jTableRefresh(dao.getOrderList(), getOrderColumn());
       }
       //검색3버튼을 클릭하면
       if(e.getSource() == btnSearch3 ) {
    	   MadangDAO dao = new MadangDAO();
    	   jTableRefresh(dao.getCustomerList(), getCustomerColumn());
       }
       //초기화버튼을 클릭하면
       if(e.getSource() == btnReset) {
    	   MadangDAO dao = new MadangDAO();
    	   dao.resetOrderList();
    	   jTableRefresh(dao.getOrderList(), getOrderColumn());
       }
   }
  
}