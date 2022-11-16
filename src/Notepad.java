import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.swing.*;
public class Notepad implements ActionListener{
    JFrame frame;
    JMenuBar mb;
    JMenu file,edit,format, view,help;
    JMenuItem New,Open,Save,SaveAs,Pagesetup,Print,Exit ,cut,copy,paste,selectAll, undo, delete,find,findNext,Replace,GoTo;
    JTextArea ta;
    JScrollPane scrollPane;
    JFileChooser chooser;
    Boolean isFileOpened = false;

    Notepad(){
        frame=new JFrame("Notepad");
        mb=new JMenuBar();
        mb.setSize(1100, 20);

        file=new JMenu("File");
        edit=new JMenu("Edit");
        format=new JMenu("format");
        view=new JMenu("view");
        help=new JMenu("Help");
        mb.add(file);
        mb.add(edit);
        mb.add(format);
        mb.add(view);
        mb.add(help);

        New=new JMenuItem("New");
        Open=new JMenuItem("Open");
        Save=new JMenuItem("Save");
        SaveAs=new JMenuItem("SaveAs");
        Pagesetup=new JMenuItem("Pagesetup");
        Print=new JMenuItem("Print");
        Exit=new JMenuItem("Exit");

        file.add(New);
        file.add(Open);
        file.add(Save);
        file.add(SaveAs);
        file.add(Pagesetup);
        file.add(Print);
        file.add(Exit);


        cut=new JMenuItem("cut");
        copy=new JMenuItem("copy");
        paste=new JMenuItem("paste");
        selectAll=new JMenuItem("selectAll");
        undo=new JMenuItem("undo");
        delete=new JMenuItem("delete");
        find=new JMenuItem("find");
        findNext=new JMenuItem("findNEXT");
        Replace=new JMenuItem("Replace");
        GoTo=new JMenuItem("GoTo");
        cut=new JMenuItem("cut");


        edit.add(undo);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(delete);
        edit.add(find);
        edit.add(findNext);
        edit.add(Replace);
        edit.add(GoTo);
        edit.add(selectAll);

        ta=new JTextArea();
        ta.setBounds(0, 50, 650, 450);

        scrollPane=new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scrollPane.setBounds(0, 20, 650, 450);

        //frame.add(ta);



        frame.add(mb);
        frame.add(scrollPane);
        frame.setSize(700, 500);
        frame.setLayout(null);
        frame.setVisible(true);

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        New.addActionListener(this);
        Open.addActionListener(this);
        Save.addActionListener(this);
        SaveAs.addActionListener(this);
        Pagesetup.addActionListener(this);
        Print.addActionListener(this);
        Exit.addActionListener(this);


    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==Save)
        {
            if(isFileOpened)
            {
                File file = new File(frame.getTitle());
                String path = file.getAbsolutePath();
                String data = ta.getText();
                try {
                    FileOutputStream fos = new FileOutputStream(path);
                    byte[] b = data.getBytes();
                    fos.write(b);
                    fos.close();
                } catch (Exception e1) {

                    e1.printStackTrace();
                }
            }else {
                e.setSource(SaveAs);
            }
        }
        if(e.getSource()==SaveAs)
        {

            String path = null;
            String data = null;
            chooser = new JFileChooser();
            int chose = chooser.showSaveDialog(SaveAs);
            if(chose==JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                path = file.getAbsolutePath();
                data =  ta.getText();
            }
            try {
                FileOutputStream fos = new FileOutputStream(path);
                byte[] b = data.getBytes();
                fos.write(b);
                fos.close();
            } catch (Exception e1) {

                e1.printStackTrace();
            }

        }
        if(e.getSource()==Open)
        {
            chooser = new JFileChooser();
            chooser.showOpenDialog(Open);
            File file = chooser.getSelectedFile();
            String path = file.getAbsolutePath();
            frame.setTitle(path);
            try {
                FileInputStream fis = new FileInputStream(file);
                ta.setText(convertInputStreamToString(fis));
                isFileOpened = true;
                fis.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }


        }
        if(e.getSource()== New) {
            frame.setTitle("New Document.txt");
            ta.setText("");
        }




        if(e.getSource()== cut)
            ta.cut();

        if(e.getSource()== copy)
            ta.copy();
        if(e.getSource()== paste)
            ta.paste();
        if(e.getSource()== selectAll)
            ta.selectAll();





    }
    private static String convertInputStreamToString(FileInputStream inputStream) throws IOException {

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }

        return result.toString(StandardCharsets.UTF_8.name());

    }

    public static void main(String[] args) {

        new Notepad();


    }

}