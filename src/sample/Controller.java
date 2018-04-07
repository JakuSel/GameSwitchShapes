package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class Controller {


    public ImageView img1;
    public ImageView img2;
    public ImageView img3;
    public ImageView img4;
    public ImageView img5;
    public ImageView img6;
    public ImageView img7;
    public ImageView img8;
    public ImageView img9;
    public ImageView img10;
    public ImageView img11;
    public ImageView img12;
    public ImageView img13;
    public ImageView img14;
    public ImageView img15;
    public ImageView img16;
    public State state = State.NEW;
    private boolean[][] field = new boolean[4][4];

    private boolean winGame(){
        int counter = 0;
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if (field[r][c])
                    counter++;
            }        }
        if (counter == 16 || counter == 0)
            return true;
        else
            return false;
    }

    public void toggleField (int num)throws Exception{
        if (state == State.PLAYING || state==State.GENERATE) {
            if (num == 1) {
                toggle(2);
                toggle(5);
                toggle(6);
            }
            else if (num ==2){
                toggle(1);
                toggle(3);
                toggle(5);
                toggle(6);
                toggle(7);
            }
            else if(num==3) {
                toggle(2);
                toggle(4);
                toggle(8);
                toggle(6);
                toggle(7);
            }
            else if(num == 4){
                toggle(2);
                toggle(4);
                toggle(8);
                toggle(6);
                toggle(7);
            }
            else if (num == 5){
                toggle(1);
                toggle(2);
                toggle(6);
                toggle(9);
                toggle(10);
            }
            else if (num == 6){
                toggle(1);
                toggle(2);
                toggle(3);
                toggle(5);
                toggle(7);
                toggle(9);
                toggle(10);
                toggle(11);
            }
            else if (num == 7){
                toggle(2);
                toggle(3);
                toggle(4);
                toggle(6);
                toggle(8);
                toggle(10);
                toggle(11);
                toggle(12);
            }
            else if (num == 8) {
                toggle(3);
                toggle(4);
                toggle(7);
                toggle(11);
                toggle(12);
            }
            else if (num == 9){
                toggle(5);
                toggle(6);
                toggle(10);
                toggle(13);
                toggle(14);
            }
            else if (num == 10){
                toggle(5);
                toggle(6);
                toggle(7);
                toggle(9);
                toggle(11);
                toggle(13);
                toggle(14);
                toggle(15);
            }
            else if (num == 11){
                toggle(6);
                toggle(7);
                toggle(8);
                toggle(10);
                toggle(12);
                toggle(14);
                toggle(15);
                toggle(16);
            }
            else if (num == 12){
                toggle(7);
                toggle(8);
                toggle(11);
                toggle(15);
                toggle(16);
            }
            else if (num == 13){
                toggle(9);
                toggle(10);
                toggle(14);
            }
            else if (num == 14){
                toggle(9);
                toggle(10);
                toggle(11);
                toggle(13);
                toggle(15);
            }
            else if (num == 15){
                toggle(10);
                toggle(11);
                toggle(12);
                toggle(14);
                toggle(16);
            }
            else {
                toggle(11);
                toggle(12);
                toggle(15);
            }
            if(winGame() && state==State.PLAYING) {
                state = State.END;
                alert();
            }
        }
    }

    public void info(MouseEvent me) throws Exception {
        ImageView imageView = (ImageView) me.getSource();
        String string=imageView.getId();
        Integer num = Integer.parseInt(string.substring(string.lastIndexOf("g")+1));

        toggleField(num);

    }

    public void toggle(int i) throws  Exception{
        int col =(i-1)%4;
        int row =(i-1)/4;

        field[row][col] = !field[row][col];
        Image image;
        String current = new java.io.File( "." ).getCanonicalPath();

        if(field[row][col]==false){
            image=new Image(new FileInputStream( current+"\\src\\img\\circle.png"));
                        }
        else{
            image=new Image(new FileInputStream(current+"\\src\\img\\cross.png"));
                         }
        switch(i) {
            case 1:   img1.setImage(image); break;
            case 2:   img2.setImage(image); break;
            case 3:   img3.setImage(image); break;
            case 4:   img4.setImage(image); break;
            case 5:   img5.setImage(image); break;
            case 6:   img6.setImage(image); break;
            case 7:   img7.setImage(image); break;
            case 8:   img8.setImage(image); break;
            case 9:   img9.setImage(image); break;
            case 10:   img10.setImage(image); break;
            case 11:   img11.setImage(image); break;
            case 12:   img12.setImage(image); break;
            case 13:   img13.setImage(image); break;
            case 14:   img14.setImage(image); break;
            case 15:   img15.setImage(image); break;
            case 16:   img16.setImage(image); break;
        }
    }

    public void newGame(MouseEvent mouseEvent) {
        if(state!=State.NEW)
            return;
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                field[i][j]=false;
        generateField();
    }

    private void generateField() {
        int i;
        state=State.GENERATE;
        try {
            Random random = new Random();
            for (i = 0; i < 10; i++) {
                toggleField(random.nextInt(16)+1);
            }
        state=State.PLAYING;
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void alert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Win");
        alert.setHeaderText("YOU ARE VICTORIOUS!!!");
        alert.setResizable(false);
        alert.setContentText("New game?");

        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.YES);

        if (button == ButtonType.OK) {
            state=State.NEW;
            generateField();
        } else {
            Platform.exit();
        }
    }
}