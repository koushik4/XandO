package com.example.xando;

import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import  java.util.*;
class Generate{
    HashMap<String, ArrayList<ArrayList<String>>> hm=new HashMap<String, ArrayList<ArrayList<String>>>();
    public Generate(){
        hm.put("win",new ArrayList<ArrayList<String>>());
        hm.put("lose",new ArrayList<ArrayList<String>>());
        hm.put("draw",new ArrayList<ArrayList<String>>());

    }
    boolean player1,player2;
    public boolean right(char[][] c,int row){
        char ch=c[row][0];
        if(ch==' ')return false;
        for(int i=0;i<3;i++){
            if(c[row][i]==' '){player1=false;player2=false;return false;}
            if(c[row][i]!=ch){player1=false;player2=false;return false;}
        }
        if(ch=='x'){player1=true;player2=false;}
        if(ch=='o'){player2=true;player1=false;}
        return true;
    }
    public boolean down(char[][] c,int col){
        char ch=c[0][col];
        if(ch==' ')return false;
        for(int i=0;i<3;i++){
            if(c[i][col]==' '){player1=false;player2=false;return false;}
            if(c[i][col]!=ch){player1=false;player2=false;return false;}
        }
        if(ch=='x'){player1=true;player2=false;}
        if(ch=='o'){player1=false;player2=true;}
        return true;
    }
    public  boolean dig(char[][] c,int row,int col){
        char ch=c[row][col];
        if(ch==' ')return false;
        for(int i=0;i<3;i++){
            if(c[row+i][col+i]==' '){player1=false;player2=false;return false;}
            if(c[row+i][col+i]!=ch){player1=false;player2=false;return false;}
        }
        if(ch=='x'){player1=true;player2=false;}
        if(ch=='o'){player1=false;player2=true;}
        return true;
    }
    public  boolean antidig(char[][] c,int row,int col){
        char ch=c[row][col];
        if(ch==' ')return false;
        for(int i=0;i<3;i++){
            if(c[row+i][col-i]==' '){player1=false;player2=false;return false;}
            if(c[row+i][col-i]!=ch){player1=false;player2=false;return false;}
        }
        if(ch=='x'){player1=true;player2=false;}
        if(ch=='o'){player1=false;player2=true;}
        return true;
    }
    public  boolean check(char[][] c){
        for(int i=0;i<3;i++){
            if(right(c,i))return true;
            if(down(c,i))return true;
        }
        return dig(c,0,0) || antidig(c,0,2);
    }
    public void traverse(char[][] c, ArrayList<String> l, int player){
        if(check(c)){
            if(player%2==0)hm.get("win").add(new ArrayList<>(l));
            else hm.get("lose").add(new ArrayList<>(l));
            return;
        }
        if(l.size()>=9){
            hm.get("draw").add(new ArrayList<>(l));
            return;
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(l.contains(i+" "+j))continue;
                l.add(i+" "+j);
                c[i][j]=player%2==0?'o':'x';
                traverse(c,l,player+1);
                c[i][j]=' ';l.remove(l.indexOf(i+" "+j));
            }
        }

    }

    public String Probability(List<String> l){
        List<List<String>> win=new ArrayList<>();
        int count=0;
        for(List<String> x:hm.get("win")){
            if(x.size()>=l.size()){
                if(l.equals(x.subList(0,l.size())))
                {win.add(x);count++;}
            }
        }
        HashMap<String,Integer> map=new HashMap<>();
        for(List<String> s:win) {
            if (s.size() > l.size()) {
                if(s.size()==l.size()+1)return s.get(l.size());
                if (!map.containsKey(s.get(l.size()))) map.put(s.get(l.size()), 0);
                map.put(s.get(l.size()), map.get(s.get(l.size())) + 1);
            }
        }
        int max=0;String res="";
        for(String s:map.keySet()){
            if(max<map.get(s)){max=map.get(s);res=s;}
        }
        return res.equals("")?"You cant win bro":"Move to "+res;

    }
}
