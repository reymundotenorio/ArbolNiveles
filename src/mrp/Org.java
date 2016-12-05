/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mrp;

/**
 *
 * @author reymundo
 */
public class Org implements Comparable{
    
    int ID_Princ;
    int ID_Secund;
    int Nivel;
    String Producto;
    String Sub_Producto;

    public Org() {
    }

    public Org(int ID_Princ, int ID_Secund, int Nivel, String Producto, String Sub_Producto) {
        this.ID_Princ = ID_Princ;
        this.ID_Secund = ID_Secund;
        this.Nivel = Nivel;
        this.Producto = Producto;
        this.Sub_Producto = Sub_Producto;
    }

    public int getID_Princ() {
        return ID_Princ;
    }

    public void setID_Princ(int ID_Princ) {
        this.ID_Princ = ID_Princ;
    }

    public int getID_Secund() {
        return ID_Secund;
    }

    public void setID_Secund(int ID_Secund) {
        this.ID_Secund = ID_Secund;
    }

   
    public int getNivel() {
        return Nivel;
    }

    public void setNivel(int Nivel) {
        this.Nivel = Nivel;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String Producto) {
        this.Producto = Producto;
    }

    public String getSub_Producto() {
        return Sub_Producto;
    }

    public void setSub_Producto(String Sub_Producto) {
        this.Sub_Producto = Sub_Producto;
    }

    @Override
    public int compareTo(Object o) {
        
          int compareage=((Org)o).getNivel();
        /* For Ascending order*/
        return this.Nivel-compareage;

        /* For Descending order do like this */
        //return compareage-this.studentage;
         }

 
    
}
