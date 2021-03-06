package io.VideoClub.Model;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Reservation implements Comparable<Reservation>{

    public enum StatusReserve{
        ACTIVE,  //ini on, finished off
        FINISHED, //ini on finised on == end
        PENDING  //ini on , finished off and end past
    }

    public Product pro;
    public IClient cli;
    public LocalDate ini;
    public LocalDate end;
    public LocalDate finished;
    public StatusReserve status;
    
    private Reservation(){};
    public Reservation(Product pro,IClient cli){
        this.pro=pro;
        this.cli=cli;
        ini=LocalDate.now();
        end=LocalDate.now().plusDays(2);
        if(end.getDayOfWeek()==DayOfWeek.SUNDAY){
            end=end.plusDays(1);
        }
        status=StatusReserve.ACTIVE;
    }

    public Reservation(Product pro, IClient cli, LocalDate ini, LocalDate end, LocalDate finished, StatusReserve status) {
        this.pro = pro;
        this.cli = cli;
        this.ini = ini;
        this.end = end;
        this.finished = finished;
        this.status = status;
    }
    
    public boolean equals(Object o){
        boolean result=false;
        if(o!=null){
            if(o instanceof Reservation){
                Reservation other=(Reservation)o;
                if(this.pro.equals(other.pro) 
                        &&this.cli.equals(other.cli)
                        &&this.ini.equals(other.ini)
                        &&this.end.equals(other.end)
                        &&this.status==other.status){
                    result=true;
                }
            }
        }
        return result;
    }
    
    @Override
    public int compareTo(Reservation o) {
        return cli.getName().compareTo(cli.getName());
    }

    @Override
    public String toString() {
        return "--------------RESERVA--------------" + 
                "\nNombre del producto: " + pro.getName() + 
                "\nEstado del producto: " + pro.getStatus() + 
                "\nCliente: " + cli.getName() + 
                "\nFecha de inicio de la reserva: " + ini + 
                "\nFecha de finalización de la reserva: " + end + 
                "\nEntrega del prodcuto:" + finished + 
                "\nEstado de la reserva:" + status +
                "\n";
    }
    
    
}
