package com.example.anurag.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener,View.OnLongClickListener {

    Button seven,dec,eight,nine,mul,four,five,six,one,two,three,zero,equal,plus,sub,div;
    TextView et;
    String temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        def();
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        zero.setOnClickListener(this);
        dec.setOnClickListener(this);
        equal.setOnClickListener(this);
        plus.setOnClickListener(this);
        sub.setOnClickListener(this);
        mul.setOnClickListener(this);
        div.setOnClickListener(this);
        dec.setOnLongClickListener(this);
    }
    public void def(){
        seven = (Button)findViewById(R.id.seven);
        eight = (Button)findViewById(R.id.eight);
        nine = (Button)findViewById(R.id.nine);
        four = (Button)findViewById(R.id.four);
        five = (Button)findViewById(R.id.five);
        six = (Button)findViewById(R.id.six);
        one = (Button)findViewById(R.id.one);
        two = (Button)findViewById(R.id.two);
        three = (Button)findViewById(R.id.three);
        mul = (Button)findViewById(R.id.mul);
        zero = (Button)findViewById(R.id.zero);
        dec = (Button) findViewById(R.id.dec);
        equal = (Button)findViewById(R.id.equal);
        plus = (Button)findViewById(R.id.plus);
        sub = (Button)findViewById(R.id.sub);
        et = (TextView)findViewById(R.id.result);
        div = (Button)findViewById(R.id.div);
    }
    @Override
    public void onClick(View v) {
        temp= (String) et.getText();
        String value=et.getText().toString();
        switch (v.getId()){

            case R.id.nine :
                if (value.length()<10)
                et.setText(et.getText()+"9");
                break;
            case R.id.eight :
                if (value.length()<10)
                et.setText(et.getText()+"8");
                break;
            case R.id.seven :
                if (value.length()<10)
                et.setText(et.getText()+"7");
                break;
            case R.id.six :
                if (value.length()<10)
                et.setText(et.getText()+"6");
                break;
            case R.id.five :
                if (value.length()<10)
                et.setText(et.getText()+"5");
                break;
            case R.id.four :
                if (value.length()<10)
                et.setText(et.getText()+"4");
                break;
            case R.id.three :
                if (value.length()<10)
                et.setText(et.getText()+"3");

                break;
            case R.id.two :
                if (value.length()<10)
                et.setText(et.getText()+"2");
                break;
            case R.id.one :
                if (value.length()<10)
                et.setText(et.getText()+"1");
                break;
            case R.id.div :
                if (value.length()<10)
                
                if(temp.length()!=0 && !temp.substring(temp.length()-1).equals("*") && !temp.substring(temp.length()-1).equals("/") && !temp.substring(temp.length()-1).equals("-") && !temp.substring(temp.length()-1).equals("+"))
                    et.setText(et.getText()+"/");
                break;
            case R.id.zero :
                if (value.length()<10)
                et.setText(et.getText()+"0");
                break;
            case R.id.dec :
                
                if(temp.length()!=0)
                    et.setText(temp.substring(0,temp.length()-1));
                break;
            case R.id.sub :
                if (value.length()<10)
                
                if(temp.length()!=0 && !temp.substring(temp.length()-1).equals("*") && !temp.substring(temp.length()-1).equals("/") && !temp.substring(temp.length()-1).equals("-") && !temp.substring(temp.length()-1).equals("+"))
                    et.setText(et.getText()+"-");
                break;
            case R.id.plus :
                if (value.length()<10)
                
                if(temp.length()!=0 && !temp.substring(temp.length()-1).equals("*") && !temp.substring(temp.length()-1).equals("/") && !temp.substring(temp.length()-1).equals("-") && !temp.substring(temp.length()-1).equals("+"))
                    et.setText(et.getText()+"+");
                break;
            case R.id.mul :


                if (value.length()<10)
                if(temp.length()!=0 && !temp.substring(temp.length()-1).equals("*") && !temp.substring(temp.length()-1).equals("/") && !temp.substring(temp.length()-1).equals("-") && !temp.substring(temp.length()-1).equals("+"))
                    et.setText(et.getText()+"*");
                break;
            case R.id.equal :
                if (value.length() < 10 );
                if(temp.length()!=0 && !value.matches("[a-zA-Z]+") && !temp.substring(temp.length()-1).equals("*") && !temp.substring(temp.length()-1).equals("/") && !temp.substring(temp.length()-1).equals("-") && !temp.substring(temp.length()-1).equals("+")) {
                    temp = "" + eval(temp);
                    et.setText(temp);
                }
                break;

        }
    }



    public static double eval(final String str) {
        class Parser {
            int pos = -1, c;

            void eatChar() {
                c = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            void eatSpace() {
                while (Character.isWhitespace(c)) eatChar();
            }

            double parse() {
                eatChar();
                double v = parseExpression();
                if (c != -1) throw new RuntimeException("Unexpected: " + (char)c);
                return v;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor | term brackets
            // factor = brackets | number | factor `^` factor
            // brackets = `(` expression `)`

            double parseExpression() {
                double v = parseTerm();
                for (;;) {
                    eatSpace();
                    if (c == '+') { // addition
                        eatChar();
                        v += parseTerm();
                    } else if (c == '-') { // subtraction
                        eatChar();
                        v -= parseTerm();
                    } else {
                        return v;
                    }
                }
            }

            double parseTerm() {
                double v = parseFactor();
                for (;;) {
                    eatSpace();
                    if (c == '/') { // division
                        eatChar();
                        v /= parseFactor();
                    } else if (c == '*' || c == '(') { // multiplication
                        if (c == '*') eatChar();
                        v *= parseFactor();
                    } else {
                        return v;
                    }
                }
            }

            double parseFactor() {
                double v;
                boolean negate = false;
                eatSpace();
                if (c == '+' || c == '-') { // unary plus & minus
                    negate = c == '-';
                    eatChar();
                    eatSpace();
                }
                if (c == '(') { // brackets
                    eatChar();
                    v = parseExpression();
                    if (c == ')') eatChar();
                } else { // numbers
                    StringBuilder sb = new StringBuilder();
                    while ((c >= '0' && c <= '9') || c == '.') {
                        sb.append((char)c);
                        eatChar();
                    }
                    if (sb.length() == 0) throw new RuntimeException("Unexpected: " + (char)c);
                    v = Double.parseDouble(sb.toString());
                }
                eatSpace();
                if (c == '^') { // exponentiation
                    eatChar();
                    v = Math.pow(v, parseFactor());
                }
                if (negate) v = -v; // unary minus is applied after exponentiation; e.g. -3^2=-9
                return v;
            }
        }
        double te;
        return new Parser().parse();
    }

    @Override
    public boolean onLongClick(View v) {
        
        et.setText("");
        return false;
    }
}

