package BookManagement;

import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
 
public class OrderView extends JFrame implements ActionListener {
    JPanel p;
    JTextField tfOrderId, tfCustomerId, tfBookId, tfPrice, tfOrderDate; //고객번호, 책번호   
    JButton btnInsert, btnCancel; //입력, 취소 버튼
   
    GridBagLayout gb;
    GridBagConstraints gbc;
    MainView mList ;
   
    public OrderView(){
        createUI(); // UI작성해주는 메소드      
    }//생성자
   
    private void createUI(){
        this.setTitle("주문");
        gb = new GridBagLayout();
        setLayout(gb);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
       
        //주문 번호
        JLabel bOrderId = new JLabel("주문 번호 : ");
        tfOrderId = new JTextField(20);     
        //그리드백에 붙이기
        gbAdd(bOrderId, 2, 0, 1, 1);
        gbAdd(tfOrderId, 3, 0, 1, 1);
       
        //고객 번호
        JLabel bCustomerId = new JLabel("고객 번호 : ");
        tfCustomerId = new JTextField(20);     
        //그리드백에 붙이기
        gbAdd(bCustomerId, 2, 1, 1, 1);
        gbAdd(tfCustomerId, 3, 1, 1, 1);       
        
        //책 번호
        JLabel bBookId = new JLabel("책 번호 : ");
        tfBookId = new JTextField(20);
        gbAdd(bBookId, 2, 2, 1, 1);
        gbAdd(tfBookId, 3, 2, 1, 1);

        //회원 가격
        JLabel bPrice = new JLabel("세일 가격 : ");
        tfPrice = new JTextField(20);
        gbAdd(bPrice, 2, 3, 1, 1);
        gbAdd(tfPrice, 3, 3, 1, 1);

        //주문 날짜
        JLabel bOrderDate = new JLabel("주문 날짜 : ");
        tfOrderDate = new JTextField(20);
        gbAdd(bOrderDate, 2, 4, 1, 1);
        gbAdd(tfOrderDate, 3, 4, 1, 1);

        //버튼
        JPanel pButton = new JPanel();
        btnInsert = new JButton("주문");
        btnCancel = new JButton("취소");     
        pButton.add(btnInsert);
        pButton.add(btnCancel);    
        gbAdd(pButton, 2, 5, 4, 1);
       
        //버튼에 감지기를 붙이자
        btnInsert.addActionListener(this);
        btnCancel.addActionListener(this);
       
        
        Dimension frameSize = this.getSize(); // 프레임 사이즈
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈
        this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2 - 300); // 화면 중앙
        
        setSize(400,400);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); //dispose(); //현재창만 닫는다.
    }//createUI
   
    
    //그리드백레이아웃에 붙이는 메소드
    private void gbAdd(JComponent c, int x, int y, int w, int h){
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gb.setConstraints(c, gbc);
        gbc.insets = new Insets(2, 2, 2, 2);
        add(c, gbc);
    }//gbAdd
   
    public static void main(String[] args) {      
        new OrderView();
    }
   
 
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == btnInsert){
            insertOrder();
        }else if(ae.getSource() == btnCancel){
            this.dispose(); 
        }      
    } 
   
    private void insertOrder(){
    	MadangDAO dao = new MadangDAO();
        //화면에서 사용자가 입력한 내용을 얻는다.
    	OrderDTO dto = getOrderData();     
        boolean ok = dao.insertMember(dto);
       
        if(ok){
            JOptionPane.showMessageDialog(this, "주문이 완료되었습니다.");
            dispose();      
        }else{
            JOptionPane.showMessageDialog(this, "주문이 정상적으로 처리되지 않았습니다.");
        }
    }
   
    public OrderDTO getOrderData(){
        //화면에서 사용자가 입력한 내용을 얻는다.
    	MadangDAO dao = new MadangDAO();
    	OrderDTO dto = new OrderDTO();
    	String orderid = tfOrderId.getText();
    	String custid = tfCustomerId.getText();
    	String bookid = tfBookId.getText();
    	String price = tfPrice.getText();
    	String orderdate = tfOrderDate.getText();
    	
        //dto에 담는다.
    	dto.setOrderId(orderid);
    	dto.setCustId(custid);
    	dto.setBookId(bookid);
    	dto.setSalePrice(price);
    	dto.setOrderDate(orderdate);
       
        return dto;
    } 
}