package edu.jsu.mcis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeController implements ActionListener {

    private final TicTacToeModel model;
    private final TicTacToeView view;
    
    /* CONSTRUCTOR */

    public TicTacToeController(int width) {
        
        /* Initialize model, view, and width */

        model = new TicTacToeModel(width);
        view = new TicTacToeView(this, width);
        
    }

    public String getMarkAsString(int row, int col){
        return (model.getMark(row, col).toString());
    }

    public TicTacToeView getView(){
        return view;
    }

    public void actionPerformed(ActionEvent event){
        // INSERT YOUR CODE HERE
        JButton source = (JButton) event.getSource();
        String name = source.getName();

        String rc = name.replace("Square", "");
        int row = Integer.parseInt(rc.charAt(0) + "");
        int col = Integer.parseInt(rc.charAt(1) + "");

        if (model.getMark(row, col).equals(TicTacToeModel.Mark.EMPTY)){
            model.makeMark(row, col);
        }

        view.updateSquares();

        TicTacToeModel.Result result = model.getResult();

        if(result != TicTacToeModel.Result.NONE){
            view.disableSquares();
            view.showResult(result.toString());
        }
        else{
            view.clearResult();
        }
    }


}
