/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javabase.apps.utility;


class A implements B
{    
    @Override
    public int methodB(int i)
    {
        return i =+ i * i;
    }
}
interface B
{
    int methodB(int i);
}
public class MainClass 
{
    public static void main(String[] args)
    {
         Byte B = new Byte("55");

         byte b = B.byteValue();

        Double D = new Double("521.56");
       b = D.byteValue();
        
        System.out.println(b);
    }
}