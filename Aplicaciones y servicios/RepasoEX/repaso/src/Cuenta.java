import java.io.Serializable;


public class Cuenta implements Serializable
{
    private double balance;  //El balance
    private int ccc;  //El numero de cuenta
    
    // Constructor de la clase Cuenta
    public Cuenta(int ccc)
    {    
        balance=0.0;
        this.ccc=ccc;
    }

    // Devuelve el balance
    public double getBalance(){
        return this.balance;
    }

    // Devuelve el ccc
    public int getCCC(){
        return this.ccc;
    }
    
    // Realiza un deposito
    public void deposito(double suma)
    {
        if (suma>0)
            balance+=suma;    
        else
            System.err.println("No se puede depositar una cantidad negativa");    
    }
    
    // Realiza una retirada
    public void retirada(double sum)
    {
        if (sum>0)
            this.balance-=sum;    
        else
            System.err.println("No se puede retirar una cantidad negativa.");    
    }

    // Actualiza la cuenta
    public void actualizar(){
        System.out.println("La clase Cuenta se ha actualizado");
    }

    public boolean equals(Object obj)
    {
	    return (obj instanceof Cuenta) && ((Cuenta) obj).getCCC()==this.ccc;
    }

    public int hashCode()
    {
	    return this.ccc;
    }
    
    public String toString()
    {
	    return "CCC " + this.ccc + ": " + "balance = " + this.balance;    
    }

     
}

