package javafx1;

import java.sql.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Label.*;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.Group;
import static javafx.scene.paint.Color.color;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class JavaFX1 extends Application {



public void start(Stage primaryStage)
{
    //long total, state_total=0, men=0, women=0, child=0;
Text t=new Text();
t.setText("Population Statistics");
t.setId("heading");
HBox h1=new HBox();
h1.getChildren().add(t);
h1.setAlignment(Pos.TOP_CENTER);
h1.setPadding(new Insets(20, 10, 20,10));

Button btn = new Button();
btn.setText("Get Details!");
ComboBox <String> state=new ComboBox<>();
state.getItems().addAll("Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir","Jharkhand","Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura","Uttar Pradesh","Uttrakhand","West Bengal");
state.setValue("Select State");
state.setPrefHeight(40);
state.setPrefWidth(305);
state.setId("state");

HBox h2 = new HBox();
h2.getChildren().addAll(state, btn);
h2.setAlignment(Pos.CENTER);
h2.setSpacing(15);
h2.setPadding(new Insets(25, 10, 20, 10));
Text t2=new Text();
t2.setText("Total Population:");
t2.setId("text2");
Text t3=new Text();
t3.setText("% of Total(of all states):");
t3.setId("text3");
HBox h3=new HBox(575);
h3.getChildren().addAll(t2, t3);
h3.setPadding(new Insets(30, 0, 20, 30));
h3.setId("invi1");

Text men1=new Text();
men1.setText("Men");
men1.setId("men1");
Text men2=new Text();
men2.setText("Number of men");
men2.setId("men2");
Text men3=new Text();
men3.setText("Percentage of total:");
men3.setId("men3");
VBox vMen=new VBox();
vMen.getChildren().addAll(men1, men2, men3);
vMen.setPrefWidth(400);
vMen.setAlignment(Pos.TOP_CENTER);
vMen.setSpacing(30);
vMen.setPadding(new Insets(20, 0, 50, 0));
vMen.setId("vmen");

Text women1=new Text();
women1.setText("Women");
women1.setId("women1");
Text women2=new Text();
women2.setText("Number of women:");
women2.setId("women2");
Text women3=new Text();
women3.setText("Percentage of total:");
women3.setId("women3");
VBox vWomen=new VBox();
vWomen.getChildren().addAll(women1, women2, women3);
vWomen.setPrefWidth(400);
vWomen.setAlignment(Pos.TOP_CENTER);
vWomen.setSpacing(30);
vWomen.setPadding(new Insets(20, 0, 50, 0));
vWomen.setId("vwomen");

Text children1=new Text();
children1.setText("Children");
children1.setId("children1");
Text children2=new Text();
children2.setText("Number of children");
children2.setId("children2");
Text children3=new Text();
children3.setText("Percentage of total:");
children3.setId("children3");
VBox vChildren=new VBox();
vChildren.getChildren().addAll(children1,children2 ,children3 );
vChildren.setPrefWidth(400);
vChildren.setAlignment(Pos.TOP_CENTER);
vChildren.setSpacing(30);
vChildren.setPadding(new Insets(20, 0, 50, 0));
vChildren.setId("vchildren");

HBox h4=new HBox();
h4.getChildren().addAll(vMen, vWomen, vChildren);
h4.setSpacing(5);
h4.setPadding(new Insets(20, 30, 10, 30));
h4.setId("invi2");

VBox vb=new VBox();
vb.getChildren().addAll(h1,h2, h3, h4);

StackPane root=new StackPane(h2);
btn.setOnAction(new EventHandler<ActionEvent>(){
    public void handle(ActionEvent event)
            {     
                HBox h3=new HBox(575);
               HBox h4=new HBox();
    try
    {
        String url="jdbc:oracle:thin:@localhost:1521:XE";   
        Class.forName("oracle.jdbc.driver.OracleDriver");
        System.out.println("Driver loaded");
        Connection con=DriverManager.getConnection(url,"system","***"); //write your username and password
        System.out.println("Connection Established");
         Statement stmt;
        ResultSet res;
        
        long total=0,state_total=0;
        long men = 0, women=0,child=0;
        String query2="select * from state";
        stmt=con.createStatement();
        res=stmt.executeQuery(query2);
            while(res.next())
            {
                
                total+=res.getInt(2)+res.getInt(3)+res.getInt(4);  //total population
            } 
                Text t2=new Text();
                t2.setText("Total Population of India:"+total);
                t2.setId("text2");
                
                String query="select * from state where states='"+state.getValue()+"'";
                res=stmt.executeQuery(query);
                 while(res.next())
            {
                System.out.print(res.getString(1)+" ");
                state_total=res.getInt(4)+res.getInt(3)+res.getInt(2); //total of a state
                men=res.getInt(2);
                women=res.getInt(3);
                child=res.getInt(4);
                
            }  
                 Text t3=new Text();
                t3.setText("% of Total(of all states):"+(1.0*state_total/total)*100+"%");
                t3.setId("text3");
                
                h3.getChildren().addAll(t2, t3);
                h3.setPadding(new Insets(30, 0, 20, 30));
                h3.setId("invi1");
                System.out.println(men+" "+women);
                Text men1=new Text();
                men1.setText("Men");
                men1.setId("men1");
                Text men2=new Text();
                men2.setText("Number of men:"+men);
                men2.setId("men2");
                Text men3=new Text();
                men3.setText("Percentage of total:"+(men*1.0/state_total*100)+"%");
                men3.setId("men3");
                VBox vMen=new VBox();
                vMen.getChildren().addAll(men1, men2, men3);
                vMen.setPrefWidth(400);
                vMen.setAlignment(Pos.TOP_CENTER);
                vMen.setSpacing(30);
                vMen.setPadding(new Insets(20, 0, 50, 0));
                vMen.setId("vmen");

                Text women1=new Text();
                women1.setText("Women");
                women1.setId("women1");
                Text women2=new Text();
                women2.setText("Number of women:"+women);
                women2.setId("women2");
                Text women3=new Text();
                women3.setText("Percentage of total:"+(women*1.0/state_total*100)+"%");
                women3.setId("women3");
                VBox vWomen=new VBox();
                vWomen.getChildren().addAll(women1, women2, women3);
                vWomen.setPrefWidth(400);
                vWomen.setAlignment(Pos.TOP_CENTER);
                vWomen.setSpacing(30);
                vWomen.setPadding(new Insets(20, 0, 50, 0));
                vWomen.setId("vwomen");

                Text children1=new Text();
                children1.setText("Children");
                children1.setId("children1");
                Text children2=new Text();
                children2.setText("Number of children:"+child);
                children2.setId("children2");
                Text children3=new Text();
                children3.setText("Percentage of total:"+(child*1.0/state_total*100)+"%");
                children3.setId("children3");
                VBox vChildren=new VBox();
                vChildren.getChildren().addAll(children1,children2 ,children3 );
                vChildren.setPrefWidth(400);
                vChildren.setAlignment(Pos.TOP_CENTER);
                vChildren.setSpacing(30);
                vChildren.setPadding(new Insets(20, 0, 50, 0));
                vChildren.setId("vchildren");

                
                h4.getChildren().addAll(vMen, vWomen, vChildren);
                h4.setSpacing(5);
                h4.setPadding(new Insets(20, 30, 10, 30));
                h4.setId("invi2");
                vb.getChildren().addAll(h1,h2,h3, h4);
                root.getChildren().add(vb);
                System.out.println("State total:"+state_total);
            
            //calculating men %
           System.out.println("%men:"+(men*1.0/state_total*100));
            System.out.println("%women:"+(women*1.0/state_total*100));
            System.out.println("%Children:"+(child*1.0/state_total*100));
            
            con.close();
            stmt.close();
            res.close();
    }
    catch (ClassNotFoundException e) { 
            System.out.println("Driver not loaded");
        }
        catch (SQLException e) { 
            System.out.println(e);
        } 
    h4.setStyle("visibility:visible");
    h3.setStyle("visibility:visible");
   }
});



root.setId("pane");
Scene scene = new Scene(root, 1280, 632);
scene.getStylesheets().addAll(getClass().getResource("styles.css").toExternalForm(),"https://fonts.googleapis.com/css2?family=Patrick+Hand&display=swap","https://fonts.googleapis.com/css2?family=Cedarville+Cursive&display=swap");



primaryStage.setTitle("Population Statistics");
primaryStage.setScene(scene);
primaryStage.show();
}
public static void main(String[] args) {
launch(args);
}



}