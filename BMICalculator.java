import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

class Test extends JFrame{
	Container c;
	JLabel l1,l2,l3,l4;
	JTextField t1;
	JComboBox c1,c2;
	JPanel p1,p2,p3;
	JButton b1;
	
	Test(){
		c=getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		
		p1=new JPanel(new FlowLayout(FlowLayout.LEFT));
		l1=new JLabel("Weight(Kg)");
		t1= new JTextField(10);
		p1.add(l1);
		p1.add(t1);
		c.add(p1);
		
		p2=new JPanel(new FlowLayout(FlowLayout.LEFT));
		String[] ft={"1","2","3","4","5","6","7","8"};
		String[] in={"1","2","3","4","5","6","7","8","9","10","11"};
		l2=new JLabel("Height");
		c1=new JComboBox(ft);
		c2=new JComboBox(in);
		l3=new JLabel("Feet");
		l4=new JLabel("Inches");
		p2.add(l2);
		p2.add(l3);
		p2.add(c1);
		p2.add(l4);
		p2.add(c2);
		c.add(p2);
		
		p3=new JPanel(new FlowLayout(FlowLayout.CENTER));
		b1=new JButton("Calculate");
		p3.add(b1);
		c.add(p3);
		b1.addActionListener(new L1());
	}
	class L1 implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			if(t1.getText().equals("")){
				JOptionPane.showMessageDialog(c, "Weight Should not be blank");
				t1.requestFocus();
			}
			else{
				try{
					double weight=Double.parseDouble(t1.getText());
					if(weight<=0){
						JOptionPane.showMessageDialog(c, "Weight should be >0");
						t1.setText("");
						t1.requestFocus();
					}
					else{
						Object ftItem=c1.getSelectedItem();
						String ft=(String)ftItem;
						int foot=Integer.parseInt(ft);
						
						Object InItem=c2.getSelectedItem();
						String in=(String)InItem;
						int inches=Integer.parseInt(in);
					
						while(foot>0){
							inches=inches+12;
							foot--;
						}
						
						double height=inches*2.54;
						double bmi=weight/(height*height);
						bmi=bmi*1000;
				
						String msg;
						if(bmi<18.5){msg="You are underweight";}
						else if(bmi>=18.5&&bmi<25){msg="You are normal";}
						else if(bmi>=25&&bmi<30){msg="You are overweight";}
						else{msg="You are obese";}
				
						NumberFormat nf=NumberFormat.getInstance();
						nf.setMaximumFractionDigits(2);
						String bmis=nf.format(bmi);
					
						JOptionPane.showMessageDialog(c, "BMI= "+bmis+" "+msg);
					
						t1.setText("");
						c1.setSelectedItem("0");
						c2.setSelectedItem("0");
					
					}
				
				}	
				catch(NumberFormatException e){
				JOptionPane.showMessageDialog(c,"Enter Proper Weight");
				t1.setText("");
				t1.requestFocus();
				}
			}
		}
	}
	public static void main(String args[]){
		Test f=new Test();
		f.setSize(300,150);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("BMI Calculator");
		f.setLocation(300,300);
		f.setResizable(false);
	}
}