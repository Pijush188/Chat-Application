import javax.management.remote.JMXConnectorFactory;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.Socket;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

public class client implements ActionListener {

    JTextField text;
    static JPanel a1;
    static Box vertical=Box.createVerticalBox();
    static DataOutputStream dout;
    static JFrame f=new JFrame();
    client(){
        f.setLayout(null);
        //add panel
        JPanel p1=new JPanel();
        p1.setBackground(new Color(7,94,84));
        //add panel above the frame
        p1.setBounds(0,0,450,70);
        p1.setLayout(null);
        f.add(p1);

        //image display[back arrow]
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        //for scaling[crop]
        Image i2=i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel back=new JLabel(i3);
        //set image position
        back.setBounds(5,20,25,25);
        p1.add(back);

        //action[exit/back]
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        //dp image display
        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icons/2.png"));
        //for scaling[crop]
        Image i5=i4.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        ImageIcon i6=new ImageIcon(i5);
        JLabel profile=new JLabel(i6);
        //set image position
        profile.setBounds(40,10,50,50);
        p1.add(profile);

        //video call image display
        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        //for scaling[crop]
        Image i8=i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i9=new ImageIcon(i8);
        JLabel videocall=new JLabel(i9);
        //set image position
        videocall.setBounds(300,20,30,30);
        p1.add(videocall);

        videocall.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==1){
                    //get url
                    String url="https://meet.google.com/";

                    //open the url in default web browser
                    try {
                        Desktop.getDesktop().browse(new java.net.URI(url));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        //voice call image display
        ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        //for scaling[crop]
        Image i11=i10.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT);
        ImageIcon i12=new ImageIcon(i11);
        JLabel voicecall=new JLabel(i12);
        //set image position
        voicecall.setBounds(350,20,35,30);
        p1.add(voicecall);

        //3 dots image display
        ImageIcon i13=new ImageIcon(ClassLoader.getSystemResource("icons/mail.png"));
        //for scaling[crop]
        Image i14=i13.getImage().getScaledInstance(35,25,Image.SCALE_DEFAULT);
        ImageIcon i15=new ImageIcon(i14);
        JLabel mail=new JLabel(i15);
        //set image position
        mail.setBounds(400,20,35,25);
        p1.add(mail);

        mail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==1){
                    //get url
                    String url="https://mail.google.com/mail/u/0/#inbox?compose=new";

                    //open the url in default web browser
                    try {
                        Desktop.getDesktop().browse(new java.net.URI(url));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });


        //name display
        JLabel name=new JLabel("Rishikesh");
        //name display coordinates
        name.setBounds(110,15,100,18);
        //text-color
        name.setForeground(Color.white);
        //text-font
        name.setFont(new Font("SAN_SERIF",Font.BOLD,18));
        p1.add(name);

        name.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==1){
                    //get url
                    String url="www.linkedin.com/in/rishikesh-hazra-466227300";

                    //open the url in default web browser
                    try {
                        Desktop.getDesktop().browse(new java.net.URI(url));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        //status display
        JLabel status=new JLabel("online");
        //name display coordinates
        status.setBounds(110,35,100,18);
        //text-color
        status.setForeground(Color.white);
        //text-font
        status.setFont(new Font("SAN_SERIF",Font.BOLD,14));
        p1.add(status);

        //text-area
        a1=new JPanel();
        a1.setBounds(5,75,440,570);
        f.add(a1);

        //text-field
        text=new JTextField();
        //field co-ordinates
        text.setBounds(5,655,310,40);
        //text font
        text.setFont(new Font("SAN_SERIF", Font.PLAIN,16));
        f.add(text);

        //Send buttons
        JButton send=new JButton("send");
        //button co-ordinates
        send.setBounds(320,655,123,40);
        //button bg-color
        send.setBackground(new Color(7,94,84));
        //set send text-color
        send.setForeground(Color.white);
        //set send font
        send.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        //send function
        send.addActionListener(this);
        f.add(send);


        //frame size
        f.setSize(450,700);
        //location(by default top left origin)
        f.setLocation(800,50);
        //BG color[from awt package]
        f.getContentPane().setBackground(Color.white);
        //action bar hide
        f.setUndecorated(true);
        //visible(by default hidden)
        f.setVisible(true);
    }

    //abstract method override[ActionListener class]
    public void actionPerformed(ActionEvent ae){
        try {
            String out = text.getText();
            JPanel p2 = formatLabel(out);

            //right side alignment
            a1.setLayout(new BorderLayout());
            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);
            //vertical alignment
            vertical.add(right);
            vertical.add(Box.createHorizontalStrut(15));
            //add into the panel
            a1.add(vertical, BorderLayout.PAGE_START);

            //send the message
            dout.writeUTF(out);
            //after sending text field empty
            text.setText("");
            //reload the panel for every message sent
            f.repaint();
            f.invalidate();
            f.validate();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    //format the sending message
    public static JPanel formatLabel(String out){
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel output=new JLabel("<html><p style=\"width : 150px\">"+out+"</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN,16));
        output.setBackground(new Color(37,211,102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));

        panel.add(output);

        //add time
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        JLabel time=new JLabel();
        //cal time
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);

        return panel;
    }

    public static void main(String[] args){
        new client();

        try{
            Socket s=new Socket("localhost",6001);
            //message receive
            DataInputStream din=new DataInputStream(s.getInputStream());
            //message output
            dout=new DataOutputStream(s.getOutputStream());

            while(true){
                a1.setLayout(new BorderLayout());
                //message read
                String msg=din.readUTF();
                //format the message[add data-time & color] from defined fuction
                JPanel panel=formatLabel(msg);

                JPanel left=new JPanel(new BorderLayout());
                left.add(panel, BorderLayout.LINE_START);
                vertical.add(left);

                vertical.add(Box.createVerticalStrut(15));
                a1.add(vertical,BorderLayout.PAGE_START);
                //reload after get message
                f.validate();

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
