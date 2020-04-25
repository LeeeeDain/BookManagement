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
    JTextField tfCustomerId, tfBookId; //고객번호, 책번호   
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
       
        //고객 번호
        JLabel bId = new JLabel("고객 번호 : ");
        tfCustomerId = new JTextField(20);     
        //그리드백에 붙이기
        gbAdd(bId, 2, 0, 1, 1);
        gbAdd(tfCustomerId, 3, 0, 1, 1);
       
        //책 번호
        JLabel bPwd = new JLabel("책 번호 : ");
        tfBookId = new JTextField(20);
        gbAdd(bPwd, 2, 1, 1, 1);
        gbAdd(tfBookId, 3, 1, 2, 1);

        //버튼
        JPanel pButton = new JPanel();
        btnInsert = new JButton("주문");
        btnCancel = new JButton("취소");     
        pButton.add(btnInsert);
        pButton.add(btnCancel);    
        gbAdd(pButton, 0, 2, 4, 1);
       
        //버튼에 감지기를 붙이자
        btnInsert.addActionListener(this);
        btnCancel.addActionListener(this);
       
        
        Dimension frameSize = this.getSize(); // 프레임 사이즈
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈
        this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2 - 300); // 화면 중앙
        
        setSize(250,200);
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
    	if(dao.isCustomer(tfCustomerId.getText()) == false && dao.isBook(tfBookId.getText()) == false) {
    		JOptionPane.showMessageDialog(this, "해당하는 회원 번호와 책 번호가 없습니다.");
    	}
    	else if(dao.isCustomer(tfCustomerId.getText()) == false) {
    		JOptionPane.showMessageDialog(this, "해당하는 회원 번호가 없습니다.");
    	}
    	else if(dao.isBook(tfBookId.getText()) == false) {
    		JOptionPane.showMessageDialog(this, "해당하는 책 번호가 없습니다.");
    	}
    	else {
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
    }
   
    public OrderDTO getOrderData(){
        //화면에서 사용자가 입력한 내용을 얻는다.
    	MadangDAO dao = new MadangDAO();
    	OrderDTO dto = new OrderDTO();
    	int orderid = dao.getNewOrderId();
    	String custid = tfCustomerId.getText();
    	String bookid = tfBookId.getText();
    	String saleprice = dao.getBookPrice(bookid);
    	SimpleDateFormat timeformat = new SimpleDateFormat ( "yyyy-MM-dd");
    	String orderdate = timeformat.format(System.currentTimeMillis());
    	
        //dto에 담는다.
    	dto.setOrderId(orderid);
    	dto.setCustId(custid);
    	dto.setBookId(bookid);
    	dto.setSalePrice(saleprice);
    	dto.setOrderDate(orderdate);
       
        return dto;
    } 
}